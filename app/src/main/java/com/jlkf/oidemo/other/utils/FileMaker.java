package com.jlkf.oidemo.other.utils;

import android.os.Environment;
import android.util.SparseArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import timber.log.Timber;

/**
 * 文件操作工具
 *
 * 1 初始化 参考MainApplication
   2 使用
        File file = new File(FileMaker.getInstance().getPath(AppSet.FOLDER_IMAGE) + "start.jpg"); //图片文件夹下
        FileMaker.getInstance().setCache(AppSet.CACHE_CITY,result); //可以保存一些json解析的result
 */
public class FileMaker {

    private static FileMaker instance;
    private String sdPath;
    private String mainPath = "/App/";
    private String autoCache = mainPath + "/autoCache/";

    //缓存文件夹已存在
    public static final int TYPE_EXIST = 2;
    //创建缓存文件夹失败
    public static final int TYPE_FAILED = 3;
    //创建缓存文件夹成功
    public static final int TYPE_SUCCESS = 1;

    private SparseArray<String> paths;

    private FileMaker() {
        //sd卡目录
        sdPath = getSdPath();
        //外部存储主目录
        mainPath = sdPath + mainPath;
        paths = new SparseArray<>();
    }

    public static FileMaker getInstance() {
        if (instance == null) {
            synchronized (FileMaker.class) {
                if (instance == null) {
                    instance = new FileMaker();
                }
            }
        }
        return instance;
    }

    /**
     *  设置主目录,一般设置为app的名字
     */
    public void setMainPath(String mainName) {
        this.mainPath = sdPath + "/" + mainName;
        this.autoCache = "cache";
        createFolder(10000, autoCache);
    }

    /**
     * 获取缓存的路径
     * @return 缓存路径
     */
    public String getCachePath() {
        return this.mainPath + "/" + autoCache + "/";
    }

    /**
     * 创建缓存文件夹
     * @param key 保存缓存文件夹的key
     * @param name 文件夹的名字
     * @return 创建成功或者失败
     */
    public int createFolder(int key, String name) {
        name = name.replaceAll("/", "");
        File file = new File(mainPath + "/" + name + "/");
        paths.append(key, file.getAbsolutePath());
        if (!file.exists()) {
            if (file.mkdirs()) {
                return TYPE_SUCCESS;
            } else {
                return TYPE_FAILED;
            }
        } else {
            return TYPE_EXIST;
        }
    }

    /**
     * 获取创建文件夹的路径
     * @param key 保存文件夹的key
     * @return 文件夹路径, 没有返回null
     */
    public String getPath(int key) {
        if (paths.indexOfKey(key) != -1) {
            return paths.get(key) + "/";
        }
        return null;
    }

    /**
     * 保存缓存内容
     * @param key 保存的文件的名字
     * @param value 需要保存的内容
     * @return 成功或者失败
     */
    public boolean setCache(String key, String value) {
        File file = new File(getCachePath() + key + ".auto");
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return false;
                }
            }
            FileOutputStream fs = new FileOutputStream(file);
            fs.write(value.getBytes());
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 获取缓存的内容
     * @param key 缓存文件的名字
     * @return 缓存内容
     */
    public String getCache(String key) {
        String res = null;
        File file = new File(getCachePath() + key + ".auto");
        try {
            Timber.e("File->" + getCachePath() + key + ".auto");
            FileInputStream fin = new FileInputStream(file);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            res = new String(buffer);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取sd卡目录
     */
    public String getSdPath() {
        //判断SD卡是否挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //路径:/storage/emulated/0
            return Environment.getExternalStorageDirectory().getPath();
        }
        //路径:/data
        return Environment.getDataDirectory().getPath();
    }
}
