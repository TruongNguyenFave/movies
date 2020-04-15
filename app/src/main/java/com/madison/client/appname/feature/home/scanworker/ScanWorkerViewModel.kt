package com.madison.client.appname.feature.home.scanworker

import androidx.lifecycle.MutableLiveData
import com.madison.client.appname.data.repository.UserRepository
import com.madison.client.appname.data.repository.remote.api.error.RetrofitException
import com.madison.client.appname.extention.helper.rx.SingleLiveEvent
import com.madison.client.appname.feature.base.BaseViewModel
import javax.inject.Inject

class ScanWorkerViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {
    val isLogoutLoadingObservable = MutableLiveData<Boolean>()
    var logoutSuccessObservable = SingleLiveEvent<Void>()
    var logoutErrorObservable = MutableLiveData<RetrofitException>()

    fun logout() {
        compositeDisposable.add(userRepository.logout().subscribeOn(schedulerProvider.io()).observeOn(
            schedulerProvider.ui()
        ).doOnSubscribe {
            isLogoutLoadingObservable.value = true
        }.doFinally {
            isLogoutLoadingObservable.value = false
        }.subscribe({
            logoutSuccessObservable.call()
        }, {
            if (it is RetrofitException) logoutErrorObservable.value = it
        })
        )
    }
}