package com.example.nicepaysnap.view.ewallet

import com.example.nicepaysnap.service.MethodService
import com.example.nicepaysnap.service.impl.EwalletServiceImpl
import com.example.nicepaysnap.view.BaseAppCompatActivity

open class BaseEwalletAppCompatActivity : BaseAppCompatActivity() {

    var ewalletService : MethodService = EwalletServiceImpl()

}