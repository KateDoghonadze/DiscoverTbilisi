package com.example.qeto.discovertbilisi.api.Service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZVIO on 11/30/2016.
 */

public class RetrofitClient {
    private static final String BASE_URL = "http://jsonstub.com/";
    private static Retrofit service=null;


        public static Retrofit getClient() {
            if (service == null) {

                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS);
                httpClient.addInterceptor(logging);

                service = new Retrofit.Builder()
                        .client(httpClient.build())
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
//                    .addConverterFactory(new MixedConverterFactory(GsonConverterFactory.create(), SimpleXmlConverterFactory.create()))
                        .build();
            }
            return service;
        }
    }
