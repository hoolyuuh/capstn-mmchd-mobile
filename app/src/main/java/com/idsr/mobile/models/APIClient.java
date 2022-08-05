package com.idsr.mobile.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.idsr.mobile.models.APIModels.LoginJS;
import com.idsr.mobile.models.APIModels.LoginResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class APIClient {
    public static final String BASE_URL = "https://capstn-mmchd.herokuapp.com/api/";
    public Retrofit retrofit;
    public Gson gson;
    public static APIEndpointInterface APIservice;

    public APIClient() {
        gson = new GsonBuilder()
                .setDateFormat(DateFormat.FULL)
                .setLenient()
                .create();
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIservice = retrofit.create(APIEndpointInterface.class);
    }

    public interface APIEndpointInterface {
        Call<ResponseBody> getTest();

        @POST("login")
        Call<LoginResponse> postLogin(@Body LoginJS login);

        @POST("newEvent")
        Call<ResponseBody> postAddEvent(@Body Event event);

        /* Template for query paths */

    }

    /* This is NOT to be called anywhere in the application. This is only a template to
		use as a guide on how to write functions with RetroFit. Do not invoke this, this
		will not do anything useful!
	 */
    public void callGetTest() {
        Call<ResponseBody> call = APIservice.getTest();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("callGetTest", response.body().string());
                } catch (IOException e) {
                    Log.e("callGetTest", e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("callGetTest", t.getMessage());
            }
        });
    }
}
