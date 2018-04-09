package com.jlkf.oidemo.personal.presenter;

import android.text.TextUtils;

import com.jlkf.oidemo.AppState;
import com.jlkf.oidemo.other.base.remote.ApiManager;
import com.jlkf.oidemo.other.base.remote.SimpleCallback;
import com.jlkf.oidemo.other.bean.UserBean;
import com.jlkf.oidemo.personal.View.LoginView;

import io.reactivex.disposables.Disposable;

/**
 * Created by DuoNuo on 2017/9/15
 */

public class LoginPresenter {

    private final LoginView loginView;
    private final ApiManager apiManager;

    public LoginPresenter(LoginView loginView, ApiManager apiManager) {
        this.loginView = loginView;
        this.apiManager = apiManager;
    }

    public void checkInput(String userName, String password){
        loginView.canLogin(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password));
    }

    public Disposable login(String username, String password){
        return apiManager.login(username, password, new SimpleCallback<UserBean>() {
            @Override
            public void onStart() {
                loginView.showLoading();
            }

            @Override
            public void onNext(UserBean userBean) {
                loginView.showUser(userBean);
            }

            @Override
            public void onComplete() {
                loginView.hideLoading();
            }
        });

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
