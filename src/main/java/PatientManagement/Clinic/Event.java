/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import PatientManagement.Patient.Encounters.Encounter;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class Event {

    String date;
    Site site;
    String budgetcode;

    ArrayList<Encounter> encounters; // encounters that day

    public Event(Site s, String bc) {
        site = s;
        budgetcode = bc;
        date = "2022/02/27";
        encounters = new ArrayList<Encounter>(); // encounters done at the event/site

    }

    public void addEncounter(Encounter en) {
        encounters.add(en);
    }

    public int getConfirmedTotals() { // total numer of positive cases in event at the site
        int sum = 0;
        for (Encounter e : encounters) { // check all encounter at the event for confirmed cases

            if (e.getDiagnosis().isConfirmed()) {
                sum = sum + 1;

            }
        }

        return sum;
    }

    public ArrayList<Encounter> getConfirmedEncounters() { // return the actual confirmed encounters to you can extract
                                                           // the patient objects
        ArrayList<Encounter> temp = new ArrayList<Encounter>();
        for (Encounter e : encounters) { // check all encounter at the event for confirmed cases

            if (e.getDiagnosis().isConfirmed()) {
                temp.add(e); // encounter of confirmed case to the list to be returned

            }
        }

        return temp;
    }

    public boolean isMatch(String bn) {
        if (budgetcode.equals(bn)) {
            return true;
        } else {
            return false;
        }
    }
}
