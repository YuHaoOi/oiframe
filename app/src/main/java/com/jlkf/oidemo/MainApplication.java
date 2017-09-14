package com.jlkf.oidemo;

import android.app.Application;

import com.jlkf.oidemo.other.utils.ShareUtils;

public class MainApplication extends Application {

    private static MainApplication instance;
    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        ShareUtils.getInstance().init(this);
    }
}
