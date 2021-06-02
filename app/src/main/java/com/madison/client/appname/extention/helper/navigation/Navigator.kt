package com.madison.client.appname.extention.helper.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import javax.inject.Inject

class Navigator @Inject constructor() {
    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int,
        addToBackStack: Boolean = false,
        tag: String? = null
    ) {
        fragmentManager.beginTransaction().also {
            it.add(frameId, fragment, tag)
            if (addToBackStack) it.addToBackStack(null)
            it.commit()
        }
    }
}