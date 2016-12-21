package com.matao.okhttpsword;

import com.matao.okhttpsword.builder.GetBuilder;
import com.matao.okhttpsword.callback.BaseCallback;
import com.matao.okhttpsword.request.RequestCall;
import com.matao.okhttpsword.utils.Platform;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by matao
 */

public class OkHttpSword {

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

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public void execute(RequestCall requestCall, BaseCallback callback) {
        if (callback == null) {
            callback = BaseCallback.CALLBACK_DEFAULT;
        }
        final BaseCallback finalCallback = callback;
        final int id = requestCall.getRequest().getId();

        requestCall.getCall().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                finalCallback.onError(call, e, id);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                finalCallback.onResponse(response, id);
            }
        });
    }
}
