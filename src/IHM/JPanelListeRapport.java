/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Donnees.CRapportVisite;
import Service.CMetierRV;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author camilletilmont
 */
public class JPanelListeRapport extends javax.swing.JPanel {

    //classe du panneau du menu principal avec la liste des rapports
    
    public JPanelListeRapport() {
        initComponents();

    }
    //attributs
    protected CMetierRV metierListeRV;
    protected JTabbedPane panelParent;
    protected JPanelDetailRapport panelDetailRapport;
    protected JPanelConnexion panelCo;

    //getter et setter
    public JPanelConnexion getPanelCo() {
        return panelCo;
    }

    public void setPanelCo(JPanelConnexion panelCo) {
        this.panelCo = panelCo;
    }

    public JPanelDetailRapport getPanelDetailRapport() {
        return panelDetailRapport;
    }

    public void setPanelDetailRapport(JPanelDetailRapport panelDetailRapport) {
        this.panelDetailRapport = panelDetailRapport;
    }

    public JTabbedPane getPanelParent() {
        return panelParent;
    }

    public void setPanelParent(JTabbedPane panelParent) {
        this.panelParent = panelParent;
    }

    public CMetierRV getMetierListeRV() {
        return metierListeRV;
    }

    public void setMetierListeRV(CMetierRV metier) {
        this.metierListeRV = metier;
    }
    
     public JTable getListRapportsTab() {
        return listRapportsTab;
    }

    public void setListRapportsTab(JTable listRapportsTab) {
        this.listRapportsTab = listRapportsTab;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        listRapportsTab = new javax.swing.JTable();
        buttonNewRapport = new javax.swing.JButton();
        buttonLireRapport = new javax.swing.JButton();
        buttonDeconnexion = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();

        listRapportsTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Praticien", "Motif Visite", "Bilan", "Visiteur"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(listRapportsTab);

        buttonNewRapport.setText("Nouveau Rapport");
        buttonNewRapport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewRapportActionPerformed(evt);
            }
        });

        buttonLireRapport.setText("Lire Rapport");
        buttonLireRapport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLireRapportActionPerformed(evt);
            }
        });

        buttonDeconnexion.setText("Se Deconnecter");
        buttonDeconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeconnexionActionPerformed(evt);
            }
        });

        buttonRefresh.setText("Rafraichir");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonNewRapport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLireRapport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDeconnexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonNewRapport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonLireRapport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(314, 314, 314)
                        .addComponent(buttonDeconnexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    //action pour la création d'un nouveau rapport
    private void buttonNewRapportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewRapportActionPerformed
        

        getPanelDetailRapport().newRV();
        getPanelParent().setSelectedIndex(1);
        getPanelParent().setEnabledAt(0, false);
        getPanelParent().setBackgroundAt(0, Color.darkGray);
        getPanelParent().setForegroundAt(0, Color.LIGHT_GRAY);
        getPanelParent().setEnabledAt(1, false);
        getPanelParent().setBackgroundAt(1, Color.lightGray);
        getPanelParent().setForegroundAt(1, Color.white);
    }//GEN-LAST:event_buttonNewRapportActionPerformed

    
    //action pour la lecture d'un rapport selectionné au préalable dans la liste
    private void buttonLireRapportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLireRapportActionPerformed
       
        if(getListRapportsTab().getSelectedRow() != -1){
        CRapportVisite rapportSelect = getMetierListeRV().getListeRapportVisite().get(getListRapportsTab().getSelectedRow());
        getPanelDetailRapport().refreshRapportUnique(rapportSelect.getIdRapportVisite());

        getPanelParent().setSelectedIndex(1);
        getPanelParent().setEnabledAt(0, false);
        getPanelParent().setBackgroundAt(0, Color.darkGray);
        getPanelParent().setForegroundAt(0, Color.LIGHT_GRAY);
        getPanelParent().setEnabledAt(1, false);
        getPanelParent().setBackgroundAt(1, Color.lightGray);
        getPanelParent().setForegroundAt(1, Color.white);
        }
    }//GEN-LAST:event_buttonLireRapportActionPerformed

    
    //action pour la mise à jour du panneau
    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
       
        refresh();

    }//GEN-LAST:event_buttonRefreshActionPerformed

    
    
    //action pour la deconnexion avec retour a la page co
    private void buttonDeconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeconnexionActionPerformed
        

        DefaultTableModel model = (DefaultTableModel) getListRapportsTab().getModel();
        model.setRowCount(0);

        getPanelParent().insertTab("Connexion", null, panelCo, TOOL_TIP_TEXT_KEY, 0);
        getPanelParent().setEnabledAt(1, false);
        getPanelParent().setBackgroundAt(1, Color.darkGray);
        getPanelParent().setForegroundAt(1, Color.LIGHT_GRAY);
        getPanelParent().setEnabledAt(2, false);
        getPanelParent().setBackgroundAt(2, Color.darkGray);
        getPanelParent().setForegroundAt(2, Color.LIGHT_GRAY);
        getPanelParent().setSelectedIndex(0);
        getPanelCo().getjPasswordField1().setEchoChar((char)0);
        getPanelCo().getjPasswordField1().setText("matricule");
        getPanelCo().getjTextField1().setText("Nom");

    }//GEN-LAST:event_buttonDeconnexionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDeconnexion;
    private javax.swing.JButton buttonLireRapport;
    private javax.swing.JButton buttonNewRapport;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable listRapportsTab;
    // End of variables declaration//GEN-END:variables

   
    //methodes
    
    //methode de mise à jour des données
    public void refresh() {
        int valueRefresh = getMetierListeRV().afficherRPVisiteurs();
        DefaultTableModel model = (DefaultTableModel) getListRapportsTab().getModel();
        switch (valueRefresh) {
            case 0:
                //vide pour visiteur
                model.setColumnCount(4);
                model.setRowCount(0);
                break;
            case 1:
                //vide pour un admin
                if (model.getColumnCount() < 5) {
                    model.addColumn("Visiteur");
                    model.setColumnCount(5);
                   
                }
                 model.setRowCount(0);
            case 2:
                //pour un visiteur
                model.setColumnCount(4);

                model.setRowCount(0);
                getMetierListeRV().getListeRapportVisite().forEach((rapport) -> {
                    model.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(rapport.getDateRapport().getTime()),
                        rapport.getPraticienRapport().getNom() + " " + rapport.getPraticienRapport().getPrenom(),
                        rapport.getMotifRapport(), rapport.getBilanRapport()});
                });
                break;
            case 3:
                //pour un admin
                if (model.getColumnCount() < 5) {
                    model.addColumn("Visiteur");
                    model.setColumnCount(5);
                }
                model.setRowCount(0);
                getMetierListeRV().getListeRapportVisite().forEach((rapport) -> {
                    model.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(rapport.getDateRapport().getTime()),
                        rapport.getPraticienRapport().getNom() + " " + rapport.getPraticienRapport().getPrenom(),
                        rapport.getMotifRapport(), rapport.getBilanRapport(), rapport.getVisiteurRapport().getMatricule()});
                });

                break;

            default:

                break;

        }

        getPanelParent().setEnabledAt(1, false);
        getPanelParent().setBackgroundAt(1, Color.darkGray);
        getPanelParent().setForegroundAt(1, Color.LIGHT_GRAY);

    }
}
