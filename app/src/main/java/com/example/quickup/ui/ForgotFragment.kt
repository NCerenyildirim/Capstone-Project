package com.example.quickup.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quickup.MainActivity
import com.example.quickup.R
import com.example.quickup.databinding.FragmentForgotBinding
import com.example.quickup.showSnackbar


class ForgotFragment : Fragment() {

        private var _binding: FragmentForgotBinding? = null
        private val binding get() = _binding!!

        private val viewModel by lazy { ForgotFragmentViewModel() }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.forgotFragmentObject = this

            with(viewModel) {

                isForgot.observe(viewLifecycleOwner) {
                    if (it) {
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        showSnackbar(view, "wrong_email_password")
                    }
                }
            }
        }

        fun forgotButton(email: String) {
            viewModel.forgot(email)
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }