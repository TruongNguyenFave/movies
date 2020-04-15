package com.madison.client.appname.feature.base

import androidx.lifecycle.ViewModel
import com.madison.client.appname.extention.helper.scheduler.AppSchedulerProvider
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