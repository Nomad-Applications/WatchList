package com.oguzhancakmak.watchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("ahmet","dljasdjdkl")
        Log.e("dasd","dljasdjdkl")
        Log.e("ahmdet","dljasdjdkl")

        Log.e("Tag","line12")
        Log.e("Tag","line13")
        Log.e("Tag","line14")
    }
     fun onCr(savedInstanceState: Bundle?) {
         Log.e("ahmdet","dljasdjdkl")
    }

}
