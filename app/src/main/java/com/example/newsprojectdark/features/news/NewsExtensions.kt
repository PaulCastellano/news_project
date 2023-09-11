package com.example.newsprojectdark.features.news

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import com.example.newsprojectdark.R

fun ImageView.startAnimation(state: Boolean, onDoEnd: () -> Unit) {
    val rotationAnim = if (state) {
        ObjectAnimator.ofFloat(this, "rotation", 0f, 360f)
    } else {
        ObjectAnimator.ofFloat(this, "rotation", 360f, 0f)
    }.apply {
        duration = 300
        interpolator = AccelerateInterpolator()
    }

    val bounceAnimX = ObjectAnimator.ofFloat(this, "scaleX", 0.2f, 1f).apply {
        duration = 300
        interpolator = OvershootInterpolator()
    }

    val bounceAnimY = ObjectAnimator.ofFloat(this, "scaleY", 0.2f, 1f).apply {
        duration = 300
        interpolator = OvershootInterpolator()
    }

    val colorAnim = if (state) {
        ObjectAnimator.ofArgb(
            this, "colorFilter",
            ContextCompat.getColor(this.context, R.color.grey_700),
            ContextCompat.getColor(this.context, R.color.orange)
        )
    } else {
        ObjectAnimator.ofArgb(
            this, "colorFilter",
            ContextCompat.getColor(this.context, R.color.orange),
            ContextCompat.getColor(this.context, R.color.grey_700)
        )
    }.apply {
        duration = 600
        interpolator = AccelerateDecelerateInterpolator()
    }

    AnimatorSet().apply {
        play(rotationAnim).with(colorAnim)
        play(bounceAnimX).with(bounceAnimY).after(rotationAnim)
        doOnEnd {
            onDoEnd.invoke()
        }
        start()
    }
}