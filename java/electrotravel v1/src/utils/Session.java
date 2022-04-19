/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.User;
import service.Serviceuser;

/**
 *
 * @author Asus
 */
public class Session {
    
private static final  Serviceuser su = new Serviceuser() ;
    
    private static Session instance = null;
    private  static User user = null;


  
    
    private Session(User userConnected) {
        super();
        Session.user = userConnected;
    }
    
    public final static Session getInstance(User userConnected) {

        if (Session.instance == null) {
            Session.instance = new Session(userConnected);

        }
        System.out.println(Session.instance);
        return Session.instance;
    }
    
    public final static Session getFirstInstance(User userConnected) {

        if (Session.instance == null) {

            Session.instance = new Session(userConnected);
         //   System.out.println(userConnected.getId());
      
        }
        return Session.instance;
    }

    public static Serviceuser getFs() {
        return su;
    }

    public static Session getInstance() {
        return instance;
    }

    public static User getUser() {
        return user;
    }

    public Session() {
    }


    public static void setUser(User user) {
        Session.user = user;
    }


   
 
    

}


