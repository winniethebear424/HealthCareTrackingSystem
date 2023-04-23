/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Catalogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import PatientManagement.Patient.ClinicalHistory.Vaccine;

/**
 *
 * @author kal bugrara
 */
public class VOrderInventoryCatalog {
    private ArrayList<Vaccine> vaccines;
    private Map<Vaccine, Integer> vaccineInventory;

    public VOrderInventoryCatalog() {
        vaccines = new ArrayList<Vaccine>();
        vaccineInventory = new HashMap<Vaccine, Integer>();
    }

    public void addNewVaccine(Vaccine vaccine, int initialInventory) {
        vaccines.add(vaccine);
        vaccineInventory.put(vaccine, initialInventory);
    }

    public void removeVaccine(Vaccine vaccine) {
        vaccines.remove(vaccine);
        vaccineInventory.remove(vaccine);
    }

    public void checkInventory(Vaccine vaccine, int doseCount) {
        // if the vaccine is not present in the map, getOrDefault() will return the default value of 0
        int inventory = vaccineInventory.getOrDefault(vaccine, 0);
        if (inventory >= doseCount){
            System.out.println("Sufficient Stock: " + inventory + " available");
        }
        else{
            System.out.println("Insufficient Stock: only " + inventory + " available");
        }
    }

    public void decrementInventory(Vaccine vaccine, int doseCount) {
        int inventory = vaccineInventory.getOrDefault(vaccine, 0);
        vaccineInventory.put(vaccine, inventory - doseCount);
    }

    public void incrementInventory(Vaccine vaccine, int doseCount) {
        int inventory = vaccineInventory.getOrDefault(vaccine, 0);
        vaccineInventory.put(vaccine, inventory + doseCount);
    }

    public ArrayList<Vaccine> getVaccines() {
        return vaccines;
    }

    public int getInventoryCount(Vaccine vaccine) {
        return vaccineInventory.getOrDefault(vaccine, 0);
    }

}


