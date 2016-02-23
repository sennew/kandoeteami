package com.projects.wens.kandoeteami.retrofit;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by senne on 23/02/2016.
 */
public class ServiceGenerator {
    private static RestAdapter.Builder builder = new RestAdapter.Builder();

    private static OkHttpClient getHttpClient() {
        OkHttpClient httpClient;
        httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        httpClient.setReadTimeout(10, TimeUnit.SECONDS);

        return httpClient;
    }

    public static <T> T createService(Class<T> serviceClass, String baseUrl){
        builder.setEndpoint(baseUrl);

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        });

        RestAdapter adapter = builder
                .setClient(new OkClient(getHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        return adapter.create(serviceClass);
    }


    public static <T> T createService(Class<T> serviceClass, String baseUrl, Gson gson){
        builder.setEndpoint(baseUrl);
        builder.setConverter(new GsonConverter(gson));

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        });

        RestAdapter adapter = builder
                .setClient(new OkClient(getHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        return adapter.create(serviceClass);
    }

}
