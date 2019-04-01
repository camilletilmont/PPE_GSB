/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Donnees.CActiviteCompl;
import Donnees.CVisiteur;
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
public class CTableActiviteRealiser {
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
    
    public CTableActiviteRealiser(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE REALISER (VIS_MATRICULE_VISITEUR CHAR(4) NOT NULL,"
                + " AC_NUM_ACTIVITE_COMPL SMALLINT NOT NULL,"
                + " PRIMARY KEY (VIS_MATRICULE_VISITEUR,"
                + " AC_NUM_ACTIVITE_COMPL) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REALISER KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`REALISER`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REALISER KO");
        }
        return res;
    }
     
     //insértion d'une liaison activitée - visiteur
     
     public int insererActiviteRealiser(CVisiteur visiteur, CActiviteCompl activ) {
        String req = "INSERT INTO `REALISER` (`VIS_MATRICULE_VISITEUR`, `AC_NUM_ACTIVITE_COMPL`) "
                + "VALUES ('"+visiteur.getMatricule()+"',"
                + " '"+ activ.getIdACtCompl() +"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REALISER KO");
        }
        return res;
    }
     
     
//     //modification d'une liaison activitée - visiteur
     
     public int modifierActiviteRealiser(CVisiteur visiteur, CActiviteCompl activ) {

        String req = "UPDATE REALISER SET "
                + "VIS_MATRICULE_VISITEUR = '" + visiteur.getMatricule() + "', "
                + "AC_NUM_ACTIVITE_COMPL = '"+ activ.getIdACtCompl() + "' "
                + " WHERE VIS_MATRICULE_VISITEUR = '" +visiteur.getMatricule()+ "' AND AC_NUM_ACTIVITE_COMPL = '"+ activ.getIdACtCompl()+"';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REALISER KO");
        }
        return res;
    }
     
     
     //suppression d'une liaison activitée - visiteur
     
     public int supprimerActiviteRealiser(String mat, String act) {
        String req = "DELETE FROM REALISER WHERE VIS_MATRICULE_VISITEUR = '" + mat + "' AND AC_NUM_ACTIVITE_COMPL = '"+ act+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.REALISER KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées d'une des colones de la table
     
     public String convertir_RS_ActiviteRealiser(ResultSet rs) {
        try {
                   String code = rs.getString(1);
                   
            
                    return code;
      
           
        } catch (SQLException ex) {
            Logger.getLogger(String.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<String> lireActiviteRealisers() {
        if (bdd.connecter() == true) {
            ArrayList<String> listeDeActiviteRealisers = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `REALISER`");
            try {
                while (rs.next()) {
                    String ActiviteRealiser = convertir_RS_ActiviteRealiser(rs);
                    listeDeActiviteRealisers.add(ActiviteRealiser);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeActiviteRealisers;
        } else {
            System.out.println("Connexion manipBdd.REALISER KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines liaisons,
     //en fonction du numéro d'activitée.
     
     public ArrayList<String> lire1MatriculeRealiser(String numAct) {
        if (bdd.connecter() == true) {
            ArrayList<String> listeActiviteRealiser = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT VIS_MATRICULE_VISITEUR FROM `REALISER` WHERE AC_NUM_ACTIVITE_COMPL = '" + numAct + "';");
            try {
                
               
                while (rs.next()) {
                    String activiteRealiser = convertir_RS_ActiviteRealiser(rs);
                    listeActiviteRealiser.add(activiteRealiser);
                    
                }
               if(listeActiviteRealiser.isEmpty()){
                   System.out.println("Liaison REALISER Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeActiviteRealiser;
        } else {
            System.out.println("Connexion manipBdd.REALISER KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines liaisons,
     //en fonction du numéro de visiteur
     
     public ArrayList<String> lire1IdActRealiser(String mat) {
        if (bdd.connecter() == true) {
            ArrayList<String> listeActiviteRealiser = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT AC_NUM_ACTIVITE_COMPL FROM `REALISER` WHERE VIS_MATRICULE_VISITEUR = '" + mat + "';");
            try {
                
               
                while (rs.next()) {
                    String activiteRealiser = convertir_RS_ActiviteRealiser(rs);
                    listeActiviteRealiser.add(activiteRealiser);
                    
                }
               if(listeActiviteRealiser.isEmpty()){
                   System.out.println("Liaison REALISER Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeActiviteRealiser;
        } else {
            System.out.println("Connexion manipBdd.REALISER KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de clients selectionnée.
     
     
     public void printActiviteRealisers(ArrayList<String> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1 + ".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableActiviteRealiser tableActiviteRealiser = new CTableActiviteRealiser();
//         CSecteur secteur = new CSecteur(1, "EST");
//         
//         CActiviteRealiser ActiviteRealiser = new CActiviteRealiser(4, "Bourgogne");
//         
//         tableActiviteRealiser.insererActiviteRealiser(ActiviteRealiser, secteur);
//         tableActiviteRealiser.supprimerActiviteRealiser("3");
//         tableActiviteRealiser.modifierActiviteRealiser(ActiviteRealiser, secteur);
//         tableActiviteRealiser.printActiviteRealisers(tableActiviteRealiser.lire1ActiviteRealiser("REG_NOM_ActiviteRealiser", "Bretagne"));
//         tableActiviteRealiser.printActiviteRealisers(tableActiviteRealiser.lireActiviteRealisers());
         
     }
}
