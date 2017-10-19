package com.jlkf.oidemo.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.jlkf.oidemo.R;

/**
 * Created by DuoNuo on 2017/10/19.
 */

public class SetEnterView extends RelativeLayout {

    public SetEnterView(Context context) {
        super(context);
        init(context,null);
    }

    public SetEnterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public SetEnterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //第三个参数：是否添加到父布局上
        View view = LayoutInflater.from(context).inflate(R.layout.view_set_enter, this, true);
    }
}
