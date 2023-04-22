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
public class Site {

    Location location;

    public Location getLocation() {
        return location;
    }

    public String getLocationString() {
        return location.toString();
    }

    public Site(Location loc) {
        location = loc;
    }

}
