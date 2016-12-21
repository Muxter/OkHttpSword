package com.matao.okhttpsword.builder;

import com.matao.okhttpsword.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by matao on 2016-12-21 14:49
 */

public abstract class BaseRequestBuilder<T extends BaseRequestBuilder> {
    protected String url;
    protected Object tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;
    protected int id;

    public T url(String url) {
        this.url = url;
        return (T) this;
    }

    public T tag(Object tag) {
        this.tag = tag;
        return (T) this;
    }

    public T id(int id) {
        this.id = id;
        return (T) this;
    }

    public T headers(Map<String, String> headers) {
        this.headers = headers;
        return (T) this;
    }

    public T addHeader(String key, String value) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, value);
        return (T) this;
    }

    public T params(Map<String, String> params) {
        this.params = params;
        return (T) this;
    }

    public T addParams(String key, String value) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, value);
        return (T) this;
    }

    public abstract RequestCall build();
}
