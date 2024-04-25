package com.example.nicepaysnap.view.payout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.example.nicepaysnap.R
import com.example.nicepaysnap.view.BaseMenuAppCompatActivity
import com.example.nicepaysnap.view.payout.approval.PayoutCheckBalance
import com.example.nicepaysnap.view.qris.QrisInquiry
import com.example.nicepaysnap.view.qris.QrisRefund
import com.example.nicepaysnap.view.qris.QrisRegistration

class PayoutMenu : BaseMenuAppCompatActivity() {

    lateinit var checkBalance : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.onCreateMenu(R.layout.layout_menu, "Payout")

        checkBalance = findViewById(R.id.check_balance_button)
        checkBalance.visibility = View.VISIBLE

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

        checkBalance.setOnClickListener {

            val page = Intent(this, PayoutCheckBalance::class.java)
            startActivity(page)

        }

        back.setOnClickListener {
            super.onBackPressed()
        }
    }

}