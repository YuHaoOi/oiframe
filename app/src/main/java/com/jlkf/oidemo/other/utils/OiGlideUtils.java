package com.jlkf.oidemo.other.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by DuoNuo on 2017/5/22
 */

public class OiGlideUtils {
    //默认加载
    public static void loadImageView(Context mContext, int path, ImageView mImageView) {
            Glide.with(mContext).load(path).into(mImageView);
    }
}
