package com.jlkf.oidemo.other.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.event.OnTitleEvent;
import com.jlkf.oidemo.other.utils.AppManager;

import butterknife.ButterKnife;


/**
 * description
 *
 * @author created by wuwang on 2016/4/25
 */
public abstract class BaseActivity extends AppCompatActivity implements OnTitleEvent {
    protected ProgressDialog waitDialog;
    public Title title;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //设置屏幕竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //保存每个activity
        AppManager.activityCreated(this);
        //隐藏键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /***********************初始化方法********************************************************/
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initViews();
        initEvents();
        initDatas();
    }

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void initDatas();


    @Override
    protected void onDestroy() {
        AppManager.activityDestroyed(this);
        super.onDestroy();
    }

    /*************************标题栏***********************************************************/

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


    /********************公共方法*****************************************************************/

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


    public Toast toast(CharSequence toast) {
        @SuppressLint("ShowToast") Toast t = Toast.makeText(this, toast, Toast.LENGTH_SHORT);
        t.show();
        return t;
    }

    public int gColor(int id){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getColor(id);
        }else{
            return this.getResources().getColor(id);
        }
    }

    protected void hideSoftKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public int getDisplayWidth() {
        int width = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        return width;
    }

    public int getDisplayHeight() {
        int height = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getHeight();
        return height;
    }
}
