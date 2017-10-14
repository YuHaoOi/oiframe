package com.jlkf.oidemo.contacts.Utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.jlkf.oidemo.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by DuoNuo on 2017/9/22.
 */

public class ClearCashPop extends BasePopupWindow implements View.OnClickListener {

    private ICallback callback;

    public ClearCashPop(Activity context, ICallback callback) {
        super(context);
        this.callback = callback;
        //控件初始化
        initView();
    }

    private void initView() {
        TextView cancleTv = (TextView) findViewById(R.id.cancel);
        TextView sureTv = (TextView) findViewById(R.id.ok);
        cancleTv.setOnClickListener(this);
        sureTv.setOnClickListener(this);
    }

    /*弹窗的动画*/
    @Override
    protected Animation initShowAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    /*窗口的布局*/
    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.clear_cash_pop);
    }

    /*添加动画的view*/
    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ok:
                if (callback != null){
                    callback.sureClick();
                }
                break;
            case R.id.cancel:
                if (callback != null){
                    callback.cancleClick();
                }
                break;
        }
        dismiss();
    }

    /**
     * 回调接口：处理点击事件
     */
    public interface ICallback {
        void sureClick();
        void cancleClick();
    }
}
