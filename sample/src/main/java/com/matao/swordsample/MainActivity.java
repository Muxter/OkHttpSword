package com.matao.swordsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.matao.okhttpsword.OkHttpSword;
import com.matao.okhttpsword.callback.StringCallback;
import com.matao.okhttpsword.interceptor.LoggerInterceptor;
import com.matao.okhttpsword.utils.MediaTypeUtil;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private Button mBtGet;
    private Button mBtPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtGet = (Button) findViewById(R.id.bt_get);
        mBtGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggerInterceptor("OkHttpSword")).build();
                OkHttpSword.init(client)
                        .get()
                        .url("https://github.com/robots.txt")
                        .id(1)
                        .build()
                        .newRequestCall()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("MainActivity", e.toString());
                            }

                            @Override
                            public void onResponse(Call call, String response, int id) {
                                Log.e("MainActivity", response);
                            }
                        });
            }
        });

        mBtPost = (Button) findViewById(R.id.bt_post);
        mBtPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpSword.getInstance()
                        .postString()
                        .url("https://api.github.com/markdown/raw")
                        .contentType(MediaTypeUtil.MEDIA_TYPE_MARKDOWN)
                        .content("xxxx")
                        .build()
                        .newRequestCall()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("MainActivity", e.toString());
                            }

                            @Override
                            public void onResponse(Call call, String response, int id) {
                                Log.e("MainActivity", response);
                            }
                        });
            }
        });


    }
}
