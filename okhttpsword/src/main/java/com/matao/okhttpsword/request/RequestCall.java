package com.matao.okhttpsword.request;

import com.matao.okhttpsword.OkHttpSword;
import com.matao.okhttpsword.callback.BaseCallback;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by matao on 2016-12-21 16:29
 */

public class RequestCall {
    private BaseRequest request;
    private Request okHttpRequest;
    private Call call;

    private long readTimeout;
    private long writeTimeout;
    private long connectTimeout;

    private OkHttpClient newClient;

    public RequestCall readTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public RequestCall writeTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public RequestCall connectionTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public RequestCall(BaseRequest request) {
        this.request = request;
    }

    public BaseRequest getRequest() {
        return request;
    }

    public Request getOkHttpRequest() {
        return okHttpRequest;
    }

    public Call getCall() {
        return call;
    }

    public Call buildCall() {
        okHttpRequest = request.generateRequest();

        // 用户重新设置了timeout， 则使用新的OkHttpClient对象
        if (readTimeout > 0 || writeTimeout > 0 || connectTimeout > 0) {
            OkHttpClient.Builder newBuilder = OkHttpSword.init().getOkHttpClient().newBuilder();

            if (readTimeout > 0) {
                newBuilder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
            }
            if (writeTimeout > 0) {
                newBuilder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
            }
            if (connectTimeout > 0) {
                newBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
            }

            newClient = newBuilder.build();
            call = newClient.newCall(okHttpRequest);
        } else {
            call = OkHttpSword.init().getOkHttpClient().newCall(okHttpRequest);
        }
        return call;
    }

    public void execute(BaseCallback callback) {
        buildCall();
        
        if (callback != null) {
            callback.onBefore(okHttpRequest, request.id);
        }

        OkHttpSword.init().execute(this, callback);
    }
}
