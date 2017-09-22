package com.jlkf.oidemo.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.contacts.activity.CacheActivity;
import com.jlkf.oidemo.other.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by DuoNuo on 2017/5/22
 */

public class ContactFragment extends BaseFragment {
    @BindView(R.id.cache_btn)
    Button cacheBtn;

    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        return rootView;
    }

    @Override
    protected void initEvents() {
        //
        RxView.clicks(cacheBtn).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                CacheActivity.actionStart(getContext());
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {

    }


}
