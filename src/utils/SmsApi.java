/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


/**
 *
 * @author Asus
 */
public class SmsApi {
   public static final String ACCOUNT_SID="AC6cba2e39cccb497206f1752a85930c16";
            public static final String AUTH_TOKEN="bc6a44dc90280402446bfde8a5593f2c";

    public  SmsApi (){
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    
    public void  sendSMS ( String sendTo,String msgText){
      
          Message message= Message.creator(
                  new PhoneNumber(sendTo),
                  new PhoneNumber("+18623297053")
                  , msgText).create();
    
}
}
