/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Donnees.CEchantillon;
import Donnees.CMedicament;
import Donnees.CRapportVisite;
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
public class CTableEchantillon {
     //attributs
    protected ArrayList<CEchantillon> listeEchantillon;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CEchantillon> getListeEchantillon() {
        return listeEchantillon;
    }

    public void setListeEchantillon(ArrayList<CEchantillon> listeEchantillon) {
        this.listeEchantillon = listeEchantillon;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableEchantillon(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE OFFRIR (RAP_NUM_RAPPORT_VISITE SMALLINT  AUTO_INCREMENT NOT NULL,"
                + " MED_DEPOTLEGAL_MEDICAMENT INT NOT NULL,"
                + " OFF_QTE_OFFRIR TINYINT,"
                + " PRIMARY KEY (RAP_NUM_RAPPORT_VISITE,"
                + " MED_DEPOTLEGAL_MEDICAMENT) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.OFFRIR KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`OFFRIR`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.OFFRIR  KO");
        }
        return res;
    }
     
     //insértion d'un Echantillon
     
     public int insererEchantillon(CEchantillon echantillon,CRapportVisite rapport) {
        String req = "INSERT INTO `OFFRIR` (`RAP_NUM_RAPPORT_VISITE`,`MED_DEPOTLEGAL_MEDICAMENT`,`OFF_QTE_OFFRIR`)"
                + " VALUES ('"+rapport.getIdRapportVisite()+"',"
                + " '"+echantillon.getMedicamentEchantillon().getDepotLegal()+"',"
                + " '"+echantillon.getQuantiteEchantillon()+"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.OFFRIR KO");
        }
        return res;
    }
     
     
//     //modification d'un Echantillon selon l'id du rapport et l'id du medicament
     
     public int modifierEchantillon(CEchantillon echantillon,CRapportVisite rapport) {

        String req = "UPDATE OFFRIR SET "
                + "RAP_NUM_RAPPORT_VISITE = '" + rapport.getIdRapportVisite() + "', "
                + "MED_DEPOTLEGAL_MEDICAMENT = '"+ echantillon.getMedicamentEchantillon().getDepotLegal() + "', "
                + "OFF_QTE_OFFRIR = '"+ echantillon.getQuantiteEchantillon()+"' "
                + " WHERE RAP_NUM_RAPPORT_VISITE = '"+ rapport/*.getIdRapportVisite()*/ + "' AND MED_DEPOTLEGAL_MEDICAMENT = '"+ echantillon.getMedicamentEchantillon().getDepotLegal() +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.OFFRIR KO");
        }
        return res;
    }
     
     
     //suppression d'un Echantillon selon l'id du rapport et l'id du medicament
     
     public int supprimerEchantillon(String idRapport,String depotMedoc) {
        String req = "DELETE FROM OFFRIR WHERE RAP_NUM_RAPPORT_VISITE = '" + idRapport +"' AND MED_DEPOTLEGAL_MEDICAMENT = '"+ depotMedoc +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.OFFRIR KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CEchantillon convertir_RS_Echantillon(ResultSet rs) {
        try {
            
            String depotMedic = rs.getString("MED_DEPOTLEGAL_MEDICAMENT");
            
            CTableMedicament tableMedic = new CTableMedicament();
            ArrayList<CMedicament> tab =  tableMedic.lire1Medicament("MED_DEPOTLEGAL_MEDICAMENT", depotMedic);
            CMedicament medic = tab.get(0);
            
            
            int quantite = rs.getInt("OFF_QTE_OFFRIR");
           
            
                    return new CEchantillon(medic, quantite);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CEchantillon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CEchantillon> lireEchantillon() {
        if (bdd.connecter() == true) {
            ArrayList<CEchantillon> listeDeEchantillon = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `OFFRIR`");
            try {
                while (rs.next()) {
                    CEchantillon Echantillon = convertir_RS_Echantillon(rs);
                    listeDeEchantillon.add(Echantillon);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeEchantillon;
        } else {
            System.out.println("Connexion manipBdd.OFFRIR KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains Echantillon,
     //en fonction du mois et du matricule liés à la fiche de Echantillon
     
     public ArrayList<CEchantillon> lire1Echantillon(String numRapport) {
        if (bdd.connecter() == true) {
            ArrayList<CEchantillon> liste1Echantillon = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `OFFRIR` WHERE RAP_NUM_RAPPORT_VISITE = '" + numRapport + "' ;");
            try {
                
               
                while (rs.next()) {
                    CEchantillon Echantillon = convertir_RS_Echantillon(rs);
                    liste1Echantillon.add(Echantillon);
                    
                }
               if(liste1Echantillon.isEmpty()){
                   System.out.println("OFFRIR Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1Echantillon;
        } else {
            System.out.println("Connexion manipBdd.OFFRIR KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Echantillon sélectionnés
     
     
     public void printEchantillon(ArrayList<CEchantillon> liste){
         liste.forEach((liste1) -> {
             System.out.println("Quantite : " +liste1.getQuantiteEchantillon() + ". Nom Medic : "+liste1.getMedicamentEchantillon().getNomCommercial()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableEchantillon tableEchantillon = new CTableEchantillon();
//         CTableMedicament tableMedic = new CTableMedicament();
//            ArrayList<CMedicament> tab =  tableMedic.lire1Medicament("MED_DEPOTLEGAL_MEDICAMENT", "123");
//            CMedicament medic = tab.get(0);
//  
//         CEchantillon echantillon = new CEchantillon(medic, 2);
//        
        
         //tableEchantillon.insererEchantillon(echantillon,2);
         //tableEchantillon.supprimerEchantillon("2", "124");
         //tableEchantillon.modifierEchantillon(echantillon, 2);
         //tableEchantillon.printEchantillon(tableEchantillon.lire1Echantillon("1"));
         //tableEchantillon.printEchantillon(tableEchantillon.lireEchantillon());
     }
}
