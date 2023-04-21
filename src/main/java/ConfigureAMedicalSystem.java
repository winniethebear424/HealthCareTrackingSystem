import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import com.github.javafaker.Faker;

import PatientManagement.Catalogs.Drug;
import PatientManagement.Catalogs.DrugCatalog;
import PatientManagement.Catalogs.VOrderItem;
import PatientManagement.Catalogs.VaccineCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.EventSchedule;
import PatientManagement.Clinic.Location;
import PatientManagement.Clinic.LocationList;
import PatientManagement.Clinic.PatientDirectory;
import PatientManagement.Clinic.Site;
import PatientManagement.Clinic.SiteCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.ClinicalHistory.Alergy;
import PatientManagement.Patient.ClinicalHistory.DiseaseCatalog;
import PatientManagement.Patient.ClinicalHistory.Vaccine;
import PatientManagement.Patient.Encounters.Diagnosis;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Orders.AssessmentOrder;
import PatientManagement.Patient.Orders.MedicationOrder;
import PatientManagement.Patient.Orders.TreatmentOrder;
import PatientManagement.Patient.Orders.VaccinationOrder;
import PatientManagement.Persona.Person;
import PatientManagement.Persona.PersonDirectory;

public class ConfigureAMedicalSystem {
    public static Clinic createAClinicAndLoadData(String name) {
        Clinic clinic = new Clinic(name);

        // Add dieases
        loadDiseaseInfo(clinic);

        // Add vaccines, drugs for the clinic
        loadVaccine(clinic);
        loadDrug(clinic);

        // Add patients
        loadPatient(clinic);

        // Add alergy information to the patient
        loadAlergyinfoToPatient(clinic);

        // Add vaccination information to the patient
        loadOrdersToPaitent(clinic);

        // Add Evens
        loadEventandEncounters(clinic);

        return clinic;
    }

    static int getRandom(int lower, int upper) {
        Random r = new Random();
        int randomInt = lower + r.nextInt(upper - lower);
        return randomInt;
    }

    static void loadDiseaseInfo(Clinic clinic){
        DiseaseCatalog diseaseCatalog = clinic.getDiseaseCatalog();
        Faker faker = new Faker();
        // generate 50 diseases
        for (int dIndex = 0; dIndex < 50; dIndex++) {
            String diseaseName = faker.medical().diseaseName();
            String category = faker.options().option("Infectious", "Hereditary");
            boolean confirmed = true;
            Diagnosis diagnosis = new Diagnosis(diseaseName, category, confirmed);
            diseaseCatalog.addConfirmedDiagnosis(diagnosis);
        }
    }

    static void loadVaccine(Clinic clinic) {
        VaccineCatalog vaccineCatalog = clinic.getVaccineCatalog();
        DiseaseCatalog diseaseCatalog = clinic.getDiseaseCatalog();
        Faker faker = new Faker();
        // generate 50 vaccines
        for (int vaccineIndex = 0; vaccineIndex < 50; vaccineIndex++) {
            // generate a fake vaccine name by placeholder text which looks like real text
            String vaccineName = faker.lorem().word() + " Vaccine";
            String preventedInfectiousDisease = diseaseCatalog.pickRandomInfectiousDisease();
            Vaccine vaccine = new Vaccine(vaccineName, preventedInfectiousDisease);
            vaccineCatalog.addVaccine(vaccine);
        }
    }

    static void loadDrug(Clinic clinic){
        DrugCatalog drugCatalog = clinic.getDrugcatalog();
        Faker faker = new Faker();
        // generate 50 drugs
        for (int drugIndex = 0; drugIndex < 50; drugIndex++) {
            // generate a fake drug name 
            String drugName = faker.medical().medicineName();
            String treatedDisease = faker.medical().diseaseName();
            Drug drug = new Drug(drugName, treatedDisease);
            drugCatalog.addDrug(drug);
        }
    }

    static void loadPatient(Clinic clinic) {
        PersonDirectory personDirectory = clinic.getPersonDirectory();
        PatientDirectory patientDirectory = clinic.getPatientDirectory();
        Faker faker = new Faker();
        // generate 30 patients
        for (int index = 1; index <= 30; index++) {
            // generate random patient full name
            String name = faker.name().fullName();
            // generate random patient age between 18 and 80
            int patientAge = faker.number().numberBetween(18, 80);
            Person newPerson = personDirectory.newPerson(name, patientAge);
            // generate patient's parent
            int dadAge = faker.number().numberBetween((patientAge + 25), 85);
            int monAge = faker.number().numberBetween((patientAge + 25), 85);
            String dadName = faker.name().firstName();
            String monName = faker.name().firstName();
            Person patientDad = personDirectory.newPerson(dadName, dadAge);
            Person patientMon = personDirectory.newPerson(monName, monAge);
            newPerson.setFather(patientDad);
            newPerson.setMother(patientMon);
            // randomlu generate 0 to 3 siblings to each patient
            int randomSiblingsCount = getRandom(0, 3);
            for (int siblingindex = 0; siblingindex < randomSiblingsCount; siblingindex++) {
                String siblingName = faker.name().firstName();
                int siblingAge = faker.number().numberBetween((patientAge - 5), (patientAge + 5));
                Person patientSibling = personDirectory.newPerson(siblingName, siblingAge);
                newPerson.addSibling(patientSibling);
            }
            patientDirectory.newPatient(newPerson);
        }
    }

