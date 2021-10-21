/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author 33760
 */
public class InscriptionMembre extends javax.swing.JFrame {
     Connection con = null;
    PreparedStatement pst = null;

    /**
     * Creates new form ConnexionMembre
     */
    
     String nom, prenom,mail, 
    adrPos,cp, pays, ville, mdp,confirmdp;
     Date dateN;
     
    
     public InscriptionMembre() {
        initComponents();
        
    }
     
    
    boolean validation(){
         
        nom = txtNom.getText();
        prenom = txtPrenom.getText();
        mail = txtMail.getText();
        adrPos = txtAdresseP.getText();
        cp = txtCP.getText();
        pays = comPays.getSelectedItem().toString();
        ville = comVille.getSelectedItem().toString();
        mdp = txtmdp.getText();
        confirmdp = txtConfirmMdp.getText();
       
        dateN = txtDateNaiss.getDate();
        
        if(nom.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez saisir le nom");
            return false;
        }
        
        if(prenom.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez saisir le prénom");
            return false;
        }
        
        if(dateN.equals(null)){
            JOptionPane.showMessageDialog(this, "veuillez indiquer la date de naissance");
            return false;
        }
        
        if(mail.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez saisir le mail");
            return false;
        }
        
        if(adrPos.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez saisir l'adresse postale");
            return false;
        }
        
        if(cp.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez saisir le code postal");
            return false;
        }
               
        
        if(mdp.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez saisir le mot de passe");
            return false;
        }
        
        if(confirmdp.equals("")){
            JOptionPane.showMessageDialog(this, "veuillez confirmer le mot de passe");
            return false;
        }
            
        if(mdp.length() <8){
            lblMdpError.setText("Password doit comporter 8 caractères");
        }
        
        if(!mdp.equals(confirmdp)){
            JOptionPane.showMessageDialog(this, "les mots de passes doivent correspondre");
            return false;
        }
        
        return true;
    }
    
    public void verifierMotDePasse(){
        mdp = txtmdp.getText();
        if(mdp.length() < 8){
            lblMdpError.setText("le password minimum 8 caractères");
        }else{
            lblMdpError.setText("");
        }
    }
    
    public void verifierCP(){
         cp = txtCP.getText();
         if(cp.length() < 5){
             lblCPError.setText("Le CP comporte 5 valeurs");
         }else{
             lblCPError.setText("");
         }
    }
    
    void insertMember() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String maDate = format.format(dateN);
        
        try{
             String sql = "INSERT INTO membre (nom, prenom,dateNais,mail,adressePostal,CP,ville,pays,MotDePass,Statut,NbEtoile) "
                     + "VALUES (?,?,?,?,?,?,?,?,?,'membreSimple',0)";
          
            Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "");
             // String query = "INSERT INTO membre (nom, prenom,dateNais,mail,adressePostal,CP,ville,pays,MotDePass,Statut,NbEtoile) VALUES (?,?,?,?,?,?,?,?,?,'membreSimple',0)";
             
              pst = con.prepareStatement(sql);
            // stmt.setInt(1 , getIdMembre());
             pst.setString(1, txtNom.getText());
             pst.setString(2, txtPrenom.getText());
             pst.setString(3, maDate);
             pst.setString(4, txtMail.getText());
             pst.setString(5, txtAdresseP.getText());
             pst.setString(6, txtCP.getText());
             pst.setString(7, comVille.getSelectedItem().toString());
             pst.setString(8, comPays.getSelectedItem().toString());
             pst.setString(9, txtmdp.getText());
              
