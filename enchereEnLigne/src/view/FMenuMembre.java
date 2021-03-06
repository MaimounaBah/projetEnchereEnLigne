/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import entity.Article;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.DBConnection;
import model.Shared;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author milop
 */
public class FMenuMembre extends javax.swing.JFrame {
    public int idMembre;
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pst = null;
    /**
     * Creates new form FMenuAcheteur
     */
    
    DefaultTableModel model;
    public FMenuMembre(int idmembre) {
        
        initComponents();
        this.idMembre = idmembre;
        termineVent();
        show_article();
        show_histoV();
        show_achatV();
        jinfomMembre.setText(Shared.informations());
        //TO DO Update according to DateCloture all the articles
        
    }
   
    public void termineVent(){
        String requete = "UPDATE article a SET a.etat = 'Fini' WHERE date_format(dateCloture,'%Y-%m-%d') = date_format(sysdate(),'%Y-%m-%d')";
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(requete);
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     public void recherche(String str){
        model = (DefaultTableModel)jTableArticle.getModel();
        TableRowSorter<DefaultTableModel>  trs = new TableRowSorter<>(model);
        jTableArticle.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        
    }
    public ArrayList<Article> resList(){
        ArrayList<Article> artList= new ArrayList<>();
       
        try {
            String requete = "SELECT idarticle,titreAnnonce,prixDep,prixReserve,dateCloture,prixAchatImme FROM article where etat = 'encours d''ench??re'";
            conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            Article a;
            while (rs.next()){
                a=new Article(rs.getInt("idarticle"),rs.getString("titreAnnonce"),rs.getFloat("prixDep"),rs.getFloat("prixReserve"),rs.getDate("dateCloture"),rs.getFloat("prixAchatImme"));
                artList.add(a);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return artList;
    }
    
    public void show_article(){
        ArrayList<Article> list = resList();
        DefaultTableModel model = (DefaultTableModel)jTable_cherche.getModel();
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdarticle();
            row[1] = list.get(i).getTitreAnnonce();
            row[2] = list.get(i).getPrixDep();
            row[3] = list.get(i).getPrixReserve();
            row[4] = list.get(i).getDateCloture();
            row[5] = list.get(i).getPrixAchatImme();
            model.addRow(row);
        }
    }
    
    public ArrayList<Article> histoList(){
            ArrayList<Article> artList= new ArrayList<>();
            Connection conn = null;
            try {
                String requete = "SELECT idarticle,titreAnnonce,prixDep,prixReserve,dateCloture,etat FROM article where idvendeur = "+this.idMembre;
                conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(requete);
                Article a;
                while (rs.next()){
                    a=new Article(rs.getInt("idarticle"),rs.getString("titreAnnonce"),rs.getFloat("prixDep"),rs.getFloat("prixReserve"),rs.getDate("dateCloture"),rs.getString("etat"));
                    artList.add(a);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            return artList;
        }
    
        public void show_histoV(){
            ArrayList<Article> list = histoList();
            DefaultTableModel model = (DefaultTableModel)jTableHitoV.getModel();
            Object[] row = new Object[6];
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getIdarticle();
                row[1] = list.get(i).getTitreAnnonce();
                row[2] = list.get(i).getPrixDep();
                row[3] = list.get(i).getPrixReserve();
                row[4] = list.get(i).getDateCloture();
                row[5] = list.get(i).getEtat();
                model.addRow(row);
            }
        }
    
         public void rechercheArticle(String str){
        model = (DefaultTableModel)jTableHitoV.getModel();
        TableRowSorter<DefaultTableModel>  trs = new TableRowSorter<>(model);
        jTableHitoV.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        
    }
         
    public void rechercheArticleVente(String str) {
        model = (DefaultTableModel)jTableArticle.getModel();
        TableRowSorter<DefaultTableModel>  trs = new TableRowSorter<>(model);
        jTableArticle.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
       
    }  
    
    public void rechercheArtMem(String str){
        model = (DefaultTableModel)jTable_cherche.getModel();
        TableRowSorter<DefaultTableModel>  trs = new TableRowSorter<>(model);
        jTable_cherche.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }
         
    private void miseAjourTableHistoriqueVente(){
        String sql = "SELECT idarticle, titreAnnonce, prixAchatImme, prixDep, prixReserve, dateCloture, pasEnchere, libelleCat, etat FROM article a,categorie c WHERE a.idCategorie = c.idCategorie ";
       try{
            conn = DBConnection.getConnection();
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            jTableHitoV.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
        
    }
    
    private void selectedRow(){
        
        ListSelectionModel model1 = jTableHitoV.getSelectionModel();
        model1.addListSelectionListener(new ListSelectionListener() {
        
             
            @Override
            public void valueChanged(ListSelectionEvent e) {
                 
                if(!model1.isSelectionEmpty()){
                    String sql = "SELECT descAnnonce, fraisPort,prenom, region FROM article a, membre m,regionlivraison r WHERE a.idVendeur = m.idmembre AND r.idregionlivraison = a.idRegionLiv";
                   try{
                       conn = DBConnection.getConnection();
                       pst = conn.prepareStatement(sql);
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
    
public ArrayList<Article> achatList(){
            ArrayList<Article> artList= new ArrayList<>();
            Connection conn = null;
            try {
                String requete = "SELECT idarticle,titreAnnonce,prixDep,prixReserve,dateCloture,etat FROM article where idarticle in (select ac.idArticle from achatimmediat ac where ac.idMembre = "+this.idMembre+")";
                conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(requete);
                Article a;
                while (rs.next()){
                    a=new Article(rs.getInt("idarticle"),rs.getString("titreAnnonce"),rs.getFloat("prixDep"),rs.getFloat("prixReserve"),rs.getDate("dateCloture"),rs.getString("etat"));
                    artList.add(a);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            return artList;
        }
    
        public void show_achatV(){
            ArrayList<Article> list = achatList();
            DefaultTableModel model = (DefaultTableModel)jTableArticle.getModel();
            Object[] row = new Object[5];
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getIdarticle();
                row[1] = list.get(i).getTitreAnnonce();
                row[2] = list.get(i).getPrixDep();
                row[3] = list.get(i).getPrixReserve();
                row[4] = list.get(i).getDateCloture();
                model.addRow(row);
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
        tab1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tab5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jinfomMembre = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        pMiseEnVente1 = new view.PMiseEnVente();
        JPannelHistoVente = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHitoV = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnSupprimer = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtIdSelectinneArticle = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtRecherche = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_cherche = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtRechercheArt = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableArticle = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtRechercheVente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        tab1.setBackground(new java.awt.Color(0, 51, 102));
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab1MouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Vendre un produit");

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        tab2.setBackground(new java.awt.Color(0, 51, 102));
        tab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab2MouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Historique vente");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Rechercher article");

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        tab5.setBackground(new java.awt.Color(0, 51, 102));
        tab5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab5MouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Mes ench??res et achat");

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        jinfomMembre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jinfomMembre.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin.png"))); // NOI18N

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 51, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jButton3.setText("Deconnexion");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jinfomMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jinfomMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(136, 136, 136)
                .addComponent(tab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pMiseEnVente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(pMiseEnVente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel3);

        JPannelHistoVente.setBackground(new java.awt.Color(255, 255, 255));

        jTableHitoV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titre", "PrisDep", "PrixRes", "Datecloture", "Etat"
            }
        ));
        jTableHitoV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHitoVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableHitoV);

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Rechercher un article");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSupprimer.setBackground(new java.awt.Color(153, 153, 153));
        btnSupprimer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Id");

        txtIdSelectinneArticle.setEditable(false);
        txtIdSelectinneArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSelectinneArticleActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel9.setText("Sasissez le nom de l'article");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setText("Sasissez le nom de l'article");

        txtRecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JPannelHistoVenteLayout = new javax.swing.GroupLayout(JPannelHistoVente);
        JPannelHistoVente.setLayout(JPannelHistoVenteLayout);
        JPannelHistoVenteLayout.setHorizontalGroup(
            JPannelHistoVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPannelHistoVenteLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(JPannelHistoVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPannelHistoVenteLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(txtRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPannelHistoVenteLayout.createSequentialGroup()
                        .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdSelectinneArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(JPannelHistoVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JPannelHistoVenteLayout.createSequentialGroup()
                    .addGap(340, 340, 340)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(341, Short.MAX_VALUE)))
        );
        JPannelHistoVenteLayout.setVerticalGroup(
            JPannelHistoVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPannelHistoVenteLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(JPannelHistoVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(JPannelHistoVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdSelectinneArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPannelHistoVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(JPannelHistoVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JPannelHistoVenteLayout.createSequentialGroup()
                    .addGap(363, 363, 363)
                    .addComponent(jLabel9)
                    .addContainerGap(355, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", JPannelHistoVente);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Rechercher un article");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable_cherche.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idArticle", "Titre", "Prix Dep", "Prix Res", "Datecloture", "AchatImme"
            }
        ));
        jScrollPane2.setViewportView(jTable_cherche);

        jButton6.setBackground(new java.awt.Color(0, 153, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Proposer un prix / Acheter");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel17.setText("Sasissez le nom de l'article");

        txtRechercheArt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheArtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(txtRechercheArt, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtRechercheArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("tab3", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTableArticle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idArticle", "Titre", "PrisDep", "PrixRes", "Datecloture"
            }
        ));
        jScrollPane3.setViewportView(jTableArticle);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel15.setText("Sasissez le nom de l'article");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel16.setText("Sasissez le nom de l'article");

        txtRechercheVente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRechercheVenteActionPerformed(evt);
            }
        });
        txtRechercheVente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheVenteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16)
                        .addGap(98, 98, 98)
                        .addComponent(txtRechercheVente, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtRechercheVente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 152, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab4", jPanel6);

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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 746, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1382, 746));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tab1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseEntered
        tab1.setBackground(new Color(50, 84, 153));
        tab1.setOpaque(true);
    }//GEN-LAST:event_tab1MouseEntered

    private void tab1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseExited
        tab1.setBackground(new Color(0, 51, 102));
    }//GEN-LAST:event_tab1MouseExited

    private void tab2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseEntered
        tab2.setBackground(new Color(50, 84, 153));
        tab2.setOpaque(true);
    }//GEN-LAST:event_tab2MouseEntered

    private void tab2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseExited
        tab2.setBackground(new Color(0, 51, 102));
    }//GEN-LAST:event_tab2MouseExited

    private void tab3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseEntered
        tab3.setBackground(new Color(50, 84, 153));
        tab3.setOpaque(true);
    }//GEN-LAST:event_tab3MouseEntered

    private void tab3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseExited
        tab3.setBackground(new Color(0, 51, 102));
    }//GEN-LAST:event_tab3MouseExited

    private void tab5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseEntered
        tab5.setBackground(new Color(50, 84, 153));
        tab5.setOpaque(true);
    }//GEN-LAST:event_tab5MouseEntered

