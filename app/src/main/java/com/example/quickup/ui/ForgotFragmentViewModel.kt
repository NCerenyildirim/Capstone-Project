package com.example.quickup.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quickup.data.repository.UsersRepository

class ForgotFragmentViewModel : ViewModel() {

    private var usersRepo = UsersRepository()

    private var _isforgot = MutableLiveData<Boolean>()
    val isForgot: LiveData<Boolean>
    get() = _isforgot

    init {
        _isforgot = usersRepo.isForgot
    }

    fun forgot(eMail: String) {
        usersRepo.forgot(eMail)
    }
}
