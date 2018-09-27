package com.cdqf.plantgps_state;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by liu on 2017/7/19.
 */

public class Preferences {

    private String TAG = Preferences.class.getSimpleName();


    private static final String GPS = "GPS";
    private static Gson gson = new Gson();

    private static SharedPreferences seabedPreferences = null;

    private static SharedPreferences.Editor seabedEditor = null;

    /**
     * 保存基本信息
     *
     * @param context
     * @param gps
     */

    public static void setGps(Context context,String gps){
        seabedPreferences = context.getSharedPreferences(GPS, 0);
        seabedEditor = seabedPreferences.edit();
        seabedEditor.putString("gps",gps);
        seabedEditor.commit();
    }

    public static String getGPS(Context context){
        seabedPreferences = context.getSharedPreferences(GPS, 0);
        String gps = seabedPreferences.getString("gps","");
        if(gps.equals("")){
            return null;
        }
        return gps ;
    }

    /**
     * 删除用户GPS
     * @param context
     */
    public static void clearId(Context context){
        seabedPreferences = context.getSharedPreferences(GPS, 0);
        seabedEditor = seabedPreferences.edit().clear();
        seabedEditor.commit();
    }
}
