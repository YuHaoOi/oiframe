package com.jlkf.oidemo.home.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jlkf.oidemo.R;

/**
 * Created by DuoNuo on 2017/10/19.
 */

public class SetEnterView extends RelativeLayout {


    private TextView nameTv;

    public SetEnterView(Context context) {
        super(context);
//        init(context,null);
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
        ImageView leftIv = (ImageView) view.findViewById(R.id.left_iv);
        ImageView rightIv = (ImageView) view.findViewById(R.id.right_iv);
        nameTv = (TextView) view.findViewById(R.id.name_tv);
        TextView infoTv = (TextView) view.findViewById(R.id.info_tv);
        View bottomLine = view.findViewById(R.id.bottom_line);
        //获取自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SetEnterView, 0, 0);
        int leftIcon = typedArray.getResourceId(R.styleable.SetEnterView_left_icon, 0);
        int rightIcon = typedArray.getResourceId(R.styleable.SetEnterView_right_icon, 0);
        String leftText = typedArray.getString(R.styleable.SetEnterView_left_text);
        String infoText = typedArray.getString(R.styleable.SetEnterView_info_text);
        int color = typedArray.getColor(R.styleable.SetEnterView_left_text_color, Color.GRAY);
        boolean isShowBottomView = typedArray.getBoolean(R.styleable.SetEnterView_show_bottom_line, true);
        boolean isShowLeftIcon = typedArray.getBoolean(R.styleable.SetEnterView_show_left_icon, true);
        boolean isShowInfo = typedArray.getBoolean(R.styleable.SetEnterView_show_info, true);
        typedArray.recycle();
        //设置
        leftIv.setImageResource(leftIcon);
        rightIv.setImageResource(rightIcon);
        nameTv.setText(leftText);
        infoTv.setText(infoText);
        nameTv.setTextColor(color);
        bottomLine.setVisibility(isShowBottomView?VISIBLE:GONE);
        leftIv.setVisibility(isShowLeftIcon?VISIBLE:GONE);
        infoTv.setVisibility(isShowInfo?VISIBLE:GONE);
    }

    public void setLeftText(String leftText){
        nameTv.setText(leftText);
    }
}
