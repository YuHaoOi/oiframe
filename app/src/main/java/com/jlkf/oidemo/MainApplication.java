package com.jlkf.oidemo;

import android.app.Application;

import com.jlkf.oidemo.other.base.di.component.AppComponent;
import com.jlkf.oidemo.other.base.di.component.DaggerAppComponent;
import com.jlkf.oidemo.other.base.di.module.AppModule;
import com.jlkf.oidemo.other.utils.ShareUtils;

public class MainApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ShareUtils.getInstance().init(this);
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
