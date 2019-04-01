/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CDosagePeriodique;
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
public class CTableDosagePeriodique {
    //attributs.
    
     protected ArrayList<CDosagePeriodique> listeDosagePeriodiques;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CDosagePeriodique> getListeDosagePeriodiques() {
        return listeDosagePeriodiques;
    }

    public void setListeDosagePeriodiques(ArrayList<CDosagePeriodique> listeDosagePeriodiques) {
        this.listeDosagePeriodiques = listeDosagePeriodiques;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableDosagePeriodique(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE DOSAGE (DOS_CODE_DOSAGE TINYINT  AUTO_INCREMENT NOT NULL,"
                + " DOS_QUANTITE_DOSAGE TINYINT,"
                + " DOS_UNITE_DOSAGE TINYINT,"
                + " PRIMARY KEY (DOS_CODE_DOSAGE) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.DOSAGE KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`DOSAGE`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.DOSAGE KO");
        }
        return res;
    }
     
     //insértion d'un DosagePeriodique
     
     public int insererDosagePeriodique(CDosagePeriodique dosagePeriodique) {
        String req = "INSERT INTO `DOSAGE` (`DOS_QUANTITE_DOSAGE`, `DOS_UNITE_DOSAGE`)"
                + " VALUES ('"+dosagePeriodique.getQuantiteDosage()+"',"
                + " '"+dosagePeriodique .getUniteDosage()+"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }
     
     
//     //modification d'un dosage selon son Id
     
     public int modifierDosagePeriodique(CDosagePeriodique dosagePeriodique) {

        String req = "UPDATE DOSAGE SET "
                + "DOS_CODE_DOSAGE = '" + dosagePeriodique.getIdDosage() + "', "
                + "DOS_QUANTITE_DOSAGE = '"+ dosagePeriodique.getQuantiteDosage() + "', "
                + "DOS_UNITE_DOSAGE = '"+ dosagePeriodique .getUniteDosage() + "' "
                + " WHERE DOS_CODE_DOSAGE = '"+ dosagePeriodique.getIdDosage() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.DOSAGE KO");
        }
        return res;
    }
     
     
     //suppression d'un dosage selon son Id
     
     public int supprimerDosagePeriodique(String x) {
        String req = "DELETE FROM DOSAGE WHERE DOS_CODE_DOSAGE = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.DOSAGE KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CDosagePeriodique convertir_RS_DosagePeriodique(ResultSet rs) {
        try {
            
            int id = rs.getInt("DOS_CODE_DOSAGE");
            int qte = rs.getInt("DOS_QUANTITE_DOSAGE");
            String unite = rs.getString("DOS_UNITE_DOSAGE");
            
                    return new CDosagePeriodique(id,qte,unite);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CDosagePeriodique.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CDosagePeriodique> lireDosagePeriodiques() {
        if (bdd.connecter() == true) {
            ArrayList<CDosagePeriodique> listeDeDosagePeriodiques = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `DOSAGE`");
            try {
                while (rs.next()) {
                    CDosagePeriodique DosagePeriodique = convertir_RS_DosagePeriodique(rs);
                    listeDeDosagePeriodiques.add(DosagePeriodique);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeDosagePeriodiques;
        } else {
            System.out.println("Connexion manipBdd.DOSAGE KO");
        }
        return null;
    }
     
     //methode permettant de lire cezrtaines entrées de la table, certains dosages,
     //en fonction l'information souhaitée.
     
     public ArrayList<CDosagePeriodique> lire1DosagePeriodiques(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CDosagePeriodique> listeDosagePeriodique = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `DOSAGE` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CDosagePeriodique DosagePeriodique = convertir_RS_DosagePeriodique(rs);
                    listeDosagePeriodique.add(DosagePeriodique);
                    
                }
               if(listeDosagePeriodique.isEmpty()){
                   System.out.println("Dosage Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeDosagePeriodique;
        } else {
            System.out.println("Connexion manipBdd.DOSAGE KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de dosages selectionnés.
     
     
     public void printDosagePeriodiques(ArrayList<CDosagePeriodique> liste){
         liste.forEach((liste1) -> {
             System.out.println("Code : " +liste1.getIdDosage() + ". Qte : "+liste1.getQuantiteDosage() +". Unite : " + liste1.getUniteDosage()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableDosagePeriodique tableDosagePeriodique = new CTableDosagePeriodique();
//         
//         
//         CDosagePeriodique dosagePeriodique = new CDosagePeriodique(2,13,"ml");
         //tableDosagePeriodique.insererDosagePeriodique(dosagePeriodique);
         //tableDosagePeriodique.supprimerDosagePeriodique("1");
         //tableDosagePeriodique.modifierDosagePeriodique(dosagePeriodique);
         //tableDosagePeriodique.printDosagePeriodiques(tableDosagePeriodique.lire1DosagePeriodiques("DOS_UNITE_DOSAGE", "ml"));
         //tableDosagePeriodique.printDosagePeriodiques(tableDosagePeriodique.lireDosagePeriodiques());
     }
}
