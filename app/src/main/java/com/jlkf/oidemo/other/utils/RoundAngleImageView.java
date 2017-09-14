package com.jlkf.oidemo.other.utils;

/**
 * Created by DuoNuo on 2016/6/16.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jlkf.oidemo.R;


public class RoundAngleImageView extends ImageView {

    private Paint paint;
    //private int roundWidth = 10;
    //private int roundHeight = 10;
    private int roundWidth = DensityUtils.dp2px(getContext(), 5);
    private int roundHeight = DensityUtils.dp2px(getContext(), 5);
    private int strokeWidth = 0;
//    private int borderColor = 0xFFFFFFFF;
    private Paint paint2;
    private float halfBorder = 0f;

    public RoundAngleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundAngleImageView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundAngleImageView);
            roundWidth = a.getDimensionPixelSize(R.styleable.RoundAngleImageView_roundWidth, roundWidth);
            roundHeight = a.getDimensionPixelSize(R.styleable.RoundAngleImageView_roundHeight, roundHeight);
            strokeWidth = a.getDimensionPixelSize(R.styleable.RoundAngleImageView_border, strokeWidth);
//            borderColor = a.getColor(R.styleable.RoundAngleImageView_borderColor, borderColor);
        } else {
            float density = context.getResources().getDisplayMetrics().density;
            roundWidth = (int) (roundWidth * density);
            roundHeight = (int) (roundHeight * density);
        }


        paint2 = new Paint();
        paint2.setXfermode(null);
    }

    private void paintRadius() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);
        super.draw(canvas2);
        paintRadius();
        drawLiftUp(canvas2);
        drawRightUp(canvas2);
        drawLiftDown(canvas2);
        drawRightDown(canvas2);
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        bitmap.recycle();
        drawBorder(canvas);
    }

    private void drawLiftUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, roundHeight);
        path.lineTo(0, 0);
        path.lineTo(roundWidth, 0);
        path.arcTo(new RectF(
                        0,
                        0,
                        roundWidth * 2,
                        roundHeight * 2),
                -90,
                -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawLiftDown(Canvas canvas) {
        Path path = new Path();
//        path.moveTo(0, getHeight()-roundHeight);
//        path.lineTo(0, getHeight());
//        path.lineTo(roundWidth, getHeight());
        path.moveTo(roundWidth, getHeight());
        path.lineTo(0, getHeight());
        path.lineTo(0, getHeight() - roundHeight);
        path.arcTo(new RectF(
                        0,
                        getHeight() - roundHeight * 2,
                        roundWidth * 2,
                        getHeight()),
                180,
                -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth() - roundWidth, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() - roundHeight);
        path.arcTo(new RectF(
                getWidth() - roundWidth * 2,
                getHeight() - roundHeight * 2,
                getWidth(),
                getHeight()), 0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth(), roundHeight);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - roundWidth, 0);
        path.arcTo(new RectF(
                        getWidth() - roundWidth * 2,
                        0,
                        getWidth(),
                        0 + roundHeight * 2),
                -90,
                90);
        path.close();
        canvas.drawPath(path, paint);
    }
//        @Override
//        protected void onDraw(Canvas canvas) {
//            Path clipPath = new Path();
//            int w = this.getWidth();
//            int h = this.getHeight();
//            clipPath.addRoundRect(new RectF(0, 0, w, h), 10.0f, 10.0f, Path.Direction.CW);
//            canvas.clipPath(clipPath);
//            super.onDraw(canvas);
//        }

    private void drawBorder(Canvas canvas) {
        if (strokeWidth > 0) {
            halfBorder = strokeWidth / 2.0f;
            paint.setStrokeWidth(strokeWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
//            paint.setColor(borderColor);
            paint.setXfermode(null);
            Path path = new Path();
            path.moveTo(roundWidth, 0);
            path.arcTo(new RectF(
                            halfBorder,
                            halfBorder,
                            roundWidth * 2 - halfBorder,
                            roundHeight * 2 - halfBorder),
                    -90,
                    -90);

            if (roundHeight * 2 != getHeight()) {
                path.lineTo(0, getHeight() - roundHeight);
            }
            path.arcTo(new RectF(
                            halfBorder,
                            getHeight() - roundHeight * 2 + halfBorder,
                            roundWidth * 2 - halfBorder,
                            getHeight() - halfBorder),
                    180,
                    -90);
            if (roundWidth * 2 != getWidth()) {
                path.lineTo(getWidth() - roundWidth, getHeight());
            }
            path.arcTo(new RectF(
                            getWidth() - 2 * roundWidth + halfBorder,
                            getHeight() - roundHeight * 2 + halfBorder,
                            getWidth() - halfBorder,
                            getHeight() - halfBorder),
                    90,
                    -90);

            if (roundHeight * 2 != getHeight()) {
                path.lineTo(getWidth(), roundHeight);
            }
            path.lineTo(getWidth(), roundHeight);
            path.arcTo(new RectF(
                            getWidth() - 2 * roundWidth + halfBorder,
                            halfBorder,
                            getWidth() - halfBorder,
                            roundHeight * 2 - halfBorder),
                    0,
                    -90);
            path.close();
            canvas.drawPath(path, paint);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
