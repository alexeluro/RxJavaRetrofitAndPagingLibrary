package com.inspiredcoda.rxjavaretrofitandpaginglibrary.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit instance;
    private Api api;


    public Client(){}

    private static synchronized Retrofit getInstance(){
        if(instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public Api getApi(){
        api = getInstance().create(Api.class);
        return api;
    }

}
