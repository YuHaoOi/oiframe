package com.jlkf.oidemo.personal.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jlkf.oidemo.MainApplication;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.base.BaseActivity;
import com.jlkf.oidemo.other.bean.UserBean;
import com.jlkf.oidemo.personal.View.LoginView;
import com.jlkf.oidemo.personal.module.LoginModule;
import com.jlkf.oidemo.personal.presenter.LoginPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import timber.log.Timber;


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
    }

    @Override
    protected void initViews() {
        MainApplication.get().getAppComponent().plus(new LoginModule(this)).inject(this);
    }


    @Override
    protected void initEvents() {
        //登录
        RxView.clicks(login).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                loginPresenter.login("18589080625","ede8fe4747908d485c17f01978e617ab");
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
        setLoading(true);
    }

    @Override
    public void hideLoading() {
        setLoading(false);
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
        Timber.e(userBean.nickname);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
