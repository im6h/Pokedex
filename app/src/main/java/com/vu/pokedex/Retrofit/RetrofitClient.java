package com.vu.pokedex.Retrofit;

import com.vu.pokedex.Common.Common;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl(Common.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }
}
