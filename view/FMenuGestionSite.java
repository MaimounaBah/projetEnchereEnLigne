/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.DBConnection;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author milop
 */
public class FMenuGestionSite extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    /**
     * Creates new form FMenuAcheteur
     */
    
    DefaultTableModel model;
    public FMenuGestionSite() {
        initComponents();
        setArticlesToTable();
    }
    
     private void miseAjourTable(){
        String sql = "SELECT idarticle, titreAnnonce, prixAchatImme, prixDep, prixReserve, dateCloture, pasEnchere, libelleCat, etat FROM article a,categorie c WHERE a.idCategorie = c.idCategorie ";
       try{
            con = DBConnection.getConnection();
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            tblArticle.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
        
    }
     
     private void  miseAjourTableMembre(){
          String sql = "SELECT idmembre, nom, prenom, dateNais, mail, adressePostal, ville, pays, Statut FROM membre ";
       try{
            con = DBConnection.getConnection();
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            tableMembre.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
     }
     
      public void setArticlesToTable(){
        try{
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT idarticle, titreAnnonce, prixAchatImme, prixDep, prixReserve, dateCloture, pasEnchere, libelleCat, etat FROM article a,categorie c WHERE a.idCategorie = c.idCategorie");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String idarticle = rs.getString("idarticle");
                String titre = rs.getString("titreAnnonce");
                Double prixAchatIm = rs.getDouble("prixAchatImme");
                Double prixDep = rs.getDouble("prixDep");
                Double prixRes = rs.getDouble("prixReserve");
                Date dateClo = rs.getDate("dateCloture");
                String pasEnchere = rs.getString("pasEnchere");
                String idCategorie = rs.getString("libelleCat");
                //String idSouCa = rs.getString("idSousCategorie");
                String etat = rs.getString("etat");
                
                Object[] obj = {idarticle, titre, prixAchatIm, prixDep, prixRes, dateClo, pasEnchere, idCategorie, etat};
                
                model = (DefaultTableModel)tblArticle.getModel();
                model.addRow(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
      
     public void setMembresToTable(){
         try{
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT idmembre, nom, prenom, dateNais, mail, adressePostal, ville, pays, Statut FROM membre ");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String idmembre = rs.getString("idmembre");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                Date dateNais = rs.getDate("dateNais");
                String mail = rs.getString("mail");
                String adresPo = rs.getString("adressePostal");
                String ville = rs.getString("ville");
                String pays = rs.getString("pays");
                String statut = rs.getString("Statut");
                
                Object[] obj = {idmembre, nom, prenom, dateNais, mail, adresPo, ville, pays, statut};
                
                model = (DefaultTableModel)tableMembre.getModel();
                model.addRow(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
     } 
    
     public void recherche(String str){
        model = (DefaultTableModel)tblArticle.getModel();
        TableRowSorter<DefaultTableModel>  trs = new TableRowSorter<>(model);
        tblArticle.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        
    }
     
      public void rechercheMembre(String str){
        model = (DefaultTableModel)tableMembre.getModel();
        TableRowSorter<DefaultTableModel>  trs = new TableRowSorter<>(model);
        tableMembre.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        
    }
     
    private void selectedRow(){
        
        ListSelectionModel model1 = tblArticle.getSelectionModel();
        model1.addListSelectionListener(new ListSelectionListener() {
        
             
            @Override
            public void valueChanged(ListSelectionEvent e) {
                 
                if(!model1.isSelectionEmpty()){
                    String sql = "SELECT descAnnonce, fraisPort,prenom, region FROM article a, membre m,regionlivraison r WHERE a.idVendeur = m.idmembre AND r.idregionlivraison = a.idRegionLiv";
                   try{
                       con = DBConnection.getConnection();
                       pst = con.prepareStatement(sql);
                       ResultSet rs = pst.executeQuery();
                       if(rs.next()){
                            String description = rs.getString("descAnnonce");
                            Double frais = rs.getDouble("fraisPort");
                            String vendeur = rs.getString("prenom");
                            String region = rs.getString("region");
                                                
                          JOptionPane.showMessageDialog(null, "Informations de l'articles\nDescription : " + description +"\nFrais port : " + frais + "\n Vendeur : " + vendeur + "\nRegion de livraion : " + region);
                       }
                       
                   }catch(Exception ex){
                       JOptionPane.showMessageDialog(null, ex);
                   }
                    
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        controleNature = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SupprimerMembre = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        JPanelArticle = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblArticle = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtRecherche = new javax.swing.JTextField();
        btnSupprimer = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtIdSelectinne = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        JPanelMembre = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMembre = new javax.swing.JTable();
        btnSupprimerMembre = new javax.swing.JButton();
        txtRechercheMembre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtIdSelectinneMembre = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        controleNature.setBackground(new java.awt.Color(0, 51, 102));
        controleNature.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controleNatureMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                controleNatureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                controleNatureMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Contôle des articles");

        javax.swing.GroupLayout controleNatureLayout = new javax.swing.GroupLayout(controleNature);
        controleNature.setLayout(controleNatureLayout);
        controleNatureLayout.setHorizontalGroup(
            controleNatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );
        controleNatureLayout.setVerticalGroup(
            controleNatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        SupprimerMembre.setBackground(new java.awt.Color(0, 51, 102));
        SupprimerMembre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SupprimerMembreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SupprimerMembreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SupprimerMembreMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Supprimer membre");
        jLabel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout SupprimerMembreLayout = new javax.swing.GroupLayout(SupprimerMembre);
        SupprimerMembre.setLayout(SupprimerMembreLayout);
        SupprimerMembreLayout.setHorizontalGroup(
            SupprimerMembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SupprimerMembreLayout.setVerticalGroup(
            SupprimerMembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        tab3.setBackground(new java.awt.Color(0, 51, 102));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab3MouseExited(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 51, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Message");

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Gestionnaire de Site");

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(51, 51, 51));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jButton4.setText("Deconnexion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controleNature, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(SupprimerMembre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(220, 220, 220)
                .addComponent(controleNature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SupprimerMembre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanelArticle.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Rechercher un article");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblArticle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Titre", "Immediat", "Prix Départ", "Frais Port", "Date Clôture", "Pas Enchère", "Catégorie", "Etat"
            }
        ));
        tblArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArticleMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblArticle);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel9.setText("Sasissez le nom de l'article");

        txtRecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheKeyReleased(evt);
            }
        });

        btnSupprimer.setBackground(new java.awt.Color(153, 153, 153));
        btnSupprimer.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnSupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(153, 153, 153));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Id");

        txtIdSelectinne.setEditable(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/list.png"))); // NOI18N
        jButton1.setText("Détail");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPanelArticleLayout = new javax.swing.GroupLayout(JPanelArticle);
        JPanelArticle.setLayout(JPanelArticleLayout);
        JPanelArticleLayout.setHorizontalGroup(
            JPanelArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPanelArticleLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(JPanelArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelArticleLayout.createSequentialGroup()
                        .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdSelectinne, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jButton1))
                    .addGroup(JPanelArticleLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(txtRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        JPanelArticleLayout.setVerticalGroup(
            JPanelArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelArticleLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPanelArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(JPanelArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSelectinne, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("tab1", JPanelArticle);

        JPanelMembre.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Rechercher un article");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableMembre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prenom", "Naissance", "Mail", "Adresse", "Ville", "Pays", "Statut"
            }
        ));
        tableMembre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMembreMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMembre);

        btnSupprimerMembre.setBackground(new java.awt.Color(255, 153, 153));
        btnSupprimerMembre.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnSupprimerMembre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnSupprimerMembre.setText("Supprimer");
        btnSupprimerMembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerMembreActionPerformed(evt);
            }
        });

        txtRechercheMembre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheMembreKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel11.setText("Sasissez le nom du membre");

        jLabel15.setBackground(new java.awt.Color(153, 153, 153));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Id");

        txtIdSelectinneMembre.setEditable(false);
        txtIdSelectinneMembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSelectinneMembreActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/list.png"))); // NOI18N
        jButton2.setText("Détail");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPanelMembreLayout = new javax.swing.GroupLayout(JPanelMembre);
        JPanelMembre.setLayout(JPanelMembreLayout);
        JPanelMembreLayout.setHorizontalGroup(
            JPanelMembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPanelMembreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelMembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(JPanelMembreLayout.createSequentialGroup()
                        .addGroup(JPanelMembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelMembreLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(txtRechercheMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPanelMembreLayout.createSequentialGroup()
                                .addComponent(btnSupprimerMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txtIdSelectinneMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129)
                                .addComponent(jButton2)))
                        .addGap(0, 165, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPanelMembreLayout.setVerticalGroup(
            JPanelMembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelMembreLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(JPanelMembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRechercheMembre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(JPanelMembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSupprimerMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSelectinneMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("tab2", JPanelMembre);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 965, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 746, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", jPanel6);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 970, 780));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 748, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1399, 804));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void controleNatureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_controleNatureMouseEntered
        controleNature.setBackground(new Color(50, 84, 153));
        controleNature.setOpaque(true);
    }//GEN-LAST:event_controleNatureMouseEntered

    private void controleNatureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_controleNatureMouseExited
        controleNature.setBackground(new Color(0, 51, 102));
    }//GEN-LAST:event_controleNatureMouseExited

    private void SupprimerMembreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimerMembreMouseEntered
        SupprimerMembre.setBackground(new Color(50, 84, 153));
        SupprimerMembre.setOpaque(true);
    }//GEN-LAST:event_SupprimerMembreMouseEntered

    private void SupprimerMembreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimerMembreMouseExited
        SupprimerMembre.setBackground(new Color(0, 51, 102));
    }//GEN-LAST:event_SupprimerMembreMouseExited

    private void controleNatureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_controleNatureMouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_controleNatureMouseClicked

    private void SupprimerMembreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimerMembreMouseClicked
        jTabbedPane1.setSelectedIndex(1);
        setMembresToTable();
    }//GEN-LAST:event_SupprimerMembreMouseClicked

    private void tab3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseEntered
        controleNature.setBackground(new Color(50, 84, 153));
        controleNature.setOpaque(true);
    }//GEN-LAST:event_tab3MouseEntered

    private void tab3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseExited
        SupprimerMembre.setBackground(new Color(0, 51, 102));
    }//GEN-LAST:event_tab3MouseExited

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_tab3MouseClicked

    private void txtRechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheKeyReleased
        // TODO add your handling code here:
        String chaineChercher = txtRecherche.getText();
        recherche(chaineChercher);
    }//GEN-LAST:event_txtRechercheKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            con.close();
            Index accueil = new Index();
            accueil.setVisible(true);
            this.dispose();
        }catch(Exception e){

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        // TODO add your handling code here:
        String sql = "DELETE FROM article WHERE idarticle = ?" ;
        try{
            //con = DBConnection.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, txtIdSelectinne.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Article supprimé");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        miseAjourTable();
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        selectedRow();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblArticleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArticleMouseClicked
        // TODO add your handling code here:
        
        try{
            int row = tblArticle.getSelectedRow();
            String table_click = (tblArticle.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM article WHERE idArticle = " + table_click;
            con = DBConnection.getConnection();
            pst = con.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                String id = rs.getString("idarticle");
                txtIdSelectinne.setText(id);
            }
            
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tblArticleMouseClicked

    private void btnSupprimerMembreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerMembreActionPerformed
        // TODO add your handling code here:
        
         String sql = "DELETE FROM membre WHERE idmembre = ?" ;
        try{
            //con = DBConnection.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, txtIdSelectinneMembre.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Membre supprimé");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        miseAjourTableMembre();
    }//GEN-LAST:event_btnSupprimerMembreActionPerformed

    private void txtRechercheMembreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheMembreKeyReleased
        // TODO add your handling code here:
        String chaineChercher = txtRechercheMembre.getText();
         rechercheMembre(chaineChercher);
    }//GEN-LAST:event_txtRechercheMembreKeyReleased

    private void txtIdSelectinneMembreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSelectinneMembreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSelectinneMembreActionPerformed

    private void tableMembreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMembreMouseClicked
        // TODO add your handling code here:
         try{
            int row = tableMembre.getSelectedRow();
            String table_clickMembre = (tableMembre.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM membre WHERE idmembre = " + table_clickMembre;
            con = DBConnection.getConnection();
            pst = con.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                String id = rs.getString("idmembre");
                txtIdSelectinneMembre.setText(id);
            }
            
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
       
    }//GEN-LAST:event_tableMembreMouseClicked

    private void jLabel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel3KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabel3KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        selectedRow();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FMenuGestionSite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FMenuGestionSite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FMenuGestionSite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FMenuGestionSite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FMenuGestionSite().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelArticle;
    private javax.swing.JPanel JPanelMembre;
    private javax.swing.JPanel SupprimerMembre;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton btnSupprimerMembre;
    private javax.swing.JPanel controleNature;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel tab3;
    private javax.swing.JTable tableMembre;
    private javax.swing.JTable tblArticle;
    private javax.swing.JTextField txtIdSelectinne;
    private javax.swing.JTextField txtIdSelectinneMembre;
    private javax.swing.JTextField txtRecherche;
    private javax.swing.JTextField txtRechercheMembre;
    // End of variables declaration//GEN-END:variables
}
