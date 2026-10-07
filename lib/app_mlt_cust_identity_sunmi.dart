import 'app_mlt_cust_identity_sunmi_platform_interface.dart';

class AppMltCustIdentitySunmi {
  Future<String?> getPlatformVersion() {
    return FlutterDevicesSunmiPlatform.instance.getPlatformVersion();
  }

  Future<bool?> isSunmiDevice() {
    return FlutterDevicesSunmiPlatform.instance.isSunmiDevice();
  }

  Future<String?> getPrintStatus() {
    return FlutterDevicesSunmiPlatform.instance.getPrintStatus();
  }
}
