package com.jlkf.oidemo.personal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.base.BaseFragment;
import com.jlkf.oidemo.personal.activitys.LoginActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * Created by DuoNuo on 2017/5/22
 */

public class PersonalFragment extends BaseFragment {

    @BindView(R.id.login_btn)
    Button loginBtn;

    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, rootView);
        initEvents();
        return rootView;
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        RxView.clicks(loginBtn).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                LoginActivity.actionStart(getContext());
            }
        });
    }
}