    static void loadAlergyinfoToPatient(Clinic clinic){
        PatientDirectory patientDirectory = clinic.getPatientDirectory();
        Faker faker = new Faker();
        for (Patient patient : patientDirectory.getPatients()) {
            // randomlu generate 0 to 3 allergens to each patient
            int randomAllergensCount = getRandom(0, 3);
            for (int index = 0; index < randomAllergensCount; index++) {
                Alergy alergy = new Alergy(faker.food().ingredient());
                patient.getAlergyhistory().addAlergyInfo(alergy);
            }
        }
    }

    static void loadOrdersToPaitent(Clinic clinic) {
        PatientDirectory patientDirectory = clinic.getPatientDirectory();
        VaccineCatalog vaccineCatalog = clinic.getVaccineCatalog();
        DrugCatalog drugCatalog = clinic.getDrugcatalog();
        Faker faker = new Faker();
        for (Patient patient : patientDirectory.getPatients()) {
            // randomly generate 0 to 3 vaccination order
            int randomVOrderCount = getRandom(0, 3);
            for (int orderIndex = 0; orderIndex < randomVOrderCount; orderIndex++) {
                // set a random date for the vaccination order
                Date randomDate = faker.date().past(3650, TimeUnit.DAYS); // up to 10 years ago

                // randomly generate 1 to 3 vaccination order items for each vaccination order
                int randomItemCount = getRandom(1, 3);
                ArrayList<VOrderItem> vOrderItems = new ArrayList<VOrderItem>();

                for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {
                    Vaccine randomVaccine = vaccineCatalog.pickRandomVaccine();
                    int randomPrice = getRandom(1, 50);
                    int randomDose = getRandom(1, 3);
                    VOrderItem vOrderItem = new VOrderItem(randomVaccine, randomPrice, randomDose);
                    vOrderItems.add(vOrderItem);
                }

                VaccinationOrder randomVaccinationOrder = new VaccinationOrder(vOrderItems, patient, randomDate,
                        clinic);
                patient.addVaccinationOrder(randomVaccinationOrder);
                randomVaccinationOrder.ExecuteOrder();

            }
            // randomly generate 0 to 3 assessment orders
            int randomAOrderCount = getRandom(0, 3);
            for (int aOrderIndex = 0; aOrderIndex < randomAOrderCount; aOrderIndex++) {
                String assessmentName = faker.medical().diseaseName() + " Assessment";
                Date randomADate = faker.date().past(3650, TimeUnit.DAYS); // up to 10 years ago
                AssessmentOrder assessmentOrder = new AssessmentOrder(assessmentName, patient, randomADate, clinic);
                patient.addAssessmentOrder(assessmentOrder);
                assessmentOrder.setResult(faker.bool().bool());
            }

            // randomly generate 0 to 3 medication orders; And generate 1 to 3 drugs for each medication order
            int randomMOrderCount = getRandom(0, 3);
            for (int mOrderIndex = 0; mOrderIndex < randomMOrderCount; mOrderIndex++) {
                Date randomMDate = faker.date().past((365*3), TimeUnit.DAYS); // up to 3 years ago
                int randomdrugCount = getRandom(1, 3);
                ArrayList<Drug> drugs = new ArrayList<Drug>();
                for (int drugIndex = 0; drugIndex < randomdrugCount; drugIndex++) {
                    Drug randomdDrug = drugCatalog.pickRandomDrug();
                    drugs.add(randomdDrug);
                MedicationOrder medicationOrder = new MedicationOrder( patient, drugs, randomMDate, clinic);
                patient.addMedicationOrder(medicationOrder);
            }
        }


            // randomly generate 0 to 3 treatment orders
            int randomTOrderCount = getRandom(0, 3);
            for (int tOrderIndex = 0; tOrderIndex < randomTOrderCount; tOrderIndex++) {
                Date randomTDate = faker.date().past((365*3), TimeUnit.DAYS); // up to 3 years ago
                String treatmentName = faker.medical().symptoms() + " Care";
                TreatmentOrder treatmentOrder = new TreatmentOrder(patient, treatmentName, randomTDate, clinic);
                patient.addTreatmentOrder(treatmentOrder);
            }
        }
    }

