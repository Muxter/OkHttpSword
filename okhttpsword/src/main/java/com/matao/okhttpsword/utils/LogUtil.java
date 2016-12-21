package com.matao.okhttpsword.utils;

import android.util.Log;

import com.matao.okhttpsword.BuildConfig;

/**
 * Created by matao on 2016-12-21 14:19
 */

public class LogUtil {
    public static final String TAG = "OkHttpSword";

    private LogUtil() {
        throw new RuntimeException("LogUtil cannot be instantiated");
    }

    public static void e(Object o) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, o.toString());
        }
    }
}
