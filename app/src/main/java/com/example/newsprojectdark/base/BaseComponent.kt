package com.example.newsprojectdark.base

import android.content.Context
import android.util.AttributeSet
import androidx.viewbinding.ViewBinding
import com.example.framework.core.base.core.CoreComponent

abstract class BaseComponent<VB : ViewBinding>(context: Context, attrs: AttributeSet? = null) :
    CoreComponent<VB>(context, attrs)