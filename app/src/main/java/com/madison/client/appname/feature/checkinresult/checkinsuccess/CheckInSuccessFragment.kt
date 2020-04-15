package com.madison.client.appname.feature.checkinresult.checkinsuccess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.madison.client.appname.R
import com.madison.client.appname.data.model.Worker
import com.madison.client.appname.extention.customview.button.PrimaryButton
import com.madison.client.appname.feature.base.BaseFragment
import com.madison.client.appname.feature.qrcodescanner.QRCodeScannerActivity
import kotlinx.android.synthetic.main.fragment_checkin_success.*

class CheckInSuccessFragment : BaseFragment() {
    companion object {
        private const val KEY_WORKER_EXTRAS = "KEY_WORKER_EXTRAS"
        fun newInstance(worker: Worker): CheckInSuccessFragment {
            val bundle = Bundle()
            bundle.putParcelable(KEY_WORKER_EXTRAS, worker)

            val fragment = CheckInSuccessFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var worker: Worker? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkin_success, container, false)
    }

    override fun initView() {
        super.initView()

        arguments?.let {
            if (it.containsKey(KEY_WORKER_EXTRAS)) {
                worker = it.getParcelable(KEY_WORKER_EXTRAS)

                updateUI()
            }
        }
    }

    private fun updateUI() {
        worker?.let {
            tvName.text = it.name
            tvPassportNumber.text = it.idNo
            tvCovid19Test.text = it.status
            tvSite.text = it.project?.name ?: ""
        }
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