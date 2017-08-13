package com.example.cr.restmvc.controller;

import android.util.Log;

import com.example.cr.restmvc.model.api.RestApiManager;
import com.example.cr.restmvc.model.pojo.Flower;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Controller
{
    private static final String TAG = Controller.class.getSimpleName();
    private FlowerCallbackListener flowerCallbackListener;
    private RestApiManager restApiManager;

    public Controller(FlowerCallbackListener listener)
    {
        flowerCallbackListener = listener;
        restApiManager = new RestApiManager();
    }

    public void startFetching()
    {
        restApiManager.getFlowerApi().getFlowers(new Callback<String>()
        {
            @Override
            public void success(String s, Response response)
            {
                Log.d(TAG, "JSON:: " +s);

                try
                {
                    JSONArray array = new JSONArray(s);

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);

                        Flower flower = new Flower.Builderm()
                                .setCategory(object.getString("category"))
                                .setPrice(object.getDouble("price"))
                                .setInstructions(object.getString("instructions"))
                                .setPhoto(object.getString("photo"))
                                .setName(object.getString("name"))
                                .setProductId(object.getInt("productId"))
                                .build();

                        flowerCallbackListener.onFetchProgress(flower);
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

                flowerCallbackListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.d(TAG, "Error: " +error.getMessage());
                flowerCallbackListener.onFetchComplete();
            }
        });
    }

    public interface FlowerCallbackListener
    {
        void onFetchProgress(Flower flower);
        void onFetchComplete();
    }
}
