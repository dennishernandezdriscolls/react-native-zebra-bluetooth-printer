
package com.zebrabluetoothprinter;

import com.facebook.react.bridge.Callback;
import android.widget.Toast;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import com.facebook.react.bridge.*;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Handler;

public class RNZebraBluetoothPrinterModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private BluetoothAdapter bluetoothAdapter;
  public BluetoothManager bluetoothManager;
  private boolean mScanning;
  public static Context context;
  private Handler handler;
  private static final long SCAN_PERIOD = 10000;
  private static final int BT_ENABLED_REQUEST = 1;
  private Activity activity;

  public void getBluetoothManagerInstance(Context c) {
    this.bluetoothManager = (BluetoothManager) c.getSystemService(Context.BLUETOOTH_SERVICE);
    this.bluetoothAdapter = this.bluetoothManager.getAdapter();
  }
  public RNZebraBluetoothPrinterModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    context = getReactApplicationContext();
    this.getBluetoothManagerInstance(context);
  
  }

  @Override
  public String getName() {
    return "RNZebraBluetoothPrinter";
  }
  
  @ReactMethod
  public void show(String text) {
    // Context context = getReactApplicationContext();
    Toast.makeText(getReactApplicationContext(), text, Toast.LENGTH_LONG).show();
  }
  @ReactMethod
  public void isEnabledBluetooth(final Promise promise) {
    // Ensures Bluetooth is available on the device and it is enabled. If not,
    // displays a dialog requesting user permission to enable Bluetooth.
    if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      // Activity a = (Activity) getContext();
      // a.startActivityForResult(enableBtIntent, BT_ENABLED_REQUEST);
      promise.resolve(false);
    }
    else {
      promise.resolve(true);
    }
  }
  // @Reactmethod 
  // public void scanDevices() {

  // }
}