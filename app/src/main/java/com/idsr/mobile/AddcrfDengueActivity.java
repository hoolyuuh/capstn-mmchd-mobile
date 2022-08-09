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
import com.idsr.mobile.models.APIModels.DengueSubmitJS;
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
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddcrfDengueActivity extends AppCompatActivity {
    private Boolean page0 = Boolean.FALSE;
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
    private Spinner sp_crfids;
    private TextView tv_crfID;
    private String crfID = "";
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

    private String labresult="", labspecimen, collectdate, receivedate, resultMeasle, resultRubella, resultVirus, resultPRC, investigator, investigatorContact, investigateDate, labselected, labSelectID;

    //    page 7
    private RadioGroup radioFinalClassif;
    private ImageButton imageDropdown1, imageDropdown2, imageDropdown3, imageDropdown4, imageDropdown5, imageDropdown6, imageDropdown7;
    private TextView textDropdown1, textDropdown2, textDropdown3, textDropdown4, textDropdown5, textDropdown6, textDropdown7;

    private String finalClassification="";
    private int RG_finalClassifID;

    //data

    private ArrayList<User> labUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = new APIClient();
        userCRFs = new ArrayList<>();
        bundle = getIntent().getExtras();
        user = bundle.getParcelable("user");

        patientnames = new ArrayList<>();
        patientArrayList = new ArrayList<>();

        Call<ArrayList<User>> call1 = apiClient.APIservice.getLabUsers();
        call1.enqueue(new Callback<ArrayList<User>>() {
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
        // something
        setContentView(R.layout.activity_addcrf_dengue0);
        this.tv_crfID = findViewById(R.id.tv_den_week);
        this.sp_crfids = findViewById(R.id.spinner_den_week);

        ArrayAdapter<String>adapterCRFIDs= new ArrayAdapter(this, android.R.layout.simple_spinner_item, userCRFs);
        adapterCRFIDs.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_crfids.setAdapter(adapterCRFIDs);
        sp_crfids.setSelection(0, true);
        sp_crfids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv_crfID.setText(sp_crfids.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
                crfID =  tv_crfID.getText().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        Call<ArrayList<String>> call = apiClient.APIservice.getMobileCRFs(user.getUserID());
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                Log.d("postNewCase", "CRF list size: " + response.body().size());
                if (response.code() == 200) {
                    userCRFs.clear();
                    userCRFs = response.body();
                    adapterCRFIDs.addAll(userCRFs);
                    adapterCRFIDs.notifyDataSetChanged();
                } else Log.d("postNewCase", "Failed: ");

            }
            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.e("postNewCase", t.getMessage());
            }
        });

        ArrayAdapter<String> adapterPatients = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,patientnames);
        this.autocompPatients = (AutoCompleteTextView)findViewById(R.id.autocomp_den_searchpatient);
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
        this.next1 = findViewById(R.id.btn_den_next1);
        this.cancel = findViewById(R.id.btn_den_cancel);
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

    public void pageOne() {
        setContentView(R.layout.activity_addcrf_dengue1);

        this.etLastname = findViewById(R.id.et_den_lastname);
        this.etFirstname = findViewById(R.id.et_den_firstname);
        this.etMiddlename = findViewById(R.id.et_den_middlename);
        this.etBirthdate = findViewById(R.id.et_den_birthdate);
        this.radioSex = (RadioGroup) findViewById(R.id.radiogroup_den_sex);
        this.radioPregnancy = (RadioGroup) findViewById(R.id.radiogroup_den_pregnancy);
        this.etPregantweeks = findViewById(R.id.et_den_pregweeks);
        // this.etPhone = findViewById(R.id.et_den_phone);
        this.tvCivilStatus = findViewById(R.id.tv_den_civilstatus);
        this.spinnerCivilstatus = findViewById(R.id.spinner_den_civilstatus);
        this.etIndigenousgroup = findViewById(R.id.et_den_indigenous);

        this.etOccupation = findViewById(R.id.et_den_occupation);
        this.etOcculoc = findViewById(R.id.et_den_occuloc);
        this.etOccuStreet = findViewById(R.id.et_den_occustreet);
        this.tvOccuCity = findViewById(R.id.tv_den_occucity);
        this.spinnerOccuCity = findViewById(R.id.spinner_den_occucity);
        this.tvOccuBrgy = findViewById(R.id.tv_den_occubrgy);
        this.spinnerOccuBrgy = findViewById(R.id.spinner_den_occubrgy);
        this.etCurrStreet = findViewById(R.id.et_den_currstreet);
        this.tvCurrCity = findViewById(R.id.tv_den_currcity);
        this.spinnerCurrCity = findViewById(R.id.spinner_den_currcity);
        this.tvCurrBrgy = findViewById(R.id.tv_den_currbrgy);
        this.spinnerCurrBrgy = findViewById(R.id.spinner_den_currbrgy);
        this.checkboxSameaddress = findViewById(R.id.checkbox_den_sameaddress);
        this.constPermanentAdd = findViewById(R.id.cons_perminfo);
        this.etPermStreet = findViewById(R.id.et_den_permstreet);
        this.tvPermCity = findViewById(R.id.tv_den_permcity);
        this.spinnerPermCity = findViewById(R.id.spinner_den_permcity);
        this.tvPermBrgy = findViewById(R.id.tv_den_permbrgy);
        this.spinnerPermBrgy = findViewById(R.id.spinner_den_permbrgy);

        this.etParentCg = findViewById(R.id.et_den_parentcg);
        this.etParentCgContact = findViewById(R.id.et_den_parentcgContact);
        this.etHCPN = findViewById(R.id.et_den_hcpn);
        this.etILHZ = findViewById(R.id.et_den_ilhz);

        // TODO: Initialize Data if Backpressed
        if(page1){

        }
//        DATE
        this.etBirthdate.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this,
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
        ArrayAdapter<CharSequence> adapterCivilStatus=ArrayAdapter.createFromResource(this, R.array.civilstatus, android.R.layout.simple_spinner_item);
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

        this.next2 = findViewById(R.id.btn_den_next2);
        this.back0 = findViewById(R.id.btn_den_back1);
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
        // something
        setContentView(R.layout.activity_addcrf_dengue2);

        this.radioPatientAdmit = findViewById(R.id.radiogroup_den_admitted);
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
//        etReportdate.setOnClickListener(new View.OnClickListener() { @Override
//        public void onClick(View v) {
//            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
//            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this, new DatePickerDialog.OnDateSetListener() {
//                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
//                    etReportdate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
//                            Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
//                            Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
//            datePickerDialog.show(); }
//        });

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

        this.next3 = findViewById(R.id.btn_den_next3);
        this.back1 = findViewById(R.id.btn_den_back2);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageOne(); }
        });

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2 = true;

