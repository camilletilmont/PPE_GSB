/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CAdresse;
import Donnees.CSpecialite;
import Donnees.CPraticien;
import Donnees.CSpePossede;
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
public class CTablePraticien {
     //attributs.
    
     protected ArrayList<CPraticien> listePraticiens;
     protected CTableSpePossede tableSpePoss = new CTableSpePossede();

    protected CBDD bdd;
    
    
    //getter et setter
    public ArrayList<CPraticien> getListePraticiens() {
        return listePraticiens;
    }

    public void setListePraticiens(ArrayList<CPraticien> listePraticiens) {
        this.listePraticiens = listePraticiens;
    }
    
    
    public CTableSpePossede getTableSpePoss() {
        return tableSpePoss;
    }

    public void setTableSpePoss(CTableSpePossede tableSpePoss) {
        this.tableSpePoss = tableSpePoss;
    }
    
      public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //constructeur de base modifié afin qu'il soit directement lié à notre base
    //de données dont les détails de connexions sont dans le fichier properties
    
    public CTablePraticien(){
    this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    
    }
    
   
    //methodes questionnant et modificant la table par des requetes SQL
    
    
    
    //création d'une table si non existante.
    
     public int creerTable() {
        String req = "CREATE TABLE PRATICIEN (PRA_NUM_PRATICIEN SMALLINT  AUTO_INCREMENT NOT NULL,"
                + " PRA_NOM_PRATICIEN VARCHAR(25),"
                + " PRA_PRENOM_PRATICIEN VARCHAR(25),"
                + " PRA_ADRESSE_PRATICIEN VARCHAR(100),"
                + " PRA_CP_PRATICIEN VARCHAR(5),"
                + " PRA_VILLE_PRATICIEN VARCHAR(45),"
                + " PRA_COEFNOTORIETE_PRATICIEN FLOAT,"
                + " TYP_CODE_TYPE_PRATICIEN TINYINT(3),"
                + " PRIMARY KEY (PRA_NUM_PRATICIEN) ) ENGINE=InnoDB;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRATICIEN KO");
        }
        return res;
    }
     
     
     //suppresion de la table si elle existe
     
     public int deleteTable() {
        String req = "DROP TABLE IF EXISTS `manipBdd`.`PRATICIEN`";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRATICIEN KO");
        }
        return res;
    }
     
     //insértion d'un Praticien
     
     public int insererPraticien(CPraticien praticien) {
        String req = "INSERT INTO `PRATICIEN` (`PRA_NOM_PRATICIEN`, `PRA_PRENOM_PRATICIEN`,"
                + "`PRA_ADRESSE_PRATICIEN`, `PRA_CP_PRATICIEN`,`PRA_VILLE_PRATICIEN`,`PRA_COEFNOTORIETE_PRATICIEN`,"
                + " `TYP_CODE_TYPE_PRATICIEN`)"
                + "VALUES ('"+praticien.getNom()+"',"
                + " '"+praticien.getPrenom()+"',"
                + " '"+praticien.getAdresse().getNumero() +","+ praticien.getAdresse().getRue()+"',"
                + " '"+praticien.getAdresse().getCodePostal()+"',"
                + " '"+praticien.getAdresse().getVille()+"',"
                + " '"+praticien.getCoeffNotoriete()+"',"
                + " '"+praticien.getTypePraticien().getIdType() +"');" ;
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRATICIEN KO");
        }
        return res;
    }
     
     
