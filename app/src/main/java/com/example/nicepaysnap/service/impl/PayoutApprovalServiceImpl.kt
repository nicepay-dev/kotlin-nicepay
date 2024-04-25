package com.example.nicepaysnap.service.impl

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.nicepaysnap.nicepay.data.util.HeaderUtil
import com.example.nicepaysnap.nicepay.data.util.TokenUtil
import com.example.nicepaysnap.nicepay.model.RequestPayoutApproval
import com.example.nicepaysnap.nicepay.model.RequestPayoutCheckBalance
import com.example.nicepaysnap.service.ApprovalService
import kotlinx.coroutines.delay

class PayoutApprovalServiceImpl : TokenUtil(), ApprovalService<RequestPayoutApproval, RequestPayoutCheckBalance> {

    override suspend fun approval(request: RequestPayoutApproval): HashMap<String, String> {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun checkBalance(request: RequestPayoutCheckBalance): HashMap<String, String> {
        var responsePayoutInquiry = HashMap<String, String>()
        getAccessToken().await()
        while(accTok.equals("")){
            delay(100)
        }

        Log.e("Akses Token : ", accTok.toString())

        var headerUtil = HeaderUtil<RequestPayoutCheckBalance>()
        apiService.checkBalancePayout(
            headerUtil.generateHeader(request, utilInfo, accTok, utilInfo.payoutCheckBalanceEndPointUrl), request
        ) {
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

}