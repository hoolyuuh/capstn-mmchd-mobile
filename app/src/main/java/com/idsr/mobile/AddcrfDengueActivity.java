package com.idsr.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.idsr.mobile.models.APIClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddcrfDengueActivity extends AppCompatActivity {
    private APIClient apiClient;
    private ArrayList<String> userCRFs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = new APIClient();
        userCRFs = new ArrayList<>();
    }

    private void getCRFs() {
        Call<ArrayList<String>> call = apiClient.APIservice.getMobileCRFs("TODO!!!");
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                Log.d("postNewCase", "CRF list size: " + response.body().size());
                if (response.code() == 200) {
                    userCRFs = response.body();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.e("postNewCase", t.getMessage());
            }
        });
    }
}
