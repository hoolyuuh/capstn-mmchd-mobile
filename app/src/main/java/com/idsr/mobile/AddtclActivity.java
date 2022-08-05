package com.idsr.mobile;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.idsr.mobile.databinding.ActivityAddcaseBinding;
import com.idsr.mobile.databinding.ActivityAddtcl0Binding;

import java.util.Calendar;
import java.util.Objects;

public class AddtclActivity extends AppCompatActivity {
    private ActivityAddtcl0Binding binding;

    private Boolean page1 = Boolean.FALSE;
    private Boolean page2 = Boolean.FALSE;
    private Boolean page3 = Boolean.FALSE;

    private Button cancel, next1, back0, next2, back1, next3, back2, submit;
    private int userId;

    //    page 0
    private AutoCompleteTextView autocompPatients;

    //    page 1
    private EditText etLastname, etMiddlename, etFirstname, etBirthdate, etPhone, etPregantweeks, etIndigenousgroup;
    private RadioGroup radioSex, radioPregnancy;
    private TextView tvCivilStatus, tvOccuCity, tvOccuBrgy, tvCurrCity, tvCurrBrgy, tvPermCity, tvPermBrgy;
    private Spinner spinnerCivilstatus, spinnerOccuCity, spinnerOccuBrgy, spinnerCurrCity, spinnerCurrBrgy, spinnerPermCity, spinnerPermBrgy;
    private CheckBox checkboxSameaddress;
    private ConstraintLayout constPermanentAdd;
    private EditText etOccupation, etOcculoc, etOccuStreet, etCurrStreet, etPermStreet, etParentCg, etParentCgContact, etHCPN, etILHZ;

    private String lastName, firstName, middleName, birthdate, sex = "", pregnancy = "", civilstatus, indigenousgroup;
    private String phone, parentCgContact;
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
    private EditText etBCG, etHepa1, etHep2, etOpv1, etOpv2, etOpv3, etPenta1, etPenta2, etPenta3, etPcv1, etPcv2, etPcv3, etMcv1, etMcv2, etDeng1, etDeng2, etDeng3;

