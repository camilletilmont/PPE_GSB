/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CFormeMedicament;
import Donnees.CMedicament;
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
public class CTableMedicamentFormuler {
     //attributs
    
    
    protected CBDD bdd;

    
    
    //getter et setter
    

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    


    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableMedicamentFormuler(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE FORMULER (MED_DEPOTLEGAL_MEDICAMENT INT  AUTO_INCREMENT NOT NULL,"
                + " PRE_CODE_PRESENTATION TINYINT NOT NULL,"
                + " PRIMARY KEY (MED_DEPOTLEGAL_MEDICAMENT,"
                + " PRE_CODE_PRESENTATION) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FORMULER KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`FORMULER`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FORMULER KO");
        }
        return res;
    }
     
     //insértion d'une liaison medicament - formulation
     
     public int insererMedicamentFormuler(CMedicament medicament, CFormeMedicament formMedoc) {
        String req = "INSERT INTO `FORMULER` (`MED_DEPOTLEGAL_MEDICAMENT`, `PRE_CODE_PRESENTATION`) "
                + "VALUES ('"+medicament.getDepotLegal()+"',"
                + " '"+ formMedoc.getIdFormeMedic() +"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FORMULER KO");
        }
        return res;
    }
     
     
 //modification d'une liaison medicament - formulation
     
     public int modifierMedicamentFormuler(CMedicament medicament, CFormeMedicament formMedoc) {

        String req = "UPDATE FORMULER SET "
                + "MED_DEPOTLEGAL_MEDICAMENT = '" + medicament.getDepotLegal() + "', "
                + "PRE_CODE_PRESENTATION = '"+ formMedoc.getIdFormeMedic() + "' "
                + " WHERE MED_DEPOTLEGAL_MEDICAMENT = '" +medicament.getDepotLegal()+ "' AND PRE_CODE_PRESENTATION = '"+ formMedoc.getIdFormeMedic()+"';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FORMULER KO");
        }
        return res;
    }
     
     
     //suppression d'une liaison medicament - formulation
     
     public int supprimerMedicamentFormuler(String depotMedoc, String idForm) {
        String req = "DELETE FROM FORMULER WHERE MED_DEPOTLEGAL_MEDICAMENT = '" + depotMedoc + "' AND PRE_CODE_PRESENTATION = '"+ idForm+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FORMULER KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées d'une des colones de la table
     
     public String convertir_RS_MedicamentFormuler(ResultSet rs) {
        try {
                   String code = rs.getString(1);
                   
            
                    return code;
      
           
        } catch (SQLException ex) {
            Logger.getLogger(String.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<String> lireMedicamentFormulers() {
        if (bdd.connecter() == true) {
            ArrayList<String> listeDeMedicamentFormulers = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `FORMULER`");
            try {
                while (rs.next()) {
                    String medicamentFormuler = convertir_RS_MedicamentFormuler(rs);
                    listeDeMedicamentFormulers.add(medicamentFormuler);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeMedicamentFormulers;
        } else {
            System.out.println("Connexion manipBdd.FORMULER KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines liaisons,
     //en fonction du numéro d'activitée.
     
     public ArrayList<String> lire1IdMedocFormuler(String numForm) {
        if (bdd.connecter() == true) {
            ArrayList<String> listeIdMedocFormuler = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT MED_DEPOTLEGAL_MEDICAMENT FROM `FORMULER` WHERE PRE_CODE_PRESENTATION = '" + numForm + "';");
            try {
                
               
                while (rs.next()) {
                    String medicamentFormuler = convertir_RS_MedicamentFormuler(rs);
                    listeIdMedocFormuler.add(medicamentFormuler);
                    
                }
               if(listeIdMedocFormuler.isEmpty()){
                   System.out.println("Liaison FORMULER Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeIdMedocFormuler;
        } else {
            System.out.println("Connexion manipBdd.FORMULER KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines liaisons,
     //en fonction du numéro de visiteur
     
     public ArrayList<String> lire1IdFormulationFormuler(String depotId) {
        if (bdd.connecter() == true) {
            ArrayList<String> listeIdFormulationFormuler = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT PRE_CODE_PRESENTATION FROM `FORMULER` WHERE MED_DEPOTLEGAL_MEDICAMENT = '" + depotId + "';");
            try {
                
               
                while (rs.next()) {
                    String medicamentFormuler = convertir_RS_MedicamentFormuler(rs);
                    listeIdFormulationFormuler.add(medicamentFormuler);
                    
                }
               if(listeIdFormulationFormuler.isEmpty()){
                   System.out.println("Liaison FORMULER Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeIdFormulationFormuler;
        } else {
            System.out.println("Connexion manipBdd.FORMULER KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de clients selectionnée.
     
     
     public void printMedicamentFormulers(ArrayList<String> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1 + ".");
        });
     
     }
     
     public static void main(String[] args) {
     
 
     }
}
