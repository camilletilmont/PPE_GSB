/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CAdresse;
import Donnees.CSecteur;
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
public class CTableVisiteur {
      //attributs.
    
     protected ArrayList<CVisiteur> listeVisiteurs;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CVisiteur> getListeVisiteurs() {
        return listeVisiteurs;
    }

    public void setListeVisiteurs(ArrayList<CVisiteur> listeVisiteurs) {
        this.listeVisiteurs = listeVisiteurs;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableVisiteur(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE IF NOT EXISTS VISITEUR (VIS_MATRICULE_VISITEUR CHAR(4) NOT NULL, "
            + "VIS_NOM_VISITEUR VARCHAR(30), "
            + "VIS_PRENOM_VISITEUR VARCHAR(30), "
            + "VIS_ADRESSE_VISITEUR VARCHAR(100), "
            + "VIS_CP_VISITEUR VARCHAR(5), "
            + "VIS_VILLE_VISITEUR VARCHAR(45), "
            + "VIS_DATEEMBAUCHE_VISITEUR DATE, "
            + "secteur_sec_code_secteur TINYINT(2) NOT NULL, "
            + "DEP_CODE_DEPARTEMENT TINYINT NOT NULL, "
            + "PRIMARY KEY (VIS_MATRICULE_VISITEUR) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.VISITEUR KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`VISITEUR`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.VISITEUR KO");
        }
        return res;
    }
     
     //insértion d'un Visiteur
     
     public int insererVisiteur(CVisiteur visiteur, CSecteur secteur) {
        String req = "INSERT INTO `VISITEUR` (`VIS_MATRICULE_VISITEUR`,`VIS_NOM_VISITEUR`, `VIS_PRENOM_VISITEUR`,"
                + "`VIS_ADRESSE_VISITEUR`, `VIS_CP_VISITEUR`,`VIS_VILLE_VISITEUR`,`VIS_DATEEMBAUCHE_VISITEUR`,"
                + " `secteur_sec_code_secteur`, `DEP_CODE_DEPARTEMENT`)"
                + "VALUES ('"+visiteur.getMatricule()+"',"
                + " '"+visiteur.getNom()+"',"
                + " '"+visiteur.getPrenom()+"',"
                + " '"+visiteur.getAdresse().getNumero() +","+ visiteur.getAdresse().getRue()+"',"
                + " '"+visiteur.getAdresse().getCodePostal()+"',"
                + " '"+visiteur.getAdresse().getVille()+"',"
                + " '"+ new Date(visiteur.getDateEmbauche().getTimeInMillis())+"',"
                + " '"+ secteur.getIdSecteur() +"',"
                + " '"+ visiteur.getDepartement() +"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.VISITEUR KO");
        }
        return res;
    }
     
     
             //modification d'un Visiteur selon son Id
     
     public int modifierVisiteur(CVisiteur visiteur,CSecteur secteur) {

        String req = "UPDATE VISITEUR SET "
                + "VIS_MATRICULE_VISITEUR = '" + visiteur.getMatricule() + "', "
                + "VIS_NOM_VISITEUR = '"+ visiteur.getNom() + "', "
                + "VIS_PRENOM_VISITEUR = '"+ visiteur.getPrenom() + "', "
                + "VIS_ADRESSE_VISITEUR = '" + visiteur.getAdresse().getNumero() +","+ visiteur.getAdresse().getRue() + "', "
                + "VIS_CP_VISITEUR = '"+ visiteur.getAdresse().getCodePostal() + "', "
                + "VIS_VILLE_VISITEUR = '"+ visiteur.getAdresse().getVille() + "', "
                + "VIS_DATEEMBAUCHE_VISITEUR = '" + new Date(visiteur.getDateEmbauche().getTimeInMillis()) + "', "
                + "secteur_sec_code_secteur = '"+ secteur.getIdSecteur() + "', "
                + "DEP_CODE_DEPARTEMENT = '"+ visiteur.getDepartement() + "' "
                + " WHERE VIS_MATRICULE_VISITEUR = '"+ visiteur.getMatricule() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.VISITEUR KO");
        }
        return res;
    }
     
     
     //suppression d'un Visiteur selon son Id
     
     public int supprimerVisiteur(String x) {
        String req = "DELETE FROM VISITEUR WHERE VIS_MATRICULE_VISITEUR = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.VISITEUR KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CVisiteur convertir_RS_Visiteur(ResultSet rs) {
        try {
            
            String address = rs.getString("VIS_ADRESSE_VISITEUR");
            int ind = address.indexOf(",");
            String num = address.substring(0, ind);
            String rue = address.substring(ind + 1);
           GregorianCalendar gb = new GregorianCalendar();
           gb.setTime(Date.valueOf(rs.getString("VIS_DATEEMBAUCHE_VISITEUR")));
            
                    return new CVisiteur(rs.getString("VIS_MATRICULE_VISITEUR"), rs.getString("VIS_NOM_VISITEUR"),rs.getString("VIS_PRENOM_VISITEUR"),
                    gb,"",new CAdresse(num,rue,rs.getString("VIS_CP_VISITEUR"),rs.getString("VIS_VILLE_VISITEUR")),rs.getInt("DEP_CODE_DEPARTEMENT"));
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CVisiteur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CVisiteur> lireVisiteurs() {
        if (bdd.connecter() == true) {
            ArrayList<CVisiteur> listeDeVisiteurs = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `VISITEUR`");
            try {
                while (rs.next()) {
                    CVisiteur visiteur = convertir_RS_Visiteur(rs);
                    listeDeVisiteurs.add(visiteur);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeVisiteurs;
        } else {
            System.out.println("Connexion manipBdd.VISITEUR KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains Visiteurs,
     //en fonction de l'information souhaitée.
     
     public ArrayList<CVisiteur> lire1Visiteurs(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CVisiteur> listeVisiteur = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `VISITEUR` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CVisiteur visiteur = convertir_RS_Visiteur(rs);
                    listeVisiteur.add(visiteur);
                    
                }
               if(listeVisiteur.isEmpty()){
                   System.out.println("VISITEUR Not Found !");
               
               }
            } catch (SQLException ex) {
                
                return listeVisiteur;
            }
            bdd.deconnecter();
            
            
            return listeVisiteur;
        } else {
            System.out.println("Connexion manipBdd.VISITEUR KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Visiteurs selectionnés.
     
     
     public void printVisiteurs(ArrayList<CVisiteur> liste){
         liste.forEach((liste1) -> {
             System.out.println("Adresse : " +liste1.getAdresse().getRue() + ". Prenom : "+liste1.getPrenom() +". Matricule : " + liste1.getMatricule()+".");
        });
     
     }
     
     public static void main(String[] args) {
//     
//         CTableVisiteur tableVisiteur = new CTableVisiteur();
//         CSecteur secteur = new CSecteur(1, "EST");
//         
//         CVisiteur visiteur = new CVisiteur("V001", "Claude", "Camille", new GregorianCalendar(), "Manucure", new CAdresse("13", "Rue de l''eglise","22150","Ploeuc sur lié"), 2);
//         
         //tableVisiteur.insererVisiteur(visiteur, secteur);
         //tableVisiteur.supprimerVisiteur("V001");
         //tableVisiteur.modifierVisiteur(visiteur, secteur);
         //tableVisiteur.printVisiteurs(tableVisiteur.lire1Visiteurs("VIS_NOM_VISITEUR", "Lol"));
         //tableVisiteur.printVisiteurs(tableVisiteur.lireVisiteurs());
     }
     
     
}
