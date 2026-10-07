import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'app_mlt_cust_identity_sunmi_platform_interface.dart';

/// An implementation of [FlutterDevicesSunmiPlatform] that uses method channels.
class MethodChannelFlutterDevicesSunmi extends FlutterDevicesSunmiPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_devices_sunmi');
  final eventChannel = const EventChannel('flutter_devices_sunmi_event');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<bool?> isSunmiDevice() async {
    try {
      final bool? result = await methodChannel.invokeMethod('isSunmiDevice');
      return result;
    } on PlatformException catch (_) {
      return false;
    } on MissingPluginException catch (_) {
      return false;
    }
  }

  @override
  Future<String?> getPrintStatus() async {
    var completer = Completer<String?>();
    eventChannel.receiveBroadcastStream().listen((event) {
      //print("Event: $event");
      completer.complete(event);
    });
    methodChannel.invokeMethod<String?>('getPrintStatus');
    return completer.future;
  }

  @override
  Future<bool?> doRawPrint(Uint8List data) async {
    try {
      final bool? result = await methodChannel.invokeMethod('doRawPrint', data);
      return result;
    } on PlatformException catch (_) {
      return false;
    } on MissingPluginException catch (_) {
      return false;
    }
  }

  @override
  Future<bool?> doBase64Print(Uint8List data) async {
    try {
      final bool? result = await methodChannel.invokeMethod('doBase64Print', data);
      return result;
    } on PlatformException catch (_) {
      return false;
    } on MissingPluginException catch (_) {
      return false;
    }
  }
}
