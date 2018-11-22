package com.example.interfacePackage.ThreadPack;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.text.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.io.IOException;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends Activity {

    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        tvResult = findViewById(R.id.tvResult);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new LogerInterceptor()).build();
        findViewById(R.id.tvGetRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Request request = new Request.Builder().get().get().url("https:www.baidu.com").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        final String result = e.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText(result);
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String result = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText(result);
                            }
                        });
                    }
                });


            }
        });
        findViewById(R.id.tvPostRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormBody formBody = new FormBody.Builder().add("username", "admin").add("password", "admin").build();

                final Request request = new Request.Builder().post(formBody).url("http://www.jianshu.com/").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        final String result = e.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText(result);
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String result = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText(result);
                            }
                        });
                    }
                });


            }
        });
        findViewById(R.id.tvFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(Environment.getDataDirectory(), "1.png");
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                final Request request = new Request.Builder().post(requestBody).url("http://www.jianshu.com/").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        final String result = e.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText(result);
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String result = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText(result);
                            }
                        });
                    }
                });


            }
        });

        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {

            }
        });


    }


}
