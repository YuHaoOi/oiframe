package com.jlkf.oidemo.contacts.provider;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by DuoNuo on 2017/10/16.
 */

public class TestContract {
    //授权信息，用以区别不同的ContentProvider
    protected static final String CONTENT_AUTHORITY = "me.pengtao.contentprovidertest";
    protected static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    protected static final String PATH_TEST = "test";

    public static final class TestEntry implements BaseColumns {


        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TEST).build();

        protected static Uri buildUri(long id) {
            //这个方法负责把id和contentUri连接成一个新的Uri
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        //表名
        public static final String TABLE_NAME = "test";
        //列名
        public static final String COLUMN_NAME = "name";
    }

}