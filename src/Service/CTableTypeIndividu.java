/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
public class CTableTypeIndividu {
     //attributs
    
     protected ArrayList<CTypeIndividu> listeTypeIndividus;
    protected CBDD bdd;

    
    
    //getter et setter
    public ArrayList<CTypeIndividu> getListeTypeIndividus() {
        return listeTypeIndividus;
    }

    public void setListeTypeIndividus(ArrayList<CTypeIndividu> listeTypeIndividus) {
        this.listeTypeIndividus = listeTypeIndividus;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    


    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableTypeIndividu(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE TYPE_INDIVIDU (TIN_CODE_TYPE_INDIVIDU TINYINT  AUTO_INCREMENT NOT NULL,"
                + " TIN_LIBELLE_TYPE_INDIVIDU VARCHAR(25),"
                + " PRIMARY KEY (TIN_CODE_TYPE_INDIVIDU) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_INDIVIDU KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`TYPE_INDIVIDU`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_INDIVIDU KO");
        }
        return res;
    }
     
     //insértion d'un Type d'Individu
     
     public int insererTypeIndividu(CTypeIndividu typeIndividu) {
        String req = "INSERT INTO `TYPE_INDIVIDU` (`TIN_LIBELLE_TYPE_INDIVIDU`) "
                + "VALUES ('"+ typeIndividu.getLibelleTypeIndividu()+"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_INDIVIDU KO");
        }
        return res;
    }
     
     
//     //modification d'un Type d'Individu selon son Id
     
     public int modifierTypeIndividu(CTypeIndividu typeIndividu) {

        String req = "UPDATE TYPE_INDIVIDU SET "
                + "TIN_CODE_TYPE_INDIVIDU = '" + typeIndividu.getIdTypeIndividu() + "', "
                + "TIN_LIBELLE_TYPE_INDIVIDU = '"+ typeIndividu.getLibelleTypeIndividu() + "' "
                + " WHERE TIN_CODE_TYPE_INDIVIDU = '"+ typeIndividu.getIdTypeIndividu() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_INDIVIDU KO");
        }
        return res;
    }
     
     
     //suppression d'un Type d'Individu selon son Id
     
     public int supprimerTypeIndividu(String x) {
        String req = "DELETE FROM TYPE_INDIVIDU WHERE TIN_CODE_TYPE_INDIVIDU = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_INDIVIDU KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CTypeIndividu convertir_RS_TypeIndividu(ResultSet rs) {
        try {
                
           return new CTypeIndividu(rs.getInt("TIN_CODE_TYPE_INDIVIDU"),rs.getString("TIN_LIBELLE_TYPE_INDIVIDU"),0.0f);
           
        } catch (SQLException ex) {
            Logger.getLogger(CTypeIndividu.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CTypeIndividu> lireTypeIndividus() {
        if (bdd.connecter() == true) {
            ArrayList<CTypeIndividu> listeDeTypeIndividus = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `TYPE_INDIVIDU`");
            try {
                while (rs.next()) {
                    CTypeIndividu TypeIndividu = convertir_RS_TypeIndividu(rs);
                    listeDeTypeIndividus.add(TypeIndividu);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeTypeIndividus;
        } else {
            System.out.println("Connexion manipBdd.TYPE_INDIVIDU KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains Types d'Individu,
     //en fonction l'information souhaitée.
     
     public ArrayList<CTypeIndividu> lire1TypeIndividu(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CTypeIndividu> listeTypeIndividu = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `TYPE_INDIVIDU` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CTypeIndividu TypeIndividu = convertir_RS_TypeIndividu(rs);
                    listeTypeIndividu.add(TypeIndividu);
                    
                }
               if(listeTypeIndividu.isEmpty()){
                   System.out.println("TYPE_INDIVIDU Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeTypeIndividu;
        } else {
            System.out.println("Connexion manipBdd.TYPE_INDIVIDU KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Types d'Individu selectionnés.
     
     
     public void printTypeIndividus(ArrayList<CTypeIndividu> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1.getIdTypeIndividu() + ". Nom TypeIndividu : "+liste1.getLibelleTypeIndividu()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableTypeIndividu tableTypeIndividu = new CTableTypeIndividu();
//         CTypeIndividu typeIndividu = new CTypeIndividu(1, "femme mure", 0.0f);
      
       //tableTypeIndividu.insererTypeIndividu(typeIndividu);
       //tableTypeIndividu.supprimerTypeIndividu("2");
       //tableTypeIndividu.modifierTypeIndividu(typeIndividu);
       //tableTypeIndividu.printTypeIndividus(tableTypeIndividu.lire1TypeIndividu("TIN_CODE_TYPE_INDIVIDU", "1"));
       //tableTypeIndividu.printTypeIndividus(tableTypeIndividu.lireTypeIndividus());
         
     }
}
