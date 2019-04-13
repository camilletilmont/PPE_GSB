/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CEchantillon;
import Donnees.CFrais;
import Donnees.CMedicament;
import Donnees.CRapportVisite;
import Donnees.CPraticien;
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
public class CTableRapportVisite {
      //attributs
    protected ArrayList<CRapportVisite> listeRapportVisite;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CRapportVisite> getListeRapportVisite() {
        return listeRapportVisite;
    }

    public void setListeFrais(ArrayList<CRapportVisite> listeRapportVisite) {
        this.listeRapportVisite = listeRapportVisite;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableRapportVisite(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE RAPPORT_VISITE (RAP_NUM_RAPPORT_VISITE SMALLINT  AUTO_INCREMENT NOT NULL,"
                + " RAP_DATE_RAPPORT_VISITE DATE,"
                + " RAP_BILAN_RAPPORT_VISITE TEXT,"
                + " RAP_MOTIF_RAPPORT_VISITE VARCHAR(50),"
                + " VIS_MATRICULE_VISITEUR CHAR(4) NOT NULL,"
                + " PRA_NUM_PRATICIEN SMALLINT NOT NULL,"
                + " PRIMARY KEY (RAP_NUM_RAPPORT_VISITE) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.RAPPORT_VISITE KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`RAPPORT_VISITE`;";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.RAPPORT_VISITE KO");
        }
        return res;
    }
     
     //insértion d'un RapportVisite
     
     public int insererRapportVisite(CRapportVisite rapportVisite) {
        String req = "INSERT INTO `RAPPORT_VISITE` (`RAP_DATE_RAPPORT_VISITE`,"
                + " `RAP_BILAN_RAPPORT_VISITE`,`RAP_MOTIF_RAPPORT_VISITE`,`VIS_MATRICULE_VISITEUR`,`PRA_NUM_PRATICIEN`)"
                + " VALUES ('"+new Date(rapportVisite.getDateRapport().getTimeInMillis())+"',"
                + " '"+rapportVisite.getBilanRapport()+"',"
                + " '"+rapportVisite.getMotifRapport()+"',"
                + " '"+rapportVisite.getVisiteurRapport().getMatricule()+"',"
                + " '"+rapportVisite.getPraticienRapport().getIdPraticien()+"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.RAPPORT_VISITE KO");
        }
        return res;
    }
     
     
