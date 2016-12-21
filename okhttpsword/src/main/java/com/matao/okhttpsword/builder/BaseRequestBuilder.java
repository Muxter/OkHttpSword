package com.matao.okhttpsword.builder;

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


}
