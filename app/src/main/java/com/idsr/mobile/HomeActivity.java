package com.idsr.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.idsr.mobile.databinding.ActivityAddtcl0Binding;
import com.idsr.mobile.databinding.ActivityHomeBinding;
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

        this.findViewById(R.id.add_case).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AddcaseActivity.class));
            }
        });

        this.findViewById(R.id.add_ebs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAddEBS = new Intent(HomeActivity.this, AddHealthEventActivity.class);
                gotoAddEBS.putExtras(bundle);
                startActivity(gotoAddEBS);
            }
        });

        this.findViewById(R.id.add_tcl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AddtclActivity.class));
            }
        });
    }
}
