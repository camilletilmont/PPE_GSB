/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CTypeFrais;
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
public class CTableTypeFrais {
    //attributs.
    
     protected ArrayList<CTypeFrais> listeTypeFrais;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CTypeFrais> getListeTypeFrais() {
        return listeTypeFrais;
    }

    public void setListeTypeFrais(ArrayList<CTypeFrais> listeTypeFrais) {
        this.listeTypeFrais = listeTypeFrais;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableTypeFrais(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE TYPE_FRAIS (TF_CODE_TYPE_FRAIS TINYINT AUTO_INCREMENT NOT NULL,"
                + " TF_LIBELLE_TYPE_FRAIS VARCHAR(30),"
                + " TF_FORFAIT_TYPE_FRAIS FLOAT,"
                + " PRIMARY KEY (TF_CODE_TYPE_FRAIS) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_FRAIS KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`TYPE_FRAIS`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_FRAIS KO");
        }
        return res;
    }
     
     //insértion d'un Type de Frais
     
     public int insererTypeFrais(CTypeFrais typeFrais) {
        String req = "INSERT INTO `TYPE_FRAIS` (`TF_LIBELLE_TYPE_FRAIS`, `TF_FORFAIT_TYPE_FRAIS`)"
                + " VALUES ('"+typeFrais.getNom()+"',"
                + " '"+typeFrais.getForfait() +"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_FRAIS KO");
        }
        return res;
    }
     
     
       //modification d'un Type de Frais selon son Id
     
     public int modifierTypeFrais(CTypeFrais typeFrais) {

        String req = "UPDATE TYPE_FRAIS SET "
                + "TF_CODE_TYPE_FRAIS = '" + typeFrais.getCode() + "', "
                + "TF_LIBELLE_TYPE_FRAIS = '"+ typeFrais.getNom() + "', "
                + "TF_FORFAIT_TYPE_FRAIS = '"+ typeFrais.getForfait() + "' "
                + " WHERE TF_CODE_TYPE_FRAIS = '"+ typeFrais.getCode() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_FRAIS KO");
        }
        return res;
    }
     
     
     //suppression d'un Type de Frais selon son Id
     
     public int supprimerTypeFrais(String x) {
        String req = "DELETE FROM TYPE_FRAIS WHERE TF_CODE_TYPE_FRAIS = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_FRAIS KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CTypeFrais convertir_RS_TypeFrais(ResultSet rs) {
        try {
            
            String code = rs.getString("TF_CODE_TYPE_FRAIS");
            String nom = rs.getString("TF_LIBELLE_TYPE_FRAIS");
            int forfait = rs.getInt("TF_FORFAIT_TYPE_FRAIS");
            
                    return new CTypeFrais(code,nom,forfait);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CTypeFrais.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CTypeFrais> lireTypeFrais() {
        if (bdd.connecter() == true) {
            ArrayList<CTypeFrais> listeDeTypeFrais = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `TYPE_FRAIS`");
            try {
                while (rs.next()) {
                    CTypeFrais TypeFrais = convertir_RS_TypeFrais(rs);
                    listeDeTypeFrais.add(TypeFrais);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeTypeFrais;
        } else {
            System.out.println("Connexion manipBdd.TYPE_FRAIS KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains Types de Frais,
     //en fonction l'information souhaitée.
     
     public ArrayList<CTypeFrais> lire1TypeFrais(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CTypeFrais> liste1TypeFrais = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `TYPE_FRAIS` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CTypeFrais TypeFrais = convertir_RS_TypeFrais(rs);
                    liste1TypeFrais.add(TypeFrais);
                    
                }
               if(liste1TypeFrais.isEmpty()){
                   System.out.println("TYPE_FRAIS Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1TypeFrais;
        } else {
            System.out.println("Connexion manipBdd.TYPE_FRAIS KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Types de Frais selectionnés.
     
     
     public void printTypeFrais(ArrayList<CTypeFrais> liste){
         liste.forEach((liste1) -> {
             System.out.println("Code : " +liste1.getCode() + ". Nom: "+liste1.getNom() +". Chef  : " + liste1.getForfait()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableTypeFrais tableTypeFrais = new CTableTypeFrais();
//         
//         
//         CTypeFrais typeFrais = new CTypeFrais("1","haha",20);
         //tableTypeFrais.insererTypeFrais(typeFrais);
         //tableTypeFrais.supprimerTypeFrais("1");
         //tableTypeFrais.modifierTypeFrais(typeFrais);
         //tableTypeFrais.printTypeFrais(tableTypeFrais.lire1TypeFrais("TF_CODE_TYPE_FRAIS", "1"));
         //tableTypeFrais.printTypeFrais(tableTypeFrais.lireTypeFrais());
     }
    
    
}
