 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Administrateur;
import entity.Article;
import entity.Categorie;
import java.awt.Image;
import entity.Regionlivraison;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author milop
 */
public class PMiseEnVente extends javax.swing.JPanel {
    Connection conn = null;
    /**
     * Creates new form PMiseEnVente
     */
    public PMiseEnVente() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension  size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight() /2);
        afficherComboCat();
        afficherComboReg();
        
        jCatCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                afficherComboSousCat();
                
            }
        });
        
        jSousCatCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("-----"+e.getActionCommand());
                afficherComboSSousCat();
            }
        });
        
    }
    
    private ArrayList<Categorie> recupereCat(){
        ArrayList<Categorie> catList= new ArrayList<>();
        try {
            String requete = "select * from Categorie c where idCategorie not in (select idSousCategorie from composer) and idCategorie not in (select idSSousCategorie from composerSous)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            Categorie c;
            while (rs.next()){
                c=new Categorie(rs.getInt("idCategorie"),rs.getString("libelleCat"));
                catList.add(c);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return catList;
    }
    
    private ArrayList<Categorie> recupereSousCat(String cat){
        ArrayList<Categorie> catList= new ArrayList<>();
        try {
            String requete = "select * from Categorie c where idCategorie in (select composer.idSousCategorie from composer where composer.idCategorie = (select c2.idCategorie from Categorie c2 where c2.libelleCat = \""+cat+"\"))";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            Categorie c;
            while (rs.next()){
                c=new Categorie(rs.getInt("idCategorie"),rs.getString("libelleCat"));
                catList.add(c);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return catList;
    }
    
    private ArrayList<Categorie> recupereSSousCat(String cat){
        ArrayList<Categorie> catList= new ArrayList<>();
        try {
            String requete = "select * from Categorie c where idCategorie in (select composerSous.idSSousCategorie from composerSous where composerSous.idSousCategorie = (select c2.idCategorie from Categorie c2 where c2.libelleCat = \""+cat+"\"))";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            Categorie c;
            while (rs.next()){
                c=new Categorie(rs.getInt("idCategorie"),rs.getString("libelleCat"));
                catList.add(c);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return catList;
    }
    
    private ArrayList<Regionlivraison> recupereRegionLivraison(){
        ArrayList<Regionlivraison> regList= new ArrayList<>();
        try {
            String requete = "select * from regionlivraison ";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            Regionlivraison r;
            while (rs.next()){
                r=new Regionlivraison(rs.getInt("idregionlivraison"),rs.getString("region"));
                regList.add(r);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return regList;
    }
    
    
    private void afficherComboCat(){
        ArrayList<Categorie> list = recupereCat();
        for (int i = 0; i < list.size(); i++) {
            jCatCombo.addItem(list.get(i).getLibelleCat());   
        }
    }
    
    private void afficherComboSousCat(){
        jSousCatCombo.removeAllItems();
        System.out.println("here");
        String selected = jCatCombo.getSelectedItem().toString();
        System.out.println(selected);
        ArrayList<Categorie> list = recupereSousCat(selected);
        for (int i = 0; i < list.size(); i++) {
            jSousCatCombo.addItem(list.get(i).getLibelleCat());   
        }
        
    }
    
    private void afficherComboSSousCat(){
        jSSousCatCombo.removeAllItems();
        System.out.println(jSousCatCombo.getSelectedIndex());
        if (jSousCatCombo.getSelectedIndex() != -1){
            String selected = jSousCatCombo.getSelectedItem().toString();
            ArrayList<Categorie> list = recupereSSousCat(selected);
            for (int i = 0; i < list.size(); i++) {
                jSSousCatCombo.addItem(list.get(i).getLibelleCat());   
            }
        }
        
    }
    
    private void afficherComboReg(){
        ArrayList<Regionlivraison> list = recupereRegionLivraison();
        for (int i = 0; i < list.size(); i++) {
            jRegCombo.addItem(list.get(i).getRegion());   
        }
    }
    
    private Categorie getIdCat(String libelle){
        String requete = "select idCategorie from Categorie where libelleCat = '"+libelle+"'";
        Categorie c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if (rs.next()){
                c=new Categorie(rs.getInt("idCategorie"));
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return c;
    }
    
    private Regionlivraison getIdRegion(String libelle){
        String requete = "select idregionlivraison from regionlivraison where region = '"+libelle+"'";
        Regionlivraison r = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if (rs.next()){
                r=new Regionlivraison(rs.getInt("idregionlivraison"));
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return r;
    }

    
    String titre,description,cat,sousCat,sSousCat,regCombo;
    float prixDep,prixRes,prixAchat,fraisPort;
    Date dateClo;

    boolean validation(){
        
        titre = jTitreAnnonce.getText();
        description = jDescription.getText();
        cat = jCatCombo.getSelectedItem().toString();
        sousCat = jSousCatCombo.getSelectedItem().toString();
        sSousCat = jSSousCatCombo.getSelectedItem().toString();
        
        prixDep = Float.parseFloat(jPrixDep.getText());
        prixRes = Float.parseFloat(jPrixRes.getText());
        prixAchat = Float.parseFloat(jPrixAchat.getText());
        fraisPort = Float.parseFloat(jFraisPort.getText());
        
        regCombo = jRegCombo.getSelectedItem().toString();
        dateClo = jDateClo.getDate();
        
        if(titre.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez saisir le titre");
            return false;
        }
        
        if(description.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez saisir la description");
            return false;
        }
        
        if(dateClo.equals(null)){
            JOptionPane.showMessageDialog(this, "veuillez indiquer la date de cloture");
            return false;
        }
        
        if(jPrixDep.getText().equals("")){
            JOptionPane.showMessageDialog(this, "veuillez saisir le prix depart");
            return false;
        }
        
        if(regCombo.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez choisir le region de livraison");
            return false;
        }
        return true;
    }
    
    
    PreparedStatement pst = null;
    
    private void insererArticle(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String maDate = format.format(dateClo);
        
        float pasEnchere;
        int idVendeur = 1;
        int idRegionLiv = getIdRegion(regCombo).getIdregionlivraison();
        int idCategorie = getIdCat(cat).getIdCategorie();
        int idSousCategorie = getIdCat(sousCat).getIdCategorie();
        
        pasEnchere = prixDep / 10;

        try{
             String sql = "INSERT INTO article (titreAnnonce, descAnnonce, prixAchatImme, prixDep, prixReserve, fraisPort, dateCloture, pasEnchere, idvendeur, idRegionLiv, idCategorie, idSousCategorie, etat) "
                     + "VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, 'Cree')";
          
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");

            pst = conn.prepareStatement(sql);
            pst.setString(1, titre);
            pst.setString(2, description);
            pst.setString(3, ""+prixAchat);
            pst.setString(4, ""+prixDep);
            pst.setString(5, ""+prixRes);
            pst.setString(6, ""+fraisPort);
            pst.setString(7, maDate);
            pst.setString(8, ""+pasEnchere);
            pst.setString(9, ""+idVendeur);
            pst.setString(10, ""+idRegionLiv);
            pst.setString(11, ""+idCategorie);
            pst.setString(12, ""+idSousCategorie);
              
            pst.executeUpdate();
            
        }catch(Exception e){
                e.printStackTrace();
        }finally{
            try{
                pst.close();
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private Article getIdArticle(){
        String requete = "select idarticle from article where titreAnnonce ='"+ titre+"'";
        Article a = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if (rs.next()){
                a=new Article(rs.getInt("idarticle"));
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return a;
    }
    
    private int getIdImage(String f){
        String requete = "select idimage from image where repertoire ='"+ f+"'";
        int id = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if (rs.next()){
                id=rs.getInt("idimage");
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return id;
    }
    
    private void insererImages(String[] imgs){
        int idArticle = getIdArticle().getIdarticle();
        for (int i = 0; i < imgs.length; i++) {
            String img = imgs[i];
            if (img==null){
                break;
            }else{
                try{
                    String sql = "INSERT INTO image (repertoire) VALUES ('"+ img  +"')";
                    
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    String sql2 = "INSERT INTO posseder (idimage,idarticle) VALUES ('"+ getIdImage(img)  +"','"+idArticle+"')";
                    stmt.executeUpdate(sql2);
                }catch(Exception e){
                        e.printStackTrace();
                }finally{
                    try{
                        pst.close();
                        conn.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTitreAnnonce = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDescription = new javax.swing.JTextArea();
        jLabelP1 = new javax.swing.JLabel();
        jLabelP3 = new javax.swing.JLabel();
        jLabelP4 = new javax.swing.JLabel();
        jLabelP2 = new javax.swing.JLabel();
        jCatCombo = new javax.swing.JComboBox();
        jSousCatCombo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSSousCatCombo = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPrixDep = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPrixRes = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPrixAchat = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jFraisPort = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jRegCombo = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jDateClo = new com.toedter.calendar.JDateChooser();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(228, 228, 228));

        jLabel1.setBackground(new java.awt.Color(237, 237, 237));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mise en vente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DESCRIPTION DE L'OBJET");

        jLabel3.setText("Titre de l'annonce");

        jLabel4.setText("Description détaillée");

        jDescription.setColumns(20);
        jDescription.setRows(5);
        jScrollPane1.setViewportView(jDescription);

        jLabelP1.setBackground(new java.awt.Color(204, 0, 153));
        jLabelP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelP3.setBackground(new java.awt.Color(204, 0, 153));
        jLabelP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelP3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelP4.setBackground(new java.awt.Color(204, 0, 153));
        jLabelP4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelP4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelP2.setBackground(new java.awt.Color(204, 0, 153));
        jLabelP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelP2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jCatCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        jSousCatCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel9.setText("Catégorie");

        jLabel10.setText("Sous-catégorie");

        jLabel11.setText("Sous-catégorie");

        jSSousCatCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ajouter Photo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("CONDITIONS DE VENTE");

        jLabel13.setText("Prix de départ");

        jLabel14.setText("Prix de réserve");

        jLabel15.setText("Prix d'achat immédiat");

        jLabel16.setText("Frais de port");

        jLabel17.setText("Région de livraison");

        jLabel18.setText("Date de clôture");

        jRegCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jButton2.setBackground(new java.awt.Color(0, 153, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Valider");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Annuler");

        jButton4.setBackground(new java.awt.Color(204, 153, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Prestations complémentaires");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jSousCatCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(57, 57, 57)
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(jSSousCatCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTitreAnnonce, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCatCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelP1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelP3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelP4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelP2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPrixDep, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPrixRes, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPrixAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(104, 104, 104))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFraisPort, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(jRegCombo, 0, 159, Short.MAX_VALUE)
                                    .addComponent(jDateClo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelP1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelP2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelP3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelP4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTitreAnnonce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCatCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSousCatCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jSSousCatCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton1)))
                .addGap(34, 34, 34)
                .addComponent(jLabel12)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jPrixDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jPrixRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jPrixAchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jFraisPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jRegCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jDateClo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        FPresta presta = new FPresta();
        presta.setVisible(true);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (validation()){
            insererArticle();
            insererImages(imgs);
            JOptionPane.showMessageDialog(null, "Success!");
        }else{
            JOptionPane.showMessageDialog(null, "Fail!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    private ImageIcon scaleImage(File f, int width, int hight){
        ImageIcon icon = new ImageIcon(f.toString());
        java.awt.Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(WIDTH, WIDTH,0);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        return scaledIcon;
    }
    
//    private String modifyDirImage(String dir){
//        String[] list = dir.split("\\");
//        String res = "";
//        for (int x=0; x<list.length; x++)   
//            res = res+"\\"+list[x];
//        return res;
//    }
    
    int labelCounter = 0;
    String[] imgs = new String[4];
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(jLabelP1.getWidth(), jLabelP1.getHeight(), Image.SCALE_SMOOTH);
        fileName = fileName.replace("\\", "\\\\");
        switch (labelCounter){
                case 0:
                    jLabelP1.setIcon(new ImageIcon(fileName));
                    fileName = fileName.replace("\\", "\\\\");
                    imgs[0] = fileName;
                    labelCounter++;
                    break;
                case 1:
                    jLabelP2.setIcon(new ImageIcon(fileName));
                    labelCounter++;
                    fileName = fileName.replace("\\", "\\\\");
                    imgs[1] = fileName;
                    break;
                case 2:
                    jLabelP3.setIcon(new ImageIcon(fileName));
                    labelCounter++;
                    fileName = fileName.replace("\\", "\\\\");
                    imgs[2] = fileName;
                    break;
                case 3:
                    jLabelP4.setIcon(new ImageIcon(fileName));
                    fileName = fileName.replace("\\", "\\\\");
                    imgs[3] = fileName;
                    labelCounter++;
                    break;
//            case 0:
//                jLabelP1.setIcon(scaleImage(f, jLabelP1.getWidth(), jLabel1.getWidth()));
//                labelCounter++;
//                break;
//            case 1:
//                jLabelP2.setIcon(scaleImage(f, jLabelP2.getWidth(), jLabel2.getWidth()));
//                labelCounter++;
//                break;
//            case 2:
//                jLabelP3.setIcon(scaleImage(f, jLabelP3.getWidth(), jLabel3.getWidth()));
//                labelCounter++;
//                break;
//            case 3:
//                jLabelP4.setIcon(scaleImage(f, jLabelP4.getWidth(), jLabel4.getWidth()));
//                labelCounter++;
//                break;
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jCatCombo;
    private com.toedter.calendar.JDateChooser jDateClo;
    private javax.swing.JTextArea jDescription;
    private javax.swing.JTextField jFraisPort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelP1;
    private javax.swing.JLabel jLabelP2;
    private javax.swing.JLabel jLabelP3;
    private javax.swing.JLabel jLabelP4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jPrixAchat;
    private javax.swing.JTextField jPrixDep;
    private javax.swing.JTextField jPrixRes;
    private javax.swing.JComboBox jRegCombo;
    private javax.swing.JComboBox jSSousCatCombo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jSousCatCombo;
    private javax.swing.JTextField jTitreAnnonce;
    // End of variables declaration//GEN-END:variables
}
