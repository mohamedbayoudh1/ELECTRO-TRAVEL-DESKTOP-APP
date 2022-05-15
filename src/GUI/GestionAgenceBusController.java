/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Agence;
import entity.Bus;
import static entity.Bus.filename;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import service.serviceAgence;
import service.serviceBus;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class GestionAgenceBusController implements Initializable {
 
    @FXML
    private TextField tnom;
    @FXML
    private TextField tadresse;
    @FXML
    private TextField tnumtel;
    @FXML
    private TextField tnumbus;
     @FXML
    private TextField ttype;
    @FXML
    private TextField tnbrdeplace;
    @FXML
    private TextField tprix;
   
    

    
   // serviceAgence sa=new serviceAgence();
    ObservableList<Agence> list =FXCollections.observableArrayList();
    @FXML
    private Tab Agence;
    @FXML
    private ImageView imagefield;
     public String imagecomp; 
    @FXML
    private TextField Email;
  
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnajouterA(javafx.event.ActionEvent event) throws AWTException {
        if(tnom.getText().equals("") || tadresse.getText().equals("")||tnumtel.getText().equals("")||tnumbus.getText().equals("")){ 
            JOptionPane.showMessageDialog(null, "veuillez remplir les champs vides");}
            else if (!(tnumtel.getText().matches("^[0-9]+$"))){
                     JOptionPane.showMessageDialog(null, "Le numéro de telephone est non Valide");}
           
               else if (tnumtel.getText().length()!=8){
                     JOptionPane.showMessageDialog(null, "Le Numero de telephone doit contenir 8 chiifres");}
                 else if (!(tnumbus.getText().matches("^[0-9]+$"))){
                     JOptionPane.showMessageDialog(null, "Le Nombre de Bus n'est pas Valide");}
                    
                    else{
          Agence a = new Agence(tnom.getText(),tadresse.getText(),Integer.valueOf(tnumtel.getText()),Integer.valueOf(tnumbus.getText()));
     serviceAgence s = new serviceAgence();
        try {
            sendMail();
            s.ajouterAgence(a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Agence ajoutée");
            alert.show();
            tnom.setText("");
            tadresse.setText("");
            tnumtel.setText("");
            tnumbus.setText("");
           
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
             java.awt.Image images = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(images, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Electro Team", "Agence ajouté", TrayIcon.MessageType.INFO);
    }}

    


    @FXML
    private void btnafficherA(javafx.event.ActionEvent event) {
        
      try {
            Parent root =  FXMLLoader.load(getClass().getResource("/GUI/Afficher.fxml"));
           Scene scene=new Scene(root);
           Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}

    @FXML
    private void btnajouterB(javafx.event.ActionEvent event) throws AWTException {
        String image=Bus.filename ;
         if(ttype.getText().equals("") || tnbrdeplace.getText().equals("")||tprix.getText().equals("")){ 
            JOptionPane.showMessageDialog(null, "veuillez remplir les champs vides");}
         else if(!(ttype.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier le type de bus");
             }
            else if (!(tprix.getText().matches("^[0-9]+$"))){
                     JOptionPane.showMessageDialog(null, "Le prix est non Valide");}
           else if (!(tnbrdeplace.getText().matches("^[0-9]+$"))){
                     JOptionPane.showMessageDialog(null, "Le nombre de places du bus est non Valide");}
           else{
           Bus b = new Bus(ttype.getText(),Integer.valueOf(tnbrdeplace.getText()),Integer.valueOf(tprix.getText()),image);
     serviceBus sb = new serviceBus();
        try {
            
            sb.ajouterBus(b);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Bus ajoutée");
            alert.show();
            ttype.setText("");
            tnbrdeplace.setText("");
            tprix.setText("");
           
           
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
             java.awt.Image images = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(images, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Electro Team", "Bus ajouté", TrayIcon.MessageType.INFO);

    }}

    @FXML
    private void btnafficherB(javafx.event.ActionEvent event) {
          try {
            Parent root =  FXMLLoader.load(getClass().getResource("/GUI/AfficherB.fxml"));
           Scene scene=new Scene(root);
           Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
    @FXML
    private void btnupimage(javafx.event.ActionEvent event) {
         
             FileChooser f = new FileChooser();
        String img;

        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.jpg","*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
           
            Image i = new Image(img) {};
           // Image i = new Image(img) ;
            
            imagefield.setImage(i);
            imagecomp = fc.toString();
            //System.out.println(imagecomp);
         ////   int index = imagecomp.lastIndexOf('\\');
          //  if (index > 0) 
          
              //  filename = imagecomp.substring(index + 1);
            

            Bus.filename =img ;
           
        }
        imagefield.setFitHeight(215);
        imagefield.setFitWidth(265);
        
        Bus.pathfile = fc.getAbsolutePath();
  
    }
    public void sendMail(){
      try{
           String host ="smtp.gmail.com" ;
            String user = "agencebus123@gmail.com";
            String pass = "guitar2020";
            String to =Email.getText();
            String from ="mahdi.jemal2011@gmail.com";
            String subject = null;
            String  messageText = null;
            //equals("mauvaise")
            if(FindTextLine()){
           // if (description_reclamation.getText().matches("mauvaise")) {
            subject = "Agence ajouté ";
             messageText= "Electro TEAM\n Cher,client," +
                    "Nous Somme Collaboré avec une nouvelle agence :\n" +tnom.getText()
                   + "\n profitez bien.\n"+"elle se trouve a  "+tadresse.getText()+"\n elle a "+tnumbus.getText()+"bus de types differents\n"
                     +"Vous pouver la contacté par telephone:"+tnumtel.getText()
                   + "" + "\n En vous souhaitant une agréable journée\n\n ELectro Team \n\n" 
                      +"-Cordialement-\n";
           
            }
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.ssh.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new java.util.Date());
            msg.setText(messageText);
           javax.mail.Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
       
       
       
       
    }
    }

    public TextField getEmail() {
        return Email;
    }

    public void setEmail(TextField Email) {
        this.Email = Email;
    }
        public boolean FindTextLine(){
        String[] words = {"mauvaise", "mauvais","insatisfait","décu"};
        String experience_des = tnom.getText();
        CharSequence c = words.toString();
        if(experience_des.contains(c)){
            //System.out.println("");    
       
        return false;
    }return true;
       
}
 

    
}
