/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

/**
 *
 * @author kal bugrara
 */
public class Location {
    String fullAddressName;
    String city;
    String street;

    public Location(String city, String street) {
        this.city = city;
        this.street = street;
        this.fullAddressName = street + ", " + city;
    }

    public Location(String fullAddressName) {
        this.fullAddressName = fullAddressName;
    }

    public String getFullAddressName() {
        return fullAddressName;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

}
