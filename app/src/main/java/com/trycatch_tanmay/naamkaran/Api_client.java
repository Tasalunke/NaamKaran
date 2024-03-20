package com.trycatch_tanmay.naamkaran;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Api_client {
    private static final String BASE_URL = "https://mapi.trycatchtech.com/v1/naamkaran/";
    private static TryCatchInterface tryCatchInterface;

    public static TryCatchInterface getTryCatchInterface() {
        if (tryCatchInterface == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(80, TimeUnit.SECONDS)
                    .connectTimeout(80, TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            tryCatchInterface = retrofit.create(TryCatchInterface.class);
        }
        return tryCatchInterface;
    }

    public interface TryCatchInterface {

            @GET("post_list_by_cat_and_gender")
            Call<ArrayList<Api_POJO>> getHinduData(@Query("category_id") int categoryId, @Query("gender") int gender);

            @GET("post_list_by_cat_and_gender")
            Call<ArrayList<Api_POJO_Muslim>> getMuslimData(@Query("category_id") int categoryId, @Query("gender") int gender);

            @GET("post_list_by_cat_and_gender")
            Call<ArrayList<Api_POJO_Christian>> getChristianData(@Query("category_id") int categoryId, @Query("gender") int gender);
        @GET("post_list_by_cat_and_gender")
        Call<ArrayList<HinduGirlApiPOJO>> getHindgirludata(@Query("category_id") int categoryId, @Query("gender") int gender);
        @GET("post_list_by_cat_and_gender")
        Call<ArrayList<MuslimGirlApi_POJO>> getMuslimgirldata(@Query("category_id") int categoryId, @Query("gender") int gender);
        @GET("post_list_by_cat_and_gender")
        Call<ArrayList<ChristianGirlApi_POJO>> getChristiangirldata(@Query("category_id") int categoryId, @Query("gender") int gender);


    }
}
