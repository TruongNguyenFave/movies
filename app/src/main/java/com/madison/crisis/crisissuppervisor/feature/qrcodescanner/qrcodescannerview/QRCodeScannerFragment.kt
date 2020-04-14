package com.madison.crisis.crisissuppervisor.feature.qrcodescanner.qrcodescannerview

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.madison.crisis.crisissuppervisor.R
import com.madison.crisis.crisissuppervisor.data.model.Worker
import com.madison.crisis.crisissuppervisor.extention.customview.scancode.*
import com.madison.crisis.crisissuppervisor.extention.helper.permission.RequestPermissionHandler
import com.madison.crisis.crisissuppervisor.extention.helper.viewextension.handleVisibility
import com.madison.crisis.crisissuppervisor.extention.helper.viewextension.isVisible
import com.madison.crisis.crisissuppervisor.feature.base.BaseFragment
import com.madison.crisis.crisissuppervisor.feature.checkinresult.CheckInResultActivity
import com.madison.crisis.crisissuppervisor.feature.checkinresult.CheckInResultActivity.Companion.KEY_WORKER_EXTRAS
import kotlinx.android.synthetic.main.fragment_qr_code_scanner.*

class QRCodeScannerFragment : BaseFragment() {
    companion object {
        fun newInstance(): QRCodeScannerFragment {
            return QRCodeScannerFragment()
        }

    }

    lateinit var qRCodeScannerViewViewModel: QRCodeScannerViewViewModel

    private lateinit var requestPermissionHandler: RequestPermissionHandler

    private var codeScanner: CodeScanner? = null

    private var isFromShopDetailScreen: Boolean = false

    private var errorDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        qRCodeScannerViewViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(QRCodeScannerViewViewModel::class.java)
        return inflater.inflate(R.layout.fragment_qr_code_scanner, container, false)
    }

    override fun initView() {
        super.initView()
        requestPermissionHandler = RequestPermissionHandler(requireActivity())

        requestPermissionForScanner()
    }

    private fun requestPermissionForScanner() {
        requestPermissionHandler.requestPermissions(
            listOf(
                Manifest.permission.CAMERA
            ), object : RequestPermissionHandler.RequestPermissionHandlerListener {
                override fun onPermissionsGranted() {
                    setupQRCodeScanner()
                }
            }, getString(R.string.camera_access_required), getString(R.string.camera_access_msg)
        )
    }

    override fun handleEvent() {
        super.handleEvent()
    }

    private fun setupQRCodeScanner() {
        codeScanner = CodeScanner(requireContext(), scannerView)

        codeScanner?.let {
            // Parameters (default values)
            it.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
            it.formats = CodeScanner.TWO_DIMENSIONAL_FORMATS // list of type BarcodeFormat,

            it.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
            it.scanMode = ScanMode.CONTINUOUS // or CONTINUOUS or PREVIEW
            it.isAutoFocusEnabled = true // Whether to enable auto focus or not
            it.isFlashEnabled = false // Whether to enable flash or not

            it.decodeCallback = DecodeCallback { result ->
                requireActivity().runOnUiThread {
                    if (loadingView?.isVisible() == false) {
                        Log.e("Gianhtran", result)
                        qRCodeScannerViewViewModel.checkIn(result)
                    }
                }
            }
            it.errorCallback = ErrorCallback { exception ->
                exception.message?.let { errorMsg ->
                    navigator.showErrorSnackbar(requireActivity(), errorMsg)
                }
            }
            it.startPreview()
        }
    }

    override fun observeLiveData() {
        super.observeLiveData()
        qRCodeScannerViewViewModel.isCheckInLoadingObservable.observe(viewLifecycleOwner, Observer {
            loadingView?.handleVisibility(it)
        })

        qRCodeScannerViewViewModel.checkInSuccessObservable.observe(viewLifecycleOwner, Observer {
            goToResultScreen(it)
        })

        qRCodeScannerViewViewModel.checkInErrorObservable.observe(viewLifecycleOwner, Observer {
            goToResultScreen()
        })
    }

    private fun goToResultScreen(worker: Worker? = null) {
        val bundle = Bundle()
        bundle.putParcelable(KEY_WORKER_EXTRAS, worker)
        navigator.startActivity(requireActivity(), CheckInResultActivity::class.java, bundle)
        onBackPressedCallback()
    }

    override fun onDestroy() {
        codeScanner?.releaseResources()
        super.onDestroy()
    }

    override fun onBackPressedCallback() {
        super.onBackPressedCallback()
        requireActivity().finish()
    }
}