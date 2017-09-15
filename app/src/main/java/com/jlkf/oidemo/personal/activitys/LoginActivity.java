package com.jlkf.oidemo.personal.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.base.BaseActivity;
import com.jlkf.oidemo.other.bean.UserBean;
import com.jlkf.oidemo.personal.View.LoginView;
import com.jlkf.oidemo.personal.component.DaggerLoginComponent;
import com.jlkf.oidemo.personal.module.LoginModule;
import com.jlkf.oidemo.personal.presenter.LoginPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);
    }


    @Override
    protected void initEvents() {
        //登录
        RxView.clicks(login).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
            }
        });
        //用户名
        RxTextView.textChanges(username).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(@NonNull CharSequence charSequence) throws Exception {
                loginPresenter.checkInput(charSequence.toString(), password.getText().toString());
            }
        });
        //密码
        RxTextView.textChanges(password).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(@NonNull CharSequence charSequence) throws Exception {
                loginPresenter.checkInput(username.getText().toString(), charSequence.toString());
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void canLogin(boolean canLogin) {
        if (canLogin){
            login.setEnabled(true);
            login.setBackgroundColor(Color.GREEN);
        } else {
            login.setEnabled(false);
            login.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public void showUser(UserBean userBean) {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
