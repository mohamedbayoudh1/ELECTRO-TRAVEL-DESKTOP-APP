/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Agence;
import entities.Bus;
import static entities.Bus.filename;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javax.swing.JOptionPane;
import services.serviceAgence;
import services.serviceBus;

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
  
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnajouterA(javafx.event.ActionEvent event) {
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
    }}

    


    @FXML
    private void btnafficherA(javafx.event.ActionEvent event) {
        
      try {
            Parent root =  FXMLLoader.load(getClass().getResource("/gui/Afficher.fxml"));
           Scene scene=new Scene(root);
           Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}

    @FXML
    private void btnajouterB(javafx.event.ActionEvent event) {
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
    }}

    @FXML
    private void btnafficherB(javafx.event.ActionEvent event) {
          try {
            Parent root =  FXMLLoader.load(getClass().getResource("/gui/AfficherB.fxml"));
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

        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.jpg"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            System.out.println(fc.getAbsoluteFile().toURI().toString());
            //Image i = new Image(img) {};
            Image i = new Image(img) ;
            
            imagefield.setImage(i);
            imagecomp = fc.toString();
            //System.out.println(imagecomp);
            int index = imagecomp.lastIndexOf('\\');
            if (index > 0) {
                filename = imagecomp.substring(index + 1);
            }

            Bus.filename = "C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\AgenceBus\\src\\img\\" + filename;
           
        }
        imagefield.setFitHeight(215);
        imagefield.setFitWidth(265);
        
        Bus.pathfile = fc.getAbsolutePath();
  
    }
 

    
}
