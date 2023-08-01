package com.example.nicepaysnap.nicepay.model

class BaseQrisResponseAdditionalInfo : BaseAdditionalInfo {

    var validityPeriod : String? = null

    constructor() {}
    constructor(
        mitraCd: String?,
        goodsNm: String?,
        billingNm: String?,
        validityPeriod: String?
    ) : super(mitraCd, goodsNm, billingNm) {
        this.validityPeriod = validityPeriod
    }

    constructor(mitraCd: String?, goodsNm: String?, billingNm: String?) : super(
        mitraCd,
        goodsNm,
        billingNm
    )


}