/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.ClinicalHistory.Vaccine;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;

import java.util.ArrayList;
import java.util.Date;

import PatientManagement.Catalogs.VOrderItem;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Patient.Patient;

/**
 *
 * @author kal bugrara
 */
public class VaccinationOrder extends Order {
    ArrayList<VOrderItem> vaccinationOrderItems;
    Patient patient;
    Date date;
    Clinic clinic;
    Encounter encounter;

    public VaccinationOrder(ArrayList<VOrderItem> vaccinationOrderItems, Patient patient, Date date, Clinic clinic) {
        // calling the default constructor of its parent class, Order.
        super();
        this.vaccinationOrderItems = vaccinationOrderItems;
        this.patient = patient;
        this.date = date;
        this.clinic = clinic;
    }

    public VaccinationOrder(String vacineName){
        vaccinationOrderItems = new ArrayList<VOrderItem>();
        Vaccine v = new Vaccine(vacineName);
        VOrderItem vOrderItem = new VOrderItem(v);
        vaccinationOrderItems.add(vOrderItem);
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter){
        this.encounter = encounter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVOrderItemName(){
        String vOrderItemName = "";
        for (VOrderItem vOrderItem: vaccinationOrderItems){
            vOrderItemName += vOrderItem.getSelectedVaccine().getVaccineName() + ", ";
            // Remove the last comma and space
            if (!vOrderItemName.isEmpty()) {
                vOrderItemName = vOrderItemName.substring(0, vOrderItemName.length() - 2);
            }
        }
        return vOrderItemName;
    }

    public void ExecuteOrder() {
        // add vaccination to the patient's vaccination history
        for (VOrderItem vOrderItem : vaccinationOrderItems) {
            this.patient.getVaccinationHistory().addVaccination(
                    new Vaccination(vOrderItem, date, vOrderItem.getDose(), clinic));
        }
    }

    // public VOrderItem newVOrderItem(Vaccine selectedVaccine, int unitPrice, int
    // dose) {
    // VOrderItem vOrderItem = new VOrderItem(selectedVaccine, unitPrice, dose);
    // vaccinationOrderItems.add(vOrderItem);
    // return vOrderItem;
    // }

    public int getVaccinationOrderTotal() {
        int sum = 0;
        for (VOrderItem vOrderItem : vaccinationOrderItems) {
            sum += vOrderItem.getVOrderItemTotal();
        }
        return sum;
    }

    public void printVaccinationOrderDetail(){
        System.out.println("-------- Vaccination Order Detail -------");
        for (VOrderItem item : vaccinationOrderItems) {
            System.out.println("Vaccine: " + item.getSelectedVaccine().getVaccineName());
            System.out.println("Disease Prevented: " + item.getSelectedVaccine().getPreventedInfectiousDisease());
            System.out.println("Dose: " + item.getDose());
        }
    }

}
