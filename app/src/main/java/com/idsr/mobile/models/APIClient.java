package com.idsr.mobile.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL = "https://covid-progplan.herokuapp.com/";
    public Retrofit retrofit;
    public Gson gson;
//    public static APIEndpoint APIservice;

    public APIClient() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        APIservice = retrofit.create(APIEndpoint.class);
    }
}
