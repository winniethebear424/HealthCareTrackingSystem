/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

import PatientManagement.Persona.Person;

/**
 *
 * @author kal bugrara
 */
public class Order {

    Person performer;
    Person originator;

    public Order() {

        performer = null;
        originator = null;
    }

    public Order(Person from, Person to) {

        performer = to;
        originator = from;
    }
}
