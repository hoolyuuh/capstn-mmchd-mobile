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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.idsr.mobile.models.APIModels.TCLResponse;
import com.idsr.mobile.models.CaseForm;
import com.idsr.mobile.models.Case;
import com.idsr.mobile.models.CaseFormJS;
import com.idsr.mobile.models.Patient;
import com.idsr.mobile.models.RiskFactors;
import com.idsr.mobile.models.CaseData;
import com.idsr.mobile.models.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddcifMeaslesActivity extends AppCompatActivity {
//    private ActivityAddcifMeasles0Binding binding0;
//    private ActivityAddcifMeasles1Binding binding1;
//    private ActivityAddcifMeasles2Binding binding2;
//    private ActivityAddcifMeasles3Binding binding3;
//    private ActivityAddcifMeasles4Binding binding4;
//    private ActivityAddcifMeasles5Binding binding5;
//    private ActivityAddcifMeasles6Binding binding6;
//    private ActivityAddcifMeasles7Binding binding7;
//    private ActivityAddcifMeasles8Binding binding8;
//    private ActivityAddcifMeasles9Binding binding9;

    private Boolean page1 = Boolean.FALSE;
    private Boolean page2 = Boolean.FALSE;
    private Boolean page3 = Boolean.FALSE;
    private Boolean page4 = Boolean.FALSE;
    private Boolean page5 = Boolean.FALSE;
    private Boolean page6 = Boolean.FALSE;
    private Boolean page7 = Boolean.FALSE;
    private Boolean page8 = Boolean.FALSE;
    private Boolean page9 = Boolean.FALSE;
    private Button cancel, next1, back0, next2, back1, next3, back2, next4, back3, next5, back4, next6, back5, next7, back6, next8, back7, next9, back8, submit;

    private APIClient apiClient;
    private User user;
    private Bundle bundle;
    private CaseForm formData;
    private ArrayList<Patient> patientArrayList;
    private Patient patient;
    private boolean existingpatient = false;
    private ArrayList<String> patientnames;
    private ArrayList<User> labUsers;

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
    private RadioGroup radioPatientAdmit;
    private EditText etAdmitdate, etOnsetdate, etReportdate, etReporter;
    private CheckBox checkRfL1, checkRfL2, checkRfL3, checkRfL4, checkRfL5, checkRfL6;
    private CheckBox checkRfC1, checkRfC2, checkRfC3, checkRfC4;
    private CheckBox checkRfH1, checkRfH2, checkRfH3, checkRfH4, checkRfH5, checkRfH6;
    private CheckBox checkRfO1, checkRfO2, checkRfO3, checkRfO4, checkRfO5, checkRfO6, checkRfO7, checkRfO8, checkRfO9, checkRfO10, checkRfO11;
    private EditText etRfLOthers, etRfCOthers, etRfHOthers, etRfOOthers;

    private String patientAdmit = "", admitdate, onsetdate, reportdate, reporter;
    private int riskfactors = 0;

    //    page 3
    private EditText etSymp1Date, etSymp2Date;
    private CheckBox checkSymp1, checkSymp2, checkSymp3, checkSymp4, checkSymp5, checkSymp6, checkSymp7, checkSymp8;
    private EditText etComplications, etSymptoms, etWorkingDiagnosis;

    private String symp1date, symp2date, complications, symptoms, workingdiagnosis;
    // TODO: symptoms initialize, idk how symptoms are stored

    //    page 4
    private RadioGroup radioMeaslesVaccination;
    private LinearLayout constVaccinated, constUnvaccinated;
    private EditText etVaccineMV, etVaccineMR, etVaccineMMR, etVaccineLastDoseDate, etVaccineValidityOthers;
    private RadioGroup radioVaccineValidity, radioVaccineCampaign;
    private CheckBox checkNoVaccReas1, checkNoVaccReas2, checkNoVaccReas3, checkNoVaccReas4, checkNoVaccReas5, checkNoVaccReas6, checkNoVaccReas7, checkNoVaccReas8, checkNoVaccReas9, checkNoVaccReasOthers;
    private EditText etNoVaccReasOther;
    private RadioGroup radioVitA;

    private String vaccinationStatus="", vaccineMV, vaccineMR, vaccineMMR, vaccineLastDoseDate, vaccinationValidity="", vaccineCampaign="", novaccineReasonOther="", vitA="";
    private ArrayList<String> noVaccReasons = new ArrayList<>();

    //    page 5
    private RadioGroup radioTravelhistory, radioRashOnset, radioMeaslesContact, radioRubellaContact, radioRubellaExposure, radioOtherFeverRashes;
    private ConstraintLayout consWithTravelHistory, consConfirmedRubella;
    private EditText etTravelPlace, etTravelDate, etRubellaContactName, etRubellaContactPlace, etRubellaTravelDate, etRubellaExposureOther;

    private String travelHistory="", travelPlace, travelDate, rashOnset="", measlesContact="", rubellaContact="", rubellaContactName, rubellaContactPlace, rubellaContactTravelDate, rubellaExposure="", otherKnownFeverRash="";

    //    page 6
    private RadioGroup radioSourceinfo;
    private String sourceinfo="";

    //    page 7
    private RadioGroup radioOutcome;
    private EditText etDatedied, etFinalDiagnosis;
    private String outcome="", datedied, finaldiagnosis;

    //    page 8
    private RadioGroup radioLabResult;
    private ConstraintLayout consWithLabResult, consNoLabResult;
    private TextView tvLabspecimen, tvLabSelect;
    private Spinner spinnerLabSpecimen, spinnerLabSelect;
    private EditText etCollectdate, etReceivedate, etresultMeasles, etresultRubella, etresultVirus, etresultPRC, etInvestigator, etInvestigContact, etInvestigDate;

    private String labresult="", labspecimen, collectdate, receivedate, resultMeasle, resultRubella, resultVirus, resultPRC, investigator, investigatorContact, investigateDate, labselected, labSelectID;

    //    page 9
    private RadioGroup radioFinalClassif;
    private ImageButton imageDropdown1, imageDropdown2, imageDropdown3, imageDropdown4, imageDropdown5, imageDropdown6, imageDropdown7;
    private TextView textDropdown1, textDropdown2, textDropdown3, textDropdown4, textDropdown5, textDropdown6, textDropdown7;

    private String finalClassification="";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = new APIClient();

        bundle = getIntent().getExtras();
        user = bundle.getParcelable("user");

        Call<ArrayList<User>> call = apiClient.APIservice.getLabUsers();
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response){
                labUsers = response.body();
                Log.d("callGetLabUsers", "labUsers get: " + labUsers.size());
            }
            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("callGetLabUsers", t.getMessage());
            }
        });

