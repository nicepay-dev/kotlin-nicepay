package com.example.nicepaysnap

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.nicepay.data.util.EwalletSnapUtil
import com.example.nicepaysnap.nicepay.model.RequestEWalletAdditionalInfo
import com.example.nicepaysnap.nicepay.model.RequestEwalletDirectDebit
import com.example.nicepaysnap.nicepay.model.RequestEwalletUrlParam
import com.example.nicepaysnap.nicepay.model.totalAmount
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


class Ewallet : AppCompatActivity() {

    lateinit var billingPhone: EditText
    lateinit var amount: EditText
    lateinit var mitra: Spinner
    lateinit var regist: Button
    var responseEw = HashMap<String, String>()
    var register : EwalletSnapUtil = EwalletSnapUtil()
    var bOption : String? = "Select Mitra"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ewallet)

        billingPhone = findViewById(R.id.editTextBillingPhone)
        amount = findViewById(R.id.editTextEwalletAmount)
        mitra = findViewById(R.id.spinnerMitra)
        regist = findViewById(R.id.registerEwalletButton)

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

        regist.setOnClickListener {
            val amountValue : totalAmount = totalAmount.Builder()
                .setValue(amount.text.toString().trim()+".00")
                .build()

            val urlParamNotify = RequestEwalletUrlParam(
                "https://test2.bi.go.id/v1/test", "PAY_NOTIFY", "Y"
            )
            val urlParamReturn = RequestEwalletUrlParam(
                "https://test2.bi.go.id/v1/test", "PAY_RETURN", "Y"
            )

            if (billingPhone.text.toString() == "") billingPhone.setText("089876543210")
            val additionalInfo = RequestEWalletAdditionalInfo(
                bOption.toString(), "Testing Nicepay E-Wallet SNAP Item", "Testing e-wallet SNAP for Android Simulation", billingPhone.text.toString(),
                "http://ptsv2.com/t/dbProcess/post", "https://www.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp",
                "{\"count\":\"1\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"Testing E-Wallet Item\",\"goods_detail\":\"Item Detail\",\"goods_amt\":\"${amountValue.value}\",\"goods_quantity\":\"1\"}]}",
                "data"
            )

            val ewalletRequest = RequestEwalletDirectDebit(
                "ref-" + SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis()),
                "IONPAYTEST", "Mobile App", amountValue, listOf(urlParamNotify, urlParamReturn), additionalInfo
            )

            lifecycleScope.launch {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (amount.text.toString() == "") {
                        Toast.makeText(applicationContext, "Amount must not be empty", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.i(this.toString() + " Response : ", parseValue(register.register(ewalletRequest)).toString())

                        val redirectUrl = responseEw.get("webRedirectUrl").toString()
                        val responseRedirect = Uri.parse(redirectUrl)
                        if (bOption.toString().equals("LINK")) {
                            linkProcess(responseEw.get("redirectToken").toString(), redirectUrl)
                        } else if (bOption.toString().equals("OVOE")) {
                            Log.i("OVO Response ", responseEw.get("responseMessage").toString())
                            if (responseEw.get("responseMessage").toString().contains("Success"))
                                Toast.makeText(applicationContext, " Response Message from OVO "
                                        + responseEw.get("responseMessage").toString()
                                        + if (responseEw["responseMessage"].toString()
                                        .contains("Success")) " Please continue the transaction on OVO apps" else " "
                                        , Toast.LENGTH_SHORT).show()
                        }
                        else {
                            val httpIntent = Intent(Intent.ACTION_VIEW)
                            httpIntent.setData(responseRedirect)
                            startActivity(httpIntent)
                        }
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

    fun linkProcess(redirectToken : String, redirectUrl : String) {
        val webView = findViewById<View>(R.id.webview) as WebView
        val htmlString = "<form \n" +
                "id=\"returnForm_ewallet\" \n" +
                "name=\"returnForm_ewallet\"\n" +
                "action=\"${redirectUrl}\" method=\"post\">\n" +
                "    <input type=\"text\" name=\"Message\" \n" +
                "    value=\"${redirectToken}\" \n" +
                "    style=\"display: none;\">\n" +
                "        <center><button type=\"submit\">Submit Pay with LinkAja</button></center>\n" +
                "</form>"

        Toast.makeText(applicationContext, "Please click \"Submit Pay with LinkAja\" button above", Toast.LENGTH_SHORT).show()
        webView.loadData(htmlString, "text/html", "UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("javascript:document.returnForm_ewallet.submit()");
    }
}