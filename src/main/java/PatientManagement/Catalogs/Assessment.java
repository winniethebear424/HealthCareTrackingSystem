package PatientManagement.Catalogs;

public class Assessment {
    String assessmentName;
    Boolean result;

    public Assessment(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentName() {
        return assessmentName;
    }
    public void setResult(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

}
