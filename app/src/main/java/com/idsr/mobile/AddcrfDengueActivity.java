package com.idsr.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.idsr.mobile.models.APIClient;
import com.idsr.mobile.models.Case;
import com.idsr.mobile.models.CaseData;
import com.idsr.mobile.models.CaseForm;
import com.idsr.mobile.models.CaseFormJS;
import com.idsr.mobile.models.Patient;
import com.idsr.mobile.models.RiskFactors;
import com.idsr.mobile.models.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddcrfDengueActivity extends AppCompatActivity {
    private Boolean page1 = Boolean.FALSE;
    private Boolean page2 = Boolean.FALSE;
    private Boolean page3 = Boolean.FALSE;
    private Boolean page4 = Boolean.FALSE;
    private Boolean page5 = Boolean.FALSE;
    private Boolean page6 = Boolean.FALSE;
    private Boolean page7 = Boolean.FALSE;
    private Button cancel, next1, back0, next2, back1, next3, back2, next4, back3, next5, back4, next6, back5, next7, back6, submit;

    private APIClient apiClient;
    private ArrayList<String> userCRFs;
    private User user;
    private Bundle bundle;
    private CaseForm formData;
    private ArrayList<Patient> patientArrayList;
    private Patient patient;
    private boolean existingpatient = false;
    private ArrayList<String> patientnames;

    //    page 0
    private AutoCompleteTextView autocompPatients;

    //    page 1
    private EditText etLastname, etMiddlename, etFirstname, etBirthdate, /*etPhone,*/ etPregantweeks, etIndigenousgroup;
    private RadioGroup radioSex, radioPregnancy;
    private TextView tvCivilStatus, tvOccuCity, tvOccuBrgy, tvCurrCity, tvCurrBrgy, tvPermCity, tvPermBrgy;
    private Spinner spinnerCivilstatus, spinnerOccuCity, spinnerOccuBrgy, spinnerCurrCity, spinnerCurrBrgy, spinnerPermCity, spinnerPermBrgy;
    private CheckBox checkboxSameaddress;
    private ConstraintLayout constPermanentAdd;
    private EditText etOccupation, etOcculoc, etOccuStreet, etCurrStreet, etPermStreet, etParentCg, etParentCgContact, etHCPN, etILHZ;

    private String lastName, firstName, middleName, birthdate, sex = "", pregnancy = "", civilstatus, indigenousgroup;
    private String /*phone,*/ parentCgContact;
    private String occupation, occuloc, occuStreet, occuCity, occuBrgy, currStreet, currCity, currBrgy;
    private Boolean sameCurrPermAddress = false;
    private String permStreet, permCity, permBrgy;
    private String parentCg, HCPN, ILHZ;

    //    page 2
    private RadioGroup radioPatientAdmit, radioConsulted;
    private EditText etAdmitdate, etOnsetdate, etReportdate, etReporter, etconsultDate, etConsultPlace;
    private CheckBox checkRfL1, checkRfL2, checkRfL3, checkRfL4, checkRfL5, checkRfL6;
    private CheckBox checkRfC1, checkRfC2, checkRfC3, checkRfC4;
    private CheckBox checkRfH1, checkRfH2, checkRfH3, checkRfH4, checkRfH5, checkRfH6;
    private CheckBox checkRfO1, checkRfO2, checkRfO3, checkRfO4, checkRfO5, checkRfO6, checkRfO7, checkRfO8, checkRfO9, checkRfO10, checkRfO11;
    private EditText etRfLOthers, etRfCOthers, etRfHOthers, etRfOOthers;

    private String patientAdmit = "", admitdate, onsetdate, reportdate, reporter, consultDate, consulted = "", consultPlace;
    private int riskfactors = 0;

    //    page 3
    private RadioGroup radioDengueVaccination;
    private ConstraintLayout constVaccinate;
    private EditText etdatefirstvax, etdatelastvax;

    private String datefirstvax, datelastvax, vaccinated = "";
    private int RG_Vaxcheckedid;

    private ArrayList<String> noVaccReasons = new ArrayList<>();

    //  page 4
    private RadioGroup radioDengueClinicalClass;

    private String clinicalClass = "";
    private int RG_clinicalClassID;

    //    page 5
    private RadioGroup radioOutcome;
    private EditText etDatedied, etFinalDiagnosis;
    private String outcome="", datedied, finaldiagnosis;

    //    page 6
    private RadioGroup radioLabResult;
    private ConstraintLayout consWithLabResult, consNoLabResult;
    private TextView tvLabspecimen, tvLabSelect;
    private Spinner spinnerLabSpecimen, spinnerLabSelect;
    private EditText etCollectdate, etReceivedate, etresultMeasles, etresultRubella, etresultVirus, etresultPRC, etInvestigator, etInvestigContact, etInvestigDate;

    private String labresult="", labspecimen, collectdate, receivedate, resultMeasle, resultRubella, resultVirus, resultPRC, investigator, investigatorContact, investigateDate, labselected;

    //    page 7
    private RadioGroup radioFinalClassif;
    private ImageButton imageDropdown1, imageDropdown2, imageDropdown3, imageDropdown4, imageDropdown5, imageDropdown6, imageDropdown7;
    private TextView textDropdown1, textDropdown2, textDropdown3, textDropdown4, textDropdown5, textDropdown6, textDropdown7;

    private String finalClassification="";
    private int RG_finalClassifID;

    //data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = new APIClient();
        userCRFs = new ArrayList<>();
        bundle = getIntent().getExtras();
        user = bundle.getParcelable("user");

        Call<ArrayList<String>> call = apiClient.APIservice.getMobileCRFs(user.getUserID());
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                Log.d("postNewCase", "CRF list size: " + response.body().size());
                if (response.code() == 200) {
                    userCRFs = response.body();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.e("postNewCase", t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        View view = findViewById(android.R.id.content).getRootView();
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_popup_backhome, null);

        // Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        // Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;
        // Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        // Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button buttonCancel = popupView.findViewById(R.id.btn_home_cancel);
        Button buttonConfirm = popupView.findViewById(R.id.btn_home_confirm);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                finish();
            }
        });
        // Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void pageZero() {
        // something
        pageOne();
    }

    public void pageOne() {
        // something
        pageTwo();
    }

    public void pageTwo() {
        // something
        setContentView(R.layout.activity_addcrf_dengue2);

        this.radioPatientAdmit = findViewById(R.id.radiogroup_patientadmit);
        this.etAdmitdate = findViewById(R.id.et_den_admitDate);
        this.etOnsetdate = findViewById(R.id.et_den_onset);

        this.checkRfL1 = findViewById(R.id.checkbox_den_rfL1);
        this.checkRfL2 = findViewById(R.id.checkbox_den_rfL2);
        this.checkRfL3 = findViewById(R.id.checkbox_den_rfL3);
        this.checkRfL4 = findViewById(R.id.checkbox_den_rfL4);
        this.checkRfL5 = findViewById(R.id.checkbox_den_rfL5);
        this.checkRfL6 = findViewById(R.id.checkbox_den_rfL6);
        this.etRfLOthers = findViewById(R.id.et_den_rfLOthers);
        this.checkRfC1 = findViewById(R.id.checkbox_den_rfC1);
        this.checkRfC2 = findViewById(R.id.checkbox_den_rfC2);
        this.checkRfC3 = findViewById(R.id.checkbox_den_rfC3);
        this.checkRfC4 = findViewById(R.id.checkbox_den_rfC4);
        this.etRfCOthers = findViewById(R.id.et_den_rfCOthers);
        this.checkRfH1 = findViewById(R.id.checkbox_den_rfH1);
        this.checkRfH2 = findViewById(R.id.checkbox_den_rfH2);
        this.checkRfH3 = findViewById(R.id.checkbox_den_rfH3);
        this.checkRfH4 = findViewById(R.id.checkbox_den_rfH4);
        this.checkRfH5 = findViewById(R.id.checkbox_den_rfH5);
        this.checkRfH6 = findViewById(R.id.checkbox_den_rfH6);
        this.etRfHOthers = findViewById(R.id.et_den_rfHOthers);
        this.checkRfO1 = findViewById(R.id.checkbox_den_rfO1);
        this.checkRfO2 = findViewById(R.id.checkbox_den_rfO2);
        this.checkRfO3 = findViewById(R.id.checkbox_den_rfO3);
        this.checkRfO4 = findViewById(R.id.checkbox_den_rfO4);
        this.checkRfO5 = findViewById(R.id.checkbox_den_rfO5);
        this.checkRfO6 = findViewById(R.id.checkbox_den_rfO6);
        this.checkRfO7 = findViewById(R.id.checkbox_den_rfO7);
        this.checkRfO8 = findViewById(R.id.checkbox_den_rfO8);
        this.checkRfO9 = findViewById(R.id.checkbox_den_rfO9);
        this.checkRfO10 = findViewById(R.id.checkbox_den_rfO10);
        this.checkRfO11 = findViewById(R.id.checkbox_den_rfO11);
        this.etRfHOthers = findViewById(R.id.et_den_rfOOthers);

        this.etconsultDate = findViewById(R.id.et_den_consultDate);
        this.etConsultPlace = findViewById(R.id.et_den_consultPlace);
        this.radioConsulted = findViewById(R.id.radiogroup_den_consulted);

        // TODO: Initialize Data if Backpressed
        if(page2){
            etAdmitdate.setText(admitdate);
            etOnsetdate.setText(onsetdate);
            etReportdate.setText(reportdate);
            etReporter.setText(reporter);

            if(consulted.equals("Yes")) {
                radioConsulted.check(R.id.radiobutton_den_consultYes);
                etConsultPlace.setText(consulted);
                etconsultDate.setText(consultDate);
                etConsultPlace.setVisibility(View.VISIBLE);
                etconsultDate.setVisibility(View.VISIBLE);
            } else radioConsulted.check(R.id.radiobutton_den_consultNo);
        }

        radioConsulted.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                consulted = radioButton.getText().toString();
                if (consulted.equals("Yes")) {
                    etConsultPlace.setVisibility(View.VISIBLE);
                    etconsultDate.setVisibility(View.VISIBLE);
                }
                else {
                    etConsultPlace.setText("");
                    etconsultDate.setText("");
                    etConsultPlace.setVisibility(View.INVISIBLE);
                    etconsultDate.setVisibility(View.INVISIBLE);
                }
            }
        });

        etAdmitdate.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etAdmitdate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                            Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                            Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etOnsetdate.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etOnsetdate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                            Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                            Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etReportdate.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etReportdate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                            Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                            Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });

        etconsultDate.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etconsultDate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                            Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                            Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });

