package com.example.nicepaysnap.nicepay.model

class RefundQrisAdditionalInfo {

    var cancelType : String? = "1"

    constructor(cancelType: String?) {
        this.cancelType = cancelType
    }

    constructor()
}