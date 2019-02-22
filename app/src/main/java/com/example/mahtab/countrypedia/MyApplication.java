package com.example.mahtab.countrypedia;

import android.app.Application;

import com.orm.SugarContext;

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }
}
