/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Orders.AssessmentOrder;
import PatientManagement.Patient.Orders.MedicationOrder;
import PatientManagement.Patient.Orders.Order;
import PatientManagement.Patient.Orders.TreatmentOrder;
import PatientManagement.Patient.Orders.VaccinationOrder;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class EncounterHistory {
    ArrayList<Encounter> encounters;
    Patient patient;

    public EncounterHistory(Patient p) {
        patient = p;
        encounters = new ArrayList<Encounter>();
    }

    // each encounter must link to the event at the site
    public Encounter newEncounter(String chiefcomplaint, Event ev) {
        Encounter e = new Encounter(patient, chiefcomplaint, ev, this);
        encounters.add(e);
        return e;
    }

    public Encounter newEncounter(Patient patient, String chiefComplaint, Diagnosis diagnosis, Event event, VaccinationOrder vaccinationOrder, AssessmentOrder assessmentOrder, MedicationOrder medicationOrder, TreatmentOrder treatmentOrder){
        Encounter encounter = new Encounter(patient, chiefComplaint, diagnosis, event, vaccinationOrder, assessmentOrder, medicationOrder, treatmentOrder);
        encounters.add(encounter);
        return encounter;
    }

    public void addEncounter(Encounter encounter){
        encounters.add(encounter);
    }
    

    public ArrayList<Encounter> getEncounterList() {
        return encounters;
    }

    public Patient getPatient() {
        return patient;
    }

    public Encounter pickRandomeEncounter(){
        if (encounters.size() == 0) return null;
        Random r = new Random();
        int randonIndex = r.nextInt(encounters.size());
        return encounters.get(randonIndex);
    }

    public Encounter pickLastedEncounter(){
        if (encounters.size() == 0) return null;
        return encounters.get(encounters.size() - 1);
    }

    public int getConfirmedDiagnosesCount() {
        int count = 0;
        for( Encounter encounter: encounters){
            if (encounter.getDiagnosis().isConfirmed()) {
                count++;
            }
        }
        return count;
    }
    

}
