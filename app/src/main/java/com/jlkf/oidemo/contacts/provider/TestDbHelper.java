package com.jlkf.oidemo.contacts.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DuoNuo on 2017/10/16.
 */

public class TestDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "test.db";

    public TestDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CONTACT_TABLE = "CREATE TABLE " + TestContract.TestEntry.TABLE_NAME + "( "
                + TestContract.TestEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TestContract.TestEntry.COLUMN_NAME + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TestContract.TestEntry.TABLE_NAME);
        onCreate(db);
    }


}