//                if (patientAdmit.length() <= 0) { page2 = page2 & false; radioPatientAdmit.setBackgroundResource(R.color.theme_lightest_red); }
//                else radioPatientAdmit.setBackgroundResource(0);
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
//                if (etReportdate.getText().toString().length() <= 0) { page2 = page2 & false; etReportdate.setBackgroundResource(R.drawable.inputbox_red); }
//                else etReportdate.setBackgroundResource(R.drawable.inputbox);
//                if (etReporter.getText().toString().length() <= 0) { page2 = page2 & false; etReporter.setBackgroundResource(R.drawable.inputbox_red); }
//                else etReporter.setBackgroundResource(R.drawable.inputbox);

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
//                    reportdate = etReportdate.getText().toString();
//                    reporter = etReporter.getText().toString();
                    consultDate = etconsultDate.getText().toString();
                    consultPlace = etConsultPlace.getText().toString();
                    patientAdmit = "NO";
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

        back2 = findViewById(R.id.btn_den_back3);
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
        setContentView(R.layout.activity_addcrf_dengue4);
        radioDengueClinicalClass = findViewById(R.id.radiogroup_den_clinic_classif);

        next5 = findViewById(R.id.btn_den_next5);
        back3 = findViewById(R.id.btn_den_back4);

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

        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageTwo(); }
        });

        next5.setOnClickListener(new View.OnClickListener() {
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
        setContentView(R.layout.activity_addcrf_dengue5);

        this.radioOutcome = findViewById(R.id.radiogroup_den_outcome);
        this.etDatedied = findViewById(R.id.et_den_dateDied);
        this.etFinalDiagnosis = findViewById(R.id.et_den_finaldiagnosis);

        // TODO: Initialize Data if Backpressed
        if(page5){

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
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etDatedied.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });

        this.next6 = findViewById(R.id.btn_den_next6);
        this.back4 = findViewById(R.id.btn_den_back5);
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageSix(); }
        });
        next6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page5 = true;

                if (outcome.length() <= 0) { page5 = page5 & false; radioOutcome.setBackgroundResource(R.color.theme_lightest_red); }
                else radioOutcome.setBackgroundResource(0);
                if (Objects.equals(outcome, "Dead")) {
                    if (etDatedied.getText().toString().length() <= 0) { page5 = page5 & false; etDatedied.setBackgroundResource(R.drawable.inputbox_red); }
                    else etDatedied.setBackgroundResource(R.drawable.inputbox);
                }
                if (etFinalDiagnosis.getText().toString().length() <= 0) { page5 = page5 & false; etFinalDiagnosis.setBackgroundResource(R.drawable.inputbox_red); }
                else etFinalDiagnosis.setBackgroundResource(R.drawable.inputbox);

                if (!page5) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    // outcome is set in onclick
                    datedied = etDatedied.getText().toString();
                    finaldiagnosis = etFinalDiagnosis.getText().toString();

                    pageSix();
                }
            }
        });
    }

    public void pageSix() {
        setContentView(R.layout.activity_addcrf_dengue6);

        this.consWithLabResult = findViewById(R.id.cons_den_yesLabResult);
        this.consNoLabResult = findViewById(R.id.cons_den_noLabResult);
        this.radioLabResult = findViewById(R.id.radiogroup_den_labResult);
        this.tvLabSelect = findViewById(R.id.tv_den_labselect);
        this.spinnerLabSelect = findViewById(R.id.spinner_den_labselect);

        // TODO: Initialize Data if Backpressed
        if(page6){

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

//        etCollectdate.setOnClickListener(new View.OnClickListener() {@Override
//        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
//            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this,
//                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
//                        etCollectdate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
//                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
//                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
//            datePickerDialog.show(); }
//        });
//        etReceivedate.setOnClickListener(new View.OnClickListener() {@Override
//        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
//            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this,
//                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
//                        etReceivedate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
//                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
//                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
//            datePickerDialog.show(); }
//        });
//        etInvestigDate.setOnClickListener(new View.OnClickListener() {@Override
//        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
//            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog datePickerDialog = new DatePickerDialog(AddcrfDengueActivity.this,
//                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
//                        etInvestigDate.setText(Strings.padStart(Integer.toString(year), 4, '0') + "-" +
//                                Strings.padStart(Integer.toString(monthOfYear), 2, '0') + "-" +
//                                Strings.padStart(Integer.toString(dayOfMonth), 2, '0'));} }, mYear, mMonth, mDay);
//            datePickerDialog.show(); }
//        });
//
//        ArrayAdapter<CharSequence>adapterLabSpecimen=ArrayAdapter.createFromResource(this, R.array.measLabSpecimen, android.R.layout.simple_spinner_item);
//        adapterLabSpecimen.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        spinnerLabSpecimen.setAdapter(adapterLabSpecimen);
//        spinnerLabSpecimen.setSelection(0, true);
//        spinnerLabSpecimen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                tvLabspecimen.setText(spinnerLabSpecimen.getSelectedItem().toString());
//                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
//        ArrayAdapter<CharSequence>adapterLabSelection=ArrayAdapter.createFromResource(this, R.array.measLabSelection, android.R.layout.simple_spinner_item);
//        adapterLabSelection.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        spinnerLabSelect.setAdapter(adapterLabSelection);
//        spinnerLabSelect.setSelection(0, true);
//        spinnerLabSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                tvLabSelect.setText(spinnerLabSelect.getSelectedItem().toString());
//                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });

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

        this.next7 = findViewById(R.id.btn_den_next7);
        this.back6 = findViewById(R.id.btn_den_back6);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageFive(); }
        });
        next7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page6 = true;

                if (labresult.length() <= 0) { page6 = page6 & false; radioLabResult.setBackgroundResource(R.color.theme_lightest_red); }
                else radioLabResult.setBackgroundResource(0);
                if (Objects.equals(labresult, "No")) {
                    if (tvLabSelect.getText().toString().length() <= 0) { page6 = page6 & false; tvLabSelect.setBackgroundResource(R.drawable.inputbox_red); tvLabSelect.setPadding(55,50,55,20); }
                    else { tvLabSelect.setBackgroundResource(R.drawable.inputbox); tvLabSelect.setPadding(55,50,55,20); }
                }
