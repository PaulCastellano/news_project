package com.example.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.framework.core.base.application.CoreConfig
import com.example.remote.service.ContentService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


private const val BASE_URL = "https://content.guardianapis.com"
private const val CONTENT_LENGTH = 250_000L

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    @Named(value = BASE_URL)
    fun provideBaseUrl(config: CoreConfig): String {
        return config.baseUrl()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(config: CoreConfig): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (!config.isDev()) {
                HttpLoggingInterceptor.Level.NONE
            } else {
                HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        // Create the Collector
        val chuckerCollector = ChuckerCollector(
            context = context,
            // Toggles visibility of the push notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(chuckerCollector)
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(CONTENT_LENGTH)
            // List of headers to replace with ** in the Chucker UI
            .redactHeaders("Auth-Token", "Bearer")
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        config: CoreConfig,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            if (config.isDev()) {
                addInterceptor(chuckerInterceptor)
            }
            connectTimeout(config.timeOut(), TimeUnit.SECONDS)
            readTimeout(config.timeOut(), TimeUnit.SECONDS)
            writeTimeout(config.timeOut(), TimeUnit.SECONDS)
            followSslRedirects(true)
            followRedirects(true)
            retryOnConnectionFailure(true)
            addInterceptor(logging)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named(value = BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): ContentService {
        return retrofit.create(ContentService::class.java)
    }
}