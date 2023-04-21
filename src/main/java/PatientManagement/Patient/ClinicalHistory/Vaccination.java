/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import java.util.Date;

import PatientManagement.Catalogs.VOrderItem;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Patient.Patient;

/**
 *
 * @author kal bugrara
 */
public class Vaccination {
    // represent a completed administration of a vaccine to a patient
    VOrderItem vOrderItem;
    Date date;
    int dose;
    Clinic clinic;

    public Vaccination(VOrderItem vOrderItem, Date date, int dose, Clinic clinic) {
        this.vOrderItem = vOrderItem;
        this.date = date;
        this.dose = dose;
        this.clinic = clinic;
    }

    public VOrderItem getvOrderItem() {
        return vOrderItem;
    }

    public Date getDate() {
        return date;
    }

    public int getDose() {
        return dose;
    }

    public Clinic getClinic() {
        return clinic;
    }

}
