/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.crypto.Data;

import PatientManagement.Catalogs.VOrderItem;
import PatientManagement.Patient.Patient;

/**
 *
 * @author kal bugrara
 */
public class VaccinationHistory {

    ArrayList<Vaccination> vaccinations;
    Patient patient;

    public VaccinationHistory(Patient patient){
        vaccinations = new ArrayList<Vaccination>();
        this.patient = patient;
    }

    public ArrayList<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public Patient getPatient() {
        return patient;
    }

    public void addVaccination(Vaccination v) {
        vaccinations.add(v);
    }

}