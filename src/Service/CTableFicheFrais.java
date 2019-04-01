/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CFicheFrais;
import Donnees.CFrais;
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
public class CTableFicheFrais {
    
    //attributs
    protected ArrayList<CFicheFrais> listeFicheFrais;
    protected CTableFrais tableFrais = new CTableFrais();
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CFicheFrais> getListeFicheFrais() {
        return listeFicheFrais;
    }

    public void setListeFrais(ArrayList<CFicheFrais> listeFicheFrais) {
        this.listeFicheFrais = listeFicheFrais;
    }
    
    
    public CTableFrais getTableFrais() {
        return tableFrais;
    }

    public void setTableFrais(CTableFrais tableFrais) {
        this.tableFrais = tableFrais;
    }
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableFicheFrais(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE FICHE_FRAIS (FF_MOIS_FICHE_FRAIS TINYINT(2)  AUTO_INCREMENT NOT NULL,"
                + " FF_NBHORSCLASSIF TINYINT,"
                + " FF_MONTANTHORSCLASSIF FLOAT,"
                + " VIS_MATRICULE_VISITEUR CHAR(4) NOT NULL,"
                + " PRIMARY KEY (FF_MOIS_FICHE_FRAIS) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FICHE_FRAIS KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS FICHE_FRAIS;";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FICHE_FRAIS KO");
        }
        return res;
    }
     
     //insértion d'une Fiche de Frais
     
     public int insererFicheFrais(CFicheFrais ficheFrais) {
        String req = "INSERT INTO `FICHE_FRAIS` (`FF_MOIS_FICHE_FRAIS`, `VIS_MATRICULE_VISITEUR`, `FF_NBHORSCLASSIF`,`FF_MONTANTHORSCLASSIF`)"
                + " VALUES ('"+ ficheFrais.getMois()+"',"
                + " '"+ficheFrais.getMatriculeVisit()+"',"
                + " '"+ficheFrais.getNbrHorsClassif()+"',"
                + " '"+ficheFrais.getMontantHorsClassif() +"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FICHE_FRAIS KO");
        }
        return res;
    }
     
     
//     //modification d'une Fiche de Frais en conftion de son Mois et de son Visiteur rattaché
     
     public int modifierFicheFrais(CFicheFrais ficheFrais) {

        String req = "UPDATE FICHE_FRAIS SET "
                + "FF_MOIS_FICHE_FRAIS = '" + ficheFrais.getMois() + "', "
                + "VIS_MATRICULE_VISITEUR = '"+ ficheFrais.getMatriculeVisit() + "', "
                + "FF_NBHORSCLASSIF = '"+ ficheFrais.getNbrHorsClassif()+"', "
                + "FF_MONTANTHORSCLASSIF = '"+ ficheFrais.getMontantHorsClassif() + "' "
                + " WHERE FF_MOIS_FICHE_FRAIS = '"+ ficheFrais.getMois() + "' AND VIS_MATRICULE_VISITEUR = '"+ficheFrais.getMatriculeVisit() +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FICHE_FRAIS KO");
        }
        return res;
    }
     
     
     //suppression d'une Fiche de Frais en conftion de son Mois et de son Visiteur rattaché
     
     public int supprimerFicheFrais(String mois, String matricule) {
        String req = "DELETE FROM FICHE_FRAIS WHERE FF_MOIS_FICHE_FRAIS = '" + mois +"' AND VIS_MATRICULE_VISITEUR = '"+ matricule +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.FICHE_FRAIS KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CFicheFrais convertir_RS_FicheFrais(ResultSet rs) {
        try {

            int mois = rs.getInt("FF_MOIS_FICHE_FRAIS");
            int nbrHors = rs.getInt("FF_NBHORSCLASSIF");
            float montant = rs.getFloat("FF_MONTANTHORSCLASSIF");
            String matVisit = rs.getString("VIS_MATRICULE_VISITEUR");
            
            
            ArrayList<CFrais> tab =  tableFrais.lire1Frais(mois,matVisit);
            
            
                    return new CFicheFrais(mois, nbrHors, montant, matVisit, tab);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CFrais.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CFicheFrais> lireFicheFrais() {
        if (bdd.connecter() == true) {
            ArrayList<CFicheFrais> listeFicheDeFrais = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `FICHE_FRAIS`");
            try {
                while (rs.next()) {
                    CFicheFrais ficheFrais = convertir_RS_FicheFrais(rs);
                    listeFicheDeFrais.add(ficheFrais);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeFicheDeFrais;
        } else {
            System.out.println("Connexion manipBdd.FICHE_FRAIS KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines fiche de frais,
     //en fonction l'information souhaitée.
     
     public ArrayList<CFicheFrais> lire1FicheFrais(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CFicheFrais> liste1FicheF = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `FICHE_FRAIS` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CFicheFrais ficheFrais = convertir_RS_FicheFrais(rs);
                    liste1FicheF.add(ficheFrais);
                    
                }
               if(liste1FicheF.isEmpty()){
                   System.out.println("FICHE_FRAIS Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1FicheF;
        } else {
            System.out.println("Connexion manipBdd.FICHE_FRAIS KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Fiches de Frais selectionnées.
     
     
     public void printFicheFrais(ArrayList<CFicheFrais> liste){
         liste.forEach((liste1) -> {
             System.out.println("Mois : " +liste1.getMois() + ". Montant Hors : "+liste1.getMontantHorsClassif() +".");
        liste1.getFraisList().forEach((listeFr) -> {
             System.out.println("Nom Type Frais : " + listeFr.gettypeFrais().getNom() + ". Montant Frais : " + listeFr.getMontantFrais());
         
         });
         
         });
     
     }
     
     public static void main(String[] args) {
     
//         CTableFicheFrais tableFicheFrais = new CTableFicheFrais();
//         List<CFrais> fraisList = new ArrayList();
//         CTypeFrais typeFr = new CTypeFrais("2", "Wesh", 34.3f);
//         CFrais frais = new CFrais(3, 3.7f, typeFr);
//         fraisList.add(frais);
//         CFicheFrais ficheFrais = new CFicheFrais(3, 23, 5000.00f, "V001", fraisList);
//         
         //tableFicheFrais.insererFicheFrais(ficheFrais);
         //tableFicheFrais.supprimerFicheFrais("3", "V001");
         //tableFicheFrais.modifierFicheFrais(ficheFrais);
         //tableFicheFrais.printFicheFrais(tableFicheFrais.lire1FicheFrais("VIS_MATRICULE_VISITEUR", "V001"));
         //tableFicheFrais.printFicheFrais(tableFicheFrais.lireFicheFrais());
     }
    
}
