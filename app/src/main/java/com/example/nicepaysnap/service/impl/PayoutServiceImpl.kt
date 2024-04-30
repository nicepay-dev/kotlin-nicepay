package com.example.nicepaysnap.service.impl

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.nicepaysnap.nicepay.data.util.TokenUtil
import com.example.nicepaysnap.nicepay.data.util.encrypt
import com.example.nicepaysnap.nicepay.model.RequestPayoutCancel
import com.example.nicepaysnap.nicepay.model.RequestPayoutInquiry
import com.example.nicepaysnap.nicepay.model.RequestPayoutRegistration
import com.example.nicepaysnap.service.MethodService
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.sql.Timestamp
import java.text.SimpleDateFormat

class PayoutServiceImpl : TokenUtil(), MethodService<RequestPayoutRegistration, RequestPayoutInquiry, RequestPayoutCancel> {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun register(request: RequestPayoutRegistration): HashMap<String, String> {
        var responsePayout = HashMap<String, String>()
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

        val stringToHash = "POST:"+ utilInfo.payoutEndPointUrl + ":" + accTok + ":" + hashBody + ":" + dateTime
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

        apiService.generatePayout(headers, request) {
            Log.e("response code : ", it?.responseCode.toString())
            if (it?.responseCode != null) {
                responsePayout = it?.toMap(HashMap()) as HashMap<String, String>
                Log.e("responsePayoutTes :", responsePayout.toString())
            } else {
                Log.e("error :","Error registering payout")
            }
        }
        while(responsePayout.isEmpty()){
            delay(100)
        }
        Log.e("responseEw :", responsePayout.toString())
        return responsePayout
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun checkStatus(request: RequestPayoutInquiry): HashMap<String, String> {
        var responsePayoutInquiry = HashMap<String, String>()
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

        val stringToHash = "POST:"+ utilInfo.payoutInquiryEndPointUrl + ":" + accTok + ":" + hashBody + ":" + dateTime
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

        apiService.checkStatusPayout(headers, request) {
            Log.e("response code : ", it?.responseCode.toString())
            if (it?.responseCode != null) {
                responsePayoutInquiry = it?.toMap(HashMap()) as HashMap<String, String>
                Log.e("responsePayoutTes :", responsePayoutInquiry.toString())
            } else {
                Log.e("error :","Error registering payout")
            }
        }
        while(responsePayoutInquiry.isEmpty()){
            delay(100)
        }
        Log.e("responsePayout :", responsePayoutInquiry.toString())
        return responsePayoutInquiry
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun refund(request: RequestPayoutCancel): HashMap<String, String> {
        var responsePayoutCancel = HashMap<String, String>()
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

        val stringToHash = "POST:"+ utilInfo.payoutRefundEndPointUrl + ":" + accTok + ":" + hashBody + ":" + dateTime
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

        apiService.cancelPayout(headers, request) {
            Log.e("response code : ", it?.responseCode.toString())
            if (it?.responseCode != null) {
                responsePayoutCancel = it?.toMap(HashMap()) as HashMap<String, String>
                Log.e("responseEWTes :", responsePayoutCancel.toString())
            } else {
                Log.e("error :","Error Refund Qris")
            }
        }
        while(responsePayoutCancel.isEmpty()){
            delay(100)
        }
        Log.e("responseEw :", responsePayoutCancel.toString())
        return responsePayoutCancel
    }
}