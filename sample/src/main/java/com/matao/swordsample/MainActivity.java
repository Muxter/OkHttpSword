package com.matao.swordsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.matao.okhttpsword.OkHttpSword;
import com.matao.okhttpsword.callback.StringCallback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button mBtOkHttpGet;
    private Button mBtSwordGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtSwordGet = (Button) findViewById(R.id.bt_sword_get);
        mBtSwordGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .build();
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

        mBtOkHttpGet = (Button) findViewById(R.id.bt_ok_http_get);
        mBtOkHttpGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .addNetworkInterceptor(new LoggerIntercept("aaa"))
                        .build();
                Request request = new Request.Builder()
                        .url("https://github.com/robots.txt")
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("MainActivity", e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("MainActivity", response.body().string());
                    }
                });
            }
        });


    }
}
