import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:app_mlt_cust_identity_sunmi/app_mlt_cust_identity_sunmi_method_channel.dart';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();

  MethodChannelAppMltCustIdentitySunmi platform = MethodChannelAppMltCustIdentitySunmi();
  const MethodChannel channel = MethodChannel('app_mlt_cust_identity_sunmi');

  setUp(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger
        .setMockMethodCallHandler(channel, (MethodCall methodCall) async {
          return '42';
        });
  });

  tearDown(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger
        .setMockMethodCallHandler(channel, null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
