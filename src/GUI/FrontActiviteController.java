/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXMasonryPane;
import entity.Activite;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.CrudActivite;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class FrontActiviteController implements Initializable {

    @FXML
    private Pane PaneBlur;
    @FXML
    private ScrollPane scrollPane;
    private final JFXMasonryPane mansoryPane = new JFXMasonryPane();
    ////
    Activite rec = new Activite();
    CrudActivite work = new CrudActivite();
    ////
    @FXML
    private StackPane StckFrontActivite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        scrollPane.setStyle("-fx-background: rgb(255,255,255);\n -fx-background-color: rgb(255,255,255)");
        initMansoryCard();
        LoadCardActivites();
    }

    private void initMansoryCard() {
        mansoryPane.setPadding(new Insets(15, 15, 15, 15));
        mansoryPane.setVSpacing(5);
        mansoryPane.setHSpacing(5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mansoryPane);

    }

    private void LoadCardActivites() {

        mansoryPane.getChildren().clear();
        List<Activite> listeActivites = new ArrayList<>();
        listeActivites = work.AfficherAllActivite(rec);

        if (!listeActivites.isEmpty()) {
            for (int i = 0; i < listeActivites.size(); i++) {
                VBox root = new VBox();

                String nom = listeActivites.get(i).getNomActivite();
                String Type = listeActivites.get(i).getTypeActivite();
                Date Date = listeActivites.get(i).getDateActivite();
                String Heure = listeActivites.get(i).getHeureActivite();
                String Duree = listeActivites.get(i).getDureeActivite();

                root.setPadding(new Insets(12, 17, 17, 17));
                root.setSpacing(13);

                ///
                root.setStyle("-fx-background-color: #fff; -fx-background-radius: 15px;-fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                //labels[i].setTextFill(Color.color(1, 0, 0));

                Label LabelNom = new Label("" + nom);
                Label LabelType = new Label("" + Type);
                LabelType.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setStyle("-fx-font-weight: bold");
                //
                Label LabelDate = new Label("" + nom);
                Label LabelHeure = new Label("" + Heure);
                Label LabelDuree = new Label("" + Duree);

                root.getChildren().addAll(LabelNom, LabelType, LabelDate, LabelHeure, LabelDuree);
                root.setAlignment(Pos.CENTER);
                mansoryPane.getChildren().add(root);

            }

        }

    }


    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void GoToRestaurant(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/GUI/FrontRestaurant.fxml"));
        StckFrontActivite.getChildren().removeAll();
        StckFrontActivite.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHotel(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/GUI/FrontHotel.fxml"));
        StckFrontActivite.getChildren().removeAll();
        StckFrontActivite.getChildren().setAll(menu);
    }
    
    
    @FXML
    private void GoToActivite(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/GUI/FrontActivite.fxml"));
        StckFrontActivite.getChildren().removeAll();
        StckFrontActivite.getChildren().setAll(menu);
    }

    @FXML
    private void GoToBack(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/GUI/BackActivite.fxml"));
        StckFrontActivite.getChildren().removeAll();
        StckFrontActivite.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHome(MouseEvent event) throws IOException {
        
                Parent menu = FXMLLoader.load(getClass().getResource("/GUI/acceuil.fxml"));
        StckFrontActivite.getChildren().removeAll();
        StckFrontActivite.getChildren().setAll(menu);
    }

}
