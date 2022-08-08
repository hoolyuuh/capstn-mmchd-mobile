package com.idsr.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.idsr.mobile.databinding.ActivityAddcaseBinding;
import com.idsr.mobile.models.User;

public class AddcaseActivity extends AppCompatActivity {
    private ActivityAddcaseBinding binding;

    private Button measles, rabies, pertussis;
    private Button dengue, cholera, leptospirosis;

    private Bundle bundle;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddcaseBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_addcase);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        user = bundle.getParcelable("user");

        this.findViewById(R.id.add_measles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCIF = new Intent(AddcaseActivity.this, AddcifMeaslesActivity.class);
                goToCIF.putExtras(bundle);
                startActivity(goToCIF);
                finish();
            }
        });
    }
}
