package com.rtndevsocket;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.devsupport.DevInternalSettings;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.packagerconnection.JSPackagerClient;
import com.facebook.react.packagerconnection.NotificationOnlyHandler;
import com.facebook.react.packagerconnection.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class DevSocketModule extends ReactContextBaseJavaModule {
    private final JSPackagerClient mPackagerClient;
    private final DevInternalSettings mSettings;

    DevSocketModule(ReactApplicationContext context) {
        super(context);
        mSettings = new DevInternalSettings(context, null);
        Map<String, RequestHandler> handlers = new HashMap<>();
        handlers.put(
                "devSocket",
                new NotificationOnlyHandler() {
                    @Override
                    public void onNotification(@Nullable Object params) {
                        sendEvent(context, "broadcast", params);
                    }
                });
        mPackagerClient = new JSPackagerClient(this.getClass().getName(), mSettings.getPackagerConnectionSettings(), handlers);
        mPackagerClient.init();
    }

    @Override
    public String getName() {
      return "DevSocketModule";
    }

    private void sendEvent(ReactContext reactContext,
                          String eventName,
                          @Nullable Object params) {
      reactContext
          .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit(eventName, params);
    }

}
