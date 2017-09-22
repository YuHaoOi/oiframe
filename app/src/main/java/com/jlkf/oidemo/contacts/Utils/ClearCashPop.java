package com.jlkf.oidemo.contacts.Utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;

import com.jlkf.oidemo.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by DuoNuo on 2017/9/22.
 */

public class ClearCashPop extends BasePopupWindow {

    public ClearCashPop(Activity context) {
        super(context);
    }

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

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.clear_cash_pop);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}
