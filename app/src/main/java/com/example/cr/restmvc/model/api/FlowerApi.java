package com.example.cr.restmvc.model.api;


import retrofit.Callback;
import retrofit.http.GET;

public interface FlowerApi
{
    @GET("/feeds/flowers.json")
    void getFlowers(Callback<String> flowers);
}
