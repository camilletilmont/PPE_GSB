/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CAdresse;
import Donnees.CRegion;
import Donnees.CSecteur;
import Donnees.CRole;
import Donnees.CVisiteur;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilletilmont
 */
public class CTableTravailler {
    
    //attributs.
    
     protected ArrayList<CRole> listeTravaillers;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CRole> getListeTravaillers() {
        return listeTravaillers;
    }

    public void setListeTravaillers(ArrayList<CRole> listeTravaillers) {
        this.listeTravaillers = listeTravaillers;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableTravailler(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE IF NOT EXISTS TRAVAILLER (JJMMAA_EMBDATE DATE NOT NULL, "
                + "REG_CODE_REGION TINYINT(2) NOT NULL, "
                + "VIS_MATRICULE_VISITEUR CHAR(4) NOT NULL, "
                + "TRA_ROLE_TRAVAILLER VARCHAR(30), "
                + "PRIMARY KEY (JJMMAA_EMBDATE, "
                + "REG_CODE_REGION, "
                + "VIS_MATRICULE_VISITEUR) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TRAVAILLER KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`TRAVAILLER`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TRAVAILLER KO");
        }
        return res;
    }
     
     //insértion d'un Role
     
     public int insererRole(CRole role, CVisiteur visiteur) {
        String req = "INSERT INTO `TRAVAILLER` (`JJMMAA_EMBDATE`,`REG_CODE_REGION`,"
                + " `VIS_MATRICULE_VISITEUR`,"
                + " `TRA_ROLE_TRAVAILLER`) "
                + "VALUES ('"+ new Date(role.getDate().getTimeInMillis())+"',"
                + " '"+role.getregion()+"',"
                + " '"+visiteur.getMatricule()+"',"
                + " '"+role.getNomRole()+"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TRAVAILLER KO");
        }
        return res;
    }
     
     
//     //modification d'un Role selon le matricule d'un visiteur, l'Id Region et la date
     
     public int modifierRole(CRole role,CSecteur secteur,CRegion region,CVisiteur visiteur) {

        String req = "UPDATE TRAVAILLER SET "
                + "JJMMAA_EMBDATE = '" + new Date(role.getDate().getTimeInMillis()) + "', "
                + "REG_CODE_REGION = '"+ role.getregion() + "', "
                + "VIS_MATRICULE_VISITEUR = '"+ visiteur.getMatricule() + "', "
                + "TRA_ROLE_TRAVAILLER = '" + role.getNomRole() +"' "
                + " WHERE VIS_MATRICULE_VISITEUR = '"+ visiteur.getMatricule() + "'"
                + " AND REG_CODE_REGION = '"+role.getregion() +"'"
                + " AND JJMMAA_EMBDATE = '"+ new Date(role.getDate().getTimeInMillis()) +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TRAVAILLER KO");
        }
        return res;
    }
     
     
     //suppression d'un Role selon le matricule d'un visiteur
     
     public int supprimerRole(String x) {
        String req = "DELETE FROM TRAVAILLER WHERE VIS_MATRICULE_VISITEUR = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TRAVAILLER KO");
        }
        return res;
    }
     
     
     
     
     //methode afin de convertire les données récupérées de la table
     
     CRole convertir_RS_Role(ResultSet rs) {
        try {
            
           
           
           GregorianCalendar gb = new GregorianCalendar();
           gb.setTime(Date.valueOf(rs.getString("JJMMAA_EMBDATE")));
            
           return new CRole(rs.getString("TRA_ROLE_TRAVAILLER"),gb,rs.getInt("REG_CODE_REGION"));
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CRole.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CRole> lireRoles() {
        if (bdd.connecter() == true) {
            ArrayList<CRole> listeDeTravaillers = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `TRAVAILLER`");
            try {
                while (rs.next()) {
                    CRole Travailler = convertir_RS_Role(rs);
                    listeDeTravaillers.add(Travailler);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeTravaillers;
        } else {
            System.out.println("Connexion manipBdd.TRAVAILLER KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains Roles,
     //en fonction l'information souhaitée.
     
     public ArrayList<CRole> lire1Roles(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CRole> listeTravailler = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `TRAVAILLER` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CRole Travailler = convertir_RS_Role(rs);
                    listeTravailler.add(Travailler);
                    
                }
               if(listeTravailler.isEmpty()){
                   System.out.println("TRAVAILLER Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeTravailler;
        } else {
            System.out.println("Connexion manipBdd.TRAVAILLER KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Roles selectionnés.
     
     
     public void printRoles(ArrayList<CRole> liste){
         liste.forEach((liste1) -> {
             System.out.println("Nom Role : " +liste1.getNomRole() + ". Date: "+ liste1.getDate().getTime() +".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableTravailler tableTravailler = new CTableTravailler();
//         CSecteur secteur = new CSecteur(1, "EST");
//         CRegion region = new CRegion(1,"Bretagne");
//         CVisiteur visiteur = new CVisiteur("V001", "Claude", "Camille", new GregorianCalendar(), "Manucure", new CAdresse("13", "Rue de l''eglise","22150","Ploeuc sur lié"), 22);
//         CRole role = new CRole("Skieur",new GregorianCalendar(),1);
//         
         //tableTravailler.insererRole(role,visiteur);
         //tableTravailler.supprimerRole("V001");
         //tableTravailler.modifierRole(role,visiteur);
         //tableTravailler.printRoles(tableTravailler.lire1Travaillers("VIS_MATRICULE_VISITEUR", "V001"));
     }
     
    
    
}
