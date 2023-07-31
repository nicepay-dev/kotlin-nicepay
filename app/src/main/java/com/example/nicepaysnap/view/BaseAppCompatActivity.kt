package com.example.nicepaysnap.view

import androidx.appcompat.app.AppCompatActivity
import com.example.nicepaysnap.service.impl.EwalletServiceImpl
import kotlinx.coroutines.delay

open class BaseAppCompatActivity : AppCompatActivity() {

    var responseEw = HashMap<String, String>()

    suspend fun parseValue(responseData: HashMap<String, String>) {
        // perform parsing operation asynchronously
        responseEw = responseData
        while(responseEw.isEmpty()){
            delay(100)
        }
    }

}