/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import service.serviceAgence;
import service.serviceBus;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class StatBusController implements Initializable {

    @FXML
    private PieChart statA;
    serviceBus sb =new serviceBus();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<PieChart.Data> pieChartData;
        try {
           

            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("comfort", sb.pendingType()),
                    new PieChart.Data("moyenne", sb.confirmedType()),
                    new PieChart.Data("mini", sb.cancelledType())
            );
           statA.setData(pieChartData);
       

        } catch (SQLException ex) {
            Logger.getLogger(StatBusController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       /* {
      try {
           
            String query = "SELECT COUNT(*),categories FROM abonnement GROUP BY categories" ;

             PreparedStatement PreparedStatement = connexion.prepareStatement(query);
             PreparedStatement pst=connexion.prepareStatement(query);

             ResultSet rst= PreparedStatement.executeQuery();
           
            while (rst.next()){              
               rs.add(new PieChart.Data(rst.getString("categories"),rst.getInt("COUNT(*)")));
            }    
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAbonneController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        stat_abon.setTitle("**Statistiques nombres des catégories**");
        stat_abon.setLegendSide(Side.LEFT);
        stat_abon.setData(Data);
        }*/
    }    
     
    }    

        
    
    

