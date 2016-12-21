package com.matao.okhttpsword;

import com.matao.okhttpsword.utils.Platform;

import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;

/**
 * Created by matao
 */

public class OkHttpSword {

    public static final String TAG = "OkHttpSword";

    private volatile static OkHttpSword mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;

    private OkHttpSword(OkHttpClient okHttpClient) {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }
        mPlatform = Platform.getPlatform();
    }

    /**
     * 用户可以手动配置OkHttpClient传入, 初始化OkHttpSword
     *
     * @param okHttpClient OkHttpClient实例
     * @return OkHttpSword实例
     */
    public static OkHttpSword init(OkHttpClient client) {
        if (mInstance == null) {
            synchronized (OkHttpSword.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpSword(client);
                }
            }
        }
        return mInstance;
    }

    /**
     * 用默认OkHttpClient的配置初始化OkHttpSword
     *
     * @return OkHttpSword实例
     */
    public static OkHttpSword init() {
        return init(null);
    }

    public Executor getExecutor() {
        return mPlatform.defaultExecutor();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public void execute() {

    }
}
