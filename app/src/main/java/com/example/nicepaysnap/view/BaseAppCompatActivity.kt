package com.example.nicepaysnap.view

import androidx.appcompat.app.AppCompatActivity
import com.example.nicepaysnap.service.impl.EwalletServiceImpl
import kotlinx.coroutines.delay

open class BaseAppCompatActivity : AppCompatActivity() {

    var responseRest = HashMap<String, String>()

    suspend fun parseValue(responseData: HashMap<String, String>) {
        // perform parsing operation asynchronously
        responseRest = responseData
        while(responseRest.isEmpty()){
            delay(100)
        }
    }

}