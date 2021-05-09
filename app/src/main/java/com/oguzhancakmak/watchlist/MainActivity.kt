package com.oguzhancakmak.watchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("Tag","line12")
        Log.e("Tag","line13")
        Log.e("Tag","line14")
    }
}