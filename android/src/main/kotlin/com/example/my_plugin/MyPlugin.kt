package com.example.my_plugin

// import android.os.Bundle
// import io.flutter.embedding.android.FlutterActivity
// import android.view.WindowManager
// import io.flutter.embedding.engine.FlutterEngine
// import io.flutter.plugin.common.MethodCall
// import io.flutter.plugin.common.MethodChannel;
// import io.flutter.plugins.GeneratedPluginRegistrant;

// class MainActivity: FlutterActivity() {

// private val CHANNEL = "com.example.my_plugin"
// override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
//     GeneratedPluginRegistrant.registerWith(flutterEngine)
//     MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
//             .setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
//                 if (call.method == "getDeviceModel") {
//                     val deviceModel = isVM();
//                     result.success(deviceModel)
//                 } else {
//                     // if called undefined method
//                     result.notImplemented()
//                 }
//             }

// }
// }
// fun isVM(): Boolean {
// val radioVersion = android.os.Build.getRadioVersion()
// return radioVersion == null || radioVersion.isEmpty() || radioVersion == "1.0.0.0"
// }

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** MyPlugin */
class MyPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "com.example.my_plugin")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getDeviceModel") {
      val emulatorType =isVM()
      result.success(emulatorType)
    } else {
      result.notImplemented()
    }
  }
  fun isVM(): Boolean {
    val radioVersion = android.os.Build.getRadioVersion()
    return radioVersion == null || radioVersion.isEmpty() || radioVersion == "1.0.0.0"
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
