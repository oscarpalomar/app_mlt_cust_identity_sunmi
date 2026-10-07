package com.neogrup.app_mlt_cust_identity_sunmi


import android.app.Activity
import android.content.Context
import androidx.annotation.NonNull
import android.util.Log
import com.neogrup.app_mlt_cust_identity_sunmi.CommonFunctions
import com.neogrup.app_mlt_cust_identity_sunmi.CommonVariables
import com.neogrup.app_mlt_cust_identity_sunmi.SunmiPrinter
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink
import io.flutter.plugin.common.EventChannel.StreamHandler
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


/** SunmiPrinterPlugin */
public class SunmiPrinterPlugin: FlutterPlugin, MethodCallHandler, StreamHandler, ActivityAware {

  private lateinit var methodChannel : MethodChannel
  private lateinit var eventChannel : EventChannel
  private lateinit var context: Context
  private lateinit var activity: Activity
  private var eventSink: EventSink? = null

  override fun onDetachedFromActivity() {
    //TODO("Not yet implemented")
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    //TODO("Not yet implemented")
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity;
  }

  override fun onDetachedFromActivityForConfigChanges() {
    //TODO("Not yet implemented")
  }

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    CommonVariables.context = flutterPluginBinding.applicationContext
    methodChannel = MethodChannel(flutterPluginBinding.binaryMessenger, CommonVariables.CHANNEL)
    methodChannel.setMethodCallHandler(this)
    CommonVariables.binaryMessenger = flutterPluginBinding.binaryMessenger
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      if (call.method == "isSunmiDevice") {
        result.success(CommonFunctions.IsSunmiDevice())
      } else {
        if (call.method == "doRawPrint") {
          if (call.hasArgument("data")) {
            result.success(SunmiPrinter().rawPrint(context!!, call.argument<ByteArray>("data")!!))
          } else
            result.success(false)
        } else {
          if (call.method == "doBase64Print") {
            if (call.hasArgument("data")) {
              SunmiPrinter().base64Print(context!!, call.argument<ByteArray>("data")!!)
              result.success(true)
            } else
              result.success(false)
            //result.error("NO_COMPATIBLE", "Device isn't compatible with Sunmi print API", null)
          } else {
            if (call.method == "getPrintStatus") {
              SunmiPrinter().getPrinterStatus(context!!)
              result.success(true)
            } else {
              result.notImplemented()
            }
          }
        }
      }
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    methodChannel.setMethodCallHandler(null)
  }

  override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
    this.eventSink = events
  }

  override fun onCancel(arguments: Any?) {
    this.eventSink = null
  }

}
