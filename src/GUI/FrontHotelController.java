/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXMasonryPane;
import entity.Hotel;
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
import service.CrudHotel;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class FrontHotelController implements Initializable {

    private StackPane StckFrontHotel;
    @FXML
    private Pane PaneBlur;
    @FXML
    private ScrollPane scrollPane;
    private final JFXMasonryPane mansoryPane = new JFXMasonryPane();
    ////
    Hotel rec = new Hotel();
    CrudHotel work = new CrudHotel();
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
        LoadCardProduits();
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

    private void LoadCardProduits() {

        mansoryPane.getChildren().clear();
        List<Hotel> listeProduits = new ArrayList<>();
        listeProduits = work.AfficherAllHotel(rec);

        if (!listeProduits.isEmpty()) {
            for (int i = 0; i < listeProduits.size(); i++) {
                VBox root = new VBox();
                ImageView PreviewImageProduit = new ImageView();
                PreviewImageProduit.setFitWidth(120);
                PreviewImageProduit.setFitHeight(120);
                PreviewImageProduit.setPreserveRatio(false);
                PreviewImageProduit.setSmooth(true);
                PreviewImageProduit.setCache(true);

                String nom = listeProduits.get(i).getNomHotel();
                String Lieux = listeProduits.get(i).getLieuxHotel();
                int Etoile = listeProduits.get(i).getNbrEtoileHotel();
                //
                File dest = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\");
                File f = new File(dest, listeProduits.get(i).getImageHotel());
                //
                if (listeProduits.get(i).getImageHotel() != null) {
                    if (!f.exists()) {
                        try {
                            Image img = new Image(new FileInputStream("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "nophoto.jpg"));
                            PreviewImageProduit.setImage(img);
                        } catch (FileNotFoundException ex) {
                            //Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            // Image img = new Image(new FileInputStream(listeUser.get(i).getCarteidentity()));
                            Image img = new Image(new FileInputStream("C:\\xampp\\htdocs\\Projet\\Uploads\\" + listeProduits.get(i).getImageHotel()));
                            PreviewImageProduit.setImage(img);
                        } catch (FileNotFoundException ex) {
                            //   Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else if (listeProduits.get(i).getImageHotel() == null) {

                    //identityView.setImage(new Image(getClass().getResource("/resources/image/empty-image.jpg").toString()));
                    File file = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "nophoto.jpg");
                    Image imagee = new Image(file.toURI().toString());
                    PreviewImageProduit.setImage(imagee);
                }
                root.setPadding(new Insets(12, 17, 17, 17));
                root.setSpacing(13);

                ///
                root.setStyle("-fx-background-color: #fff; -fx-background-radius: 15px;-fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                //labels[i].setTextFill(Color.color(1, 0, 0));

                Label LabelNom = new Label("" + nom);
                Label LabelLieux = new Label("" + Lieux);

                if (Etoile == 1) {
                    Label LabelEtoiles = new Label("" + "⭐");
                    root.getChildren().addAll(LabelNom, PreviewImageProduit, LabelLieux, LabelEtoiles);
                }
                if (Etoile == 2) {
                    Label LabelEtoiles = new Label("" + "⭐⭐");
                    root.getChildren().addAll(LabelNom, PreviewImageProduit, LabelLieux, LabelEtoiles);
                }
                if (Etoile == 3) {
                    Label LabelEtoiles = new Label("" + "⭐⭐⭐");
                    root.getChildren().addAll(LabelNom, PreviewImageProduit, LabelLieux, LabelEtoiles);

                }
                if (Etoile == 4) {
                    Label LabelEtoiles = new Label("" + "⭐⭐⭐⭐");
                    root.getChildren().addAll(LabelNom, PreviewImageProduit, LabelLieux, LabelEtoiles);

                }
                if (Etoile == 5) {
                    Label LabelEtoiles = new Label("" + "⭐⭐⭐⭐⭐");
                    root.getChildren().addAll(LabelNom, PreviewImageProduit, LabelLieux, LabelEtoiles);

                }

                LabelLieux.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setStyle("-fx-font-weight: bold");
                //label2.setStyle("-fx-font-weight: bold");

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