    private void tab5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseExited
        tab5.setBackground(new Color(0, 51, 102));
    }//GEN-LAST:event_tab5MouseExited

    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_tab1MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_tab2MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_tab3MouseClicked

    private void tab5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseClicked
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_tab5MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int i = jTable_cherche.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTable_cherche.getModel();
        int idArticle = Integer.parseInt( model.getValueAt(i,0).toString());
        FProposerPrixAchat proposerPrixAchat = new FProposerPrixAchat(idArticle);
        proposerPrixAchat.setVisible(true);
        proposerPrixAchat.pack();
        proposerPrixAchat.idMembre = this.idMembre;
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        // TODO add your handling code here:
        String sql = "DELETE  FROM article WHERE idarticle = ? AND idarticle IN (SELECT * FROM posseder )" ;

        try{
            conn = DBConnection.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtIdSelectinneArticle.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Article supprim??");
            conn.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        miseAjourTableHistoriqueVente();
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void txtIdSelectinneArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSelectinneArticleActionPerformed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_txtIdSelectinneArticleActionPerformed

    private void txtRechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheKeyReleased
        // TODO add your handling code here:
        String chaineChercher = txtRecherche.getText();
        rechercheArticle(chaineChercher);
    }//GEN-LAST:event_txtRechercheKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
            conn.close();
            Shared.setUserId(0);
            Index accueil = new Index();
            accueil.setVisible(true);
            this.dispose();
        }catch(Exception e){

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTableHitoVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHitoVMouseClicked
        // TODO add your handling code here:
        
         try{
            int row = jTableHitoV.getSelectedRow();
            String table_click = (jTableHitoV.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM article WHERE idArticle = " + table_click;
            conn = DBConnection.getConnection();
            pst = conn.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                String id = rs.getString("idarticle");
                txtIdSelectinneArticle.setText(id);
            }
            
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTableHitoVMouseClicked

    private void txtRechercheVenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheVenteKeyReleased
        // TODO add your handling code here:
        String chaineChercher = txtRechercheVente.getText();
        rechercheArticleVente(chaineChercher);
    }//GEN-LAST:event_txtRechercheVenteKeyReleased

    private void txtRechercheVenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRechercheVenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheVenteActionPerformed

    private void txtRechercheArtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheArtKeyReleased
        // TODO add your handling code here:
        String chaineChercher = txtRechercheArt.getText();
        rechercheArtMem(chaineChercher);
    }//GEN-LAST:event_txtRechercheArtKeyReleased

    
    
    
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
            java.util.logging.Logger.getLogger(FMenuMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FMenuMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FMenuMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FMenuMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FMenuMembre(this.idmembre).setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPannelHistoVente;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableArticle;
    private javax.swing.JTable jTableHitoV;
    private javax.swing.JTable jTable_cherche;
    private javax.swing.JLabel jinfomMembre;
    private view.PMiseEnVente pMiseEnVente1;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab5;
    private javax.swing.JTextField txtIdSelectinneArticle;
    private javax.swing.JTextField txtRecherche;
    private javax.swing.JTextField txtRechercheArt;
    private javax.swing.JTextField txtRechercheVente;
    // End of variables declaration//GEN-END:variables
}
