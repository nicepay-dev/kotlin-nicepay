package com.example.nicepaysnap.view.ewallet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.nicepaysnap.R

class EwalletMenu : AppCompatActivity() {

    lateinit var register : AppCompatButton
    lateinit var inquiry : AppCompatButton
    lateinit var refund : AppCompatButton
    lateinit var back : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_ewallet)

        register = findViewById(R.id.register_button)
        inquiry = findViewById(R.id.inquiry_button)
        refund = findViewById(R.id.refund_button)
        back = findViewById(R.id.back_button)

        register.setOnClickListener {

            val page = Intent(this, EwalletRegistration::class.java)
            startActivity(page)

        }

        inquiry.setOnClickListener {

            val page = Intent(this, EwalletInquiry::class.java)
            startActivity(page)

        }

        refund.setOnClickListener {

            val page = Intent(this, EwalletRefund::class.java)
            startActivity(page)

        }

        back.setOnClickListener {
            super.onBackPressed()
        }

    }

}