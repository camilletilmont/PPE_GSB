/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CRegion;
import Donnees.CSecteur;
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
public class CTableRegion {
    //attributs
    
     protected ArrayList<CRegion> listeRegions;
    protected CBDD bdd;

    
    
    //getter et setter
    public ArrayList<CRegion> getListeRegions() {
        return listeRegions;
    }

    public void setListeRegions(ArrayList<CRegion> listeRegions) {
        this.listeRegions = listeRegions;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    


    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableRegion(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE IF NOT EXISTS REGION (REG_CODE_REGION TINYINT(2)  AUTO_INCREMENT NOT NULL,"
                + " REG_NOM_REGION VARCHAR(40),"
                + " SEC_CODE_SECTEUR TINYINT(2) NOT NULL,"
                + " PRIMARY KEY (REG_CODE_REGION) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REGION KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`REGION`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REGION KO");
        }
        return res;
    }
     
     //insértion d'une Region
     
     public int insererRegion(CRegion region, CSecteur secteur) {
        String req = "INSERT INTO `REGION` (`REG_NOM_REGION`, `SEC_CODE_SECTEUR`) "
                + "VALUES ('"+region.getNomRegion()+"',"
                + " '"+ secteur.getIdSecteur() +"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REGION KO");
        }
        return res;
    }
     
     
//     //modification d'une Region selon son Id
     
     public int modifierRegion(CRegion region,CSecteur secteur) {

        String req = "UPDATE REGION SET "
                + "REG_CODE_REGION = '" + region.getIdRegion() + "', "
                + "REG_NOM_REGION = '"+ region.getNomRegion() + "', "
                + "SEC_CODE_SECTEUR = '"+ secteur.getIdSecteur() +"' "
                + " WHERE REG_CODE_REGION = '"+ region.getIdRegion() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REGION KO");
        }
        return res;
    }
     
     
     //suppression d'une Region selon son Id
     
     public int supprimerRegion(String x) {
        String req = "DELETE FROM REGION WHERE REG_CODE_REGION = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REGION KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     public CRegion convertir_RS_Region(ResultSet rs) {
        try {
                   int code = rs.getInt("REG_CODE_REGION");
                   String nomRegion = rs.getString("REG_NOM_REGION");
            
                    return new CRegion(code,nomRegion);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CRegion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CRegion> lireRegions() {
        if (bdd.connecter() == true) {
            ArrayList<CRegion> listeDeRegions = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `REGION`");
            try {
                while (rs.next()) {
                    CRegion region = convertir_RS_Region(rs);
                    listeDeRegions.add(region);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeRegions;
        } else {
            System.out.println("Connexion manipBdd.REGION KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines Regions,
     //en fonction l'information souhaitée.
     
     public ArrayList<CRegion> lire1Region(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CRegion> listeRegion = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `REGION` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CRegion region = convertir_RS_Region(rs);
                    listeRegion.add(region);
                    
                }
               if(listeRegion.isEmpty()){
                   System.out.println("REGION Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeRegion;
        } else {
            System.out.println("Connexion manipBdd.REGION KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Regions selectionnées.
     
     
     public void printRegions(ArrayList<CRegion> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1.getIdRegion() + ". Nom Region : "+liste1.getNomRegion()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableRegion tableRegion = new CTableRegion();
//         CSecteur secteur = new CSecteur(1, "EST");
//         
//         CRegion region = new CRegion(4, "Bourgogne");
//         
//         tableRegion.insererRegion(region, secteur);
//         tableRegion.supprimerRegion("3");
//         tableRegion.modifierRegion(region, secteur);
//         tableRegion.printRegions(tableRegion.lire1Region("REG_NOM_REGION", "Bretagne"));
//         tableRegion.printRegions(tableRegion.lireRegions());
         
     }
    
}
