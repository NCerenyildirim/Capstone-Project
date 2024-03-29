package com.example.quickup.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quickup.data.repository.UsersRepository

class SignUpFragmentViewModel : ViewModel() {

    private var usersRepo = UsersRepository()

    private var _isInfosValid = MutableLiveData<Boolean>()
    val isInfosValid: LiveData<Boolean> = _isInfosValid

    private var _isValidMail = MutableLiveData<Boolean>()
    val isValidMail: LiveData<Boolean> = _isValidMail

    private var _isPasswordMatch = MutableLiveData<Boolean>()
    val isPasswordMatch: LiveData<Boolean> = _isPasswordMatch

    private var _isSignUp = MutableLiveData<Boolean>()
    val isSignUp: LiveData<Boolean> = _isSignUp

    init {
        _isSignUp = usersRepo.isSignUp
    }

    fun signUp(
        eMail: String,
        password: String,
        confirmPassword: String

    ) {

        if (eMail.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            _isInfosValid.value = false
        } else {

            if (Patterns.EMAIL_ADDRESS.matcher(eMail).matches().not()) {
                _isValidMail.value = false
            } else {
                if (password != confirmPassword) {
                    _isPasswordMatch.value = false
                } else {
                    usersRepo.signUp(eMail, password)
                }
            }
        }
    }

}