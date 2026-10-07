import 'app_mlt_cust_identity_sunmi_platform_interface.dart';

class AppMltCustIdentitySunmi {
  Future<String?> getPlatformVersion() {
    return FlutterDevicesSunmiPlatform.instance.getPlatformVersion();
  }

  Future<bool?> isSunmiDevice() {
    return FlutterDevicesSunmiPlatform.instance.isSunmiDevice();
  }

  Future<String?> getStatus(String printerDriver, bool byPass) {
    return FlutterDevicesSunmiPlatform.instance.getStatus(printerDriver, byPass);
  }
}
