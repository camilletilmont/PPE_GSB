/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
public class CTableInteractionMedicament {
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
    
    public CTableInteractionMedicament(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE INTERAGIR (MED_DEPOTLEGAL_MEDICAMENT INT  AUTO_INCREMENT NOT NULL,"
                + " MED_DEPOTLEGAL_MEDICAMENT_INTERAGIR INT,"
                + " PRIMARY KEY (MED_DEPOTLEGAL_MEDICAMENT,"
                + " MED_DEPOTLEGAL_MEDICAMENT_INTERAGIR) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INTERAGIR KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`INTERAGIR`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INTERAGIR KO");
        }
        return res;
    }
     
     //insértion d'une liaison interaction
     
     public int insererInteractionMedicament(CMedicament medoc, CMedicament medocInter) {
        String req = "INSERT INTO `INTERAGIR` (`MED_DEPOTLEGAL_MEDICAMENT`, `MED_DEPOTLEGAL_MEDICAMENT_INTERAGIR`) "
                + "VALUES ('"+medoc.getDepotLegal()+"',"
                + " '"+ medocInter.getDepotLegal() +"');" ;
        
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
     
     
 //modification d'une liaison interaction
     
     public int modifierInteractionMedicament(CMedicament medoc, CMedicament medocInter) {
        String req = "UPDATE INTERAGIR SET "
                + "MED_DEPOTLEGAL_MEDICAMENT = '" + medoc.getDepotLegal() + "', "
                + "MED_DEPOTLEGAL_MEDICAMENT_INTERAGIR = '"+ medocInter.getDepotLegal() + "' "
                + " WHERE MED_DEPOTLEGAL_MEDICAMENT = '" +medoc.getDepotLegal()+ "' AND MED_DEPOTLEGAL_MEDICAMENT_INTERAGIR = '"+ medocInter.getDepotLegal()+"';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INTERAGIR KO");
        }
        return res;
    }
     
     
     //suppression d'une liaison interaction
     
     public int supprimerInteractionMedicament(String medoc, String medocInter) {
        String req = "DELETE FROM INTERAGIR WHERE MED_DEPOTLEGAL_MEDICAMENT = '" + medoc + "' AND MED_DEPOTLEGAL_MEDICAMENT_INTERAGIR = '"+ medocInter+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INTERAGIR KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées d'une des colones de la table
     
     public String convertir_RS_InteractionMedicament(ResultSet rs) {
        try {
                   String code = rs.getString(1);
                   
            
                    return code;
      
           
        } catch (SQLException ex) {
            Logger.getLogger(String.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<String> lireInteractionMedicaments() {
        if (bdd.connecter() == true) {
            ArrayList<String> listeDeInteractionMedicaments = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `INTERAGIR`");
            try {
                while (rs.next()) {
                    String interactionMedicament = convertir_RS_InteractionMedicament(rs);
                    listeDeInteractionMedicaments.add(interactionMedicament);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeInteractionMedicaments;
        } else {
            System.out.println("Connexion manipBdd.INTERAGIR KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines liaisons,
     //en fonction du numéro du medicament principal
     
     public ArrayList<String> lire1IdMedocInteraction(String numMedoc) {
        if (bdd.connecter() == true) {
            ArrayList<String> listeIdMedocInter = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT MED_DEPOTLEGAL_MEDICAMENT_INTERAGIR FROM `INTERAGIR` WHERE MED_DEPOTLEGAL_MEDICAMENT = '" + numMedoc + "';");
            ResultSet rs2 = bdd.executerRequeteQuery("SELECT MED_DEPOTLEGAL_MEDICAMENT FROM `INTERAGIR` WHERE MED_DEPOTLEGAL_MEDICAMENT_INTERAGIR = '" + numMedoc + "';");
            try {
                
               
                while (rs.next()) {
                    String InteractionMedicament1 = convertir_RS_InteractionMedicament(rs);
                    
                    listeIdMedocInter.add(InteractionMedicament1);
                    
                }
                while(rs2.next()){
                String InteractionMedicament2 = convertir_RS_InteractionMedicament(rs2);
                if(!listeIdMedocInter.contains(InteractionMedicament2)){
                listeIdMedocInter.add(InteractionMedicament2);
                }
         
                }
               if(listeIdMedocInter.isEmpty()){
                   System.out.println("Liaison INTERAGIR Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeIdMedocInter;
        } else {
            System.out.println("Connexion manipBdd.INTERAGIR KO");
        }
        return null;
    }
     
    
     //methode permettant d'afficher une liste de Medicaments selectionnés en interaction avec notre medicament principal.
     
     
     public void printInteractionMedicaments(ArrayList<String> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1 + ".");
        });
     
     }
     
     public static void main(String[] args) {
     

     }
}
