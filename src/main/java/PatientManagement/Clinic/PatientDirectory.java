/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class PatientDirectory {
    Clinic clinic;
    ArrayList<Patient> patients;
    ArrayList<Patient> infectiousPaitents;
    ArrayList<Patient> confirmedPatients;

    PatientDirectory(Clinic clinic) {
        this.clinic = clinic;
        patients = new ArrayList<Patient>();
        infectiousPaitents = new ArrayList<Patient>();
        confirmedPatients = new ArrayList<Patient>();
    }

    public Patient findPatientByName (String patientName){
        for (Patient patient: patients){
            if(patient.getPerson().getId() == patientName){}
            return patient;
        }
        return null;
    }

    public Patient findPatientByNameAndAge (String patientName, int age){
        for (Patient patient: patients){
            if(patient.getPerson().getId() == patientName && patient.getPerson().getAge() == age){}
            return patient;
        }
        return null;
    }

    public int getConfirmedPositiveTotals() {
        int sum = 0;

        for (Patient p : patients) {
            if (p.isConfirmedPositive()) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    public ArrayList<Patient> getAllConfirmedPositives() {
        ArrayList<Patient> temp = new ArrayList<Patient>();
        for (Patient p : patients) {
            if (p.isConfirmedPositive() == true) {
                temp.add(p);
            }
        }
        return temp; // has the list of encounters with confirmed diagnosis
    }

    public Patient newPatient(Person person) {
        Patient patient = new Patient(person, clinic);
        patients.add(patient);
        return patient;
    }

    public Patient pickRandomPatient(){
        if (patients.size() == 0) return null;
        Random r = new Random();
        int randonIndex = r.nextInt(patients.size());
        return patients.get(randonIndex);
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }
    

    public void collectInfectiousPatient(){
        for (Patient sickPatient: patients){
            if (sickPatient.isConfirmedInfectiousPositive()){
                infectiousPaitents.add(sickPatient);
            } 
        }
    }

    public void collectconfirmedPatients(){
        for (Patient sickPatient: patients){
            if (sickPatient.isConfirmedPositive()){
                confirmedPatients.add(sickPatient);
            } 
        }
    }

    public Patient pickRandomInfectiousPatient(){
        if (infectiousPaitents.size() == 0) return null;
        Random r = new Random();
        int randonIndex = r.nextInt(infectiousPaitents.size());
        return infectiousPaitents.get(randonIndex);
    }

    public ArrayList<Patient> getInfectiousPaitents() {
        return infectiousPaitents;
    }

    public ArrayList<Patient> getConfirmedPatients() {
        return confirmedPatients;
    }

}