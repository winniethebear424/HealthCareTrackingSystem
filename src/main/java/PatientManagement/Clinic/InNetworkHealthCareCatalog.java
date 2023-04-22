package PatientManagement.Clinic;

import java.util.ArrayList;
import java.util.HashMap;

public class InNetworkHealthCareCatalog {
    HashMap <String, ArrayList<OtherHealthCare>> cityMedicalSourceCatalog;
    // ArrayList<OtherHealthCare> sanJoseClinics;
    // ArrayList<OtherHealthCare> paloAltoClinics;
    // ArrayList<OtherHealthCare> sunnyvaleClinics;


    public InNetworkHealthCareCatalog() {
        cityMedicalSourceCatalog = new HashMap <String, ArrayList<OtherHealthCare>> ();
    // sanJoseClinics = new ArrayList<OtherHealthCare> ();
    // paloAltoClinics = new ArrayList<OtherHealthCare> ();
    // sunnyvaleClinics = new ArrayList<OtherHealthCare> ();
    }

    public void addNewCityAndNewSource (String city, ArrayList<OtherHealthCare> medicalSourceList){
        cityMedicalSourceCatalog.put(city, medicalSourceList);
    }

    public void addANewInsititution (String city, OtherHealthCare institution){
        ArrayList<OtherHealthCare> medicalSources = cityMedicalSourceCatalog.get(city);
        medicalSources.add(institution);
    }
    
    // public ArrayList<OtherHealthCare> getSanJoseClinics() {
    //     return sanJoseClinics;
    // }

    // public ArrayList<OtherHealthCare> getPaloAltoClinics() {
    //     return paloAltoClinics;
    // }
 
    // public ArrayList<OtherHealthCare> getsunnyvaleClinics() {
    //     return sunnyvaleClinics;
    // }

    // public void addMedicalSourceToSJ(OtherHealthCare medicalSources){
    //     sanJoseClinics.add(medicalSources);
    // }

    // public void addMedicalSourceToPA(OtherHealthCare medicalSources){
    //     paloAltoClinics.add(medicalSources);
    // }

    // public void addMedicalSourceToSV(OtherHealthCare medicalSources){
    //     sunnyvaleClinics.add(medicalSources);
    // }

    public void findAndPrintMedicalSource(String cityName){
        System.out.println("------------------------------------------------------------");        
        System.out.println("                " + cityName + " Medical Source");
        System.out.println("------------------------------------------------------------");   
        System.out.println("Medical Source \t|\t Medical Source Address   ");
        for(OtherHealthCare institution:cityMedicalSourceCatalog.get("San Jose")){
            String insititutionName = institution.getHealthcareInsititutionName();
            String insititutionAddress = institution.getLocation().getFullAddressName();
            System.out.println(insititutionName + "\t|\t" + insititutionAddress);
        }
        System.out.println();
    }

    // public void findAndPrintSJMedicalSource(){
    //     System.out.println("------------------------------------------------------------");        
    //     System.out.println("\t\tSan Jose Medical Source\t\t");
    //     System.out.println("------------------------------------------------------------");   
    //     System.out.println("Medical Source \t|\t Medical Source Address   ");
    //     for(OtherHealthCare sjMS:cityMedicalSourceCatalog.get("San Jose")){
    //         String sjMedicalSourceName = sjMS.getHealthcareInsititutionName();
    //         String sjMedicalSourseAddress = sjMS.getLocation().getFullAddressName();
    //         System.out.println(sjMedicalSourceName + "\t|\t" + sjMedicalSourseAddress );
    //     }
    // }

    // public void findAndPrintPAMedicalSource(){
    //     System.out.println("------------------------------------------------------------");        
    //     System.out.println("\t\t Palo Alto Medical Source\t\t");
    //     System.out.println("------------------------------------------------------------");   
    //     System.out.println("Medical Source \t|\t Medical Source Address   ");
    //     for(OtherHealthCare paMS:cityMedicalSourceCatalog.get("Palo Alto")){
    //         String paMedicalSourceName = paMS.getHealthcareInsititutionName();
    //         String paMedicalSourseAddress = paMS.getLocation().getFullAddressName();
    //         System.out.println(paMedicalSourceName + "\t|\t" + paMedicalSourseAddress );
    //     }
    // }
    
    // public void findAndPrintSVMedicalSource(){
    //     System.out.println("------------------------------------------------------------");        
    //     System.out.println("\t\tSunnyvale Medical Source\t\t");
    //     System.out.println("------------------------------------------------------------");   
    //     System.out.println("Medical Source \t|\t Medical Source Address   ");
    //     for(OtherHealthCare svMS:cityMedicalSourceCatalog.get("Sunnyvale")){
    //         String svMedicalSourceName = svMS.getHealthcareInsititutionName();
    //         String svMedicalSourseAddress = svMS.getLocation().getFullAddressName();
    //         System.out.println(svMedicalSourceName + "\t|\t" + svMedicalSourseAddress );
    //     }
    // }

}
