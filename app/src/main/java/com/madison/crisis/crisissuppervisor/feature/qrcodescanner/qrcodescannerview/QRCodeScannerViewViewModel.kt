package com.madison.crisis.crisissuppervisor.feature.qrcodescanner.qrcodescannerview

import androidx.lifecycle.MutableLiveData
import com.madison.crisis.crisissuppervisor.data.model.Worker
import com.madison.crisis.crisissuppervisor.data.repository.UserRepository
import com.madison.crisis.crisissuppervisor.data.repository.remote.api.error.RetrofitException
import com.madison.crisis.crisissuppervisor.feature.base.BaseViewModel
import javax.inject.Inject

class QRCodeScannerViewViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    val isCheckInLoadingObservable = MutableLiveData<Boolean>()
    var checkInSuccessObservable = MutableLiveData<Worker>()
    var checkInErrorObservable = MutableLiveData<RetrofitException>()

    fun checkIn(qrCode: String) {
        compositeDisposable.add(userRepository.checkIn(qrCode).subscribeOn(schedulerProvider.io()).observeOn(
            schedulerProvider.ui()
        ).doOnSubscribe {
            isCheckInLoadingObservable.value = true
        }.doFinally {
            isCheckInLoadingObservable.value = false
        }.subscribe({
            checkInSuccessObservable.value = it
        }, {
            if (it is RetrofitException) checkInErrorObservable.value = it
        })
        )
    }

}