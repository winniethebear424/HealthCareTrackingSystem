/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient;

import PatientManagement.Catalogs.Drug;
import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.PatientDirectory;
import PatientManagement.Patient.ClinicalHistory.Alergy;
import PatientManagement.Patient.ClinicalHistory.AlergyHistory;
import PatientManagement.Patient.ClinicalHistory.MedicationHistory;
import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.ClinicalHistory.VaccinationHistory;
import PatientManagement.Patient.Encounters.Diagnosis;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Patient.Orders.AssessmentOrder;
import PatientManagement.Patient.Orders.MedicationOrder;
import PatientManagement.Patient.Orders.Order;
import PatientManagement.Patient.Orders.TreatmentOrder;
import PatientManagement.Patient.Orders.VaccinationOrder;
import PatientManagement.Persona.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

/**
 *
 * @author kal bugrara
 */
public class Patient {
    Clinic clinic;
    EncounterHistory encounterhistory;
    VaccinationHistory vaccinationHistory;
    MedicationHistory medicationHistory;
    Person person;
    AlergyHistory alergyhistory;
    ArrayList<AssessmentOrder> assessmentOrders;
    ArrayList<MedicationOrder> medicationOrders;
    ArrayList<TreatmentOrder> treatmentOrders;
    ArrayList<VaccinationOrder> vaccinationOrders;

    public Patient(Person p, Clinic clinic) {
        encounterhistory = new EncounterHistory(this); // link this patient object back
        vaccinationHistory = new VaccinationHistory(this);
        alergyhistory = new AlergyHistory();
        medicationHistory = new MedicationHistory(this);
        person = p;
        this.clinic = clinic;
        assessmentOrders = new ArrayList<AssessmentOrder>();
        medicationOrders = new ArrayList<MedicationOrder>();
        treatmentOrders = new ArrayList<TreatmentOrder>();
        vaccinationOrders = new ArrayList<VaccinationOrder>();
    }

    public EncounterHistory getEncounterHistory() {
        return encounterhistory;
    }
    // the below method will return the encounter where the infection occured
    // from the returned encounter you pull the event, the site, etc.

