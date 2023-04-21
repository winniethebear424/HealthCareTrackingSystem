/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import java.util.ArrayList;

import PatientManagement.Catalogs.Drug;
import PatientManagement.Patient.Patient;

/**
 *
 * @author kal bugrara
 */
public class MedicationHistory {
    ArrayList<Drug> drugs;
    Patient patient;

    public MedicationHistory(ArrayList<Drug> drugs, Patient patient) {
            this.drugs = drugs;
            this.patient = patient;
        }

    public MedicationHistory(Patient patient) {
        drugs = new ArrayList<Drug>();
        this.patient = patient;
    }


    public void addMedication(Drug drug) {
        drugs.add(drug);
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public Patient getPatient() {
        return patient;
    }

}