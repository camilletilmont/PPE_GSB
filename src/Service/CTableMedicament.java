/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CFamille;
import Donnees.CFormeMedicament;
import Donnees.CMedicament;
import Donnees.CFrais;
import Donnees.CPosologie;
import Donnees.CValeurComposant;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilletilmont
 */
public class CTableMedicament {
    //attributs
    protected ArrayList<CMedicament> listeMedicament;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CMedicament> getListeMedicament() {
        return listeMedicament;
    }

    public void setListeFrais(ArrayList<CMedicament> listeMedicament) {
        this.listeMedicament = listeMedicament;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableMedicament(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE MEDICAMENT (MED_DEPOTLEGAL_MEDICAMENT INT  AUTO_INCREMENT NOT NULL,"
                + " MED_NOMCOMMERCIAL_MEDICAMENT VARCHAR(50),"
                + " MED_COMPOSITION_MEDICAMENT TEXT,"
                + " MED_EFFETS_MEDICAMENT TEXT,"
                + " MED_CONTREINDIC_MEDICAMENT TEXT,"
                + " MED_PRIXECHANTILLON_MEDICAMENT FLOAT,"
                + " FAM_CODE_FAMILLE SMALLINT NOT NULL,"
                + " PRIMARY KEY (MED_DEPOTLEGAL_MEDICAMENT) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.MEDICAMENT KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`MEDICAMENT`;";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.MEDICAMENT KO");
        }
        return res;
    }
     
     //insértion d'un Medicament
     
     public int insererMedicament(CMedicament medicament) {
        String req = "INSERT INTO `MEDICAMENT` (`MED_DEPOTLEGAL_MEDICAMENT`, `MED_NOMCOMMERCIAL_MEDICAMENT`,"
                + " `MED_COMPOSITION_MEDICAMENT`,`MED_EFFETS_MEDICAMENT`,`MED_CONTREINDIC_MEDICAMENT`,`MED_PRIXECHANTILLON_MEDICAMENT`,`FAM_CODE_FAMILLE`)"
                + " VALUES ('"+ medicament.getDepotLegal()+"',"
                + " '"+medicament.getNomCommercial()+"',"
                + " '"+medicament.getComposition()+"',"
                + " '"+medicament.getEffets()+"',"
                + " '"+medicament.getContreIndic()+"',"
                + " '"+medicament.getPrixEchantillon()+"',"
                + " '"+ medicament.getFamille().getIdFamille() +"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.MEDICAMENT KO");
        }
        return res;
    }
     
     
