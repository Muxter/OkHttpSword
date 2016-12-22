package com.matao.okhttpsword.utils;

import okhttp3.MediaType;

/**
 * Created by matao on 2016-12-21 16:14
 */

public class MediaTypeUtil {

    public static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain;charset=utf-8");
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private MediaTypeUtil() {
        throw new RuntimeException("MediaTypeUtil cannot be instantiated");
    }
}
