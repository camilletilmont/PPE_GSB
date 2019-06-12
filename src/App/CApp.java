/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import IHM.JFrameRapportVisite;
import Service.CMetierRV;
import java.awt.Color;

/**
 *
 * @author camilletilmont
 */
public class CApp {
 
    
    //classe qui lance l'application entière
    
    public static void main(String[] argv) {
        
        
        //création de l'objet metier
        CMetierRV metierRV = new CMetierRV();
        
        //création de la fenêtre
        JFrameRapportVisite fenetreApp = new JFrameRapportVisite();
        
        //rend la fenêtre visible
        fenetreApp.setVisible(true);
        
        //liaison entre l'objet metier créé et les différents panels
        fenetreApp.getjPanelConnexion2().setMetierConnexion(metierRV);
        fenetreApp.getjPanelListeRapport1().setMetierListeRV(metierRV);
        fenetreApp.getjPanelDetailRapport2().setMetierDetailRV(metierRV);
        
        //mise en forme du visuel pour la connexion au lancement
        fenetreApp.getjTabbedPane2().setEnabledAt(1, false);
        fenetreApp.getjTabbedPane2().setBackgroundAt(1, Color.darkGray);
        fenetreApp.getjTabbedPane2().setForegroundAt(1, Color.LIGHT_GRAY);
        fenetreApp.getjTabbedPane2().setEnabledAt(2, false);
        fenetreApp.getjTabbedPane2().setBackgroundAt(2, Color.darkGray);
        fenetreApp.getjTabbedPane2().setForegroundAt(2, Color.LIGHT_GRAY);
    }
    
}
