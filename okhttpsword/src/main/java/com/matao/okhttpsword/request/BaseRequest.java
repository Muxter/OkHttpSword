package com.matao.okhttpsword.request;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by matao on 2016-12-21 14:54
 */

public abstract class BaseRequest {
    protected String url;
    protected Object tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;
    protected int id;

    protected Request.Builder builder = new Request.Builder();

    public BaseRequest(String url, Object tag, Map<String, String> headers, Map<String, String> params, int id) {
        this.url = url;
        this.tag = tag;
        this.headers = headers;
        this.params = params;
        this.id = id;

        if (url == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        initBuilder();
    }

    public int getId() {
        return id;
    }

    /**
     * 初始化url, tag, headers
     */
    private void initBuilder() {
        builder.url(url)
                .tag(tag)
                .headers(buildHeader());
    }

    private Headers buildHeader() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headerBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        return headerBuilder.build();
    }

    protected abstract RequestBody buildRequestBody();

    protected abstract Request buildRequest(RequestBody requestBody);

    public Request generateRequest() {
        RequestBody requestBody = buildRequestBody();
        Request request = buildRequest(requestBody);
        return request;
    }

    public RequestCall newRequestCall() {
        return new RequestCall(this);
    }

}
