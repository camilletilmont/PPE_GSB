/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CSpecialite;
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
public class CTableSpecialite {
    
     //attributs
    
     protected ArrayList<CSpecialite> listeSpecialites;
    protected CBDD bdd;

    
    
    //getter et setter
    public ArrayList<CSpecialite> getListeSpecialites() {
        return listeSpecialites;
    }

    public void setListeSpecialites(ArrayList<CSpecialite> listeSpecialites) {
        this.listeSpecialites = listeSpecialites;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    


    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableSpecialite(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE SPECIALITE (SPE_CODE_SPECIALITE SMALLINT  AUTO_INCREMENT NOT NULL,"
                + " SPE_LIBELLE_SPECIALITE VARCHAR(80),"
                + " PRIMARY KEY (SPE_CODE_SPECIALITE) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SPECIALITE KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`SPECIALITE`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SPECIALITE KO");
        }
        return res;
    }
     
     //insértion d'une Specialité
     
     public int insererSpecialite(CSpecialite specialite) {
        String req = "INSERT INTO `SPECIALITE` (`SPE_LIBELLE_SPECIALITE`) "
                + "VALUES ('"+ specialite.getLibelle()+"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SPECIALITE KO");
        }
        return res;
    }
     
     
       //modification d'une Specialité selon son Id
     
     public int modifierSpecialite(CSpecialite specialite) {

        String req = "UPDATE SPECIALITE SET "
                + "SPE_CODE_SPECIALITE = '" + specialite.getIdSpecialite() + "', "
                + "SPE_LIBELLE_SPECIALITE = '"+ specialite.getLibelle() + "' "
                + " WHERE SPE_CODE_SPECIALITE = '"+ specialite.getIdSpecialite() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SPECIALITE KO");
        }
        return res;
    }
     
     
     //suppression d'une Spécialité selon son Id
     
     public int supprimerSpecialite(String x) {
        String req = "DELETE FROM SPECIALITE WHERE SPE_CODE_SPECIALITE = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SPECIALITE KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CSpecialite convertir_RS_Specialite(ResultSet rs) {
        try {
                
           return new CSpecialite(rs.getInt("SPE_CODE_SPECIALITE"),rs.getString("SPE_LIBELLE_SPECIALITE"));
           
        } catch (SQLException ex) {
            Logger.getLogger(CSpecialite.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CSpecialite> lireSpecialites() {
        if (bdd.connecter() == true) {
            ArrayList<CSpecialite> listeDeSpecialites = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `SPECIALITE`");
            try {
                while (rs.next()) {
                    CSpecialite Specialite = convertir_RS_Specialite(rs);
                    listeDeSpecialites.add(Specialite);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeSpecialites;
        } else {
            System.out.println("Connexion manipBdd.SPECIALITE KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines spécialités,
     //en fonction l'information souhaitée.
     
     public ArrayList<CSpecialite> lire1Specialite(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CSpecialite> listeSpecialite = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `SPECIALITE` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CSpecialite Specialite = convertir_RS_Specialite(rs);
                    listeSpecialite.add(Specialite);
                    
                }
               if(listeSpecialite.isEmpty()){
                   System.out.println("SPECIALITE Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeSpecialite;
        } else {
            System.out.println("Connexion manipBdd.SPECIALITE KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Spécialités selectionnées.
     
     
     public void printSpecialites(ArrayList<CSpecialite> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1.getIdSpecialite() + ". Nom Specialite : "+liste1.getLibelle()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableSpecialite tableSpecialite = new CTableSpecialite();
//         CSpecialite specialite = new CSpecialite(3, "Chomeur");
//      
       //tableSpecialite.insererSpecialite(specialite);
       //tableSpecialite.supprimerSpecialite("2");
       //tableSpecialite.modifierSpecialite(specialite);
       //tableSpecialite.printSpecialites(tableSpecialite.lire1Specialite("SPE_LIBELLE_SPECIALITE", "Chomeur"));
       //tableSpecialite.printSpecialites(tableSpecialite.lireSpecialites());
     }
}
