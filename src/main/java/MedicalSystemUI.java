
// import java.util.Date;
// import java.util.Scanner;

// import PatientManagement.Clinic.Clinic;
// import PatientManagement.Clinic.Event;
// import PatientManagement.Clinic.InNetworkHealthCareCatalog;
// import PatientManagement.Clinic.PatientDirectory;
// import PatientManagement.Patient.Patient;
// import PatientManagement.Patient.ClinicalHistory.Alergy;
// import PatientManagement.Patient.Encounters.Diagnosis;
// import PatientManagement.Patient.Encounters.Encounter;
// import PatientManagement.Patient.Orders.AssessmentOrder;
// import PatientManagement.Patient.Orders.MedicationOrder;
// import PatientManagement.Patient.Orders.Order;
// import PatientManagement.Patient.Orders.TreatmentOrder;
// import PatientManagement.Patient.Orders.VaccinationOrder;
// import PatientManagement.Persona.Person;

// public class MedicalSystemUI {
//     private static Clinic clinic;
//     private static PatientDirectory patientDirectory;
//     private static InNetworkHealthCareCatalog inNetworkHealthCareCatalog;
//     private static Scanner scanner = new Scanner(System.in);

//     public static void main(String[] args) {
//         clinic = ConfigureAMedicalSystem.createAClinicAndLoadData("NEU Bug Writers");
//         patientDirectory = clinic.getPatientDirectory();
//         inNetworkHealthCareCatalog = clinic.getNetworkHealthCareList();
//         int page = 1;
//         String input;
//         boolean exit = false;

//         while (!exit) {
//             switch (page) {
//                 case 1:
//                     System.out.println("Welcome to Bug Writer Clinic. Please pick an option:\n" +
//                             "1. New Patient Visit\n" +
//                             "2. Exiting Patient Visit\n" +
//                             "3. Check Infection Information\n" +
//                             "4. Exit");
//                     input = scanner.nextLine();
//                     switch (input) {
//                         case "1":
//                             System.out.println(
//                                     "Please enter new patient's name and age OR enter 0 go back to the previous page");
//                             String nameAge = scanner.nextLine();
//                             if (nameAge.equals("0")) {
//                                 break;
//                             }
//                             String[] nameAgeArray = nameAge.split(" ");
//                             if (nameAgeArray.length < 2) {
//                                 System.out.println("Invalid input.");
//                                 break;
//                             }
//                             String name = nameAgeArray[0];
//                             int age = Integer.parseInt(nameAgeArray[1]);
//                             Person newPerson = new Person(name, age);
//                             Patient newPatient = new Patient(newPerson, clinic);
//                             System.out.println("Please enter patient's father's name and age");
//                             String fatherNameAge = scanner.nextLine();
//                             String[] fatherNameAgeArray = fatherNameAge.split(" ");
//                             if (fatherNameAgeArray.length < 2) {
//                                 System.out.println("Invalid input.");
//                                 break;
//                             }
//                             String fatherName = fatherNameAgeArray[0];
//                             int fatherAge = Integer.parseInt(fatherNameAgeArray[1]);
//                             newPerson.setFather(new Person(fatherName, fatherAge));
//                             System.out.println("Please enter patient's mother's name and age");
//                             String motherNameAge = scanner.nextLine();
//                             String[] motherNameAgeArray = motherNameAge.split(" ");
//                             if (motherNameAgeArray.length < 2) {
//                                 System.out.println("Invalid input.");
//                                 break;
//                             }
//                             String motherName = motherNameAgeArray[0];
//                             int motherAge = Integer.parseInt(motherNameAgeArray[1]);
//                             newPerson.setMother(new Person(motherName, motherAge));
//                             System.out.println("Please enter sibling's name and age, if N/A, please enter 0");
//                             String siblingNameAge = scanner.nextLine();
//                             String[] siblingNameAgeArray = siblingNameAge.split(" ");

