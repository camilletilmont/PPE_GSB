/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import IHM.JFrameRapportVisite;
import Service.CMetierRV;

/**
 *
 * @author camilletilmont
 */
public class CApp {
 
    public static void main(String[] argv) {
        CMetierRV metierRV = new CMetierRV();
        JFrameRapportVisite fenetreApp = new JFrameRapportVisite();
        fenetreApp.setVisible(true);
        fenetreApp.getjPanelConnexion1().setMetierConnexion(metierRV);
        fenetreApp.getJPanelDetailRapport().setMetierDetailRV(metierRV);
    }
    
}