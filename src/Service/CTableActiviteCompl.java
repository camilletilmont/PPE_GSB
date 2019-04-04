/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CActiviteCompl;
import Donnees.CFrais;
import Donnees.CInvitation;
import Donnees.CVisiteur;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilletilmont
 */
public class CTableActiviteCompl {
    
      //attributs
    protected ArrayList<CActiviteCompl> listeActiviteCompl;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CActiviteCompl> getListeActiviteCompl() {
        return listeActiviteCompl;
    }

    public void setListeFrais(ArrayList<CActiviteCompl> listeActiviteCompl) {
        this.listeActiviteCompl = listeActiviteCompl;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableActiviteCompl(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE ACTIVITE_COMPL (AC_NUM_ACTIVITE_COMPL SMALLINT  AUTO_INCREMENT NOT NULL,"
                + " AC_DATE_ACTIVITE_COMPL DATETIME,"
                + " AC_LIEU_ACTIVITE_COMPL VARCHAR(50),"
                + " AC_THEME_ACTIVITE_COMPL VARCHAR(50),"
                + " PRIMARY KEY (AC_NUM_ACTIVITE_COMPL) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.ACTIVITE_COMPL KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`ACTIVITE_COMPL`;";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.ACTIVITE_COMPL KO");
        }
        return res;
    }
     
     //insértion d'une activitée
     
     public int insererActiviteCompl(CActiviteCompl activiteCompl) {
        String req = "INSERT INTO `ACTIVITE_COMPL` (`AC_NUM_ACTIVITE_COMPL`, `AC_DATE_ACTIVITE_COMPL`, `AC_LIEU_ACTIVITE_COMPL`,`AC_THEME_ACTIVITE_COMPL`)"
                + " VALUES ('"+ activiteCompl.getIdACtCompl()+"',"
                + " '"+new Date(activiteCompl.getDate().getTimeInMillis())+"',"
                + " '"+activiteCompl.getLieu()+"',"
                + " '"+ activiteCompl.getTheme() +"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.ACTIVITE_COMPL KO");
        }
        return res;
    }
     
     
