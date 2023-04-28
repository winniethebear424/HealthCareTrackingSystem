/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import java.util.Date;

import PatientManagement.Catalogs.AgeGroup;
import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.InNetworkHealthCareCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Orders.AssessmentOrder;
import PatientManagement.Patient.Orders.MedicationOrder;
import PatientManagement.Patient.Orders.Order;
import PatientManagement.Patient.Orders.TreatmentOrder;
import PatientManagement.Patient.Orders.VaccinationOrder;

/**
 *
 * @author kal bugrara
 * 
 */
// vorder = encounter.newVaccinationOrder();
// vacorder.newVaccination();

public class Encounter {
    Patient patient;
    String chiefComplaint;
    Diagnosis diagnosis;
    Event event;
    VitalSigns vitalSigns;
    EncounterHistory encounterHistory;
    VaccinationOrder vaccinationOrder;
    AssessmentOrder assessmentOrder;
    MedicationOrder medicationOrder;
    TreatmentOrder treatmentOrder;

    public Encounter(Patient patient, String chiefComplaint, Diagnosis diagnosis, Event event,
            EncounterHistory encounterHistory) {
        this.patient = patient;
        this.chiefComplaint = chiefComplaint;
        this.diagnosis = diagnosis;
        this.event = event;
        vitalSigns = new VitalSigns(this);
        this.encounterHistory = encounterHistory;
    }

    public Encounter(Patient patient, String chiefComplaint, Diagnosis diagnosis, Event event,
            VaccinationOrder vaccinationOrder, AssessmentOrder assessmentOrder, MedicationOrder medicationOrder,
            TreatmentOrder treatmentOrder) {
        this.patient = patient;
        this.chiefComplaint = chiefComplaint;
        this.diagnosis = diagnosis;
        this.event = event;
        vitalSigns = new VitalSigns(this);
        // this.encounterHistory = encounterHistory;
        patient.getPerson().addSeen(event.getDateString(), event.getSite().getLocationString());

        if (vaccinationOrder != null) {
            this.vaccinationOrder = vaccinationOrder;
            vaccinationOrder.setEncounter(this);
        }

        if (assessmentOrder != null) {
            this.assessmentOrder = assessmentOrder;
            assessmentOrder.setEncounter(this);
        }

        if (medicationOrder != null) {
            this.medicationOrder = medicationOrder;
            medicationOrder.setEncounter(this);
        }

        if (treatmentOrder != null) {
            this.treatmentOrder = treatmentOrder;
            treatmentOrder.setEncounter(this);
        }
    }

    public Encounter(Patient p, String cc, Event ev, EncounterHistory eh) { // event is the date when the check was made
        chiefComplaint = cc;
        event = ev;
        patient = p;
        encounterHistory = eh;
    }

    public Encounter(Patient patient, VaccinationOrder vaccinationOrder) {
        this.patient = patient;
        this.vaccinationOrder = vaccinationOrder;
    }

    public void newDiagnosis(String diseaseName, String diseasetype, boolean confirmed) {
        diagnosis = new Diagnosis(diseaseName, diseasetype, confirmed);
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public Patient getPatient() {
        return patient;
    }

    public Limits getVitalSignLimits(int age, String name) {
        Clinic clinic = this.getPatient().getClinic();
        VitalSignsCatalog vsc = clinic.getVitalSignsCatalog();
        return vsc.findVitalSignLimits(age, name);
    }

    public VitalSignMetric addNewVitals(String name, int value) {
        return vitalSigns.addNewVitals(name, value);
    }

    // public EncounterHistory getEncounterHistory() {
    // return encounterHistory;
    // }

    public boolean areVitalsNormal() {
        return vitalSigns.areNormal();
    }

    public Event getEvent() {
        return event;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public VaccinationOrder getVaccinationOrder() {
        return vaccinationOrder;
    }

    public AssessmentOrder getAssessmentOrder() {
        return assessmentOrder;
    }

    public MedicationOrder getMedicationOrder() {
        return medicationOrder;
    }

    public TreatmentOrder getTreatmentOrder() {
        return treatmentOrder;
    }

    public void findLocalMedicalSource(InNetworkHealthCareCatalog inNetworkHealthCareCatalog) {

    }

    public void printEncounterSummary() {
        String order = "";
        if (vaccinationOrder != null) {
            order = "Vaccination Order";
        } else if (assessmentOrder != null) {
            order = "Assessment Order";
        } else if (medicationOrder != null) {
            order = "Medication Order";
        } else if (treatmentOrder != null) {
            order = "Treatment Order";
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("                      Encounter Summary                     ");
        System.out.println("------------------------------------------------------------");
        System.out.println("Patient Name: " + patient.getPerson().getPersonId());
        System.out.println("Date: " + event.getDate());
        System.out.println("Chief Complaint: " + chiefComplaint);
        System.out.println("Diagnosis: " + diagnosis.getDiseaseName() + "; Type: " + diagnosis.getCategory()
                + "; Confirmed: " + diagnosis.isConfirmed());
        System.out.println("Order: " + order);
        System.out.println();
        if (vaccinationOrder != null) {
            vaccinationOrder.printVaccinationOrderDetail();
        } else if (assessmentOrder != null) {
            assessmentOrder.printAssessmentOrderDetail();
        } else if (medicationOrder != null) {
            medicationOrder.printMedicationOrderDetail();
        } else if (treatmentOrder != null) {
            treatmentOrder.printTreatmentOrderDetail();
        }
        System.out.println();
        System.out.println("----------------------------- Vital Signs -----------------------------");
        System.out.printf("%-40s | %-7s | %-13s | %-10s\n", "Vital Sign ", "Value ", "Normal Range ", "Normal ");
        for (VitalSignMetric vitalSignMetric : vitalSigns.getVitalSigns()) {
            String vsName = vitalSignMetric.getName();
            int vsValue = vitalSignMetric.getValue();
            String vsRange = vitalSignMetric.getUpperLower().getRangeString();
            Boolean vsResult = vitalSignMetric.isNormal();
            System.out.printf("%-40s | %-7s | %-13s | %-10s\n", vsName, vsValue, vsRange, vsResult);
        }

        System.out.println();
    }

}