package com.jlkf.oidemo.other.base.remote;

/**
 * Created by DuoNuo on 2017/9/21.
 */
public interface SimpleCallback<T> {
    void onStart();
    void onNext(T t);
    void onComplete();
}