//     //modification d'un Medicament
     
     public int modifierMedicament(CMedicament medicament) {

        String req = "UPDATE MEDICAMENT SET "
                + "MED_DEPOTLEGAL_MEDICAMENT = '" + medicament.getDepotLegal() + "', "
                + "MED_NOMCOMMERCIAL_MEDICAMENT = '"+ medicament.getNomCommercial() + "', "
                + "MED_COMPOSITION_MEDICAMENT = '"+ medicament.getComposition()+"', "
                + "MED_EFFETS_MEDICAMENT = '"+ medicament.getEffets()+"', "
                + "MED_CONTREINDIC_MEDICAMENT = '"+ medicament.getContreIndic()+"', "
                + "MED_PRIXECHANTILLON_MEDICAMENT = '"+ medicament.getPrixEchantillon()+"', "
                + "FAM_CODE_FAMILLE = '"+ medicament.getFamille().getIdFamille() + "' "
                + " WHERE MED_DEPOTLEGAL_MEDICAMENT = '"+ medicament.getDepotLegal() + "' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.MEDICAMENT KO");
        }
        return res;
    }
     
     
     //suppression d'un Medicament
     
     public int supprimerMedicament(String colone, String data) {
        String req = "DELETE FROM MEDICAMENT WHERE "+colone +" = '" + data +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.MEDICAMENT KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CMedicament convertir_RS_Medicament(ResultSet rs) {
        try {
            
            int depotId = rs.getInt("MED_DEPOTLEGAL_MEDICAMENT");
            String nom = rs.getString("MED_NOMCOMMERCIAL_MEDICAMENT");
            String compo = rs.getString("MED_COMPOSITION_MEDICAMENT");
            String effets = rs.getString("MED_EFFETS_MEDICAMENT");
            String contrIndic = rs.getString("MED_CONTREINDIC_MEDICAMENT");
            float prix = rs.getFloat("MED_PRIXECHANTILLON_MEDICAMENT");
        
            //objet famille
           CTableFamille tableFamille = new CTableFamille();
           CFamille famille = tableFamille.lire1Famille("FAM_CODE_FAMILLE", rs.getString("FAM_CODE_FAMILLE")).get(0);
            
           //liste composants
           CTableValeurComposant tableConstituer = new CTableValeurComposant();
           ArrayList<CValeurComposant> listValCompo = tableConstituer.lire1ValeurComposant(rs.getString("MED_DEPOTLEGAL_MEDICAMENT"));
           
           //liste Presentations
           CTableMedicamentFormuler tableFormuler = new CTableMedicamentFormuler();
           ArrayList<String> listIdPresentation = tableFormuler.lire1IdFormulationFormuler(rs.getString("MED_DEPOTLEGAL_MEDICAMENT"));
           CTableFormeMedicament tablePresentationMedoc = new CTableFormeMedicament();
           ArrayList<CFormeMedicament> listFormMedoc = new ArrayList();
           listIdPresentation.forEach((liste1) -> {
                   CFormeMedicament formMedoc = tablePresentationMedoc.lire1FormeMedicament("PRE_CODE_PRESENTATION", liste1).get(0);
                   listFormMedoc.add(formMedoc);
                   });
            
           //liste medocs contre indication
            CTableInteractionMedicament tableInteragir = new CTableInteractionMedicament();
            ArrayList<String> listDepotLegInter = tableInteragir.lire1IdMedocInteraction(rs.getString("MED_DEPOTLEGAL_MEDICAMENT"));
            
            
            //liste posologies
            CTablePosologie tablePoso = new CTablePosologie();
            ArrayList<CPosologie> listPoso = tablePoso.lire1Posologies(rs.getString("MED_DEPOTLEGAL_MEDICAMENT"));
            
                    return new CMedicament(depotId, nom, compo, effets, contrIndic, prix, famille, listValCompo, listFormMedoc, listDepotLegInter, listPoso);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CFrais.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CMedicament> lireMedicament() {
        if (bdd.connecter() == true) {
            ArrayList<CMedicament> listeMedocs = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `MEDICAMENT`");
            try {
                while (rs.next()) {
                    CMedicament Medicament = convertir_RS_Medicament(rs);
                    listeMedocs.add(Medicament);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeMedocs;
        } else {
            System.out.println("Connexion manipBdd.MEDICAMENT KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains medicaments,
     //en fonction l'information souhaitée.
     
     public ArrayList<CMedicament> lire1Medicament(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CMedicament> liste1Medoc = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `MEDICAMENT` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CMedicament Medicament = convertir_RS_Medicament(rs);
                    liste1Medoc.add(Medicament);
                    
                }
               if(liste1Medoc.isEmpty()){
                   System.out.println("MEDICAMENT Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1Medoc;
        } else {
            System.out.println("Connexion manipBdd.MEDICAMENT KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste d'activités selectionnées.
     
     
     public void printMedicament(ArrayList<CMedicament> liste){
         liste.forEach((liste1) -> {
             System.out.println("Nom : " +liste1.getNomCommercial() + ". Id : "+liste1.getDepotLegal() +". Famille : " + liste1.getFamille().getNomFamille());
        liste1.getListMedicamentsInteraction().forEach((listeInter) -> {
             System.out.println("Nom Medoc Interaction : " + listeInter+ ".");
         
         });
         liste1.getListValeurComposants().forEach((listComp) -> {
             System.out.println("Nom Composant :" + listComp.getComposant().getNomComposant()+ ". Quantite : "+listComp.getQuantite()+".");
         
         });
         liste1.getListFormeMedicament().forEach((listForm) -> {
             System.out.println("Nom Formulation :" + listForm.getNomFormMedic()+ ".");
         
         });
         liste1.getListPosologie().forEach((listPoso) -> {
             System.out.println("Unite dosage :" + listPoso.getDosagePeriodique().getUniteDosage()+". Nombre Jour poso :" + listPoso.getNbrJourPosologie() + ". Type Individu : "+listPoso.getTypeIndividu().getLibelleTypeIndividu() + ".");
         
         });
         });
     
     }
     
     public static void main(String[] args) {
     
//         CTableMedicament tableMedicament = new CTableMedicament();
//         CTableValeurComposant tableConstituer = new CTableValeurComposant();
//           ArrayList<CValeurComposant> listValCompo = new ArrayList();
//           
//           
//           ArrayList<CFormeMedicament> listFormMedoc = new ArrayList();
//           ArrayList<String> listMed = new ArrayList();
//           ArrayList<CPosologie> listPos = new ArrayList();
//           
//         CMedicament medoc = new CMedicament(124, "Tsoin", "Safran", "Psycho", "Toux", 23.8f, new CFamille(3, "Anti-inflammatoire"),listValCompo , listFormMedoc, listMed, listPos);
         //tableMedicament.insererMedicament(medoc);
         //tableMedicament.supprimerMedicament("MED_DEPOTLEGAL_MEDICAMENT", "124");
         //tableMedicament.modifierMedicament(medoc);
         //tableMedicament.printMedicament(tableMedicament.lire1Medicament("MED_DEPOTLEGAL_MEDICAMENT", "124"));
         //tableMedicament.printMedicament(tableMedicament.lireMedicament());
}
}
