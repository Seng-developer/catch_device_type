import 'dart:async';
import 'package:flutter/services.dart';

class MyPlugin {
  static const MethodChannel _channel = MethodChannel('com.example.my_plugin');
  static Future<bool?> get platformVersion async {
    final bool? version = await _channel.invokeMethod('getDeviceModel');
    return version;
  }
}
