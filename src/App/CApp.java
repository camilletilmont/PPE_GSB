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
 
    public static void main(String[] argv) {
        CMetierRV metierRV = new CMetierRV();
        JFrameRapportVisite fenetreApp = new JFrameRapportVisite();
        fenetreApp.setVisible(true);
        fenetreApp.getjPanelConnexion2().setMetierConnexion(metierRV);
        fenetreApp.getjPanelListeRapport1().setMetierListeRV(metierRV);
        fenetreApp.getjPanelDetailRapport2().setMetierDetailRV(metierRV);
        fenetreApp.getjTabbedPane2().setEnabledAt(1, false);
        fenetreApp.getjTabbedPane2().setBackgroundAt(1, Color.darkGray);
        fenetreApp.getjTabbedPane2().setForegroundAt(1, Color.LIGHT_GRAY);
        fenetreApp.getjTabbedPane2().setEnabledAt(2, false);
        fenetreApp.getjTabbedPane2().setBackgroundAt(2, Color.darkGray);
        fenetreApp.getjTabbedPane2().setForegroundAt(2, Color.LIGHT_GRAY);
    }
    
}
