
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
//                             System.out.println("Please enter new patient's name and age OR enter 0 go back to the previous page");
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
//                             if (siblingNameAgeArray.length < 2) {
//                                 System.out.println("Invalid input.");
//                                 break;
//                             }
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
//                             System.out.println("Please enter patient's diagnosis information (disease name, disease category, confirmed?)");
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
//                             System.out.println("Please enter patient's treatment order (medication order, vaccination order, treatment order, assessment order). Enter 0 to skip.");
//                             while (true) {
//                                 input = scanner.nextLine();
//                                 if (input.equals("0")) {
//                                     break;
//                                 }
//                                 else if (input.equals("medication order")){
//                                     input = scanner.nextLine();
//                                     System.out.println("Please enter drug name");
//                                     mo = new MedicationOrder(input);
//                                     newPatient.addMedicationOrder(mo);
//                                 }
//                                 else if(input.equals("vaccination order")){
//                                     input = scanner.nextLine();
//                                     System.out.println("Please enter vaccine name");
//                                     vo = new VaccinationOrder(input);
//                                 }
//                                 else if(input.equals("treatment order")){
//                                     input = scanner.nextLine();
//                                     System.out.println("Please enter treatment name");
//                                     to = new TreatmentOrder(input);
//                                 }
//                                 else if(input.equals("assessment order")){
//                                     input = scanner.nextLine();
//                                     System.out.println("Please enter assessment name");
//                                     ao = new AssessmentOrder(input);
//                                 }
//                                 else{
//                                     System.out.println("Invalid input.");
//                                     continue;
//                                 }
//                             }
//                             Date currentDate = new Date();
//                             String fakeCurrentLocation = "NEU St. NEU city";
//                             Event event = new Event(fakeCurrentLocation, currentDate);
//                             Encounter encounter = new Encounter(newPatient, chiefComplaint, diagnosis, event, vo, ao, mo, to);
//                             newPatient.addEncounter(encounter);
//                             System.out.println("Encounter created.");
//                             page = 1;
//                             break;

//                         case "2":
//                             System.out.println("Please enter existing patient's name and age, or Enter 0 go back to the previous page.");
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
//                             System.out.println("Please enter patient's chief complaint");
//                             input = scanner.nextLine();
//                             existingPatient.setChiefComplaint(input);
//                             System.out.println("Please enter patient's medical history");
//                             input = scanner.nextLine();
//                             existingPatient.setMedicalHistory(input);
//                             System.out.println("Please enter patient's diagnosis information (disease name, disease category, confirmed?)");
//                             input = scanner.nextLine();
//                             diagnosisInfo = input.split(",");
//                             if (diagnosisInfo.length < 3) {
//                                 System.out.println("Invalid input.");
//                                 break;
//                             }
//                             diseaseName = diagnosisInfo[0];
//                             diseaseCategory = diagnosisInfo[1];
//                             isConfirmed = Boolean.parseBoolean(diagnosisInfo[2]);
//                             diagnosis = new Diagnosis(diseaseName, diseaseCategory, isConfirmed);
//                             existingPatient.addDiagnosis(diagnosis);
//                             System.out.println("Please enter patient's treatment order (medication order, vaccination order, treatment order, assessment order). Enter 0 to skip.");
//                             while (true) {
//                                 input = scanner.nextLine();
//                                 if (input.equals("0")) {
//                                     break;
//                                 }
//                                 String[] treatmentOrder = input.split(",");
//                                 if (treatmentOrder.length < 2) {
//                                     System.out.println("Invalid input.");
//                                     continue;
//                                 }
                            



//                 -----------------------

//                             System.out.println("Please enter patient's chief complaint");
//                             input = scanner.nextLine();
//                             String chiefComplaint = input;
//                             System.out.println("Please enter diagnosis disease");
//                             input = scanner.nextLine();
//                             String chiefComplaint = input;

//                             newPatient.setChiefComplaint(input);

//                             System.out.println("Patient information added successfully.");
//                             break;
//                         case "2":
//                             System.out.println("Please enter patient's ID or Enter 0 go back to the previous page");
//                             input = scanner.nextLine();
//                             if (input.equals("0")) {
//                                 break;
//                             }
//                             Patient patient = patientDirectory.getPatientByID(input);
//                             if (patient == null) {
//                                 System.out.println("Patient not found.");
//                                 break;
//                             }
//                             System.out.println("Patient found. Please enter the date of visit (MM/DD/YYYY)");
//                             String visitDate = scanner.nextLine();
//                             System.out.println("Please enter the doctor's name");
//                             String doctorName = scanner.nextLine();
//                             Doctor doctor = clinic.getDoctorByName(doctorName);
//                             if (doctor == null) {
//                                 System.out.println("Doctor not found.");
//                                 break;
//                             }
//                             System.out.println("Please enter the diagnosis");
//                             String diagnosis = scanner.nextLine();
//                             Visit visit = new Visit(visitDate, doctor, diagnosis);
//                             patient.addVisit(visit);
//                             System.out.println("Visit information added successfully.");
//                             break;
//                         case "3":
//                             System.out.println("Please enter a keyword to search for");
//                             input = scanner.nextLine();
//                             inNetworkHealthCareCatalog.searchProviders(input);
//                             break;
//                         case "4":
//                             exit = true;
//                             break;
//                         default:
//                             System.out.println("Invalid input.");
//                             break;
//                     }
//                     break;
//                 default:
//                     System.out.println("Invalid page number.");
//                     break;
//             }
//         }
//     }
    

// }