//                else if (Objects.equals(labresult, "Yes")) {
//                    if (tvLabspecimen.getText().toString().length() <= 0) { page6 = page6 & false; tvLabspecimen.setBackgroundResource(R.drawable.inputbox_red); tvLabSelect.setPadding(55,50,55,20); }
//                    else { tvLabspecimen.setBackgroundResource(R.drawable.inputbox); tvLabspecimen.setPadding(55,50,55,20); }
//                    if (etCollectdate.getText().toString().length() <= 0) { page6 = page6 & false; etCollectdate.setBackgroundResource(R.drawable.inputbox_red); }
//                    else etCollectdate.setBackgroundResource(R.drawable.inputbox);
//                    if (etReceivedate.getText().toString().length() <= 0) { page6 = page6 & false; etReceivedate.setBackgroundResource(R.drawable.inputbox_red); }
//                    else etReceivedate.setBackgroundResource(R.drawable.inputbox);
//                    if (etresultMeasles.getText().toString().length() <= 0) { page6 = page6 & false; etresultMeasles.setBackgroundResource(R.drawable.inputbox_red); }
//                    else etresultMeasles.setBackgroundResource(R.drawable.inputbox);
//                    if (etresultRubella.getText().toString().length() <= 0) { page6 = page6 & false; etresultRubella.setBackgroundResource(R.drawable.inputbox_red); }
//                    else etresultRubella.setBackgroundResource(R.drawable.inputbox);
//                    if (etresultVirus.getText().toString().length() <= 0) { page6 = page6 & false; etresultVirus.setBackgroundResource(R.drawable.inputbox_red); }
//                    else etresultVirus.setBackgroundResource(R.drawable.inputbox);
//                    if (etresultPRC.getText().toString().length() <= 0) { page6 = page6 & false; etresultPRC.setBackgroundResource(R.drawable.inputbox_red); }
//                    else etresultPRC.setBackgroundResource(R.drawable.inputbox);
//                    if (etInvestigator.getText().toString().length() <= 0) { page6 = page6 & false; etInvestigator.setBackgroundResource(R.drawable.inputbox_red); }
//                    else etInvestigator.setBackgroundResource(R.drawable.inputbox);
//                    if (etInvestigContact.getText().toString().length() <= 0) { page6 = page6 & false; etInvestigContact.setBackgroundResource(R.drawable.inputbox_red); }
//                    else etInvestigContact.setBackgroundResource(R.drawable.inputbox);
//                    if (etInvestigDate.getText().toString().length() <= 0) { page6 = page6 & false; etInvestigDate.setBackgroundResource(R.drawable.inputbox_red); }
//                    else etInvestigDate.setBackgroundResource(R.drawable.inputbox);
//                }

                if (!page6) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    // set in onclick: labresult
