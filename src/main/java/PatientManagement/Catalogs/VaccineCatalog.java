/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Catalogs;

import java.util.ArrayList;
import java.util.Random;

import PatientManagement.Patient.ClinicalHistory.Vaccine;

/**
 *
 * @author kal bugrara
 */
public class VaccineCatalog {
    ArrayList<Vaccine> vaccines; //list of vaccines initially empty

    public VaccineCatalog(){
        this.vaccines = new ArrayList<Vaccine>();
    }

    public void addVaccine(Vaccine vaccineToAdd) {
        this.vaccines.add(vaccineToAdd);
    }

    public ArrayList<Vaccine> getVaccines() {
        return vaccines;
    }
    
    public Vaccine pickRandomVaccine(){
        if (vaccines.size() == 0) return null;
        Random random = new Random();
        int randomIndex = random.nextInt(vaccines.size());
        return vaccines.get(randomIndex);
    }

}
