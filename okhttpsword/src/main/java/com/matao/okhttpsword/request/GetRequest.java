package com.matao.okhttpsword.request;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by matao
 */

public class GetRequest extends BaseRequest {
    public GetRequest(String url, Object tag, Map<String, String> headers, Map<String, String> params, int id) {
        super(url, tag, headers, params, id);
    }

    /**
     * Get请求不需要RequestBody
     *
     * @return
     */
    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    /**
     * 返回Get请求的Request对象
     *
     * @param requestBody
     * @return
     */
    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return builder.get().build();
    }
}
