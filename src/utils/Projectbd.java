/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author Asus
 */
public class Projectbd {
    
    public final String url="jdbc:mysql://localhost:3306/mahdi";
    public final String login="root";
    public final String pwd="";
    
    Connection connexion;
    public static Projectbd instance;
    
    private Projectbd(){
        try {
            connexion =DriverManager.getConnection(url, login, pwd);
            System.out.println("Cnx etablie ...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("ERREUR");
            
        }
    }
    
    
    public static Projectbd getInstance (){
        if (instance == null)
            instance = new Projectbd();
        
        return instance;
    }
    
    public Connection getCnx(){
        return connexion;
    }
    
    
    
}