/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import PatientManagement.Clinic.Clinic;

/**
 *
 * @author kal bugrara
 */
public class Diagnosis {
    String diseaseName;
    String category; // "Infectious" or "Hereditary"
    boolean confirmed = false;
    Condition condition;

    public Diagnosis(String diseaseName, String cat, boolean c) {
        this.diseaseName = diseaseName;
        category = cat;
        confirmed = c; // Assumption: if true then lab tests confirm that it is a diesease
    }
    
    public String getCategory() {
        return category;
    }

    public boolean isConfirmedInfectious(){
        if ( category == "Infectious" && confirmed){
            return true;
        } else{
            return false;
        }
    }

    public boolean isConfirmed() {
        return confirmed; // just return the boolean
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public void setconfirmed(){
        this.confirmed = true;        
    }

}
