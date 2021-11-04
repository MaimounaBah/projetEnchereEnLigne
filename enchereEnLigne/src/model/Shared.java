/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 33760
 */
public class Shared {
    
    private Shared(){
        
    }
    
    public static int userId ;
    
    public static int getUserId(){
        return userId;
    }
    
    public static void setUserId(int id){
        userId = id;
    }
    
     public static String nom, prenom ;
    
     public static String informations(){
         return nom + " " + prenom;
     }
     
     public static String informationsNom(){
         return nom;
     }
}
