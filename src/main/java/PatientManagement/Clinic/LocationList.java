/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class LocationList {
    ArrayList<Location> locations;

    public LocationList() {
        locations = new ArrayList<Location>();
    }

    public Location getLocation(String locname) {
        // search for a location object by locname;
        Location l = null;
        return l;
    }

    public Location newLocation(String name) {
        Location newLoc = new Location(name);
        locations.add(newLoc);
        return newLoc;
    }

    public Location newLocation(String city, String street) {
        Location newLoc = new Location(city, street);
        locations.add(newLoc);
        return newLoc;
    }

}
