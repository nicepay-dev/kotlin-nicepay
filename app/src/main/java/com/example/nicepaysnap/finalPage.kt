package com.example.nicepaysnap

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class finalPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_page)

        val data = intent.getSerializableExtra("data")
        Log.e("we got data", data.toString())
    }
}