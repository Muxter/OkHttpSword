package com.matao.okhttpsword.utils;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by matao
 * <p>
 * 参考OkHttp的Platform类
 */

public class Platform {
    private static final Platform PLATFORM = findPlatform();

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new AndroidPlatform();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Platform();
    }

    public static Platform getPlatform() {
        LogUtil.e(PLATFORM.getClass().toString());
        return PLATFORM;
    }

    public Executor defaultExecutor() {
        return Executors.newCachedThreadPool();
    }

    public void execute(Runnable runnable) {
        defaultExecutor().execute(runnable);
    }

    /**
     * 若是Android平台，则线程调度至主线程
     */
    static class AndroidPlatform extends Platform {
        @Override
        public Executor defaultExecutor() {
            return new MainThreadExecutor();
        }

        static class MainThreadExecutor implements Executor {

            private final Handler mHandler = new Handler(Looper.getMainLooper());

            @Override
            public void execute(Runnable runnable) {
                mHandler.post(runnable);
            }
        }
    }
}
