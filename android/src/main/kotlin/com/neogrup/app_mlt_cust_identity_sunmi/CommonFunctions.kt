package com.neogrup.app_mlt_cust_identity_sunmi

import android.os.Build
import android.util.Log

object CommonFunctions {

    fun IsSunmiDevice () : Boolean {
        return IsSunmiT2Mini()
    }

    private fun IsSunmiT2Mini(): Boolean {
        Log.d ("IsSunmiT2Mini", "Device: ${Build.DEVICE} Model: ${Build.MODEL}")
        if (Build.DEVICE == "D3MINI" && Build.MODEL == "D3 MINI") return true
        if (Build.DEVICE == "T2mini" && Build.MODEL == "T2mini") return true
        return if (Build.DEVICE == "T2mini_s" && Build.MODEL == "T2mini_s") true else false
    }


}