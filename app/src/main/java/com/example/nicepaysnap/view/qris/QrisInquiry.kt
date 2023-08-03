package com.example.nicepaysnap.view.qris

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.R
import com.example.nicepaysnap.nicepay.model.RequestQrisInquiry
import com.example.nicepaysnap.nicepay.model.totalAmount
import kotlinx.coroutines.launch

class QrisInquiry : BaseQrisAppCompatActivity() {

    lateinit var inputOriginalReferenceNo : EditText
    lateinit var inputPartnerReferenceNo : EditText
    lateinit var storeId: EditText
    lateinit var merchantId: EditText

    lateinit var textViewBillingPhone : TextView
    lateinit var textViewTransactionStatusDesc : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineLayout(R.layout.inquiry_qris)

        var resultLayout  : View = findViewById(R.id.id_layout_result_ewallet_inquiry)
        resultLayout.setVisibility(View.GONE)

        var responseCode : EditText = findViewById(R.id.editResponseCode)
        var responseMessage : EditText = findViewById(R.id.editResponseMessage)
        var partnerReferenceNo : EditText = findViewById(R.id.editResultReferenceNo)
        var originalReferenceNo : EditText = findViewById(R.id.editResultTxId)
        var serviceCode : EditText = findViewById(R.id.editServiceCode)
        var latestTransactionStatus : EditText = findViewById(R.id.editLatestTransactionStatus)
        var transactionStatusDesc : EditText = findViewById(R.id.editTransactionStatusDesc)
        var amountValue : EditText = findViewById(R.id.editAmountValue)
        var amountCurrency : EditText = findViewById(R.id.editAmountCurrency)
        var mitraCd : EditText = findViewById(R.id.editMitraCd)
        var billingPhone : EditText = findViewById(R.id.editResultBillingPhone)

        textViewBillingPhone = findViewById(R.id.textBillingPhone)
        textViewTransactionStatusDesc = findViewById(R.id.textLatestTransactionStatusDesc)

        inputOriginalReferenceNo = findViewById(R.id.editTxId)
        inputPartnerReferenceNo = findViewById(R.id.editReferenceNo)
        storeId = findViewById(R.id.editTexStoreId)
        storeId.setText(DEFAULT_STORE_ID)
        merchantId = findViewById(R.id.editTextMerchantId)
        merchantId.setText(DEFAULT_MERCHANT_ID)

        buttonCloseLayout.setOnClickListener {
            super.onBackPressed()
        }

        submit.setOnClickListener {
            if (storeId.text.toString() == "") storeId.setText(DEFAULT_STORE_ID)
            if (merchantId.text.toString() == "") storeId.setText(DEFAULT_MERCHANT_ID)

            if (inputOriginalReferenceNo.text.toString() == ""
                || inputPartnerReferenceNo.text.toString() == "") {
                Toast.makeText(applicationContext,
                    "Original Reference No and Partner Reference No must not be empty",
                    Toast.LENGTH_SHORT).show()
            } else {
                val requestQrisInqury = RequestQrisInquiry(
                    merchantId.text.toString(), inputPartnerReferenceNo.text.toString(),
                    inputOriginalReferenceNo.text.toString(), "51", storeId.text.toString()
                )

                lifecycleScope.launch {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Log.i(this.toString() + " Response : ",
                            parseValue(qrisService.checkStatus(requestQrisInqury)).toString())

                        if (responseRest.get("responseMessage").toString().contains("Success")) {
                            responseCode.setText(responseRest.get("responseCode").toString())
                            responseMessage.setText(responseRest.get("responseMessage").toString())
                            partnerReferenceNo.setText(
                                responseRest.get("partnerReferenceNo").toString()
                            )
                            originalReferenceNo.setText(
                                responseRest.get("originalReferenceNo").toString()
                            )
                            serviceCode.setText(responseRest.get("serviceCode").toString())
                            latestTransactionStatus.setText(
                                responseRest.get("latestTransactionStatus").toString()
                            )
                            amountValue.setText(responseRest.get("amountValue").toString())
                            amountCurrency.setText(responseRest.get("amountCurrency").toString())
                            mitraCd.setText(responseRest.get("mitraCd").toString())


                            textViewBillingPhone.text = "Billing Name"
                            textViewTransactionStatusDesc.text = "Goods Name"

                            transactionStatusDesc.setText(
                                responseRest.get("goodsNm").toString()
                            )
                            billingPhone.setText(responseRest.get("billingNm").toString())

                            resultLayout.setVisibility(View.VISIBLE)
                        } else {
                            Toast.makeText(applicationContext, "Response Inquiry : " +
                                    responseRest.get("responseMessage").toString(), Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

}