/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author 33760
 */
public class DBConnection {
    
    public static Connection getConnection(){
        Connection con = null;
        
        try{
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere", "root", "zhou");
        }catch(Exception e){
         e.printStackTrace();
        }
        return con;
    }
    
}
