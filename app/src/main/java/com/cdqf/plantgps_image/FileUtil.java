package com.cdqf.plantgps_image;

import android.os.Environment;

import java.io.File;

/**
 * Created by gaolf on 15/12/24.
 */
public abstract class FileUtil {

    public static final String IMG_CACHE1 = Environment.getExternalStorageDirectory() + File.separator + "photoonethree.png";
    public static final String IMG_CACHE2 = Environment.getExternalStorageDirectory() + File.separator + "photoonetwo.png";

    //宝贝上架图片路径
    public static final String IMG_CACHE3 = Environment.getExternalStorageDirectory() + File.separator + "shelves.png";

    //头像的路径
    public static final String IMG_CACHE4 = Environment.getExternalStorageDirectory() + File.separator + "head.png";

    //QQ头像的路径
    public static final String IMG_CACHE5 = Environment.getExternalStorageDirectory() + File.separator + "QQhead.png";

    public static final String APK = Environment.getExternalStorageDirectory() + File.separator;

    public static final String PUBLIC_CACHE = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".png";

}
