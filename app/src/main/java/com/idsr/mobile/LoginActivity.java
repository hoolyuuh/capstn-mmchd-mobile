package com.idsr.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.idsr.mobile.databinding.ActivityLoginBinding;
import com.idsr.mobile.models.APIClient;
import com.idsr.mobile.models.User;

public class LoginActivity extends AppCompatActivity {
    private APIClient apiClient;

    private ActivityLoginBinding binding;
    private EditText ETemail, ETpassword;
    private Button login;
    private ImageView eyes;
    private Boolean see;

    private String email, password;
    private Bundle bundle;
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiClient = new APIClient();
        bundle = new Bundle();

        ETemail = findViewById(R.id.ti_email);
        ETpassword = findViewById(R.id.ti_password);
        this.login = findViewById(R.id.btn_log);
        this.eyes = findViewById(R.id.iv_eye);
        see = false;

        this.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ETemail.getText().toString().isEmpty() || ETpassword.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Please complete the form with correct information.", Toast.LENGTH_SHORT).show();

                else {
//                    User user = callUserLog();
//                    if(user.getUserType()==0) sendAdminToken();
                    // TODO: create login method
                    //if(userIsFound)
                    bundle.putParcelable("user", new User(ETemail.getText().toString(),"Sample Type", "Username"));
                    Intent gotoHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                    gotoHomeActivity.putExtras(bundle);
                    startActivity(gotoHomeActivity);
                    /* else {
                        Toast.makeText(LoginActivity.this, "Login credentials failed.", Toast.LENGTH_SHORT).show();
                    }
                     */
                }
            }
        });

        this.eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                see = !see;
                if (see) {
                    eyes.setImageResource(R.drawable.eyeon);
                    ETpassword.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else {
                    eyes.setImageResource(R.drawable.eyeoff);
                    ETpassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }
}