//                             if (!siblingNameAgeArray[0].equals("0")) {
//                                 String siblingName = siblingNameAgeArray[0];
//                                 int siblingAge = Integer.parseInt(siblingNameAgeArray[1]);
//                                 newPerson.addSibling(new Person(siblingName, siblingAge));
//                             }
//                             for (int i = 0; i < 5; i++) {
//                                 System.out.println("Please enter patient's allergy information (Enter 0 to skip)");
//                                 input = scanner.nextLine();
//                                 if (input.equals("0")) {
//                                     break;
//                                 }
//                                 Alergy alergy = new Alergy(input);
//                                 newPatient.addAllergy(alergy);
//                             }
//                             patientDirectory.addPatient(newPatient);
//                             System.out.println("Patient created. Please enter patient's chief complaint");
//                             input = scanner.nextLine();
//                             String chiefComplaint = input;
//                             System.out.println(
//                                     "Please enter patient's diagnosis information (disease name, disease category, confirmed?)");
//                             input = scanner.nextLine();
//                             String[] diagnosisInfo = input.split(",");
//                             if (diagnosisInfo.length < 3) {
//                                 System.out.println("Invalid input.");
//                                 break;
//                             }
//                             String diseaseName = diagnosisInfo[0];
//                             String diseaseCategory = diagnosisInfo[1];
//                             boolean isConfirmed = Boolean.parseBoolean(diagnosisInfo[2]);
//                             Diagnosis diagnosis = new Diagnosis(diseaseName, diseaseCategory, isConfirmed);
//                             MedicationOrder mo = null;
//                             VaccinationOrder vo = null;
//                             AssessmentOrder ao = null;
//                             TreatmentOrder to = null;
//                             System.out.println(
//                                     "Please enter patient's treatment order (medication order, vaccination order, treatment order, assessment order). Enter 0 to skip.");
//                             while (true) {
//                                 input = scanner.nextLine();
//                                 if (input.equals("0")) {
//                                     break;
//                                 } else if (input.equals("medication order")) {
//                                     input = scanner.nextLine();
//                                     System.out.println("Please enter drug name");
//                                     mo = new MedicationOrder(input);
//                                     newPatient.addMedicationOrder(mo);
//                                 } else if (input.equals("vaccination order")) {
//                                     input = scanner.nextLine();
//                                     System.out.println("Please enter vaccine name");
//                                     vo = new VaccinationOrder(input);
//                                     newPatient.addVaccinationOrder(vo);
//                                 } else if (input.equals("treatment order")) {
//                                     input = scanner.nextLine();
//                                     System.out.println("Please enter treatment name");
//                                     to = new TreatmentOrder(input);
//                                     newPatient.addTreatmentOrder(to);
//                                 } else if (input.equals("assessment order")) {
//                                     input = scanner.nextLine();
//                                     System.out.println("Please enter assessment name");
//                                     ao = new AssessmentOrder(input);
//                                     newPatient.addAssessmentOrder(ao);
//                                 } else {
//                                     System.out.println("Invalid input.");
//                                     continue;
//                                 }
//                             }
//                             Date currentDate = new Date();
//                             String fakeCurrentLocation = "NEU St. NEU city";
//                             Event event = new Event(fakeCurrentLocation, currentDate);
//                             Encounter encounter = new Encounter(newPatient, chiefComplaint, diagnosis, event, vo, ao,
//                                     mo, to);
//                             newPatient.addEncounter(encounter);
//                             System.out.println("Encounter created.");
//                             page = 1;
//                             break;

//                         case "2":

//                             System.out.println(
//                                     "Please enter existing patient's name and age, or Enter 0 go back to the previous page.");
//                             nameAge = scanner.nextLine();
//                             if (nameAge.equals("0")) {
//                                 break;
//                             }
//                             nameAgeArray = nameAge.split(" ");
//                             if (nameAgeArray.length < 2) {
//                                 System.out.println("Invalid input.");
//                                 break;
//                             }
//                             name = nameAgeArray[0];
//                             age = Integer.parseInt(nameAgeArray[1]);
//                             Patient existingPatient = patientDirectory.findPatientByNameAndAge(name, age);
//                             if (existingPatient == null) {
//                                 System.out.println("Patient not found.");

//                                 break;
//                             }

