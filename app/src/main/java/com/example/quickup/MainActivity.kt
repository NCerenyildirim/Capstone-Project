package com.example.quickup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.quickup.R
import com.example.quickup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView4) as NavHostFragment
        NavigationUI.setupWithNavController(binding.mainbottomnav, navHostFragment.navController)
    }
}