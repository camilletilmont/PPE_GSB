/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CComposant;
import Donnees.CValeurComposant;
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
public class CTableValeurComposant {
 
    //attributs
    protected ArrayList<CValeurComposant> listeValeurComposant;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CValeurComposant> getListeValeurComposant() {
        return listeValeurComposant;
    }

    public void setListeValeurComposant(ArrayList<CValeurComposant> listeValeurComposant) {
        this.listeValeurComposant = listeValeurComposant;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableValeurComposant(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE CONSTITUER (MED_DEPOTLEGAL_MEDICAMENT INT  AUTO_INCREMENT NOT NULL,"
                + " CMP_CODE_COMPOSANT SMALLINT NOT NULL,"
                + " CST_QTE_CONSTITUER FLOAT,"
                + " CST_UNITE_CONSTITUER VARCHAR(2),"
                + " PRIMARY KEY (MED_DEPOTLEGAL_MEDICAMENT,"
                + " CMP_CODE_COMPOSANT) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.CONSTITUER KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`CONSTITUER`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.CONSTITUER KO");
        }
        return res;
    }
     
     //insértion d'une Valeur de Composant
     
     public int insererValeurComposant(CValeurComposant valeurComposant,/*CMedicament medoc*/ int medoc) {
        String req = "INSERT INTO `CONSTITUER` (`MED_DEPOTLEGAL_MEDICAMENT`,`CMP_CODE_COMPOSANT`,`CST_QTE_CONSTITUER`, `CST_UNITE_CONSTITUER`)"
                + " VALUES ('"+medoc/*.getDepotLegal()*/+"',"
                + " '"+valeurComposant.getComposant().getIdComposant()+"',"
                + " '"+valeurComposant.getQuantite()+"',"
                + " '"+valeurComposant.getUnite()+"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.CONSTITUER KO");
        }
        return res;
    }
     
     
//     //modification d'une Valeur de Composant selon l'Id du composant et le depot legal du medicament
     
     public int modifierValeurComposant(CValeurComposant valeurComposant,/*CMedicament medoc*/int medoc) {

        String req = "UPDATE CONSTITUER SET "
                + "MED_DEPOTLEGAL_MEDICAMENT = '" + medoc/*.getDepotLegal()*/ + "', "
                + "CMP_CODE_COMPOSANT = '"+ valeurComposant.getComposant().getIdComposant() + "', "
                + "CST_QTE_CONSTITUER = '"+ valeurComposant.getQuantite()+"', "
                + "CST_UNITE_CONSTITUER = '"+ valeurComposant.getUnite() + "' "
                + " WHERE MED_DEPOTLEGAL_MEDICAMENT = '"+ medoc/*.getDepotLegal()*/+ "' AND CMP_CODE_COMPOSANT = '"+valeurComposant.getComposant().getIdComposant() +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.CONSTITUER KO");
        }
        return res;
    }
     
     
     //suppression d'une Valeur de Composant selon l'Id du composant et le depot legal du medicament
     
     public int supprimerValeurComposant(String codeMedoc,String codeCompo) {
        String req = "DELETE FROM CONSTITUER WHERE MED_DEPOTLEGAL_MEDICAMENT = '"+ codeMedoc+ "' AND CMP_CODE_COMPOSANT = '"+codeCompo +"';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.CONSTITUER KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CValeurComposant convertir_RS_ValeurComposant(ResultSet rs) {
        try {
            
            String code = rs.getString("CMP_CODE_COMPOSANT");
            
            CTableComposant tablComp = new CTableComposant();
            ArrayList<CComposant> listeCompo =  tablComp.lire1Composant("CMP_CODE_COMPOSANT",code);
            CComposant compo = listeCompo.get(0);
            
            float quantite = rs.getFloat("CST_QTE_CONSTITUER");
            
            String unite = rs.getString("CST_UNITE_CONSTITUER");
            
                    return new CValeurComposant(compo, quantite, unite);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CValeurComposant.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CValeurComposant> lireValeurComposant() {
        if (bdd.connecter() == true) {
            ArrayList<CValeurComposant> listeDeValeurComposant = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `CONSTITUER`");
            try {
                while (rs.next()) {
                    CValeurComposant ValeurComposant = convertir_RS_ValeurComposant(rs);
                    listeDeValeurComposant.add(ValeurComposant);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeValeurComposant;
        } else {
            System.out.println("Connexion manipBdd.CONSTITUER KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines Valeurs de Composant,
     //en fonction du dépot legal du medicament
     
     public ArrayList<CValeurComposant> lire1ValeurComposant(String depotLegal) {
        if (bdd.connecter() == true) {
            ArrayList<CValeurComposant> liste1ValeurComposant = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `CONSTITUER` WHERE MED_DEPOTLEGAL_MEDICAMENT = '" + depotLegal + "' ;");
            try {
                
               
                while (rs.next()) {
                    CValeurComposant ValeurComposant = convertir_RS_ValeurComposant(rs);
                    liste1ValeurComposant.add(ValeurComposant);
                    
                }
               if(liste1ValeurComposant.isEmpty()){
                   System.out.println("CONSTITUER Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1ValeurComposant;
        } else {
            System.out.println("Connexion manipBdd.CONSTITUER KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Valeurs de Composant selectionnées.
     
     
     public void printValeurComposant(ArrayList<CValeurComposant> liste){
         liste.forEach((liste1) -> {
             System.out.println("Nom Compo : " +liste1.getComposant().getNomComposant() + ". Qte: "+liste1.getQuantite() +".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableValeurComposant tableValeurComposant = new CTableValeurComposant();
//         CComposant comp = new CComposant(5671, "Uranium 223");
//  
//         CValeurComposant ValeurComposant = new CValeurComposant(comp, 185f, "Kg");
//         int medoc = 321;
         //tableValeurComposant.insererValeurComposant(ValeurComposant,medoc);
         //tableValeurComposant.supprimerValeurComposant("321" ,"31");
         //tableValeurComposant.modifierValeurComposant(ValeurComposant,medoc);
         //tableValeurComposant.printValeurComposant(tableValeurComposant.lire1ValeurComposant("321"));
         //tableValeurComposant.printValeurComposant(tableValeurComposant.lireValeurComposant());
     }
    
}
