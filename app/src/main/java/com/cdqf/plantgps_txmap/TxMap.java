package com.cdqf.plantgps_txmap;

/**
 * Created by liu on 2018/7/18.
 */

public class TxMap {
    private static TxMap txMap = new TxMap();

    public static TxMap getTxMap() {
        return txMap;
    }

    //详情地址
    private String address;

    //经度
    private double longitude;

    //纬度
    private double latitude;

    public static void setTxMap(TxMap txMap) {
        TxMap.txMap = txMap;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
