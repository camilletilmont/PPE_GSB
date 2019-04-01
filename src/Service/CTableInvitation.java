/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CActiviteCompl;
import Donnees.CAdresse;
import Donnees.CPraticien;
import Donnees.CInvitation;
import Donnees.CSpePossede;
import Donnees.CSpecialite;
import Donnees.CTypePraticien;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilletilmont
 */
public class CTableInvitation {
    //attributs
    protected ArrayList<CInvitation> listeInvitation;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CInvitation> getListeInvitation() {
        return listeInvitation;
    }

    public void setListeInvitation(ArrayList<CInvitation> listeInvitation) {
        this.listeInvitation = listeInvitation;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableInvitation(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE INVITER (AC_NUM_ACTIVITE_COMPL SMALLINT  AUTO_INCREMENT NOT NULL,"
                + " PRA_NUM_PRATICIEN SMALLINT NOT NULL,"
                + " SPECIALISTEON_INVITER VARCHAR(50),"
                + " PRIMARY KEY (AC_NUM_ACTIVITE_COMPL,"
                + " PRA_NUM_PRATICIEN) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INVITER KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`INVITER`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INVITER KO");
        }
        return res;
    }
     
     //insértion d'une Invitation
     
     public int insererInvitation(CInvitation invitation,CActiviteCompl activite) {
        String req = "INSERT INTO `INVITER` (`AC_NUM_ACTIVITE_COMPL`,`PRA_NUM_PRATICIEN`,`SPECIALISTEON_INVITER`)"
                + " VALUES ('"+activite.getIdACtCompl()+"',"
                + " '"+invitation.getPraticien().getIdPraticien()+"',"
                + " '"+invitation.getSpecialisteOn()+"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INVITER KO");
        }
        return res;
    }
     
     
//     //modification d'une Invitation selon l'id activité et l'id praticien
     
     public int modifierInvitation(CInvitation invitation,CActiviteCompl activite) {

        String req = "UPDATE INVITER SET "
                + "AC_NUM_ACTIVITE_COMPL = '" + activite.getIdACtCompl() + "', "
                + "PRA_NUM_PRATICIEN = '"+ invitation.getPraticien().getIdPraticien() + "', "
                + "SPECIALISTEON_INVITER = '"+ invitation.getSpecialisteOn()+"' "
                + " WHERE AC_NUM_ACTIVITE_COMPL = '"+ activite.getIdACtCompl() + "' AND PRA_NUM_PRATICIEN = '"+ invitation.getPraticien().getIdPraticien() +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INVITER KO");
        }
        return res;
    }
     
     
     //suppression d'une Invitation selon l'id activité et l'id praticien
     
     public int supprimerInvitation(String numActi,String numPrati) {
        String req = "DELETE FROM INVITER WHERE AC_NUM_ACTIVITE_COMPL = '" + numActi +"' AND PRA_NUM_PRATICIEN = '"+ numPrati +"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INVITER KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CInvitation convertir_RS_Invitation(ResultSet rs) {
        try {
            
            String codePra = rs.getString("PRA_NUM_PRATICIEN");
            
            CTablePraticien lirePrat = new CTablePraticien();
            ArrayList<CPraticien> tab =  lirePrat.lire1Praticiens("PRA_NUM_PRATICIEN",codePra);
            CPraticien praticien1 = tab.get(0);
            
            String spe = rs.getString("SPECIALISTEON_INVITER");
            
            
                    return new CInvitation(praticien1, spe);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CInvitation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CInvitation> lireInvitations() {
        if (bdd.connecter() == true) {
            ArrayList<CInvitation> listeDeInvitation = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `INVITER`");
            try {
                while (rs.next()) {
                    CInvitation Invitation = convertir_RS_Invitation(rs);
                    listeDeInvitation.add(Invitation);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeInvitation;
        } else {
            System.out.println("Connexion manipBdd.INVITER KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certaines invitations,
     //en fonction l'information souhaitée.
     
     public ArrayList<CInvitation> lire1Invitation(String colone, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CInvitation> liste1Invitation = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `INVITER` WHERE "+ colone +" = '" + data +"' ;");
            try {
                
               
                while (rs.next()) {
                    CInvitation Invitation = convertir_RS_Invitation(rs);
                    liste1Invitation.add(Invitation);
                    
                }
               if(liste1Invitation.isEmpty()){
                   System.out.println("INVITER Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1Invitation;
        } else {
            System.out.println("Connexion manipBdd.INVITER KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste d'invitations selectionnées.
     
     
     public void printInvitation(ArrayList<CInvitation> liste){
         liste.forEach((liste1) -> {
             System.out.println("Nom : " +liste1.getPraticien().getNom()+ ". Specialiste On : "+liste1.getSpecialisteOn() +".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableInvitation tableInvitation = new CTableInvitation();
//         CSpecialite spec = new CSpecialite(3, "Chomeur");
//         CSpePossede spePoss = new CSpePossede("Docteur", 3, spec);
//         CTypePraticien typePra = new CTypePraticien(1, "Medecin", "Hospice");
//         
//         List<CSpePossede> listSpe = new ArrayList();
//         listSpe.add(spePoss);
//         
//         CPraticien praticien = new CPraticien(2, "Einsten", "Albert", new CAdresse("3", "rue de michel", "22150", "Ploeuc"), typePra, listSpe, 0);
//         CInvitation invitation = new CInvitation(praticien,"Entropie");
//         
//         CTableActiviteCompl tabAct = new CTableActiviteCompl();
//         CActiviteCompl act = tabAct.lire1ActiviteCompl("AC_NUM_ACTIVITE_COMPL", "1").get(0);
         //tableInvitation.insererInvitation(invitation,act);
         //tableInvitation.supprimerInvitation("1","2");
         //tableInvitation.modifierInvitation(invitation, act);
         //tableInvitation.printInvitation(tableInvitation.lire1Invitation("2", "1"));
         //tableInvitation.printInvitation(tableInvitation.lireInvitations());
     }
}
