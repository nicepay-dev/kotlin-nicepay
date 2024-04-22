package com.example.nicepaysnap.view.payout

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.R
import com.example.nicepaysnap.nicepay.model.RequestPayoutRegistration
import com.example.nicepaysnap.nicepay.model.totalAmount
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class PayoutRegistration : BasePayoutAppCompatActivity() {

    lateinit var beneficiaryBankCode : Spinner
    lateinit var payoutMethod : Spinner
    lateinit var beneficiaryAccountNo : EditText
    lateinit var beneficiaryName : EditText
    lateinit var beneficiaryPhone : EditText
    lateinit var amount : EditText
    lateinit var reservedDt : DatePicker
    lateinit var reservedTm : TimePicker

    var bankSelected : String? = "Select Payout Bank"
    var methodSelected : String? = "Select Payout Bank"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineLayout(R.layout.activity_payout)

        var txId : EditText = findViewById(R.id.editResultTxId)
        var refNo : EditText = findViewById(R.id.editResultReferenceNo)
        var resultLayout  : View = findViewById(R.id.id_layout_result_generate_payout)

        beneficiaryBankCode =  findViewById(R.id.spinnerPayoutBank)
        payoutMethod = findViewById(R.id.spinnerPayoutMethod)
        beneficiaryAccountNo = findViewById(R.id.editTextBeneficiaryAccountNo)
        beneficiaryName = findViewById(R.id.editTextBeneficiaryName)
        beneficiaryPhone = findViewById(R.id.editTextBeneficiaryPhone)
        amount = findViewById(R.id.editTextPayoutAmount)
        reservedDt = findViewById(R.id.editTextReservedDate)
        reservedTm = findViewById(R.id.editTextReservedTime)

        val bankCodeOption = resources.getStringArray(R.array.payoutBankCode)
        val methodOption = resources.getStringArray(R.array.payoutMethod)

        if (beneficiaryBankCode != null && methodOption != null) {
            val BankAdapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, bankCodeOption)
            beneficiaryBankCode.adapter = BankAdapter
            beneficiaryBankCode.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    bankSelected = bankCodeOption[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
            
            val methodAdapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, methodOption)
            payoutMethod.adapter = methodAdapter
            payoutMethod.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    if (methodOption[position].toString().contains("Internal Transfer")) {
                        methodSelected = "0"
                    } else if (methodOption[position].toString().contains("Online Transfer")) {
                        methodSelected = "1"
                    } else if (methodOption[position].toString().contains("SKN")) {
                        methodSelected = "2"
                    } else if (methodOption[position].toString().contains("RTGS")) {
                        methodSelected = "3"
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        buttonCloseLayout.setOnClickListener{
            super.onBackPressed()
        }

        submit.setOnClickListener {
            val amountValue : totalAmount = totalAmount.Builder()
                .setValue(amount.text.toString().trim()+".00")
                .build()


            val requestPayoutRegistration = RequestPayoutRegistration(bankSelected, methodSelected,
                beneficiaryAccountNo.text.toString(), beneficiaryName.text.toString(),
                beneficiaryPhone.text.toString(),
                SimpleDateFormat("yyyyMMdd").parse(reservedDt.toString()).toString(),
                SimpleDateFormat("hhmmss").parse(reservedTm.toString()).toString(),
                "ref-" + SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis())
            )

            lifecycleScope.launch {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (beneficiaryAccountNo.text.toString() == "" || beneficiaryName.text.toString() == ""
                        || beneficiaryPhone.text.toString() == "")
                        Toast.makeText(applicationContext, "Amount must not be empty", Toast.LENGTH_SHORT).show()
                    else {
                        Log.i(this.toString() + " Response : ",
                            parseValue(payoutService.register(requestPayoutRegistration)).toString())

                        val responseMessage = responseRest.get("").toString()
                        if (responseMessage.equals("Successful")) {
                            refNo.setText(responseRest.get("partnerReferenceNo"))
                            txId.setText(responseRest.get("originalReferenceNo"))

                            resultLayout.setVisibility(View.VISIBLE)
                        }

                    }
                }
            }
        }

    }
}