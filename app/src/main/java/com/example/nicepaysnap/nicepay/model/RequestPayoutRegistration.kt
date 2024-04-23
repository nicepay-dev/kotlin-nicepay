package com.example.nicepaysnap.nicepay.model

class RequestPayoutRegistration {

    var beneficiaryBankCode : String? = ""
    var payoutMethod : String? = ""
    var beneficiaryAccountNo : String? = ""
    var beneficiaryName : String? = ""
    var beneficiaryPhone : String? = ""
    var reservedDt : String? = ""
    var reservedTm : String? = ""
    var amount : totalAmount = totalAmount.Builder().build()

    var merchantId : String? = "IONPAYTEST"
    var msId : String? = ""
    var beneficiaryCustomerResidence : String? = "1"
    var beneficiaryCustomerType : String? = "1"
    var beneficiaryPostalCode : String? = "12345"
    var partnerReferenceNo : String? = "reference-test"
    var deliveryId : String? = ""
    var deliveryName : String? = ""
    var description : String? = "Testing description"
    var beneficiaryPOE : String? = "Testing beneficiaryPOE"
    var beneficiaryDOE : String? = "220101"
    var beneficiaryCoNo : String? = "12345JP"
    var beneficiaryAddress : String? = "Testing beneficiaryAddress"
    var beneficiaryAuthPhoneNumber : String? = "123"
    var beneficiaryMerCategory : String? = "1"
    var beneficiaryCoMgmtName : String? = "beneficiaryCoMgmtName"
    var beneficiaryCoShName : String? = "beneficiaryCoShName"

    constructor()
    constructor(
        beneficiaryBankCode: String?,
        payoutMethod: String?,
        beneficiaryAccountNo: String?,
        beneficiaryName: String?,
        beneficiaryPhone: String?,
        reservedDt: String?,
        reservedTm: String?,
        partnerReferenceNo : String?,
        amount : totalAmount
    ) {
        this.beneficiaryBankCode = beneficiaryBankCode
        this.payoutMethod = payoutMethod
        this.beneficiaryAccountNo = beneficiaryAccountNo
        this.beneficiaryName = beneficiaryName
        this.beneficiaryPhone = beneficiaryPhone
        this.reservedDt = reservedDt
        this.reservedTm = reservedTm
        this.partnerReferenceNo = partnerReferenceNo
        this.amount = amount

        if (payoutMethod != "0") this.beneficiaryCustomerType = payoutMethod
    }

    constructor(
        beneficiaryBankCode: String?,
        payoutMethod: String?,
        beneficiaryAccountNo: String?,
        beneficiaryName: String?,
        beneficiaryPhone: String?,
        reservedDt: String?,
        reservedTm: String?,
        merchantId: String?,
        msId: String?,
        beneficiaryCustomerResidence: String?,
        beneficiaryCustomerType: String?,
        beneficiaryPostalCode: String?,
        partnerReferenceNo: String?,
        deliveryId: String?,
        deliveryName: String?,
        description: String?,
        beneficiaryPOE: String?,
        beneficiaryDOE: String?,
        beneficiaryCoNo: String?,
        beneficiaryAddress: String?,
        beneficiaryAuthPhoneNumber: String?,
        beneficiaryMerCategory: String?,
        beneficiaryCoMgmtName: String?,
        beneficiaryCoShName: String?
    ) {
        this.beneficiaryBankCode = beneficiaryBankCode
        this.payoutMethod = payoutMethod
        this.beneficiaryAccountNo = beneficiaryAccountNo
        this.beneficiaryName = beneficiaryName
        this.beneficiaryPhone = beneficiaryPhone
        this.reservedDt = reservedDt
        this.reservedTm = reservedTm
        this.merchantId = merchantId
        this.msId = msId
        this.beneficiaryCustomerResidence = beneficiaryCustomerResidence
        this.beneficiaryCustomerType = beneficiaryCustomerType
        this.beneficiaryPostalCode = beneficiaryPostalCode
        this.partnerReferenceNo = partnerReferenceNo
        this.deliveryId = deliveryId
        this.deliveryName = deliveryName
        this.description = description
        this.beneficiaryPOE = beneficiaryPOE
        this.beneficiaryDOE = beneficiaryDOE
        this.beneficiaryCoNo = beneficiaryCoNo
        this.beneficiaryAddress = beneficiaryAddress
        this.beneficiaryAuthPhoneNumber = beneficiaryAuthPhoneNumber
        this.beneficiaryMerCategory = beneficiaryMerCategory
        this.beneficiaryCoMgmtName = beneficiaryCoMgmtName
        this.beneficiaryCoShName = beneficiaryCoShName
    }

}