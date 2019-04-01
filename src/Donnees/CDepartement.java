/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

/**
 *
 * @author Sabrina Cos
 */
public class CDepartement {
     
    //attributs Departement
    
    protected int idDepartement;
    protected String nomDep;
    protected boolean chefVente;
    
    
    
    // Getters et Setters
    
    public int getIdDepartement() {
        return idDepartement;
    }

    public final void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getNomDep() {
        return nomDep;
    }

    public final void setNomDep(String nomDep) {
        this.nomDep = nomDep;
    }

    public boolean isChefVente() {
        return chefVente;
    }

    public final void setChefVente(boolean chefVente) {
        this.chefVente = chefVente;
    }
    
    
    
    // Constructeur
    
    public CDepartement(int idDepartement, String nomDep, boolean chefVente){
        setIdDepartement(idDepartement);
        setNomDep(nomDep);
        setChefVente(chefVente);
    
    }
    
}