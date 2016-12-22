package com.matao.okhttpsword.request.builder;

import android.net.Uri;

import com.matao.okhttpsword.request.GetRequest;

import java.util.Map;

/**
 * Created by matao on 2016-12-21 14:48
 */

public class GetBuilder extends BaseRequestBuilder<GetBuilder, GetRequest>{

    @Override
    public GetRequest build() {
        if (params != null && !params.isEmpty()) {
            Uri.Builder uriBuilder = new Uri.Builder();
            uriBuilder.authority(url);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            url = uriBuilder.build().toString();
        }
        return new GetRequest(url, tag, headers, params, id);
    }

//    @Override
//    public RequestCall build() {
//        if (params != null && !params.isEmpty()) {
//            Uri.Builder uriBuilder = new Uri.Builder();
//            uriBuilder.authority(url);
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
//            }
//            url = uriBuilder.build().toString();
//        }
//        return new GetRequest(url, tag, headers, params, id).buildRequestCall();
//    }
}
