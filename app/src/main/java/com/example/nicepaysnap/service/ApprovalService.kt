package com.example.nicepaysnap.service

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nicepaysnap.nicepay.data.enumeration.EApproval

interface ApprovalService<T, U> {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun approval(request : T, approval : EApproval): HashMap<String, String>

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun checkBalance(request : U): HashMap<String, String>

}