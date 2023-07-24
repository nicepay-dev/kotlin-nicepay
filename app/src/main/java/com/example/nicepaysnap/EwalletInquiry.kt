package com.example.nicepaysnap

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.nicepay.data.util.EwalletSnapUtil
import com.example.nicepaysnap.nicepay.model.RequestEwalletInquiry
import com.example.nicepaysnap.nicepay.model.totalAmount
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EwalletInquiry : AppCompatActivity() {

    lateinit var inputOriginalReferenceNo : EditText
    lateinit var inputPartnerReferenceNo : EditText
    lateinit var inputAmount : EditText
    lateinit var buttonCheckStatus : Button
    var responseEw = HashMap<String, String>()
    var ewalletSnapUtil : EwalletSnapUtil = EwalletSnapUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inquiry_ewallet)

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

        inputOriginalReferenceNo = findViewById(R.id.editTxId)
        inputPartnerReferenceNo = findViewById(R.id.editReferenceNo)
        inputAmount = findViewById(R.id.editAmount)
        buttonCheckStatus = findViewById(R.id.inquiryEwalletButton)

        buttonCheckStatus.setOnClickListener {
            if (inputAmount.text.toString() == "" || inputOriginalReferenceNo.text.toString() == ""
                || inputPartnerReferenceNo.text.toString() == "") {
                Toast.makeText(applicationContext,
                    "Amount, Original Reference No and Partner Reference No must not be empty",
                    Toast.LENGTH_SHORT).show()
            } else {
                val amount : totalAmount = totalAmount.Builder()
                    .setValue(inputAmount.text.toString().trim()+".00")
                    .build()

                val dateFormat : DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+07:00")
                Log.i("Date timestamp", dateFormat.format(Date()))
                val requestEwalletInquiry = RequestEwalletInquiry(
                    inputPartnerReferenceNo.text.toString(), inputOriginalReferenceNo.text.toString(),
                    dateFormat.format(Date()), amount
                )

                lifecycleScope.launch {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Log.i(this.toString() + " Response : ",
                            parseValue(ewalletSnapUtil.checkStatus(requestEwalletInquiry)).toString())

                        responseCode.setText(responseEw.get("responseCode").toString())
                        responseMessage.setText(responseEw.get("responseMessage").toString())
                        partnerReferenceNo.setText(responseEw.get("partnerReferenceNo").toString())
                        originalReferenceNo.setText(responseEw.get("originalReferenceNo").toString())
                        serviceCode.setText(responseEw.get("serviceCode").toString())
                        latestTransactionStatus.setText(responseEw.get("latestTransactionStatus").toString())
                        transactionStatusDesc.setText(responseEw.get("transactionStatusDesc").toString())
                        amountValue.setText(responseEw.get("amountValue").toString())
                        amountCurrency.setText(responseEw.get("amountCurrency").toString())
                        mitraCd.setText(responseEw.get("mitraCd").toString())
                        billingPhone.setText(responseEw.get("billingPhone").toString())

                        resultLayout.setVisibility(View.VISIBLE)
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