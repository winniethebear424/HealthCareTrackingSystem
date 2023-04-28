/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import PatientManagement.Patient.Encounters.Encounter;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author kal bugrara
 */
public class Event {

    Date date;
    Site site;
    String budgetcode;
    ArrayList<Encounter> encounters; // encounters that day

    public Event(Site s, String bc, Date date) {
        site = s;
        budgetcode = bc;
        this.date = date;
        encounters = new ArrayList<Encounter>(); // encounters done at the event/site
    }

    public Event(String location, Date date) {
        Location l = new Location(location);
        Site s = new Site(l);
        site = s;
        this.date = date;
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
    public Date getDate() {
        return date;
    }

    public String getDateString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    public Site getSite() {
        return site;
    }
}
