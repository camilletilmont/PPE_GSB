/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

/**
 *
 * @author camilletilmont
 */
public class CInvitation {
    
    //attributs
    protected CPraticien praticien;
    protected String specialisteOn;

    
    //getter et setter
    public CPraticien getPraticien() {
        return praticien;
    }

    public final void setPraticien(CPraticien praticien) {
        this.praticien = praticien;
    }

    public String getSpecialisteOn() {
        return specialisteOn;
    }

    public final void setSpecialisteOn(String specialisteOn) {
        this.specialisteOn = specialisteOn;
    }
    
    
    //constructeur
    public CInvitation(CPraticien prat, String speOn){
        setPraticien(prat);
        setSpecialisteOn(speOn);
    
    }
}
