package com.jlkf.oidemo.other.base.di.module;

import com.jlkf.oidemo.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DuoNuo on 2017/9/15.
 */
@Module
public class AppModule {
    private MainApplication application;

    public AppModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public MainApplication provideMainApplication(){
        return application;
    }

}
