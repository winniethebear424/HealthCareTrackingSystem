
import com.github.javafaker.Faker;

import PatientManagement.Catalogs.AgeGroup;
import PatientManagement.Catalogs.VitalSignLimits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.EventSchedule;
import PatientManagement.Clinic.InNetworkHealthCareCatalog;
import PatientManagement.Clinic.Location;
import PatientManagement.Clinic.LocationList;
import PatientManagement.Clinic.PatientDirectory;
import PatientManagement.Clinic.Site;
import PatientManagement.Clinic.SiteCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.VitalSignMetric;
import PatientManagement.Persona.Person;
import PatientManagement.Persona.PersonDirectory;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kal bugrara
 */

public class PatientCareMain {

    private ArrayList<Person> personlist;
    private ArrayList<Person> potentialInfected;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // build a clinic and load relevant data
        Clinic clinic = ConfigureAMedicalSystem.createAClinicAndLoadData("NEU Bug Writers");
        PatientDirectory patientDirectory = clinic.getPatientDirectory();
        InNetworkHealthCareCatalog inNetworkHealthCareCatalog = clinic.getNetworkHealthCareList();


        // pick a random patient or find a patient by name
        // Patient harryPotter = patientDirectory.findPatientByName("Harry Potter");
        Patient randomPatient = patientDirectory.pickRandomPatient();

        // check the patient's personal information
        randomPatient.printPersonalInfo();

        // check the patient's lasted encounter information
        randomPatient.getEncounterHistory().pickLastedEncounter().printEncounterSummary();
        // randomPatient.getEncounterHistory().pickRandomeEncounter()

        // according to the patient lasted encounter information to find location, and printout local medical source
        String patientCity = randomPatient.getEncounterHistory().pickLastedEncounter().getEvent().getSite().getLocation().getCity();
        inNetworkHealthCareCatalog.findAndPrintMedicalSource(patientCity); 

        // check the patient's vaccination history
        randomPatient.printVaccinationHistoryReport();

        // check the patient's assessment information
        randomPatient.printAssessmentReport();

        // check the patient's medication history
        randomPatient.printMedicationHistory();

        // check the patient's diagnosis history report
        randomPatient.printDiagnosisReport();

        // Print infection report & Trends on Infection deceases
        clinic.printoutInfectionReport(2);
        clinic.printInfectiousCountByCityForPastThreeMonths();


        //V. #1 Randomly pick group of residents recovered from infectious diseases and closely monitor their mobility as the neighbourhood would like to protect the rest residents.
        ConfigureAMedicalSystem config = new ConfigureAMedicalSystem();
         config.getRandomInfoAll();

        //V. #2 Potential patient on track (based on #1 and a specific location and time input
        //V. #3 Calculate street infection case per day, automatically shows level of risk
        config.getPotentialInfected("04-24-2023","Oak St, San Jose");
    }
}