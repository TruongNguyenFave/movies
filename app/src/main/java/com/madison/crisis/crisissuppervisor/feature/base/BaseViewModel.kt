package com.madison.crisis.crisissuppervisor.feature.base

import androidx.lifecycle.ViewModel
import com.madison.crisis.crisissuppervisor.extention.helper.scheduler.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val schedulerProvider = AppSchedulerProvider()

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}