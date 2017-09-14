package com.jlkf.oidemo.other.common;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;


/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2016/6/1
 */
public class PopupArrowDrawable extends Drawable {

    private RectF rectF;
    private int arrowHeight=15;
    private float xPos=1.0f/2.0f;
    private int padding=80;
    private int arrowSize=20;
    private int bgColor=0xFFFFFFFF;

    public PopupArrowDrawable(){
    }

    public void setPosition(float xPos){
        this.xPos=xPos;
    }

    public void setPadding(int padding){
        this.padding=padding;
    }

    public void setArrow(int height,int halfWidth){
        this.arrowHeight=height;
        this.arrowSize=halfWidth;
    }

    public void setBackgroudColor(int bgColor){
        this.bgColor=bgColor;
    }

    @Override
    public void draw(Canvas canvas) {
        float pos=(rectF.right-rectF.left)*xPos+padding;
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(bgColor);
        Path path=new Path();
        path.moveTo(rectF.left,rectF.top+arrowHeight);
        path.lineTo(rectF.left,rectF.bottom);
        path.lineTo(rectF.right,rectF.bottom);
        path.lineTo(rectF.right,rectF.top+arrowHeight);
        path.lineTo(pos+arrowSize,rectF.top+arrowHeight);
        path.lineTo(pos,rectF.top);
        path.lineTo(pos-arrowSize,rectF.top+arrowHeight);
        path.close();
        canvas.drawPath(path,paint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF=new RectF(left,top,right,bottom);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
