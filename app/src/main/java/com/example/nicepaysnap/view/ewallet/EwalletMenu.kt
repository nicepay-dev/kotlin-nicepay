package com.example.nicepaysnap.view.ewallet

import android.content.Intent
import android.os.Bundle
import com.example.nicepaysnap.R
import com.example.nicepaysnap.view.BaseMenuAppCompatActivity

class EwalletMenu : BaseMenuAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.onCreateMenu(R.layout.layout_menu, "E-Wallet")

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