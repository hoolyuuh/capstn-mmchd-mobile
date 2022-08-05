package com.idsr.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.idsr.mobile.models.Event;
import com.idsr.mobile.models.User;

import java.util.Calendar;

public class AddHealthEventActivity extends AppCompatActivity {

    private Boolean page1 = Boolean.FALSE;
    private Boolean page2 = Boolean.FALSE;
    private Boolean page3 = Boolean.FALSE;

    private Button back1, next1, back2, next2, back3, next3, submit, cancel;

    // page 1
    private EditText ET_CaptureDate, ET_CaptureTime, ET_ReportSource;
    private RadioGroup RG_Source;
    private int RG_source_checkedID;
    // page 2
    private EditText ET_EventDetails, ET_HouseStreet;

    // page 3


    //Data
    private Event event;
    private Bundle bundle;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getIntent().getExtras();
        user = bundle.getParcelable("user");
        event = new Event();
        event.setUserID(user.getUserID());

        page1();
    }

    public void page1(){
        setContentView(R.layout.activity_health_event1);

        this.ET_CaptureDate = findViewById(R.id.ti_ebs_capture_date);
        this.ET_CaptureTime = findViewById(R.id.ti_ebs_capture_time);
        this.ET_ReportSource = findViewById(R.id.ti_ebs_report_source);
        this.RG_Source = findViewById(R.id.rg_ebs_source);

        this.next1 = findViewById(R.id.btn_ebs_next0);
        this.back1 = findViewById(R.id.btn_ebs_back0);

//      TODO : Fill up on backpress
        if(page1){
            ET_ReportSource.setText(event.getReportSource());
            ET_CaptureDate.setText(event.getDateCaptured());
            ET_CaptureTime.setText(event.getTimeCaptured());
            RG_Source.check(RG_source_checkedID);
        }

        this.ET_CaptureDate.setOnClickListener(new View.OnClickListener() {@Override

            public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddHealthEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                            ET_CaptureDate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });

        this.ET_CaptureTime.setOnClickListener(new View.OnClickListener(){@Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour, mMinute;
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddHealthEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                ET_CaptureTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();}
        });

        // Launch Time Picker Dialog

        RG_Source.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                event.setSource(radioButton.getText().toString());
                RG_source_checkedID = i;
//                Toast.makeText(getBaseContext(), sex, Toast.LENGTH_SHORT).show();
            }
        });

        // Next Button
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page1 = true;

                if (ET_ReportSource.getText().toString().length() <= 0) { page1 = false; ET_ReportSource.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_ReportSource.setBackgroundResource(R.drawable.inputbox);
                if (ET_CaptureDate.getText().toString().length() <= 0) { page1 = page1 & false; ET_CaptureDate.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_CaptureDate.setBackgroundResource(R.drawable.inputbox);
                if (ET_CaptureTime.getText().toString().length() <= 0) { page1 = page1 & false; ET_CaptureTime.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_CaptureTime.setBackgroundResource(R.drawable.inputbox);
                if (event.getSource() == null) { page1 = page1 & false; RG_Source.setBackgroundResource(R.color.theme_lightest_red); }
                else RG_Source.setBackgroundResource(0);

                if(!page1) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    event.setReportSource(ET_ReportSource.getText().toString());
                    event.setDateCaptured(ET_CaptureDate.getText().toString());
                    event.setTimeCaptured(ET_CaptureTime.getText().toString());
                    page2();
                }
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void page2(){
        setContentView(R.layout.activity_health_event2);

        this.next2 = findViewById(R.id.btn_ebs_next1);
        this.back2 = findViewById(R.id.btn_ebs_back1);

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2 = true;

                if (ET_ReportSource.getText().toString().length() <= 0) { page1 = false; ET_ReportSource.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_ReportSource.setBackgroundResource(R.drawable.inputbox);
                if (ET_CaptureDate.getText().toString().length() <= 0) { page1 = page1 & false; ET_CaptureDate.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_CaptureDate.setBackgroundResource(R.drawable.inputbox);
                if (ET_CaptureTime.getText().toString().length() <= 0) { page1 = page1 & false; ET_CaptureTime.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_CaptureTime.setBackgroundResource(R.drawable.inputbox);
                if (event.getSource() == null) { page1 = page1 & false; RG_Source.setBackgroundResource(R.color.theme_lightest_red); }
                else RG_Source.setBackgroundResource(0);

                if(!page1) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    event.setReportSource(ET_ReportSource.getText().toString());
                    event.setDateCaptured(ET_CaptureDate.getText().toString());
                    event.setTimeCaptured(ET_CaptureTime.getText().toString());
                    page2();
                }
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page1();
            }
        });
    }

    public void page3(){

    }
}
