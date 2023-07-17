package com.example.nicepaysnap.nicepay.model


class vaComponent private constructor(builder : Builder){
    var partnerServiceId : String? = null
    var customerNo : String? = null
    var virtualAccountNo : String? = null
    var virtualAccountName : String? = null
    var trxId : String? = null
    lateinit var totalAmount : totalAmount
    lateinit var additionalInfo: additionalInfo

    class Builder{
        private var partnerServiceId : String? = null
        private var customerNo : String? = null
        private var virtualAccountNo : String? = null
        private var virtualAccountName : String? = null
        private var trxId : String? = null
        lateinit var totalAmount : totalAmount
        lateinit var additionalInfo: additionalInfo

        fun setPartnerServiceId(partnerServiceId : String) = apply { this.partnerServiceId = partnerServiceId }
        fun setCustomerNo(customerNo : String) = apply { this.customerNo = customerNo }
        fun setVirtualAccountNo(virtualAccountNo : String) = apply { this.virtualAccountNo = virtualAccountNo }
        fun setVirtualAccountName(virtualAccountName : String) = apply { this.virtualAccountName = virtualAccountName }
        fun setTrxId(trxId : String) = apply { this.trxId = trxId }
        fun setTotalAmount(totalAmount: totalAmount) = apply { this.totalAmount = totalAmount }
        fun setAdditionalInfo(additionalInfo: additionalInfo) = apply { this.additionalInfo = additionalInfo }
        fun build()  = vaComponent(this)

        fun getPartnerServiceId() = partnerServiceId
        fun getCustomerNo() = customerNo
        fun getVirtualAccountNo() = virtualAccountNo
        fun getVirtualAccountName() = virtualAccountName
        fun getTrxId() = trxId
        @JvmName("getTotalAmount1")
        fun getTotalAmount() = totalAmount
        @JvmName("getAdditionalInfo1")
        fun getAdditionalInfo() = additionalInfo
    }

    init {
        partnerServiceId = builder.getPartnerServiceId()
        customerNo = builder.getCustomerNo()
        virtualAccountNo = builder.getVirtualAccountNo()
        virtualAccountName = builder.getVirtualAccountName()
        trxId = builder.getTrxId()
        totalAmount = builder.getTotalAmount()
        additionalInfo = builder.getAdditionalInfo()
    }

    override fun toString(): String {
        return "vaComponent(partnerServiceId=$partnerServiceId, customerNo=$customerNo, virtualAccountNo=$virtualAccountNo, virtualAccountName=$virtualAccountName, trxId=$trxId, totalAmount=$totalAmount, additionalInfo=$additionalInfo)"
    }


}