//     //modification d'un Praticien selon son Id
     
     public int modifierPraticien(CPraticien praticien) {

        String req = "UPDATE PRATICIEN SET "
                + "PRA_NUM_PRATICIEN = '" + praticien.getIdPraticien() + "', "
                + "PRA_NOM_PRATICIEN = '"+ praticien.getNom() + "', "
                + "PRA_PRENOM_PRATICIEN = '"+ praticien.getPrenom() + "', "
                + "PRA_ADRESSE_PRATICIEN = '" + praticien.getAdresse().getNumero() +","+ praticien.getAdresse().getRue() + "', "
                + "PRA_CP_PRATICIEN = '"+ praticien.getAdresse().getCodePostal() + "', "
                + "PRA_VILLE_PRATICIEN = '"+ praticien.getAdresse().getVille() + "', "
                + "PRA_COEFNOTORIETE_PRATICIEN = '" + praticien.getCoeffNotoriete() + "', "
                + "TYP_CODE_TYPE_PRATICIEN = '"+ praticien.getTypePraticien().getIdType() + "' "
                + " WHERE PRA_NUM_PRATICIEN = '"+ praticien.getIdPraticien() + "';";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRATICIEN KO");
        }
        return res;
    }
     
     
     //suppression d'un Praticien selon son Id
     
     public int supprimerPraticien(String x) {
        String req = "DELETE FROM PRATICIEN WHERE PRA_NUM_PRATICIEN = '" + x+"' ;";
        
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipBdd.PRATICIEN KO");
        }
        return res;
    }
     
     
     //methode afin de convertire les données récupérées de la table
     
     CPraticien convertir_RS_Praticien(ResultSet rs) {
        try {
            int id = rs.getInt("PRA_NUM_PRATICIEN");
            String nom = rs.getString("PRA_NOM_PRATICIEN");
            String prenom = rs.getString("PRA_PRENOM_PRATICIEN");
            String address = rs.getString("PRA_ADRESSE_PRATICIEN");
            int ind = address.indexOf(",");
            String num = address.substring(0, ind);
            String rue = address.substring(ind + 1);
            String codePostal = rs.getString("PRA_CP_PRATICIEN");
            String ville = rs.getString("PRA_VILLE_PRATICIEN");
           int coefNoto = rs.getInt("PRA_COEFNOTORIETE_PRATICIEN");
           
           CTableTypePraticien tableTypePra = new CTableTypePraticien();
           ArrayList<CTypePraticien> listeTypePra = tableTypePra.lire1TypePraticiens("TYP_CODE_TYPE_PRATICIEN", rs.getString("TYP_CODE_TYPE_PRATICIEN"));
           CTypePraticien typePra = listeTypePra.get(0);
           
           ArrayList<CSpePossede> listeSpe = tableSpePoss.lire1SpePossede(rs.getString("PRA_NUM_PRATICIEN"));
            
                    return new CPraticien(id, nom, prenom, new CAdresse(num, rue, codePostal, ville), typePra, listeSpe, coefNoto);
      
           
        } catch (SQLException ex) {
            Logger.getLogger(CPraticien.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
     
     
     //methode permettant de lire toutes les entrées de la table
     
     public ArrayList<CPraticien> lirePraticiens() {
        if (bdd.connecter() == true) {
            ArrayList<CPraticien> listeDePraticiens = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `PRATICIEN`");
            try {
                while (rs.next()) {
                    CPraticien Praticien = convertir_RS_Praticien(rs);
                    listeDePraticiens.add(Praticien);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return listeDePraticiens;
        } else {
            System.out.println("Connexion manipBdd.PRATICIEN KO");
        }
        return null;
    }
     
     //methode permettant de lire certaines entrées de la table, certains praticiens,
     //en fonction l'information souhaitée.
     
     public ArrayList<CPraticien> lire1Praticiens(String column, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CPraticien> listePraticien = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `PRATICIEN` WHERE " + column + " = '" + data + "';");
            try {
                
               
                while (rs.next()) {
                    CPraticien Praticien = convertir_RS_Praticien(rs);
                    listePraticien.add(Praticien);
                    
                }
               if(listePraticien.isEmpty()){
                   System.out.println("PRATICIEN Not Found !");
               
               }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            
            return listePraticien;
        } else {
            System.out.println("Connexion manipBdd.PRATICIEN KO");
        }
        return null;
    }
    
     //methode permettant d'afficher une liste de Praticiens selectionnés.
     
     
     public void printPraticiens(ArrayList<CPraticien> liste){
         liste.forEach((liste1) -> {
             System.out.println("Adresse : " +liste1.getAdresse().getNumero() + ". Prenom : "+liste1.getPrenom() +". Type : " + liste1.getTypePraticien().getLibelle()+".");
              liste1.getSpecialiteList().forEach((listeSpe) -> {
             System.out.println("Nom titre : " + listeSpe.getDiplome() + ". Nom Spe : " + listeSpe.getSpecialite().getLibelle());
         
         });
        });
     
     }
     
     public static void main(String[] args) {
     
//         CTablePraticien tablePraticien = new CTablePraticien();
//         ArrayList<CSpePossede> speList = new ArrayList();
//         CSpecialite spe = new CSpecialite(1, "Urologue");
//         CSpePossede spePoss = new CSpePossede("Docteur", 3, spe);
//         speList.add(spePoss);
//         
//         CPraticien praticien = new CPraticien(2, "Einstein", "Albert", new CAdresse("3", "rue de michel", "22150", "Ploeuc"), new CTypePraticien(1, "Medecin", "Hospice"), speList, 20);
//         
         //tablePraticien.insererPraticien(praticien);
         //tablePraticien.supprimerPraticien("2");
         //tablePraticien.modifierPraticien(praticien);
         //tablePraticien.printPraticiens(tablePraticien.lire1Praticiens("PRA_NOM_PRATICIEN", "Merou"));
         //tablePraticien.printPraticiens(tablePraticien.lirePraticiens());
         
         
         String nom[] = {"Matisse", "Van Huten", "Copola", "Spinrad", "Douchet", "Azerty", "Van Gogh", "De Vinci", "Curiosity", "Demeter"};
         String prenom[] = {"Jacques", "Henrie", "Franck", "Viviane", "Mathias", "Georgette", "Clayton", "Blouge", "Elijah", "Anna"};
         String typeRue[] = {"rue", "avenue", "boulevard", "venelle", "route", "chemin", "place", "square", "boulevard", "carrefour"};
         String nomRue[] = {"clos clochet", "de marmonette", "Klosto", "Ploula", "de la marine", "de l oublie", "des autres", "Joliot Curie", "avant", "Claude Sauterne"};
         String ville[] = {"Rennes", "Valence", "Fecamp", "MonJardin", "Alambra", "Blosasc", "La Trinite", "Saint Sauveur", "Quintin", "Treguier"};
         
         for(int x = 0; x < 20;x ++){
             CTablePraticien tablePraticien = new CTablePraticien();
             String name = nom[(int)(Math.random() * ((9) + 1))];
             String lastN = prenom[(int)(Math.random() * ((9) + 1))];
             String numeroRue = Integer.toString((int)(Math.random() * ((399) + 1)));
             String codeP = Integer.toString((int)(Math.random() * ((97000-10000) + 1)));
             String streetType = typeRue[(int)(Math.random() * ((9) + 1))];
             String streetName = nomRue[(int)(Math.random() * ((9) + 1))];
             String city = ville[(int)(Math.random() * ((9) + 1))];
             
             
             ArrayList<CTypePraticien> listPra = new CTableTypePraticien().lireTypePraticiens();
             
             ArrayList<CSpecialite> listSpe = new CTableSpecialite().lireSpecialites();
             ArrayList<CSpePossede> listPoss = new ArrayList<>();
             listPoss.add(new CSpePossede("Docteur", (int)(Math.random() * ((9) + 1)), listSpe.get((int)(Math.random() * ((6) + 1)))));
             
             
             int coef = (int)(Math.random() * ((100) + 1));
             
             CPraticien praticien = new CPraticien(1, name, lastN, new CAdresse(numeroRue, " "+streetType+" " +streetName, codeP, city), listPra.get((int)(Math.random() * ((6) + 1))), listPoss, coef);
             
             
             
             
             tablePraticien.insererPraticien(praticien);
             
         
         }
         
     }
    
    
    
}