//     //modification d'un RapportVisite
     
     public int modifierRapportVisite(CRapportVisite rapportVisite) {

        String req = "UPDATE RAPPORT_VISITE SET "
                + "RAP_NUM_RAPPORT_VISITE = '" + rapportVisite.getIdRapportVisite() + "', "
                + "RAP_DATE_RAPPORT_VISITE = '"+ new Date(rapportVisite.getDateRapport().getTimeInMillis()) + "', "
                + "RAP_BILAN_RAPPORT_VISITE = '"+ rapportVisite.getBilanRapport()+"', "
                + "RAP_MOTIF_RAPPORT_VISITE = '"+ rapportVisite.getMotifRapport()+"', "
                + "VIS_MATRICULE_VISITEUR = '"+ rapportVisite.getVisiteurRapport().getMatricule()+"', "
                + "PRA_NUM_PRATICIEN = '"+ rapportVisite.getPraticienRapport().getIdPraticien()+"' "
                + " WHERE RAP_NUM_RAPPORT_VISITE = '"+ rapportVisite.getIdRapportVisite() + "' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.RAPPORT_VISITE KO");
        }
        return res;
    }
     
     
     //suppression d'un RapportVisite
     
     public int supprimerRapportVisite(String colone, String data) {
        String req = "DELETE FROM RAPPORT_VISITE WHERE "+colone +" = '" + data +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.RAPPORT_VISITE KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CRapportVisite convertir_RS_RapportVisite(ResultSet rs) {
        try {
            
            int idRapport = rs.getInt("RAP_NUM_RAPPORT_VISITE");
            String bilan = rs.getString("RAP_BILAN_RAPPORT_VISITE");
            String motif = rs.getString("RAP_MOTIF_RAPPORT_VISITE");
            
            GregorianCalendar gb = new GregorianCalendar();
            gb.setTime(Date.valueOf(rs.getString("RAP_DATE_RAPPORT_VISITE")));
           
           //objet Visiteur
           CTableVisiteur tableVisiteur= new CTableVisiteur();
           CVisiteur visit = tableVisiteur.lire1Visiteurs("VIS_MATRICULE_VISITEUR", rs.getString("VIS_MATRICULE_VISITEUR")).get(0);
            
           //objet Praticien
           CTablePraticien tablePraticien = new CTablePraticien();
           CPraticien praticien = tablePraticien.lire1Praticiens("PRA_NUM_PRATICIEN", rs.getString("PRA_NUM_PRATICIEN")).get(0);
           

           //liste Echantillon
            CTableEchantillon tableEchan = new CTableEchantillon();
            ArrayList<CEchantillon> listEchan = tableEchan.lire1Echantillon(rs.getString("RAP_NUM_RAPPORT_VISITE"));
            
            
            //liste Medicament
            //CTableMedicament tableMedoc = new CTableMedicament();
            ArrayList<CMedicament> listMedoc = new ArrayList();
            
                    return new CRapportVisite(idRapport, gb, bilan, motif, visit, praticien, listEchan, listMedoc);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CFrais.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CRapportVisite> lireRapportVisite() {
        if (bdd.connecter() == true) {
            ArrayList<CRapportVisite> listeRapports = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `RAPPORT_VISITE`");
            try {
                while (rs.next()) {
                    CRapportVisite RapportVisite = convertir_RS_RapportVisite(rs);
                    listeRapports.add(RapportVisite);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeRapports;
        } else {
            System.out.println("Connexion manipBdd.RAPPORT_VISITE KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains RapportVisites,
     //en fonction l'information souhaitée.
     
     public ArrayList<CRapportVisite> lire1RapportVisite(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CRapportVisite> liste1Rapport = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `RAPPORT_VISITE` WHERE " + column + " = '" + data + "' ORDER BY RAP_DATE_RAPPORT_VISITE DESC;");
            try {
                
               
                while (rs.next()) {
                    CRapportVisite rapportVisite = convertir_RS_RapportVisite(rs);
                    liste1Rapport.add(rapportVisite);
                    
                }
               if(liste1Rapport.isEmpty()){
                   System.out.println("RAPPORT_VISITE Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1Rapport;
        } else {
            System.out.println("Connexion manipBdd.RAPPORT_VISITE KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de rapports selectionnés.
     
     
     public void printRapportVisite(ArrayList<CRapportVisite> liste){
         liste.forEach((liste1) -> {
             System.out.println("Bilan : " +liste1.getBilanRapport() + ". Id : "+liste1.getIdRapportVisite()+". Nom Praticien : "+liste1.getPraticienRapport().getNom() + ". Nom Visiteur : " + liste1.getVisiteurRapport().getNom());
        liste1.getListeEchantillonRapport().forEach((listeEchan) -> {
             System.out.println("Nom Echantillon: " + listeEchan.getMedicamentEchantillon().getNomCommercial()+ ".");
         
         });
       
         });
     
     }
     
     public static void main(String[] args) {
     
//         CTableRapportVisite tableRapportVisite = new CTableRapportVisite();
//         
//         CTableVisiteur tableVisit = new CTableVisiteur();
//         CVisiteur visit = tableVisit.lire1Visiteurs("VIS_MATRICULE_VISITEUR", "V001").get(0);
//         
//         CTablePraticien tablePrat = new CTablePraticien();
//         CPraticien prati = tablePrat.lire1Praticiens("PRA_NUM_PRATICIEN", "1").get(0);
//         
//         CTableValeurComposant tableConstituer = new CTableValeurComposant();
//           ArrayList<CValeurComposant> listValCompo = new ArrayList();
//           
//           CTableEchantillon tableEchantillon = new CTableEchantillon();
//           //ArrayList<CEchantillon> listEchant = tableEchantillon.lire1Echantillon("1");
//           ArrayList<CEchantillon> listEchant = new ArrayList();
//           ArrayList<CMedicament> listMed = new ArrayList();
//           
//           
//         CRapportVisite rapportVisit = new CRapportVisite(3, new GregorianCalendar(), "Oh Hell No", "new medoc", visit, prati, listEchant, listMed);
         //tableRapportVisite.insererRapportVisite(rapportVisit);
         //tableRapportVisite.supprimerRapportVisite("RAP_NUM_RAPPORT_VISITE", "2");
         //tableRapportVisite.modifierRapportVisite(rapportVisit);
         //tableRapportVisite.printRapportVisite(tableRapportVisite.lire1RapportVisite("RAP_NUM_RAPPORT_VISITE", "1"));
         //tableRapportVisite.printRapportVisite(tableRapportVisite.lireRapportVisite());
}
}
