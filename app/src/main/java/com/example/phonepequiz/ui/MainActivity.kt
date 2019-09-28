package com.example.phonepequiz.ui

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.phonepequiz.R
import com.example.phonepequiz.databinding.ActivityMainBinding
import com.example.phonepequiz.ui.adapter.CharacterAdapter
import com.example.phonepequiz.ui.viewModels.LogoViewModel

class MainActivity : AppCompatActivity() {
    lateinit var logoViewModel: LogoViewModel
    lateinit var dataBinding: ActivityMainBinding
    lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        characterAdapter = CharacterAdapter()
        logoViewModel = ViewModelProviders.of(this).get(LogoViewModel::class.java)
        dataBinding.rvJumbledOption.layoutManager = GridLayoutManager(this, 10)
        dataBinding.rvJumbledOption.adapter = characterAdapter
        attachObservers()
    }

    fun attachObservers() {
        logoViewModel.uiActions.observe(this, Observer {
            when (it) {
                LogoViewModel.TAKE_TO_RESULT -> {
                    //start result activity
                }
            }
        })
        logoViewModel.question.observe(this, Observer {
            Glide.with(this@MainActivity).load(it.logo.imageUrl).into(dataBinding.logoView)
            characterAdapter.characterList = it.jumbledAnswer as MutableList<Char>
            characterAdapter.notifyDataSetChanged()
        })
    }
}
