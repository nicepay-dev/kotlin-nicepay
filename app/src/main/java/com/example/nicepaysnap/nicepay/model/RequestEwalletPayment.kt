package com.example.nicepaysnap.nicepay.model

class RequestEwalletPayment {

    var partnerReferenceNo : String? = null
    var merchantId : String? = "IONPAYTEST"
    var subMerchantId : String? = null
    var externalStoreId : String? = null
    var validUpTo : String? = null
    var pointOfInitiation : String? = null
    var amount: totalAmount? = null
    var urlParams: List<RequestEwalletUrlParam>? = listOf(
        RequestEwalletUrlParam("https://test2.bi.go.id/v1/test", "PAY_NOTIFY", "Y"),
        RequestEwalletUrlParam("https://test2.bi.go.id/v1/test", "PAY_RETURN", "Y")
    )
    var additionalInfo: RequestEWalletAdditionalInfo? = null

    constructor()
    constructor(
        partnerReferenceNo: String?,
        merchantId: String?,
        subMerchantId: String?,
        externalStoreId: String?,
        validUpTo: String?,
        pointOfInitiation: String?,
        amount: totalAmount?,
        urlParams: List<RequestEwalletUrlParam>?,
        additionalInfo: RequestEWalletAdditionalInfo?
    ) {
        this.partnerReferenceNo = partnerReferenceNo
        this.merchantId = merchantId
        this.subMerchantId = subMerchantId
        this.externalStoreId = externalStoreId
        this.validUpTo = validUpTo
        this.pointOfInitiation = pointOfInitiation
        this.amount = amount
        this.urlParams = urlParams
        this.additionalInfo = additionalInfo
    }

}