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
import com.example.nicepaysnap.nicepay.model.RequestPayoutCancel
import kotlinx.coroutines.launch

class PayoutCancel : BasePayoutAppCompatActivity() {

    lateinit var inputOriginalReferenceNo : EditText
    lateinit var inputPartnerReferenceNo : EditText
    lateinit var inputBeneficiaryAccountNo : EditText
    lateinit var merchantId: EditText
    lateinit var resultLayout  : View

    lateinit var textForm : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineLayout(R.layout.inquiry_payout)

        textForm = findViewById(R.id.textForm)
        textForm.setText("Cancel Payout Form")

        inputOriginalReferenceNo = findViewById(R.id.editTxId)
        inputPartnerReferenceNo = findViewById(R.id.editReferenceNo)
        inputBeneficiaryAccountNo = findViewById(R.id.editTexBeneficiaryAccountNo)
        merchantId = findViewById(R.id.editTextMerchantId)
        merchantId.setText(DEFAULT_MERCHANT_ID)

        resultLayout = findViewById(R.id.id_layout_result_payout_inquiry)
        resultLayout.setVisibility(View.GONE)

        inputBeneficiaryAccountNo.visibility = View.GONE

        buttonCloseLayout.setOnClickListener {
            super.onBackPressed()
        }

        submit.setText("Cancel Payout")
        submit.setOnClickListener {
            if (merchantId.text.toString() == "") merchantId.setText(DEFAULT_MERCHANT_ID)

            if (inputOriginalReferenceNo.text.toString() == ""
                || inputPartnerReferenceNo.text.toString() == "")
                Toast.makeText(applicationContext,
                    "Amount, Original Reference No and Partner Reference No must not be empty",
                    Toast.LENGTH_SHORT).show()
            else {
                val requestPayoutCancel = RequestPayoutCancel(
                    inputPartnerReferenceNo.text.toString(), inputOriginalReferenceNo.text.toString()
                )

                lifecycleScope.launch {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Log.i(this.toString() + " Response : ",
                            parseValue(payoutService.refund(requestPayoutCancel)).toString())

                        Toast.makeText(applicationContext, responseRest.get("responseMessage").toString()
                                + " Cancel with Response Code: " + responseRest.get("responseCode"), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

}