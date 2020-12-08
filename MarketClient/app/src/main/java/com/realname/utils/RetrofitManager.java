package com.realname.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    //设置公共的请求URL
    private static String BASE_URL="http://mysterykiiten.top/MarketServer/";
    private final Retrofit retrofit;


    private static class SingleHolder{
        private static final RetrofitManager _INSTANCE =new RetrofitManager(BASE_URL);
    }

    public static RetrofitManager getDefault(){
        return SingleHolder._INSTANCE;
    }

    private  RetrofitManager(String baseUrl){
        retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(buildOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


    }

    private OkHttpClient buildOkhttpClient() {
        OkHttpClient client =new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .connectTimeout(5000,TimeUnit.MILLISECONDS)
                .build();
        return  client;
    }

    public <T> T create(Class<T> Clazz){

        return  retrofit.create(Clazz);

    }

}
