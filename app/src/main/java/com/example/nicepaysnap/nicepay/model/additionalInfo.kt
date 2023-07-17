package com.example.nicepaysnap.nicepay.model

class additionalInfo private constructor(builder : additionalInfo.Builder) {
    var bankCd : String? = null
    var goodsNm : String? = null
    var dbProcessUrl : String? = null
    var vacctValidDt : String? = null
    var vacctValidTm : String? = null
    var msId : String? = null
    var msFee : String? = null
    var msFeeType : String? = null
    var mbFee : String? = null
    var mbFeeType : String? = null

    class Builder{
        private var bankCd : String? = null
        private var goodsNm : String? = null
        private var dbProcessUrl : String? = null
        private var vacctValidDt : String? = null
        private var vacctValidTm : String? = null
        private var msId : String? = null
        private var msFee : String? = null
        private var msFeeType : String? = null
        private var mbFee : String? = null
        private var mbFeeType : String? = null

        fun setBankCd(bankCd : String) = apply { this.bankCd = bankCd }
        fun setGoodsNm(goodsNm : String) = apply { this.goodsNm = goodsNm }
        fun setDbProcessUrl(dbProcessUrl : String) = apply { this.dbProcessUrl = dbProcessUrl }
        fun setVacctValidDt(vacctValidDt : String) = apply { this.vacctValidDt = vacctValidDt }
        fun setVacctValidTm(vacctValidTm : String) = apply { this.vacctValidTm = vacctValidTm }
        fun setMsId(msId : String) = apply { this.msId = msId }
        fun setMsFee(msFee : String) = apply { this.msFee = msFee }
        fun setMsFeeType(msFeeType : String) = apply { this.msFeeType = msFeeType }
        fun setMbFee(mbFee : String) = apply { this.mbFee = mbFee }
        fun setMbFeeType(mbFeeType : String) = apply { this.mbFeeType = mbFeeType }
        fun build()  = additionalInfo(this)

        fun getBankCd() = bankCd
        fun getGoodsNm() = goodsNm
        fun getDbProcessUrl() = dbProcessUrl
        fun getVacctValidDt() = vacctValidDt
        fun getVacctValidTm() = vacctValidTm
        fun getMsId() = msId
        fun getMsFee() = msFee
        fun getMsFeeType() = msFeeType
        fun getMbFee() = mbFee
        fun getMbFeeType() = mbFeeType
    }

    init {
        bankCd = builder.getBankCd()
        goodsNm = builder.getGoodsNm()
        dbProcessUrl = builder.getDbProcessUrl()
        vacctValidDt = builder.getVacctValidDt()
        vacctValidTm = builder.getVacctValidTm()
        msId = builder.getMsId()
        msFee = builder.getMsFee()
        msFeeType = builder.getMsFeeType()
        mbFee = builder.getMbFee()
        mbFeeType = builder.getMbFeeType()
    }
}