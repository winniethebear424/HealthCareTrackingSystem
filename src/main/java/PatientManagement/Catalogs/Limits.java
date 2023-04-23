package PatientManagement.Catalogs;

public class Limits {
    int upper;
    int lower;

    public int getUpper() {
        return upper;
    }

    public int getLower() {
        return lower;
    }

    public Limits(int u, int l) {
        upper = u;
        lower = l;
    }

    public Boolean isWithinLimits(int value) {
        if ((value >= upper) || (value <= lower))
            return false;
        return true;
    }

    public String getRangeString (){
        return lower + " - " + upper;
    }
}
