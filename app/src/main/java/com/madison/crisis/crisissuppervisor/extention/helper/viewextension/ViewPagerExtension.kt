package com.madison.crisis.crisissuppervisor.extention.helper.viewextension

import androidx.viewpager.widget.ViewPager

fun ViewPager.onPageSelected(onPageSelected: (Int) -> Unit) {
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            // do nothing
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            // do nothing
        }

        override fun onPageSelected(position: Int) {
            onPageSelected.invoke(position)
        }

    })
}