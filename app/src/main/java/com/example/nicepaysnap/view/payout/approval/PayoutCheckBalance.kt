package com.example.nicepaysnap.view.payout.approval

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.R
import com.example.nicepaysnap.nicepay.model.RequestPayoutCheckBalance
import kotlinx.coroutines.launch

class PayoutCheckBalance : BasePayoutApprovalAppCompatActivity() {

    lateinit var inputOriginalReferenceNo : EditText
    lateinit var inputPartnerReferenceNo : EditText
    lateinit var inputBeneficiaryAccountNo : EditText
    lateinit var merchantId: EditText
    lateinit var resultLayout  : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineLayout(R.layout.inquiry_payout)

        inputOriginalReferenceNo = findViewById(R.id.editTxId)
        inputPartnerReferenceNo = findViewById(R.id.editReferenceNo)
        inputBeneficiaryAccountNo = findViewById(R.id.editTexBeneficiaryAccountNo)

        inputOriginalReferenceNo.setVisibility(View.GONE)
        inputPartnerReferenceNo.setVisibility(View.GONE)
        inputBeneficiaryAccountNo.setVisibility(View.GONE)

        merchantId = findViewById(R.id.editTextMerchantId)
        merchantId.setText(DEFAULT_MERCHANT_ID)

        resultLayout = findViewById(R.id.id_layout_result_payout_inquiry)
        resultLayout.setVisibility(View.GONE)

        buttonCloseLayout.setOnClickListener {
            super.onBackPressed()
        }

        submit.setOnClickListener {
            if (merchantId.text.toString() == "") merchantId.setText(DEFAULT_MERCHANT_ID)

            lifecycleScope.launch {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.i(this.toString() + " Response : ",
                        parseValue(approvalService.checkBalance(RequestPayoutCheckBalance(merchantId.text.toString())))
                            .toString())

                    if (responseRest.get("responseMessage").toString().contains("Success")) {
                        Toast.makeText(applicationContext, responseRest.get("responseMessage").toString() + " check balance"
//                                + " with Response Code : " + responseRest.get("responseCode").toString()
                                + ". Available balance " + responseRest.get("availableBalance").toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}