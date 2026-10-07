package com.neogrup.app_mlt_cust_identity_sunmi

import android.content.Context
import android.os.RemoteException
import android.util.Base64
import com.neogrup.app_mlt_cust_identity_sunmi.CommonVariables
import com.sunmi.peripheral.printer.InnerPrinterCallback
import com.sunmi.peripheral.printer.InnerPrinterException
import com.sunmi.peripheral.printer.InnerPrinterManager
import com.sunmi.peripheral.printer.SunmiPrinterService


class SunmiPrinter {

    private var sunmiPrinterService: SunmiPrinterService? = null
    private var sunmiPrinterServiceGetStatus: SunmiPrinterService? = null
    private var context: Context? = null

    //
    // Functions for printing on Sunmi printers
    //

    private val innerPrinterCallback: InnerPrinterCallback = object : InnerPrinterCallback() {
        public override fun onDisconnected() {
            sunmiPrinterService = null
        }

        public override fun onConnected(service: SunmiPrinterService) {
            sunmiPrinterService = service
            if (mData != null) {
                sunmiPrinterService!!.sendRAWData(mData, null)
                CommonVariables.print_response_channel?.send("0")
            }
            unbindPrinter()
        }
    }

    private fun init(context: Context?) {
        this.context = context
        try {
            InnerPrinterManager.getInstance().bindService(context, innerPrinterCallback)
        } catch (e: InnerPrinterException) {
            e.printStackTrace()
        }
    }

    private fun unbindPrinter() {
        try {
            InnerPrinterManager.getInstance().unBindService(context, innerPrinterCallback)
        } catch (e: InnerPrinterException) {
            e.printStackTrace()
        }
    }

    private var mData: ByteArray? = null

    fun rawPrint(context: Context, data: ByteArray) {
        getPrinterStatusInternal(context, object : iInternalPrinterStatusCallback {
            override fun readedStatus(status: InternalPrinterStatusEnum?) {
                when (status) {
                    InternalPrinterStatusEnum.Normal -> {
                        mData = data
                        init(context)
                    }
                    else -> {
                        CommonVariables.print_response_channel?.send(getValueFromPrinterStatus(status).toString())
                    }
                }
            }
        })
    }

    fun base64Print(context: Context, data: ByteArray) {
        getPrinterStatusInternal(context, object : iInternalPrinterStatusCallback {
            override fun readedStatus(status: InternalPrinterStatusEnum?) {
                when (status) {
                    InternalPrinterStatusEnum.Normal -> {
                        mData = Base64.decode(data, Base64.DEFAULT)
                        init(context)
                    }
                    else -> {
                        CommonVariables.print_response_channel?.send(getValueFromPrinterStatus(status).toString())
                    }
                }
            }
        })
    }

    //
    // Functions for receiving the Sunmi printer status
    //

    enum class InternalPrinterStatusEnum {
        Normal, OutOfPaper, OpenCover, OtherError
    }

    fun getValueFromPrinterStatus (status : InternalPrinterStatusEnum?) : Int {

        return when (status) {
            InternalPrinterStatusEnum.Normal -> 0
            InternalPrinterStatusEnum.OutOfPaper -> 1
            InternalPrinterStatusEnum.OpenCover -> 2
            else -> 3
        }

    }

    interface iInternalPrinterStatusCallback {
        fun readedStatus(status: InternalPrinterStatusEnum?)
    }

    interface InternalPrinterStatusCallback {
        fun readedStatus(status: String?)
    }

    var mStatusCallback: iInternalPrinterStatusCallback? = null

    fun getPrinterStatus (context: Context, callback: InternalPrinterStatusCallback?) : Boolean {
        getPrinterStatusInternal(context, object : iInternalPrinterStatusCallback {
            override fun readedStatus(status: InternalPrinterStatusEnum?) {
                callback?.readedStatus(getValueFromPrinterStatus(status).toString())
            }
        })
        return true
    }

    private fun getPrinterStatusInternal(context: Context, callback: iInternalPrinterStatusCallback?) : Boolean{
        mStatusCallback = callback
        initStatus(context)
        return true
    }

    private fun initStatus(context: Context?) {
        this.context = context
        try {
            InnerPrinterManager.getInstance().bindService(context, innerPrinterCallbackGetStatus)
        } catch (e: InnerPrinterException) {
            e.printStackTrace()
        }
    }

    private fun unbindPrinterStatus() {
        try {
            InnerPrinterManager.getInstance().unBindService(context, innerPrinterCallbackGetStatus)
        } catch (e: InnerPrinterException) {
            e.printStackTrace()
        }
    }

    private val innerPrinterCallbackGetStatus: InnerPrinterCallback = object : InnerPrinterCallback() {
        override fun onConnected(sunmiPrinterService: SunmiPrinterService) {
            sunmiPrinterServiceGetStatus = sunmiPrinterService
            try {
                val result = sunmiPrinterServiceGetStatus!!.updatePrinterState()
                when (result) {
                    1 -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.Normal)
                    2 -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.OtherError)
                    3 -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.OtherError)
                    4 -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.OutOfPaper)
                    5 -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.OtherError)
                    6 -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.OpenCover)
                    7 -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.OtherError)
                    8 -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.OtherError)
                    9 -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.OtherError)
                    else -> if (mStatusCallback != null) mStatusCallback!!.readedStatus(InternalPrinterStatusEnum.Normal)
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            unbindPrinterStatus()
        }

        override fun onDisconnected() {
            sunmiPrinterServiceGetStatus = null
        }
    }



}