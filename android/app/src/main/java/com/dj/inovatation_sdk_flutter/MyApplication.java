package com.dj.inovatation_sdk_flutter;

import android.app.Application;

import com.innovation.animal.breedfunctionsdk.SDK;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化SDK
        SDK.init(this);
    }
}
