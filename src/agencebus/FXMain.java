/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agencebus;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author oussa
 */
public class FXMain extends Application {

    Stage stage;
    Parent parent;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/Gui/GestionAgenceBus.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("Afficher");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