    public Encounter getConfirmedEncounter() {
        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if (diag.isConfirmed()) {
                return currentencounter;// send back the whole encounter so we extract event and site
            }
        }
        return null;
    }

    public boolean isConfirmedPositive() {
        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();
        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if(diag.isConfirmed()) {
                return true;
            }
        }
        return false;
    }

    public boolean isConfirmedInfectiousPositive() {

        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if(diag.isConfirmedInfectious()) {
                return true;
            }
        }
        return false;
    }

    public Person getPerson() {
        return person;
    }

    public void addEncounter(Encounter encounter){
        encounterhistory.getEncounterList().add(encounter);
    }

    public Encounter newEncounter(String chiefcomplaint, Event ev) {
        return encounterhistory.newEncounter(chiefcomplaint, ev);
    }

    public Clinic getClinic() {
        return clinic;
    }

    public VaccinationHistory getVaccinationHistory() {
        return vaccinationHistory;
    }

    public void addVaccinationOrder(VaccinationOrder vo){
        vaccinationOrders.add(vo);
    }

    public void addMedicationOrder(MedicationOrder mo){
        medicationOrders.add(mo);
    }

    public void addAssessmentOrder(AssessmentOrder ao){
        assessmentOrders.add(ao);
    }

    public void addTreatmentOrder(TreatmentOrder to){
        treatmentOrders.add(to);
    }

    public ArrayList<AssessmentOrder> getAssessmentOrders() {
        return assessmentOrders;
    }

    public ArrayList<MedicationOrder> getMedicationOrders() {
        return medicationOrders;
    }

    public ArrayList<TreatmentOrder> getTreatmentOrders() {
        return treatmentOrders;
    }

    public ArrayList<VaccinationOrder> getVaccinationOrders() {
        return vaccinationOrders;
    }

    public MedicationHistory getMedicationHistory() {
        return medicationHistory;
    }

    public AlergyHistory getAlergyhistory() {
        return alergyhistory;
    }

    public void setAlergyhistory(AlergyHistory alergyhistory) {
        this.alergyhistory = alergyhistory;
    }

    public void addAllergy(Alergy alergy){
        alergyhistory.addAlergyInfo(alergy);
    }

    public String getPastientLastestSeenTimeString(){
        return encounterhistory.pickLastedEncounter().getEvent().getDateString();
    }

    public String getPastientLastestSeenLocation(){
        return encounterhistory.pickLastedEncounter().getEvent().getSite().getLocationString();
    }


    public void printVaccinationHistoryReport(){
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("                              " + person.getPersonId() + "'s Vaccination History");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        if (vaccinationHistory.getVaccinations().isEmpty()){
            System.out.println("No Vaccination History Found.");
            System.out.println(); 
            return;
        }
        System.out.printf("%-4s | %-30s | %-30s | %-5s | %-28s\n","no. ","Vaccination ","Prevented Disease ","Dose ","Date ");

        for(Vaccination vaccination: vaccinationHistory.getVaccinations()){
            int index = vaccinationHistory.getVaccinations().indexOf(vaccination);
            String vaccineName = vaccination.getvOrderItem().getSelectedVaccine().getVaccineName();
            String preventedDisease = vaccination.getvOrderItem().getSelectedVaccine().getPreventedInfectiousDisease();
            int dose = vaccination.getvOrderItem().getDose();
            Date date = vaccination.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String formattedDate = dateFormat.format(date);
            System.out.printf("%-4s | %-30s | %-30s | %-5s | %-28s\n",(index + 1) + ". " , vaccineName , preventedDisease , dose , formattedDate);
        }
        System.out.println(); 
    }

    public void printAssessmentReport(){
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("                              " + person.getPersonId() + "'s Assessment Order Report");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        if (assessmentOrders.isEmpty()){
            System.out.println("No Assessment Order Found.");
            System.out.println(); 
            return;
        }
        System.out.printf("%-4s | %-40s | %-10s | %-12s\n", "no. " , "Assessment " , "Result ", "Date ");
        for (AssessmentOrder assessmentOrder: assessmentOrders){
            int index = assessmentOrders.indexOf(assessmentOrder);
            String assessmentName = assessmentOrder.getassessmentName();
            String assessmentResult = "";
            if(assessmentOrder.getResult()){
                assessmentResult = "Positive";
            } else {
                assessmentResult = "Negative";
            }
            Date date = assessmentOrder.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String formattedDate = dateFormat.format(date);
            System.out.printf("%-4s | %-40s | %-10s | %-12s\n", (index + 1) + ". " , assessmentName , assessmentResult , formattedDate);
        }
        System.out.println(); 
    }

    public void printMedicationHistory(){
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("                              " + person.getPersonId() + "'s Medication History");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        if (medicationHistory.getDrugs().isEmpty()){
            System.out.println("No Medication Information Found.");
            System.out.println(); 
            return;
        }
        System.out.printf("%-4s | %-40s | %-25s\n","no. ", "Medication " , "Indicitions ");
        for (Drug drug: medicationHistory.getDrugs()){
            int index = medicationHistory.getDrugs().indexOf(drug);
            String drugName = drug.getName();
            String treatedDisease = drug.getTreatedDisease();
            System.out.printf("%-4s | %-40s | %-25s\n",(index + 1) + ". " , drugName , treatedDisease);
        }
        System.out.println(); 

    }

    public void printDiagnosisReport(){
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                              " + person.getPersonId() + "'s Diagnosis History");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s | %-12s | %-30s | %-30s | %-18s\n","no. ","Date ","Chief Complaint ","Diagnosis ","Treatment Plan ");

        if (encounterhistory.getEncounterList().isEmpty()){
            System.out.println("No Encounter Information Found.");
            System.out.println(); 
            return;
        }
        for (Encounter encounter: encounterhistory.getEncounterList()){
            int index = encounterhistory.getEncounterList().indexOf(encounter);
            Date date = encounter.getEvent().getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String formattedDate = dateFormat.format(date);
            String chiefComplaint = encounter.getChiefComplaint();
            String diagnosisCategory = encounter.getDiagnosis().getDiseaseName() + " (" + encounter.getDiagnosis().getCategory()+ ") ";
            String treatment = "";

            for (VaccinationOrder order : vaccinationOrders) {
                if (order.getEncounter() != null) {
                    if (order.getEncounter().equals(encounter)) {
                        treatment += "Vaccination Order, ";
                    }
                }
            }
            for (AssessmentOrder order : assessmentOrders) {
                if (order.getEncounter() != null) {
                    if (order.getEncounter().equals(encounter)) {
                        treatment += "Assessment Order, ";
                    }
                }
            }
            for (MedicationOrder order : medicationOrders) {
                if (order.getEncounter() != null) {
                    if (order.getEncounter().equals(encounter)) {
                        treatment += "Medication Order, ";
                    }
                }
            }
            for (TreatmentOrder order : treatmentOrders) {
                if (order.getEncounter() != null) {
                    if (order.getEncounter().equals(encounter)) {
                        treatment += "Treatment Order, ";
                    }
                }
            }
    
            if (treatment.isEmpty()) {
                System.out.printf("%-4s | %-12s | %-30s | %-30s | %-18s\n", (index + 1) + ". ", formattedDate, chiefComplaint, diagnosisCategory, "No treatment orders found.");
            } else {
                // Remove the trailing comma and space from the treatment string
                treatment = treatment.substring(0, treatment.length() - 2);
                System.out.printf("%-4s | %-12s | %-30s | %-30s | %-18s\n", (index + 1) + ". ", formattedDate, chiefComplaint, diagnosisCategory, treatment);
            }
        }
        System.out.println(); 
        
    }

    public void printPersonalInfo() {
        System.out.println("------------------------------------------------------------");
        System.out.println("       " + person.getPersonId() + "'s Personal Information");
        System.out.println("------------------------------------------------------------");
        System.out.println("Patient Name: " + person.getPersonId());
        System.out.println("Age: " + person.getAge());
        System.out.println();
        System.out.println("----------- Parents -----------");
        System.out.println("Father: " + person.getFather().getPersonId() + ", Age: " + person.getFather().getAge());
        System.out.println("Mother: " + person.getMother().getPersonId() + ", Age: " + person.getMother().getAge());
        System.out.println();
        System.out.println("----------- Siblings -----------");
        if (person.getSiblings().isEmpty()){
            System.out.println("No Sibling");
        } else {
        for (Person sibling : person.getSiblings()) {
            System.out.println("Name: " + sibling.getPersonId() + ", Age: " + sibling.getAge());
        }
    }
        System.out.println();
        System.out.println("----------- Allergy History -----------");
        if (alergyhistory.getAlergies().isEmpty()){
            System.out.println("No Allergy");
        } else {
        for (Alergy alergy : alergyhistory.getAlergies()) {
            System.out.println(alergy.getAllergens());
        }
    }
        System.out.println();
        System.out.println("----------- Vaccination Record -----------");
        for (Vaccination vaccination : vaccinationHistory.getVaccinations()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String formattedDate = dateFormat.format(vaccination.getDate());
            System.out.printf("%-25s | %-18s\n", vaccination.getvOrderItem().getSelectedVaccine().getVaccineName() , formattedDate);
        }
        System.out.println();
        Encounter latestEncounter = encounterhistory.pickLastedEncounter();
        System.out.println("----------- Latest Encounter -----------");
        System.out.println("Chief Complaint: " + latestEncounter.getChiefComplaint());
        System.out.println("Diagnosis: " + latestEncounter.getDiagnosis().getDiseaseName() + "; Type: " + latestEncounter.getDiagnosis().getCategory()+ "; Confirmed: " + latestEncounter.getDiagnosis().isConfirmed());
        if (latestEncounter.getDiagnosis().isConfirmed() == true){
            System.out.println("Person's LastSeen: " + person.getLastSeen());
        }
        else System.out.println("There is no record for non-infected patient");
        System.out.println();

    }


}
