package com.example.nicepaysnap.view.qris

import com.example.nicepaysnap.service.MethodService
import com.example.nicepaysnap.service.impl.QrisServiceImpl
import com.example.nicepaysnap.view.BaseAppCompatActivity

open class BaseQrisAppCompatActivity : BaseAppCompatActivity() {

    var qrisService : MethodService = QrisServiceImpl()

}