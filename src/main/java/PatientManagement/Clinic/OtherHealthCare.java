package PatientManagement.Clinic;

public class OtherHealthCare {
    String healthcareInsititutionName;
    Location location;

    public OtherHealthCare(String healthcareInsititutionName, Location location) {
        this.healthcareInsititutionName = healthcareInsititutionName;
        this.location = location;
    }


    public String getHealthcareInsititutionName() {
        return healthcareInsititutionName;
    }
    public void setHealthcareInsititutionName(String healthcareInsititutionName) {
        this.healthcareInsititutionName = healthcareInsititutionName;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }



}
