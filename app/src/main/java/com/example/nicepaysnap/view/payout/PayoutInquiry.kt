package com.example.nicepaysnap.view.payout

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.R
import com.example.nicepaysnap.nicepay.model.RequestPayoutInquiry
import kotlinx.coroutines.launch

class PayoutInquiry : BasePayoutAppCompatActivity() {

    lateinit var inputOriginalReferenceNo : EditText
    lateinit var inputPartnerReferenceNo : EditText
    lateinit var inputBeneficiaryAccountNo : EditText
    lateinit var merchantId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineLayout(R.layout.inquiry_payout)

        inputOriginalReferenceNo = findViewById(R.id.editTxId)
        inputPartnerReferenceNo = findViewById(R.id.editReferenceNo)
        inputBeneficiaryAccountNo = findViewById(R.id.editTexBeneficiaryAccountNo)
        inputBeneficiaryAccountNo.visibility = View.VISIBLE

        var resultLayout  : View = findViewById(R.id.id_layout_result_payout_inquiry)
        resultLayout.setVisibility(View.GONE)

        var responseCode : EditText = findViewById(R.id.editResponseCode)
        var responseMessage : EditText = findViewById(R.id.editResponseMessage)
        var partnerReferenceNo : EditText = findViewById(R.id.editResultReferenceNo)
        var originalReferenceNo : EditText = findViewById(R.id.editResultTxId)

        var beneficiaryAccountNo : EditText = findViewById(R.id.editBeneficiaryAccountNo)
        var beneficiaryName : EditText = findViewById(R.id.editBeneficiaryName)
        var beneficiaryBankCode : EditText = findViewById(R.id.editBeneficiaryBankCode)
        var beneficiaryCustomerResidence : EditText = findViewById(R.id.editBeneficiaryCustomerResidence)
        var beneficiaryCustomerType : EditText = findViewById(R.id.editBeneficiaryCustomerType)

        var transactionDate : EditText = findViewById(R.id.editTransactionDate)

        var latestTransactionStatus : EditText = findViewById(R.id.editLatestTransactionStatus)
        var transactionStatusDesc : EditText = findViewById(R.id.editTransactionStatusDesc)
        var amountValue : EditText = findViewById(R.id.editAmountValue)

        buttonCloseLayout.setOnClickListener {
            super.onBackPressed()
        }

        inputOriginalReferenceNo = findViewById(R.id.editTxId)
        inputPartnerReferenceNo = findViewById(R.id.editReferenceNo)
        merchantId = findViewById(R.id.editTextMerchantId)
        merchantId.setText(DEFAULT_MERCHANT_ID)

        submit.setOnClickListener {
            if (merchantId.text.toString() == "") merchantId.setText(DEFAULT_MERCHANT_ID)

            if (inputOriginalReferenceNo.text.toString() == ""
                || inputPartnerReferenceNo.text.toString() == "")
                Toast.makeText(applicationContext,
                    "Amount, Original Reference No and Partner Reference No must not be empty",
                    Toast.LENGTH_SHORT).show()
            else {
                val requestPayoutInquiry : RequestPayoutInquiry = RequestPayoutInquiry(
                    merchantId.text.toString(), inputPartnerReferenceNo.text.toString(),
                    inputOriginalReferenceNo.text.toString(), inputBeneficiaryAccountNo.text.toString()
                )

                lifecycleScope.launch {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Log.i(this.toString() + " Response : ",
                            parseValue(payoutService.checkStatus(requestPayoutInquiry)).toString())

                        if (responseRest.get("responseMessage").toString().contains("Success")) {
                            responseCode.setText(responseRest.get("responseCode").toString())
                            responseMessage.setText(responseRest.get("responseMessage").toString())
                            partnerReferenceNo.setText(
                                responseRest.get("partnerReferenceNo").toString()
                            )
                            originalReferenceNo.setText(
                                responseRest.get("originalReferenceNo").toString()
                            )
                            latestTransactionStatus.setText(
                                responseRest.get("latestTransactionStatus").toString()
                            )
                            transactionStatusDesc.setText(
                                responseRest.get("transactionStatusDesc").toString()
                            )
                            amountValue.setText(responseRest.get("amountValue").toString())
                            beneficiaryAccountNo.setText(responseRest.get("beneficiaryAccountNo").toString())
                            beneficiaryName.setText(responseRest.get("beneficiaryName").toString())
                            beneficiaryBankCode.setText(responseRest.get("beneficiaryBankCode").toString())
                            beneficiaryCustomerResidence.setText(responseRest.get("beneficiaryCustomerResidence").toString())
                            beneficiaryCustomerType.setText(responseRest.get("beneficiaryCustomerType").toString())
                            transactionDate.setText(responseRest.get("transactionDate").toString())

                            resultLayout.setVisibility(View.VISIBLE)
                        }
                    }
                }
            }
        }
    }

}