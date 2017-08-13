package com.example.cr.restmvc.model.api;


import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestApiManager
{
    private FlowerApi flowerApi;
    public static final String BASE_URL = "http://services.hanselandpetal.com";
    public static final String PHOTO_URL = BASE_URL + "/photos/";

    public FlowerApi getFlowerApi()
    {
        if(flowerApi == null)
        {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(String.class, new StringDesirializer());

            flowerApi = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setConverter(new GsonConverter(gsonBuilder.create()))
                    .build()
                    .create(FlowerApi.class);
        }
        return flowerApi;
    }
}
