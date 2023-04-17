/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Patient.Patient;

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
    // vital signs
    // orders: assessmentorders, ....

    public Encounter(Patient p, String cc, Event ev, EncounterHistory eh) { // event is the date when the check was made
        chiefComplaint = cc;
        event = ev;
        patient = p;
        encounterHistory = eh;
        vitalSigns = new VitalSigns(this);
    }

    public void newDiagnosis(String diseasetype, boolean confirmed) {
        diagnosis = new Diagnosis(diseasetype, confirmed);
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public Limits getVitalSignLimits(int age, String name) {
        Clinic clinic = encounterHistory.getPatient().getClinic();
        VitalSignsCatalog vsc = clinic.getVitalSignsCatalog();
        return vsc.findVitalSignLimits(age, name);
    }

    public VitalSignMetric addNewVitals(String name, int value) {
        return vitalSigns.addNewVitals(name, value);
    }

    public EncounterHistory getEncounterHistory() {
        return encounterHistory;
    }

    public boolean areVitalsNormal() {
        return vitalSigns.areNormal();
    }
}