package com.idsr.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.idsr.mobile.databinding.ActivityAddcifMeaslesBinding;
import com.idsr.mobile.databinding.ActivityStartBinding;

public class AddcifMeaslesActivity extends AppCompatActivity {
    private ActivityAddcifMeaslesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddcifMeaslesBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_addcif_measles);

//        startActivity(new Intent(AddcifMeaslesActivity.this, AddcaseActivity.class));
    }
}
