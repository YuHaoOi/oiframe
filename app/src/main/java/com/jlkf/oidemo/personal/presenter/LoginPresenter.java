package com.jlkf.oidemo.personal.presenter;

import android.text.TextUtils;

import com.jlkf.oidemo.AppState;
import com.jlkf.oidemo.other.bean.UserBean;
import com.jlkf.oidemo.personal.View.LoginView;

/**
 * Created by DuoNuo on 2017/9/15.
 */

public class LoginPresenter {

    private final LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void checkInput(String userName, String password){
        loginView.canLogin(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password));
    }

    public void saveUserInfo(UserBean userBean){
        AppState.getInstance().setUserInfo(userBean);
    }

    public String getUserName(){
        return AppState.getInstance().getUserName();
    }

    public String getPassword(){
        return AppState.getInstance().getPassword();
    }
}
