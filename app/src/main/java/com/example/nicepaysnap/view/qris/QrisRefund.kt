package com.example.nicepaysnap.view.qris

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.nicepaysnap.R
import com.google.android.material.textfield.TextInputLayout

class QrisRefund : BaseQrisAppCompatActivity() {

    lateinit var inputOriginalReferenceNo : EditText
    lateinit var inputPartnerReferenceNo : EditText
    lateinit var inputAmount : EditText
    lateinit var inputReason : EditText
    lateinit var refundType: Spinner
    var tOption : String? = "Select Refund Type"

    lateinit var storeId: EditText
    lateinit var merchantId: EditText
    lateinit var hiddenStoreId: TextInputLayout
    lateinit var hiddenMerchantId: TextInputLayout
    lateinit var paymentMethodTitle: TextView
    lateinit var paymentMethodTitleForm: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineLayout(R.layout.refund_ewallet)

        inputOriginalReferenceNo = findViewById(R.id.editTxId)
        inputPartnerReferenceNo = findViewById(R.id.editReferenceNo)
        inputAmount = findViewById(R.id.editAmount)
        inputReason = findViewById(R.id.editReason)
        refundType = findViewById(R.id.spinnerRefundType)
        paymentMethodTitle = findViewById(R.id.paymentMethodTitle)
        paymentMethodTitle.text = "Refund QRIS Form"
        paymentMethodTitleForm = findViewById(R.id.paymentMethodTitleForm)
        paymentMethodTitleForm.text = "QRIS"
        storeId = findViewById(R.id.editTexStoreId)
        storeId.setText(DEFAULT_STORE_ID)
        merchantId = findViewById(R.id.editTextMerchantId)
        merchantId.setText(DEFAULT_MERCHANT_ID)

        hiddenStoreId = findViewById(R.id.textInputStoreId)
        hiddenStoreId.visibility = View.VISIBLE
        hiddenMerchantId = findViewById(R.id.textInputMerchantId)
        hiddenMerchantId.visibility = View.VISIBLE

        buttonCloseLayout.setOnClickListener {
            super.onBackPressed()
        }

        val optionRefundType = resources.getStringArray(R.array.refundType)

        if (refundType != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, optionRefundType)
            refundType.adapter = adapter
            refundType.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    if (optionRefundType[position].toString().equals("Full"))
                        tOption = "1"
                    else if (optionRefundType[position].toString().equals("Partial"))
                        tOption = "2"
                    else tOption = "0"
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }

}