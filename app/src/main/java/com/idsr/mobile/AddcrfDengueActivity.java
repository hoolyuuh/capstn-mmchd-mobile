package com.idsr.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.idsr.mobile.models.APIClient;
import com.idsr.mobile.models.Case;
import com.idsr.mobile.models.CaseData;
import com.idsr.mobile.models.CaseForm;
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
import java.util.Date;

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

    private String labresult="", labspecimen, collectdate, receivedate, resultMeasle, resultRubella, resultVirus, resultPRC, investigator, investigatorContact, investigateDate, labselected;

    //    page 9
    private RadioGroup radioFinalClassif;
    private ImageButton imageDropdown1, imageDropdown2, imageDropdown3, imageDropdown4, imageDropdown5, imageDropdown6, imageDropdown7;
    private TextView textDropdown1, textDropdown2, textDropdown3, textDropdown4, textDropdown5, textDropdown6, textDropdown7;

    private String finalClassification="";

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
        pageThree();
    }

    public void pageThree() {
        // something
        pageFour();
    }

    public void pageFour() {
        // something
        pageFive();
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