    static void loadEventandEncounters(Clinic clinic){
        EventSchedule eventSchedule = clinic.getEventschedule();
        LocationList locationList = clinic.getLocationList();
        SiteCatalog siteCatalog = clinic.getSiteCatalog();
        VaccineCatalog vaccineCatalog = clinic.getVaccineCatalog();
        PatientDirectory patientDirectory = clinic.getPatientDirectory();
        DrugCatalog drugCatalog = clinic.getDrugcatalog();
        DiseaseCatalog diseaseCatalog = clinic.getDiseaseCatalog();
        Faker faker = new Faker();

        // generate 10 scheduled events
        for (int eventindex = 0; eventindex < 10; eventindex++) {
            Location location = locationList.newLocation(faker.address().fullAddress());
            Site site = siteCatalog.newSite(location);
            String budgetnumer = faker.number().digits(8);
            // up to 3 years ago
            Date date = faker.date().past((365 * 3), TimeUnit.DAYS); 
            eventSchedule.newEvent(site, budgetnumer, date);
        }

        // randomly generate 5 to 30 encounters for each event
        for (Event event: eventSchedule.getScheduledevents()){
            int randomEncounterCount = getRandom(5, 30);
            for (int encounterIndex = 0; encounterIndex < randomEncounterCount; encounterIndex++) {
                // pick a random patient for each encounter
                Patient patient = patientDirectory.pickRandomPatient();

                // // generate a patient for each encounter
                // String name = faker.name().fullName();
                // // generate random age between 18 and 100
                // int age = faker.number().numberBetween(18, 100);
                // Person newPerson = personDirectory.newPerson(name, age);
                // Patient patient = new Patient(newPerson, clinic);
                // patientDirectory.addPatient(patient);

                // generate chief complaint
                String chiefComplaint = faker.medical().symptoms();
                // generate diagnosis
                String diseaseName = faker.medical().diseaseName();
                String category = faker.options().option("Infectious", "Hereditary");
                boolean confirmed = faker.bool().bool();
                Diagnosis diagnosis = new Diagnosis(diseaseName, category, confirmed);

                // declare and initialize variables to store orders
                VaccinationOrder vaccinationOrder = null;
                AssessmentOrder assessmentOrder = null;
                MedicationOrder medicationOrder = null;
                TreatmentOrder treatmentOrder = null;


                // generate order base on the diagnosis
                if (diagnosis.getCategory().equals("Infectious")){
                    if (diagnosis.isConfirmed()){
                        diseaseCatalog.addConfirmedDiagnosis(diagnosis);
                        // if the patient is confirmed to infectious disease, generate a vaccination order and randomly generate 1 to 3 vaccination order items
                        int randomItemCount = getRandom(1, 3);
                        ArrayList<VOrderItem> vOrderItems = new ArrayList<VOrderItem>();
                        for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {
                            Vaccine randomVaccine = vaccineCatalog.pickRandomVaccine();
                            int randomPrice = getRandom(1, 50);
                            int randomDose = getRandom(1, 3);
                            VOrderItem vOrderItem = new VOrderItem(randomVaccine, randomPrice, randomDose);
                            vOrderItems.add(vOrderItem);
                        }
                        vaccinationOrder = new VaccinationOrder(vOrderItems, patient, event.getDate(), clinic);
                        patient.addVaccinationOrder(vaccinationOrder);
                        vaccinationOrder.ExecuteOrder();
                    } else {
                        // if the patient lookes like have infectious disease but not be confirmed yet, assign assessment
                        assessmentOrder = new AssessmentOrder(faker.medical().diseaseName() + " Assessment", patient, event.getDate(), clinic);
                        patient.addAssessmentOrder(assessmentOrder);
                        // assume patient do the assessment when the assessment order is assigned
                        assessmentOrder.setResult(faker.bool().bool());
                    }
                } else {
                    //  if the patient is confirmed to hereditary disease, generate a medication order which contains 1 to 3 drugs
                    if (diagnosis.isConfirmed()) {
                        diseaseCatalog.addConfirmedDiagnosis(diagnosis);
                        ArrayList<Drug> drugs = new ArrayList<Drug>();
                        int randomdrugCount = getRandom(1, 3);
                        for (int drugIndex = 0; drugIndex < randomdrugCount; drugIndex++) {
                            Drug randomdDrug = drugCatalog.pickRandomDrug();
                            drugs.add(randomdDrug);
                        }
                        medicationOrder = new MedicationOrder( patient, drugs ,event.getDate(), clinic);
                        patient.addMedicationOrder(medicationOrder);
                    } else {
                        // if the patient lookes like have infectious disease but not be confirmed yet, assign treatment order
                        treatmentOrder = new TreatmentOrder(patient, chiefComplaint +" Care", event.getDate(), clinic);
                        patient.addTreatmentOrder(treatmentOrder);
                    }
                }
                // create encounter and add order
                Encounter encounter = new Encounter(patient, chiefComplaint, diagnosis, event, vaccinationOrder, assessmentOrder, medicationOrder, treatmentOrder);
                event.addEncounter(encounter);
                patient.getEncounterHistory().addEncounter(encounter);
            }
        }
    }
}
