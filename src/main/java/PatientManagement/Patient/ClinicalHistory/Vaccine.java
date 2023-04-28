/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import com.github.javafaker.Faker;

/**
 *
 * @author kal bugrara
 */
public class Vaccine {
    private String vaccineName;
    private String preventedInfectiousDisease;

    public Vaccine(String vaccineName, String preventedInfectiousDisease){
        this.vaccineName = vaccineName;
        this.preventedInfectiousDisease = preventedInfectiousDisease;
    }

    public Vaccine(String vaccineName){
        this.vaccineName = vaccineName;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getPreventedInfectiousDisease() {
        return preventedInfectiousDisease;
    }

   
    
}
