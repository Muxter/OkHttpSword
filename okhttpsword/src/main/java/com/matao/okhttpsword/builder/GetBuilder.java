package com.matao.okhttpsword.builder;

import android.net.Uri;

import com.matao.okhttpsword.request.GetRequest;
import com.matao.okhttpsword.request.RequestCall;

import java.util.Map;

/**
 * Created by matao on 2016-12-21 14:48
 */

public class GetBuilder extends BaseRequestBuilder<GetBuilder>{

    @Override
    public RequestCall build() {
        if (params != null && !params.isEmpty()) {
            Uri.Builder uriBuilder = new Uri.Builder();
            uriBuilder.authority(url);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            url = uriBuilder.build().toString();
        }
        return new GetRequest(url, tag, headers, params, id).build();
    }
}
