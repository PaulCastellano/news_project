package com.example.remote.service

import com.example.model.remote.base.PaginationResponse
import com.example.model.remote.base.SingleItemResponse
import com.example.model.remote.item.NewContent
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ContentService {
    @GET("search?show-fields=thumbnail&api-key=bab1fae0-d33d-4bfb-988e-8dfa0ba99d8f")
    suspend fun getNewsContentList(
        @Query("page") page: Int,
        @QueryMap options: Map<String, String>? = null
    ): PaginationResponse<NewContent>

    @GET("{id}?api-key=bab1fae0-d33d-4bfb-988e-8dfa0ba99d8f")
    suspend fun getNewContent(
        @Path(value = "id", encoded = true) id: String
    ): SingleItemResponse<NewContent>
}