//        binding0 = ActivityAddcifMeasles0Binding.inflate(getLayoutInflater());
//        binding1 = ActivityAddcifMeasles1Binding.inflate(getLayoutInflater());
//        binding2 = ActivityAddcifMeasles2Binding.inflate(getLayoutInflater());
//        binding3 = ActivityAddcifMeasles3Binding.inflate(getLayoutInflater());
//        binding4 = ActivityAddcifMeasles4Binding.inflate(getLayoutInflater());
//        binding5 = ActivityAddcifMeasles5Binding.inflate(getLayoutInflater());
//        binding6 = ActivityAddcifMeasles6Binding.inflate(getLayoutInflater());
//        binding7 = ActivityAddcifMeasles7Binding.inflate(getLayoutInflater());
//        binding8 = ActivityAddcifMeasles8Binding.inflate(getLayoutInflater());
//        binding9 = ActivityAddcifMeasles9Binding.inflate(getLayoutInflater());
        pageZero();
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
        setContentView(R.layout.activity_addcif_measles0);

        patientnames = new ArrayList<>();

        ArrayAdapter<String> adapterPatients = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,patientnames);
        this.autocompPatients = (AutoCompleteTextView)findViewById(R.id.autocomp_mea_searchpatient);
        autocompPatients.setThreshold(1);
        autocompPatients.setAdapter(adapterPatients);

        autocompPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                patientInfoFill(patientnames.indexOf(autocompPatients.getText().toString()));
                pageTwo();
            }
        });

        getPatientAutofill(adapterPatients);
        this.next1 = findViewById(R.id.btn_meas_next1);
        this.cancel = findViewById(R.id.btn_meas_cancel);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageOne();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void patientInfoFill(int i) {
        // TODO: get patient info
        patient = patientArrayList.get(i);
        page1 = true;
        existingpatient = true;

        Log.e("patientInfoFill", "Patient Name: " + patientArrayList.get(i).getFirstName());
    }

    public void pageOne() {
        setContentView(R.layout.activity_addcif_measles1);

        this.etLastname = findViewById(R.id.et_mea_lastname);
        this.etFirstname = findViewById(R.id.et_mea_firstname);
        this.etMiddlename = findViewById(R.id.et_mea_middlename);
        this.etBirthdate = findViewById(R.id.et_mea_birthdate);
        this.radioSex = (RadioGroup) findViewById(R.id.radiogroup_sex);
        this.radioPregnancy = (RadioGroup) findViewById(R.id.radiogroup_pregnancy);
        this.etPregantweeks = findViewById(R.id.et_mea_pregweeks);
        // this.etPhone = findViewById(R.id.et_mea_phone);
        this.tvCivilStatus = findViewById(R.id.tv_mea_civilstatus);
        this.spinnerCivilstatus = findViewById(R.id.spinner_mea_civilstatus);
        this.etIndigenousgroup = findViewById(R.id.et_mea_indigenous);

        this.etOccupation = findViewById(R.id.et_mea_occupation);
        this.etOcculoc = findViewById(R.id.et_mea_occuloc);
        this.etOccuStreet = findViewById(R.id.et_mea_occustreet);
        this.tvOccuCity = findViewById(R.id.tv_mea_occucity);
        this.spinnerOccuCity = findViewById(R.id.spinner_mea_occucity);
        this.tvOccuBrgy = findViewById(R.id.tv_mea_occubrgy);
        this.spinnerOccuBrgy = findViewById(R.id.spinner_mea_occubrgy);
        this.etCurrStreet = findViewById(R.id.et_mea_currstreet);
        this.tvCurrCity = findViewById(R.id.tv_mea_currcity);
        this.spinnerCurrCity = findViewById(R.id.spinner_mea_currcity);
        this.tvCurrBrgy = findViewById(R.id.tv_mea_currbrgy);
        this.spinnerCurrBrgy = findViewById(R.id.spinner_mea_currbrgy);
        this.checkboxSameaddress = findViewById(R.id.checkbox_mea_sameaddress);
        this.constPermanentAdd = findViewById(R.id.cons_perminfo);
        this.etPermStreet = findViewById(R.id.et_mea_permstreet);
        this.tvPermCity = findViewById(R.id.tv_mea_permcity);
        this.spinnerPermCity = findViewById(R.id.spinner_mea_permcity);
        this.tvPermBrgy = findViewById(R.id.tv_mea_permbrgy);
        this.spinnerPermBrgy = findViewById(R.id.spinner_mea_permbrgy);

        this.etParentCg = findViewById(R.id.et_mea_parentcg);
        this.etParentCgContact = findViewById(R.id.et_mea_parentcgContact);
        this.etHCPN = findViewById(R.id.et_mea_hcpn);
        this.etILHZ = findViewById(R.id.et_mea_ilhz);

        // TODO: Initialize Data if Backpressed
        if(page1){

        }
//        DATE
        this.etBirthdate.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                            etBirthdate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                    Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                    Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });

//        RADIO
        radioSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                sex = radioButton.getText().toString();
//                Toast.makeText(getBaseContext(), sex, Toast.LENGTH_SHORT).show();
            }
        });

        radioPregnancy.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                pregnancy = radioButton.getText().toString();
                if (pregnancy.equals("Not Pregnant")) {
                    etPregantweeks.setText("");
                    etPregantweeks.setTextIsSelectable(false);
                }
                else etPregantweeks.setTextIsSelectable(true);
            }
        });

        checkboxSameaddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) constPermanentAdd.setVisibility(View.GONE);
                else constPermanentAdd.setVisibility(View.VISIBLE);
                sameCurrPermAddress = b;}
        });

