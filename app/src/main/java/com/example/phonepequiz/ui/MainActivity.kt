package com.example.phonepequiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.phonepequiz.ui.viewModels.LogoViewModel
import com.example.phonepequiz.R
import com.example.phonepequiz.utils.ViewModelFactory
import com.example.phonepequiz.repository.LogoRepository

class MainActivity : AppCompatActivity() {
    lateinit var logoViewModel: LogoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logoViewModel = ViewModelProviders.of(this).get(LogoViewModel::class.java)
    }
}
