/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI ;
import entity.Mail ;
import static entity.Mail.sendMail;
import entity.Role;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.management.Notification;
import service.Serviceuser;
import static sun.security.jgss.GSSUtil.login;
import utils.Projectbd;
import utils.Session;
import utils.SmsApi;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField numtel_user;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private TextField adresse_user;
    @FXML
    private AnchorPane arlogin;
    @FXML
    private AnchorPane arsignup;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Button oublier;
    @FXML
    private TextField email1;
    @FXML
    private Button envoyermdp;
    @FXML
    private AnchorPane mdpoublier;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
  
 Serviceuser su = new Serviceuser() ;
    @FXML
    private void ajouter(ActionEvent event) throws Exception {
          
              StringBuilder errors=new StringBuilder();
        
         
        
         if(prenom.getText().length()<3 || numtel_user.getText().length()<3 || mdp.getText().length()<3){
                    if(mdp.getText().length()<3){
                        errors.append("Votre Mot De Pass doit comporter au moins 3 caractères");
                      
                    }
                    if(nom.getText().length()<3){
                        errors.append("Votre Nom doit comporter au moins 3 caractères");
                        
                    }
                    if(prenom.getText().length()<3){
                        errors.append("Votre Prenom doit comporter au moins 3 caractères\n");
                        
                    }
         }
              if(prenom.getText().trim().isEmpty()){
            errors.append("entre votre prenom\n");
        }
        if(numtel_user.getText().trim().isEmpty() ){
            errors.append("entre votre num tel\n");
        }
        if(nom.getText().trim().isEmpty()){
            errors.append("entre votre nom \n");
        }
        if(email.getText().trim().isEmpty()){
            errors.append("entre votre email\n");
        }
        if(mdp.getText().trim().isEmpty()){
            errors.append("entre votre mdp\n");
        }
        if(adresse_user.getText().trim().isEmpty() ){
            errors.append("entre votre adress user\n");
        }
        
        if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else{
            User u =new User();
            u.setNom(nom.getText());
            u.setPrenom(prenom.getText());
           
            u.setAdresse_user(adresse_user.getText());
            u.setEmail(email.getText());
            u.setMdp(mdp.getText());
             u.setNumtel_user(Integer.parseInt(numtel_user.getText()));
                  SmsApi sms= new SmsApi();
          sms.sendSMS("+216"+Integer.toString(u.getNumtel_user()), "Bienvenue A ElectroTravel  mr "+u.getNom()+" "+u.getPrenom());
                                                                                                                                         
            u.setRole(Role.CLIENT);
           su.ajouteruser(u);
           Alert alertajout = new Alert(Alert.AlertType.INFORMATION);
                alertajout.setHeaderText("inscription avec succes! ");
                alertajout.showAndWait();
                
                email.setText("");
                nom.setText("");
                prenom.setText("");
                mdp.setText("");
                adresse_user.setText("");
                numtel_user.setText("");
                
                   arlogin.setVisible(true);
        arsignup.setVisible(false);
        
        Mail.sendMail(u.getEmail(), u.getNom());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("votre compte a éte crée avec succées veuillez consulter votre compte un email sera envoyé ! ");
                alert.showAndWait();
                   arlogin.setVisible(true);
        arsignup.setVisible(false);                                                                                                                                               
        }
      
        
    
    
    }

    @FXML
    private void login(ActionEvent event) {
        arlogin.setVisible(true);
        arsignup.setVisible(false);
        mdpoublier.setVisible(false);
    }

    @FXML
    private void signup(ActionEvent event) {
        arlogin.setVisible(false);
        arsignup.setVisible(true);
    }
    
    
    //
    
@FXML 
 private void loginn(ActionEvent event) throws IOException {
   
    
        User u = new User();

        u.setEmail(username.getText());
        u.setMdp(password.getText());
        
        if (su.login(u.getEmail(), u.getMdp())) {

           
            Parent home_page_parent;
           
            if (su.checkRole(username.getText()).equals("Admin"))  {
//               Session.getFirstInstance(Session.getUser());
//               
//                    System.out.println(Session.getUser().getId_user());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Welcome Admin");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/admininterface.fxml"));
                home_page_parent = loader.load();
                AdmininterfaceController ad = loader.getController();
                username.getScene().setRoot(home_page_parent);


            } 
        else    if (su.checkRole(username.getText()).equals("Client"))  {
                 //Session.getFirstInstance(Session.getUser());
               //  System.out.println(Session.getUser().id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Accessed as User !  ");
                alert.showAndWait();

           
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/clientinterface.fxml"));
                home_page_parent = loader.load();
                ClientinterfaceController da = loader.getController();
                username.getScene().setRoot(home_page_parent);

            }
         
    
        }
 else   
 {
  
      Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("veuillez inserer un mdp ou email valides ");
       alert.showAndWait();
     
     
 }
 
        
        
        
           
 }

 
    @FXML
    private void oublier(ActionEvent event) {
     mdpoublier.setVisible(true);
        arlogin.setVisible(false);
        
    }

    @FXML
    private void envoyermdp(ActionEvent event) throws Exception {
      StringBuilder erreur=new StringBuilder();  
if(email1.getText().equals("") || !email1.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
        {
            erreur.append("Il faut saisir une adresse mail!");
            

        }
        else
        {
          Connection cnx = Projectbd.getInstance().getCnx();
        System.out.println("connectNow = "+cnx.toString());
        
        String verifyLogin = "select  mdp from user where email= '"+email1.getText()+"'";
            System.out.println("email: " + email1.getText());
       
            Statement stm =cnx.createStatement();
        
            System.out.println(verifyLogin);
            ResultSet resultat = stm.executeQuery(verifyLogin);
            while (resultat.next()) 
            {
                
                    
                    
                    String mdp = resultat.getString(1);
                    System.out.println(email1.getText());
                  
                    sendMail(email1.getText(),mdp);
                    
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("un mail sera envoyé a votre email saisie ");
                alert.showAndWait();
                    
                    mdpoublier.setVisible(true);
                    arlogin.setVisible(false);
                    
                    
                }
        }
               
                
            
        
        
    }
       
    
    
    
    }
 


 
 
 
        
    
    

