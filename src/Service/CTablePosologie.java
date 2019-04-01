/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CDosagePeriodique;
import Donnees.CPosologie;
import Donnees.CTypeIndividu;
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
public class CTablePosologie {
    //attributs.
    
     protected ArrayList<CPosologie> listePosologies;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CPosologie> getListePosologies() {
        return listePosologies;
    }

    public void setListePosologies(ArrayList<CPosologie> listePosologies) {
        this.listePosologies = listePosologies;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTablePosologie(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE PRESCRIRE (MED_DEPOTLEGAL_MEDICAMENT INT  AUTO_INCREMENT NOT NULL,"
                + " TIN_CODE_TYPE_INDIVIDU TINYINT NOT NULL,"
                + " DOS_CODE_DOSAGE TINYINT NOT NULL,"
                + " PRE_POSOLOGIE TINYINT,"
                + " PRIMARY KEY (MED_DEPOTLEGAL_MEDICAMENT,"
                + " TIN_CODE_TYPE_INDIVIDU,"
                + " DOS_CODE_DOSAGE) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESCRIRE KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`PRESCRIRE`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESCRIRE KO");
        }
        return res;
    }
     
     //insértion d'une Posologie
     
     public int insererPosologie(CPosologie posologie, /*CMedicament*/ int medoc) {
        String req = "INSERT INTO `PRESCRIRE` (`MED_DEPOTLEGAL_MEDICAMENT`,`TIN_CODE_TYPE_INDIVIDU`,"
                + " `DOS_CODE_DOSAGE`,"
                + " `PRE_POSOLOGIE`) "
                + "VALUES ('"+ medoc/*.getDepotLegal()*/+"',"
                + " '"+posologie.getTypeIndividu().getIdTypeIndividu()+"',"
                + " '"+posologie.getDosagePeriodique().getIdDosage()+"',"
                + " '"+posologie.getNbrJourPosologie()+"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESCRIRE KO");
        }
        return res;
    }
     
     
//     //modification d'une Posologie selon le depot légal du médicament, l'id du type d'individu et l'id du dosage
     
     public int modifierPosologie(CPosologie posologie, /*CMedicament*/ int medoc) {

        String req = "UPDATE PRESCRIRE SET "
                + "MED_DEPOTLEGAL_MEDICAMENT = '" + medoc/*.getDepotLegal()*/ + "', "
                + "TIN_CODE_TYPE_INDIVIDU = '"+ posologie.getTypeIndividu().getIdTypeIndividu() + "', "
                + "DOS_CODE_DOSAGE = '"+ posologie.getDosagePeriodique().getIdDosage() + "', "
                + "PRE_POSOLOGIE = '" + posologie.getNbrJourPosologie() +"' "
                + " WHERE MED_DEPOTLEGAL_MEDICAMENT = '"+ medoc/*.getDepotLegal()*/ + "'"
                + " AND TIN_CODE_TYPE_INDIVIDU = '"+posologie.getTypeIndividu().getIdTypeIndividu() +"'"
                + " AND DOS_CODE_DOSAGE = '"+ posologie.getDosagePeriodique().getIdDosage() +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESCRIRE KO");
        }
        return res;
    }
     
     
     //suppression d'une Posologie selon le depot légal du médicament, l'id du type d'individu et l'id du dosage
     
     public int supprimerPosologie(String idMedoc, String idIndi, String idDos) {
        String req = "DELETE FROM PRESCRIRE WHERE MED_DEPOTLEGAL_MEDICAMENT = '" +idMedoc+"' AND TIN_CODE_TYPE_INDIVIDU = '"+ idIndi+ "' AND DOS_CODE_DOSAGE = '"+idDos +"';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESCRIRE KO");
        }
        return res;
    }
     
     
     
     
     //methode afin de convertire les données récupérées de la table
     
     CPosologie convertir_RS_Posologie(ResultSet rs) {
        try {
            
           CTableTypeIndividu tableIndi = new CTableTypeIndividu();
           ArrayList<CTypeIndividu> listIndi = tableIndi.lire1TypeIndividu("TIN_CODE_TYPE_INDIVIDU", rs.getString("TIN_CODE_TYPE_INDIVIDU"));
           CTypeIndividu indi = listIndi.get(0);
           
           CTableDosagePeriodique tableDos = new CTableDosagePeriodique();
           ArrayList<CDosagePeriodique> listDos = tableDos.lire1DosagePeriodiques("DOS_CODE_DOSAGE", rs.getString("DOS_CODE_DOSAGE"));
           CDosagePeriodique dos = listDos.get(0);
                   
           int nbrJour = rs.getInt("PRE_POSOLOGIE");
           
           return new CPosologie(indi, dos, nbrJour);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CPosologie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CPosologie> lirePosologies() {
        if (bdd.connecter() == true) {
            ArrayList<CPosologie> listeDePosologies = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `PRESCRIRE`");
            try {
                while (rs.next()) {
                    CPosologie Posologie = convertir_RS_Posologie(rs);
                    listeDePosologies.add(Posologie);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDePosologies;
        } else {
            System.out.println("Connexion manipBdd.PRESCRIRE  KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines Posologies,
     //en selon le depot légal du médicament, l'id du type d'individu et l'id du dosage
     
     public ArrayList<CPosologie> lire1Posologies(String idMedoc) {
        if (bdd.connecter() == true) {
            ArrayList<CPosologie> listePosologie = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `PRESCRIRE` WHERE MED_DEPOTLEGAL_MEDICAMENT = '" +idMedoc+"' ;");
            try {
                
               
                while (rs.next()) {
                    CPosologie Posologie = convertir_RS_Posologie(rs);
                    listePosologie.add(Posologie);
                    
                }
               if(listePosologie.isEmpty()){
                   System.out.println("PRESCRIRE Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listePosologie;
        } else {
            System.out.println("Connexion manipBdd.PRESCRIRE KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Posologies selectionnées.
     
     
     public void printPosologies(ArrayList<CPosologie> liste){
         liste.forEach((liste1) -> {
             System.out.println("Nombre Jour : " +liste1.getNbrJourPosologie() + ". Dosage Unite : "+ liste1.getDosagePeriodique().getUniteDosage() +". Type Indi : "+ liste1.getTypeIndividu().getLibelleTypeIndividu()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTablePosologie tablePosologie = new CTablePosologie();
//         CTypeIndividu indi = new CTypeIndividu(3, "enfant maigre", 0);
//         CDosagePeriodique dos = new CDosagePeriodique(2, 13, "ml");
//         int medoc = 1;
//         CPosologie posologie = new CPosologie(indi, dos, 14);
//         
         //tablePosologie.insererPosologie(posologie, medoc);
         //tablePosologie.supprimerPosologie("V001");
         //tablePosologie.modifierPosologie(Posologie,visiteur);
         //tablePosologie.printPosologies(tablePosologie.lire1Posologies("VIS_MATRICULE_VISITEUR", "V001"));
     }
     
}
