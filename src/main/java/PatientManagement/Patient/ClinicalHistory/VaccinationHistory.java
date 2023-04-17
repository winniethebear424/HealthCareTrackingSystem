/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class VaccinationHistory {

    ArrayList<Vaccination> vaccinations;

    public void addVaccination(Vaccination v) {
        vaccinations.add(v);
    }

}