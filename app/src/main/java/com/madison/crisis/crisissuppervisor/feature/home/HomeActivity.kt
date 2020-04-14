package com.madison.crisis.crisissuppervisor.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.madison.crisis.crisissuppervisor.R
import com.madison.crisis.crisissuppervisor.feature.base.BaseActivity
import com.madison.crisis.crisissuppervisor.feature.home.scanworker.ScanWorkerFragment

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        goToScanWorkerScreen()
    }

    private fun goToScanWorkerScreen() {
        var scanWorkerFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (scanWorkerFragment == null) {
            scanWorkerFragment = ScanWorkerFragment.newInstance()
            navigator.addFragment(
                supportFragmentManager, scanWorkerFragment, R.id.contentFrame
            )
        }
    }
}