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
import javax.swing.JOptionPane;
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

    public JButton getButtonAddEchan() {
        return buttonAddEchan;
    }

    public void setButtonAddEchan(JButton buttonAddEchan) {
        this.buttonAddEchan = buttonAddEchan;
    }

    public JButton getButtonAnnuler() {
        return buttonAnnuler;
    }

    public void setButtonAnnuler(JButton buttonAnnuler) {
        this.buttonAnnuler = buttonAnnuler;
    }

    public JButton getButtonDeleteEchan() {
        return buttonDeleteEchan;
    }

    public void setButtonDeleteEchan(JButton buttonDeleteEchan) {
        this.buttonDeleteEchan = buttonDeleteEchan;
    }

    public JButton getButtonModifier() {
        return buttonModifier;
    }

    public void setButtonModifier(JButton buttonModifier) {
        this.buttonModifier = buttonModifier;
    }

    public JButton getButtonSupprimer() {
        return buttonSupprimer;
    }

    public void setButtonSupprimer(JButton buttonSupprimer) {
        this.buttonSupprimer = buttonSupprimer;
    }

    public JButton getButtonValider() {
        return buttonValider;
    }

    public void setButtonValider(JButton buttonValider) {
        this.buttonValider = buttonValider;
    }

    public JTable getEchantillonTab() {
        return echantillonTab;
    }

    public void setEchantillonTab(JTable echantillonTab) {
        this.echantillonTab = echantillonTab;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public JLabel getLabelBilanTitre() {
        return labelBilanTitre;
    }

    public void setLabelBilanTitre(JLabel labelBilanTitre) {
        this.labelBilanTitre = labelBilanTitre;
    }

    public JLabel getLabelDate() {
        return labelDate;
    }

    public void setLabelDate(JLabel labelDate) {
        this.labelDate = labelDate;
    }

    public JLabel getLabelDateTitre() {
        return labelDateTitre;
    }

    public void setLabelDateTitre(JLabel labelDateTitre) {
        this.labelDateTitre = labelDateTitre;
    }

    public JLabel getLabelEchantillonsListTitre() {
        return labelEchantillonsListTitre;
    }

    public void setLabelEchantillonsListTitre(JLabel labelEchantillonsListTitre) {
        this.labelEchantillonsListTitre = labelEchantillonsListTitre;
    }

    public JLabel getLabelMedicamentTitre() {
        return labelMedicamentTitre;
    }

    public void setLabelMedicamentTitre(JLabel labelMedicamentTitre) {
        this.labelMedicamentTitre = labelMedicamentTitre;
    }

    public JLabel getLabelPraticienTitre() {
        return labelPraticienTitre;
    }

    public void setLabelPraticienTitre(JLabel labelPraticienTitre) {
        this.labelPraticienTitre = labelPraticienTitre;
    }

    public JLabel getLabelQteTitre() {
        return labelQteTitre;
    }

    public void setLabelQteTitre(JLabel labelQteTitre) {
        this.labelQteTitre = labelQteTitre;
    }

    public JLabel getLabelTitreTitre() {
        return labelTitreTitre;
    }

    public void setLabelTitreTitre(JLabel labelTitreTitre) {
        this.labelTitreTitre = labelTitreTitre;
    }

    public JLabel getLabelVisiteurTitre() {
        return labelVisiteurTitre;
    }

    public void setLabelVisiteurTitre(JLabel labelVisiteurTitre) {
        this.labelVisiteurTitre = labelVisiteurTitre;
    }

    public JTextField getMotifText() {
        return motifText;
    }

    public void setMotifText(JTextField motifText) {
        this.motifText = motifText;
    }

    public JLabel getNomPraticien() {
        return nomPraticien;
    }

    public void setNomPraticien(JLabel nomPraticien) {
        this.nomPraticien = nomPraticien;
    }

    public JLabel getNomVisiteurLabel() {
        return nomVisiteurLabel;
    }

    public void setNomVisiteurLabel(JLabel nomVisiteurLabel) {
        this.nomVisiteurLabel = nomVisiteurLabel;
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
        labelDate = new javax.swing.JLabel();
        motifText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        echantillonTab = new javax.swing.JTable();
        labelEchantillonsListTitre = new javax.swing.JLabel();
        buttonModifier = new javax.swing.JButton();
        buttonValider = new javax.swing.JButton();
        MedocComboBox = new javax.swing.JComboBox<>();
        buttonAddEchan = new javax.swing.JButton();
        buttonDeleteEchan = new javax.swing.JButton();
        labelPraticienTitre = new javax.swing.JLabel();
        labelTitreTitre = new javax.swing.JLabel();
        labelBilanTitre = new javax.swing.JLabel();
        QteComboBox = new javax.swing.JComboBox<>();
        PraticienComboBox = new javax.swing.JComboBox<>();
        nomPraticien = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bilanText = new javax.swing.JTextArea();
        labelDateTitre = new javax.swing.JLabel();
        labelVisiteurTitre = new javax.swing.JLabel();
        labelMedicamentTitre = new javax.swing.JLabel();
        labelQteTitre = new javax.swing.JLabel();
        buttonAnnuler = new javax.swing.JButton();
        buttonSupprimer = new javax.swing.JButton();

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

        labelEchantillonsListTitre.setText("Liste d'échantillons offerts :");

        buttonModifier.setText("Modifer");
        buttonModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModifierActionPerformed(evt);
            }
        });

        buttonValider.setText("Enregistrer");
        buttonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonValiderActionPerformed(evt);
            }
        });

        buttonAddEchan.setText("Ajout Echantillon");
        buttonAddEchan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddEchanActionPerformed(evt);
            }
        });

        buttonDeleteEchan.setText("Supprimer Echantillon");
        buttonDeleteEchan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteEchanActionPerformed(evt);
            }
        });

        labelPraticienTitre.setText("Praticien :");

        labelTitreTitre.setText("Titre :");

        labelBilanTitre.setText("Bilan :");

        QteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        PraticienComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PraticienComboBoxActionPerformed(evt);
            }
        });

        bilanText.setColumns(20);
        bilanText.setRows(5);
        jScrollPane2.setViewportView(bilanText);

        labelDateTitre.setText("Date :");

        labelVisiteurTitre.setText("Visiteur :");

        labelMedicamentTitre.setText("Medicament :");

        labelQteTitre.setText("Quantité :");

        buttonAnnuler.setText("Annuler");
        buttonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnnulerActionPerformed(evt);
            }
        });

        buttonSupprimer.setForeground(new java.awt.Color(255, 0, 0));
        buttonSupprimer.setText("Supprimer");
        buttonSupprimer.setToolTipText("");
        buttonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSupprimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(motifText, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTitreTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelPraticienTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PraticienComboBox, 0, 288, Short.MAX_VALUE)
                                    .addComponent(nomPraticien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelDateTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelVisiteurTitre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nomVisiteurLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(labelBilanTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(buttonModifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelEchantillonsListTitre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(MedocComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonAddEchan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonDeleteEchan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(labelMedicamentTitre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(QteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelQteTitre, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonValider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonAnnuler, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonSupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPraticienTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDateTitre)))
                    .addComponent(labelTitreTitre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nomPraticien, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomVisiteurLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelVisiteurTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PraticienComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(motifText, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(labelBilanTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQteTitre)
                    .addComponent(labelMedicamentTitre)
                    .addComponent(labelEchantillonsListTitre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MedocComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(QteComboBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonAddEchan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDeleteEchan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonValider, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(buttonModifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void motifTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motifTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motifTextActionPerformed

    private void buttonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonValiderActionPerformed
        // TODO add your handling code here:
        if (buttonValider.getText().equalsIgnoreCase("Enregistrer")) {

            int id = 0;
            GregorianCalendar gb = new GregorianCalendar();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date;
            try {

                date = format.parse(getLabelDate().getText());
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

        } else if (buttonValider.getText().equalsIgnoreCase("Ok")) {
            getPanelGeneral().setEnabledAt(0, true);
            getPanelGeneral().setBackgroundAt(0, Color.lightGray);
            getPanelGeneral().setForegroundAt(0, Color.white);
            getPanelGeneral().setSelectedIndex(0);

        } else if (buttonValider.getText().equalsIgnoreCase("Mettre à jour")) {

            GregorianCalendar gb = new GregorianCalendar();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date;
            try {
                date = format.parse(getLabelDate().getText());
                System.out.println(getLabelDate().getText());
                gb.setTime(date);
            } catch (ParseException ex) {
                Logger.getLogger(JPanelDetailRapport.class.getName()).log(Level.SEVERE, null, ex);
            }

            String bilan = getBilanText().getText();
            String motif = getMotifText().getText();
            CVisiteur visit = getMetierDetailRV().getRapportV1().getVisiteurRapport();

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

                String praticienSelect = getNomPraticien().getText();

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


    }//GEN-LAST:event_buttonValiderActionPerformed

    private void buttonAddEchanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddEchanActionPerformed
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


    }//GEN-LAST:event_buttonAddEchanActionPerformed

    private void buttonDeleteEchanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteEchanActionPerformed
        // TODO add your handling code here:

        DefaultTableModel modelEchan = (DefaultTableModel) getEchantillonTab().getModel();
        modelEchan.removeRow(getEchantillonTab().getSelectedRow());
    }//GEN-LAST:event_buttonDeleteEchanActionPerformed

    private void buttonModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModifierActionPerformed
        // TODO add your handling code here:
        getMotifText().setEditable(true);
        getBilanText().setEditable(true);
        getPraticienComboBox().hide();
        getNomPraticien().show();

        getMedocComboBox().show();
        if (getMedocComboBox().getItemCount() == 0) {
            for (int y = 0; y < getMetierDetailRV().getListeMedicaments().size(); y++) {
                getMedocComboBox().addItem(getMetierDetailRV().getListeMedicaments().get(y).getNomCommercial());

            }
        }

        getQteComboBox().show();
        getButtonAddEchan().show();
        getButtonSupprimer().hide();
        getButtonValider().setText("Mettre à jour");
        getButtonDeleteEchan().show();
        getButtonModifier().hide();
        getLabelMedicamentTitre().show();
        getLabelQteTitre().show();
        getButtonAnnuler().show();


    }//GEN-LAST:event_buttonModifierActionPerformed

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

    private void buttonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSupprimerActionPerformed
        // TODO add your handling code here:

        JOptionPane jop2 = new JOptionPane();
        
        int option = jop2.showConfirmDialog(null, "Voulez vous vraiment supprimer ce rapport ?", "Warning", JOptionPane.YES_NO_OPTION, 
        JOptionPane.WARNING_MESSAGE);
        
       
        if (option == JOptionPane.OK_OPTION) {
            getMetierDetailRV().supprimerRapportVisite();
            
            getPanelGeneral().setEnabledAt(0, true);
            getPanelGeneral().setBackgroundAt(0, Color.lightGray);
            getPanelGeneral().setForegroundAt(0, Color.white);
            getPanelGeneral().setSelectedIndex(0);
            
            getPanelList().refresh();
            
            
        }

    }//GEN-LAST:event_buttonSupprimerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> MedocComboBox;
    private javax.swing.JComboBox<String> PraticienComboBox;
    private javax.swing.JComboBox<String> QteComboBox;
    private javax.swing.JTextArea bilanText;
    private javax.swing.JButton buttonAddEchan;
    private javax.swing.JButton buttonAnnuler;
    private javax.swing.JButton buttonDeleteEchan;
    private javax.swing.JButton buttonModifier;
    private javax.swing.JButton buttonSupprimer;
    private javax.swing.JButton buttonValider;
    private javax.swing.JTable echantillonTab;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelBilanTitre;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelDateTitre;
    private javax.swing.JLabel labelEchantillonsListTitre;
    private javax.swing.JLabel labelMedicamentTitre;
    private javax.swing.JLabel labelPraticienTitre;
    private javax.swing.JLabel labelQteTitre;
    private javax.swing.JLabel labelTitreTitre;
    private javax.swing.JLabel labelVisiteurTitre;
    private javax.swing.JTextField motifText;
    private javax.swing.JLabel nomPraticien;
    private javax.swing.JLabel nomVisiteurLabel;
    // End of variables declaration//GEN-END:variables

    public void refreshRapportUnique(int id) {

        this.cle = id;
        int value1Rapport = getMetierDetailRV().lire1RV(Integer.toString(id));

        if (value1Rapport == 2) {
            CRapportVisite rapportUnique = getMetierDetailRV().getRapportV1();
            getMotifText().setText(rapportUnique.getMotifRapport());
            getMotifText().setEditable(false);
            getBilanText().setText(rapportUnique.getBilanRapport());
            getBilanText().setEditable(false);
            getLabelDate().setText(new SimpleDateFormat("dd/MM/yyyy").format(rapportUnique.getDateRapport().getTime()));
            getNomVisiteurLabel().setText(rapportUnique.getVisiteurRapport().getNom());
            getPraticienComboBox().hide();
            getNomPraticien().show();
            getNomPraticien().setText(rapportUnique.getPraticienRapport().getIdPraticien() + "-" + rapportUnique.getPraticienRapport().getNom() + " " + rapportUnique.getPraticienRapport().getPrenom());

            DefaultTableModel modelEchan = (DefaultTableModel) getEchantillonTab().getModel();
            modelEchan.setRowCount(0);
            rapportUnique.getListeEchantillonRapport().forEach((echantillon) -> {
                modelEchan.addRow(new Object[]{echantillon.getMedicamentEchantillon().getNomCommercial(), echantillon.getQuantiteEchantillon()});
            });

            getMedocComboBox().hide();
            getQteComboBox().hide();
            getButtonAddEchan().hide();
            getButtonModifier().show();
            getButtonValider().setText("Ok");
            getButtonDeleteEchan().hide();
            getLabelMedicamentTitre().hide();
            getLabelQteTitre().hide();
            getButtonAnnuler().hide();
            getButtonSupprimer().hide();

            if (getMetierDetailRV().getVisiteur().getRole().equals("Administrateur")) {
                getButtonSupprimer().show();

            }

        }

    }

    public void newRV() {

        //this.rapportUnique = getMetierDetailRV().getRapportV1();
        getMotifText().setText("");
        getMotifText().setEditable(true);
        getBilanText().setText("");
        getBilanText().setEditable(true);
        getLabelDate().setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(new GregorianCalendar(Locale.FRANCE).getTimeInMillis())));
        getNomVisiteurLabel().setText(getMetierDetailRV().getVisiteur().getNom());
        getPraticienComboBox().show();
        for (int x = 0; x < getMetierDetailRV().getListPraticien().size(); x++) {

            getPraticienComboBox().addItem(getMetierDetailRV().getListPraticien().get(x).getIdPraticien() + "-" + getMetierDetailRV().getListPraticien().get(x).getNom() + " " + getMetierDetailRV().getListPraticien().get(x).getPrenom());
        }
        getNomPraticien().setText("");
        getNomPraticien().hide();

        DefaultTableModel modelEchan = (DefaultTableModel) getEchantillonTab().getModel();
        modelEchan.setRowCount(0);

        getMedocComboBox().show();

        if (getMedocComboBox().getItemCount() == 0) {
            for (int y = 0; y < getMetierDetailRV().getListeMedicaments().size(); y++) {
                getMedocComboBox().addItem(getMetierDetailRV().getListeMedicaments().get(y).getNomCommercial());

            }
        }

        getNomPraticien().hide();
        getQteComboBox().show();
        getButtonAnnuler().show();
        getButtonSupprimer().hide();
        getButtonValider().setText("Enregistrer");
        getButtonValider().show();
        getButtonModifier().hide();
        getLabelMedicamentTitre().show();
        getLabelQteTitre().show();
        getButtonAnnuler().show();

    }

}
