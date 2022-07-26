package com.idsr.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.idsr.mobile.databinding.ActivityAddcifMeasles0Binding;
import com.idsr.mobile.databinding.ActivityAddcifMeasles1Binding;
import com.idsr.mobile.databinding.ActivityAddcifMeasles2Binding;
import com.idsr.mobile.databinding.ActivityAddcifMeasles3Binding;
import com.idsr.mobile.databinding.ActivityAddcifMeasles4Binding;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddcifMeaslesActivity extends AppCompatActivity {
    private ActivityAddcifMeasles0Binding binding0;
    private ActivityAddcifMeasles1Binding binding1;
    private ActivityAddcifMeasles2Binding binding2;
    private ActivityAddcifMeasles3Binding binding3;
    private ActivityAddcifMeasles4Binding binding4;

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

    private String lastName, firstName, middleName, birthdate, sex, pregnancy, civilstatus, indigenousgroup;
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

    private String patientAdmit, admitdate, onsetdate, reportdate, reporter;
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

    private String vaccinationStatus, vaccineMV, vaccineMR, vaccineMMR, vaccineLastDoseDate, vaccinationValidity, vaccineCampaign, novaccineReasonOther, vitA;
    // TODO: no vaxx reason initializaton, not sure also how theyre stored


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding0 = ActivityAddcifMeasles0Binding.inflate(getLayoutInflater());
        binding1 = ActivityAddcifMeasles1Binding.inflate(getLayoutInflater());
        binding2 = ActivityAddcifMeasles2Binding.inflate(getLayoutInflater());
        binding3 = ActivityAddcifMeasles3Binding.inflate(getLayoutInflater());
        binding4 = ActivityAddcifMeasles4Binding.inflate(getLayoutInflater());
        pageZero();
//        startActivity(new Intent(AddcifMeaslesActivity.this, AddcaseActivity.class));
    }

    public void pageZero() {
        setContentView(R.layout.activity_addcif_measles0);

        String[] patientnames =  {
            "Hello, Android - Ed Burnette",
            "Professional Android 2 App Dev - Reto Meier",
        };
        ArrayAdapter<String> adapterPatients = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,patientnames);
        this.autocompPatients = (AutoCompleteTextView)findViewById(R.id.autocomp_mea_searchpatient);
        autocompPatients.setThreshold(1);
        autocompPatients.setAdapter(adapterPatients);
        autocompPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                patientInfoFill(autocompPatients.getText().toString());
                Toast.makeText(getBaseContext(), autocompPatients.getText().toString(), Toast.LENGTH_SHORT).show();
                pageTwo();
            }
        });

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

    public void patientInfoFill(String patient) {
        //get patient info
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
        this.etPhone = findViewById(R.id.et_mea_phone);
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
        this.checkboxSameaddress = findViewById(R.id.checkbox_sameaddress);
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

//        DATE
        this.etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                                etBirthdate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);}
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
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
                if (pregnancy.equals("Not Pregnant")) etPregantweeks.setTextIsSelectable(false);
                else etPregantweeks.setTextIsSelectable(true);
            }
        });

        checkboxSameaddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    constPermanentAdd.setVisibility(View.GONE);
                    sameCurrPermAddress = true;
                }
                else {
                    constPermanentAdd.setVisibility(View.VISIBLE);
                    sameCurrPermAddress = false;
                }
            }
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
            @Override
            public void onClick(View view) {
                lastName = etLastname.getText().toString();
                firstName = etFirstname.getText().toString();
                middleName = etMiddlename.getText().toString();
                birthdate = etBirthdate.getText().toString();
                // sex & pregnancy above in radio onclick
                civilstatus = tvCivilStatus.getText().toString();
                indigenousgroup = etIndigenousgroup.getText().toString();
                phone = etPhone.getText().toString();
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
                parentCg = etCurrStreet.getText().toString();
                parentCgContact = etCurrStreet.getText().toString();
                HCPN = etCurrStreet.getText().toString();
                ILHZ = etCurrStreet.getText().toString();

                // idk how to check validity of phone nums zz, both phone & parentCgContact
//                if (lastName.isEmpty() && firstName.isEmpty() && middleName.isEmpty() && birthdate.isEmpty() &&
//                        sex.isEmpty() && pregnancy.isEmpty() && civilstatus.isEmpty() && indigenousgroup.isEmpty() &&
//                        occupation.isEmpty() && occuloc.isEmpty() && occuStreet.isEmpty() && occuCity.isEmpty() && occuBrgy.isEmpty() &&
//                        currStreet.isEmpty() && currCity.isEmpty() && currBrgy.isEmpty() &&
//                        (sameCurrPermAddress || (permStreet.isEmpty() && permCity.isEmpty() && permBrgy.isEmpty())) &&
//                        parentCg.isEmpty() && HCPN.isEmpty() && ILHZ.isEmpty()) {
                if (false) {
                    Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                    page1 = false;
                }
                else {
                    page1 = true;
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
                                etAdmitdate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });
        etOnsetdate.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etOnsetdate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });
        etReportdate.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        this.next3 = findViewById(R.id.btn_meas_next3);
        this.back1 = findViewById(R.id.btn_meas_back1);

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // patientAdmit set in onclick
                admitdate = etAdmitdate.getText().toString();
                onsetdate = etOnsetdate.getText().toString();
                reportdate = etReportdate.getText().toString();
                reporter = etReporter.getText().toString();
                // TODO: idk how to get risk factor

                // TODO: some validation
                if (false) {
                    Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                    page2 = false;
                }
                else {
                    page2 = true;
                    pageThree();
                }
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageOne(); }
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
                        etSymp1Date.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });
        etSymp2Date.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override  public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etSymp2Date.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
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
                // TODO: idk how to get symptoms
                symp1date = etSymp1Date.getText().toString();
                symp2date = etSymp2Date.getText().toString();
                complications = etComplications.getText().toString();
                symptoms = etSymptoms.getText().toString();
                workingdiagnosis = etWorkingDiagnosis.getText().toString();

                // no validation needed, since not required
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
        this.etVaccineValidityOthers = findViewById(R.id.radiobutton_vaccinevalidationOther);
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

        radioMeaslesVaccination.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                if (radioButton.getText().toString().equals("Yes")) {
                    constVaccinated.setVisibility(View.VISIBLE);
                    constUnvaccinated.setVisibility(View.GONE);}
                else if (radioButton.getText().toString().equals("No")) {
                    constVaccinated.setVisibility(View.GONE);
                    constUnvaccinated.setVisibility(View.VISIBLE);}
                vaccinationStatus = radioButton.getText().toString();
            }
        });

        etVaccineLastDoseDate.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() { @Override
                    public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                        etVaccineLastDoseDate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });

        radioVaccineValidity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                if (radioButton.getText().toString().equals("Others:")) etVaccineValidityOthers.setVisibility(View.VISIBLE);
                else etVaccineValidityOthers.setVisibility(View.INVISIBLE);
                vaccinationValidity = radioButton.getText().toString();}
        });

        etVaccineValidityOthers.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final Calendar c = Calendar.getInstance(); int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddcifMeaslesActivity.this, new DatePickerDialog.OnDateSetListener() { @Override
                public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                    etVaccineValidityOthers.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);} }, mYear, mMonth, mDay);
                datePickerDialog.show(); }
        });

        radioVaccineCampaign.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                vaccineCampaign = radioButton.getText().toString();}
        });

        checkNoVaccReasOthers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  { @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) etNoVaccReasOther.setVisibility(View.VISIBLE); else etNoVaccReasOther.setVisibility(View.INVISIBLE); } });

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
                // vaccinationStatus set in onclick
                vaccineMV = etVaccineMV.getText().toString();
                vaccineMR = etVaccineMR.getText().toString();
                vaccineMMR = etVaccineMMR.getText().toString();
                vaccineLastDoseDate = etVaccineLastDoseDate.getText().toString();
                vaccinationValidity = etVaccineValidityOthers.getText().toString();
                // vaccinevalidity & vaccineCampaign set in onclick

                // TODO: no vaxx reason retrieve, use ischeck()
                novaccineReasonOther = etNoVaccReasOther.getText().toString();
                // vita also set onclick

                // TODO: validation ekek
                if (false) {
                    Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                    page4 = false;
                }
                else {
                    page4 = true;
                    pageFive();
                }
            }
        });
    }

    public void pageFive() {
        setContentView(R.layout.activity_addcif_measles4);

        this.next6 = findViewById(R.id.btn_meas_next5);
        this.back4 = findViewById(R.id.btn_meas_back3);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pageFour(); }
        });
        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: validation ekek
                if (false) {
                    Toast.makeText(getBaseContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                    page5 = false;
                }
                else {
                    page5 = true;
                    pageSix();
                }
            }
        });
    }

    public void pageSix() {

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
