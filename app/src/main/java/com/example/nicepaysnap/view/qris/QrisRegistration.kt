package com.example.nicepaysnap.view.qris

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
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
import com.example.nicepaysnap.nicepay.model.RequestQrisAdditionalInfo
import com.example.nicepaysnap.nicepay.model.RequestQrisRegister
import com.example.nicepaysnap.nicepay.model.totalAmount
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar


class QrisRegistration : BaseQrisAppCompatActivity() {

    lateinit var goodsName: EditText
    lateinit var billingName: EditText
    lateinit var amount: EditText
    lateinit var mitra: Spinner
    lateinit var regist: Button
    lateinit var buttonCloseLayout : ImageView
    lateinit var imageQrResult : ImageView
    lateinit var buttonDownloadQr : Button
    var qrUrlResult : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qris)

        var txId : EditText = findViewById(R.id.editResultTxId)
        var refNo : EditText = findViewById(R.id.editResultReferenceNo)
        var validityPeriod : EditText = findViewById(R.id.editResultValidityPeriod)

        goodsName = findViewById(R.id.editTextGoodsName)
        billingName = findViewById(R.id.editTextBillingName)
        amount = findViewById(R.id.editTextEwalletAmount)
        mitra = findViewById(R.id.spinnerMitra)
        regist = findViewById(R.id.registerEwalletButton)
        buttonCloseLayout = findViewById(R.id.buttonCloseLayout)
        imageQrResult = findViewById(R.id.imageQrResult)
        buttonDownloadQr = findViewById(R.id.buttonDownloadQr)
        var tOption : String? = "Select Mitra"

        val mitraOption = resources.getStringArray(R.array.mitraCodeQr)

        var resultLayout  : View = findViewById(R.id.id_layout_result_generate_qr)
        resultLayout.setVisibility(View.GONE)

        if (mitra != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, mitraOption)
            mitra.adapter = adapter
            mitra.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    if (mitraOption[position].toString().contains("ShopeePay")) {
                        tOption = "QSHP"
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

            if (goodsName.text.toString() == "") goodsName.setText("Testing SNAP E-Wallet Item Name")
            if (billingName.text.toString() == "") billingName.setText("Testing SNAP E-Wallet Billing Name")
            val additionalInfo : RequestQrisAdditionalInfo = RequestQrisAdditionalInfo(
                tOption.toString(), goodsName.text.toString(), billingName.text.toString(),
                "{\"count\":1,\"item\":[{\"img_url\":\"https://d3nevzfk7ii3be.cloudfront.net/igi/vOrGHXlovukA566A.medium\",\"goods_name\":${goodsName.text.toString()},\"goods_detail\":\"Goods Detail\",\"goods_amt\":${amount.text.toString()},\"goods_quantity\":\"1\"}]}",
                "089876543210",
                "testing.ionpaytest@nicepay.co.id", "Jakarta Selatan", "Jl. Raya Casablanca Raya",
                "DKI Jakarta", "12870", "Indonesia"
            )

            val qrisRequest : RequestQrisRegister = RequestQrisRegister(
                "ref-" + SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis()),
                amountValue, setValidityPeriod(), additionalInfo
            )

            lifecycleScope.launch {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (amount.text.toString() == "") {
                        Toast.makeText(applicationContext, "Amount must not be empty", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.i(this.toString() + " Response : ", parseValue(qrisService.register(qrisRequest)).toString())

                        val qrContent = responseRest.get("qrContent")
                        qrUrlResult = responseRest.get("qrUrl").toString()
                        imageQrResult.setImageBitmap(getQrCodeBitmap(qrContent))

                        txId.setText(responseRest.get("referenceNo"))
                        refNo.setText(responseRest.get("partnerReferenceNo"))
                        validityPeriod.setText(responseRest.get("validityPeriod"))

                        resultLayout.setVisibility(View.VISIBLE)
                    }
                }
            }

        }

        buttonDownloadQr.setOnClickListener {
            val httpIntent = Intent(Intent.ACTION_VIEW)
            httpIntent.setData(Uri.parse(qrUrlResult))
            startActivity(httpIntent)
        }
    }

    private fun setValidityPeriod() : String {
        val dateFormat : DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        var calendar : Calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(GregorianCalendar.SECOND, 1200)
        return dateFormat.format(calendar.time)
    }

    private fun getQrCodeBitmap(content: String?): Bitmap {
        val size = 512 //pixels
        // Make the QR code buffer border narrower
        // val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 }
        val bits = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

}