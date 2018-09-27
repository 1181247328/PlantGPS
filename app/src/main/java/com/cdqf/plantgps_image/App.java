package com.cdqf.plantgps_image;

import android.content.Context;
import android.support.multidex.MultiDex;


import com.mob.MobApplication;

/**
 * Created by gaolf on 15/12/24.
 */
public class App extends MobApplication {
    private static App sInstance = new App();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App getInstance() {
        return sInstance;
    }
}
