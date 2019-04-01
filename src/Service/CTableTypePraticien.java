/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CTypePraticien;
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
public class CTableTypePraticien {
    
     //attributs.
    
     protected ArrayList<CTypePraticien> listeTypePraticiens;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CTypePraticien> getListeTypePraticiens() {
        return listeTypePraticiens;
    }

    public void setListeTypePraticiens(ArrayList<CTypePraticien> listeTypePraticiens) {
        this.listeTypePraticiens = listeTypePraticiens;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableTypePraticien(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE TYPE_PRATICIEN (TYP_CODE_TYPE_PRATICIEN TINYINT(3)  AUTO_INCREMENT NOT NULL,"
                + " TYP_LIBELLE_TYPE_PRATICIEN VARCHAR(20),"
                + " TYP_LIEU_TYPE_PRATICIEN VARCHAR(20),"
                + " PRIMARY KEY (TYP_CODE_TYPE_PRATICIEN) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_PRATICIEN KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`TYPE_PRATICIEN`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_PRATICIEN KO");
        }
        return res;
    }
     
     //insértion d'un Type de Praticien
     
     public int insererTypePraticien(CTypePraticien typePraticien) {
        String req = "INSERT INTO `TYPE_PRATICIEN` (`TYP_LIBELLE_TYPE_PRATICIEN`, `TYP_LIEU_TYPE_PRATICIEN`)"
                + " VALUES ('"+typePraticien.getLibelle()+"',"
                + " '"+typePraticien.getLieu() +"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_PRATICIEN KO");
        }
        return res;
    }
     
     
//     //modification d'un Type de Praticien selon l'id
     
     public int modifierTypePraticien(CTypePraticien typePraticien) {

        String req = "UPDATE TYPE_PRATICIEN SET "
                + "TYP_CODE_TYPE_PRATICIEN = '" + typePraticien.getIdType() + "', "
                + "TYP_LIBELLE_TYPE_PRATICIEN = '"+ typePraticien.getLibelle() + "', "
                + "TYP_LIEU_TYPE_PRATICIEN = '"+ typePraticien.getLieu() + "' "
                + " WHERE TYP_CODE_TYPE_PRATICIEN = '"+ typePraticien.getIdType() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_PRATICIEN KO");
        }
        return res;
    }
     
     
     //suppression d'un Type de Praticien selon l'Id
     
     public int supprimerTypePraticien(String x) {
        String req = "DELETE FROM TYPE_PRATICIEN WHERE TYP_CODE_TYPE_PRATICIEN = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.TYPE_PRATICIEN KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CTypePraticien convertir_RS_TypePraticien(ResultSet rs) {
        try {
            
            int id = rs.getInt("TYP_CODE_TYPE_PRATICIEN");
            String libelle = rs.getString("TYP_LIBELLE_TYPE_PRATICIEN");
            String lieu = rs.getString("TYP_LIEU_TYPE_PRATICIEN");
            
                    return new CTypePraticien(id,libelle,lieu);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CTypePraticien.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CTypePraticien> lireTypePraticiens() {
        if (bdd.connecter() == true) {
            ArrayList<CTypePraticien> listeDeTypePraticiens = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `TYPE_PRATICIEN`");
            try {
                while (rs.next()) {
                    CTypePraticien TypePraticien = convertir_RS_TypePraticien(rs);
                    listeDeTypePraticiens.add(TypePraticien);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeTypePraticiens;
        } else {
            System.out.println("Connexion manipBdd.TYPE_PRATICIEN KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains Types de Praticien,
     //en fonction l'information souhaitée.
     
     public ArrayList<CTypePraticien> lire1TypePraticiens(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CTypePraticien> listeTypePraticien = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `TYPE_PRATICIEN` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CTypePraticien TypePraticien = convertir_RS_TypePraticien(rs);
                    listeTypePraticien.add(TypePraticien);
                    
                }
               if(listeTypePraticien.isEmpty()){
                   System.out.println("TYPE_PRATICIEN Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeTypePraticien;
        } else {
            System.out.println("Connexion manipBdd.TYPE_PRATICIEN KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Types de Praticien selectionnés.
     
     
     public void printTypePraticiens(ArrayList<CTypePraticien> liste){
         liste.forEach((liste1) -> {
             System.out.println("Code : " +liste1.getIdType() + ". Libelle : "+liste1.getLibelle() +". Lieu  : " + liste1.getLieu()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableTypePraticien tableTypePraticien = new CTableTypePraticien();
//         
//         
//         CTypePraticien typePraticien = new CTypePraticien(1,"Medecin","Hospice");
         //tableTypePraticien.insererTypePraticien(typePraticien);
         //tableTypePraticien.supprimerTypePraticien("1");
         //tableTypePraticien.modifierTypePraticien(typePraticien);
         //tableTypePraticien.printTypePraticiens(tableTypePraticien.lire1TypePraticiens("TYP_CODE_TYPE_PRATICIEN", "3"));
         //tableTypePraticien.printTypePraticiens(tableTypePraticien.lireTypePraticiens());
     }
    
}
