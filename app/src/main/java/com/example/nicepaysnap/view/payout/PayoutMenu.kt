package com.example.nicepaysnap.view.payout

import android.content.Intent
import android.os.Bundle
import com.example.nicepaysnap.R
import com.example.nicepaysnap.view.BaseMenuAppCompatActivity
import com.example.nicepaysnap.view.qris.QrisInquiry
import com.example.nicepaysnap.view.qris.QrisRefund
import com.example.nicepaysnap.view.qris.QrisRegistration

class PayoutMenu : BaseMenuAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.onCreateMenu(R.layout.layout_menu, "Payout")

        register.setOnClickListener {

            val page = Intent(this, PayoutRegistration::class.java)
            startActivity(page)

        }

        inquiry.setOnClickListener {

            val page = Intent(this, PayoutInquiry::class.java)
            startActivity(page)

        }

        refund.setOnClickListener {

            val page = Intent(this, PayoutCancel::class.java)
            startActivity(page)

        }

        back.setOnClickListener {
            super.onBackPressed()
        }
    }

}