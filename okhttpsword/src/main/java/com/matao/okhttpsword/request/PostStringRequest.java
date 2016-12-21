package com.matao.okhttpsword.request;

import com.matao.okhttpsword.utils.MediaTypeUtil;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by matao
 */

public class PostStringRequest extends BaseRequest {

    private MediaType contentType;
    private String content;

    public PostStringRequest(String url, Object tag, Map<String, String> headers, Map<String, String> params, int id, MediaType contentType, String content) {
        super(url, tag, headers, params, id);
        this.content = content;
        this.contentType = contentType;

        if (this.content == null) {
            throw new IllegalArgumentException("content cannot be null");
        }

        if (this.contentType == null) {
            this.contentType = MediaTypeUtil.MEDIA_TYPE_TEXT;
        }
    }

    @Override
    protected RequestBody buildRequestBody() {
        return RequestBody.create(contentType, content);
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return builder.post(requestBody).build();
    }
}
