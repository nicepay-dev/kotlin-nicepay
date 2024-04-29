package com.example.nicepaysnap.service.impl

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.nicepaysnap.nicepay.data.enumeration.EApproval
import com.example.nicepaysnap.nicepay.data.util.HeaderUtil
import com.example.nicepaysnap.nicepay.data.util.TokenUtil
import com.example.nicepaysnap.nicepay.model.RequestPayoutApproval
import com.example.nicepaysnap.nicepay.model.RequestPayoutCheckBalance
import com.example.nicepaysnap.service.ApprovalService
import kotlinx.coroutines.delay

class PayoutApprovalServiceImpl : TokenUtil(), ApprovalService<RequestPayoutApproval, RequestPayoutCheckBalance> {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun approval(request: RequestPayoutApproval, approval : EApproval): HashMap<String, String> {
        var response = HashMap<String, String>()
        getAccessToken().await()
        while(accTok.equals("")){
            delay(100)
        }

        Log.e("Akses Token : ", accTok.toString())
        var headerUtil = HeaderUtil<RequestPayoutApproval>()
        var url: String

        if (approval.equals(EApproval.APPROVE)) url = utilInfo.payoutApproveEndPointUrl
        else url = utilInfo.payoutRejectEndPointUrl

        apiService.approvalPayout(
            headerUtil.generateHeader(request, utilInfo, accTok, url), request, approval
        ) {
            Log.e("response code : ", it?.responseCode.toString())
            if (it?.responseCode != null) {
                response = it?.toMap(HashMap()) as HashMap<String, String>
                Log.e("responsePayoutTes :", response.toString())
            } else {
                Log.e("error :","Error approval payout")
            }
        }
        while(response.isEmpty()){
            delay(100)
        }
        Log.e("responsePayout :", response.toString())

        return response
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