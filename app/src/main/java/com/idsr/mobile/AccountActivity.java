package com.idsr.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.idsr.mobile.models.APIClient;
import com.idsr.mobile.models.APIModels.EventJs;
import com.idsr.mobile.models.APIModels.SettingsResponse;
import com.idsr.mobile.models.APIModels.TCLResponse;
import com.idsr.mobile.models.Event;
import com.idsr.mobile.models.User;
import com.idsr.mobile.models.UserSettings;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    private APIClient apiClient;

    private TextView tv_name, tv_email;
    private Switch sw_consent;

    private Bundle bundle;
    private User user;
    private SettingsResponse settingsResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        apiClient = new APIClient();
        bundle = getIntent().getExtras();
        user = bundle.getParcelable("user");

        Call<SettingsResponse> call = apiClient.APIservice.getUserSettings(user.getUserID());
        call.enqueue(new Callback<SettingsResponse>() {
            @Override
            public void onResponse(Call<SettingsResponse> call, Response<SettingsResponse> response){
//                Log.e("TestingAPI", "Got here");
//                Log.e("TestingAPI", "Contents: " + response.body().getTCL().getTCLID());
                if(response.code() == 200){
                    settingsResponse =response.body();
                    navToSettings();
                } else {
                    Toast.makeText(AccountActivity.this, "Server is unable to access user settings.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            @Override
            public void onFailure(Call<SettingsResponse> call, Throwable t) {
                Log.e("callGetTest", t.getMessage());
                finish();
            }
        });
    }

    private void navToSettings() {
        setContentView(R.layout.activity_account);

        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        sw_consent = findViewById(R.id.switch_location);

        tv_name.setText(user.getFirstName() +  " " + user.getLastName());
        tv_email.setText(user.getUserEmail());

        if(settingsResponse.getUserSettings().getPushDataAccept() == 1)
            sw_consent.setChecked(true);
        else
            sw_consent.setChecked(false);

        sw_consent.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) settingsResponse.getUserSettings().setPushDataAccept(1);
                else settingsResponse.getUserSettings().setPushDataAccept(0);

                Call<ResponseBody> call = apiClient.APIservice.postUpdatePushData(settingsResponse.getUserSettings());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response.code() == 200) {
                                Toast.makeText(AccountActivity.this, "Changes have been saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AccountActivity.this, "Changes were unable to be saved", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Error e) {
                            Log.e("failedPostEvent", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("failedPostLogin", t.getMessage());
                    }
                });
            }
        });
    }
}