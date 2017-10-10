package com.jlkf.oidemo.other.base.remote;

import android.app.Application;

import com.jlkf.oidemo.other.bean.UserBean;

import io.reactivex.disposables.Disposable;

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

    // 返回Disposable解决Activity内存泄漏问题
    public Disposable login(String userName, String password, SimpleCallback<UserBean> simpleCallback){
//        apiService.login(userName, password)
//                .flatMap(new BaseResponseFunc<UserBean>())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ExceptionSubscriber<UserBean>(simpleCallback, application));
         return apiService.login(userName, password)
                .compose(new NetTransformer<UserBean>())
                .subscribeWith(new ExceptionSubscriber<>(simpleCallback, application));
    }
}
