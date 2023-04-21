/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kal bugrara
 */

public class EventSchedule {

    ArrayList<Event> scheduledevents;

    public EventSchedule() {
        scheduledevents = new ArrayList<Event>();
    }

    public Event newEvent(Site s, String budgetnumer, Date date) {

        Event newevent = new Event(s, budgetnumer, date);
        scheduledevents.add(newevent);
        return newevent;
    }

    // finds all positive confirmations for all events that match the budgetnumber
    public int getEventConfirmedCasesByBudgetNumber(String budgetnumber) {
        int sum = 0;
        for (Event e : scheduledevents) {

            if (e.isMatch(budgetnumber))

                sum = sum + e.getConfirmedTotals();

        }
        return sum;

    }
    public ArrayList<Event> getScheduledevents() {
        return scheduledevents;
    }
}