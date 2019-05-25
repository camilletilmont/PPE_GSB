/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;



import Donnees.CPraticien;
import Donnees.CSpePossede;
import Donnees.CSpecialite;
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
public class CTableSpePossede {
    
    //attributs
    protected ArrayList<CSpePossede> listeSpePossede;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CSpePossede> getListeSpePossede() {
        return listeSpePossede;
    }

    public void setListeSpePossede(ArrayList<CSpePossede> listeSpePossede) {
        this.listeSpePossede = listeSpePossede;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableSpePossede(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE POSSEDER (PRA_NUM_PRATICIEN SMALLINT  AUTO_INCREMENT NOT NULL,"
                + " SPE_CODE_SPECIALITE SMALLINT NOT NULL,"
                + " POS_DIPLOME_POSSEDER VARCHAR(80),"
                + " POS_COEFPRESCRIPTION_POSSEDER FLOAT,"
                + " PRIMARY KEY (PRA_NUM_PRATICIEN,"
                + " SPE_CODE_SPECIALITE) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.POSSEDER KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`POSSEDER`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.POSSEDER KO");
        }
        return res;
    }
     
     //insértion d'une SpePossede
     
     public int insererSpePossede(CSpePossede spePossede,CPraticien praticien) {
        String req = "INSERT INTO `POSSEDER` (`PRA_NUM_PRATICIEN`,`SPE_CODE_SPECIALITE`,`POS_DIPLOME_POSSEDER`, `POS_COEFPRESCRIPTION_POSSEDER`)"
                + " VALUES ('"+praticien.getIdPraticien()+"',"
                + " '"+spePossede.getSpecialite().getIdSpecialite()+"',"
                + " '"+spePossede.getDiplome()+"',"
                + " '"+spePossede.getCoeffPrescription()+"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.POSSEDER KO");
        }
        return res;
    }
     
     
//     //modification d'une SpePosseder selon l'id du praticien et l'id de la specialité
     
     public int modifierSpePossede(CSpePossede spePossede,CPraticien praticien) {

        String req = "UPDATE POSSEDER SET "
                + "PRA_NUM_PRATICIEN = '" + praticien.getIdPraticien() + "', "
                + "SPE_CODE_SPECIALITE = '"+ spePossede.getSpecialite().getIdSpecialite() + "', "
                + "POS_DIPLOME_POSSEDER = '"+ spePossede.getDiplome()+"', "
                + "POS_COEFPRESCRIPTION_POSSEDER = '"+ spePossede.getCoeffPrescription() + "' "
                + " WHERE PRA_NUM_PRATICIEN = '"+ praticien.getIdPraticien() + "' AND SPE_CODE_SPECIALITE = '"+spePossede.getSpecialite().getIdSpecialite() +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.POSSEDER KO");
        }
        return res;
    }
     
     
     //suppression d'une SpePosseder selon l'id du praticien et l'id de la specialité
     
     public int supprimerSpePossede(String numPrati,String codeSpec) {
        String req = "DELETE FROM POSSEDER WHERE PRA_NUM_PRATICIEN = '" + numPrati +"' AND SPE_CODE_SPECIALITE = '"+ codeSpec +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.POSSEDER KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CSpePossede convertir_RS_SpePossede(ResultSet rs) {
        try {
            
            String code = rs.getString("SPE_CODE_SPECIALITE");
            
            CTableSpecialite lireSpecia = new CTableSpecialite();
            ArrayList<CSpecialite> tab =  lireSpecia.lire1Specialite("SPE_CODE_SPECIALITE",code);
            CSpecialite specialite1 = tab.get(0);
            
            String diplome = rs.getString("POS_DIPLOME_POSSEDER");
            int coef = rs.getInt("POS_COEFPRESCRIPTION_POSSEDER");
            
            
                    return new CSpePossede(diplome, coef, specialite1);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CSpePossede.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CSpePossede> lireSpePossedes() {
        if (bdd.connecter() == true) {
            ArrayList<CSpePossede> listeDeSpePossede = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `POSSEDER`");
            try {
                while (rs.next()) {
                    CSpePossede SpePossede = convertir_RS_SpePossede(rs);
                    listeDeSpePossede.add(SpePossede);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeSpePossede;
        } else {
            System.out.println("Connexion manipBdd.POSSEDER KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines SpéPosseder,
     //en fonction de l'id du praticien
     
     public ArrayList<CSpePossede> lire1SpePossede(String numeroPraticien) {
        if (bdd.connecter() == true) {
            ArrayList<CSpePossede> liste1SpePossede = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `POSSEDER` WHERE PRA_NUM_PRATICIEN = '" + numeroPraticien +"' ;");
            try {
                
               
                while (rs.next()) {
                    CSpePossede SpePossede = convertir_RS_SpePossede(rs);
                    liste1SpePossede.add(SpePossede);
                    
                }
               if(liste1SpePossede.isEmpty()){
                   System.out.println("POSSEDER Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1SpePossede;
        } else {
            System.out.println("Connexion manipBdd.POSSEDER KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de SpePosseder selectionnées.
     
     
     public void printSpePossede(ArrayList<CSpePossede> liste){
         liste.forEach((liste1) -> {
             System.out.println("Diplome : " +liste1.getDiplome()+ ". Coeff : "+liste1.getCoeffPrescription() +". Specialite  : " + liste1.getSpecialite().getLibelle()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableSpePossede tableSpePossede = new CTableSpePossede();
//         CSpecialite spec = new CSpecialite(3, "Chomeur");
//         CTypePraticien typePra = new CTypePraticien(1, "Medecin", "Hospice");
//         CSpePossede spePossede = new CSpePossede("NTM", 0, spec);
//         List<CSpePossede> list = new ArrayList();
//         list.add(spePossede);
//         CPraticien praticien = new CPraticien(1, "Merou", "Henry", new CAdresse("13", "rue de l eglise", "22150", "Ploeuc"), typePra, list, 2);
         //tableSpePossede.insererSpePossede(spePossede,praticien);
         //tableSpePossede.supprimerSpePossede("1","3");
         //tableSpePossede.modifierSpePossede(spePossede, praticien);
         //tableSpePossede.printSpePossede(tableSpePossede.lire1SpePossede("1", "3"));
         //tableSpePossede.printSpePossede(tableSpePossede.lireSpePossedes());
//         CTableSpePossede tableSpePossede = new CTableSpePossede();
//         ArrayList<CSpecialite> listSpe = new CTableSpecialite().lireSpecialites();
//         
//         ArrayList<CPraticien> listPra = new CTablePraticien().lirePraticiens();
//         
//         for(int x = 0;x <= 60;x++){
//         tableSpePossede.insererSpePossede(new CSpePossede("Docteur", (int)(Math.random() * ((10) + 1)), listSpe.get((int)(Math.random() * ((6) + 1)))),listPra.get(x));
//         }
     }
   
    
}
