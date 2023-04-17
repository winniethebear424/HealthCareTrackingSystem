package PatientManagement.Catalogs;

import java.util.ArrayList;

public class VitalSignsCatalog {
    ArrayList<AgeGroup> ageGroups;
    ArrayList<VitalSignLimits> vitalSignLimits;

    public VitalSignsCatalog() {
        vitalSignLimits = new ArrayList<VitalSignLimits>();
        ageGroups = new ArrayList<AgeGroup>();
    }

    public AgeGroup newAgeGroup(String name, int upper, int lower) {
        AgeGroup newGroup = new AgeGroup(name, upper, lower);
        ageGroups.add(newGroup);
        return newGroup;
    }

    public VitalSignLimits newVitalSignLimits(String name) {
        VitalSignLimits signLimits = new VitalSignLimits(name);
        vitalSignLimits.add(signLimits);
        return signLimits;
    }

    public AgeGroup findAgeGroupByName(String name) {
        for (AgeGroup ag : ageGroups) {
            if (ag.getName().equals(name))
                return ag;
        }
        return null;
    }

    public VitalSignLimits findVitalSignLimitsByName(String name) {
        for (VitalSignLimits vsl : vitalSignLimits) {
            if (vsl.getName().equals(name))
                return vsl;
        }
        return null;
    }

    public AgeGroup findAgeGroup(int age) {
        for (AgeGroup ag : ageGroups) {
            if (ag.isInGroup(age))
                return ag;
        }
        return null;
    }

    public Limits findVitalSignLimits(int age, String name) {
        // Trying to find vital sign limits by name
        VitalSignLimits vsl = findVitalSignLimitsByName(name);
        if (vsl == null)
            return null;
        // Trying to find a proper age group
        AgeGroup ageGroup = findAgeGroup(age);
        if (ageGroup == null)
            return null;
        return vsl.getLimits(ageGroup);
    }

    public Boolean isNormal(int age, String name, int value) {
        Limits limits = findVitalSignLimits(age, name);
        if (limits == null)
            return false;
        return limits.isWithinLimits(value);
    }

}
