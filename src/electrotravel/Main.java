/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrotravel;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import static entity.Mail.sendMail;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
                                                                                                                                                                                                                        //import utils.SmsApi;
                                                                                                                                                                                                                       //import static utils.SmsApi.ACCOUNT_SID;
//import static utils.SmsApi.AUTH_TOKEN;


/**
 *
 * @author                  
 */
public class Main extends Application {
    
    
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
          
       try {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
        Scene scene =new Scene(root);
        primaryStage.setTitle("Login!");
        primaryStage.setScene(scene);
        primaryStage.show();
   
    }
catch(IOException ex )
{  System.out.println(ex.getMessage());}
       
       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
