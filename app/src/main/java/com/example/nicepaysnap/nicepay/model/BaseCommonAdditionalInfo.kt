package com.example.nicepaysnap.nicepay.model

open class BaseCommonAdditionalInfo : BaseAdditionalInfo {

    var dbProcessUrl: String? = "http://ptsv2.com/t/dbProcess/post"
    var callBackUrl: String? = "https://www.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp"
    var cartData: String? = "{\"count\":\"1\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"${goodsNm}\",\"goods_detail\":\"Item Detail\",\"goods_amt\":%s,\"goods_quantity\":\"1\"}]}"

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