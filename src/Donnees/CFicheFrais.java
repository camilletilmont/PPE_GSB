/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

import java.util.List;

/** 
 *
 * @author Sabrina Cos
 */
public final class CFicheFrais {
    
    //attributs FicheFrais
    
    protected int mois;
    protected int nbrHorsClassif;
    protected float montantHorsClassif;
    protected String matriculeVisit;
    protected List<CFrais> fraisList;
    
    
    
    // Getters et Setters

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getNbrHorsClassif() {
        return nbrHorsClassif;
    }

    public void setNbrHorsClassif(int nbrHorsClassif) {
        this.nbrHorsClassif = nbrHorsClassif;
    }

    public float getMontantHorsClassif() {
        return montantHorsClassif;
    }

    public void setMontantHorsClassif(float montantHorsClassif) {
        this.montantHorsClassif = montantHorsClassif;
    }

    public String getMatriculeVisit() {
        return matriculeVisit;
    }

    public void setMatriculeVisit(String matricule) {
        this.matriculeVisit = matricule;
    }

    public List<CFrais> getFraisList() {
        return fraisList;
    }

    public void setFraisList(List<CFrais> fraisList) {
        this.fraisList = fraisList;
    }
    
    
    
    //Constructeur
    
    public CFicheFrais (int mois, int nbrHorsClassif, float montantHorsClass, String matricule, List<CFrais> fraisList){
        setMois(mois);
        setNbrHorsClassif(nbrHorsClassif);
        setMontantHorsClassif(montantHorsClass);
        setMatriculeVisit(matricule);
        setFraisList(fraisList);
        
    }
    
    
}