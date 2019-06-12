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

    //classe métier faisant le lien entre les classes manipulant les données de la BDD
    //et la frame
    
    
    //attributs
    protected CVisiteur visiteur;
    protected ArrayList<CRapportVisite> listeRapportVisite;
    protected ArrayList<CPraticien> listPraticien;
    protected ArrayList<CMedicament> listeMedicaments;
    protected CTableEchantillon tableEchan;
    protected ArrayList<CEchantillon> listEchan;
    protected CTableRapportVisite tableRV;
    protected CRapportVisite rapportV1;

    
    //getter et setter
    
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
    public CMetierRV() {
        CTablePraticien tablePratic = new CTablePraticien();
        setListPraticien(tablePratic.lirePraticiens());
        CTableMedicament tableLectureMedoc = new CTableMedicament();
        setListeMedicaments(tableLectureMedoc.lireMedicament());
        this.tableEchan = new CTableEchantillon();
        this.tableRV = new CTableRapportVisite();

    }

    //methodes
    
    
    //methode pour la connexion d'un visiteur avec vérification 
    
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

    //methode pour la création d'un rapport de visite
    public void creerRapportVisite(CRapportVisite rapportInsert) {

        try {
            
            //création du rapport
            tableRV.insererRapportVisite(rapportInsert);
            try {
                CRapportVisite rapportTempId = tableRV.lireDernierRapportVisite();

                    //création des echantillons liés
                    for (int a = 0; a < rapportInsert.getListeEchantillonRapport().size(); a++) {

                        try {
                            tableEchan.insererEchantillon(rapportInsert.getListeEchantillonRapport().get(a), rapportTempId);
                        } catch (Exception ex) {
                            System.out.println("Insertion Echantillon n° " + a + " IHM = null");
                        }

                    }
                

            } catch (Exception ex) {
                System.out.println("Lecture dernier RV IHM = null");
            }

        } catch (Exception ex) {
            System.out.println("Insertion RV IHM = null");
        }

    }

    
    //methode pour la lecture d'un rapport en particulier
    public int lire1RV(String numero) {

        ArrayList<CRapportVisite> rapportTest = getTableRV().lire1RapportVisite("RAP_NUM_RAPPORT_VISITE", numero);

        if (rapportTest.isEmpty()) {

            return 0;
        } else {
            setRapportV1(rapportTest.get(0));
            return 2;
        }
    }

    
    //methode pour la modification d'un rapport existant
    public void modifierRapportVisite(CRapportVisite rapportUpdate) {
        
        try {
            //lance la modification du rapport
            tableRV.modifierRapportVisite(rapportUpdate);
            try {
                //recupère tous les echantillons liés à ce rapport dans la BDD
                ArrayList<CEchantillon> listEchanExistInTab = tableEchan.lire1Echantillon(Integer.toString(rapportUpdate.getIdRapportVisite()));
                
                    for (int a = 0; a < rapportUpdate.getListeEchantillonRapport().size(); a++) {
                            boolean testExist = false;
                        try {
                            
                            //compare les enchatillons du rapport de la BDD et ceux de l'object rapport modifié
                            
                            
                            for(int x = 0; x < listEchanExistInTab.size();x++){
                                if(listEchanExistInTab.get(x).getMedicamentEchantillon().getNomCommercial().equals(rapportUpdate.getListeEchantillonRapport().get(a).getMedicamentEchantillon().getNomCommercial())){
                                    testExist = true;
                                }
                            }
                            
                            
                            //applique les modification ou les nouvelles insertions d'échantillons
                            if(testExist){
                            tableEchan.modifierEchantillon(rapportUpdate.getListeEchantillonRapport().get(a), rapportUpdate);
                            }else{
                            tableEchan.insererEchantillon(rapportUpdate.getListeEchantillonRapport().get(a), rapportUpdate);
                            }
                            
                           
                            
                            
                        } catch (Exception ex) {
                            System.out.println("Mise à jour Echantillon n° " + a + " IHM = null");
                        }

                    }
                    
                    
                     try{
                         
                         //compare la liste des chantillons de la BDD et ceux du rapport modifié pour voir si certains ont disparu
                         for(int y = 0; y < listEchanExistInTab.size();y++){
                             
                             //si le rapport modifié n'a plus d'échantillons ont les supprimes tous dans la BDD
                             //sinon on supprime uniquement ceux qui ont disparus
                             if(!rapportUpdate.getListeEchantillonRapport().isEmpty()){
                             boolean testDelete = false;
                             for (int b = 0; b < rapportUpdate.getListeEchantillonRapport().size(); b++) {
                             
                                  if(rapportUpdate.getListeEchantillonRapport().get(b).getMedicamentEchantillon().getNomCommercial().equals(listEchanExistInTab.get(y).getMedicamentEchantillon().getNomCommercial())){
                                  
                                  testDelete = true;
                                  }
                                 
                             
                             }
                             
                             if(!testDelete){
                                 tableEchan.supprimerEchantillon(Integer.toString(rapportUpdate.getIdRapportVisite()), Integer.toString(listEchanExistInTab.get(y).getMedicamentEchantillon().getDepotLegal()));
                             
                             
                             }
                             }else{
                                  tableEchan.supprimerEchantillon(Integer.toString(rapportUpdate.getIdRapportVisite()), Integer.toString(listEchanExistInTab.get(y).getMedicamentEchantillon().getDepotLegal()));  
                                     }
                     
                         } 
                            
                            }catch(Exception ex){
                            
                                System.out.println("Suppression MAJ Echantillon IHM");
                            }
                    
                

            } catch (Exception ex) {
                System.out.println("Lecture Echantillon pour MAJ IHM = null");
            }

        } catch (Exception ex) {
            System.out.println("MAJ RV IHM = null");
        }

        
        
    }

    
    //methode pour la suppresion d'un rapport (uniquement pour l'admin)
    public void supprimerRapportVisite() {
        
        
        String idRapportToDelete = Integer.toString(rapportV1.getIdRapportVisite());
        //on supprime d'avord le echantillons de la table OFFRIR qui font références
        //au rapport à supprimer
        try{
        
        tableEchan.supprimerEchantillonFrom1Rapport(idRapportToDelete);
        
            try{
            //on supprime le rapport en question
            tableRV.supprimerRapportVisite("RAP_NUM_RAPPORT_VISITE", idRapportToDelete);
            }catch(Exception ex){
            System.out.println("Suppresion d'un rapport IHM = impossible");
            
            }
        
        
        }catch(Exception ex){
        
        System.out.println("Suppression des echantillons avant suppression rapport IHM = impossible");
        }
        
    }

    //methode pour afficher sur la page principale les rapports du visiteur connecté
    //pour l'admin cela affiche tous les rapports de tous les visiteurs
    public int afficherRPVisiteurs() {
        CTableRapportVisite tableRP = new CTableRapportVisite();
        
        ArrayList<CRapportVisite> rapportsTest;
        
        if(getVisiteur().getRole().equals("Administrateur")){
            rapportsTest = tableRP.lireRapportVisite();

        if (rapportsTest.isEmpty()) {
            setListeRapportVisite(rapportsTest);
            return 1;
        } else {
            setListeRapportVisite(rapportsTest);
            return 3;

        }
            
        
        }else{
        rapportsTest = tableRP.lire1RapportVisite("VIS_MATRICULE_VISITEUR", getVisiteur().getMatricule());

        if (rapportsTest.isEmpty()) {
        setListeRapportVisite(rapportsTest);
            return 0;
        } else {
            setListeRapportVisite(rapportsTest);
            return 2;

        }
        }
    }

}
