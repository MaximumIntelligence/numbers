package com.mornhouse.numbers.presentation.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mornhouse.numbers.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
    }
}