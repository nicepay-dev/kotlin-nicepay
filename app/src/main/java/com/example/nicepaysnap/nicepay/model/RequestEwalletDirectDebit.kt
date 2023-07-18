package com.example.nicepaysnap.nicepay.model

class RequestEwalletDirectDebit {

    var partnerReferenceNo : String? = "reference-test"
    var merchantId : String? = "IONPAYTEST"
    var subMerchantId : String? = ""
    var externalStoreId : String? = ""
    var validUpTo : String? = ""
    var pointOfInitiation : String? = ""
    var amount : totalAmount? = null
    var urlParam : List<RequestEwalletUrlParam>? = listOf(
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
        this.urlParam = urlParams
        this.additionalInfo = additionalInfo
    }

    constructor(
        partnerReferenceNo: String?,
        merchantId: String?,
        pointOfInitiation: String?,
        amount: totalAmount?,
        urlParams: List<RequestEwalletUrlParam>?,
        additionalInfo: RequestEWalletAdditionalInfo?
    ) {
        this.partnerReferenceNo = partnerReferenceNo
        this.merchantId = merchantId
        this.pointOfInitiation = pointOfInitiation
        this.amount = amount
        this.urlParam = urlParams
        this.additionalInfo = additionalInfo
    }

    override fun toString(): String {
        return "RequestEwalletDirectDebit(partnerReferenceNo=$partnerReferenceNo, merchantId=$merchantId, subMerchantId=$subMerchantId, externalStoreId=$externalStoreId, validUpTo=$validUpTo, pointOfInitiation=$pointOfInitiation, amount=$amount, urlParams=$urlParam, additionalInfo=$additionalInfo)"
    }


}