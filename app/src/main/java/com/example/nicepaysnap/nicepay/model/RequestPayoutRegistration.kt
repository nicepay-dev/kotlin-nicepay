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
    var beneficiaryCustomerResidence : String? = "Testing beneficiaryCustomerResidence"
    var beneficiaryCustomerType : String? = "Testing beneficiaryCustomerType"
    var beneficiaryPostalCode : String? = "Testing beneficiaryPostalCode"
    var partnerReferenceNo : String? = "reference-test"
    var deliveryId : String? = "Testing deliveryId"
    var deliveryName : String? = "Testing deliveryName"
    var description : String? = "Testing description"
    var beneficiaryPOE : String? = "Testing beneficiaryPOE"
    var beneficiaryDOE : String? = "Testing beneficiaryDOE"
    var beneficiaryCoNo : String? = "Testing beneficiaryCoNo"
    var beneficiaryAddress : String? = "Testing beneficiaryAddress"
    var beneficiaryAuthPhoneNumber : String? = "Testing beneficiaryAuthPhoneNumber"
    var beneficiaryMerCategory : String? = "Testing beneficiaryMerCategory"
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
        partnerReferenceNo : String?
    ) {
        this.beneficiaryBankCode = beneficiaryBankCode
        this.payoutMethod = payoutMethod
        this.beneficiaryAccountNo = beneficiaryAccountNo
        this.beneficiaryName = beneficiaryName
        this.beneficiaryPhone = beneficiaryPhone
        this.reservedDt = reservedDt
        this.reservedTm = reservedTm
        this.partnerReferenceNo = partnerReferenceNo
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