    private String BCG, Hepa1, Hep2, Opv1, Opv2, Opv3, Penta1, Penta2, Penta3, Pcv1, Pcv2, Pcv3, Mcv1, Mcv2, Deng1, Deng2, Deng3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageZero();
    }

    private void pageZero() {
        setContentView(R.layout.activity_addtcl0);

        String[] patientnames =  {
                "Hello, Android - Ed Burnette",
                "Professional Android 2 App Dev - Reto Meier",
        };
        ArrayAdapter<String> adapterPatients = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,patientnames);
        this.autocompPatients = (AutoCompleteTextView)findViewById(R.id.autocomp_tcl_searchpatient);
        autocompPatients.setThreshold(1);
        autocompPatients.setAdapter(adapterPatients);
        autocompPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                patientInfoFill(autocompPatients.getText().toString());
                pageTwo();
            }
        });

        this.next1 = findViewById(R.id.btn_tcl_next1);
        this.cancel = findViewById(R.id.btn_tcl_cancel);
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

    public void patientInfoFill(String patient) {
        // TODO: get patient info
    }

    public void pageOne() {
        setContentView(R.layout.activity_addtcl1);

        this.etLastname = findViewById(R.id.et_tcl_lastname);
        this.etFirstname = findViewById(R.id.et_tcl_firstname);
        this.etMiddlename = findViewById(R.id.et_tcl_middlename);
        this.etBirthdate = findViewById(R.id.et_tcl_birthdate);
        this.radioSex = (RadioGroup) findViewById(R.id.radiogroup_sex);
        this.radioPregnancy = (RadioGroup) findViewById(R.id.radiogroup_pregnancy);
        this.etPregantweeks = findViewById(R.id.et_tcl_pregweeks);
        this.etPhone = findViewById(R.id.et_tcl_phone);
        this.tvCivilStatus = findViewById(R.id.tv_tcl_civilstatus);
        this.spinnerCivilstatus = findViewById(R.id.spinner_tcl_civilstatus);
        this.etIndigenousgroup = findViewById(R.id.et_tcl_indigenous);

        this.etOccupation = findViewById(R.id.et_tcl_occupation);
        this.etOcculoc = findViewById(R.id.et_tcl_occuloc);
        this.etOccuStreet = findViewById(R.id.et_tcl_occustreet);
        this.tvOccuCity = findViewById(R.id.tv_tcl_occucity);
        this.spinnerOccuCity = findViewById(R.id.spinner_tcl_occucity);
        this.tvOccuBrgy = findViewById(R.id.tv_tcl_occubrgy);
        this.spinnerOccuBrgy = findViewById(R.id.spinner_tcl_occubrgy);
        this.etCurrStreet = findViewById(R.id.et_tcl_currstreet);
        this.tvCurrCity = findViewById(R.id.tv_tcl_currcity);
        this.spinnerCurrCity = findViewById(R.id.spinner_tcl_currcity);
        this.tvCurrBrgy = findViewById(R.id.tv_tcl_currbrgy);
        this.spinnerCurrBrgy = findViewById(R.id.spinner_tcl_currbrgy);
        this.checkboxSameaddress = findViewById(R.id.checkbox_tcl_sameddress);
        this.constPermanentAdd = findViewById(R.id.cons_perminfo);
        this.etPermStreet = findViewById(R.id.et_tcl_permstreet);
        this.tvPermCity = findViewById(R.id.tv_tcl_permcity);
        this.spinnerPermCity = findViewById(R.id.spinner_tcl_permcity);
        this.tvPermBrgy = findViewById(R.id.tv_tcl_permbrgy);
        this.spinnerPermBrgy = findViewById(R.id.spinner_tcl_permbrgy);

        this.etParentCg = findViewById(R.id.et_tcl_parentcg);
        this.etParentCgContact = findViewById(R.id.et_tcl_parentcgContact);
        this.etHCPN = findViewById(R.id.et_tcl_hcpn);
        this.etILHZ = findViewById(R.id.et_tcl_ilhz);

//        DATE
        this.etBirthdate.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) { final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this,
                    new DatePickerDialog.OnDateSetListener() { @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etBirthdate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
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

        this.next2 = findViewById(R.id.btn_tcl_next2);
        this.back0 = findViewById(R.id.btn_tcl_back0);
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
                if (etPhone.getText().toString().length() <= 0) { page1 = page1 & false; etPhone.setBackgroundResource(R.drawable.inputbox_red); }
                else etPhone.setBackgroundResource(R.drawable.inputbox);
                if (tvCivilStatus.getText().toString().length() <= 0) { page1 = page1 & false; tvCivilStatus.setBackgroundResource(R.drawable.inputbox_red); tvCivilStatus.setPadding(55,50,55,20); }
                else { tvCivilStatus.setBackgroundResource(R.drawable.inputbox); tvCivilStatus.setPadding(55,50,55,20); }

                if (etOccupation.getText().toString().length() <= 0) { page1 = page1 & false; etOccupation.setBackgroundResource(R.drawable.inputbox_red); }
                else etOccupation.setBackgroundResource(R.drawable.inputbox);
                if (etOcculoc.getText().toString().length() <= 0) { page1 = page1 & false; etOcculoc.setBackgroundResource(R.drawable.inputbox_red); }
                else etOcculoc.setBackgroundResource(R.drawable.inputbox);
                if (etOccuStreet.getText().toString().length() <= 0) { page1 = page1 & false; etOccuStreet.setBackgroundResource(R.drawable.inputbox_red); }
                else etOccuStreet.setBackgroundResource(R.drawable.inputbox);
                if (tvOccuCity.getText().toString().length() <= 0) { page1 = page1 & false; tvOccuCity.setBackgroundResource(R.drawable.inputbox_red); tvOccuCity.setPadding(25,50,25,20); }
                else { tvOccuCity.setBackgroundResource(R.drawable.inputbox); tvOccuCity.setPadding(25,50,25,20); }
                if (tvOccuBrgy.getText().toString().length() <= 0) { page1 = page1 & false; tvOccuBrgy.setBackgroundResource(R.drawable.inputbox_red); tvOccuBrgy.setPadding(25,50,25,20); }
                else { tvOccuBrgy.setBackgroundResource(R.drawable.inputbox); tvOccuBrgy.setPadding(25,50,25,20); }

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
                    phone = etPhone.getText().toString();
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
        setContentView(R.layout.activity_addtcl2);

        this.radioPatientAdmit = findViewById(R.id.radiogroup_patientadmit);
        this.etAdmitdate = findViewById(R.id.et_tcl_admitDate);
        this.etOnsetdate = findViewById(R.id.et_tcl_onsetdate);
        this.etReportdate = findViewById(R.id.et_tcl_reportdate);
        this.etReporter = findViewById(R.id.et_tcl_reporter);

        this.checkRfL1 = findViewById(R.id.checkbox_tcl_rfL1);
        this.checkRfL2 = findViewById(R.id.checkbox_tcl_rfL2);
        this.checkRfL3 = findViewById(R.id.checkbox_tcl_rfL3);
        this.checkRfL4 = findViewById(R.id.checkbox_tcl_rfL4);
        this.checkRfL5 = findViewById(R.id.checkbox_tcl_rfL5);
        this.checkRfL6 = findViewById(R.id.checkbox_tcl_rfL6);
        this.etRfLOthers = findViewById(R.id.et_tcl_rfLOthers);
        this.checkRfC1 = findViewById(R.id.checkbox_tcl_rfC1);
        this.checkRfC2 = findViewById(R.id.checkbox_tcl_rfC2);
        this.checkRfC3 = findViewById(R.id.checkbox_tcl_rfC3);
        this.checkRfC4 = findViewById(R.id.checkbox_tcl_rfC4);
        this.etRfCOthers = findViewById(R.id.et_tcl_rfCOthers);
        this.checkRfH1 = findViewById(R.id.checkbox_tcl_rfH1);
        this.checkRfH2 = findViewById(R.id.checkbox_tcl_rfH2);
        this.checkRfH3 = findViewById(R.id.checkbox_tcl_rfH3);
        this.checkRfH4 = findViewById(R.id.checkbox_tcl_rfH4);
        this.checkRfH5 = findViewById(R.id.checkbox_tcl_rfH5);
        this.checkRfH6 = findViewById(R.id.checkbox_tcl_rfH6);
        this.etRfHOthers = findViewById(R.id.et_tcl_rfHOthers);
        this.checkRfO1 = findViewById(R.id.checkbox_tcl_rfO1);
        this.checkRfO2 = findViewById(R.id.checkbox_tcl_rfO2);
        this.checkRfO3 = findViewById(R.id.checkbox_tcl_rfO3);
        this.checkRfO4 = findViewById(R.id.checkbox_tcl_rfO4);
        this.checkRfO5 = findViewById(R.id.checkbox_tcl_rfO5);
        this.checkRfO6 = findViewById(R.id.checkbox_tcl_rfO6);
        this.checkRfO7 = findViewById(R.id.checkbox_tcl_rfO7);
        this.checkRfO8 = findViewById(R.id.checkbox_tcl_rfO8);
        this.checkRfO9 = findViewById(R.id.checkbox_tcl_rfO9);
        this.checkRfO10 = findViewById(R.id.checkbox_tcl_rfO10);
        this.checkRfO11 = findViewById(R.id.checkbox_tcl_rfO11);
        this.etRfHOthers = findViewById(R.id.et_tcl_rfOOthers);

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
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etAdmitdate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etOnsetdate.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etOnsetdate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etReportdate.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etReportdate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
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

        this.next3 = findViewById(R.id.btn_tcl_next3);
        this.back1 = findViewById(R.id.btn_tcl_back1);

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
                    // TODO: idk how to get risk factor

                    pageThree();
                }
            }
        });
    }

    public void pageThree() {
        setContentView(R.layout.activity_addtcl3);

        this.etBCG = findViewById(R.id.et_bcg);
        this.etHepa1 = findViewById(R.id.et_hepa_within);
        this.etHep2 = findViewById(R.id.et_hepa_more);
        this.etOpv1 = findViewById(R.id.et_opv_1);
        this.etOpv2 = findViewById(R.id.et_opv_2);
        this.etOpv3 = findViewById(R.id.et_opv_3);
        this.etPenta1 = findViewById(R.id.et_penta_1);
        this.etPenta2 = findViewById(R.id.et_penta_2);
        this.etPenta3 = findViewById(R.id.et_penta_3);
        this.etPcv1 = findViewById(R.id.et_pcv_1);
        this.etPcv2 = findViewById(R.id.et_pcv_2);
        this.etPcv3 = findViewById(R.id.et_pcv_3);
        this.etMcv1 = findViewById(R.id.et_mcv1);
        this.etMcv2 = findViewById(R.id.et_mcv2);
        this.etDeng1 = findViewById(R.id.et_deng_1);
        this.etDeng2 = findViewById(R.id.et_deng_2);
        this.etDeng3 = findViewById(R.id.et_deng_3);

        etBCG.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etBCG.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etHepa1.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etHepa1.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etHep2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etHep2.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etOpv1.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etOpv1.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etOpv2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etOpv2.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etOpv3.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etOpv3.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etPenta1.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etPenta1.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etPenta2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etPenta2.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etPenta3.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etPenta3.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etPcv1.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etPcv1.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etPcv2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etPcv2.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etPcv3.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etPcv3.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etMcv1.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etMcv1.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etMcv2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etMcv2.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etDeng1.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etDeng1.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etDeng2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etDeng2.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });
        etDeng3.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
            mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddtclActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etDeng3.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
            datePickerDialog.show(); }
        });

        this.submit = findViewById(R.id.btn_tcl_submit);
        this.back2 = findViewById(R.id.btn_tcl_back2);

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageTwo(); }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page3 = true;

                if (!page3) Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                else {
                    BCG = etBCG.getText().toString();
                    Hepa1 = etHepa1.getText().toString();
                    Hep2 = etHep2.getText().toString();
                    Opv1 = etOpv1.getText().toString();
                    Opv2 = etOpv2.getText().toString();
                    Opv3 = etOpv3.getText().toString();
                    Penta1 = etPenta1.getText().toString();
                    Penta2 = etPenta2.getText().toString();
                    Penta3 = etPenta3.getText().toString();
                    Pcv1 = etPcv1.getText().toString();
                    Pcv2 = etPcv2.getText().toString();
                    Pcv3 = etPcv3.getText().toString();
                    Mcv1 = etMcv1.getText().toString();
                    Mcv2 = etMcv2.getText().toString();
                    Deng1 = etDeng1.getText().toString();
                    Deng2 = etDeng2.getText().toString();
                    Deng3 = etDeng3.getText().toString();

                    submit(view);
                }
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
            popupWindow.dismiss();
            //As an example, display the message
            Toast.makeText(view.getContext(), "Please wait for page to load", Toast.LENGTH_SHORT).show();

            setContentView(R.layout.activity_loading);

            // TODO: make function to submit the thingies
            TextView tvCaseAddStatus = findViewById(R.id.tv_caseAddStatus);
            ProgressBar loadingpanel = findViewById(R.id.loadingPanel);
            ImageView imgCheck = findViewById(R.id.imgCheckPanel);

            LinearLayout layoutDone = findViewById(R.id.layout_done);
            Button buttonHome = findViewById(R.id.btn_home);
            Button buttonAddCase = findViewById(R.id.btn_addanothercase);

            int i = 900000000;
            while (i>= -900000000) {
                i -= 1;
                if (i== -900000000) {
                    loadingpanel.setVisibility(View.GONE);
                    imgCheck.setVisibility(View.VISIBLE);
                    layoutDone.setVisibility(View.VISIBLE);
                    tvCaseAddStatus.setText("Case successfully submitted!");

                    buttonHome.setOnClickListener(new View.OnClickListener() { @Override
                    public void onClick(View view) {
                        startActivity(new Intent(AddtclActivity.this, HomeActivity.class));
                    }
                    });
                    buttonAddCase.setOnClickListener(new View.OnClickListener() { @Override
                    public void onClick(View view) {
                        startActivity(new Intent(AddtclActivity.this, AddtclActivity.class));
                    }
                    });
                }
            }
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

}
