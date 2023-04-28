/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

import java.util.ArrayList;
import java.util.Date;

import PatientManagement.Clinic.Clinic;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.VitalSigns;

/**
 *
 * @author kal bugrara
 */
public class AssessmentOrder {
    String assessmentName;
    Patient patient;
    Date date;
    Clinic clinic;
    Boolean result;
    Encounter encounter;

    public AssessmentOrder(String assessmentName, Patient patient, Date date, Clinic clinic) {
        this.assessmentName = assessmentName;
        this.patient = patient;
        this.date = date;
        this.clinic = clinic;
    }

    public AssessmentOrder(String assessmentName) {
        this.assessmentName = assessmentName;
    }
    
    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public String getassessmentName() {
        return assessmentName;
    }

    public Patient getPatient() {
        return patient;
    }

    public Date getDate() {
        return date;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
    
    public Encounter getEncounter() {
        return encounter;
    }

    public void printAssessmentOrderDetail(){
        System.out.println("-------- Assessment Order Detail -------");
        System.out.println("Assessment Name: " + assessmentName);
        System.out.println("Result: " + result);
    }


}
