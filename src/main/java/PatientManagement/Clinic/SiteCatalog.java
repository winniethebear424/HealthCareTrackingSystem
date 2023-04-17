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
public class SiteCatalog {

    ArrayList<Site> sites;

    public SiteCatalog() {
        sites = new ArrayList<Site>();
    }

    public Site newSite(Location l) {
        Site s = new Site(l);
        sites.add(s);
        return s;
    }
}
