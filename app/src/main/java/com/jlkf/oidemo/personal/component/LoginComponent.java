package com.jlkf.oidemo.personal.component;

import com.jlkf.oidemo.personal.activitys.LoginActivity;
import com.jlkf.oidemo.personal.module.LoginModule;
import com.jlkf.oidemo.personal.scrope.LoginActivityScrope;

import dagger.Component;

/**
 * Created by DuoNuo on 2017/9/15.
 */
@LoginActivityScrope
@Component(modules = {LoginModule.class})
public interface LoginComponent  {
    void inject(LoginActivity loginActivity);
}
