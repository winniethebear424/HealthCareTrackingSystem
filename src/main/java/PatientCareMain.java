
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

        // Print infection report
        clinic.printoutInfectionReport(1);

    }






    

}
