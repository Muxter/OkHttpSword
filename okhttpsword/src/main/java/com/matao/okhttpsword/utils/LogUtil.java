package com.matao.okhttpsword.utils;

import android.util.Log;

import com.matao.okhttpsword.BuildConfig;
import com.matao.okhttpsword.OkHttpSword;

/**
 * Created by matao on 2016-12-21 14:19
 */

public class LogUtil {
    private LogUtil() {
        throw new RuntimeException("LogUtil cannot be instantiated");
    }

    public static void e(Object o) {
        if (BuildConfig.DEBUG) {
            Log.e(OkHttpSword.TAG, o.toString());
        }
    }
}
