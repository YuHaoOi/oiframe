package com.jlkf.oidemo.personal.module;

import com.jlkf.oidemo.other.base.remote.ApiManager;
import com.jlkf.oidemo.personal.View.LoginView;
import com.jlkf.oidemo.personal.presenter.LoginPresenter;
import com.jlkf.oidemo.personal.scrope.PerActivity;

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

    @Provides @PerActivity
    public LoginView provideLoginView(){
        return loginView;
    }

    @Provides @PerActivity
    public LoginPresenter provideLoginPresenter(ApiManager apiManager){
        return new LoginPresenter(loginView, apiManager);
    }
}
