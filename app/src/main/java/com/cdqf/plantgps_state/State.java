package com.cdqf.plantgps_state;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;

import com.cdqf.plantgps_activity.Gps;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class State {
    private String TAG = State.class.getSimpleName();

    private static State state = new State();

    private List<Gps> gpsList = new ArrayList<Gps>();

    private ImageLoader imageLoader = ImageLoader.getInstance();

    //头像
    private Bitmap headBitmap = null;

    public static State getState() {
        return state;
    }

    /**
     * 将Bitmap保存在本地
     *
     * @param bitmap
     */
    public void saveBitmapFile(Bitmap bitmap, String uri) {
        File file = new File(uri);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一张图片
     *
     * @param context
     */
    public void headFail(Context context, String path) {
        setHeadBitmap(null);
        if (TextUtils.isEmpty(path)) {
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File file = new File(path);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
            file.delete();
        }
    }

    public DisplayImageOptions getImageLoaderOptions(int loading, int empty, int fail) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(loading)
                .showImageForEmptyUri(empty)
                .showImageOnFail(fail)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(5))
                .build();
        return options;
    }

    /**
     * 为头像而准备
     *
     * @param loading
     * @param empty
     * @param fail
     * @return
     */
    public DisplayImageOptions getHeadImageLoaderOptions(int loading, int empty, int fail) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(loading)
                .showImageForEmptyUri(empty)
                .showImageOnFail(fail)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        return options;
    }

    /**
     * 保存图片的配置
     *
     * @param context
     * @param cache   "imageLoaderworld/Cache"
     */
    public ImageLoaderConfiguration getImageLoaderConfing(Context context, String cache) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, cache);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800)
                .threadPoolSize(10)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100)
                .discCache(new UnlimitedDiskCache(cacheDir))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))
                .writeDebugLogs()
                .build();
        return config;
    }

    /**
     * 初始化imageLoad
     *
     * @param context
     * @return
     */
    public ImageLoaderConfiguration getConfiguration(Context context) {
        ImageLoaderConfiguration configuration = getImageLoaderConfing(context, "imageLoaderword/Chace");
        return configuration;
    }

    public ImageLoader getImageLoader(Context context) {
        imageLoader.init(getConfiguration(context));
        return imageLoader;
    }

    public Bitmap getHeadBitmap() {
        return headBitmap;
    }

    public void setHeadBitmap(Bitmap headBitmap) {
        this.headBitmap = headBitmap;
    }

    public List<Gps> getGpsList() {
        return gpsList;
    }

    public void setGpsList(List<Gps> gpsList) {
        this.gpsList = gpsList;
    }
}
