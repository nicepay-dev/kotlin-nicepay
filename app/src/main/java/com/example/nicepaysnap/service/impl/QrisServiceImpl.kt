package com.example.nicepaysnap.service.impl

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.nicepaysnap.nicepay.data.util.TokenUtil
import com.example.nicepaysnap.nicepay.data.util.encrypt
import com.example.nicepaysnap.nicepay.model.BaseRequestRefund
import com.example.nicepaysnap.nicepay.model.RequestQrisInquiry
import com.example.nicepaysnap.nicepay.model.RequestQrisRefund
import com.example.nicepaysnap.nicepay.model.RequestQrisRegister
import com.example.nicepaysnap.service.MethodService
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.sql.Timestamp
import java.text.SimpleDateFormat

class QrisServiceImpl : TokenUtil(),
    MethodService<RequestQrisRegister, RequestQrisInquiry, RequestQrisRefund> {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun register(request: RequestQrisRegister): HashMap<String, String> {
        var responseQr = HashMap<String, String>()
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

        val convertJsonString = Gson().toJson(request)
        val hashBody = encrypt.sha256EncodeHex(convertJsonString)?.lowercase()

        val dateFormated = SimpleDateFormat("yyyyMMddhhmmssXXX").format(System.currentTimeMillis())
        Log.i("dateFormated = ", dateFormated)

        val stringToHash = "POST:"+ utilInfo.qrisEndPointUrl + ":" + accTok + ":" + hashBody + ":" + dateTime
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

        apiService.generateQr(headers, request) {
            Log.e("response code : ", it?.responseCode.toString())
            if (it?.responseCode != null) {
                responseQr = it?.toMap(HashMap()) as HashMap<String, String>
                Log.e("responseQrTes :", responseQr.toString())
            } else {
                Log.e("error :","Error registering Qris")
            }
        }
        while(responseQr.isEmpty()){
            delay(100)
        }
        Log.e("responseQr :", responseQr.toString())
        return responseQr
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun checkStatus(request: RequestQrisInquiry): HashMap<String, String> {
        var responseQr = HashMap<String, String>()
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

        val convertJsonString = Gson().toJson(request)
        val hashBody = encrypt.sha256EncodeHex(convertJsonString)?.lowercase()

        val dateFormated = SimpleDateFormat("yyyyMMddhhmmssXXX").format(System.currentTimeMillis())
        Log.i("dateFormated = ", dateFormated)

        val stringToHash = "POST:"+ utilInfo.qrisInquiryEndPointUrl + ":" + accTok + ":" + hashBody + ":" + dateTime
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

        apiService.checkStatusQr(headers, request) {
            Log.e("response code : ", it?.responseCode.toString())
            if (it?.responseCode != null) {
                responseQr = it?.toMap(HashMap()) as HashMap<String, String>
                Log.e("responseQrTes :", responseQr.toString())
            } else {
                Log.e("error :","Error Check Status Qris")
            }
        }
        while(responseQr.isEmpty()){
            delay(100)
        }
        Log.e("responseQr :", responseQr.toString())
        return responseQr
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun refund(request: RequestQrisRefund): HashMap<String, String> {
        var responseQr = HashMap<String, String>()
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

        val convertJsonString = Gson().toJson(request)
        val hashBody = encrypt.sha256EncodeHex(convertJsonString)?.lowercase()

        val dateFormated = SimpleDateFormat("yyyyMMddhhmmssXXX").format(System.currentTimeMillis())
        Log.i("dateFormated = ", dateFormated)

        val stringToHash = "POST:"+ utilInfo.qrisRefundEndPointUrl + ":" + accTok + ":" + hashBody + ":" + dateTime
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

        apiService.refundQr(headers, request) {
            Log.e("response code : ", it?.responseCode.toString())
            if (it?.responseCode != null) {
                responseQr = it?.toMap(HashMap()) as HashMap<String, String>
                Log.e("responseEWTes :", responseQr.toString())
            } else {
                Log.e("error :","Error Refund Qris")
            }
        }
        while(responseQr.isEmpty()){
            delay(100)
        }
        Log.e("responseEw :", responseQr.toString())
        return responseQr
    }

}