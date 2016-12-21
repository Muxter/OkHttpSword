package com.matao.okhttpsword.builder;

import com.matao.okhttpsword.utils.MediaTypeUtil;

import okhttp3.MediaType;

/**
 * Created by matao on 2016-12-21 18:03
 */

public class PostJsonBuilder extends PostStringBuilder {

    @Override
    public PostStringBuilder contentType(MediaType contentType) {
        return super.contentType(MediaTypeUtil.MEDIA_TYPE_JSON);
    }
}
