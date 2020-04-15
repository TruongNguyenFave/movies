package com.madison.client.appname.feature.home.scanworker

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.madison.client.appname.R
import com.madison.client.appname.extention.customview.button.PrimaryButton
import com.madison.client.appname.feature.auth.AuthActivity
import com.madison.client.appname.feature.auth.AuthActivity.Companion.KEY_AUTH_EXTRAS
import com.madison.client.appname.feature.base.BaseFragment
import com.madison.client.appname.feature.qrcodescanner.QRCodeScannerActivity
import kotlinx.android.synthetic.main.fragment_scan_worker.*

class ScanWorkerFragment : BaseFragment() {
    companion object {
        fun newInstance(): ScanWorkerFragment {
            return ScanWorkerFragment()
        }
    }

    lateinit var scanWorkerViewModel: ScanWorkerViewModel

    lateinit var logoutHandler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        scanWorkerViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ScanWorkerViewModel::class.java)
        return inflater.inflate(R.layout.fragment_scan_worker, container, false)
    }

    override fun initView() {
        super.initView()
        logoutHandler = Handler()
    }

    override fun handleEvent() {
        super.handleEvent()
        btnScan.listener = object : PrimaryButton.OnPrimaryButtonListener {
            override fun onClick(view: View?) {
                navigator.startActivity(requireActivity(), QRCodeScannerActivity::class.java)
            }
        }

        btnLogout.listener = object : PrimaryButton.OnPrimaryButtonListener {
            override fun onClick(view: View?) {
                scanWorkerViewModel.logout()
            }
        }
    }

    override fun observeLiveData() {
        super.observeLiveData()
        scanWorkerViewModel.isLogoutLoadingObservable.observe(viewLifecycleOwner, Observer {
            btnLogout.handleLoading(it)
        })

        scanWorkerViewModel.logoutSuccessObservable.observe(viewLifecycleOwner, Observer {
            logoutHandler.postDelayed({
                val bundle = Bundle()
                bundle.putBoolean(KEY_AUTH_EXTRAS, true)

                navigator.startActivityAtRoot(
                    requireActivity(), AuthActivity::class.java, bundle
                )
            }, 100)
        })

        scanWorkerViewModel.logoutErrorObservable.observe(viewLifecycleOwner, Observer {
            handleShowServerError(it)
        })
    }

    override fun onDestroy() {
        logoutHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    override fun onBackPressedCallback() {
        super.onBackPressedCallback()
        requireActivity().finish()
    }
}