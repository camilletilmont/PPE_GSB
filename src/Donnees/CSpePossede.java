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
public class CSpePossede {
    
    //attributs
    
    protected String diplome;
    protected int coeffPrescription;
    protected CSpecialite specialite;

    
    //Getters et Setters
    
    public String getDiplome() {
        return diplome;
    }

    public final void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public int getCoeffPrescription() {
        return coeffPrescription;
    }

    public final void setCoeffPrescription(int coeffPrescription) {
        this.coeffPrescription = coeffPrescription;
    }
    
     public CSpecialite getSpecialite() {
        return specialite;
    }

    public final void setSpecialite(CSpecialite specialite) {
        this.specialite = specialite;
    }
    
    //Constructeur
    
     public CSpePossede(String diplome, int coeffPrescription,CSpecialite spec){
        setDiplome(diplome);
        setCoeffPrescription(coeffPrescription);
        setSpecialite(spec);
     
     }
}
