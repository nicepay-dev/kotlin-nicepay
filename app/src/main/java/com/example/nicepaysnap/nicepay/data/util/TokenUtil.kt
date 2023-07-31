package com.example.nicepaysnap.nicepay.data.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.nicepaysnap.nicepay.model.accessData
import com.example.nicepaysnap.nicepay.model.utilInformation
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.sql.Timestamp
import java.text.SimpleDateFormat

open class TokenUtil {

    var utilInfo : utilInformation = utilInformation()
    val apiService = restApiService()
    var accTok : String? = ""

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAccessToken() = GlobalScope.async {
        val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        val ctime = Timestamp(System.currentTimeMillis())
        val dateTime = date.format(ctime)

        val stringToSign : String = utilInfo.clientKey + "|" + dateTime

        var gson = Gson()
        val signature : String? = encrypt.signSHA256RSA(stringToSign, utilInfo.privateKey)


        val accessData = accessData("client_credentials")
        val headers = HashMap<String, String>()
        headers["X-SIGNATURE"] = signature.toString()
        headers["X-CLIENT-KEY"] = utilInfo.clientKey
        headers["X-TIMESTAMP"] = dateTime

        apiService.generateAccessToken(headers, accessData) {
            Log.e("response code : ", it?.responseCode.toString())
            if (it?.responseCode != null) {
                // it = newly added user parsed as response
                accTok = it?.accessToken.toString()
                Log.e("accessTokenGeneratedSuccessfully ", accTok.toString())
            } else {
                Log.e("error :","Error registering access token")
            }
        }
    }

}