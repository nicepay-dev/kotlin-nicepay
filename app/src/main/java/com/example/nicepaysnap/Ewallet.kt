package com.example.nicepaysnap

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.nicepay.data.util.EwalletSnapUtil
import com.example.nicepaysnap.nicepay.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class Ewallet : AppCompatActivity() {

    lateinit var goodsName: EditText
    lateinit var amount: EditText
    lateinit var mitra: Spinner
    lateinit var pay: Button
    var responseEw = HashMap<String, String>()
    var register : EwalletSnapUtil = EwalletSnapUtil()
    var bOption : String? = "Select Mitra"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ewallet)

        goodsName = findViewById(R.id.editTextGoodsName)
        amount = findViewById(R.id.editTextEwalletAmount)
        mitra = findViewById(R.id.spinnerMitra)
        pay = findViewById(R.id.registerEwalletButton)

        val mitraOption = resources.getStringArray(R.array.mitraCode)

        if (mitra != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, mitraOption)
            mitra.adapter = adapter
            mitra.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    bOption = mitraOption[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        pay.setOnClickListener {
            val amountValue : totalAmount = totalAmount.Builder()
                .setValue(amount.text.toString().trim()+".00")
                .build()

            val urlParamNotify = RequestEwalletUrlParam(
                "https://test2.bi.go.id/v1/test", "PAY_NOTIFY", "Y"
            )
            val urlParamReturn = RequestEwalletUrlParam(
                "https://test2.bi.go.id/v1/test", "PAY_RETURN", "Y"
            )

            val additionalInfo = RequestEWalletAdditionalInfo(
                mitra.toString(), goodsName.toString(), "Testing e-wallet SNAP for Android Simulation", "089876543210",
                "http://ptsv2.com/t/dbProcess/post", "https://www.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp",
                "{\"count\":\"1\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"${goodsName}\",\"goods_detail\":\"Item Detail\",\"goods_amt\":${amountValue.toString().trim()+".00"},\"goods_quantity\":\"1\"}]}",
                "data"
            )

            val ewalletRequest = RequestEwalletDirectDebit(
                "ref-" + SimpleDateFormat("yyyyMMddhhmmssXXX").format(System.currentTimeMillis()),
                "IONPAYTEST", "Mobile App", amountValue, listOf(urlParamNotify, urlParamReturn), additionalInfo
            )

            lifecycleScope.launch {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.i(this.toString() + " Request : ", ewalletRequest.toString())
                    val response = parseValue(register.register(ewalletRequest))
                    Log.i(this.toString() + " Response : ", response.toString())
                }
                Toast.makeText(this@Ewallet,"your virtual account is " + responseEw.get("webRedirectUrl").toString(),
                    Toast.LENGTH_LONG).show()
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