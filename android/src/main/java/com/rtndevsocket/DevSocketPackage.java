package com.rtndevsocket;

import android.content.pm.ApplicationInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.ReactPackage;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DevSocketPackage implements ReactPackage {

   @Override
   public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
       return Collections.emptyList();
   }

   @RequiresApi(api = Build.VERSION_CODES.DONUT)
   @Override
   public List<NativeModule> createNativeModules(
           ReactApplicationContext reactContext) {
       List<NativeModule> modules = new ArrayList<>();
       boolean isDebuggable =  ( 0 != ( reactContext.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE ) );
       if (isDebuggable) {
           modules.add(new DevSocketModule(reactContext));
       }
       return modules;
   }
}