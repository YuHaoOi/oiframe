package com.jlkf.oidemo.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jlkf.oidemo.R;
import com.jlkf.oidemo.home.widget.SetEnterView;
import com.jlkf.oidemo.other.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetEnterActivity extends BaseActivity {


    @BindView(R.id.name_sev)
    SetEnterView nameSev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_enter);
        ButterKnife.bind(this);
    }

    @Override
    protected void initViews() {
        nameSev.setLeftText("我的头像");
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SetEnterActivity.class);
        context.startActivity(intent);
    }
}
