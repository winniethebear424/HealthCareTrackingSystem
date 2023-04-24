/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import com.github.javafaker.Faker;

import PatientManagement.Catalogs.DrugCatalog;
import PatientManagement.Catalogs.VaccineCatalog;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.ClinicalHistory.DiseaseCatalog;
import PatientManagement.Patient.Encounters.Diagnosis;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
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
    InNetworkHealthCareCatalog networkHealthCareList;
    private int CitypotentialInfected;

    public Clinic(String n) {
        name = n;
        eventschedule = new EventSchedule();
        sitelist = new SiteCatalog();
        locationlist = new LocationList();
        persondirectory = new PersonDirectory();
        patientdirectory = new PatientDirectory(this);
        vitalSignsCatalog = new VitalSignsCatalog();
        vaccineCatalog = new VaccineCatalog();
        drugcatalog = new DrugCatalog();
        diseaseCatalog = new DiseaseCatalog();
        networkHealthCareList = new InNetworkHealthCareCatalog();
    }

    public SiteCatalog getSiteCatalog(){
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

    public InNetworkHealthCareCatalog getNetworkHealthCareList() {
        return networkHealthCareList;
    }

    public void printoutInfectionReport(int inputMonthNumber) {
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              Report of Infection                           ");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        String monthString = "";
        if(inputMonthNumber == 1){ monthString = "January";
        } else if (inputMonthNumber == 2){ monthString = "February";} else if (inputMonthNumber == 3){ monthString = "March";} else if (inputMonthNumber == 4){ monthString = "April";
        } else if (inputMonthNumber == 5){ monthString = "May";} else if (inputMonthNumber == 6){ monthString = "June";} else if (inputMonthNumber == 7){ monthString = "July";
        } else if (inputMonthNumber == 8){ monthString = "August";} else if (inputMonthNumber == 9){ monthString = "September";} else if (inputMonthNumber == 10){ monthString = "October";
        } else if (inputMonthNumber == 11){ monthString = "November";} else { monthString = "December";} ;

        System.out.println("Month: " + monthString);
        // Create maps to store the count of different types of patients for each city
        Map<String, Integer> cityPatientCounts = new HashMap<>();
        Map<String, Integer> cityInfectiousCounts = new HashMap<>();
        Map<String, Integer> cityConfirmedCounts = new HashMap<>();

        // Loop through the list of all patients
        for (Patient patient : patientdirectory.getPatients()) {
            // Check if the patient was showed in the given month
            for(Encounter encouter: patient.getEncounterHistory().getEncounterList()){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(encouter.getEvent().getDate());
                int month = calendar.get(Calendar.MONTH) + 1;
                if(month == inputMonthNumber){
                    // Increment the count of infectious patients for the patient's city
                    String city = encouter.getEvent().getSite().getLocation().getCity();
                    int totalCount = cityPatientCounts.getOrDefault(city, 0);
                    cityPatientCounts.put(city, totalCount + 1);

                    if(encouter.getDiagnosis().isConfirmedInfectious()){
                        // Increment the count of infectious patients 
                        int infectiousCount = cityInfectiousCounts.getOrDefault(city, 0);
                        cityInfectiousCounts.put(city, infectiousCount + 1);
                    }

                    if(encouter.getDiagnosis().isConfirmed()){
                        // Increment the count of infectious patients for the patient's city
                        int confirmedCount = cityConfirmedCounts.getOrDefault(city, 0);
                        cityConfirmedCounts.put(city, confirmedCount + 1);
                    }

                }
            }
        }

        // Create a set of all the unique city names
        Set<String> cityNames = new HashSet<>();
        cityNames.addAll(cityPatientCounts.keySet());
        System.out.printf("%-18s %-18s %-18s %-18s %-18s %-18s\n", "City", "All Patients", "Total Confirmed", "Infectious", "Other", "Infected Rate");

        // Loop through each city and print the disease counts
        for (String city : cityNames) {
            int allPatientCount = cityPatientCounts.getOrDefault(city, 0);
            int confirmedCount = cityConfirmedCounts.getOrDefault(city, 0);
            int infectiousCount = cityInfectiousCounts.getOrDefault(city, 0);
            int otherCount = confirmedCount - infectiousCount;
            double infectiousRatio = (double)infectiousCount / allPatientCount * 100;    
            System.out.printf("%-18s %-18s %-18s %-18s %-18s %-18s\n", city, allPatientCount, confirmedCount, infectiousCount, otherCount,String.format("%.2f%%", infectiousRatio));


            //V.InfectedNum
          // working on future months
//             Month currentMonth = LocalDate.now().getMonth();    // current month
//             Month nextMonth = currentMonth.plus(1);     // following month...and so on...
//             Month nextMonth2 = currentMonth.plus(2);
//             Month nextMonth3 = currentMonth.plus(3);

            //working on infection rate & city population
            Faker faker = new Faker();
            Random random = new Random();   // Generate a random infectious rate within the range
            double minInfectiousRate = infectiousCount-0.15;    // Set the range of infectious rates (e.g. +-0.15)
            double maxInfectiousRate = infectiousCount+0.15;
            this.CitypotentialInfected = faker.number().numberBetween(1000, 10000);   // City population
            int populationDensity = faker.number().numberBetween(300, 500);   // per street
            double EstInfectiousRate = minInfectiousRate + (maxInfectiousRate - minInfectiousRate) * random.nextDouble();
            int CitypotentialInfected = (int) (this.CitypotentialInfected * EstInfectiousRate);
            double StPotentialInfected = populationDensity * EstInfectiousRate;
//            System.out.println("-------------------------------------------------------------------------------------------------------------");
//            System.out.println("                                   Trends on Infection deceases                                   ");
//            System.out.println("-------------------------------------------------------------------------------------------------------------");
//            System.out.printf("| %-15s %-15s| %-15s|","Data as of", currentMonth,city);
//            System.out.printf("\n| %-15s| %-15s| %-15s| %-15s|",currentMonth,nextMonth,nextMonth2,nextMonth3);
//            System.out.printf("\n| %-15s| %-15s| %-15s| %-15s|" ,CitypotentialInfected,CitypotentialInfected,CitypotentialInfected,CitypotentialInfected);
        }
        for (String city : cityNames) {
            Month currentMonth = LocalDate.now().getMonth();    // current month
            Month nextMonth = currentMonth.plus(1);     // following month...and so on...
            Month nextMonth2 = currentMonth.plus(2);
            Month nextMonth3 = currentMonth.plus(3);

            System.out.println("\n----------------------------------------------------------------------------------------------");
            System.out.println("                                   Trends on Infection deceases                                   ");
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.printf("%-18s %-18s %-18s ", city,"\nData as of",currentMonth);
            System.out.printf("\n %-18s %-18s %-18s %-18s ", currentMonth, nextMonth, nextMonth2, nextMonth3);
            System.out.printf("\n %-18s %-18s %-18s %-18s ", this.CitypotentialInfected, this.CitypotentialInfected, this.CitypotentialInfected, this.CitypotentialInfected);

        }

    };


    }

