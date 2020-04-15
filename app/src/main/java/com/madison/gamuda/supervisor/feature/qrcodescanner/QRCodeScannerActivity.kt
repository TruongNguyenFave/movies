package com.madison.gamuda.supervisor.feature.qrcodescanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.madison.gamuda.supervisor.R
import com.madison.gamuda.supervisor.feature.base.BaseActivity
import com.madison.gamuda.supervisor.feature.qrcodescanner.qrcodescannerview.QRCodeScannerFragment

class QRCodeScannerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_scanner)

        initView()
    }

    private fun initView() {
        var qrCodeScannerFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (qrCodeScannerFragment == null) {
            qrCodeScannerFragment = QRCodeScannerFragment.newInstance()
            navigator.addFragment(
                supportFragmentManager, qrCodeScannerFragment, R.id.contentFrame
            )
        }
    }
}