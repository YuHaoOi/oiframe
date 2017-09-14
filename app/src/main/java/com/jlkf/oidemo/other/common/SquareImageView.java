package com.jlkf.oidemo.other.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/***
 * * @description   方形的ImageView,以宽度为准，自带选中样式
 * * 由 马君 创建于 2015年11月28日 15:35
 ***/
public class SquareImageView extends ImageView {

    private Paint paint;

    private int normalColor=0xffff0000;
    private boolean isDrawSelector=true;
    private boolean isSelected=false;


    private float size=0;
    private float selectorSize;
    private float selectorLeft;
    private float selectorTop;
    private float selectorRight;
    private float selectorBottom;
    private float selectorXcenter;
    private float selectorYcenter;
    private float selectorStrokeWidth;
    private Bitmap noChecked;
    private Bitmap checked;

    private int filter=0x88000000;

    public SquareImageView(Context context) {
        this(context, null);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(normalColor);
    }

    public void selected(boolean isSelected) {
        this.isSelected = isSelected;
        if (isSelected) {
            try {
                setColorFilter(filter);
            }catch (Exception e){
                e.getStackTrace();
            }
        } else {
            setColorFilter(null);
        }
        invalidate();
    }

    public void selectedReverse(){
        selected(!this.isSelected);
    }

    public boolean isSelected(){
        return this.isSelected;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        if(size==0){
            size=getMeasuredWidth();
            selectorTop=size/20;
            selectorLeft=size*19/20-size/6;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isDrawSelector){
            noChecked=getNoCheckedBitmap((int) (size / 6));
            checked=getCheckedBitmap((int)(size/6));
            if(isSelected){
                canvas.drawBitmap(checked, selectorLeft, selectorTop, paint);
            }else{
                canvas.drawBitmap(noChecked,selectorLeft,selectorTop,paint);
            }
            noChecked.recycle();
            checked.recycle();
        }
    }

    private Bitmap getNoCheckedBitmap(int size){
        Bitmap bitmap= Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_4444);
        Paint paint=new Paint();
        paint.setColor(0xFFDDDDDD);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(size/10);
        paint.setAntiAlias(true);
        Canvas canvas=new Canvas(bitmap);
        canvas.drawCircle(size/2,size/2,size*9/20,paint);
        return bitmap;
    }

    private Bitmap getCheckedBitmap(int size){
        Bitmap bitmap= Bitmap.createBitmap(noChecked);
        Paint paint=new Paint();
        paint.setColor(0xFFFF7C36);
        paint.setAntiAlias(true);
        Canvas canvas=new Canvas(bitmap);
        canvas.drawCircle(size/2,size/2,size*1/3,paint);
        return bitmap;
    }
}
