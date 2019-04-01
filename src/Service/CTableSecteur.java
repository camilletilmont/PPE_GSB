/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


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
public class CTableSecteur {
      //attributs
    
     protected ArrayList<CSecteur> listeSecteurs;
    protected CBDD bdd;

    
    
    //getter et setter
    public ArrayList<CSecteur> getListeSecteurs() {
        return listeSecteurs;
    }

    public void setListeSecteurs(ArrayList<CSecteur> listeSecteurs) {
        this.listeSecteurs = listeSecteurs;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    


    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableSecteur(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE IF NOT EXISTS SECTEUR (SEC_CODE_SECTEUR TINYINT(2)"
                + " AUTO_INCREMENT NOT NULL,"
                + " SEC_LIBELLE VARCHAR(15),"
                + " PRIMARY KEY (SEC_CODE_SECTEUR) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SECTEUR KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`SECTEUR`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SECTEUR KO");
        }
        return res;
    }
     
     //insértion d'un Secteur
     
     public int insererSecteur(CSecteur secteur) {
        String req = "INSERT INTO `SECTEUR` (`SEC_LIBELLE`) "
                + "VALUES ('"+ secteur.getNomSecteur()+"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SECTEUR KO");
        }
        return res;
    }
     
     
         //modification d'un Secteur selon son Id
     
     public int modifierSecteur(CSecteur secteur) {

        String req = "UPDATE SECTEUR SET "
                + "SEC_CODE_SECTEUR = '" + secteur.getIdSecteur() + "', "
                + "SEC_LIBELLE = '"+ secteur.getNomSecteur() + "' "
                + " WHERE SEC_CODE_SECTEUR = '"+ secteur.getIdSecteur() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SECTEUR KO");
        }
        return res;
    }
     
     
     //suppression d'un Secteur selon son Id
     
     public int supprimerSecteur(String x) {
        String req = "DELETE FROM SECTEUR WHERE SEC_CODE_SECTEUR = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.SECTEUR KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CSecteur convertir_RS_Secteur(ResultSet rs) {
        try {
                
           return new CSecteur(rs.getInt("SEC_CODE_SECTEUR"),rs.getString("SEC_LIBELLE"));
           
        } catch (SQLException ex) {
            Logger.getLogger(CSecteur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CSecteur> lireSecteurs() {
        if (bdd.connecter() == true) {
            ArrayList<CSecteur> listeDeSecteurs = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `SECTEUR`");
            try {
                while (rs.next()) {
                    CSecteur secteur = convertir_RS_Secteur(rs);
                    listeDeSecteurs.add(secteur);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeSecteurs;
        } else {
            System.out.println("Connexion manipBdd.SECTEUR KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains secteurs,
     //en fonction l'information souhaitée.
     
     public ArrayList<CSecteur> lire1Secteur(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CSecteur> listeSecteur = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `SECTEUR` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CSecteur secteur = convertir_RS_Secteur(rs);
                    listeSecteur.add(secteur);
                    
                }
               if(listeSecteur.isEmpty()){
                   System.out.println("SECTEUR Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeSecteur;
        } else {
            System.out.println("Connexion manipBdd.SECTEUR KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Secteurs selectionnés.
     
     
     public void printSecteurs(ArrayList<CSecteur> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1.getIdSecteur() + ". Nom Secteur : "+liste1.getNomSecteur()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableSecteur tableSecteur = new CTableSecteur();
//         CSecteur secteur = new CSecteur(1, "OUEST");
//      
       //tableSecteur.insererSecteur(secteur);
       //tableSecteur.supprimerSecteur("2");
       //tableSecteur.modifierSecteur(secteur);
       //tableSecteur.printSecteurs(tableSecteur.lire1Secteur("SEC_LIBELLE", "OUEST"));
       //tableSecteur.printSecteurs(tableSecteur.lireSecteurs());
         
     }
}
