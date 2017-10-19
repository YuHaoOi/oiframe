package com.jlkf.oidemo.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.base.BaseActivity;

public class SetEnterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_enter);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, SetEnterActivity.class);
        context.startActivity(intent);
    }
}
