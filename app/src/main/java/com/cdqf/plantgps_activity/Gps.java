package com.cdqf.plantgps_activity;

public class Gps {
    //经度
    public String longitude;
    //纬度
    public String latitude;
    //地址
    public String address;
    //景名
    public String name;
    //图片文件
    public String imageFile;

    public Gps(String longitude, String latitude, String address, String name, String imageFile) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.name = name;
        this.imageFile = imageFile;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
