package com.matao.swordsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.matao.okhttpsword.OkHttpSword;
import com.matao.okhttpsword.callback.StringCallback;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private Button mBtGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtGet = (Button) findViewById(R.id.bt_get);
        mBtGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpSword.init()
                        .get()
                        .url("https://github.com/robots.txt")
                        .id(1)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.d("MainActivity", e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.d("MainActivity", response);
                            }
                        });
            }
        });
    }
}
