package com.idsr.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.idsr.mobile.databinding.ActivityAddcaseBinding;

public class AddcaseActivity extends AppCompatActivity {
    private ActivityAddcaseBinding binding;

    private Button measles, rabies, pertussis;
    private Button dengue, cholera, leptospirosis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddcaseBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_addcase);

        this.findViewById(R.id.add_measles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddcaseActivity.this, AddcifMeaslesActivity.class));
            }
        });
    }
}
