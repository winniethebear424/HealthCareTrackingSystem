/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import com.github.javafaker.Faker;

import PatientManagement.Catalogs.DrugCatalog;
import PatientManagement.Catalogs.VaccineCatalog;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.ClinicalHistory.DiseaseCatalog;
import PatientManagement.Persona.Person;
import PatientManagement.Persona.PersonDirectory;

/**
 *
 * @author kal bugrara
 */
public class Clinic {
    PatientDirectory patientdirectory;
    PersonDirectory persondirectory;
    SiteCatalog sitelist;
    LocationList locationlist;
    DrugCatalog drugcatalog;
    EventSchedule eventschedule;
    VitalSignsCatalog vitalSignsCatalog;
    String name; // name of the clinic
    VaccineCatalog vaccineCatalog;
    DiseaseCatalog diseaseCatalog;

    public Clinic(String n) {
        eventschedule = new EventSchedule();
        sitelist = new SiteCatalog();
        locationlist = new LocationList();
        persondirectory = new PersonDirectory();
        patientdirectory = new PatientDirectory(this);
        vitalSignsCatalog = new VitalSignsCatalog();
        name = n;
        vaccineCatalog = new VaccineCatalog();
        drugcatalog = new DrugCatalog();
        diseaseCatalog = new DiseaseCatalog();
    }

    public SiteCatalog getSiteCatalog() {
        return sitelist;
    }

    public LocationList getLocationList() {
        return locationlist;
    }

    public Site newSite(Location location) {

        Site s = sitelist.newSite(location);
        return s;
    }

    public VitalSignsCatalog getVitalSignsCatalog() {
        return vitalSignsCatalog;
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public PatientDirectory getPatientDirectory() {
        return patientdirectory;
    }

    public VaccineCatalog getVaccineCatalog() {
        return vaccineCatalog;
    }

    public EventSchedule getEventschedule() {
        return eventschedule;
    }

    public DrugCatalog getDrugcatalog() {
        return drugcatalog;
    }

    public DiseaseCatalog getDiseaseCatalog() {
        return diseaseCatalog;
    }

}
