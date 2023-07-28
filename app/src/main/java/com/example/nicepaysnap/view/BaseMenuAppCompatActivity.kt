package com.example.nicepaysnap.view

import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.nicepaysnap.R

open class BaseMenuAppCompatActivity : AppCompatActivity() {

    lateinit var register : AppCompatButton
    lateinit var inquiry : AppCompatButton
    lateinit var refund : AppCompatButton
    lateinit var back : ImageButton
    lateinit var menuTextView : TextView

    fun onCreateMenu(view : Int, menuName : String) {
        setContentView(view)

        menuTextView = findViewById(R.id.textview_first)
        menuTextView.text = menuName

        register = findViewById(R.id.register_button)
        inquiry = findViewById(R.id.inquiry_button)
        refund = findViewById(R.id.refund_button)
        back = findViewById(R.id.back_button)
    }

}