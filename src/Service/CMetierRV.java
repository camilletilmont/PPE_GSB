/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CEchantillon;
import Donnees.CMedicament;
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
    protected ArrayList<CRapportVisite> listeRapportVisite;
    protected ArrayList<CPraticien> listPraticien;
    protected ArrayList<CMedicament> listeMedicaments;
    protected CTableEchantillon tableEchan;
    protected ArrayList<CEchantillon> listEchan;
    protected CTableRapportVisite tableRV;
    protected CRapportVisite rapportV1;

    
    
    public ArrayList<CEchantillon> getListEchan() {
        return listEchan;
    }

    public void setListEchan(ArrayList<CEchantillon> listEchan) {
        this.listEchan = listEchan;
    }
    
    public CTableEchantillon getTableEchan() {
        return tableEchan;
    }

    public void setTableEchan(CTableEchantillon tableEchan) {
        this.tableEchan = tableEchan;
    }
    

    public CRapportVisite getRapportV1() {
        return rapportV1;
    }

    public void setRapportV1(CRapportVisite rapportV1) {
        this.rapportV1 = rapportV1;
    }
    

    public CTableRapportVisite getTableRV() {
        return tableRV;
    }

    public void setTableRV(CTableRapportVisite tableRV) {
        this.tableRV = tableRV;
    }

   
   

    //getter et setter
    
    public CVisiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(CVisiteur visiteur) {
        this.visiteur = visiteur;
    }

    public ArrayList<CRapportVisite> getListeRapportVisite() {
        return listeRapportVisite;
    }

    public void setListeRapportVisite(ArrayList<CRapportVisite> listeRapportVisite) {
        this.listeRapportVisite = listeRapportVisite;
    }

    public ArrayList<CPraticien> getListPraticien() {
        return listPraticien;
    }

    public final void setListPraticien(ArrayList<CPraticien> listPraticien) {
        this.listPraticien = listPraticien;
    }

    public ArrayList<CMedicament> getListeMedicaments() {
        return listeMedicaments;
    }

    public final void setListeMedicaments(ArrayList<CMedicament> listeMedoc) {
        this.listeMedicaments = listeMedoc;
    }


    //constructeur
    
    public CMetierRV(){
        CTablePraticien tablePratic = new CTablePraticien();
        setListPraticien(tablePratic.lirePraticiens());
        CTableMedicament tableLectureMedoc = new CTableMedicament();
        setListeMedicaments(tableLectureMedoc.lireMedicament());
        this.tableEchan = new CTableEchantillon();
        this.tableRV = new CTableRapportVisite();
        
        
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
    
    public void deconnexion(){

    
    }
    
    public void creerRapportVisite(String bilan, String motif,CPraticien pratic){
    
        
    //rapportVisite = new CRapportVisite(0, new GregorianCalendar(), bilan, motif, getVisiteur(), pratic, listEchantillonRap, listMedicamentPresRap);
    
    }
    
    public int lire1RV(String numero){
        
        
        ArrayList<CRapportVisite> rapportTest = getTableRV().lire1RapportVisite("RAP_NUM_RAPPORT_VISITE", numero);
        
        if(rapportTest.isEmpty()){
        
        return 0;
        }else{
        setRapportV1(rapportTest.get(0));
        return 2;
        }
    }
    
    
   
    
    public void modifierRapportVisite(){}
    
    public void supprimerRapportVisite(){}
    
    
    public void ajouterMedicOffert(){}
    
    public int afficherRPVisiteurs(){
       CTableRapportVisite tableRP = new CTableRapportVisite();
       ArrayList<CRapportVisite> rapportsTest = tableRP.lire1RapportVisite("VIS_MATRICULE_VISITEUR", getVisiteur().getMatricule());
       
       if(rapportsTest.isEmpty()){
           return 0;
       }else{
        setListeRapportVisite(rapportsTest);
        return 2;
       
       }
    }
    
}
