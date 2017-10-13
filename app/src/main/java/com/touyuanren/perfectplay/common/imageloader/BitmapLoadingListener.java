package com.touyuanren.perfectplay.common.imageloader;

import android.graphics.Bitmap;



public interface BitmapLoadingListener {

    void onSuccess(Bitmap b);

    void onError();
}
