/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

/**
 *
 * @author kal bugrara
 */
public class Alergy {
    String allergen;

    public Alergy(String allergen) {
        this.allergen = allergen;
    }

    public String getAllergens() {
        return allergen;
    }

    public void setAllergens(String allergen) {
        this.allergen = allergen;
    }

}
