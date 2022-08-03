package com.idsr.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.idsr.mobile.databinding.ActivityAddcaseBinding;
import com.idsr.mobile.databinding.ActivityAddtcl0Binding;

public class AddtclActivity extends AppCompatActivity {
    private ActivityAddtcl0Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddtcl0Binding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_addtcl0);


    }
}
