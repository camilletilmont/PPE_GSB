/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Donnees.CEchantillon;
import Donnees.CMedicament;
import Donnees.CPraticien;
import Donnees.CRapportVisite;
import Donnees.CVisiteur;
import Service.CMetierRV;
import java.awt.Color;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author camilletilmont
 */
public class JPanelDetailRapport extends javax.swing.JPanel {

    /**
     * Creates new form JPanelDetailRapport
     */
    public JPanelDetailRapport() {
        initComponents();

    }

    protected CMetierRV metierDetailRV;
    protected int cle;
    protected JTabbedPane panelGeneral;
    protected JPanelListeRapport panelList;

    public JPanelListeRapport getPanelList() {
        return panelList;
    }

    public void setPanelList(JPanelListeRapport panelList) {
        this.panelList = panelList;
    }

    public JTabbedPane getPanelGeneral() {
        return panelGeneral;
    }

    public void setPanelGeneral(JTabbedPane panelGeneral) {
        this.panelGeneral = panelGeneral;
    }

    public int getCle() {
        return cle;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }

    public CMetierRV getMetierDetailRV() {
        return metierDetailRV;
    }

    public void setMetierDetailRV(CMetierRV metier) {
        this.metierDetailRV = metier;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomVisiteurLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        motifText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        echantillonTab = new javax.swing.JTable();
        echantListLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        MedocComboBox = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        labelPra = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        QteComboBox = new javax.swing.JComboBox<>();
        PraticienComboBox = new javax.swing.JComboBox<>();
        nomPra = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bilanText = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        buttonAnnuler = new javax.swing.JButton();

        motifText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motifTextActionPerformed(evt);
            }
        });

        echantillonTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Echantillon", "Quantité"
            }
        ));
        jScrollPane1.setViewportView(echantillonTab);

        echantListLabel.setText("Liste d'échantillons offerts :");

        jButton1.setText("Modifer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Enregistrer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Ajout Echantillon");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Supprimer Echantillon");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        labelPra.setText("Praticien :");

        jLabel2.setText("Titre :");

        jLabel3.setText("Bilan :");

        QteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        PraticienComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PraticienComboBoxActionPerformed(evt);
            }
        });

        bilanText.setColumns(20);
        bilanText.setRows(5);
        jScrollPane2.setViewportView(bilanText);

        jLabel1.setText("Date :");

        jLabel4.setText("Visiteur :");

        jLabel5.setText("Medicament :");

        jLabel6.setText("Quantité :");

        buttonAnnuler.setText("Annuler");
        buttonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnnulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(motifText, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelPra, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PraticienComboBox, 0, 288, Short.MAX_VALUE)
                            .addComponent(nomPra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomVisiteurLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(echantListLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(MedocComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(QteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(buttonAnnuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPra, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nomPra, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomVisiteurLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PraticienComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(motifText, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(echantListLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MedocComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(QteComboBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAnnuler))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void motifTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motifTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motifTextActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (jButton2.getText().equalsIgnoreCase("Enregistrer")) {

            int id = 0;
            GregorianCalendar gb = new GregorianCalendar();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date;
            try {

                date = format.parse(getDateLabel().getText());
                gb.setTime(date);
            } catch (ParseException ex) {
                Logger.getLogger(JPanelDetailRapport.class.getName()).log(Level.SEVERE, null, ex);
            }

            String bilan = getBilanText().getText();
            String motif = getMotifText().getText();
            CVisiteur visit = getMetierDetailRV().getVisiteur();

            ArrayList<CEchantillon> listEchantillonsNewRapport = new ArrayList<>();

            for (int y = 0; y < getEchantillonTab().getRowCount(); y++) {
                for (int x = 0; x < getMetierDetailRV().getListeMedicaments().size(); x++) {
                    if (getEchantillonTab().getModel().getValueAt(y, 0).toString().equals(getMetierDetailRV().getListeMedicaments().get(x).getNomCommercial())) {

                        int l = Integer.parseInt(getEchantillonTab().getModel().getValueAt(y, 1).toString());
                        CEchantillon echanTemp = new CEchantillon(getMetierDetailRV().getListeMedicaments().get(x), l);

                        listEchantillonsNewRapport.add(echanTemp);

                    }
                }
            }

            CPraticien prat = null;

            for (int z = 0; z < getMetierDetailRV().getListPraticien().size(); z++) {
                int pratTemp = getMetierDetailRV().getListPraticien().get(z).getIdPraticien();

                String praticienSelect = getPraticienComboBox().getSelectedItem().toString();

                String idTemp = praticienSelect.substring(0, praticienSelect.indexOf("-"));

                if (idTemp.equals(Integer.toString(pratTemp))) {
                    prat = getMetierDetailRV().getListPraticien().get(z);

                }

            }

            ArrayList<CMedicament> listMedVide = new ArrayList<>();

            if (prat != null) {

                getMetierDetailRV().creerRapportVisite(new CRapportVisite(id, gb, bilan, motif, visit, prat, listEchantillonsNewRapport, listMedVide));

            } else {
                System.out.println("Erreur insertion RV IHM 'enregistrer' nouveau rapport");
            }

            getPanelGeneral().setEnabledAt(0, true);
            getPanelGeneral().setBackgroundAt(0, Color.lightGray);
            getPanelGeneral().setForegroundAt(0, Color.white);
            getPraticienComboBox().setSelectedIndex(0);
            getPanelGeneral().setSelectedIndex(0);
            getMedocComboBox().setSelectedIndex(0);
            getQteComboBox().setSelectedIndex(0);

        } else if (jButton2.getText().equalsIgnoreCase("Ok")) {
            getPanelGeneral().setEnabledAt(0, true);
            getPanelGeneral().setBackgroundAt(0, Color.lightGray);
            getPanelGeneral().setForegroundAt(0, Color.white);
            getPanelGeneral().setSelectedIndex(0);

        } else if (jButton2.getText().equalsIgnoreCase("Mettre à jour")) {

            GregorianCalendar gb = new GregorianCalendar();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date;
            try {
                date = format.parse(getDateLabel().getText());
                System.out.println(getDateLabel().getText());
                gb.setTime(date);
            } catch (ParseException ex) {
                Logger.getLogger(JPanelDetailRapport.class.getName()).log(Level.SEVERE, null, ex);
            }

            String bilan = getBilanText().getText();
            String motif = getMotifText().getText();
            CVisiteur visit = getMetierDetailRV().getVisiteur();

            ArrayList<CEchantillon> listEchantillonsNewRapport = new ArrayList<>();

            for (int y = 0; y < getEchantillonTab().getRowCount(); y++) {
                for (int x = 0; x < getMetierDetailRV().getListeMedicaments().size(); x++) {
                    if (getEchantillonTab().getModel().getValueAt(y, 0).toString().equals(getMetierDetailRV().getListeMedicaments().get(x).getNomCommercial())) {

                        int l = Integer.parseInt(getEchantillonTab().getModel().getValueAt(y, 1).toString());
                        CEchantillon echanTemp = new CEchantillon(getMetierDetailRV().getListeMedicaments().get(x), l);

                        listEchantillonsNewRapport.add(echanTemp);

                    }
                }
            }

            CPraticien prat = null;

            for (int z = 0; z < getMetierDetailRV().getListPraticien().size(); z++) {
                int pratTemp = getMetierDetailRV().getListPraticien().get(z).getIdPraticien();

                String praticienSelect = getNomPra().getText();

                String idTemp = praticienSelect.substring(0, praticienSelect.indexOf("-"));

                if (idTemp.equals(Integer.toString(pratTemp))) {
                    prat = getMetierDetailRV().getListPraticien().get(z);

                }

            }

            ArrayList<CMedicament> listMedVide = new ArrayList<>();

            if (prat != null) {

                getMetierDetailRV().modifierRapportVisite(new CRapportVisite(cle, gb, bilan, motif, visit, prat, listEchantillonsNewRapport, listMedVide));

            } else {

                System.out.println("Erreur insertion RV IHM 'enregistrer' nouveau rapport");
            }

            getPanelGeneral().setEnabledAt(0, true);
            getPanelGeneral().setBackgroundAt(0, Color.lightGray);
            getPanelGeneral().setForegroundAt(0, Color.white);
            
            getPanelGeneral().setSelectedIndex(0);
            getMedocComboBox().setSelectedIndex(0);
            getQteComboBox().setSelectedIndex(0);

        }

        getPanelList().refresh();


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelEchan = (DefaultTableModel) getEchantillonTab().getModel();
        boolean exist = false;
        for (int a = 0; a < getEchantillonTab().getRowCount(); a++) {
            if (getMedocComboBox().getSelectedItem().equals(modelEchan.getValueAt(a, 0))) {

                exist = true;
            }

        }
        if (!exist && modelEchan.getRowCount() < 10) {
            modelEchan.addRow(new Object[]{getMedocComboBox().getSelectedItem(), getQteComboBox().getSelectedItem()});
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        DefaultTableModel modelEchan = (DefaultTableModel) getEchantillonTab().getModel();
        modelEchan.removeRow(getEchantillonTab().getSelectedRow());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        getMotifText().setEditable(true);
        getBilanText().setEditable(true);
        getPraticienComboBox().hide();
        getNomPra().show();

        getMedocComboBox().show();
        if(getMedocComboBox().getItemCount() == 0){
        for (int y = 0; y < getMetierDetailRV().getListeMedicaments().size(); y++) {
            getMedocComboBox().addItem(getMetierDetailRV().getListeMedicaments().get(y).getNomCommercial());

        }
        }

        getQteComboBox().show();
        getjButton3().show();
        getjButton2().setText("Mettre à jour");
        getjButton5().show();
        getjButton1().hide();
        getjLabel5().show();
        getjLabel6().show();
        getButtonAnnuler().show();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void PraticienComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PraticienComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PraticienComboBoxActionPerformed

    private void buttonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnnulerActionPerformed
        // TODO add your handling code here:

        getPanelGeneral().setEnabledAt(0, true);
        getPanelGeneral().setBackgroundAt(0, Color.lightGray);
        getPanelGeneral().setForegroundAt(0, Color.white);
        getPanelGeneral().setSelectedIndex(0);

        getMedocComboBox().setSelectedIndex(0);
        getQteComboBox().setSelectedIndex(0);

        getPanelList().refresh();

    }//GEN-LAST:event_buttonAnnulerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> MedocComboBox;
    private javax.swing.JComboBox<String> PraticienComboBox;
    private javax.swing.JComboBox<String> QteComboBox;
    private javax.swing.JTextArea bilanText;
    private javax.swing.JButton buttonAnnuler;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel echantListLabel;
    private javax.swing.JTable echantillonTab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelPra;
    private javax.swing.JTextField motifText;
    private javax.swing.JLabel nomPra;
    private javax.swing.JLabel nomVisiteurLabel;
    // End of variables declaration//GEN-END:variables

    public JComboBox<String> getMedocComboBox() {
        return MedocComboBox;
    }

    public void setMedocComboBox(JComboBox<String> MedocComboBox) {
        this.MedocComboBox = MedocComboBox;
    }

    public JComboBox<String> getPraticienComboBox() {
        return PraticienComboBox;
    }

    public void setPraticienComboBox(JComboBox<String> PraticienComboBox) {
        this.PraticienComboBox = PraticienComboBox;
    }

    public JComboBox<String> getQteComboBox() {
        return QteComboBox;
    }

    public void setQteComboBox(JComboBox<String> QteComboBox) {
        this.QteComboBox = QteComboBox;
    }

    public JTextArea getBilanText() {
        return bilanText;
    }

    public void setBilanText(JTextArea bilanText) {
        this.bilanText = bilanText;
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(JLabel dateLabel) {
        this.dateLabel = dateLabel;
    }

    public JLabel getEchantListLabel() {
        return echantListLabel;
    }

    public void setEchantListLabel(JLabel echantListLabel) {
        this.echantListLabel = echantListLabel;
    }

    public JTable getEchantillonTab() {
        return echantillonTab;
    }

    public void setEchantillonTab(JTable echantillonTab) {
        this.echantillonTab = echantillonTab;
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public void setjButton1(JButton jButton1) {
        this.jButton1 = jButton1;
    }

    public JButton getButtonAnnuler() {
        return buttonAnnuler;
    }

    public void setButtonAnnuler(JButton buttonAnnuler) {
        this.buttonAnnuler = buttonAnnuler;
    }

    public JButton getjButton2() {
        return jButton2;
    }

    public void setjButton2(JButton jButton2) {
        this.jButton2 = jButton2;
    }

    public JButton getjButton3() {
        return jButton3;
    }

    public void setjButton3(JButton jButton3) {
        this.jButton3 = jButton3;
    }

    public JButton getjButton5() {
        return jButton5;
    }

    public void setjButton5(JButton jButton5) {
        this.jButton5 = jButton5;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JLabel getjLabel5() {
        return jLabel5;
    }

    public void setjLabel5(JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }

    public JLabel getjLabel6() {
        return jLabel6;
    }

    public void setjLabel6(JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JLabel getLabelPra() {
        return labelPra;
    }

    public void setLabelPra(JLabel labelPra) {
        this.labelPra = labelPra;
    }

    public JTextField getMotifText() {
        return motifText;
    }

    public void setMotifText(JTextField motifText) {
        this.motifText = motifText;
    }

    public JLabel getNomPra() {
        return nomPra;
    }

    public void setNomPra(JLabel nomPra) {
        this.nomPra = nomPra;
    }

    public JLabel getNomVisiteurLabel() {
        return nomVisiteurLabel;
    }

    public void setNomVisiteurLabel(JLabel nomVisiteurLabel) {
        this.nomVisiteurLabel = nomVisiteurLabel;
    }

    public void refreshRapportUnique(int id) {

        this.cle = id;
        int value1Rapport = getMetierDetailRV().lire1RV(Integer.toString(id));

        if (value1Rapport == 2) {
            CRapportVisite rapportUnique = getMetierDetailRV().getRapportV1();
            getMotifText().setText(rapportUnique.getMotifRapport());
            getMotifText().setEditable(false);
            getBilanText().setText(rapportUnique.getBilanRapport());
            getBilanText().setEditable(false);
            getDateLabel().setText(new SimpleDateFormat("dd/MM/yyyy").format(rapportUnique.getDateRapport().getTime()));
            getNomVisiteurLabel().setText(rapportUnique.getVisiteurRapport().getNom());
            getPraticienComboBox().hide();
            getNomPra().show();
            getNomPra().setText(rapportUnique.getPraticienRapport().getIdPraticien() + "-" + rapportUnique.getPraticienRapport().getNom() + " " + rapportUnique.getPraticienRapport().getPrenom());

            DefaultTableModel modelEchan = (DefaultTableModel) getEchantillonTab().getModel();
            modelEchan.setRowCount(0);
            rapportUnique.getListeEchantillonRapport().forEach((echantillon) -> {
                modelEchan.addRow(new Object[]{echantillon.getMedicamentEchantillon().getNomCommercial(), echantillon.getQuantiteEchantillon()});
            });

            getMedocComboBox().hide();
            getQteComboBox().hide();
            getjButton1().show();
            getjButton3().hide();
            getjButton2().setText("Ok");
            getjButton5().hide();
            getjLabel5().hide();
            getjLabel6().hide();
            getButtonAnnuler().hide();

        }

    }

    public void newRV() {

        //this.rapportUnique = getMetierDetailRV().getRapportV1();
        getMotifText().setText("");
        getMotifText().setEditable(true);
        getBilanText().setText("");
        getBilanText().setEditable(true);
        getDateLabel().setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(new GregorianCalendar(Locale.FRANCE).getTimeInMillis())));
        getNomVisiteurLabel().setText(getMetierDetailRV().getVisiteur().getNom());
        getPraticienComboBox().show();
        for (int x = 0; x < getMetierDetailRV().getListPraticien().size(); x++) {

            getPraticienComboBox().addItem(getMetierDetailRV().getListPraticien().get(x).getIdPraticien() + "-" + getMetierDetailRV().getListPraticien().get(x).getNom() + " " + getMetierDetailRV().getListPraticien().get(x).getPrenom());
        }
        getNomPra().setText("");
        getNomPra().hide();

        DefaultTableModel modelEchan = (DefaultTableModel) getEchantillonTab().getModel();
        modelEchan.setRowCount(0);
        

        getMedocComboBox().show();
        
        if(getMedocComboBox().getItemCount() == 0){
        for (int y = 0; y < getMetierDetailRV().getListeMedicaments().size(); y++) {
            getMedocComboBox().addItem(getMetierDetailRV().getListeMedicaments().get(y).getNomCommercial());

        }
        }

        getNomPra().hide();
        getQteComboBox().show();
        getjButton3().show();
        getjButton2().setText("Enregistrer");
        getjButton5().show();
        getjButton1().hide();
        getjLabel5().show();
        getjLabel6().show();
        getButtonAnnuler().show();

    }

}
