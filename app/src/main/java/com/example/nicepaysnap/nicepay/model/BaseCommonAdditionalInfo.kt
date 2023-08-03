package com.example.nicepaysnap.nicepay.model

open class BaseCommonAdditionalInfo : BaseAdditionalInfo {

    var dbProcessUrl: String? = null
    var callBackUrl: String? = null
    var cartData: String? = null

    constructor()



    constructor(
        mitraCd: String?,
        goodsNm: String?,
        billingNm: String?,
        dbProcessUrl: String?,
        callBackUrl: String?,
        cartData: String?
    ) : super(mitraCd, goodsNm, billingNm) {
        this.dbProcessUrl = dbProcessUrl
        this.callBackUrl = callBackUrl
        this.cartData = cartData
    }

    constructor(goodsNm: String?, billingNm: String?) : super(goodsNm, billingNm)
    constructor(mitraCd: String?, goodsNm: String?, billingNm: String?) : super(
        mitraCd,
        goodsNm,
        billingNm
    )

    constructor(mitraCd: String?, goodsNm: String?, billingNm: String?, cartData: String?) : super(
        mitraCd,
        goodsNm,
        billingNm
    ) {
        this.cartData = cartData
    }


}