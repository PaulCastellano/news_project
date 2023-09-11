package com.example.framework.extensions

import android.content.Context
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View

val Context.inflater: LayoutInflater get() = LayoutInflater.from(this)

fun View.setSafeOnClickListener(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}