//                             System.out.println("Please pick an option:\n" +
//                                     "1. Create New Encounter\n" +
//                                     "2. Check Personal Information\n" +
//                                     "3. Check Lasted Encounter Information\n" +
//                                     "4. Find Local Medical Source for the Patient\n" +
//                                     "5. Check Patient's Vaccination History\n" +
//                                     "6. Check Patient's Assessment Information\n" +
//                                     "7. Check Patient's Medication History\n" +
//                                     "8. Check Patient's Diagnosis History Report\n" +
//                                     "9. Exit");
//                             input = scanner.nextLine();
//                             switch (input) {
//                                 case "1":
//                                     System.out.println("Patient found. Please enter patient's chief complaint");
//                                     input = scanner.nextLine();
//                                     String epchiefComplaint = input;
//                                     System.out.println(
//                                             "Please enter patient's diagnosis information (disease name, disease category, confirmed?)");
//                                     input = scanner.nextLine();
//                                     String[] epdiagnosisInfo = input.split(",");
//                                     String epdiseaseName = epdiagnosisInfo[0];
//                                     String epdiseaseCategory = epdiagnosisInfo[1];
//                                     boolean episConfirmed = Boolean.parseBoolean(epdiagnosisInfo[2]);
//                                     Diagnosis epdiagnosis = new Diagnosis(epdiseaseName, epdiseaseCategory,
//                                             episConfirmed);
//                                     MedicationOrder epmo = null;
//                                     VaccinationOrder epvo = null;
//                                     AssessmentOrder epao = null;
//                                     TreatmentOrder epto = null;
//                                     System.out.println(
//                                             "Please enter patient's treatment order (medication order, vaccination order, treatment order, assessment order). Enter 0 to skip.");
//                                     while (true) {
//                                         input = scanner.nextLine();
//                                         if (input.equals("0")) {
//                                             break;
//                                         } else if (input.equals("medication order")) {
//                                             input = scanner.nextLine();
//                                             System.out.println("Please enter drug name");
//                                             epmo = new MedicationOrder(input);
//                                             existingPatient.addMedicationOrder(epmo);
//                                         } else if (input.equals("vaccination order")) {
//                                             input = scanner.nextLine();
//                                             System.out.println("Please enter vaccine name");
//                                             epvo = new VaccinationOrder(input);
//                                             existingPatient.addVaccinationOrder(epvo);
//                                         } else if (input.equals("treatment order")) {
//                                             input = scanner.nextLine();
//                                             System.out.println("Please enter treatment name");
//                                             epto = new TreatmentOrder(input);
//                                             existingPatient.addTreatmentOrder(epto);
//                                         } else if (input.equals("assessment order")) {
//                                             input = scanner.nextLine();
//                                             System.out.println("Please enter assessment name");
//                                             epao = new AssessmentOrder(input);
//                                             existingPatient.addAssessmentOrder(epao);
//                                         } else {
//                                             System.out.println("Invalid input.");
//                                             continue;
//                                         }
//                                     }
//                                     Date currentDate2 = new Date();
//                                     String fakeCurrentLocation2 = "NEU St. NEU city";
//                                     Event event2 = new Event(fakeCurrentLocation2, currentDate2);
//                                     Encounter epencounter = new Encounter(existingPatient, epchiefComplaint,
//                                             epdiagnosis, event2, epvo, epao, epmo, epto);
//                                     existingPatient.addEncounter(epencounter);
//                                     System.out.println("Encounter created.");
//                                     page = 1;
//                                     break;

//                                 case "2":
//                                     existingPatient.printPersonalInfo();

//                                 case "3":
//                                     existingPatient.getEncounterHistory().pickLastedEncounter().printEncounterSummary();

//                                     // according to the patient lasted encounter information to find location, and
//                                     // printout local medical source
//                                 case "4":
//                                     String patientCity = existingPatient.getEncounterHistory().pickLastedEncounter()
//                                             .getEvent().getSite().getLocation().getCity();
//                                     inNetworkHealthCareCatalog.findAndPrintMedicalSource(patientCity);

//                                 case "5":
//                                     // check the patient's vaccination history
//                                     existingPatient.printVaccinationHistoryReport();

//                                 case "6":
//                                     // check the patient's assessment information
//                                     existingPatient.printAssessmentReport();

//                                 case "7":
//                                     // check the patient's medication history
//                                     existingPatient.printMedicationHistory();

//                                 case "8":
//                                     // check the patient's diagnosis history report
//                                     existingPatient.printDiagnosisReport();

//                             }
//                         case "3":
//                             System.out.println("Please pick an option:\n" +
//                                     "1. Check Monthly Report of Infection in Different Cities\n" +
//                                     "2. Check Trends on Infection Deceases in Past 3 Months\n" +
//                                     "3. Check Infectious Patient's Mobility\n" +
//                                     "4. Check Street Infection Risk\n" +
//                                     "5. Exit");
//                             input = scanner.nextLine();
//                             switch (input) {
//                                 case "1":
//                                     System.out.println(
//                                             "Please input the month number, for example: if you want to check January data, enter 1 ");
//                                     input = scanner.nextLine();
//                                     int inputMonthNumber = Integer.parseInt(input);
//                                     clinic.printoutInfectionReport(inputMonthNumber);

//                                 case "2":
//                                     clinic.printInfectiousCountByCityForPastThreeMonths();

//                                 case "3":
//                                     ConfigureAMedicalSystem config = new ConfigureAMedicalSystem();
//                                     config.getRandomInfoAll();

//                                 case "4":
//                                     ConfigureAMedicalSystem config3 = new ConfigureAMedicalSystem();
//                                     System.out.println(
//                                             "Please input the date you want to check for, for example: 04-18-2023");
//                                     input = scanner.nextLine();
//                                     String stRiskDate = input;
//                                     System.out.println(
//                                             "Please input the street you want to check for, for example: Oak St, San Jose");
//                                     input = scanner.nextLine();
//                                     String stRiskStreet = input;
//                                     config3.getPotentialInfected(stRiskDate, stRiskStreet);

//                             }
//                         case "4":
//                             exit = true;
//                     }

//             }
//         }
//     }

// }
