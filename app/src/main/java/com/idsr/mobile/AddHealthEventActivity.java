package com.idsr.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.idsr.mobile.databinding.ActivityHomeBinding;

public class AddHealthEventActivity extends AppCompatActivity {

    private Boolean page1 = Boolean.FALSE;
    private Boolean page2 = Boolean.FALSE;
    private Boolean page3 = Boolean.FALSE;

    private Button back1, next1, back2, next2, back3, next3, submit, cancel;

    // page 1
    private EditText ET_CaptureDate, ET_CaptureTime, ET_Source, ET_ReportSource;
    private RadioGroup RG_Source;

    // page 2
    private EditText ET_EventDetails;

    // page 3


    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityAddHealthEventBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_health_event1);
    }


}
