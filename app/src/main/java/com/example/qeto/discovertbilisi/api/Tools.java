package com.example.qeto.discovertbilisi.api;

import android.os.Build;

/**
 * Created by QETO on 12/2/2016.
 */

public class Tools {
    public static int getAndroidVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean atLeastMarshmallow() {
        return getAndroidVersion() >= Build.VERSION_CODES.M;
    }

    public static boolean atLeastKitKat() {
        return getAndroidVersion() >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean atLeastJellyBeanMR1() {
        return getAndroidVersion() >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }
}
