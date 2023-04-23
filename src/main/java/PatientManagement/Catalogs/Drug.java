/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Catalogs;

/**
 *
 * @author kal bugrara
 */
public class Drug {
    String name;
    String treatedDisease;

    public String getName() {
        return name;
    }

    public String getTreatedDisease() {
        return treatedDisease;
    }

    public Drug(String name, String treatedDisease) {
        this.name = name;
        this.treatedDisease = treatedDisease;
    }

    public Drug(String n) {
        name = n;
    }

}
