package com.example.nicepaysnap.nicepay.model

class totalAmount private constructor(builder : totalAmount.Builder) {
    var value : String? = null
    var currency : String? = "IDR"

    class Builder{
        private var value : String? = null
        private var currency : String? = "IDR"

        fun setValue(value : String) = apply { this.value = value }
        fun setCurrency(currency : String) = apply { this.currency = currency }
        fun build()  = totalAmount(this)

        fun getValue() = value
        fun getCurrency() = currency
    }

    init {
        currency = builder.getCurrency()
        value = builder.getValue()
    }
}