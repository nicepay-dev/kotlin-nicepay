package com.example.nicepaysnap.nicepay.model

open class BaseRequestInquiry {

    var merchantId: String? = "IONPAYTEST"
    var originalPartnerReferenceNo: String? = ""
    var originalReferenceNo: String? = ""
    var serviceCode: String? = "54"
    var externalStoreId: String? = ""

    constructor()
    constructor(originalPartnerReferenceNo: String?, originalReferenceNo: String?) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
    }

    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        serviceCode: String?
    ) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
        this.serviceCode = serviceCode
    }

    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        serviceCode: String?,
        externalStoreId: String?
    ) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
        this.serviceCode = serviceCode
        this.externalStoreId = externalStoreId
    }

    constructor(
        merchantId: String?,
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        serviceCode: String?,
        externalStoreId: String?
    ) {
        this.merchantId = merchantId
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
        this.serviceCode = serviceCode
        this.externalStoreId = externalStoreId
    }


}