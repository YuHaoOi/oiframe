package com.jlkf.oidemo.contacts.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.Button;
import android.widget.ImageView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jlkf.oidemo.AppSet;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.base.BaseActivity;
import com.jlkf.oidemo.other.utils.FileMaker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class TakePhoneActivity extends BaseActivity {

    private static final int REQUEST_CODE_TAKE_PHOTO = 0x110;
    @BindView(R.id.phone_btn)
    Button phoneBtn;
    @BindView(R.id.phone_iv)
    ImageView phoneIv;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_phone);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        //拍照点击事件
        RxView.clicks(phoneBtn).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                //无压缩拍照
                takePhotoNoCompress();
            }
        });
    }

    /**
     * 演示7.0权限适配问题
     */
    private void takePhotoNoCompress() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //通过Intent的resolveActivity方法，并想该方法传入包管理器可以对包管理器进行查询以确定是否有Activity能够启动该Intent
        if (intent.resolveActivity(getPackageManager()) != null){
            String fileName = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CHINA).format(new Date());
            File file = new File(FileMaker.getInstance().getPath(AppSet.FOLDER_IMAGE) + fileName + ".jpg");
            Uri fileUri = null;
            if (Build.VERSION.SDK_INT >= 24) {
                //7.0的系统需要通过FileProvider（ContentProvider的子类）方式传递
                fileUri = FileProvider.getUriForFile(this, "com.jlkf.oidemo", file);
            } else {
                //7.0以前是通过这种方式传递参数，如果7.0以下手机采用以上方式，需要为FileProvider授权
                fileUri = Uri.fromFile(file);
            }
            //生成的uri：content://com.jlkf.oidemo/external/2017-06-01-04:14:11.jpg
            //可以看到格式为：content://authorities/定义的xml里面name属性/文件的相对路径，即name隐藏了可存储的文件夹路径。
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
            mCurrentPhotoPath = file.getAbsolutePath();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    phoneIv.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
                }
                break;
        }
    }

    @Override
    protected void initDatas() {

    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, TakePhoneActivity.class);
        context.startActivity(intent);
    }
}
