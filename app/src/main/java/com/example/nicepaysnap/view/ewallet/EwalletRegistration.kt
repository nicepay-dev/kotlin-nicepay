package com.example.nicepaysnap.view.ewallet

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
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.R
import com.example.nicepaysnap.nicepay.model.RequestEWalletAdditionalInfo
import com.example.nicepaysnap.nicepay.model.RequestEwalletDirectDebit
import com.example.nicepaysnap.nicepay.model.RequestEwalletUrlParam
import com.example.nicepaysnap.nicepay.model.totalAmount
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


class EwalletRegistration : BaseEwalletAppCompatActivity() {

    lateinit var goodsName: EditText
    lateinit var billingPhone: EditText
    lateinit var amount: EditText
    lateinit var mitra: Spinner
    lateinit var regist: Button
    lateinit var buttonCloseLayout : ImageView
    var tOption : String? = "Select Mitra"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ewallet)

        goodsName = findViewById(R.id.editTextGoodsName)
        billingPhone = findViewById(R.id.editTextBillingPhone)
        amount = findViewById(R.id.editTextEwalletAmount)
        mitra = findViewById(R.id.spinnerMitra)
        regist = findViewById(R.id.registerEwalletButton)
        buttonCloseLayout = findViewById(R.id.buttonCloseLayout)

        val mitraOption = resources.getStringArray(R.array.mitraCode)

        if (mitra != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, mitraOption)
            mitra.adapter = adapter
            mitra.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    if (mitraOption[position].toString().equals("ShopeePay")) {
                        tOption = "ESHP"
                    } else if (mitraOption[position].toString().equals("OVO")) {
                        tOption = "OVOE"
                    } else if (mitraOption[position].toString().equals("LinkAja")) {
                        tOption = "LINK"
                    } else {
                        tOption = mitraOption[position]
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
            if (goodsName.text.toString() == "") goodsName.setText("Testing SNAP E-Wallet Item Name")
            val additionalInfo = RequestEWalletAdditionalInfo(
                tOption.toString(), goodsName.text.toString(), "Testing e-wallet SNAP for Android Simulation", billingPhone.text.toString(),
                "http://ptsv2.com/t/dbProcess/post", "https://www.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp",
                "{\"count\":\"1\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"${goodsName.text.toString()}\",\"goods_detail\":\"Item Detail\",\"goods_amt\":\"${amountValue.value}\",\"goods_quantity\":\"1\"}]}",
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
                        Log.i(this.toString() + " Response : ", parseValue(ewalletService.register(ewalletRequest)).toString())

                        val redirectUrl = responseRest.get("webRedirectUrl").toString()
                        val responseRedirect = Uri.parse(redirectUrl)
                        if (tOption.toString().equals("LINK")) {
                            linkProcess(responseRest.get("redirectToken").toString(), redirectUrl)
                        } else if (tOption.toString().equals("OVOE")) {
                            Log.i("OVO Response ", responseRest.get("responseMessage").toString())
                            if (responseRest.get("responseMessage").toString().contains("Success"))
                                Toast.makeText(applicationContext, " Response Message from OVO "
                                        + responseRest.get("responseMessage").toString()
                                        + if (responseRest["responseMessage"].toString()
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

    fun linkProcess(redirectToken : String, redirectUrl : String) {
        val webView = findViewById<View>(R.id.webview) as WebView
        val htmlString = "<form \n" +
                "id=\"returnForm_ewallet\" \n" +
                "name=\"returnForm_ewallet\"\n" +
                "action=\"${redirectUrl}\" method=\"post\">\n" +
                "    <input type=\"text\" name=\"Message\" \n" +
                "    value=\"${redirectToken}\" \n" +
                "    style=\"display: none;\">\n" +
                "        <center><img id=\"loading-image\" src=\"https://upload.wikimedia.org/wikipedia/commons/c/c7/Loading_2.gif?20170503175831\" width=\"74\" alt=\"Loading...\" /></center>\n" +
                "</form>" + "\n" +
                "<script>\n" +
                "\tsetInterval(\n" +
                "\t\t\tfunction(){ document.getElementById('returnForm_ewallet').submit() },\n" +
                "\t\t\t1000\n" +
                "\t)\n" +
                "</script>"

        Toast.makeText(applicationContext, "Please wait and submit the form above", Toast.LENGTH_SHORT).show()
        webView.loadData(htmlString, "text/html", "UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("javascript:document.returnForm_ewallet.submit()");
    }
}