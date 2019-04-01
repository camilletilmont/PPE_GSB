/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CFormeMedicament;
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
public class CTableFormeMedicament {
     //attributs
    
     protected ArrayList<CFormeMedicament> listeFormeMedicaments;
    protected CBDD bdd;

    
    
    //getter et setter
    public ArrayList<CFormeMedicament> getListeFormeMedicaments() {
        return listeFormeMedicaments;
    }

    public void setListeFormeMedicaments(ArrayList<CFormeMedicament> listeFormeMedicaments) {
        this.listeFormeMedicaments = listeFormeMedicaments;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    


    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableFormeMedicament(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE PRESENTATION (PRE_CODE_PRESENTATION TINYINT  AUTO_INCREMENT NOT NULL,"
                + " PRE_LIBELLE_PRESENTATION VARCHAR(50),"
                + " PRIMARY KEY (PRE_CODE_PRESENTATION) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESENTATION KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`PRESENTATION`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESENTATION KO");
        }
        return res;
    }
     
     //insértion d'une forme de medicament
     
     public int insererFormeMedicament(CFormeMedicament formeMedicament) {
        String req = "INSERT INTO `PRESENTATION` (`PRE_LIBELLE_PRESENTATION`) "
                + "VALUES ('"+ formeMedicament.getNomFormMedic()+"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESENTATION KO");
        }
        return res;
    }
     
     
//     //modification d'une forme de medicament selon son Id
     
     public int modifierFormeMedicament(CFormeMedicament formeMedicament) {

        String req = "UPDATE PRESENTATION SET "
                + "PRE_CODE_PRESENTATION = '" + formeMedicament.getIdFormeMedic() + "', "
                + "PRE_LIBELLE_PRESENTATION = '"+ formeMedicament.getNomFormMedic() + "' "
                + " WHERE PRE_CODE_PRESENTATION = '"+ formeMedicament.getIdFormeMedic() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESENTATION KO");
        }
        return res;
    }
     
     
     //suppression d'une forme de medicament selon son Id
     
     public int supprimerFormeMedicament(String x) {
        String req = "DELETE FROM PRESENTATION WHERE PRE_CODE_PRESENTATION = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRESENTATION KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CFormeMedicament convertir_RS_FormeMedicament(ResultSet rs) {
        try {
                
           return new CFormeMedicament(rs.getInt("PRE_CODE_PRESENTATION"),rs.getString("PRE_LIBELLE_PRESENTATION"));
           
        } catch (SQLException ex) {
            Logger.getLogger(CFormeMedicament.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CFormeMedicament> lireFormeMedicaments() {
        if (bdd.connecter() == true) {
            ArrayList<CFormeMedicament> listeDeFormeMedicaments = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `PRESENTATION`");
            try {
                while (rs.next()) {
                    CFormeMedicament FormeMedicament = convertir_RS_FormeMedicament(rs);
                    listeDeFormeMedicaments.add(FormeMedicament);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeFormeMedicaments;
        } else {
            System.out.println("Connexion manipBdd.PRESENTATION KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines formes de medicament,
     //en fonction l'information souhaitée.
     
     public ArrayList<CFormeMedicament> lire1FormeMedicament(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CFormeMedicament> listeFormeMedicament = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `PRESENTATION` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CFormeMedicament FormeMedicament = convertir_RS_FormeMedicament(rs);
                    listeFormeMedicament.add(FormeMedicament);
                    
                }
               if(listeFormeMedicament.isEmpty()){
                   System.out.println("PRESENTATION Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listeFormeMedicament;
        } else {
            System.out.println("Connexion manipBdd.PRESENTATION KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de formes de medicament selectionnées.
     
     
     public void printFormeMedicaments(ArrayList<CFormeMedicament> liste){
         liste.forEach((liste1) -> {
             System.out.println("Id : " +liste1.getIdFormeMedic() + ". Nom FormeMedicament : "+liste1.getNomFormMedic()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableFormeMedicament tableFormeMedicament = new CTableFormeMedicament();
//         CFormeMedicament formeMedicament = new CFormeMedicament(3, "Non");
//      
       //tableFormeMedicament.insererFormeMedicament(formeMedicament);
       //tableFormeMedicament.supprimerFormeMedicament("1");
       //tableFormeMedicament.modifierFormeMedicament(formeMedicament);
       //tableFormeMedicament.printFormeMedicaments(tableFormeMedicament.lire1FormeMedicament("PRE_CODE_PRESENTATION", "5"));
       //tableFormeMedicament.printFormeMedicaments(tableFormeMedicament.lireFormeMedicaments());
         
     }
}
