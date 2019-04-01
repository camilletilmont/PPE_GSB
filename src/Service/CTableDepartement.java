

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CDepartement;
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
public class CTableDepartement {
    
      //attributs.
    
     protected ArrayList<CDepartement> listeDepartements;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CDepartement> getListeDepartements() {
        return listeDepartements;
    }

    public void setListeDepartements(ArrayList<CDepartement> listeDepartements) {
        this.listeDepartements = listeDepartements;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableDepartement(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE DEPARTEMENT (DEP_CODE_DEPARTEMENT TINYINT  AUTO_INCREMENT NOT NULL,"
                + " DEP_NOM_DEPARTEMENT VARCHAR(50),"
                + " DEP_CHEFVENTE_DEPARTEMENT BOOLEAN,"
                + " PRIMARY KEY (DEP_CODE_DEPARTEMENT) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.DEPARTEMENT KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`DEPARTEMENT`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.DEPARTEMENT KO");
        }
        return res;
    }
     
     //insértion d'un Departement
     
     public int insererDepartement(CDepartement departement) {
        String req = "INSERT INTO `Departement` (`DEP_NOM_DEPARTEMENT`, `DEP_CHEFVENTE_DEPARTEMENT`)"
                + " VALUES ('"+departement.getNomDep()+"',"
                + " '"+(departement.isChefVente() ? 1 : 0) +"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.DEPARTEMENT KO");
        }
        return res;
    }
     
     
//     //modification d'un departement selon son Id
     
     public int modifierDepartement(CDepartement departement) {

        String req = "UPDATE Departement SET "
                + "DEP_CODE_DEPARTEMENT = '" + departement.getIdDepartement() + "', "
                + "DEP_NOM_DEPARTEMENT = '"+ departement.getNomDep() + "', "
                + "DEP_CHEFVENTE_DEPARTEMENT = '"+ (departement.isChefVente() ? 1 : 0) + "' "
                + " WHERE DEP_CODE_DEPARTEMENT = '"+ departement.getIdDepartement() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.DEPARTEMENT KO");
        }
        return res;
    }
     
     
     //suppression d'un departement selon son Id
     
     public int supprimerDepartement(String x) {
        String req = "DELETE FROM DEPARTEMENT WHERE DEP_CODE_DEPARTEMENT = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.DEPARTEMENT KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CDepartement convertir_RS_Departement(ResultSet rs) {
        try {
            
            int id = rs.getInt("DEP_CODE_DEPARTEMENT");
            String nom = rs.getString("DEP_NOM_DEPARTEMENT");
            boolean chef = rs.getBoolean("DEP_CHEFVENTE_DEPARTEMENT");
            
                    return new CDepartement(id,nom,chef);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CDepartement.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CDepartement> lireDepartements() {
        if (bdd.connecter() == true) {
            ArrayList<CDepartement> listeDeDepartements = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `DEPARTEMENT`");
            try {
                while (rs.next()) {
                    CDepartement departement = convertir_RS_Departement(rs);
                    listeDeDepartements.add(departement);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeDepartements;
        } else {
            System.out.println("Connexion manipBdd.DEPARTEMENT KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains départements,
     //en fonction l'information souhaitée.
     
     public ArrayList<CDepartement> lire1Departements(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CDepartement> listeDepartement = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `DEPARTEMENT` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CDepartement departement = convertir_RS_Departement(rs);
                    listeDepartement.add(departement);
                    
                }
               if(listeDepartement.isEmpty()){
                   System.out.println("Departement Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeDepartement;
        } else {
            System.out.println("Connexion manipBdd.DEPARTEMENT KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de departements selectionnés.
     
     
     public void printDepartements(ArrayList<CDepartement> liste){
         liste.forEach((liste1) -> {
             System.out.println("Code : " +liste1.getIdDepartement() + ". Nom: "+liste1.getNomDep() +". Chef  : " + liste1.isChefVente()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableDepartement tableDepartement = new CTableDepartement();
//         
//         
//         CDepartement departement = new CDepartement(3,"Bi",true);
         //tableDepartement.insererDepartement(departement);
         //tableDepartement.supprimerDepartement("1");
         //tableDepartement.modifierDepartement(departement);
         //tableDepartement.printDepartements(tableDepartement.lire1Departements("DEP_CODE_DEPARTEMENT", "3"));
         //tableDepartement.printDepartements(tableDepartement.lireDepartements());
     }
    
}
