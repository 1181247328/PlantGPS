package com.cdqf.plantgps_image;

import android.graphics.Bitmap;

/**
 * Created by XinAiXiaoWen on 2017/3/28.
 */

public class ShelvesImageFind {
    public Bitmap bitmap;

    public String file;

    public ShelvesImageFind(String file) {
        this.file = file;
    }

    public ShelvesImageFind(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ShelvesImageFind(Bitmap bitmap, String file) {
        this.bitmap = bitmap;
        this.file = file;
    }
}
