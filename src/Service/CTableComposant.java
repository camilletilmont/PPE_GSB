/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CComposant;
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
public class CTableComposant {
    //attributs
    
     protected ArrayList<CComposant> listeComposants;
    protected CBDD bdd;

    
    
    //getter et setter
    public ArrayList<CComposant> getListeComposants() {
        return listeComposants;
    }

    public void setListeComposants(ArrayList<CComposant> listeComposants) {
        this.listeComposants = listeComposants;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    


    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableComposant(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE COMPOSANT (CMP_CODE_COMPOSANT SMALLINT  AUTO_INCREMENT NOT NULL,"
                + " CMP_LIBELLE_COMPOSANT VARCHAR(100),"
                + " PRIMARY KEY (CMP_CODE_COMPOSANT) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.COMPOSANT KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`COMPOSANT`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.COMPOSANT KO");
        }
        return res;
    }
     
     //insértion d'un composant
     
     public int insererComposant(CComposant composant) {
        String req = "INSERT INTO `COMPOSANT` (`CMP_CODE_COMPOSANT`,`CMP_LIBELLE_COMPOSANT`) "
                + "VALUES ('"+ composant.getIdComposant()+"','"+ composant.getNomComposant()+"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.COMPOSANT KO");
        }
        return res;
    }
     
     
  //modification d'un composant en fonction de son Id
     
     public int modifierComposant(CComposant somposant) {

        String req = "UPDATE COMPOSANT SET "
                + "CMP_CODE_COMPOSANT = '" + somposant.getIdComposant() + "', "
                + "CMP_LIBELLE_COMPOSANT = '"+ somposant.getNomComposant() + "' "
                + " WHERE CMP_CODE_COMPOSANT = '"+ somposant.getIdComposant() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.COMPOSANT KO");
        }
        return res;
    }
     
     
     //suppression d'un composant en fonction de son Id
     
     public int supprimerComposant(String x) {
        String req = "DELETE FROM COMPOSANT WHERE CMP_CODE_COMPOSANT = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.COMPOSANT KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CComposant convertir_RS_Composant(ResultSet rs) {
        try {
                
           return new CComposant(rs.getInt("CMP_CODE_COMPOSANT"),rs.getString("CMP_LIBELLE_COMPOSANT"));
           
        } catch (SQLException ex) {
            Logger.getLogger(CComposant.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CComposant> lireComposants() {
        if (bdd.connecter() == true) {
            ArrayList<CComposant> listeDeComposants = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `COMPOSANT`");
            try {
                while (rs.next()) {
                    CComposant Composant = convertir_RS_Composant(rs);
                    listeDeComposants.add(Composant);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeComposants;
        } else {
            System.out.println("Connexion manipBdd.COMPOSANT KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains composants,
     //en fonction l'information souhaitée.
     
     public ArrayList<CComposant> lire1Composant(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CComposant> listeComposant = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `COMPOSANT` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CComposant Composant = convertir_RS_Composant(rs);
                    listeComposant.add(Composant);
                    
                }
               if(listeComposant.isEmpty()){
                   System.out.println("COMPOSANT Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeComposant;
        } else {
            System.out.println("Connexion manipBdd.COMPOSANT KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de composants selectionnés.
     
     
     public void printComposants(ArrayList<CComposant> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1.getIdComposant() + ". Nom Composant : "+liste1.getNomComposant()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableComposant tableComposant = new CTableComposant();
//         CComposant composant = new CComposant(31, "Borium");
//      
       //tableComposant.insererComposant(composant);
       //tableComposant.supprimerComposant("31");
       //tableComposant.modifierComposant(composant);
       //tableComposant.printComposants(tableComposant.lire1Composant("CMP_LIBELLE_COMPOSANT", "Uranium 223"));
      // tableComposant.printComposants(tableComposant.lireComposants());
         
     }
}
