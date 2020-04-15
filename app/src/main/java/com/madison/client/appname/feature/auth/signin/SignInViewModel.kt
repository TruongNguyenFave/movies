package com.madison.client.appname.feature.auth.signin

import androidx.lifecycle.MutableLiveData
import com.madison.client.appname.data.model.User
import com.madison.client.appname.data.repository.UserRepository
import com.madison.client.appname.data.repository.local.api.AccessTokenWrapper
import com.madison.client.appname.data.repository.remote.api.error.RetrofitException
import com.madison.client.appname.feature.base.BaseViewModel
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private var userRepository: UserRepository, private var accessTokenWrapper: AccessTokenWrapper
) : BaseViewModel() {
    val isLoginLoadingObservable = MutableLiveData<Boolean>()
    var loginSuccessObservable = MutableLiveData<User>()
    var loginErrorObservable = MutableLiveData<RetrofitException>()

    fun login(userName: String, password: String) {
        compositeDisposable.add(userRepository.signIn(
            userName, password
        ).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui()).doOnSubscribe {
            isLoginLoadingObservable.value = true
        }.doFinally {
            isLoginLoadingObservable.value = false
        }.subscribe({
            accessTokenWrapper.saveAccessToken(it.password ?: "")

            loginSuccessObservable.value = it
        }, {
            if (it is RetrofitException) loginErrorObservable.value = it
        })
        )
    }
}