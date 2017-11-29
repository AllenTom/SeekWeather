package com.allen.api

import java.io.IOException

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Allen on 2016/12/6.
 */

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val newUrl = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter("key", "2ff525fa5a2748248618fe7c1159d124")

        val newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(newUrl.build())
                .build()
        return chain.proceed(newRequest)


    }
}
