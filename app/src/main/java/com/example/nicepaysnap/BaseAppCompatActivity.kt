package com.example.nicepaysnap

import androidx.appcompat.app.AppCompatActivity
import com.example.nicepaysnap.nicepay.data.util.EwalletSnapUtil
import kotlinx.coroutines.delay

open class BaseAppCompatActivity : AppCompatActivity() {

    var responseEw = HashMap<String, String>()
    var ewalletSnapUtil : EwalletSnapUtil = EwalletSnapUtil()

    suspend fun parseValue(responseData: HashMap<String, String>) {
        // perform parsing operation asynchronously
        responseEw = responseData
        while(responseEw.isEmpty()){
            delay(100)
        }
    }

}