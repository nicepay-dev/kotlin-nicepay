package com.example.nicepaysnap.nicepay.model

class RequestEWalletAdditionalInfo {
    var mitraCd: String? = null
    var goodsNm: String? = null
    var billingNm: String? = null
    var billingPhone: String? = null
    var dbProcessUrl: String? = null
    var callBackUrl: String? = null
    var cartData: String? = "{\"count\":\"1\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"${goodsNm}\",\"goods_detail\":\"Item Detail\",\"goods_amt\":%s,\"goods_quantity\":\"1\"}]}"
    var msId: String? = null
    var msFee: String? = null
    var msFeeType: String? = null
    var mbFee: String? = null
    var mbFeeType: String? = null

    constructor()
    constructor(
        mitraCd: String?,
        goodsNm: String?,
        billingNm: String?,
        billingPhone: String?,
        dbProcessUrl: String?,
        callBackUrl: String?,
        cartData: String?,
        msId: String?,
        msFee: String?,
        msFeeType: String?,
        mbFee: String?,
        mbFeeType: String?
    ) {
        this.mitraCd = mitraCd
        this.goodsNm = goodsNm
        this.billingNm = billingNm
        this.billingPhone = billingPhone
        this.dbProcessUrl = dbProcessUrl
        this.callBackUrl = callBackUrl
        this.cartData = cartData
        this.msId = msId
        this.msFee = msFee
        this.msFeeType = msFeeType
        this.mbFee = mbFee
        this.mbFeeType = mbFeeType
    }
}