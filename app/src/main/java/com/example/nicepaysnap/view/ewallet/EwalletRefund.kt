package com.example.nicepaysnap.view.ewallet

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.R
import com.example.nicepaysnap.nicepay.model.RefundEwalletAdditionalInfo
import com.example.nicepaysnap.nicepay.model.RequestEwalletRefund
import com.example.nicepaysnap.nicepay.model.totalAmount
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class EwalletRefund : BaseEwalletAppCompatActivity() {

    lateinit var inputOriginalReferenceNo : EditText
    lateinit var inputPartnerReferenceNo : EditText
    lateinit var inputAmount : EditText
    lateinit var inputReason : EditText
    lateinit var refundType: Spinner
    var tOption : String? = "Select Refund Type"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineLayout(R.layout.refund_ewallet)

        inputOriginalReferenceNo = findViewById(R.id.editTxId)
        inputPartnerReferenceNo = findViewById(R.id.editReferenceNo)
        inputAmount = findViewById(R.id.editAmount)
        inputReason = findViewById(R.id.editReason)
        refundType = findViewById(R.id.spinnerRefundType)

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
                    else tOption = "2"
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        submit.setOnClickListener {
            if (inputAmount.text.toString() == "" || inputOriginalReferenceNo.text.toString() == ""
                || inputPartnerReferenceNo.text.toString() == "" || inputReason.text.toString() == "") {
                Toast.makeText(applicationContext,
                    "Amount, Reason, Original Reference No and Partner Reference No must not be empty",
                    Toast.LENGTH_SHORT).show()
            } else {
                val amount : totalAmount = totalAmount.Builder()
                    .setValue(inputAmount.text.toString().trim()+".00")
                    .build()

                val dateFormat : DateFormat = SimpleDateFormat("yyyyMMddHHmmss")
                val requestEwalletRefund = RequestEwalletRefund(
                    inputPartnerReferenceNo.text.toString(), inputOriginalReferenceNo.text.toString(),
                    inputReason.text.toString(), "ref_refund-" + dateFormat.format(Date()), amount,
                    RefundEwalletAdditionalInfo(tOption)
                )

                lifecycleScope.launch {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Log.i(this.toString() + " Response : ",
                            parseValue(ewalletService.refund(requestEwalletRefund)).toString())

                        Toast.makeText(applicationContext, "Response Refund : " +
                                responseRest.get("responseMessage").toString() + " " +
                                responseRest.get("responseCode").toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}