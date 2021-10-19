try{
            String query = "INSERT INTO membre (nom, prenom,dateNais,mail,adressePostal,CP,ville,pays,MotDePass,Statut,NbEtoile) VALUES (?,?,?,?,?,?,?,?,?,'membreSimple',0)";
             Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "");
            pst = con.prepareStatement(query);
            pst.setString(1, jtxtNom.getText());
            pst.setString(2, jtxtPrenom.getText());
            pst.setString(3, ((JTextField)jdateNaisse.getDateEditor().getUiComponent()).getText());
            pst.setString(4, jtxtAdresseElec.getText());
            pst.setString(5, jtxtCodePO.getText());
            pst.setString(6, jcombPays.getSelectedItem().toString());
            pst.setString(7, jCombVille.getSelectedItem().toString());
            pst.setString(8, jpassMdp.getText());
            pst.setString(9, jpassConfirMDP.getText());
            if(jpassMdp.getText() == null ? jpassConfirMDP.getText() == null : jpassMdp.getText().equals(jpassConfirMDP.getText())){
              
                pst.executeUpdate();
                //JOptionPane.showMessageDialog(null, "Inscription effectuée avec succès");
                int response = JOptionPane.showConfirmDialog(null, "Inscription réussie. Se connecter?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response ==JOptionPane.YES_OPTION){
                    FConnexion connexion = new FConnexion();
                    connexion.setVisible(true);
                    this.setVisible(false);
                }else if(response == JOptionPane.NO_OPTION){
                    this.setVisible(true);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Attention vos deux mots de passe ne correspondent pas");
            }