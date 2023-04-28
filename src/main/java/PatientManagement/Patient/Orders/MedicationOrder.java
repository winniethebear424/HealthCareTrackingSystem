/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

import java.util.ArrayList;
import java.util.Date;

import PatientManagement.Catalogs.Drug;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Encounters.Encounter;

/**
 *
 * @author kal bugrara
 */
public class MedicationOrder {
    Patient patient;
    ArrayList<Drug> drugs;
    Date date;
    Clinic clinic;
    Encounter encounter;

    public MedicationOrder(Patient patient, ArrayList<Drug> drugs, Date date, Clinic clinic) {
        this.patient = patient;
        this.drugs = drugs;
        this.date = date;
        this.clinic = clinic;
        for(Drug drug: drugs){
            patient.getMedicationHistory().addMedication(drug);
        }
    }

    public MedicationOrder(String drugName){
        Drug drug = new Drug(drugName);
        drugs = new ArrayList<Drug>();
        drugs.add(drug);
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }
    
    public Encounter getEncounter() {
        return encounter;
    }

    public Patient getPatient() {
        return patient;
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public Date getDate() {
        return date;
    }
    public Clinic getClinic() {
        return clinic;
    }

    public void printMedicationOrderDetail(){
        System.out.println("-------- Medications Order Detail -------");
        for (Drug drug: drugs){
            System.out.println("Medications: " + drug.getName());
            System.out.println("Indictions: for the relief of the " + drug.getTreatedDisease());
        }
        
    }
    
}
