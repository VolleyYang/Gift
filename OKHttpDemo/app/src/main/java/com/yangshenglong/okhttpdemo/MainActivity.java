package com.yangshenglong.okhttpdemo;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button  btnGet,btnPost;
    private String  getUrl = "http://api-v2.mall.hichao.com/forum/banner?ga=%2Fforum%2Fbanner";
    private String  postUrl = "http://h5api.myoho.net/index.php?r=Apiemag/magList&startTime=0&lastTime=1479784306&magCount=3&magType=5&width=1080&height=1776&ppi=480&num=3";
    private OkHttpClient okHttp;
    private String key = "parameters";
    private String value = "{“platform”:\"4\",\"locale\":\"zh-Hans\",\"app\":\"efashion\",\"language\":\"zh-Hans\",\"udid\":\"00000000000000063aa461b71c4cfcf\",\"curVersion\":\"5.0.4\",\"authInfo\":{\"udid\":\"00000000000000063aa461b71c4cfcf\"}}";
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = (Button) findViewById(R.id.btn_get);
        btnPost = (Button) findViewById(R.id.btn_post);
        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
//        okHttp = new OkHttpClient();
        okHttp = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .cache(new Cache(Environment.getExternalStorageDirectory(),10*1024*1024)).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:
                getOk();
                break;
            case R.id.btn_post:
                postOk();
                break;
        }
    }

    private void postOk() {
        FormBody formBody = new FormBody.Builder().add(key,value).build();
        Request request = new Request.Builder().url(postUrl).post(formBody).build();
        okHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("MainActivity", response.body().string());
            }
        });
    }

    private void getOk() {
        Request request = new Request.Builder().url(getUrl).build();
        okHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();
                String result = response.body().string();
                Bean bean = gson.fromJson(result,Bean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //okHttp 运行在子线程,用handler 把解析完的数据发送到主线程
                        //在进行UI操作
                    }
                });
            }
        });
    }
}
