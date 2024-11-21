package com.karla.cookyhome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karla.cookyhome.repository.UserRepository
import com.karla.cookyhome.utils.base.BaseViewModel


class SplashViewModel : BaseViewModel() {
    private var isLoginStatus = MutableLiveData<Boolean>()
    private var splashScreenRepository: UserRepository = UserRepository()

    fun isLogin() {
        isLoginStatus.value = splashScreenRepository.isLogin()
    }

    fun getIsLogin(): LiveData<Boolean> {
        return isLoginStatus
    }
}