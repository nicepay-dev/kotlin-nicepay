package com.example.nicepaysnap.nicepay.model

open class BaseAdditionalInfo {

    var mitraCd: String? = null
    var goodsNm: String? = null
    var billingNm: String? = null

    constructor()
    constructor(mitraCd: String?, goodsNm: String?, billingNm: String?) {
        this.mitraCd = mitraCd
        this.goodsNm = goodsNm
        this.billingNm = billingNm
    }

    constructor(goodsNm: String?, billingNm: String?) {
        this.goodsNm = goodsNm
        this.billingNm = billingNm
    }

}