package com.waslla_bank.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import com.waslla_bank.interfaces.HttpApiInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bassiouny on 26/03/18.
 */

public class ApiConfig {

    private static Retrofit retrofit;
    //private static final String baseUrl = "http://www.wasllabank.com/public/api/";
    private static final String baseUrl = "http://www.wasllabank.com/application/public/api/";
    //private static final String baseUrl = "http://www.esameisa.com/demo/waslabank/public/api/";
    public static HttpApiInterface httpApiInterface;


    private ApiConfig() {
    }

    public static void initRetrofitConfig() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            httpApiInterface = retrofit.create(HttpApiInterface.class);
        }
    }
}