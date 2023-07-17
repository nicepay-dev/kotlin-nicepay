package com.example.nicepaysnap.nicepay.data.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.nicepaysnap.nicepay.model.accessData
import com.example.nicepaysnap.nicepay.model.utilInformation
import com.example.nicepaysnap.nicepay.model.vaComponent
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import java.sql.Timestamp
import java.text.SimpleDateFormat

class EwalletSnapUtil {

    var utilInfo : utilInformation = utilInformation()
    val apiService = restApiService()
    var accTok : String? = ""

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun payment(componentModel : vaComponent): HashMap<String, String> {
        var responseVA = HashMap<String, String>()
        getAccessToken().await()
        while(accTok.equals("")){
            delay(100)
        }

        Log.e("Akses Token : ", accTok.toString())
        val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        val ctime = Timestamp(System.currentTimeMillis())
        val dateTime = date.format(ctime)

        val convertJsonString = Gson().toJson(componentModel)
        val hashBody = encrypt.sha256EncodeHex(convertJsonString)?.lowercase()

        val dateFormated = SimpleDateFormat("yyyyMMddhhmmssXXX").format(System.currentTimeMillis())

        val stringToHash = "POST:"+ utilInfo.endPointUrl + ":" + accTok + ":" + hashBody + ":" + dateTime
        Log.e("Before", stringToHash)
        val afterHash = encrypt.hmacSha512encodeBase64(utilInfo.secretKey, stringToHash)
        Log.e("After", afterHash.toString())

        val headers = HashMap<String, String>()
        headers["X-SIGNATURE"] = afterHash.toString()
        headers["X-CLIENT-KEY"] = utilInfo.clientKey
        headers["X-TIMESTAMP"] = dateTime
        headers["Authorization"] = "Bearer " + accTok
        headers["CHANNEL-ID"] = utilInfo.clientKey
        headers["X-EXTERNAL-ID"] = utilInfo.clientKey + "" + dateFormated
        headers["X-PARTNER-ID"] = utilInfo.clientKey


        Log.e("body request", componentModel.toString())
        Log.e("body", componentModel.virtualAccountName.toString())
        apiService.generateVirtualAccount(headers, componentModel) {
            Log.e("response code : ", it?.responseCode.toString())
            if (it?.responseCode != null) {
                responseVA = it?.toMap(HashMap()) as HashMap<String, String>
                Log.e("responseVATes :", responseVA.toString())
            } else {
                Log.e("error :","Error registering virtual account")
            }
        }
        while(responseVA.isEmpty()){
            delay(100)
        }
        Log.e("responseVA :", responseVA.toString())
        return responseVA
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getAccessToken() = GlobalScope.async {
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
