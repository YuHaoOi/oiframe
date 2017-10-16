package com.jlkf.oidemo.contacts.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.jlkf.oidemo.contacts.provider.TestContract.TestEntry.TABLE_NAME;

/**
 * Created by DuoNuo on 2017/10/16.
 */

public class TestProvider extends ContentProvider {

    private TestDbHelper mOpenHelper;
    private static final int TEST = 100;

    static UriMatcher buildUriMatcher(){
        //创建一个Uri匹配器
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //向匹配器中添加Uri,参数三称之为：匹配码
        uriMatcher.addURI(TestContract.CONTENT_AUTHORITY, TestContract.PATH_TEST, TEST);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new TestDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (buildUriMatcher().match(uri)){
            case TEST:
                cursor = db.query(TABLE_NAME,strings,s,strings1,s1,null,null);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;
        long _id;
        switch (buildUriMatcher().match(uri)){
            case TEST:
                _id = db.insert(TABLE_NAME,null,contentValues);
                if (_id > 0){
                    returnUri = TestContract.TestEntry.buildUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new android.database.SQLException("Unknown uri: " + uri);
        }
        db.close();
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        int rows = db.delete(TABLE_NAME, s, strings);
        db.close();
        return rows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows = db.update(TABLE_NAME, contentValues, s, strings);
        db.close();
        return rows;
    }
}
