package com.example.nicepaysnap.view

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.nicepaysnap.R
import com.example.nicepaysnap.service.impl.EwalletServiceImpl
import kotlinx.coroutines.delay

open class BaseAppCompatActivity : AppCompatActivity() {

    var responseRest = HashMap<String, String>()
    lateinit var submit: Button
    lateinit var buttonCloseLayout : ImageView

    suspend fun parseValue(responseData: HashMap<String, String>) {
        // perform parsing operation asynchronously
        responseRest = responseData
        while(responseRest.isEmpty()){
            delay(100)
        }
    }

    fun determineLayout(int : Int) {
        setContentView(int)
        submit = findViewById(R.id.submitButton)
        buttonCloseLayout = findViewById(R.id.buttonCloseLayout)
    }

}