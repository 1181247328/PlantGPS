package com.cdqf.plantgps_txmap;

/**
 * Created by liu on 2018/7/4.
 */

public class LoadFind {
    public String load;

    public int i;

    //经度
    public double longitude;

    //纬度
    public double latitude;

    public LoadFind(String load, int i) {
        this.load = load;
        this.i = i;
    }

    public LoadFind(String load, int i, double longitude, double latitude) {
        this.load = load;
        this.i = i;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
