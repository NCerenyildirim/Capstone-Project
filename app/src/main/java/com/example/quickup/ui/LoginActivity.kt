package com.example.quickup.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.databinding.DataBindingUtil
import com.example.quickup.Constants.SIGN_IN
import com.example.quickup.MainActivity
import com.example.quickup.R
import com.example.quickup.databinding.ActivityLoginBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.analytics.FirebaseAnalytics.Event.SIGN_UP
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        Firebase.auth.currentUser?.let {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val titleList = arrayListOf(SIGN_IN, SIGN_UP)

        binding.useradapter.adapter = UserAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.loginTabLayout, binding.useradapter) { tab, position ->
            tab.text = titleList[position]
        }.attach()
    }
}