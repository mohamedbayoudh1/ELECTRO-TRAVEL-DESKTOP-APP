/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package electrotravel;
import entity.Role;
import entity.User ;
import javax.xml.transform.Source;
import service.Serviceuser;
import utils.SmsApi;

/**
 *
 * @author Asus
 */                                                                                                                                                                                                                                                                                    //7
public class Electrotravel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Serviceuser su = new Serviceuser() ;
            
         SmsApi s = new SmsApi();
           s.sendSMS("Electro travel say Hello ! ","53001618");         
             
                                                                                                                                                                                                                                                                               //8
  
    }
    
}
