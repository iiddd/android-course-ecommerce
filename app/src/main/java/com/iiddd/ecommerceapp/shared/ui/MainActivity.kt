package com.iiddd.ecommerceapp.shared.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iiddd.ecommerceapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}