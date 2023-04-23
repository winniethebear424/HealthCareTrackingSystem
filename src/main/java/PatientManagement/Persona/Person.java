package PatientManagement.Persona;

import PatientManagement.Patient.Patient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kal bugrara
 */
public class Person {
    String name;
    String id;
    Person mother;
    Person father;
    ArrayList<Person> siblings;
    Patient patient;
    int age;
    HashMap<String, String> Seen;

    public Person(String id, int a) {
        this.id = id;
        siblings = new ArrayList<Person>();
        age = a;
        Seen = new HashMap<String, String>();
    }

    public Person(String name, String id, int a) {
        this.id = id;
        this.name = name;
        age = a;
        Seen = new HashMap<String, String>();
    }
    public String getName(){
        return name;
    }
    
    public Person getMother() {
        return mother;
    }

    public Person getFather() {
        return father;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Person> getSiblings() {
        return siblings;
    }

    public void setSiblings(ArrayList<Person> siblings) {
        this.siblings = siblings;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public String getPersonId() {
        return id;
    }

    public void setFather(Person f) {
        father = f;
    }

    public void setMother(Person m) {
        mother = m;
    }

    public void addSibling(Person s) {
        if (siblings.contains(s))
            return; // sibling already in the arraylist
        siblings.add(s);
    }

    public boolean isMatch(String id) {
        if (getPersonId().equals(id))
            return true;
        return false;
    }

    public int getAge() {
        return age;
    }
    
    public void addSeen(String time, String location) {    //Set
        Seen.put(time, location);
    }

    public String getSeenTimeByLocation(String location){
        for (Map.Entry<String, String> entry : Seen.entrySet()) {
            if (entry.getValue().equals(location)) {
                return entry.getKey();
            }
        }
        return "Location not found";
    }

    public HashMap<String, String> getSeen() {
        return Seen;
    }   //This is to show SeenHistory


    public String getLastSeen() {    //This is to get the most recent track
        String mostRecent = null;
        for (Map.Entry<String,String>entry: Seen.entrySet()){
            if (mostRecent == null || entry.getKey().compareTo(mostRecent)>0) {
                mostRecent = entry.getKey();
            }
        }
        if (mostRecent !=null){
            System.out.println(mostRecent+ ": " + Seen.get(mostRecent));
        }
        return mostRecent+ ": " +Seen.get(mostRecent);
        }

}
