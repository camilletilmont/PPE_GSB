/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CEchantillon;
import Donnees.CPraticien;
import Donnees.CRapportVisite;
import Donnees.CVisiteur;
import java.util.ArrayList;

/**
 *
 * @author camilletilmont
 */
public class CMetierRV {
    

    //attributs
    
    protected CVisiteur visiteur;
    protected CRapportVisite rapportVisite;
    protected ArrayList<CPraticien> listPraticien;
    protected ArrayList<CEchantillon> listeLectureEchantillion;

   

    //getter et setter
    
    public CVisiteur getVisiteur() {
        return visiteur;
    }

    public final void setVisiteur(CVisiteur visiteur) {
        this.visiteur = visiteur;
    }

    public CRapportVisite getRapportVisite() {
        return rapportVisite;
    }

    public void setRapportVisite(CRapportVisite rapportVisite) {
        this.rapportVisite = rapportVisite;
    }
    
   
    public ArrayList<CPraticien> getListPraticien() {
        return listPraticien;
    }

    public final void setListPraticien(ArrayList<CPraticien> listPraticien) {
        this.listPraticien = listPraticien;
    }
    
     public ArrayList<CEchantillon> getListeLectureEchantillion() {
        return listeLectureEchantillion;
    }

    public final void setListeLectureEchantillion(ArrayList<CEchantillon> listeLectureEchantillion) {
        this.listeLectureEchantillion = listeLectureEchantillion;
    }

    //constructeur
    
    public CMetierRV(){
        CTablePraticien tablePratic = new CTablePraticien();
        setListPraticien(tablePratic.lirePraticiens());
        CTableEchantillon tableLectureEchantillon = new CTableEchantillon();
        setListeLectureEchantillion(tableLectureEchantillon.lireEchantillon());
        
        
    }
    
    //methodes
    
    
    public int connexion(String id, String nom) {
        CTableVisiteur tableVisit = new CTableVisiteur();
        ArrayList<CVisiteur> visiTest = tableVisit.lire1Visiteurs("VIS_MATRICULE_VISITEUR", id);
        if (visiTest.isEmpty()) {

            return 0;
        } else if (!visiTest.get(0).getNom().equals(nom)) {

            return 1;

        } else if (visiTest.get(0).getNom().equals(nom)) {
            setVisiteur(visiTest.get(0));
            return 2;
        } else {

            return 3;

        }
    }
    
    public void creerRapportVisite(String bilan, String motif,CPraticien pratic){
    
        
    //rapportVisite = new CRapportVisite(0, new GregorianCalendar(), bilan, motif, getVisiteur(), pratic, listEchantillonRap, listMedicamentPresRap);
    
    }
    
    public void lireRapportVisite(){}
    
    public void modifierRapportVisite(){}
    
    public void supprimerRapportVisite(){}
    
}
