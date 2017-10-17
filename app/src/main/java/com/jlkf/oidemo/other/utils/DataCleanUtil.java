package com.jlkf.oidemo.other.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by DuoNuo on 2017/9/22.
 */

public class DataCleanUtil {
    /**
     * 计算缓存大小
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) {
        long cacheSize = 0;
        try {
            cacheSize = getFolderSize(context.getCacheDir());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheSize += getFolderSize(context.getExternalCacheDir());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getFormatSize(cacheSize);
    }


    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                boolean isDelete = deleteDir(file);
                //一个文件删除失败返回false
                if (!isDelete) {
                    return false;
                }
            }
        }
        return dir != null && dir.delete();
    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File tempFile : fileList) {
                // 如果下面还有文件
                if (tempFile.isDirectory()) {
                    size = size + getFolderSize(tempFile);
                } else {
                    size = size + tempFile.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     */
    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0k";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            //toPlainString():此方法返回此BigDecimal的字符串表示形式不带指数字段
            return new BigDecimal(kiloByte).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            return new BigDecimal(megaByte).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraByte = gigaByte / 1024;
        if (teraByte < 1) {
            return new BigDecimal(gigaByte).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }

        return new BigDecimal(teraByte).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
