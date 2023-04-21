/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Catalogs;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class DrugCatalog {
    ArrayList<Drug> drugs;

    public DrugCatalog() {
        drugs = new ArrayList<Drug>();
    }

    public void addDrug(Drug drugToAdd) {
        this.drugs.add(drugToAdd);
    }
    
    public ArrayList<Drug> getDrugs() {
        return drugs;
    }
    
    public Drug pickRandomDrug(){
        if (drugs.size() == 0) return null;
        Random random = new Random();
        int randomIndex = random.nextInt(drugs.size());
        return drugs.get(randomIndex);
    }

}
