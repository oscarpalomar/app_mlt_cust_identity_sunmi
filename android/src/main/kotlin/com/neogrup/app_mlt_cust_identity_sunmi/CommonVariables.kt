package com.neogrup.app_mlt_cust_identity_sunmi


import android.content.Context
import android.os.Build
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StringCodec

object CommonVariables {

    var context: Context? = null
    val CHANNEL : String = "com.neogrup.flutter/sunmi_printer_plugin"
    var binaryMessenger : BinaryMessenger? = null

    var status_response_channel : BasicMessageChannel<String>? = null
    var print_response_channel : BasicMessageChannel<String>? = null

}