package com.example.nicepaysnap

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nicepaysnap.nicepay.data.util.nicepayLib
import com.example.nicepaysnap.nicepay.model.additionalInfo
import com.example.nicepaysnap.nicepay.model.totalAmount
import com.example.nicepaysnap.nicepay.model.vaComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class virtualAccount : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var amount: EditText
    lateinit var bank: Spinner
    lateinit var pay: Button
    lateinit var buttonCloseLayout : ImageView
    var responseVA = HashMap<String, String>()
    var lib : nicepayLib = nicepayLib()
    var bOption : String? = "Select Bank"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_account)

        name = findViewById(R.id.editTextName)
        amount = findViewById(R.id.editTextAmount)
        bank = findViewById(R.id.spinnerBank)
        pay = findViewById(R.id.cirPayButton)
        buttonCloseLayout = findViewById(R.id.buttonCloseLayout)

        val bankOption = resources.getStringArray(R.array.bankCode)

        if (bank != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, bankOption)
            bank.adapter = adapter
            bank.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    bOption = bankOption[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        buttonCloseLayout.setOnClickListener{
            super.onBackPressed()
        }

        pay.setOnClickListener {
            val vaAmount : totalAmount = totalAmount.Builder()
                .setValue(amount.text.toString().trim()+".00")
                .build()

            val addInfo : additionalInfo = additionalInfo.Builder()
                .setBankCd(bOption.toString())
                .setGoodsNm("tes va")
                .setDbProcessUrl("https://ptsv2.com/t/test-nicepay-v2/post")
                .build()

            val vaForm : vaComponent = vaComponent.Builder()
                .setVirtualAccountName(name.text.toString().trim())
                .setTrxId("IONPAYTEST")
                .setTotalAmount(vaAmount)
                .setAdditionalInfo(addInfo)
                .build()

            lifecycleScope.launch {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    parseValue(lib.payment(vaForm))
                }
                Toast.makeText(this@virtualAccount,"your virtual account is " + responseVA.get("virtualAccountNo").toString(),Toast.LENGTH_LONG).show()


            }

        }
    }

    suspend fun parseValue(responseData: HashMap<String, String>) {
        // perform parsing operation asynchronously
        responseVA = responseData
        while(responseVA.isEmpty()){
            delay(100)
        }
    }
}

