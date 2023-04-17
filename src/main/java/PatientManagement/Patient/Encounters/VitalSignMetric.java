package PatientManagement.Patient.Encounters;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Patient.Patient;

public class VitalSignMetric {
    Patient patient;
    String name;
    Limits upperLower;
    int value;

    public VitalSignMetric(Patient p, String n, Limits l, int v) {
        patient = p;
        name = n;
        upperLower = l;
        value = v;
    }

    public Boolean isNormal() {
        return upperLower.isWithinLimits(value);
    }
}
