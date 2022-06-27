package com.example.quickup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quickup.R
import com.example.quickup.databinding.FragmentSignUpBinding
import com.example.quickup.showSnackbar

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { SignUpFragmentViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpFragmentObject = this
        initObservers()
    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {

                isInfosValid.observe(viewLifecycleOwner) {
                    if (it.not()) showSnackbar(
                        requireView(), "incomplete_information_entered"
                    )
                }

                isValidMail.observe(viewLifecycleOwner) {
                    if (it.not()) {
                        emailInputLayout.error = "invalid_mail"
                    } else {
                        emailInputLayout.error = ""
                    }
                }

                isPasswordMatch.observe(viewLifecycleOwner) {
                    if (it.not()) {
                        passwordInputLayout.error = "password_match_error"
                        confirmPasswordInputLayout.error = "password_match_error"
                    } else {
                        passwordInputLayout.error = ""
                        confirmPasswordInputLayout.error = ""
                    }
                }

                isSignUp.observe(viewLifecycleOwner) {
                    if (it) {
                        showSnackbar(requireView(), "sign_up_snack_text")
                        clearFields()
                    } else {
                        emailInputLayout.error = "registered_mail"
                    }
                }
            }
        }
    }



    fun signUpButton(
        email: String,
        password: String,
        confirmPassword: String,

    ) {
        viewModel.signUp(email, password, confirmPassword)
    }

    private fun clearFields() {
        with(binding) {
            emailEditText.setText("")
            emailInputLayout.error = ""
            passwordEditText.setText("")
            passwordInputLayout.error = ""
            confirmPasswordEditText.setText("")
            confirmPasswordInputLayout.error = ""

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}