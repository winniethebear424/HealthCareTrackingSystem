package PatientManagement.Catalogs;

import java.util.ArrayList;

public class AgeGroup {
    String name;
    Limits ageLimits;
    ArrayList<VitalSignLimits> vitalSignLimits;

    public AgeGroup(String name, int upper, int lower) {
        this.name = name;
        ageLimits = new Limits(upper, lower);
        vitalSignLimits = new ArrayList<VitalSignLimits>();
    }

    public String getName() {
        return name;
    }

    public Boolean isInGroup(int age) {
        return ageLimits.isWithinLimits(age);
    }

    public void addLimits(VitalSignLimits limits) {
        vitalSignLimits.add(limits);
    }
}
