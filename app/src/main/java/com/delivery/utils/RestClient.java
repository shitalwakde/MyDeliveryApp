package com.delivery.utils;


import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RestClient {

    private static final String BASE_URI = AutoSingleton.getInstance().TEST_URL;

    private ApiInterface apiService;

    public RestClient() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Content-Type", "application/json");
            }
        };

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(1, TimeUnit.MINUTES);
        okHttpClient.setConnectTimeout(1, TimeUnit.MINUTES);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(BASE_URI)
                .build();

        apiService = restAdapter.create(ApiInterface.class);
    }

    public ApiInterface getApiService(){
        return apiService;
    }
}
