package com.jlkf.oidemo;

import android.app.Application;
import android.util.Log;

import com.jlkf.oidemo.other.base.di.component.AppComponent;
import com.jlkf.oidemo.other.base.di.component.DaggerAppComponent;
import com.jlkf.oidemo.other.base.di.module.ApiModule;
import com.jlkf.oidemo.other.base.di.module.AppModule;
import com.jlkf.oidemo.other.utils.FakeCrashLibrary;
import com.jlkf.oidemo.other.utils.ShareUtils;

import timber.log.Timber;

public class MainApplication extends Application {

    private AppComponent mAppComponent;
    private static MainApplication mainApplication;

    public static MainApplication get(){
        return mainApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
        ShareUtils.getInstance().init(this);
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).apiModule(new ApiModule()).build();
        initTimber();

    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }


    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }
            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