//     //modification d'une activitée
     
     public int modifierActiviteCompl(CActiviteCompl activiteCompl) {

        String req = "UPDATE ACTIVITE_COMPL SET "
                + "AC_NUM_ACTIVITE_COMPL = '" + activiteCompl.getIdACtCompl() + "', "
                + "AC_DATE_ACTIVITE_COMPL = '"+ new Date(activiteCompl.getDate().getTimeInMillis()) + "', "
                + "AC_LIEU_ACTIVITE_COMPL = '"+ activiteCompl.getLieu()+"', "
                + "AC_THEME_ACTIVITE_COMPL = '"+ activiteCompl.getTheme() + "' "
                + " WHERE AC_NUM_ACTIVITE_COMPL = '"+ activiteCompl.getIdACtCompl() + "' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.ACTIVITE_COMPL KO");
        }
        return res;
    }
     
     
     //suppression d'une activitée
     
     public int supprimerActiviteCompl(String colone, String data) {
        String req = "DELETE FROM ACTIVITE_COMPL WHERE "+colone +" = '" + data +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.ACTIVITE_COMPL KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CActiviteCompl convertir_RS_ActiviteCompl(ResultSet rs) {
        try {

            int id = rs.getInt("AC_NUM_ACTIVITE_COMPL");
            
            GregorianCalendar gb = new GregorianCalendar();
            gb.setTime(Timestamp.valueOf(rs.getString("AC_DATE_ACTIVITE_COMPL")));
            
            
            String lieu = rs.getString("AC_LIEU_ACTIVITE_COMPL");
            String theme = rs.getString("AC_THEME_ACTIVITE_COMPL");

            String etat = "ok";
            
            
            
            CTableInvitation tableInvit = new CTableInvitation();
            ArrayList<CInvitation> listInvit = tableInvit.lire1Invitation("AC_NUM_ACTIVITE_COMPL", rs.getString("AC_NUM_ACTIVITE_COMPL"));
            
            CTableActiviteRealiser tableReal = new CTableActiviteRealiser();
            ArrayList<String> listMat = tableReal.lire1MatriculeRealiser(rs.getString("AC_NUM_ACTIVITE_COMPL"));
            CTableVisiteur tableVisit = new CTableVisiteur();
            ArrayList<CVisiteur> listVisit = new ArrayList();
            listMat.forEach((liste1) -> {
                    CVisiteur visit = tableVisit.lire1Visiteurs("VIS_MATRICULE_VISITEUR", liste1).get(0);
                    listVisit.add(visit);
            });
            
                    return new CActiviteCompl(id,gb,lieu,theme,listVisit,listInvit,etat);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CFrais.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CActiviteCompl> lireActiviteCompl() {
        if (bdd.connecter() == true) {
            ArrayList<CActiviteCompl> listeFicheDeFrais = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `ACTIVITE_COMPL`");
            try {
                while (rs.next()) {
                    CActiviteCompl ActiviteCompl = convertir_RS_ActiviteCompl(rs);
                    listeFicheDeFrais.add(ActiviteCompl);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeFicheDeFrais;
        } else {
            System.out.println("Connexion manipBdd.ACTIVITE_COMPL KO");
        }
        return null;
    }
     
     //methode permettant de lire une seule entrée de la table, une seule activité,
     //en fonction l'information souhaitée.
     
     public ArrayList<CActiviteCompl> lire1ActiviteCompl(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CActiviteCompl> liste1FicheF = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `ACTIVITE_COMPL` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CActiviteCompl ActiviteCompl = convertir_RS_ActiviteCompl(rs);
                    liste1FicheF.add(ActiviteCompl);
                    
                }
               if(liste1FicheF.isEmpty()){
                   System.out.println("Activité Complémentaire Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1FicheF;
        } else {
            System.out.println("Connexion manipBdd.ACTIVITE_COMPL KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste d'activités selectionnées.
     
     
     public void printActiviteCompl(ArrayList<CActiviteCompl> liste){
         liste.forEach((liste1) -> {
             System.out.println("id : " +liste1.getIdACtCompl() + ". Theme : "+liste1.getTheme() +".");
        liste1.getInvitationList().forEach((listeInvit) -> {
             System.out.println("Nom Prat : " + listeInvit.getPraticien().getNom() + ". SpeOn : " + listeInvit.getSpecialisteOn());
         
         });
         liste1.getVisiteurList().forEach((listVisit) -> {
             System.out.println("Nom Visiteur :" + listVisit.getNom()+ ".");
         
         });
         });
     
     }
     
     public static void main(String[] args) {
     
//         CTableActiviteCompl tableActiviteCompl = new CTableActiviteCompl();
//         List<CVisiteur> listVisit = new ArrayList();
//         CTableVisiteur tabVisit = new CTableVisiteur();
//         CVisiteur visit = tabVisit.lire1Visiteurs("VIS_MATRICULE_VISITEUR", "V001").get(0);
//         listVisit.add(visit);
//         CTableInvitation tabInvit = new CTableInvitation();
//         List<CInvitation> listInvit = tabInvit.lire1Invitation("AC_NUM_ACTIVITE_COMPL", "1");
//         CActiviteCompl activiteCompl = new CActiviteCompl(2, new GregorianCalendar(), "Marseille", "Nulachié", listVisit, listInvit, "ok");
//         
         //tableActiviteCompl.insererActiviteCompl(activiteCompl);
         //tableActiviteCompl.supprimerActiviteCompl("AC_NUM_ACTIVITE_COMPL", "2");
         //tableActiviteCompl.modifierActiviteCompl(activiteCompl);
         //tableActiviteCompl.printActiviteCompl(tableActiviteCompl.lire1ActiviteCompl("AC_NUM_ACTIVITE_COMPL", "1"));
         //tableActiviteCompl.printActiviteCompl(tableActiviteCompl.lireActiviteCompl());
}
}