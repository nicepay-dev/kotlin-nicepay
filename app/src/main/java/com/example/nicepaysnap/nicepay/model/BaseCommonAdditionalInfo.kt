package com.example.nicepaysnap.nicepay.model

open class BaseCommonAdditionalInfo : BaseAdditionalInfo {

    var dbProcessUrl: String? = null
    var callBackUrl: String? = null
    var cartData: String? = "{\"count\":\"1\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"${goodsNm}\",\"goods_detail\":\"Item Detail\",\"goods_amt\":%s,\"goods_quantity\":\"1\"}]}"

    constructor()
    constructor(dbProcessUrl: String?, callBackUrl: String?, cartData: String?) : super() {
        this.dbProcessUrl = dbProcessUrl
        this.callBackUrl = callBackUrl
        this.cartData = cartData
    }


}