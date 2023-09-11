package com.example.framework.extensions
inline fun <reified T : Any> Any.cast(): T {
    return this as T
}