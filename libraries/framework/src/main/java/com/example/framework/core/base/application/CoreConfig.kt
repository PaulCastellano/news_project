package com.example.framework.core.base.application

abstract class CoreConfig {
    abstract fun appName(): String

    abstract fun baseUrl(): String

    abstract fun timeOut(): Long

    open fun isDev() = false

    open fun uncaughtExceptionPage(): Class<*>? = null

    open fun uncaughtExceptionMessage(): String? = null
}