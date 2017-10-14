package com.jlkf.oidemo.contacts.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.contacts.Utils.ClearCashPop;
import com.jlkf.oidemo.other.base.BaseActivity;
import com.jlkf.oidemo.other.utils.DataCleanUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class CacheActivity extends BaseActivity implements ClearCashPop.ICallback {

    @BindView(R.id.clear_btn)
    Button clearBtn;
    @BindView(R.id.info_tv)
    TextView infoTv;
    private String cacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
    }

    @Override
    protected void initViews() {
        //计算缓存大小
        cacheSize = DataCleanUtil.getTotalCacheSize(this);
        infoTv.setText("缓存大小:" + cacheSize);
    }

    @Override
    protected void initEvents() {
        RxView.clicks(clearBtn).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                showCachePop();
            }
        });
    }


    private void showCachePop() {
        ClearCashPop clearCashPop = new ClearCashPop(this, this);
        clearCashPop.showPopupWindow();
    }

    @Override
    protected void initDatas() {

    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, CacheActivity.class);
        context.startActivity(intent);
    }

    //弹窗确定回调
    @Override
    public void sureClick() {
        Snackbar.make(infoTv, "确定", Snackbar.LENGTH_SHORT).show();
        //清除缓存
        DataCleanUtil.clearAllCache(CacheActivity.this);
        //更新缓存大小
        cacheSize = DataCleanUtil.getTotalCacheSize(this);
        infoTv.setText("缓存大小:" + cacheSize);

    }

    //弹窗取消回调
    @Override
    public void cancleClick() {
        Snackbar.make(infoTv, "取消", Snackbar.LENGTH_SHORT).show();
    }
}
