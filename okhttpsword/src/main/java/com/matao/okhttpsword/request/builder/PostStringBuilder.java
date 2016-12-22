package com.matao.okhttpsword.request.builder;

import com.matao.okhttpsword.request.PostStringRequest;
import com.matao.okhttpsword.request.RequestCall;

import okhttp3.MediaType;

/**
 * Created by matao on 2016-12-21 18:00
 */

public class PostStringBuilder extends BaseRequestBuilder<PostStringBuilder> {

    protected MediaType contentType;
    protected String content;

    public PostStringBuilder contentType(MediaType contentType) {
        this.contentType = contentType;
        return this;
    }

    public PostStringBuilder content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(url, tag, headers, params, id, contentType, content).build();
    }
}