//                    labspecimen = tvLabspecimen.getText().toString();
//                    collectdate = etCollectdate.getText().toString();
//                    receivedate = etReceivedate.getText().toString();
//                    resultMeasle = etresultMeasles.getText().toString();
//                    resultRubella = etresultRubella.getText().toString();
//                    resultVirus = etresultVirus.getText().toString();
//                    resultPRC = etresultPRC.getText().toString();
//                    investigator = etInvestigator.getText().toString();
//                    investigatorContact = etInvestigContact.getText().toString();
//                    investigateDate = etInvestigDate.getText().toString();
                    labselected = tvLabSelect.getText().toString();

                    pageSeven();
                }
            }
        });
    }

    public void pageSeven() {
        // something

        setContentView(R.layout.activity_addcrf_dengue7);

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

        this.back6 = findViewById(R.id.btn_den_back7);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageSix(); }
        });

        this.submit = findViewById(R.id.btn_den_next8);
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
                cases.setReportDate((new Date()).toString());
                cases.setInvestigationDate("");
                cases.setDateAdmitted(admitdate);
                cases.setDateOnset(onsetdate);
                cases.setReporterName("");
                cases.setReporterContact(null);
                cases.setInvestigatorLab(labSelectID);
                cases.setInvestigatorName(labselected);
                cases.setInvestigatorContact("");
                cases.setFinalDiagnosis(finaldiagnosis);
                cases.setCRFID(crfID);

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
                    patientForm.setAgeType("years");
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
                caseData.setPatientAdmitted(patientAdmit);
                // prepping form data before sending to retrofit
                formData = new CaseForm();
                formData.setCases(cases);
                formData.setPatient(patientForm);
                formData.setRiskFactors(riskFactors);
                formData.setCaseData(caseData);

                Call<ResponseBody> call = apiClient.APIservice.postMobCRF(new DengueSubmitJS(crfID, formData));
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
                                    finish();
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

    public void patientInfoFill(int i) {
        // TODO: get patient info
        patient = patientArrayList.get(i);
        page1 = true;
        existingpatient = true;

        Log.e("patientInfoFill", "Patient Name: " + patientArrayList.get(i).getFirstName());
    }
}
