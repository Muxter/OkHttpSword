package com.matao.okhttpsword;

import com.matao.okhttpsword.callback.BaseCallback;
import com.matao.okhttpsword.request.RequestCall;
import com.matao.okhttpsword.request.builder.GetBuilder;
import com.matao.okhttpsword.request.builder.PostJsonBuilder;
import com.matao.okhttpsword.request.builder.PostStringBuilder;
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
     * 用默认OkHttpClient的配置初始化OkHttpSword
     *
     * @return OkHttpSword实例
     */
    public static OkHttpSword init() {
        return init(null);
    }

    /**
     * 用户可以手动配置OkHttpClient传入, 初始化OkHttpSword
     *
     * @param client OkHttpClient实例
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

    public Executor getExecutor() {
        return mPlatform.defaultExecutor();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostJsonBuilder postJson() {
        return new PostJsonBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public void execute(RequestCall requestCall, BaseCallback callback) {
        if (callback == null) {
            callback = BaseCallback.CALLBACK_DEFAULT;
        }
        final int id = requestCall.getRequest().getId();

        final BaseCallback finalCallback = callback;    // Java语法限制
        requestCall.getCall().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                failureCallback(call, e, finalCallback, id);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (call.isCanceled()) {
                    failureCallback(call, new IOException("Call has been canceled!"), finalCallback, id);
                    return;
                }

                if (!finalCallback.validateReponse(response, id)) {
                    failureCallback(call, new IOException("Response is invalidate. Error code: " + response.code()), finalCallback, id);
                    return;
                }

                try {
                    Object parsedResponse = finalCallback.parseNetworkResponse(response, id);
                    successCallback(call, parsedResponse, finalCallback, id);
                } catch (Exception e) {
                    failureCallback(call, e, finalCallback, id);
                } finally {
                    if (response.body() != null) {
                        response.close();
                    }
                }
            }
        });
    }

    private void failureCallback(final Call call, final Exception e, final BaseCallback callback, final int id) {
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }

    private void successCallback(final Call call, final Object response, final BaseCallback callback, final int id) {
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(call, response, id);
                callback.onAfter(id);
            }
        });
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }
}
