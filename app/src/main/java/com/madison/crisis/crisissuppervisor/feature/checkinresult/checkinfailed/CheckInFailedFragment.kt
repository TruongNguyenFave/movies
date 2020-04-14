package com.madison.crisis.crisissuppervisor.feature.checkinresult.checkinfailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.madison.crisis.crisissuppervisor.R
import com.madison.crisis.crisissuppervisor.extention.customview.button.PrimaryButton
import com.madison.crisis.crisissuppervisor.feature.base.BaseFragment
import com.madison.crisis.crisissuppervisor.feature.qrcodescanner.QRCodeScannerActivity
import kotlinx.android.synthetic.main.fragment_checkin_failed.*

class CheckInFailedFragment : BaseFragment() {
    companion object {
        fun newInstance(): CheckInFailedFragment {
            return CheckInFailedFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkin_failed, container, false)
    }

    override fun handleEvent() {
        super.handleEvent()
        btnScanAgain.listener = object : PrimaryButton.OnPrimaryButtonListener {
            override fun onClick(view: View?) {
                navigator.startActivity(requireActivity(), QRCodeScannerActivity::class.java)
                onBackPressedCallback()
            }
        }
    }

    override fun onBackPressedCallback() {
        super.onBackPressedCallback()
        requireActivity().finish()
    }
}