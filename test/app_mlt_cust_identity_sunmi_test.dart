import 'package:flutter_test/flutter_test.dart';
import 'package:app_mlt_cust_identity_sunmi/app_mlt_cust_identity_sunmi.dart';
import 'package:app_mlt_cust_identity_sunmi/app_mlt_cust_identity_sunmi_platform_interface.dart';
import 'package:app_mlt_cust_identity_sunmi/app_mlt_cust_identity_sunmi_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAppMltCustIdentitySunmiPlatform
    with MockPlatformInterfaceMixin
    implements AppMltCustIdentitySunmiPlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final AppMltCustIdentitySunmiPlatform initialPlatform = AppMltCustIdentitySunmiPlatform.instance;

  test('$MethodChannelAppMltCustIdentitySunmi is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAppMltCustIdentitySunmi>());
  });

  test('getPlatformVersion', () async {
    AppMltCustIdentitySunmi appMltCustIdentitySunmiPlugin = AppMltCustIdentitySunmi();
    MockAppMltCustIdentitySunmiPlatform fakePlatform = MockAppMltCustIdentitySunmiPlatform();
    AppMltCustIdentitySunmiPlatform.instance = fakePlatform;

    expect(await appMltCustIdentitySunmiPlugin.getPlatformVersion(), '42');
  });
}
