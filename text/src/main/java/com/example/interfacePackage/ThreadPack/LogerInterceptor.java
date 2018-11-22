package com.example.interfacePackage.ThreadPack;

import java.io.IOException;
import java.net.HttpCookie;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LogerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request requestBody = chain.request();
        if (requestBody.method().equals("GET")) {
            HttpUrl httpUrl = requestBody.url().newBuilder().addQueryParameter("zjamg", "123").build();
            requestBody = requestBody.newBuilder().url(httpUrl).build();
        }



        return chain.proceed(requestBody);
    }
}
