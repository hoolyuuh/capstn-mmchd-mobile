package com.idsr.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.idsr.mobile.databinding.ActivityAddtcl0Binding;
import com.idsr.mobile.databinding.ActivityHomeBinding;
import com.idsr.mobile.models.APIClient;
import com.idsr.mobile.models.User;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private Bundle bundle;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_home);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        user = bundle.getParcelable("user");

        Toast.makeText(HomeActivity.this, "Hello " + user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_LONG).show();

        // CIF + CRF Activity
        this.findViewById(R.id.new_disease_case).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddCase = new Intent(HomeActivity.this, AddcaseActivity.class);
                goToAddCase.putExtras(bundle);
                startActivity(goToAddCase);
            }
        });

        // Health Event Activity
        this.findViewById(R.id.new_health_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAddEBS = new Intent(HomeActivity.this, AddHealthEventActivity.class);
                gotoAddEBS.putExtras(bundle);
                startActivity(gotoAddEBS);
            }
        });

        // TCL Activity
        this.findViewById(R.id.new_immunization).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddImmun = new Intent(HomeActivity.this, AddtclActivity.class);
                goToAddImmun.putExtras(bundle);
                startActivity(goToAddImmun);
            }
        });

        // Logout Action
        this.findViewById(R.id.user_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // User Account Activity
        this.findViewById(R.id.user_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAccountActivity = new Intent(HomeActivity.this, AccountActivity.class);
                goToAccountActivity.putExtras(bundle);
                startActivity(goToAccountActivity);
            }
        });
    }
}
