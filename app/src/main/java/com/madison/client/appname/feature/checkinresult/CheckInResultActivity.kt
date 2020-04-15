package com.madison.client.appname.feature.checkinresult

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.madison.client.appname.R
import com.madison.client.appname.data.model.Worker
import com.madison.client.appname.feature.base.BaseActivity
import com.madison.client.appname.feature.checkinresult.checkinfailed.CheckInFailedFragment
import com.madison.client.appname.feature.checkinresult.checkinsuccess.CheckInSuccessFragment

class CheckInResultActivity : BaseActivity() {
    companion object {
        const val KEY_WORKER_EXTRAS = "KEY_WORKER_EXTRAS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_checkin_result)

        val worker = intent.getParcelableExtra<Worker>(KEY_WORKER_EXTRAS)

        if (worker != null) {
            goToCheckInSuccessScreen(worker)
        } else {
            goToCheckInFailedScreen()
        }
    }

    private fun goToCheckInSuccessScreen(worker: Worker) {
        var currentFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (currentFragment == null) {
            currentFragment = CheckInSuccessFragment.newInstance(worker)
            navigator.addFragment(
                supportFragmentManager, currentFragment, R.id.contentFrame
            )
        }
    }

    private fun goToCheckInFailedScreen() {
        var currentFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (currentFragment == null) {
            currentFragment = CheckInFailedFragment.newInstance()
            navigator.addFragment(
                supportFragmentManager, currentFragment, R.id.contentFrame
            )
        }
    }
}