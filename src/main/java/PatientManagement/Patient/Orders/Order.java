/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

import java.lang.reflect.Array;
import java.util.ArrayList;

import PatientManagement.Persona.Person;

/**
 *
 * @author kal bugrara
 */
public class Order {
    ArrayList<AssessmentOrder> assessmentOrders;
    ArrayList<MedicationOrder> medicationOrders;
    ArrayList<VaccinationOrder> vaccinationOrders;
    ArrayList<TreatmentOrder> treatmentOrders;

    Person performer;
    Person originator;

    public Order() {

        performer = null;
        originator = null;
    }

    // public Order(String treatmentName, String treatmentType){

    //     assessmentOrders = new ArrayList<AssessmentOrder>();
    //     medicationOrders = new ArrayList<MedicationOrder>();
    //     vaccinationOrders = new ArrayList<VaccinationOrder>();
    //     treatmentOrders = new ArrayList<TreatmentOrder>();

    // }

    public Order(Person from, Person to) {

        performer = to;
        originator = from;
    }
}
