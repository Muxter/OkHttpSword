package com.matao.okhttpsword.callback;

import okhttp3.Response;

/**
 * Created by matao on 2016-12-21 19:06
 */

public abstract class StringCallback extends BaseCallback<String>{

    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        return response.body().string();
    }
}
