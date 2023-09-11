package com.example.framework.core.base.application

interface CoreConfigProvider<T : CoreConfig> {
    fun appConfig(): T
}