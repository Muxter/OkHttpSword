package com.matao.okhttpsword.request;

import com.matao.okhttpsword.utils.MediaTypeUtil;

import java.util.Map;

/**
 * Created by matao
 */

public class PostJsonRequest extends PostStringRequest {

    public PostJsonRequest(String url, Object tag, Map<String, String> headers, Map<String, String> params, int id, String json) {
        super(url, tag, headers, params, id, MediaTypeUtil.MEDIA_TYPE_JSON, json);
    }
}