//        checkRfO1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  { @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//            if (b && riskfactors/1000==1) riskfactors += 1000; } });
        checkRfL6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  { @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) etRfLOthers.setVisibility(View.VISIBLE); else etRfLOthers.setVisibility(View.INVISIBLE); } });
        checkRfC4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  { @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) etRfCOthers.setVisibility(View.VISIBLE); else etRfCOthers.setVisibility(View.INVISIBLE); } });
        checkRfH6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  { @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) etRfHOthers.setVisibility(View.VISIBLE); else etRfHOthers.setVisibility(View.INVISIBLE); } });
        checkRfO11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  { @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) etRfOOthers.setVisibility(View.VISIBLE); else etRfOOthers.setVisibility(View.INVISIBLE); } });

        this.next3 = findViewById(R.id.btn_meas_next3);
        this.back1 = findViewById(R.id.btn_meas_back1);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageOne(); }
        });

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2 = true;

                if (patientAdmit.length() <= 0) { page2 = page2 & false; radioPatientAdmit.setBackgroundResource(R.color.theme_lightest_red); }
                else radioPatientAdmit.setBackgroundResource(0);
                if (Objects.equals(patientAdmit, "Yes")) {
                    if (etAdmitdate.getText().toString().length() <= 0) { page2 = page2 & false; etAdmitdate.setBackgroundResource(R.drawable.inputbox_red); }
                    else etAdmitdate.setBackgroundResource(R.drawable.inputbox);
                }

                if (consulted.length() <= 0) { page2 = page2 & false; radioConsulted.setBackgroundResource(R.color.theme_lightest_red); }
                else radioConsulted.setBackgroundResource(0);
                if (Objects.equals(patientAdmit, "Yes")) {
                    if (etconsultDate.getText().toString().length() <= 0) { page2 = page2 & false; etconsultDate.setBackgroundResource(R.drawable.inputbox_red); }
                    else etconsultDate.setBackgroundResource(R.drawable.inputbox);
                    if (etConsultPlace.getText().toString().length() <= 0) { page2 = page2 & false; etConsultPlace.setBackgroundResource(R.drawable.inputbox_red); }
                    else etConsultPlace.setBackgroundResource(R.drawable.inputbox);
                }

                if (etOnsetdate.getText().toString().length() <= 0) { page2 = page2 & false; etOnsetdate.setBackgroundResource(R.drawable.inputbox_red); }
                else etOnsetdate.setBackgroundResource(R.drawable.inputbox);
                if (etReportdate.getText().toString().length() <= 0) { page2 = page2 & false; etReportdate.setBackgroundResource(R.drawable.inputbox_red); }
                else etReportdate.setBackgroundResource(R.drawable.inputbox);
                if (etReporter.getText().toString().length() <= 0) { page2 = page2 & false; etReporter.setBackgroundResource(R.drawable.inputbox_red); }
                else etReporter.setBackgroundResource(R.drawable.inputbox);

                if (checkRfL6.isChecked()) {
                    if (etRfLOthers.getText().toString().length() <= 0) { page2 = page2 & false; etRfLOthers.setBackgroundResource(R.drawable.inputbox_red); }
                    else etRfLOthers.setBackgroundResource(R.drawable.inputbox);
                }
                if (checkRfC4.isChecked()) {
                    if (etRfCOthers.getText().toString().length() <= 0) { page2 = page2 & false; etRfCOthers.setBackgroundResource(R.drawable.inputbox_red); }
                    else etRfCOthers.setBackgroundResource(R.drawable.inputbox);
                }
                if (checkRfH6.isChecked()) {
                    if (etRfHOthers.getText().toString().length() <= 0) { page2 = page2 & false; etRfHOthers.setBackgroundResource(R.drawable.inputbox_red); }
                    else etRfHOthers.setBackgroundResource(R.drawable.inputbox);
                }
                if (checkRfO11.isChecked()) {
                    if (etRfOOthers.getText().toString().length() <= 0) { page2 = page2 & false; etRfOOthers.setBackgroundResource(R.drawable.inputbox_red); }
                    else etRfOOthers.setBackgroundResource(R.drawable.inputbox);
                }

                // TODO: validate risk factors

                if (!page2) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    // patientAdmit set in onclick
                    admitdate = etAdmitdate.getText().toString();
                    onsetdate = etOnsetdate.getText().toString();
                    reportdate = etReportdate.getText().toString();
                    reporter = etReporter.getText().toString();
                    consultDate = etconsultDate.getText().toString();
                    consultPlace = etConsultPlace.getText().toString();
                    pageThree();
                }
            }
        });
    }

    public void pageThree() {
        // something
        setContentView(R.layout.activity_addcrf_dengue3);

        radioDengueVaccination = findViewById(R.id.radiogroup_den_vaxxed);
        etdatefirstvax = findViewById(R.id.et_den_vaxxedFirstDate);
        etdatelastvax = findViewById(R.id.et_den_vaxxedFirstDate);

        back2 = findViewById(R.id.btn_den_back2);
        next4 = findViewById(R.id.btn_den_next4);

        if(page3) {
            radioDengueVaccination.check(RG_Vaxcheckedid);
            etdatelastvax.setText(datelastvax);
            etdatefirstvax.setText(datefirstvax);

            if(vaccinated.equals("Yes")) {
                etdatelastvax.setVisibility(View.VISIBLE);
                etdatefirstvax.setVisibility(View.VISIBLE);
            }
        }

        radioDengueVaccination.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                vaccinated = radioButton.getText().toString();
                RG_Vaxcheckedid = i;

                if (vaccinated.equals("Yes")) {
                    etdatelastvax.setVisibility(View.VISIBLE);
                    etdatefirstvax.setVisibility(View.VISIBLE);
                }
                else {
                    etdatelastvax.setVisibility(View.INVISIBLE);
                    etdatefirstvax.setVisibility(View.INVISIBLE);
                }
            }
        });

        this.etdatefirstvax.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this,
                        new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                            etdatefirstvax.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                    Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                    Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });

        this.etdatelastvax.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etdatelastvax.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageTwo(); }
        });

        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page3 = true;

                if (vaccinated.equals("")) { page3 = false; radioDengueVaccination.setBackgroundResource(R.color.theme_lightest_red); }
                else radioDengueVaccination.setBackgroundResource(0);
                if(vaccinated.equals("Yes")){
                    if (etdatefirstvax.getText().toString().length() <= 0) { page3 = false; etdatefirstvax.setBackgroundResource(R.drawable.inputbox_red); }
                    else etdatefirstvax.setBackgroundResource(R.drawable.inputbox);
                    if (etdatelastvax.getText().toString().length() <= 0) { page3 = false; etdatelastvax.setBackgroundResource(R.drawable.inputbox_red); }
                    else etdatelastvax.setBackgroundResource(R.drawable.inputbox);
                }


                if (!page3) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    datefirstvax = etdatefirstvax.getText().toString();
                    datelastvax = etdatelastvax.getText().toString();

                    pageFour();
                }
            }
        });
    }

    public void pageFour() {
        // something
        radioDengueClinicalClass = findViewById(R.id.radiogroup_den_clinic_classif);

        next5 = findViewById(R.id.btn_den_next5);
        back4 = findViewById(R.id.btn_den_back4);

        if(page4){
            radioDengueClinicalClass.check(RG_clinicalClassID);
        }

        radioDengueClinicalClass.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                clinicalClass = radioButton.getText().toString();
                RG_clinicalClassID = i;
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageTwo(); }
        });

        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page4 = true;

                if (clinicalClass.equals("")) { page3 = false; radioDengueClinicalClass.setBackgroundResource(R.color.theme_lightest_red); }
                else radioDengueClinicalClass.setBackgroundResource(0);

                if (!page4) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    datefirstvax = etdatefirstvax.getText().toString();
                    datelastvax = etdatelastvax.getText().toString();

                    pageFive();
                }
            }
        });
    }

    public void pageFive() {
        // something
        pageSix();
    }

    public void pageSix() {
        // something
        pageSeven();
    }

    public void pageSeven() {
        // something

        this.radioFinalClassif = findViewById(R.id.radiogroup_den_case_classif);

        if(page7) {
            radioFinalClassif.check(RG_finalClassifID);
        }

        radioFinalClassif.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                finalClassification = radioButton.getText().toString();
                RG_finalClassifID = i;
            }
        });

        this.back6 = findViewById(R.id.btn_den_back6);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageSix(); }
        });

        this.submit = findViewById(R.id.btn_meas_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page7 = true;

                if (finalClassification.length() <= 0) { page7 = page7 & false; radioFinalClassif.setBackgroundResource(R.color.theme_lightest_red); }
                else radioFinalClassif.setBackgroundResource(0);

                if (!page7) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    // finalClassification set during onclick
                    submit(view);
                }
            }
        });
    }

    public void submit(View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_popup_submitcase, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button buttonCancel = popupView.findViewById(R.id.btn_submit_cancel);
        Button buttonConfirm = popupView.findViewById(R.id.btn_submit_confirm);
        buttonCancel.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        buttonConfirm.setOnClickListener(new View.OnClickListener() { @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                //As an example, display the message
                Toast.makeText(view.getContext(), "Please wait for page to load", Toast.LENGTH_SHORT).show();

                setContentView(R.layout.activity_loading);

                TextView tvCaseAddStatus = findViewById(R.id.tv_caseAddStatus);
                ProgressBar loadingpanel = findViewById(R.id.loadingPanel);
                ImageView imgCheck = findViewById(R.id.imgCheckPanel);
                LinearLayout layoutDone = findViewById(R.id.layout_done);
                Button buttonHome = findViewById(R.id.btn_home);
                Button buttonAddCase = findViewById(R.id.btn_addanothercase);

                // CASES
                Case cases = new Case();
                cases.setDiseaseID("DI-0000000000003");
                cases.setReportedBy(user.getUserID());
                cases.setCaseLevel(finalClassification);
                cases.setReportDate(reportdate);
                cases.setInvestigationDate(investigateDate);
                cases.setDateAdmitted(admitdate);
                cases.setDateOnset(onsetdate);
                cases.setReporterName(reporter);
                cases.setReporterContact(null);
                cases.setInvestigatorLab(labselected);
                cases.setInvestigatorName(investigator);
                cases.setInvestigatorContact(investigatorContact);
                cases.setFinalDiagnosis(finaldiagnosis);
                cases.setCRFID(null);

                // PATIENTS
                Patient patientForm = new Patient();
                // TODO: autofill patient
                if (existingpatient) {
                    patientForm = patient;
                } else {
                    patientForm.setPatientID(null);
                    // phone
                    patientForm.setEpiID(null);
                    patientForm.setLastName(lastName);
                    patientForm.setFirstName(firstName);
                    patientForm.setMidName(middleName);
                    patientForm.setCurrHouseStreet(currStreet);
                    patientForm.setCurrBrgy(currBrgy);
                    patientForm.setCurrCity(currCity);
                    patientForm.setPermHouseStreet(permStreet);
                    patientForm.setPermBrgy(permBrgy);
                    patientForm.setPermCity(permCity);
                    patientForm.setSex(sex);
                    patientForm.setBirthdate(birthdate);

                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = formatter.parse(birthdate);
                        patientForm.setAgeNo(Math.toIntExact(ChronoUnit.YEARS.between((Temporal) date, LocalDate.now(ZoneId.of("GMT+8")))));
                    } catch (ParseException e) {
                        Log.d("ParseException", "Got an exception in converting birthdate! " + e);
                        patient.setAgeNo(30);
                    }
                    patientForm.setAgeType("year");
                    patientForm.setAdmitStatus(patientAdmit);
                    patientForm.setCivilStatus(civilstatus);
                    patientForm.setOccupation(occupation);
                    patientForm.setOccuLoc(occuloc);
                    patientForm.setOccuStreet(occuStreet);
                    patientForm.setOccuCity(occuCity);
                    patientForm.setOccuBrgy(occuBrgy);
                    patientForm.setGuardianName(parentCg);
                    patientForm.setGuardianContact(parentCgContact);
                    patientForm.setIndGroup(indigenousgroup);
                    patientForm.setPregWeeks(pregnancy);
                    patientForm.setHCPN(HCPN);
                    patientForm.setILHZ(ILHZ);
                }

                // RISK FACTORS
                RiskFactors riskFactors = new RiskFactors();
                riskFactors.setLSmoking(checkRfL2.isChecked());
                riskFactors.setLAlcoholism(checkRfL3.isChecked());
                riskFactors.setLDrugUse(checkRfL4.isChecked());
                riskFactors.setLPhysicalInactivity(checkRfL5.isChecked());
                if (checkRfL6.isChecked()) {
                    riskFactors.setLOthers(etRfLOthers.getText().toString());
                } else {
                    riskFactors.setLOthers("");
                }
                riskFactors.setCAsthma(checkRfC2.isChecked());
                riskFactors.setCHereditary(checkRfC3.isChecked());
                if (checkRfC4.isChecked()) {
                    riskFactors.setCOthers(etRfCOthers.getText().toString());
                } else {
                    riskFactors.setCOthers("");
                }
                riskFactors.setHDiabetes(checkRfH2.isChecked());
                riskFactors.setHHeartDisease(checkRfH3.isChecked());
                riskFactors.setHHypertension(checkRfH4.isChecked());
                riskFactors.setHObesity(checkRfH5.isChecked());
                if (checkRfH6.isChecked()) {
                    riskFactors.setHOthers(etRfHOthers.getText().toString());
                } else {
                    riskFactors.setHOthers("");
                }
                riskFactors.setOAirPollution(checkRfO2.isChecked());
                riskFactors.setOCleanWater(checkRfO3.isChecked());
                riskFactors.setOFlooding(checkRfO4.isChecked());
                riskFactors.setOHealthFacility(checkRfO5.isChecked());
                riskFactors.setOHealthEdu(checkRfO6.isChecked());
                riskFactors.setOPoverty(checkRfO7.isChecked());
                riskFactors.setOShelter(checkRfO8.isChecked());
                riskFactors.setOWasteMgmt(checkRfO9.isChecked());
                riskFactors.setOVacCoverage(checkRfO10.isChecked());
                if (checkRfO11.isChecked()) {
                    riskFactors.setOOthers(etRfOOthers.getText().toString());
                } else {
                    riskFactors.setOOthers("");
                }

                // CASE DATA
                CaseData caseData = new CaseData();
                // checkSymp1, checkSymp2
                // TODO : Set Case Data

                // prepping form data before sending to retrofit
                formData = new CaseForm();
                formData.setCases(cases);
                formData.setPatient(patientForm);
                formData.setRiskFactors(riskFactors);
                formData.setCaseData(caseData);

                Call<ResponseBody> call = apiClient.APIservice.postMobCRF(formData, "");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.d("postNewCase", "Submitted, " + response.body().string());
                            if (response.code() == 200) {
                                loadingpanel.setVisibility(View.GONE);
                                imgCheck.setVisibility(View.VISIBLE);
                                layoutDone.setVisibility(View.VISIBLE);
                                tvCaseAddStatus.setText("Case successfully submitted!");
                                buttonHome.setOnClickListener(new View.OnClickListener() { @Override
                                public void onClick(View view) {
                                    // pageNine();
                                    startActivity(new Intent(AddcrfDengueActivity.this, HomeActivity.class));
                                }
                                });
                                buttonAddCase.setOnClickListener(new View.OnClickListener() { @Override
                                public void onClick(View view) {
                                    // startActivity(new Intent(AddcrfDengueActivity.this, AddcaseActivity.class));
                                    finish();
                                }
                                });
                            }
                        } catch (IOException e) {
                            Log.d("postNewCase", "IOException? " + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("postNewCase", t.getMessage());
                    }
                });
            }
        });

        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() { @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
