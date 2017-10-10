package com.jlkf.oidemo.other.base.remote;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by DuoNuo on 2017/9/21.
 */

public class BaseResponseFunc<T> implements Function<BaseResponse<T>, Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
        //遇到非200错误统一处理,将BaseResponse转换成您想要的对象
        if (tBaseResponse.code != 200) {
            return Observable.error(new Throwable(tBaseResponse.msg));
        } else {
            if (tBaseResponse.data != null) {
                return Observable.just(tBaseResponse.data);
            } else {
                //返回的data为null的时候，返回msg回去，控制ApiService的泛型为String即可
                return (Observable<T>) Observable.just(tBaseResponse.msg);
            }
        }
    }
}
