package com.jlkf.oidemo.other.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.event.OnTitleEvent;
import com.jlkf.oidemo.other.utils.AppManager;


/**
 * description
 *
 * @author created by wuwang on 2016/4/25
 */
public abstract class BaseActivity extends AppCompatActivity implements OnTitleEvent {
    protected ProgressDialog waitDialog;
    public Title title;
    protected Context mContext;
    public LinearLayout topBarLl;

    @Override
    public void onLeftClick(View view) {
        hideSoftKeyboard();
        finish();
    }

    @Override
    public void onRightClick(View view) {

    }

    @Override
    public void onCenterClick(View view) {

    }

    public int getDisplayWidth() {
        int width = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        return width;
    }

    public int getDisplayHeight() {
        int height = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getHeight();
        return height;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppManager.activityCreated(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void initDatas();


    @Override
    protected void onDestroy() {
        AppManager.activityDestroyed(this);
        super.onDestroy();
    }

    /**
     * 是否为当前的activity添加标题
     */
    public void supportTitle(boolean isSupport) {
        if (isSupport) {
            View v = findViewById(R.id.title);
            if (v != null) {
                title = new Title(v, this);
            } else {
                try {
                    throw new Exception("Cannot find Title");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void setLoading(boolean isLoading) {
        try {
            if (isLoading) {
                if (waitDialog == null || !waitDialog.isShowing()) {
                    waitDialog = new ProgressDialog(BaseActivity.this, R.style.MyDialogStyleBottom);
                    waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    waitDialog.setCanceledOnTouchOutside(false);
                    ImageView view = new ImageView(BaseActivity.this);
                    view.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    Animation loadAnimation = AnimationUtils.loadAnimation(
                            BaseActivity.this, R.anim.rotate);
                    view.startAnimation(loadAnimation);
                    loadAnimation.start();
                    view.setImageResource(R.mipmap.loading);
                    waitDialog.show();
                    waitDialog.setContentView(view);
                }
            } else {
                if (waitDialog != null && waitDialog.isShowing()) {
                    waitDialog.dismiss();
                    waitDialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Toast toast(CharSequence toast){
        @SuppressLint("ShowToast") Toast t= Toast.makeText(this, toast, Toast.LENGTH_SHORT);
        t.show();
        return t;
    }

    protected void hideSoftKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}