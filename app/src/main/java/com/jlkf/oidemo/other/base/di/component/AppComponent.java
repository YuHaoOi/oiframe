package com.jlkf.oidemo.other.base.di.component;

import com.jlkf.oidemo.other.base.di.module.ApiModule;
import com.jlkf.oidemo.other.base.di.module.AppModule;
import com.jlkf.oidemo.personal.component.LoginComponent;
import com.jlkf.oidemo.personal.module.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DuoNuo on 2017/9/15.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    LoginComponent plus(LoginModule loginModule);
}
