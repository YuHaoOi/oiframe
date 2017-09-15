package com.jlkf.oidemo.other.base.di.component;

import com.jlkf.oidemo.MainApplication;
import com.jlkf.oidemo.other.base.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DuoNuo on 2017/9/15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    MainApplication provideMainApplication();
}
