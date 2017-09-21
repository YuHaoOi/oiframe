package com.jlkf.oidemo.other.base.remote;

import android.app.Application;

import com.jlkf.oidemo.other.bean.UserBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DuoNuo on 2017/9/21.
 */

public class ApiManager {

    private ApiService apiService;
    private Application application;

    public ApiManager(ApiService apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }

    public void login(String userName, String password, SimpleCallback<UserBean> simpleCallback){
        apiService.login(userName, password)
                .flatMap(new BaseResponseFunc<UserBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExceptionSubscriber<UserBean>(simpleCallback, application));
    }
}
