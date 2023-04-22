/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Encounters.Diagnosis;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class DiseaseCatalog {
    ArrayList<Diagnosis> diseaselist; // only confirmed diagnosis get added to the disease catalog
    // record confirmed infectious diseases and hereditary diseases
    ArrayList<Diagnosis> infectiousDiseaselist;
    ArrayList<Diagnosis> hereditaryDiseaselist;

    public DiseaseCatalog() {
        diseaselist = new ArrayList<Diagnosis>();
        infectiousDiseaselist = new ArrayList<Diagnosis>();
        hereditaryDiseaselist = new ArrayList<Diagnosis>();
    }

    public void addConfirmedDiagnosis(Diagnosis diagnosis){
        diseaselist.add(diagnosis);
        if (diagnosis.getCategory() == "Infectious"){
            infectiousDiseaselist.add(diagnosis);
        } else {
            hereditaryDiseaselist.add(diagnosis);
        }
    }

    public String pickRandomInfectiousDisease(){
        if (infectiousDiseaselist.size() == 0) return null;
        Random r = new Random();
        int randonIndex = r.nextInt(infectiousDiseaselist.size());
        return infectiousDiseaselist.get(randonIndex).getDiseaseName();
    }

    public String pickRandomHereditaryDisease(){
        if (hereditaryDiseaselist.size() == 0) return null;
        Random r = new Random();
        int randonIndex = r.nextInt(hereditaryDiseaselist.size());
        return hereditaryDiseaselist.get(randonIndex).getDiseaseName();
    }        

    public ArrayList<Diagnosis> getDiseaselist() {
        return diseaselist;
    }
    public void setDiseaselist(ArrayList<Diagnosis> diseaselist) {
        this.diseaselist = diseaselist;
    }
    public ArrayList<Diagnosis> getInfectiousDiseaselist() {
        return infectiousDiseaselist;
    }
    public void setInfectiousDiseaselist(ArrayList<Diagnosis> infectiousDiseaselist) {
        this.infectiousDiseaselist = infectiousDiseaselist;
    }
    public ArrayList<Diagnosis> getHereditaryDiseaselist() {
        return hereditaryDiseaselist;
    }
    public void setHereditaryDiseaselist(ArrayList<Diagnosis> hereditaryDiseaselist) {
        this.hereditaryDiseaselist = hereditaryDiseaselist;
    }

}
