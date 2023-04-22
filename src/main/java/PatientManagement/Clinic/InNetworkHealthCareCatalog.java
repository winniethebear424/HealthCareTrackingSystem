package PatientManagement.Clinic;

import java.util.ArrayList;

public class InNetworkHealthCareCatalog {
    ArrayList<OtherHealthCare> sanJoseClinics;
    ArrayList<OtherHealthCare> paloAltoClinics;
    ArrayList<OtherHealthCare> sunnyvaleClinics;

    public InNetworkHealthCareCatalog() {
    sanJoseClinics = new ArrayList<OtherHealthCare> ();
    paloAltoClinics = new ArrayList<OtherHealthCare> ();;
    sunnyvaleClinics = new ArrayList<OtherHealthCare> ();;
    }
    
    public ArrayList<OtherHealthCare> getSanJoseClinics() {
        return sanJoseClinics;
    }

    public ArrayList<OtherHealthCare> getPaloAltoClinics() {
        return paloAltoClinics;
    }
 
    public ArrayList<OtherHealthCare> getsunnyvaleClinics() {
        return sunnyvaleClinics;
    }

    public void addMedicalSourceToSJ(OtherHealthCare medicalSources){
        sanJoseClinics.add(medicalSources);
    }

    public void addMedicalSourceToPA(OtherHealthCare medicalSources){
        paloAltoClinics.add(medicalSources);
    }

    public void addMedicalSourceToSV(OtherHealthCare medicalSources){
        sunnyvaleClinics.add(medicalSources);
    }

    public void findAndPrintSJMedicalSource(){
        System.out.println("------------------------------------------------------------");        
        System.out.println("\t\tSan Jose Medical Source\t\t");
        System.out.println("------------------------------------------------------------");   
        System.out.println("Medical Source \t|\t Medical Source Address   ");
        for(OtherHealthCare sjMS:sanJoseClinics){
            String sjMedicalSourceName = sjMS.getHealthcareInsititutionName();
            String sjMedicalSourseAddress = sjMS.getLocation().getFullAddressName();
            System.out.println(sjMedicalSourceName + "\t|\t" + sjMedicalSourseAddress );
        }
    }

    public void findAndPrintPAMedicalSource(){
        System.out.println("------------------------------------------------------------");        
        System.out.println("\t\t PaloAlto Medical Source\t\t");
        System.out.println("------------------------------------------------------------");   
        System.out.println("Medical Source \t|\t Medical Source Address   ");
        for(OtherHealthCare paMS:paloAltoClinics){
            String paMedicalSourceName = paMS.getHealthcareInsititutionName();
            String paMedicalSourseAddress = paMS.getLocation().getFullAddressName();
            System.out.println(paMedicalSourceName + "\t|\t" + paMedicalSourseAddress );
        }
    }
    
    public void findAndPrintSVMedicalSource(){
        System.out.println("------------------------------------------------------------");        
        System.out.println("\t\tsunnyVale Medical Source\t\t");
        System.out.println("------------------------------------------------------------");   
        System.out.println("Medical Source \t|\t Medical Source Address   ");
        for(OtherHealthCare svMS:sunnyvaleClinics){
            String svMedicalSourceName = svMS.getHealthcareInsititutionName();
            String svMedicalSourseAddress = svMS.getLocation().getFullAddressName();
            System.out.println(svMedicalSourceName + "\t|\t" + svMedicalSourseAddress );
        }
    }

}
