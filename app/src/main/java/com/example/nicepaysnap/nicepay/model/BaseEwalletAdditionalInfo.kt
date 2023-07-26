package com.example.nicepaysnap.nicepay.model

open class BaseEwalletAdditionalInfo {

    var mitraCd: String? = null
    var goodsNm: String? = null
    var billingPhone: String? = null
    var billingNm: String? = null

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

}