             pst.executeUpdate();
              int response = JOptionPane.showConfirmDialog(null, "Inscription réussie. Se connecter?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response ==JOptionPane.YES_OPTION){
                    ConnexionM connexion = new ConnexionM();
                    connexion.setVisible(true);
                    this.dispose();
                }else if(response == JOptionPane.NO_OPTION){
                    this.setVisible(true);
                }
             
        }catch(Exception e){
                e.printStackTrace();
        }finally{
            try{
                pst.close();
                con.close();
            }catch(Exception e){
                e.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnInscription = new javax.swing.JButton();
        btnConnexion = new javax.swing.JButton();
        txtPrenom = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        txtAdresseP = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        comPays = new javax.swing.JComboBox();
        comVille = new javax.swing.JComboBox();
        txtDateNaiss = new com.toedter.calendar.JDateChooser();
        txtConfirmMdp = new javax.swing.JPasswordField();
        txtmdp = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtCP = new javax.swing.JTextField();
        lblMdpError = new javax.swing.JLabel();
        lblCPError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 43)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INSCRIPTION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel1)
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, -1));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Prénom");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date de naissance");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mail");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Code postal");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nom");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ville");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pays");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mot de passe");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Confirmation");

        btnInscription.setBackground(new java.awt.Color(0, 51, 51));
        btnInscription.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnInscription.setForeground(new java.awt.Color(255, 255, 255));
        btnInscription.setText("S'incrire");
        btnInscription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscriptionActionPerformed(evt);
            }
        });

        btnConnexion.setBackground(new java.awt.Color(0, 51, 51));
        btnConnexion.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnConnexion.setForeground(new java.awt.Color(255, 255, 255));
        btnConnexion.setText("Se connecter");
        btnConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnexionActionPerformed(evt);
            }
        });

        txtPrenom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtNom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtAdresseP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtMail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        comPays.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner", "France", "Chine", "Vietname", "Guinée", " " }));

        comVille.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner", "Toulouse", "Paris", "Pekin", "Hanoi", "Conakry", "Dinguiraye" }));

        txtConfirmMdp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmMdpActionPerformed(evt);
            }
        });

        txtmdp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmdpKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmdpKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Retour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Adresse postale");

        txtCP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCPKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCPKeyReleased(evt);
            }
        });

        lblMdpError.setForeground(new java.awt.Color(240, 0, 0));

        lblCPError.setForeground(new java.awt.Color(240, 0, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(221, 221, 221)
                        .addComponent(txtConfirmMdp, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comVille, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(114, 114, 114)
                                .addComponent(comPays, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(165, 165, 165))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtAdresseP)
                                            .addComponent(txtMail, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(txtPrenom, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtDateNaiss, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCP))
                                        .addGap(56, 56, 56)
                                        .addComponent(lblCPError, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtmdp, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(lblMdpError, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(156, 156, 156))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnInscription, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(219, 219, 219)
                                .addComponent(btnConnexion)
                                .addGap(156, 156, 156)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(365, 365, 365)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(417, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel6)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3))
                    .addComponent(txtDateNaiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtAdresseP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCPError, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(comPays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comVille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel9)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMdpError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtmdp))
                        .addGap(31, 31, 31)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtConfirmMdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInscription)
                    .addComponent(btnConnexion)
                    .addComponent(jButton1))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(568, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 810, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtConfirmMdpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmMdpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmMdpActionPerformed

    private void btnInscriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscriptionActionPerformed
        // TODO add your handling code here:
        if(validation()){
            insertMember();
        }
    }//GEN-LAST:event_btnInscriptionActionPerformed

    private void txtmdpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdpKeyPressed
        // TODO add your handling code here:
        verifierMotDePasse();
    }//GEN-LAST:event_txtmdpKeyPressed

    private void txtmdpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdpKeyReleased
        // TODO add your handling code here:
        verifierMotDePasse();;
    }//GEN-LAST:event_txtmdpKeyReleased

    private void txtCPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPKeyReleased
        // TODO add your handling code here:
        verifierCP();
    }//GEN-LAST:event_txtCPKeyReleased

    private void txtCPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPKeyPressed
        // TODO add your handling code here:
        verifierCP();
    }//GEN-LAST:event_txtCPKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Index accueil = new Index();
        accueil.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnexionActionPerformed
        // TODO add your handling code here:
        ConnexionM membre = new ConnexionM();
        membre.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConnexionActionPerformed

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
            java.util.logging.Logger.getLogger(InscriptionMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InscriptionMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InscriptionMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InscriptionMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InscriptionMembre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnexion;
    private javax.swing.JButton btnInscription;
    private javax.swing.JComboBox comPays;
    private javax.swing.JComboBox comVille;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCPError;
    private javax.swing.JLabel lblMdpError;
    private javax.swing.JTextField txtAdresseP;
    private javax.swing.JTextField txtCP;
    private javax.swing.JPasswordField txtConfirmMdp;
    private com.toedter.calendar.JDateChooser txtDateNaiss;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtPrenom;
    private javax.swing.JPasswordField txtmdp;
    // End of variables declaration//GEN-END:variables
}
