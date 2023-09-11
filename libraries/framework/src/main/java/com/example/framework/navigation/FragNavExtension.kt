package com.example.framework.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.framework.R

fun FragmentActivity.showFragNav(builder: FragNav) {
    var fm = supportFragmentManager

    if (builder.manager != null) {
        fm = builder.manager
    }

    val ft = fm.beginTransaction()

    if (builder.clearBackStack) {
        fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    if (builder.dialogFragment != null) {
        builder.dialogFragment.show(fm, null)
    } else {
        val fragment = builder.fragment ?: return
        val tag = builder.tag
        val containerId = builder.viewId

        if (builder.animationType !== AnimationType.NO_ANIM) {
            val anim = AnimationType.getAnimation(builder.animationType)
            ft.setCustomAnimations(anim[0], anim[1], anim[2], anim[3])
        }

        if (builder.addToBackStack) {
            ft.addToBackStack(tag)
        }

        when (builder.transitionType) {
            TransitionType.ADD -> ft.add(containerId, fragment, tag)
            TransitionType.SHOW -> ft.show(fragment)
            TransitionType.HIDE -> ft.hide(fragment)
            else -> ft.replace(containerId, fragment, tag)
        }

        ft.commitAllowingStateLoss()
    }
}

fun FragmentActivity.navigateFragment(
    fragment: Fragment,
    addToBackStack: Boolean = true,
    fragmentManagerEnable: Boolean = false,
    clearBackStack: Boolean = false,
    @IdRes viewId: Int = R.id.nav_container,
    transitionType: TransitionType = TransitionType.REPLACE,
    animation: AnimationType = AnimationType.ENTER_FROM_RIGHT
) {
    val frag = FragNav.create {
        setFragment(fragment)
        setFragmentManager(if (fragmentManagerEnable) supportFragmentManager else null)
        setAddToBackStack(addToBackStack)
        setClearBackStack(clearBackStack)
        setViewId(viewId)
        setTransitionType(transitionType)
        setAnimation(animation)
    }

    showFragNav(frag)
}

fun Fragment.navigateFragment(
    fragment: Fragment,
    addToBackStack: Boolean = true,
    fragmentManagerEnable: Boolean = false,
    clearBackStack: Boolean = false,
    @IdRes viewId: Int = R.id.nav_container,
    transitionType: TransitionType = TransitionType.REPLACE,
    animation: AnimationType = AnimationType.NO_ANIM
) {
    val fragNav = FragNav.create {
        setFragment(fragment)
        setFragmentManager(if (fragmentManagerEnable) childFragmentManager else null)
        setAddToBackStack(addToBackStack)
        setClearBackStack(clearBackStack)
        setViewId(viewId)
        setTransitionType(transitionType)
        setAnimation(animation)
    }

    activity?.showFragNav(fragNav)
}

/*
 **************************************************************
 **************************************************************
 */

/**
 * val fragment = supportFragmentManager.instantiate(fragmentClassName)
 * val fragment = childFragmentManager.instantiate(fragmentClassName)
 */

fun FragmentManager.instantiate(className: String): Fragment {
    return fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), className)
}

fun FragmentActivity.classFragment(className: String): Fragment {
    return supportFragmentManager.instantiate(className)
}
