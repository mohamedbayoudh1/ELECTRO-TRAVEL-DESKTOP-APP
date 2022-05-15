/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Bus;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.geometry.Insets ;

import service.serviceBus;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FrontController implements Initializable {

   @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            allact();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()) ;
        } catch (IOException ex1) {
ex1.getMessage()      ;  }
    }  
    
     public void allact() throws SQLException, IOException{
    
       int column =2;
        int row = 1;
        service.serviceBus actS = new serviceBus();
        try {
          
            List<Bus> la = actS.afficherBus();
            System.out.println(la);
            //for (Activitie actt : actS.afficheractivities())
            for (int i = 0; i < la.size(); i++)
            {
                 
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/itemB.fxml"));
             
                AnchorPane anchorPane = fxmlLoader.load();
                
             
                
           //     ItemFXMLController itemController = fxmlLoader.getController();
                   System.out.println(la.get(i).getImage());
             //   itemController.safe(la.get(i));
             ItemBController itemController = fxmlLoader.getController();
             itemController.safe(la.get(i));
                                
                
                if (column == 2) {
                    column = 0;
                    row++;
                }
                
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(anchorPane, new Insets(30));
                
            }
        } catch (IOException e) {
            System.out.println(e);
        }     
     


     
     
    }
    
    
}
