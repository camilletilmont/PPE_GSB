/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CFicheFrais;
import Donnees.CFrais;
import Donnees.CTypeFrais;
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
public class CTableFrais {
    //attributs
    protected ArrayList<CFrais> listeFrais;
    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CFrais> getListeFrais() {
        return listeFrais;
    }

    public void setListeFrais(ArrayList<CFrais> listeFrais) {
        this.listeFrais = listeFrais;
    }
    
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTableFrais(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE INCLURE (TF_CODE_TYPE_FRAIS TINYINT  AUTO_INCREMENT NOT NULL,"
                + " FF_MOIS_FICHE_FRAIS TINYINT(2) NOT NULL,"
                + " INC_QTE_INCLURE TINYINT,"
                + " INC_MONTANT_INCLURE FLOAT,"
                + " PRIMARY KEY (TF_CODE_TYPE_FRAIS, FF_MOIS_FICHE_FRAIS) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INCLURE KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`INCLURE`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INCLURE  KO");
        }
        return res;
    }
     
     //insértion d'un Frais
     
     public int insererFrais(CFrais frais,CFicheFrais ficheFrais) {
        String req = "INSERT INTO `INCLURE` (`TF_CODE_TYPE_FRAIS`,`FF_MOIS_FICHE_FRAIS`,`VIS_MATRICULE_VISITEUR`, `INC_QTE_INCLURE`,`INC_MONTANT_INCLURE`)"
                + " VALUES ('"+frais.gettypeFrais().getCode()+"',"
                + " '"+ficheFrais.getMois()+"',"
                + " '"+ficheFrais.getMatriculeVisit()+"',"
                + " '"+frais.getQuantiteFrais()+"',"
                + " '"+frais.getMontantFrais() +"');" ;
         
       int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INCLURE KO");
        }
        return res;
    }
     
     
//     //modification d'un frais selon le type de frais et le mois et le matricule liés a la fiche de frais
     
     public int modifierFrais(CFrais frais,CFicheFrais ficheFrais) {

        String req = "UPDATE INCLURE SET "
                + "TF_CODE_TYPE_FRAIS = '" + frais.gettypeFrais().getCode() + "', "
                + "FF_MOIS_FICHE_FRAIS = '"+ ficheFrais.getMois() + "', "
                + "VIS_MATRICULE_VISITEUR = '"+ ficheFrais.getMatriculeVisit()+"', "
                + "INC_QTE_INCLURE = '"+ frais.getQuantiteFrais() + "', "
                + "INC_MONTANT_INCLURE = '"+ frais.getMontantFrais() + "' "
                + " WHERE TF_CODE_TYPE_FRAIS = '"+ frais.gettypeFrais().getCode() + "' AND FF_MOIS_FICHE_FRAIS = '"+ficheFrais.getMois() +"' AND VIS_MATRICULE_VISITEUR = '"+ ficheFrais.getMatriculeVisit()+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INCLURE KO");
        }
        return res;
    }
     
     
     //suppression d'un frais selon le type de frais et le mois et le matricule liés a la fiche de frais
     
     public int supprimerFrais(String codeFr,String mois,String matricule) {
        String req = "DELETE FROM INCLURE WHERE TF_CODE_TYPE_FRAIS = '" + codeFr +"' AND FF_MOIS_FICHE_FRAIS = '"+ mois +"' AND VIS_MATRICULE_VISITEUR = '"+ matricule+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.INCLURE KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CFrais convertir_RS_Frais(ResultSet rs) {
        try {
            
            String code = rs.getString("TF_CODE_TYPE_FRAIS");
            
            CTableTypeFrais lireTypeF = new CTableTypeFrais();
            ArrayList<CTypeFrais> tab =  lireTypeF.lire1TypeFrais("TF_CODE_TYPE_FRAIS",code);
            CTypeFrais typeF = tab.get(0);
            
            
            int quantite = rs.getInt("INC_QTE_INCLURE");
            float montant = rs.getFloat("INC_MONTANT_INCLURE");
            
                    return new CFrais(quantite, montant, typeF);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CFrais.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CFrais> lireFrais() {
        if (bdd.connecter() == true) {
            ArrayList<CFrais> listeDeFrais = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `INCLURE`");
            try {
                while (rs.next()) {
                    CFrais Frais = convertir_RS_Frais(rs);
                    listeDeFrais.add(Frais);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDeFrais;
        } else {
            System.out.println("Connexion manipBdd.INCLURE KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains frais,
     //en fonction du mois et du matricule liés à la fiche de frais
     
     public ArrayList<CFrais> lire1Frais(int dataMois,String dataMat) {
        if (bdd.connecter() == true) {
            ArrayList<CFrais> liste1Frais = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `INCLURE` WHERE FF_MOIS_FICHE_FRAIS = '" + dataMois + "' AND VIS_MATRICULE_VISITEUR = '" + dataMat + "' ;");
            try {
                
               
                while (rs.next()) {
                    CFrais Frais = convertir_RS_Frais(rs);
                    liste1Frais.add(Frais);
                    
                }
               if(liste1Frais.isEmpty()){
                   System.out.println("INCLURE Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return liste1Frais;
        } else {
            System.out.println("Connexion manipBdd.INCLURE KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de frais sélectionnés
     
     
     public void printFrais(ArrayList<CFrais> liste){
         liste.forEach((liste1) -> {
             System.out.println("Quantite : " +liste1.getQuantiteFrais() + ". Montant: "+liste1.getMontantFrais() +". Type  : " + liste1.gettypeFrais().getNom()+".");
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTableFrais tableFrais = new CTableFrais();
//         CTypeFrais typeFr = new CTypeFrais("2", "Wesh", 34.3f);
//  
//         CFrais frais = new CFrais(3, 3.7f, typeFr);
//         List<CFrais> list = new ArrayList();
//         list.add(frais);
//         CFicheFrais ficheFr = new CFicheFrais(3, 4, 230.60f, "V001", list );
//         tableFrais.insererFrais(frais,ficheFr);
         //tableFrais.supprimerFrais("1","3","V001");
         //tableFrais.modifierFrais(frais,ficheFr);
         //tableFrais.printFrais(tableFrais.lire1Frais("TF_CODE_TYPE_FRAIS", "2"));
         //tableFrais.printFrais(tableFrais.lireFrais());
     }
   
    
}
