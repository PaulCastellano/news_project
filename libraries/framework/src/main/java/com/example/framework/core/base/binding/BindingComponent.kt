package com.example.framework.core.base.binding

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.example.framework.extensions.inflater

open class BindingComponent<VB : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    lateinit var binding: VB

    init {
        initBinding()
    }

    private fun initBinding() {
        if (::binding.isInitialized.not()) {
            binding = getBinding(this.context.inflater, container = this)
        }
    }
}