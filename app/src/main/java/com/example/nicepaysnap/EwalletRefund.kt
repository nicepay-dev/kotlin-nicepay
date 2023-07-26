package com.example.nicepaysnap

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.nicepay.data.util.EwalletSnapUtil
import com.example.nicepaysnap.nicepay.model.RefundEwalletAdditionalInfo
import com.example.nicepaysnap.nicepay.model.RequestEwalletRefund
import com.example.nicepaysnap.nicepay.model.totalAmount
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class EwalletRefund : AppCompatActivity() {

    lateinit var inputOriginalReferenceNo : EditText
    lateinit var inputPartnerReferenceNo : EditText
    lateinit var inputAmount : EditText
    lateinit var inputReason : EditText
    lateinit var refundType: Spinner
    lateinit var buttonRefund : Button
    var ewalletSnapUtil : EwalletSnapUtil = EwalletSnapUtil()
    var responseEw = HashMap<String, String>()
    var tOption : String? = "Select Refund Type"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.refund_ewallet)

        inputOriginalReferenceNo = findViewById(R.id.editTxId)
        inputPartnerReferenceNo = findViewById(R.id.editReferenceNo)
        inputAmount = findViewById(R.id.editAmount)
        inputReason = findViewById(R.id.editReason)
        buttonRefund = findViewById(R.id.refundEwalletButton)
        refundType = findViewById(R.id.spinnerRefundType)

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

        buttonRefund.setOnClickListener {
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
                            parseValue(ewalletSnapUtil.refund(requestEwalletRefund)).toString())

                        Toast.makeText(applicationContext, "Response Refund : " +
                                responseEw.get("responseMessage").toString() + " " +
                                responseEw.get("responseCode").toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    suspend fun parseValue(responseData: HashMap<String, String>) {
        // perform parsing operation asynchronously
        responseEw = responseData
        while(responseEw.isEmpty()){
            delay(100)
        }
    }

}