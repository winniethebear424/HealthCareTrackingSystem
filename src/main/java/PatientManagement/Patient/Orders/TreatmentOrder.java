/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

import java.util.Date;

import PatientManagement.Clinic.Clinic;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Encounters.Encounter;

/**
 *
 * @author kal bugrara
 */
public class TreatmentOrder {
    Patient patient;
    String treatment;
    Date date;
    Clinic clinic;
    Encounter encounter;

    public TreatmentOrder(Patient patient, String treatment, Date date, Clinic clinic) {
        this.patient = patient;
        this.treatment = treatment;
        this.date = date;
        this.clinic = clinic;
    }

    public TreatmentOrder(String treatment) {
        this.treatment = treatment;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }
    
    public Patient getPatient() {
        return patient;
    }
    public String getTreatment() {
        return treatment;
    }
    public Date getDate() {
        return date;
    }
    public Clinic getClinic() {
        return clinic;
    }
    public Encounter getEncounter() {
        return encounter;
    }

    public void printTreatmentOrderDetail(){
        System.out.println("-------- Treatment Order Detail -------");
        System.out.println("Treatment Name: " + treatment);
    }
    

    
    
}
