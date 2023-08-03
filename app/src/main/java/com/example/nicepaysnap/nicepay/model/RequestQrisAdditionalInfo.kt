package com.example.nicepaysnap.nicepay.model

class RequestQrisAdditionalInfo : BaseCommonAdditionalInfo {

    var billingPhone : String? = null
    var billingEmail : String? = null
    var billingCity : String? = null
    var billingAddr : String? = null
    var billingState : String? = null
    var billingPostCd : String? = null
    var billingCountry : String? = null
    var userIP : String? = "127.0.0.1"

    constructor()
    constructor(
        mitraCd: String?,
        goodsNm: String?,
        billingNm: String?,
        dbProcessUrl: String?,
        callBackUrl: String?,
        cartData: String?,
        billingPhone: String?,
        billingEmail: String?,
        billingCity: String?,
        billingAddr: String?,
        billingState: String?,
        billingPostCd: String?,
        billingCountry: String?
    ) : super(mitraCd, goodsNm, billingNm, dbProcessUrl, callBackUrl, cartData) {
        this.billingPhone = billingPhone
        this.billingEmail = billingEmail
        this.billingCity = billingCity
        this.billingAddr = billingAddr
        this.billingState = billingState
        this.billingPostCd = billingPostCd
        this.billingCountry = billingCountry
        this.userIP = userIP
    }

    constructor(
        mitraCd: String?,
        goodsNm: String?,
        billingNm: String?,
        dbProcessUrl: String?,
        callBackUrl: String?,
        cartData: String?,
        billingPhone: String?,
        billingEmail: String?,
        billingCity: String?,
        billingAddr: String?,
        billingState: String?,
        billingPostCd: String?,
        billingCountry: String?,
        userIP: String?
    ) : super(mitraCd, goodsNm, billingNm, dbProcessUrl, callBackUrl, cartData) {
        this.billingPhone = billingPhone
        this.billingEmail = billingEmail
        this.billingCity = billingCity
        this.billingAddr = billingAddr
        this.billingState = billingState
        this.billingPostCd = billingPostCd
        this.billingCountry = billingCountry
        this.userIP = userIP
    }

    constructor(
        mitraCd: String?,
        goodsNm: String?,
        billingNm: String?,
        billingPhone: String?,
        billingEmail: String?,
        billingCity: String?,
        billingAddr: String?,
        billingState: String?,
        billingPostCd: String?,
        billingCountry: String?
    ) : super(mitraCd, goodsNm, billingNm) {
        this.billingPhone = billingPhone
        this.billingEmail = billingEmail
        this.billingCity = billingCity
        this.billingAddr = billingAddr
        this.billingState = billingState
        this.billingPostCd = billingPostCd
        this.billingCountry = billingCountry
    }

    constructor(
        mitraCd: String?,
        goodsNm: String?,
        billingNm: String?,
        cartData: String?,
        billingPhone: String?,
        billingEmail: String?,
        billingCity: String?,
        billingAddr: String?,
        billingState: String?,
        billingPostCd: String?,
        billingCountry: String?
    ) : super(mitraCd, goodsNm, billingNm, cartData) {
        this.billingPhone = billingPhone
        this.billingEmail = billingEmail
        this.billingCity = billingCity
        this.billingAddr = billingAddr
        this.billingState = billingState
        this.billingPostCd = billingPostCd
        this.billingCountry = billingCountry
    }


}