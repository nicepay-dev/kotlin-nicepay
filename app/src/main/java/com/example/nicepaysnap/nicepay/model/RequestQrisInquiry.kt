package com.example.nicepaysnap.nicepay.model

class RequestQrisInquiry : BaseRequestInquiry {

    var additionalInfo : RequestQrisAdditionalInfo? = null

    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        serviceCode: String?,
        externalStoreId: String?,
        additionalInfo: RequestQrisAdditionalInfo?
    ) : super(originalPartnerReferenceNo, originalReferenceNo, serviceCode, externalStoreId) {
        this.additionalInfo = additionalInfo
    }

    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        serviceCode: String?,
        externalStoreId: String?
    ) : super(originalPartnerReferenceNo, originalReferenceNo, serviceCode, externalStoreId)

    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        serviceCode: String?,
        additionalInfo: RequestQrisAdditionalInfo?
    ) : super(originalPartnerReferenceNo, originalReferenceNo, serviceCode) {
        this.additionalInfo = additionalInfo
    }

    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        serviceCode: String?
    ) : super(originalPartnerReferenceNo, originalReferenceNo, serviceCode)


}