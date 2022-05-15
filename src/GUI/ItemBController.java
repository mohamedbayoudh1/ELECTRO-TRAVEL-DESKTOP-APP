/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Bus;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ItemBController implements Initializable {

     @FXML
    private ImageView imga;
    @FXML
    private Label typeb;
    @FXML
    private Label prixb;
      private Bus bus;
    @FXML
    private Label nb;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void safe(Bus act){
          
      
      /*  noma.setText(agence.getNomagence());
        adressea.setText(agence.getAdresse());
        Image image = new Image(getClass().getResourceAsStream(agence.getImage()));
        imga.setImage(image);*/
        this.bus=act;
        
          typeb.setText(bus.getType());
        prixb.setText(String.valueOf(bus.getPrix()));
          nb.setText(String.valueOf(bus.getNbrPlace()));
       
       
       
          if(!act.getImage().isEmpty()){
            
       Image img = new javafx.scene.image.Image(act.getImage(), 179, 143, true, true);
            
            
        imga.setImage(img);
          
          }
       
       else{
           
          /* Image empty = new Image("/img/empty.jpg");
           imga.setFitHeight(200);
            imga.setFitWidth(200);
           imga.setImage(empty);*/
           
           
       
       }}}
      
         
     
      
    