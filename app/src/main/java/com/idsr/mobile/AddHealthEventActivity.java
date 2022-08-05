package com.idsr.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.idsr.mobile.models.APIClient;
import com.idsr.mobile.models.APIModels.LoginJS;
import com.idsr.mobile.models.APIModels.LoginResponse;
import com.idsr.mobile.models.Event;
import com.idsr.mobile.models.User;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHealthEventActivity extends AppCompatActivity {

    private APIClient apiClient;

    private Boolean page1 = Boolean.FALSE;
    private Boolean page2 = Boolean.FALSE;
    private Boolean page3 = Boolean.FALSE;

    private Button back1, next1, back2, submit, confirm, cancel;

    // page 1
    private EditText ET_CaptureDate, ET_CaptureTime, ET_ReportSource;
    private RadioGroup RG_Source;
    private int RG_source_checkedID;
    // page 2
    private EditText ET_EventDetails, ET_HouseStreet, ET_NumCases, ET_NumDeaths, ET_Remarks;
    private Spinner S_City, S_Brgy;
    private TextView TV_City, TV_Brgy;
    // page 3


    //Data
    private Event healthEvent;
    private Bundle bundle;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiClient = new APIClient();

        bundle = getIntent().getExtras();
        user = bundle.getParcelable("user");
        healthEvent = new Event();
        healthEvent.setUserID(user.getUserID());

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
            ET_ReportSource.setText(healthEvent.getReportSource());
            ET_CaptureDate.setText(healthEvent.getDateCaptured());
            ET_CaptureTime.setText(healthEvent.getTimeCaptured());
            RG_Source.check(RG_source_checkedID);
        }

        this.ET_CaptureDate.setOnClickListener(new View.OnClickListener() {@Override

            public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddHealthEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                            ET_CaptureDate.setText( Strings.padStart(Integer.toString(year),4,'0') + "-" +
                                    Strings.padStart(Integer.toString(monthOfYear),2,'0') + "-" +
                                    Strings.padStart(Integer.toString(dayOfMonth),2,'0')
                            );} }, mYear, mMonth, mDay);
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

                                ET_CaptureTime.setText(Strings.padStart(Integer.toString(hourOfDay),2,'0') + ":" + Strings.padStart(Integer.toString(minute),2,'0') + ":00");
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
                healthEvent.setSource(radioButton.getText().toString());
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
                if (ET_CaptureDate.getText().toString().length() <= 0) { page1 = false; ET_CaptureDate.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_CaptureDate.setBackgroundResource(R.drawable.inputbox);
                if (ET_CaptureTime.getText().toString().length() <= 0) { page1 = false; ET_CaptureTime.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_CaptureTime.setBackgroundResource(R.drawable.inputbox);
                if (healthEvent.getSource() == null) { page1 = false; RG_Source.setBackgroundResource(R.color.theme_lightest_red); }
                else RG_Source.setBackgroundResource(0);

                if(!page1) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    healthEvent.setReportSource(ET_ReportSource.getText().toString());
                    healthEvent.setDateCaptured(ET_CaptureDate.getText().toString());
                    healthEvent.setTimeCaptured(ET_CaptureTime.getText().toString());
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

        this.ET_EventDetails = findViewById(R.id.ti_ebs_event_details);
        this.ET_HouseStreet = findViewById(R.id.ti_ebs_street);
        this.ET_NumCases = findViewById(R.id.ti_ebs_cases);
        this.ET_NumDeaths = findViewById(R.id.ti_ebs_deaths);
        this.ET_Remarks = findViewById(R.id.ti_ebs_remarks);

        this.S_Brgy = findViewById(R.id.spinner_ebs_brgy);
        this.S_City = findViewById(R.id.spinner_ebs_city);

        this.TV_Brgy = findViewById(R.id.tv_ebs_brgy);
        this.TV_City = findViewById(R.id.tv_ebs_city);

        this.submit = findViewById(R.id.btn_ebs_submit);
        this.back2 = findViewById(R.id.btn_ebs_back1);

        ArrayAdapter<CharSequence> adapterCity=ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_item);
        S_City.setAdapter(adapterCity);
        S_City.setSelection(0, true);
        S_City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TV_City.setText(S_City.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);

                ArrayAdapter<CharSequence>adapterBrgy=ArrayAdapter.createFromResource(S_Brgy.getContext(), getBrgy(S_City.getSelectedItem().toString()), android.R.layout.simple_spinner_item);
                adapterBrgy.setDropDownViewResource(android.R.layout.simple_spinner_item);
                S_Brgy.setAdapter(adapterBrgy);
                S_Brgy.setSelection(0, true);
                S_Brgy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        TV_Brgy.setText(S_Brgy.getSelectedItem().toString());
                        ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        if(page2) {
            ET_EventDetails.setText(healthEvent.getEventDetails());
            ET_Remarks.setText(healthEvent.getRemarks());
            ET_NumCases.setText(healthEvent.getNumCases());
            ET_NumDeaths.setText(healthEvent.getNumDeaths());
            ET_HouseStreet.setText(healthEvent.getLocHouseStreet());

            TV_City.setText(healthEvent.getLocCity());
            TV_Brgy.setText(healthEvent.getLocBrgy());
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2 = true;

                if (ET_EventDetails.getText().toString().length() <= 0) { page2 = false; ET_EventDetails.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_EventDetails.setBackgroundResource(R.drawable.inputbox);
                if (ET_HouseStreet.getText().toString().length() <= 0) { page2 = false; ET_HouseStreet.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_HouseStreet.setBackgroundResource(R.drawable.inputbox);
                if (ET_Remarks.getText().toString().length() <= 0) { page2 = false; ET_Remarks.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_Remarks.setBackgroundResource(R.drawable.inputbox);
                if (ET_NumCases.getText().toString().length() <= 0) { page2 = false; ET_NumCases.setBackgroundResource(R.drawable.inputbox_red); }
                else ET_NumCases.setBackgroundResource(R.drawable.inputbox);
                if (TV_Brgy.getText().toString().length() <= 0) { page2 = false; TV_Brgy.setBackgroundResource(R.drawable.inputbox_red); }
                else TV_Brgy.setBackgroundResource(R.drawable.inputbox);
                if (TV_City.getText().toString().length() <= 0) { page2 = false; TV_City.setBackgroundResource(R.drawable.inputbox_red); }
                else TV_City.setBackgroundResource(R.drawable.inputbox);
                if (ET_NumDeaths.getText().toString().length() <= 0 ) {
                    page2 = false; ET_NumDeaths.setBackgroundResource(R.drawable.inputbox_red); }
                else {
                    if(Integer.parseInt(ET_NumDeaths.getText().toString()) > Integer.parseInt(ET_NumCases.getText().toString())) {
                        page2 = false;
                        ET_NumCases.setBackgroundResource(R.drawable.inputbox_red);
                        ET_NumDeaths.setBackgroundResource(R.drawable.inputbox_red);
                    }
                    else ET_NumDeaths.setBackgroundResource(R.drawable.inputbox);
                }


                if(!page2) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    healthEvent.setEventDetails(ET_EventDetails.getText().toString());
                    healthEvent.setLocHouseStreet(ET_HouseStreet.getText().toString());
                    healthEvent.setRemarks(ET_Remarks.getText().toString());
                    healthEvent.setNumCases(ET_NumCases.getText().toString());
                    healthEvent.setNumDeaths(ET_NumDeaths.getText().toString());
                    healthEvent.setLocBrgy(TV_Brgy.getText().toString());
                    healthEvent.setLocCity(TV_City.getText().toString());

//                    For testing purposes only
                    Log.e("Values", healthEvent.toString());
                    submit(view);
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

    public void submit(View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_confirm_popup, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button buttonCancel = popupView.findViewById(R.id.btn_cancel);
        Button buttonConfirm = popupView.findViewById(R.id.btn_confirm);
        buttonCancel.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            popupWindow.dismiss();
        }
        });
        buttonConfirm.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {

            //As an example, display the message
            Toast.makeText(view.getContext(), "Please wait for page to load", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
            setContentView(R.layout.activity_loading);

            // TODO: make function to submit the thingies
            TextView tvCaseAddStatus = findViewById(R.id.tv_caseAddStatus);
            ProgressBar loadingpanel = findViewById(R.id.loadingPanel);
            ImageView imgCheck = findViewById(R.id.imgCheckPanel);

            LinearLayout layoutDone = findViewById(R.id.layout_done);
            Button buttonHome = findViewById(R.id.btn_home);
            Button buttonAddEvent = findViewById(R.id.btn_addanothercase);

            Call<ResponseBody> call = apiClient.APIservice.postAddEvent(healthEvent);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response.code() == 200) {
                            loadingpanel.setVisibility(View.GONE);
                            imgCheck.setVisibility(View.VISIBLE);
                            layoutDone.setVisibility(View.VISIBLE);
                            tvCaseAddStatus.setText("Event successfully submitted!");
                            buttonAddEvent.setText("Add New Event");

                            buttonHome.setOnClickListener(new View.OnClickListener() { @Override
                            public void onClick(View view) {
                                finish();
                            }
                            });
                            buttonAddEvent.setOnClickListener(new View.OnClickListener() { @Override
                            public void onClick(View view) {
                                page1 = false;
                                page2 = false;
                                healthEvent = new Event();
                                healthEvent.setUserID(user.getUserID());

                                page1();
                            }
                            });
                        } else {
                            Toast.makeText(AddHealthEventActivity.this, "Add Event Failed", Toast.LENGTH_LONG).show();
                            page2();
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

        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() { @Override
        public boolean onTouch(View v, MotionEvent healthEvent) {
            //Close the window when clicked
            popupWindow.dismiss();
            return true; }
        });
    }

    public int getBrgy(String city) {
        switch (city) {
            case "CALOOCAN CITY": return R.array.caloocan;
            case "LAS PIÑAS CITY": return R.array.laspinas;
            case "MAKATI CITY": return R.array.makati;
            case "MALABON CITY": return R.array.malabon;
            case "MANDALUYONG CITY": return R.array.mandaluyong;
            case "MANILA CITY": return R.array.manila;
            case "MARIKINA CITY": return R.array.marikina;
            case "MUNTINLUPA CITY": return R.array.muntinlupa;
            case "NAVOTAS CITY": return R.array.navotas;
            case "PARAÑAQUE CITY": return R.array.paranaque;
            case "PASAY CITY": return R.array.pasay;
            case "PASIG CITY": return R.array.pasig;
            case "QUEZON CITY": return R.array.quezon;
            case "SAN JUAN CITY": return R.array.sanjuan;
            case "TAGUIG CITY": return R.array.taguig;
            case "VALENZUELA CITY": return R.array.valenzuela;
            default: return 0;
        }
    }
}
