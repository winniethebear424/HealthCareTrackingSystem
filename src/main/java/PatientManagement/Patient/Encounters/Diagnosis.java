/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

/**
 *
 * @author kal bugrara
 */
public class Diagnosis {
    String category; // infectious or Hereditary
    boolean confirmed = false;
    Condition condition;

    public Diagnosis(String cat, boolean c) {
        category = cat;
        confirmed = c; // Assumption: if true then lab tests confirm that it is a diesease
    }

    public boolean isConfirmed() {
        return confirmed; // just return the boolean
    }

}
