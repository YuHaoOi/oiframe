package com.jlkf.oidemo.personal.module;

import com.jlkf.oidemo.personal.View.LoginView;
import com.jlkf.oidemo.personal.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DuoNuo on 2017/9/15.
 */
@Module
public class LoginModule {
    private LoginView loginView;

    public LoginModule(LoginView loginView){
        this.loginView = loginView;
    }
    @Provides
    LoginView provideLoginView(){
        return loginView;
    }

    @Provides
    LoginPresenter provideLoginPresenter(){
        return new LoginPresenter(loginView);
    }
}
