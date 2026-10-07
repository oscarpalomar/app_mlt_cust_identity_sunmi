import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'app_mlt_cust_identity_sunmi_method_channel.dart';

abstract class FlutterDevicesSunmiPlatform extends PlatformInterface {
  /// Constructs a FlutterDevicesSunmiPlatform.
  FlutterDevicesSunmiPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterDevicesSunmiPlatform _instance = MethodChannelFlutterDevicesSunmi();

  /// The default instance of [FlutterDevicesSunmiPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterDevicesSunmi].
  static FlutterDevicesSunmiPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterDevicesSunmiPlatform] when
  /// they register themselves.
  static set instance(FlutterDevicesSunmiPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<bool?> isSunmiDevice() {
    throw UnimplementedError('isSunmiDevice() has not been implemented.');
  }

  Future<String?> getStatus(String printerDriver, bool byPass) {
    throw UnimplementedError('getStatus() has not been implemented.');
  }
}
