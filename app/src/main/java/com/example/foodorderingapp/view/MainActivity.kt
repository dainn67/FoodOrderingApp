package com.example.foodorderingapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.foodorderingapp.R
import com.example.foodorderingapp.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by lazy {
        ViewModelProvider(this)[MyViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}