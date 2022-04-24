/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Bus;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.serviceBus;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherBController implements Initializable {

  @FXML
  private TableView<Bus>TableView;
   @FXML
  private TableColumn<Bus,String>ttype;
    @FXML
  private TableColumn<Bus,Integer>tnbrdeplace;
       @FXML
  private TableColumn<Bus,   Integer>tprix;
     @FXML
  private TableColumn<Bus,String>timage;
     ObservableList list;
    @FXML
    private TextField typeup;
    @FXML
    private TextField nbrdeplaceup;
    @FXML
    private TextField prixup;
    @FXML
    private Button modB;
   
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     afficherB();
    }   

    @FXML
    private void btnsupprimerB(ActionEvent event) throws SQLException{
       
    if (TableView.getSelectionModel().isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        JOptionPane.showMessageDialog(null,"Aucun bus n'est selectionnée ,veuillez choisir un bus");
     }else{
   int responce=JOptionPane.showConfirmDialog(null, "Attention vous allez supprimer le bus sélectionné etes-vous sur ?","Suppression",JOptionPane.YES_NO_OPTION);
            if (responce==JOptionPane.YES_OPTION){
           serviceBus sb = new serviceBus();
                    Bus b = (Bus) TableView.getSelectionModel().getSelectedItem();
                     sb.SupprimerBus(b);
           
          Alert alert = new Alert(Alert.AlertType.INFORMATION);

         alert.setContentText("Votre bus a été bien supprime");
                  JOptionPane.showMessageDialog(null,"bus supprimé");
           //  afficherB();
            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre bus a été bien supprime");
                 JOptionPane.showMessageDialog(null,"Suppression annulé");
            }
           // afficherB();
          
    }}
    public void afficherB(){
         try{
        serviceBus sb= new serviceBus();
        List<Bus> buses = sb.afficherBus();
        list =FXCollections.observableArrayList(buses);
        TableView.setItems(list);
          
        ttype.setCellValueFactory(new PropertyValueFactory<>("type"));
         tnbrdeplace.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
         tprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          timage.setCellValueFactory(new PropertyValueFactory<>("image"));
          
      } catch (SQLException ex){
          System.out.println(ex.getMessage());
      }
        
    }

    @FXML
    private void btnmodB(ActionEvent event) {
         if(typeup.getText().equals("") || nbrdeplaceup.getText().equals("")||prixup.getText().equals("")){ 
            JOptionPane.showMessageDialog(null, "veuillez remplir les champs vides");}
         else if(!(typeup.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier le type de bus");
             }
            else if (!(prixup.getText().matches("^[0-9]+$"))){
                     JOptionPane.showMessageDialog(null, "Le prix est non Valide");}
           else if (!(nbrdeplaceup.getText().matches("^[0-9]+$"))){
                     JOptionPane.showMessageDialog(null, "Le nombre de places du bus est non Valide");}
           else{
        
        
        Bus b = TableView.getSelectionModel().getSelectedItem();

        b.setType(typeup.getText());

        b.setNbrPlace(Integer.valueOf(nbrdeplaceup.getText()));

       
        b.setPrix(Integer.valueOf(prixup.getText()));

        // M.setImage(imagecomp);
        serviceBus sb = new serviceBus();

        try {

            sb.ModifierBus(b);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.show();
            alert.setTitle("updated !");
            alert.setContentText("updated succesfully");
          

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
            alert.setTitle("fail !");
            alert.setContentText("failed ");

        }
     
        afficherB() ;
     
       
    }}

   
    @FXML
    private void selectB(MouseEvent event) {
         Bus b = TableView.getSelectionModel().getSelectedItem();

        typeup.setText(b.getType());
        
          
            nbrdeplaceup.setText(String.valueOf(b.getNbrPlace()));
            
              prixup.setText(String.valueOf(b.getPrix()));
    }

    @FXML
    private void btnstat(ActionEvent event) throws SQLException {
        
        
           try {
            serviceBus sb = new serviceBus();
        List<Bus> buses = sb.afficherBus();
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("StatBus.fxml"));
           AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
    }
    }
    
  

    
    

