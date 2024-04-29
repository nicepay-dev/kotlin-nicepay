package com.example.nicepaysnap.view.payout

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.R
import com.example.nicepaysnap.nicepay.data.enumeration.EApproval
import com.example.nicepaysnap.nicepay.model.*
import com.example.nicepaysnap.service.ApprovalService
import com.example.nicepaysnap.service.impl.PayoutApprovalServiceImpl
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class PayoutRegistration : BasePayoutAppCompatActivity() {

    var approvalService : ApprovalService<RequestPayoutApproval, RequestPayoutCheckBalance> = PayoutApprovalServiceImpl()

    lateinit var beneficiaryBankCode : Spinner
    lateinit var payoutMethod : Spinner
    lateinit var beneficiaryAccountNo : EditText
    lateinit var beneficiaryName : EditText
    lateinit var beneficiaryPhone : EditText
    lateinit var amount : EditText
    lateinit var reservedDt : DatePicker
    lateinit var reservedTm : TimePicker

    lateinit var approveBtn: Button
    lateinit var rejectBtn: Button

    lateinit var approval : EApproval

    var bankSelected : String? = "Select Payout Bank"
    var methodSelected : String? = "Select Payout Bank"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineLayout(R.layout.activity_payout)

        var txId : EditText = findViewById(R.id.editResultTxId)
        var refNo : EditText = findViewById(R.id.editResultReferenceNo)
        var resultLayout  : View = findViewById(R.id.id_layout_result_generate_payout)
        resultLayout.setVisibility(View.GONE)

        beneficiaryBankCode =  findViewById(R.id.spinnerPayoutBank)
        payoutMethod = findViewById(R.id.spinnerPayoutMethod)
        beneficiaryAccountNo = findViewById(R.id.editTextBeneficiaryAccountNo)
        beneficiaryName = findViewById(R.id.editTextBeneficiaryName)
        beneficiaryPhone = findViewById(R.id.editTextBeneficiaryPhone)
        amount = findViewById(R.id.editTextPayoutAmount)
        reservedDt = findViewById(R.id.textInputReservedDate)
        reservedTm = findViewById(R.id.textInputReservedTime)

        approveBtn = findViewById(R.id.buttonApprove)
        rejectBtn = findViewById(R.id.buttonReject)

        val bankCodeOption = resources.getStringArray(R.array.payoutBankCode)
        val methodOption = resources.getStringArray(R.array.payoutMethod)

        reservedDt.minDate = System.currentTimeMillis()

        val defaultTimer : Calendar = Calendar.getInstance()
        defaultTimer.add(Calendar.MINUTE, 120)
        reservedTm.hour = defaultTimer.get(Calendar.HOUR_OF_DAY)

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
            val calendar : Calendar = Calendar.getInstance()
            val isSameDay = isSameDay(calendar, getDateFromDatePicker(reservedDt))
            calendar.add(Calendar.MINUTE, 65)

            val amountValue : totalAmount = totalAmount.Builder()
                .setValue(amount.text.toString().trim()+".00")
                .build()

            val requestPayoutRegistration = RequestPayoutRegistration(bankSelected, methodSelected,
                beneficiaryAccountNo.text.toString(), beneficiaryName.text.toString(),
                beneficiaryPhone.text.toString(),
                SimpleDateFormat("yyyyMMdd").format(getDateFromDatePicker(reservedDt).time).toString(),
                SimpleDateFormat("HHmmss").format(getTimeFromTimePicker(reservedTm).time).toString(),
                "ref-" + SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis()),
                amountValue
            )

            lifecycleScope.launch {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (beneficiaryAccountNo.text.toString() == "" || beneficiaryName.text.toString() == ""
                        || beneficiaryPhone.text.toString() == "")
                        Toast.makeText(applicationContext, "Beneficiary Account, Beneficiary Name and Amount must not be empty", Toast.LENGTH_SHORT).show()
                    else if (isSameDay && getTimeFromTimePicker(reservedTm).before(calendar)) {
                        Toast.makeText(applicationContext, "Please input reserved time more than 1 hour and 5 minute for today date", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.i(this.toString() + " Response : ",
                            parseValue(payoutService.register(requestPayoutRegistration)).toString())

                        val responseMessage = responseRest.get("responseMessage").toString()
                        if (responseMessage.equals("Successful")) {
                            refNo.setText(responseRest.get("partnerReferenceNo"))
                            txId.setText(responseRest.get("originalReferenceNo"))

                            resultLayout.setVisibility(View.VISIBLE)
                        } else Toast.makeText(applicationContext, "Failed to register payout. This might caused by amount limit and balance. Please adjust amount value", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        approveBtn.setOnClickListener {
            Log.i("Test", "approveBtn clicked")
            approval = EApproval.APPROVE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (txId.text.toString() != "" || refNo.text.toString() != "") {

                    lifecycleScope.launch {
                        Log.i(
                            this.toString() + " Response : ",
                            parseValue(approvalService.approval(RequestPayoutApproval(
                                refNo.text.toString(), txId.text.toString()), approval)).toString()
                        )
                        Toast.makeText(applicationContext, responseRest.get("responseMessage").toString() + " " +
                                approval.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        rejectBtn.setOnClickListener {
            Log.i("Test", "rejectBtn clicked")
            approval = EApproval.REJECT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (txId.text.toString() != "" || refNo.text.toString() != "") {

                    lifecycleScope.launch {
                        Log.i(
                            this.toString() + " Response : ",
                            parseValue(approvalService.approval(RequestPayoutApproval(
                                refNo.text.toString(), txId.text.toString()), approval)).toString()
                        )
                        Toast.makeText(applicationContext, responseRest.get("responseMessage").toString() + " " +
                                approval.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
    }

    private fun getDateFromDatePicker(datePicker: DatePicker): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
        return calendar
    }

    private fun getTimeFromTimePicker(timePicker: TimePicker): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)
        return calendar
    }
}