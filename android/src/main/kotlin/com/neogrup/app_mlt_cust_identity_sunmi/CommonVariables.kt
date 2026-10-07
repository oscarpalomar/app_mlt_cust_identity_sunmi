package com.neogrup.app_mlt_cust_identity_sunmi


import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StringCodec

@SuppressLint("StaticFieldLeak")
object CommonVariables {

    var context: Context? = null
    val CHANNEL : String = "flutter_devices_sunmi"
    var EVENT: String = "flutter_devices_sunmi_event"

    var status_response_channel : BasicMessageChannel<String>? = null
    var print_response_channel : BasicMessageChannel<String>? = null

}