//        DROPDOWN
        ArrayAdapter<CharSequence>adapterCivilStatus=ArrayAdapter.createFromResource(this, R.array.civilstatus, android.R.layout.simple_spinner_item);
        adapterCivilStatus.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCivilstatus.setAdapter(adapterCivilStatus);
        spinnerCivilstatus.setSelection(0, true);
        spinnerCivilstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvCivilStatus.setText(spinnerCivilstatus.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<CharSequence>adapterOccuCity=ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapterOccuCity.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerOccuCity.setAdapter(adapterOccuCity);
        spinnerOccuCity.setSelection(0, true);
        spinnerOccuCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvOccuCity.setText(spinnerOccuCity.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);

                ArrayAdapter<CharSequence>adapterOccuBrgy=ArrayAdapter.createFromResource(spinnerOccuBrgy.getContext(), getBrgy(spinnerOccuCity.getSelectedItem().toString()), android.R.layout.simple_spinner_item);
                adapterOccuBrgy.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinnerOccuBrgy.setAdapter(adapterOccuBrgy);
                spinnerOccuBrgy.setSelection(0, true);
                spinnerOccuBrgy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        tvOccuBrgy.setText(spinnerOccuBrgy.getSelectedItem().toString());
                        ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<CharSequence>adapterCurrCity=ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapterCurrCity.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCurrCity.setAdapter(adapterCurrCity);
        spinnerCurrCity.setSelection(0, true);
        spinnerCurrCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvCurrCity.setText(spinnerCurrCity.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);

                ArrayAdapter<CharSequence>adapterCurrBrgy=ArrayAdapter.createFromResource(spinnerCurrBrgy.getContext(), getBrgy(spinnerCurrCity.getSelectedItem().toString()), android.R.layout.simple_spinner_item);
                adapterCurrBrgy.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinnerCurrBrgy.setAdapter(adapterCurrBrgy);
                spinnerCurrBrgy.setSelection(0, true);
                spinnerCurrBrgy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        tvCurrBrgy.setText(spinnerCurrBrgy.getSelectedItem().toString());
                        ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<CharSequence>adapterPermCity=ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapterPermCity.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerPermCity.setAdapter(adapterPermCity);
        spinnerPermCity.setSelection(0, true);
        spinnerPermCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvPermCity.setText(spinnerPermCity.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);

                ArrayAdapter<CharSequence>adapterPermBrgy=ArrayAdapter.createFromResource(spinnerPermBrgy.getContext(), getBrgy(spinnerPermCity.getSelectedItem().toString()), android.R.layout.simple_spinner_item);
                adapterPermBrgy.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinnerPermBrgy.setAdapter(adapterPermBrgy);
                spinnerPermBrgy.setSelection(0, true);
                spinnerPermBrgy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        tvPermBrgy.setText(spinnerPermBrgy.getSelectedItem().toString());
                        ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        this.next2 = findViewById(R.id.btn_meas_next2);
        this.back0 = findViewById(R.id.btn_meas_back0);
        next2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                page1 = true;

                if (etLastname.getText().toString().length() <= 0) { page1 = page1 & false; etLastname.setBackgroundResource(R.drawable.inputbox_red); }
                else etLastname.setBackgroundResource(R.drawable.inputbox);
                if (etFirstname.getText().toString().length() <= 0) { page1 = page1 & false; etFirstname.setBackgroundResource(R.drawable.inputbox_red); }
                else etFirstname.setBackgroundResource(R.drawable.inputbox);
                if (etMiddlename.getText().toString().length() <= 0) { page1 = page1 & false; etMiddlename.setBackgroundResource(R.drawable.inputbox_red); }
                else etMiddlename.setBackgroundResource(R.drawable.inputbox);
                if (etBirthdate.getText().toString().length() <= 0) { page1 = page1 & false; etBirthdate.setBackgroundResource(R.drawable.inputbox_red); }
                else etBirthdate.setBackgroundResource(R.drawable.inputbox);
                if (sex.length() <= 0) { page1 = page1 & false; radioSex.setBackgroundResource(R.color.theme_lightest_red); }
                else radioSex.setBackgroundResource(0);
                if (pregnancy.length() <= 0 && etPregantweeks.length() <= 0) { page1 = page1 & false; radioPregnancy.setBackgroundResource(R.color.theme_lightest_red); }
                else radioPregnancy.setBackgroundResource(0);
                /* if (etPhone.getText().toString().length() <= 0) { page1 = page1 & false; etPhone.setBackgroundResource(R.drawable.inputbox_red); }
                else etPhone.setBackgroundResource(R.drawable.inputbox);
                */
                if (tvCivilStatus.getText().toString().length() <= 0) { page1 = page1 & false; tvCivilStatus.setBackgroundResource(R.drawable.inputbox_red); tvCivilStatus.setPadding(55,50,55,20); }
                else { tvCivilStatus.setBackgroundResource(R.drawable.inputbox); tvCivilStatus.setPadding(55,50,55,20); }

                if (etOccupation.getText().toString().length() <= 0) { page1 = page1 & false; etOccupation.setBackgroundResource(R.drawable.inputbox_red); }
                else etOccupation.setBackgroundResource(R.drawable.inputbox);
//                if (etOcculoc.getText().toString().length() <= 0) { page1 = page1 & false; etOcculoc.setBackgroundResource(R.drawable.inputbox_red); }
//                else etOcculoc.setBackgroundResource(R.drawable.inputbox);
//                if (etOccuStreet.getText().toString().length() <= 0) { page1 = page1 & false; etOccuStreet.setBackgroundResource(R.drawable.inputbox_red); }
//                else etOccuStreet.setBackgroundResource(R.drawable.inputbox);
//                if (tvOccuCity.getText().toString().length() <= 0) { page1 = page1 & false; tvOccuCity.setBackgroundResource(R.drawable.inputbox_red); tvOccuCity.setPadding(25,50,25,20); }
//                else { tvOccuCity.setBackgroundResource(R.drawable.inputbox); tvOccuCity.setPadding(25,50,25,20); }
//                if (tvOccuBrgy.getText().toString().length() <= 0) { page1 = page1 & false; tvOccuBrgy.setBackgroundResource(R.drawable.inputbox_red); tvOccuBrgy.setPadding(25,50,25,20); }
//                else { tvOccuBrgy.setBackgroundResource(R.drawable.inputbox); tvOccuBrgy.setPadding(25,50,25,20); }

                if (etCurrStreet.getText().toString().length() <= 0) { page1 = page1 & false; etCurrStreet.setBackgroundResource(R.drawable.inputbox_red); }
                else etCurrStreet.setBackgroundResource(R.drawable.inputbox);
                if (tvCurrCity.getText().toString().length() <= 0) { page1 = page1 & false; tvCurrCity.setBackgroundResource(R.drawable.inputbox_red); tvCurrCity.setPadding(25,50,25,20); }
                else { tvCurrCity.setBackgroundResource(R.drawable.inputbox); tvCurrCity.setPadding(25,50,25,20); }
                if (tvCurrBrgy.getText().toString().length() <= 0) { page1 = page1 & false; tvCurrBrgy.setBackgroundResource(R.drawable.inputbox_red); tvCurrBrgy.setPadding(25,50,25,20); }
                else { tvCurrBrgy.setBackgroundResource(R.drawable.inputbox); tvCurrBrgy.setPadding(25,50,25,20); }
                // sameCurrPermAddress not required but red
                if (!sameCurrPermAddress) {
                    if (etPermStreet.getText().toString().length() <= 0) { page1 = page1 & false; etPermStreet.setBackgroundResource(R.drawable.inputbox_red); }
                    else etPermStreet.setBackgroundResource(R.drawable.inputbox);
                    if (tvPermCity.getText().toString().length() <= 0) { page1 = page1 & false; tvPermCity.setBackgroundResource(R.drawable.inputbox_red); tvPermCity.setPadding(55,50,55,20); }
                    else { tvPermCity.setBackgroundResource(R.drawable.inputbox); tvPermCity.setPadding(55,50,55,20); }
                    if (tvPermBrgy.getText().toString().length() <= 0) { page1 = page1 & false; tvPermBrgy.setBackgroundResource(R.drawable.inputbox_red); tvPermBrgy.setPadding(55,50,55,20); }
                    else { tvPermBrgy.setBackgroundResource(R.drawable.inputbox); tvPermBrgy.setPadding(55,50,55,20); }
                }
                if (etParentCg.getText().toString().length() <= 0) { page1 = page1 & false; etParentCg.setBackgroundResource(R.drawable.inputbox_red); }
                else etParentCg.setBackgroundResource(R.drawable.inputbox);
                if (etParentCgContact.getText().toString().length() <= 0) { page1 = page1 & false; etParentCgContact.setBackgroundResource(R.drawable.inputbox_red); }
                else etParentCgContact.setBackgroundResource(R.drawable.inputbox);

                if (!page1) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    lastName = etLastname.getText().toString();
                    firstName = etFirstname.getText().toString();
                    middleName = etMiddlename.getText().toString();
                    birthdate = etBirthdate.getText().toString();
                    // sex & pregnancy above in radio onclick, but preg weeks below
                    if (pregnancy.length()==0) pregnancy = etPregantweeks.getText().toString();
                    // phone = etPhone.getText().toString();
                    civilstatus = tvCivilStatus.getText().toString();
                    indigenousgroup = etIndigenousgroup.getText().toString();
                    occupation = etOccupation.getText().toString();
                    occuloc = etOcculoc.getText().toString();
                    occuStreet = etOccuStreet.getText().toString();
                    occuCity = tvOccuCity.getText().toString();
                    occuBrgy = tvOccuBrgy.getText().toString();
                    currStreet = etCurrStreet.getText().toString();
                    currCity = tvCurrCity.getText().toString();
                    currBrgy = tvCurrBrgy.getText().toString();
                    // sameCurrPermAddress set in checkbox onclick
                    permStreet = etPermStreet.getText().toString();
                    permCity = tvPermCity.getText().toString();
                    permBrgy = tvPermBrgy.getText().toString();
                    parentCg = etParentCg.getText().toString();
                    parentCgContact = etParentCgContact.getText().toString();
                    HCPN = etHCPN.getText().toString();
                    ILHZ = etILHZ.getText().toString();

                    pageTwo();
                }
            }
        });
        back0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageZero();
            }
        });
    }

    public void pageTwo() {
        setContentView(R.layout.activity_addcif_measles2);

        this.radioPatientAdmit = findViewById(R.id.radiogroup_patientadmit);
        this.etAdmitdate = findViewById(R.id.et_mea_admitDate);
        this.etOnsetdate = findViewById(R.id.et_mea_onsetdate);
        this.etReportdate = findViewById(R.id.et_mea_reportdate);
        this.etReporter = findViewById(R.id.et_mea_reporter);

        this.checkRfL1 = findViewById(R.id.checkbox_mea_rfL1);
        this.checkRfL2 = findViewById(R.id.checkbox_mea_rfL2);
        this.checkRfL3 = findViewById(R.id.checkbox_mea_rfL3);
        this.checkRfL4 = findViewById(R.id.checkbox_mea_rfL4);
        this.checkRfL5 = findViewById(R.id.checkbox_mea_rfL5);
        this.checkRfL6 = findViewById(R.id.checkbox_mea_rfL6);
        this.etRfLOthers = findViewById(R.id.et_mea_rfLOthers);
        this.checkRfC1 = findViewById(R.id.checkbox_mea_rfC1);
        this.checkRfC2 = findViewById(R.id.checkbox_mea_rfC2);
        this.checkRfC3 = findViewById(R.id.checkbox_mea_rfC3);
        this.checkRfC4 = findViewById(R.id.checkbox_mea_rfC4);
        this.etRfCOthers = findViewById(R.id.et_mea_rfCOthers);
        this.checkRfH1 = findViewById(R.id.checkbox_mea_rfH1);
        this.checkRfH2 = findViewById(R.id.checkbox_mea_rfH2);
        this.checkRfH3 = findViewById(R.id.checkbox_mea_rfH3);
        this.checkRfH4 = findViewById(R.id.checkbox_mea_rfH4);
        this.checkRfH5 = findViewById(R.id.checkbox_mea_rfH5);
        this.checkRfH6 = findViewById(R.id.checkbox_mea_rfH6);
        this.etRfHOthers = findViewById(R.id.et_mea_rfHOthers);
        this.checkRfO1 = findViewById(R.id.checkbox_mea_rfO1);
        this.checkRfO2 = findViewById(R.id.checkbox_mea_rfO2);
        this.checkRfO3 = findViewById(R.id.checkbox_mea_rfO3);
        this.checkRfO4 = findViewById(R.id.checkbox_mea_rfO4);
        this.checkRfO5 = findViewById(R.id.checkbox_mea_rfO5);
        this.checkRfO6 = findViewById(R.id.checkbox_mea_rfO6);
        this.checkRfO7 = findViewById(R.id.checkbox_mea_rfO7);
        this.checkRfO8 = findViewById(R.id.checkbox_mea_rfO8);
        this.checkRfO9 = findViewById(R.id.checkbox_mea_rfO9);
        this.checkRfO10 = findViewById(R.id.checkbox_mea_rfO10);
        this.checkRfO11 = findViewById(R.id.checkbox_mea_rfO11);
        this.etRfHOthers = findViewById(R.id.et_mea_rfOOthers);

        // TODO: Initialize Data if Backpressed
        if(page2){
            etAdmitdate.setText(admitdate);
            etOnsetdate.setText(onsetdate);
            etReportdate.setText(reportdate);
            etReporter.setText(reporter);
        }

        radioPatientAdmit.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                patientAdmit = radioButton.getText().toString();
                if (patientAdmit.equals("Yes")) etAdmitdate.setVisibility(View.VISIBLE);
                else etAdmitdate.setVisibility(View.INVISIBLE);
            }
        });

        etAdmitdate.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etReportdate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
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
                    pageThree();
                }
            }
        });

    }

    public void pageThree() {
        setContentView(R.layout.activity_addcif_measles3);

        this.checkSymp1 = findViewById(R.id.checkbox_mea_symp1);
        this.checkSymp2 = findViewById(R.id.checkbox_mea_symp2);
        this.checkSymp3 = findViewById(R.id.checkbox_mea_symp3);
        this.checkSymp4 = findViewById(R.id.checkbox_mea_symp4);
        this.checkSymp5 = findViewById(R.id.checkbox_mea_symp5);
        this.checkSymp6 = findViewById(R.id.checkbox_mea_symp6);
        this.checkSymp7 = findViewById(R.id.checkbox_mea_symp7);
        this.checkSymp8 = findViewById(R.id.checkbox_mea_symp8);
        this.etSymp1Date = findViewById(R.id.checkbox_mea_symp1date);
        this.etSymp2Date = findViewById(R.id.checkbox_mea_symp2date);
        this.etComplications = findViewById(R.id.et_mea_clinicalcomplications);
        this.etSymptoms = findViewById(R.id.et_mea_othersymptoms);
        this.etWorkingDiagnosis = findViewById(R.id.et_mea_workingdiagnosis);

        // TODO: Initialize Data if Backpressed
        if(page3){

        }

        checkSymp1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  { @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) etSymp1Date.setVisibility(View.VISIBLE); else etSymp1Date.setVisibility(View.INVISIBLE); } });
        checkSymp2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  { @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) etSymp2Date.setVisibility(View.VISIBLE); else etSymp2Date.setVisibility(View.INVISIBLE); } });

        etSymp1Date.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etSymp1Date.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });
        etSymp2Date.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etSymp2Date.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });

        this.next4 = findViewById(R.id.btn_meas_next4);
        this.back2 = findViewById(R.id.btn_meas_back2);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageTwo(); }
        });
        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page3 = true;

                // TODO: idk how to get symptoms
                symp1date = etSymp1Date.getText().toString();
                symp2date = etSymp2Date.getText().toString();
                complications = etComplications.getText().toString();
                symptoms = etSymptoms.getText().toString();
                workingdiagnosis = etWorkingDiagnosis.getText().toString();

                // no validation needed, since no required
                page3 = true;
                pageFour();
            }
        });
    }

    public void pageFour() {
        setContentView(R.layout.activity_addcif_measles4);

        this.radioMeaslesVaccination = (RadioGroup) findViewById(R.id.radiogroup_measlesvaccination);
        this.constVaccinated = findViewById(R.id.cons_mea_vaccinated);
        this.constUnvaccinated = findViewById(R.id.cons_mea_unvaccinated);
        this.etVaccineMV = findViewById(R.id.et_mea_vaccinedoseMV);
        this.etVaccineMR = findViewById(R.id.et_mea_vaccinedoseMR);
        this.etVaccineMMR = findViewById(R.id.et_mea_vaccinedoseMMR);
        this.etVaccineLastDoseDate = findViewById(R.id.et_mea_vaccinelastdosedate);
        this.radioVaccineValidity = findViewById(R.id.radiogroup_vaccinevalidation);
        this.etVaccineValidityOthers = findViewById(R.id.et_vaccinevalidationOther);
        this.radioVaccineCampaign = findViewById(R.id.radiogroup_vaccinecampaign);

        this.checkNoVaccReas1 = findViewById(R.id.checkbox_novaccine1);
        this.checkNoVaccReas2 = findViewById(R.id.checkbox_novaccine2);
        this.checkNoVaccReas3 = findViewById(R.id.checkbox_novaccine3);
        this.checkNoVaccReas4 = findViewById(R.id.checkbox_novaccine4);
        this.checkNoVaccReas5 = findViewById(R.id.checkbox_novaccine5);
        this.checkNoVaccReas6 = findViewById(R.id.checkbox_novaccine6);
        this.checkNoVaccReas7 = findViewById(R.id.checkbox_novaccine7);
        this.checkNoVaccReas8 = findViewById(R.id.checkbox_novaccine8);
        this.checkNoVaccReas9 = findViewById(R.id.checkbox_novaccine9);
        this.checkNoVaccReasOthers = findViewById(R.id.checkbox_novaccineOther);
        this.etNoVaccReasOther = findViewById(R.id.et_novaccineothers);
        this.radioVitA = findViewById(R.id.radiogroup_vitA);

        // TODO: Initialize Data if Backpressed
        if(page4){

        }

        radioMeaslesVaccination.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                vaccinationStatus = radioButton.getText().toString();
                if (radioButton.getText().toString().equals("Yes")) {
                    constVaccinated.setVisibility(View.VISIBLE);
                    constUnvaccinated.setVisibility(View.GONE);}
                else if (radioButton.getText().toString().equals("No")) {
                    constVaccinated.setVisibility(View.GONE);
                    constUnvaccinated.setVisibility(View.VISIBLE);}
            }
        });

        etVaccineLastDoseDate.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() { @Override
                    public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etVaccineLastDoseDate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });

        radioVaccineValidity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                vaccinationValidity = radioButton.getText().toString();
                if (radioButton.getText().toString().equals("Other:")) etVaccineValidityOthers.setVisibility(View.VISIBLE);
                else etVaccineValidityOthers.setVisibility(View.INVISIBLE);
            }
        });

        radioVaccineCampaign.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                vaccineCampaign = radioButton.getText().toString();
            }
        });

        checkNoVaccReasOthers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  { @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) etNoVaccReasOther.setVisibility(View.VISIBLE);
                else etNoVaccReasOther.setVisibility(View.INVISIBLE);
            }
        });

        radioVitA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                vitA = radioButton.getText().toString();}
        });

        this.next5 = findViewById(R.id.btn_meas_next5);
        this.back3 = findViewById(R.id.btn_meas_back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageThree(); }
        });
        next5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page4 = true;

                if (vaccinationStatus.length() <= 0) { page4 = page4 & false; radioMeaslesVaccination.setBackgroundResource(R.color.theme_lightest_red); }
                else radioMeaslesVaccination.setBackgroundResource(0);
                if (vaccinationStatus.equals("Yes")) {
                    if (etVaccineLastDoseDate.getText().toString().length() <= 0) { page4 = page4 & false; etVaccineLastDoseDate.setBackgroundResource(R.drawable.inputbox_red); }
                    else etVaccineLastDoseDate.setBackgroundResource(R.drawable.inputbox);
                    if (vaccinationValidity.length() <= 0) { page4 = page4 & false; radioVaccineValidity.setBackgroundResource(R.color.theme_lightest_red); }
                    else radioVaccineValidity.setBackgroundResource(0);
                    if (vaccinationValidity.equals("Other:")) {
                        if (etVaccineValidityOthers.getText().toString().length() <= 0) { page4 = page4 & false; etVaccineValidityOthers.setBackgroundResource(R.color.theme_lightest_red); }
                        else etVaccineValidityOthers.setBackgroundResource(0);
                    }
                    if (vaccineCampaign.length() <= 0) { page4 = page4 & false; radioVaccineCampaign.setBackgroundResource(R.color.theme_lightest_red); }
                    else radioVaccineCampaign.setBackgroundResource(0);
                }
                else if (vaccinationStatus.equals("No")) {
                    if (checkNoVaccReasOthers.isChecked()) {
                        if (etNoVaccReasOther.getText().toString().length() <= 0) { page4 = page4 & false; etNoVaccReasOther.setBackgroundResource(R.color.theme_lightest_red); }
                        else etNoVaccReasOther.setBackgroundResource(0);
                    }
                }
                if (vitA.length() <= 0) { page4 = page4 & false; radioVitA.setBackgroundResource(R.color.theme_lightest_red); }
                else radioVitA.setBackgroundResource(0);

                if (!page4) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    // set in onclick: vaccinationStatus, vaccinevalidity, vaccineCampaign
                    vaccineMV = etVaccineMV.getText().toString();
                    vaccineMR = etVaccineMR.getText().toString();
                    vaccineMMR = etVaccineMMR.getText().toString();
                    vaccineLastDoseDate = etVaccineLastDoseDate.getText().toString();
                    vaccinationValidity = etVaccineValidityOthers.getText().toString();
                    noVaccReasons.add(Boolean.toString(checkNoVaccReas1.isChecked()));
                    noVaccReasons.add(Boolean.toString(checkNoVaccReas2.isChecked()));
                    noVaccReasons.add(Boolean.toString(checkNoVaccReas3.isChecked()));
                    noVaccReasons.add(Boolean.toString(checkNoVaccReas4.isChecked()));
                    noVaccReasons.add(Boolean.toString(checkNoVaccReas5.isChecked()));
                    noVaccReasons.add(Boolean.toString(checkNoVaccReas6.isChecked()));
                    noVaccReasons.add(Boolean.toString(checkNoVaccReas7.isChecked()));
                    noVaccReasons.add(Boolean.toString(checkNoVaccReas8.isChecked()));
                    noVaccReasons.add(Boolean.toString(checkNoVaccReas9.isChecked()));
                    noVaccReasons.add(Boolean.toString(checkNoVaccReasOthers.isChecked()));
                    novaccineReasonOther = etNoVaccReasOther.getText().toString();
                    // vitA also set onclick

                    pageFive();
                }
            }
        });
    }

    public void pageFive() {
        setContentView(R.layout.activity_addcif_measles5);

        this.radioTravelhistory = findViewById(R.id.radiogroup_mea_travelhistory);
        this.consWithTravelHistory = findViewById(R.id.cons_mea_withtravelhistory);
        this.etTravelPlace = findViewById(R.id.et_mea_travelplace);
        this.etTravelDate = findViewById(R.id.et_mea_traveldate);
        this.radioRashOnset = findViewById(R.id.radiogroup_rashonsetdate);
        this.radioMeaslesContact = findViewById(R.id.radiogroup_measlescontact);
        this.radioRubellaContact = findViewById(R.id.radiogroup_rubellacontact);
        this.consConfirmedRubella = findViewById(R.id.cons_mea_confirmedrubella);
        this.etRubellaContactName = findViewById(R.id.et_rubella_contactname);
        this.etRubellaContactPlace = findViewById(R.id.et_rubella_contactplace);
        this.etRubellaTravelDate = findViewById(R.id.et_rubella_traveldate);
        this.radioRubellaExposure = findViewById(R.id.radiogroup_rubellaExposure);
        this.etRubellaExposureOther = findViewById(R.id.et_rubellaexposureOther);
        this.radioOtherFeverRashes = findViewById(R.id.radiogroup_otherfeverrashes);

        // TODO: Initialize Data if Backpressed
        if(page5){

        }

        radioTravelhistory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                if (radioButton.getText().toString().equals("Yes")) consWithTravelHistory.setVisibility(View.VISIBLE);
                else consWithTravelHistory.setVisibility(View.GONE);
                travelHistory = radioButton.getText().toString();
            }
        });
        radioRashOnset.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                rashOnset = radioButton.getText().toString();}
        });
        radioMeaslesContact.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                measlesContact = radioButton.getText().toString();}
        });
        radioRubellaContact.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                if (radioButton.getText().toString().equals("Yes")) consConfirmedRubella.setVisibility(View.VISIBLE);
                else consConfirmedRubella.setVisibility(View.GONE);
                rubellaContact = radioButton.getText().toString();}
        });
        radioRubellaExposure.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                if (radioButton.getText().toString().equals("Other:")) etRubellaExposureOther.setVisibility(View.VISIBLE);
                else etRubellaExposureOther.setVisibility(View.GONE);
                rubellaExposure = radioButton.getText().toString();}
        });
        radioOtherFeverRashes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                otherKnownFeverRash = radioButton.getText().toString();}
        });

        this.etTravelPlace.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etTravelPlace.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                    Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                    Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        this.etTravelDate.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etTravelDate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                    Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                    Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });

        this.next6 = findViewById(R.id.btn_meas_next6);
        this.back4 = findViewById(R.id.btn_meas_back4);
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageFour(); }
        });
        next6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page5 = true;

                if (travelHistory.length() <= 0) { page5 = page5 & false; radioTravelhistory.setBackgroundResource(R.color.theme_lightest_red); }
                else radioTravelhistory.setBackgroundResource(0);
                if (Objects.equals(travelHistory, "Yes")) {
                    if (etTravelPlace.getText().toString().length() <= 0) { page5 = page5 & false; etTravelPlace.setBackgroundResource(R.drawable.inputbox_red); }
                    else etTravelPlace.setBackgroundResource(R.drawable.inputbox);
                    if (etTravelDate.getText().toString().length() <= 0) { page5 = page5 & false; etTravelDate.setBackgroundResource(R.drawable.inputbox_red); }
                    else etTravelDate.setBackgroundResource(R.drawable.inputbox);
                    if (rashOnset.length() <= 0) { page5 = page5 & false; radioRashOnset.setBackgroundResource(R.color.theme_lightest_red); }
                    else radioRashOnset.setBackgroundResource(0);
                }
                if (measlesContact.length() <= 0) { page5 = page5 & false; radioMeaslesContact.setBackgroundResource(R.color.theme_lightest_red); }
                else radioMeaslesContact.setBackgroundResource(0);
                if (rubellaContact.length() <= 0) { page5 = page5 & false; radioRubellaContact.setBackgroundResource(R.color.theme_lightest_red); }
                else radioRubellaContact.setBackgroundResource(0);
                if (Objects.equals(rubellaContact, "Yes")) {
                    if (etRubellaContactName.getText().toString().length() <= 0) { page5 = page5 & false; etRubellaContactName.setBackgroundResource(R.drawable.inputbox_red); }
                    else etRubellaContactName.setBackgroundResource(R.drawable.inputbox);
                    if (etRubellaContactPlace.getText().toString().length() <= 0) { page5 = page5 & false; etRubellaContactPlace.setBackgroundResource(R.drawable.inputbox_red); }
                    else etRubellaContactPlace.setBackgroundResource(R.drawable.inputbox);
                    if (etRubellaTravelDate.getText().toString().length() <= 0) { page5 = page5 & false; etRubellaTravelDate.setBackgroundResource(R.drawable.inputbox_red); }
                    else etRubellaTravelDate.setBackgroundResource(R.drawable.inputbox);
                    if (rubellaExposure.length() <= 0) { page5 = page5 & false; radioRubellaExposure.setBackgroundResource(R.color.theme_lightest_red); }
                    else radioRubellaExposure.setBackgroundResource(0);
                    if (Objects.equals(rubellaExposure, "Other:")) {
                        if (etRubellaExposureOther.getText().toString().length() <= 0) { page5 = page5 & false; etRubellaExposureOther.setBackgroundResource(R.drawable.inputbox_red); }
                        else etRubellaExposureOther.setBackgroundResource(R.drawable.inputbox);
                    }
                }
                if (otherKnownFeverRash.length() <= 0) { page5 = page5 & false; radioOtherFeverRashes.setBackgroundResource(R.color.theme_lightest_red); }
                else radioOtherFeverRashes.setBackgroundResource(0);

                if (!page5) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    // all set in onclick: travelHistory, rashOnset, measlesContact, rubellaContact, rubellaExposure, otherKnownFeverRash
                    travelPlace = etTravelPlace.getText().toString();
                    travelDate = etTravelDate.getText().toString();
                    rubellaContactName = etRubellaContactName.getText().toString();
                    rubellaContactPlace = etRubellaContactPlace.getText().toString();
                    rubellaContactTravelDate = etRubellaTravelDate.getText().toString();

                    pageSix();
                }
            }
        });
    }

    public void pageSix() {
        setContentView(R.layout.activity_addcif_measles6);

        this.radioSourceinfo = findViewById(R.id.radiogroup_mea_sourceinfo);

        // TODO: Initialize Data if Backpressed
        if(page6){

        }

        radioSourceinfo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                sourceinfo = radioButton.getText().toString();}
        });

        this.next7 = findViewById(R.id.btn_meas_next7);
        this.back5 = findViewById(R.id.btn_meas_back5);
        back5.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View view) { pageFive(); }
        });
        next7.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View view) {
                page6 = true;

                if (sourceinfo.length() <= 0) { page6 = page6 & false; radioSourceinfo.setBackgroundResource(R.color.theme_lightest_red); }
                else radioSourceinfo.setBackgroundResource(0);

                if (!page6) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    // sourceinfo set in onclick
                    pageSeven();
                }
            }
        });
    }

    public void pageSeven() {
        setContentView(R.layout.activity_addcif_measles7);

        this.radioOutcome = findViewById(R.id.radiogroup_mea_outcome);
        this.etDatedied = findViewById(R.id.et_mea_datedied);
        this.etFinalDiagnosis = findViewById(R.id.et_mea_finaldiagnosis);

        // TODO: Initialize Data if Backpressed
        if(page7){

        }

        radioOutcome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                outcome = radioButton.getText().toString();
                if (outcome.equals("Alive")) etDatedied.setVisibility(View.INVISIBLE);
                else etDatedied.setVisibility(View.VISIBLE);}
        });

        etDatedied.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etDatedied.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });

        this.next8 = findViewById(R.id.btn_meas_next8);
        this.back6 = findViewById(R.id.btn_meas_back6);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageSix(); }
        });
        next8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page7 = true;

                if (outcome.length() <= 0) { page7 = page7 & false; radioOutcome.setBackgroundResource(R.color.theme_lightest_red); }
                else radioOutcome.setBackgroundResource(0);
                if (Objects.equals(outcome, "Dead")) {
                    if (etDatedied.getText().toString().length() <= 0) { page7 = page7 & false; etDatedied.setBackgroundResource(R.drawable.inputbox_red); }
                    else etDatedied.setBackgroundResource(R.drawable.inputbox);
                }
                if (etFinalDiagnosis.getText().toString().length() <= 0) { page7 = page7 & false; etFinalDiagnosis.setBackgroundResource(R.drawable.inputbox_red); }
                else etFinalDiagnosis.setBackgroundResource(R.drawable.inputbox);

                if (!page7) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    // outcome is set in onclick
                    datedied = etDatedied.getText().toString();
                    finaldiagnosis = etFinalDiagnosis.getText().toString();

                    pageEight();
                }
            }
        });
    }

    public void pageEight() {
        setContentView(R.layout.activity_addcif_measles8);

        this.radioLabResult = findViewById(R.id.radiogroup_mea_labresult);
        this.consWithLabResult = findViewById(R.id.cons_mea_withlabresult);
        this.consNoLabResult = findViewById(R.id.cons_mea_nolabresult);
        this.tvLabspecimen = findViewById(R.id.tv_mea_labspecimen);
        this.spinnerLabSpecimen = findViewById(R.id.spinner_mea_labspecimen);
        this.etCollectdate = findViewById(R.id.et_mea_collectdate);
        this.etReceivedate = findViewById(R.id.et_mea_receivedate);
        this.etresultMeasles = findViewById(R.id.et_meaaslesresultIgm);
        this.etresultRubella = findViewById(R.id.et_rubellaresultIgm);
        this.etresultVirus = findViewById(R.id.et_meaaslesresultVirusIsolation);
        this.etresultPRC = findViewById(R.id.et_meaaslesresultPRC);
        this.etInvestigator = findViewById(R.id.et_mea_resultInvestigator);
        this.etInvestigContact = findViewById(R.id.et_mea_resultInvestigatorContact);
        this.etInvestigDate = findViewById(R.id.et_mea_resultInvestiDate);
        this.tvLabSelect = findViewById(R.id.tv_mea_labselect);
        this.spinnerLabSelect = findViewById(R.id.spinner_mea_labselect);

        // TODO: Initialize Data if Backpressed
        if(page8){

        }

        radioLabResult.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                labresult = radioButton.getText().toString();
                if (labresult.equals("Yes")) {consWithLabResult.setVisibility(View.VISIBLE); consNoLabResult.setVisibility(View.GONE);}
                else if (labresult.equals("No")) {consWithLabResult.setVisibility(View.GONE); consNoLabResult.setVisibility(View.VISIBLE);}
                else if (labresult.equals("Processing")) {consWithLabResult.setVisibility(View.GONE); consNoLabResult.setVisibility(View.GONE);}
            }
        });

        etCollectdate.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etCollectdate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etReceivedate.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etReceivedate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etInvestigDate.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etInvestigDate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });

        ArrayAdapter<CharSequence>adapterLabSpecimen=ArrayAdapter.createFromResource(this, R.array.measLabSpecimen, android.R.layout.simple_spinner_item);
        adapterLabSpecimen.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLabSpecimen.setAdapter(adapterLabSpecimen);
        spinnerLabSpecimen.setSelection(0, true);
        spinnerLabSpecimen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvLabspecimen.setText(spinnerLabSpecimen.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayList<String> labUserNames = new ArrayList<>();
        for (int i = 0; i < labUsers.size(); i++) {
            labUserNames.add(labUsers.get(i).getDruName());
        }
        ArrayAdapter<String> adapterLabSelection = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labUserNames);
        spinnerLabSelect.setAdapter(adapterLabSelection);
        spinnerLabSelect.setSelection(0, true);
        spinnerLabSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvLabSelect.setText(spinnerLabSelect.getSelectedItem().toString());
                labSelectID = labUsers.get(i).getUserID();
                Log.d("onLabUserSelect", "LabUser: " + labUsers.get(i).getUserID() + "-" + labUsers.get(i).getDruName());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        this.next9 = findViewById(R.id.btn_meas_next9);
        this.back7 = findViewById(R.id.btn_meas_back7);
        back7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageSeven(); }
        });
        next9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page8 = true;

                if (labresult.length() <= 0) { page8 = page8 & false; radioLabResult.setBackgroundResource(R.color.theme_lightest_red); }
                else radioLabResult.setBackgroundResource(0);
                if (Objects.equals(labresult, "No")) {
                    if (tvLabSelect.getText().toString().length() <= 0) { page8 = page8 & false; tvLabSelect.setBackgroundResource(R.drawable.inputbox_red); tvLabSelect.setPadding(55,50,55,20); }
                    else { tvLabSelect.setBackgroundResource(R.drawable.inputbox); tvLabSelect.setPadding(55,50,55,20); }
                }
                else if (Objects.equals(labresult, "Yes")) {
                    if (tvLabspecimen.getText().toString().length() <= 0) { page8 = page8 & false; tvLabspecimen.setBackgroundResource(R.drawable.inputbox_red); tvLabSelect.setPadding(55,50,55,20); }
                    else { tvLabspecimen.setBackgroundResource(R.drawable.inputbox); tvLabspecimen.setPadding(55,50,55,20); }
                    if (etCollectdate.getText().toString().length() <= 0) { page8 = page8 & false; etCollectdate.setBackgroundResource(R.drawable.inputbox_red); }
                    else etCollectdate.setBackgroundResource(R.drawable.inputbox);
                    if (etReceivedate.getText().toString().length() <= 0) { page8 = page8 & false; etReceivedate.setBackgroundResource(R.drawable.inputbox_red); }
                    else etReceivedate.setBackgroundResource(R.drawable.inputbox);
                    if (etresultMeasles.getText().toString().length() <= 0) { page8 = page8 & false; etresultMeasles.setBackgroundResource(R.drawable.inputbox_red); }
                    else etresultMeasles.setBackgroundResource(R.drawable.inputbox);
                    if (etresultRubella.getText().toString().length() <= 0) { page8 = page8 & false; etresultRubella.setBackgroundResource(R.drawable.inputbox_red); }
                    else etresultRubella.setBackgroundResource(R.drawable.inputbox);
                    if (etresultVirus.getText().toString().length() <= 0) { page8 = page8 & false; etresultVirus.setBackgroundResource(R.drawable.inputbox_red); }
                    else etresultVirus.setBackgroundResource(R.drawable.inputbox);
                    if (etresultPRC.getText().toString().length() <= 0) { page8 = page8 & false; etresultPRC.setBackgroundResource(R.drawable.inputbox_red); }
                    else etresultPRC.setBackgroundResource(R.drawable.inputbox);
                    if (etInvestigator.getText().toString().length() <= 0) { page8 = page8 & false; etInvestigator.setBackgroundResource(R.drawable.inputbox_red); }
                    else etInvestigator.setBackgroundResource(R.drawable.inputbox);
                    if (etInvestigContact.getText().toString().length() <= 0) { page8 = page8 & false; etInvestigContact.setBackgroundResource(R.drawable.inputbox_red); }
                    else etInvestigContact.setBackgroundResource(R.drawable.inputbox);
                    if (etInvestigDate.getText().toString().length() <= 0) { page8 = page8 & false; etInvestigDate.setBackgroundResource(R.drawable.inputbox_red); }
                    else etInvestigDate.setBackgroundResource(R.drawable.inputbox);
                }

                if (!page8) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    // set in onclick: labresult
                    labspecimen = tvLabspecimen.getText().toString();
                    collectdate = etCollectdate.getText().toString();
                    receivedate = etReceivedate.getText().toString();
                    resultMeasle = etresultMeasles.getText().toString();
                    resultRubella = etresultRubella.getText().toString();
                    resultVirus = etresultVirus.getText().toString();
                    resultPRC = etresultPRC.getText().toString();
                    investigator = etInvestigator.getText().toString();
                    investigatorContact = etInvestigContact.getText().toString();
                    investigateDate = etInvestigDate.getText().toString();
                    labselected = tvLabSelect.getText().toString();

                    pageNine();
                }
            }
        });
    }

    public void pageNine() {
        setContentView(R.layout.activity_addcif_measles9);

        // radiogroup & dropdown imgbutton
        this.radioFinalClassif = findViewById(R.id.radiogroup_mea_finalclassif);
        this.imageDropdown1 = findViewById(R.id.image_mea_expand1);
        this.imageDropdown2 = findViewById(R.id.image_mea_expand2);
        this.imageDropdown3 = findViewById(R.id.image_mea_expand3);
        this.imageDropdown4 = findViewById(R.id.image_mea_expand4);
        this.imageDropdown5 = findViewById(R.id.image_mea_expand5);
        this.imageDropdown6 = findViewById(R.id.image_mea_expand6);
        this.imageDropdown7 = findViewById(R.id.image_mea_expand7);
        this.textDropdown1 = findViewById(R.id.tv_mea_expand1);
        this.textDropdown2 = findViewById(R.id.tv_mea_expand2);
        this.textDropdown3 = findViewById(R.id.tv_mea_expand3);
        this.textDropdown4 = findViewById(R.id.tv_mea_expand4);
        this.textDropdown5 = findViewById(R.id.tv_mea_expand5);
        this.textDropdown6 = findViewById(R.id.tv_mea_expand6);
        this.textDropdown7 = findViewById(R.id.tv_mea_expand7);

        imageDropdown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textDropdown1.getVisibility() == View.VISIBLE) {
//                    TransitionManager.beginDelayedTransition((ViewGroup) lineargroup1, new AutoTransition());
                    textDropdown1.setVisibility(View.GONE);
                    imageDropdown1.setBackgroundResource(R.drawable.arrow_down);
                }
                else {
                    textDropdown1.setVisibility(View.VISIBLE);
                    imageDropdown1.setBackgroundResource(R.drawable.arrow_up);
                }
            }
        });
        imageDropdown2.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View view) {
                if (textDropdown2.getVisibility() == View.VISIBLE) { textDropdown2.setVisibility(View.GONE); imageDropdown2.setBackgroundResource(R.drawable.arrow_down); }
                else { textDropdown2.setVisibility(View.VISIBLE); imageDropdown2.setBackgroundResource(R.drawable.arrow_up); }
        } });
        imageDropdown3.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            if (textDropdown3.getVisibility() == View.VISIBLE) { textDropdown3.setVisibility(View.GONE); imageDropdown3.setBackgroundResource(R.drawable.arrow_down); }
            else { textDropdown3.setVisibility(View.VISIBLE); imageDropdown3.setBackgroundResource(R.drawable.arrow_up); }
        } });
        imageDropdown4.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            if (textDropdown4.getVisibility() == View.VISIBLE) { textDropdown4.setVisibility(View.GONE); imageDropdown4.setBackgroundResource(R.drawable.arrow_down); }
            else { textDropdown4.setVisibility(View.VISIBLE); imageDropdown4.setBackgroundResource(R.drawable.arrow_up); }
        } });
        imageDropdown5.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            if (textDropdown5.getVisibility() == View.VISIBLE) { textDropdown5.setVisibility(View.GONE); imageDropdown5.setBackgroundResource(R.drawable.arrow_down); }
            else { textDropdown5.setVisibility(View.VISIBLE); imageDropdown5.setBackgroundResource(R.drawable.arrow_up); }
        } });
        imageDropdown6.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            if (textDropdown6.getVisibility() == View.VISIBLE) { textDropdown6.setVisibility(View.GONE); imageDropdown6.setBackgroundResource(R.drawable.arrow_down); }
            else { textDropdown6.setVisibility(View.VISIBLE); imageDropdown6.setBackgroundResource(R.drawable.arrow_up); }
        } });
        imageDropdown7.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            if (textDropdown7.getVisibility() == View.VISIBLE) { textDropdown7.setVisibility(View.GONE); imageDropdown7.setBackgroundResource(R.drawable.arrow_down); }
            else { textDropdown7.setVisibility(View.VISIBLE); imageDropdown7.setBackgroundResource(R.drawable.arrow_up); }
        } });

        radioFinalClassif.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                finalClassification = radioButton.getText().toString();
            }
        });

        this.submit = findViewById(R.id.btn_meas_submit);
        this.back8 = findViewById(R.id.btn_meas_back8);
        back8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageEight(); }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page9 = true;

                if (finalClassification.length() <= 0) { page9 = page9 & false; radioFinalClassif.setBackgroundResource(R.color.theme_lightest_red); }
                else radioFinalClassif.setBackgroundResource(0);

                if (!page9) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
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
                cases.setDiseaseID("DI-0000000000000");
                cases.setReportedBy(user.getUserID());
                cases.setCaseLevel(finalClassification);
                cases.setReportDate(reportdate);
                cases.setInvestigationDate(investigateDate);
                cases.setDateAdmitted(admitdate);
                cases.setDateOnset(onsetdate);
                cases.setReporterName(reporter);
                cases.setReporterContact(null);
                cases.setInvestigatorLab(labSelectID);
                cases.setInvestigatorName(labselected);
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
                        patientForm.setAgeNo(Math.toIntExact(ChronoUnit.YEARS.between(date.toInstant().atZone(ZoneId.of("GMT+8")).toLocalDate(), LocalDate.now(ZoneId.of("GMT+8")))));
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
                caseData.setPatientAdmitted(patientAdmit);
                caseData.setSympFever(symp1date);
                caseData.setSympRash(symp2date);
                caseData.setSympLymph(checkSymp3.isChecked());
                caseData.setSympCough(checkSymp4.isChecked());
                caseData.setSympKoplik(checkSymp5.isChecked());
                caseData.setSympRunnynose(checkSymp6.isChecked());
                caseData.setSympRedeye(checkSymp7.isChecked());
                caseData.setSympArthrisis(checkSymp8.isChecked());
                caseData.setComplications(complications);
                caseData.setOtherSymptoms(symptoms);
                caseData.setDiagnosis(workingdiagnosis);
                caseData.setMCVaccine(vaccinationStatus);
                caseData.setMCVmv(vaccineMV);
                caseData.setMCVmr(vaccineMR);
                caseData.setMCVmmr(vaccineMMR);
                caseData.setMCVlastDoseDate(vaccineLastDoseDate);
                caseData.setMCVvalidation(vaccinationValidity);
                caseData.setMCVCampaign(vaccineCampaign);
                caseData.setNoMCVreason(noVaccReasons);
                caseData.setVitA(vitA);
                caseData.setTravelHistory(travelHistory);
                caseData.setTravelHistoryPlace(travelPlace);
                caseData.setTravelHistoryDate(travelDate);
                caseData.setTravelDaysRashOnset(rashOnset);
                caseData.setExpContactMeasles(measlesContact);
                caseData.setExpContactRubella(rubellaContact);
                caseData.setExpContactName(rubellaContactName);
                caseData.setExpContactPlace(rubellaContactPlace);
                caseData.setExpContactDate(rubellaContactTravelDate);
                caseData.setExpPlaceType(rubellaExposure);
                caseData.setOtherCommunityCases(otherKnownFeverRash);
                caseData.setLabSpecimen(labspecimen);
                caseData.setLabDateCollected(collectdate);
                caseData.setLabDateSent(labresult);
                caseData.setLabDateReceived(receivedate);
                caseData.setLabMeaslesResult(resultMeasle);
                caseData.setLabRubellaResult(resultRubella);
                caseData.setLabVirusResult(resultVirus);
                caseData.setLabPCRResult(resultPRC);
                caseData.setFinalClassification(finalClassification);
                caseData.setSourceInfection(sourceinfo);
                caseData.setOutcome(outcome);
                caseData.setDateDied(datedied);
                caseData.setFinalDiagnosis(finaldiagnosis);

                // prepping form data before sending to retrofit
                formData = new CaseForm();
                formData.setCases(cases);
                formData.setPatient(patientForm);
                formData.setRiskFactors(riskFactors);
                formData.setCaseData(caseData);

                CaseFormJS caseFormJS = new CaseFormJS();
                caseFormJS.setFormData(formData);

                Call<ResponseBody> call = apiClient.APIservice.postNewCase(caseFormJS);
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
                                        pageNine();
                                        startActivity(new Intent(AddcifMeaslesActivity.this, HomeActivity.class));
                                    }
                                });
                                buttonAddCase.setOnClickListener(new View.OnClickListener() { @Override
                                    public void onClick(View view) {
                                        // startActivity(new Intent(AddcifMeaslesActivity.this, AddcaseActivity.class));
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

    private void getPatientAutofill(ArrayAdapter<String> adapter) {
        patientnames.clear();

        Call<ArrayList<Patient>> call = apiClient.APIservice.getPatientAutofill(user.getUserID(),"false");
        patientArrayList = new ArrayList<Patient>();

        call.enqueue(new Callback<ArrayList<Patient>>() {
            @Override
            public void onResponse(Call<ArrayList<Patient>> call, Response<ArrayList<Patient>> response){
                patientArrayList.addAll(response.body());

                for(int i = 0; i < patientArrayList.size(); i++) {
                    patientnames.add(patientArrayList.get(i).getFirstName() + " " + patientArrayList.get(i).getMidName() + " " + patientArrayList.get(i).getLastName());
                }

                Log.e("getPatientAutofill", "Data Loaded");
                adapter.addAll(patientnames);
                adapter.notifyDataSetChanged();
                Log.e("getPatientAutofill", "Dataset Changed");
            }
            @Override
            public void onFailure(Call<ArrayList<Patient>> call, Throwable t) {
                Log.e("getPatientAutofill", t.getMessage());
            }
        });
    }
}
