/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CFamille;
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
public class CTableFamille {
     //attributs
    
     protected ArrayList<CFamille> listeFamilles;
    protected CBDD bdd;

    
    
    //getter et setter
    public ArrayList<CFamille> getListeFamilles() {
        return listeFamilles;
    }

    public void setListeFamilles(ArrayList<CFamille> listeFamilles) {
        this.listeFamilles = listeFamilles;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    


    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableFamille(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE FAMILLE (FAM_CODE_FAMILLE SMALLINT  AUTO_INCREMENT NOT NULL,"
                + " FAM_LIBELLE_FAMILLE VARCHAR(100),"
                + " PRIMARY KEY (FAM_CODE_FAMILLE) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FAMILLE KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`FAMILLE`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FAMILLE KO");
        }
        return res;
    }
     
     //insértion d'une famille de medicament
     
     public int insererFamille(CFamille famille) {
        String req = "INSERT INTO `FAMILLE` (`FAM_LIBELLE_FAMILLE`) "
                + "VALUES ('"+ famille.getNomFamille()+"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FAMILLE KO");
        }
        return res;
    }
     
     
//     //modification d'une famille en fonction de son Id
     
     public int modifierFamille(CFamille famille) {

        String req = "UPDATE FAMILLE SET "
                + "FAM_CODE_FAMILLE = '" + famille.getIdFamille() + "', "
                + "FAM_LIBELLE_FAMILLE = '"+ famille.getNomFamille() + "' "
                + " WHERE FAM_CODE_FAMILLE = '"+ famille.getIdFamille() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FAMILLE KO");
        }
        return res;
    }
     
     
     //suppression d'une famille en fonction de son Id
     
     public int supprimerFamille(String x) {
        String req = "DELETE FROM FAMILLE WHERE FAM_CODE_FAMILLE = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FAMILLE KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CFamille convertir_RS_Famille(ResultSet rs) {
        try {
                
           return new CFamille(rs.getInt("FAM_CODE_FAMILLE"),rs.getString("FAM_LIBELLE_FAMILLE"));
           
        } catch (SQLException ex) {
            Logger.getLogger(CFamille.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CFamille> lireFamilles() {
        if (bdd.connecter() == true) {
            ArrayList<CFamille> listeDeFamilles = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `FAMILLE`");
            try {
                while (rs.next()) {
                    CFamille Famille = convertir_RS_Famille(rs);
                    listeDeFamilles.add(Famille);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeFamilles;
        } else {
            System.out.println("Connexion manipBdd.FAMILLE KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines familles,
     //en fonction l'information souhaitée.
     
     public ArrayList<CFamille> lire1Famille(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CFamille> listeFamille = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `FAMILLE` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CFamille Famille = convertir_RS_Famille(rs);
                    listeFamille.add(Famille);
                    
                }
               if(listeFamille.isEmpty()){
                   System.out.println("FAMILLE Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeFamille;
        } else {
            System.out.println("Connexion manipBdd.FAMILLE KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de familles selectionnées.
     
     
     public void printFamilles(ArrayList<CFamille> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1.getIdFamille() + ". Nom Famille : "+liste1.getNomFamille()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableFamille tableFamille = new CTableFamille();
//         CFamille famille = new CFamille(3, "Anti-inflammatoire");
//      
       //tableFamille.insererFamille(famille);
       //tableFamille.supprimerFamille("2");
       //tableFamille.modifierFamille(famille);
       //tableFamille.printFamilles(tableFamille.lire1Famille("FAM_CODE_FAMILLE", "3"));
       //tableFamille.printFamilles(tableFamille.lireFamilles());
         
     }
}
