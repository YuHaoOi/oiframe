package com.jlkf.oidemo.contacts.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.base.BaseActivity;

import butterknife.BindView;

public class ProviderActivity extends BaseActivity {

    private ContentResolver contentResolver;
    @BindView(R.id.info_tv)
    TextView infoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        //获取ContentResolver对象，负责向ContentProvider来请求数据
        contentResolver = getContentResolver();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    //增
    public void insert(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TestContract.TestEntry.COLUMN_NAME, "yu");
        contentResolver.insert(TestContract.TestEntry.CONTENT_URI, contentValues);
        query(null);
    }

    public void query(View view) {
        Cursor cursor = contentResolver.query(TestContract.TestEntry.CONTENT_URI, null, null, null, null);
        StringBuilder sb = new StringBuilder();
        //数据总数
        int count = cursor.getCount();
        sb.append("数据总数：" + count + "\n");
        while (cursor.moveToNext()){
            //列数
            int index = cursor.getColumnIndex(TestContract.TestEntry.COLUMN_NAME);
            sb.append("数据").append("(").append(cursor.getPosition()).append(")").append(cursor.getString(index)).append("\n");
        }
        cursor.close();
        infoTv.setText(sb.toString());
    }

    public void delete(View view) {
        contentResolver.delete(TestContract.TestEntry.CONTENT_URI, "name like ?", new String[]{"%" + "yu" + "%"});
        query(null);
    }

    public void update(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TestContract.TestEntry.COLUMN_NAME, "hao");
        contentResolver.update(TestContract.TestEntry.CONTENT_URI, contentValues, "name like ?", new String[]{"%" + "yu" + "%"});
        query(null);
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, ProviderActivity.class);
        context.startActivity(intent);
    }
}
