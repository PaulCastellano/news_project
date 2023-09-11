package com.example.framework.extensions

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.observeFlow(property: Flow<T>, block: (T) -> Unit) {
    lifecycleScope.launch {
        property.collect { block(it) }
    }
}

fun <T> LifecycleOwner.observeFlowStart(property: Flow<T>, block: (T) -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            property.collect { block(it) }
        }
    }
}