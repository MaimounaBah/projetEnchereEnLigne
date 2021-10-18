/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import view.FAccueil;


/**
 *
 * @author 33760
 */
public class Main {
    
    public static void main(String[] args){
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/enchere", "root", "GuisseFatouBailo2016");
            if(conn !=null){
                FAccueil accueil = new FAccueil();
		accueil.setVisible(true);
            }      
        }catch(Exception e){
            System.out.println(e.getMessage());
             System.out.println("Echec de la connexion !");   
        }
    }
    
}
