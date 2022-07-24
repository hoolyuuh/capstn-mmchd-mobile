package com.idsr.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.idsr.mobile.databinding.ActivityAddcifMeasles0Binding;
import com.idsr.mobile.databinding.ActivityAddcifMeasles1Binding;
import com.idsr.mobile.databinding.ActivityAddcifMeasles2Binding;

import java.util.Calendar;
import java.util.Date;

public class AddcifMeaslesActivity extends AppCompatActivity {
    private ActivityAddcifMeasles0Binding binding0;
    private ActivityAddcifMeasles1Binding binding1;
    private ActivityAddcifMeasles2Binding binding2;

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

    private AutoCompleteTextView autocompPatients;

    private EditText ETlastname, ETmiddlename, ETfirstname, ETbirthdate, ETphone;
    private RadioGroup radioSex;
    private RadioButton radioFemale, radioMale;
    private Spinner spinnerCivilstatus, spinnerOccCity, spinnerOccBrgy, spinnerCurrCity, spinnerCurrBrgy, spinnerPermCity, spinnerPermBrgy;
    private CheckBox checkboxSameaddress;
    private ConstraintLayout constPermanentAdd;
    private EditText ETstreet, ETbarangay, ETcity, ETprovince;

    private int userId;
    private String firstName, middleName, lastName, sex, civilstatus;
    private Date birthday;
    private int phone;
    private String street;
    private String city;
    private String barangay;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding0 = ActivityAddcifMeasles0Binding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_addcif_measles0);
        pagezero();
//        startActivity(new Intent(AddcifMeaslesActivity.this, AddcaseActivity.class));
    }

    public void pagezero() {
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
                binding2 = ActivityAddcifMeasles2Binding.inflate(getLayoutInflater());
                setContentView(R.layout.activity_addcif_measles2);
                pageTwoOnwards();
            }
        });

        this.next1 = findViewById(R.id.btn_meas_next1);
        this.cancel = findViewById(R.id.btn_meas_cancel);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding1 = ActivityAddcifMeasles1Binding.inflate(getLayoutInflater());
                setContentView(R.layout.activity_addcif_measles1);
                pageOne();
            }
        });
    }

    public void pageOne() {

        this.ETlastname = findViewById(R.id.ti_mea_lastname);
        this.ETfirstname = findViewById(R.id.ti_mea_firstname);
        this.ETmiddlename = findViewById(R.id.ti_mea_middlename);
        this.ETbirthdate = findViewById(R.id.ti_mea_birthdate);
        this.radioSex = (RadioGroup) findViewById(R.id.radiogroup_sex);
        this.radioFemale = findViewById(R.id.radiobutton_female);
        this.radioMale = findViewById(R.id.radiobutton_male);
        this.ETphone = findViewById(R.id.ti_mea_phone);
        this.spinnerCivilstatus = findViewById(R.id.spinner_mea_civilstatus);
        this.spinnerOccCity = findViewById(R.id.spinner_mea_occucity);
        this.spinnerOccBrgy = findViewById(R.id.spinner_mea_occubrgy);
        this.spinnerCurrCity = findViewById(R.id.spinner_mea_currcity);
        this.spinnerCurrBrgy = findViewById(R.id.spinner_mea_currbrgy);
        this.spinnerPermCity = findViewById(R.id.spinner_mea_permcity);
        this.spinnerPermBrgy = findViewById(R.id.spinner_mea_permbrgy);
        this.checkboxSameaddress = findViewById(R.id.checkbox_sameaddress);
        this.constPermanentAdd = findViewById(R.id.cons_perminfo);

        TextView civilstatus = findViewById(R.id.tv_mea_civilstatus);
        TextView occucity = findViewById(R.id.tv_mea_occucity);
        TextView occubrgy = findViewById(R.id.tv_mea_occubrgy);
        TextView currcity = findViewById(R.id.tv_mea_currcity);
        TextView currbrgy = findViewById(R.id.tv_mea_currbrgy);
        TextView permcity = findViewById(R.id.tv_mea_permcity);
        TextView permbrgy = findViewById(R.id.tv_mea_permbrgy);

//        DATE
        this.ETbirthdate.setOnClickListener(new View.OnClickListener() {
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
                                ETbirthdate.setText( (monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);}
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

//        RADIO
        radioSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                String gender=radioButton.getText().toString();
                Toast.makeText(getBaseContext(), gender, Toast.LENGTH_SHORT).show();
            }
        });

        checkboxSameaddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) constPermanentAdd.setVisibility(View.GONE);
                else constPermanentAdd.setVisibility(View.VISIBLE);
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
                civilstatus.setText(spinnerCivilstatus.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<CharSequence>adapterOccuCity=ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapterOccuCity.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerOccCity.setAdapter(adapterOccuCity);
        spinnerOccCity.setSelection(0, true);
        spinnerOccCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                occucity.setText(spinnerOccCity.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);

                ArrayAdapter<CharSequence>adapterOccuBrgy=ArrayAdapter.createFromResource(spinnerOccBrgy.getContext(), getBrgy(spinnerOccCity.getSelectedItem().toString()), android.R.layout.simple_spinner_item);
                adapterOccuBrgy.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinnerOccBrgy.setAdapter(adapterOccuBrgy);
                spinnerOccBrgy.setSelection(0, true);
                spinnerOccBrgy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        occubrgy.setText(spinnerOccBrgy.getSelectedItem().toString());
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
                currcity.setText(spinnerCurrCity.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);

                ArrayAdapter<CharSequence>adapterCurrBrgy=ArrayAdapter.createFromResource(spinnerCurrBrgy.getContext(), getBrgy(spinnerCurrCity.getSelectedItem().toString()), android.R.layout.simple_spinner_item);
                adapterCurrBrgy.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinnerCurrBrgy.setAdapter(adapterCurrBrgy);
                spinnerCurrBrgy.setSelection(0, true);
                spinnerCurrBrgy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        currbrgy.setText(spinnerCurrBrgy.getSelectedItem().toString());
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
                permcity.setText(spinnerPermCity.getSelectedItem().toString());
                ((TextView) adapterView.getChildAt(0)).setVisibility(View.INVISIBLE);

                ArrayAdapter<CharSequence>adapterPermBrgy=ArrayAdapter.createFromResource(spinnerPermBrgy.getContext(), getBrgy(spinnerPermCity.getSelectedItem().toString()), android.R.layout.simple_spinner_item);
                adapterPermBrgy.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinnerPermBrgy.setAdapter(adapterPermBrgy);
                spinnerPermBrgy.setSelection(0, true);
                spinnerPermBrgy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        permbrgy.setText(spinnerPermBrgy.getSelectedItem().toString());
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
                binding2 = ActivityAddcifMeasles2Binding.inflate(getLayoutInflater());
                setContentView(R.layout.activity_addcif_measles2);
                pageTwoOnwards();
            }
        });
    }

    public void pageTwoOnwards() {

    }

    public void patientInfoFill(String patient) {
        //get patient info
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
