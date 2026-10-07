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
    methodChannel = MethodChannel(flutterPluginBinding.binaryMessenger, CommonVariables.CHANNEL)
    methodChannel.setMethodCallHandler(this)
    eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, CommonVariables.EVENT)
    eventChannel.setStreamHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    when (call.method) {
      "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
      "isSunmiDevice" -> result.success(CommonFunctions.IsSunmiDevice())
      "doRawPrint" -> {
        if (call.hasArgument("data")) {
          result.success(SunmiPrinter().rawPrint(context, call.argument<ByteArray>("data")!!, object: SunmiPrinter.InternalPrinterCallback {
            override fun result(success: Boolean) {
              eventSink?.success(success)
            }
          })
        } else {
          eventSink?.success(false)
        }
      }
      "doBase64Print" -> {
        if (call.hasArgument("data")) {
          SunmiPrinter().base64Print(context, call.argument<ByteArray>("data")!!, object: SunmiPrinter.InternalPrinterCallback {
            override fun result(success: Boolean) {
              eventSink?.success(success)
            }
          })
        } else {
          eventSink?.success(false)
        }
      }
      "getPrintStatus" -> {
        SunmiPrinter().getPrinterStatus(context, object: SunmiPrinter.InternalPrinterStatusCallback {
          override fun readedStatus(status: String?) {
            eventSink?.success(status)
          }
        })
      }
      else -> result.notImplemented()
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
