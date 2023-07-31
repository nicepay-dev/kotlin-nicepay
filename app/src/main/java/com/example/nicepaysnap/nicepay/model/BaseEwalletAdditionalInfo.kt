package com.example.nicepaysnap.nicepay.model

open class BaseEwalletAdditionalInfo : BaseCommonAdditionalInfo {

    var billingPhone: String? = null

    constructor() {}



    constructor(
        mitraCd: String?,
        goodsNm: String?,
        billingPhone: String?,
        billingNm: String?
    ) {
        this.mitraCd = mitraCd
        this.goodsNm = goodsNm
        this.billingPhone = billingPhone
        this.billingNm = billingNm
    }

    constructor(mitraCd: String?, goodsNm: String?, billingNm: String?) : super(
        mitraCd,
        goodsNm,
        billingNm
    )

}