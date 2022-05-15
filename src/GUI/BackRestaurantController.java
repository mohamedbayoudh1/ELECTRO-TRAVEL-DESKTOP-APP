/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.BackHotelController.encode;
import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Restaurant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import service.CrudRestaurant;
import utils.Projectbd;


/**
 * FXML Controller class
 *
 * @author yassin
 */
public class BackRestaurantController implements Initializable {

    @FXML
    private TableColumn<Restaurant, String> col_Nom;
    @FXML
    private TableColumn<Restaurant, ImageView> col_image;
    @FXML
    private TableColumn<Restaurant, Integer> col_Nombre;
    @FXML
    private TableColumn<Restaurant, String> col_Action;
    @FXML
    private Pane PaneBlur;
    @FXML
    private Pane PaneFormulaire;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private Text TitreFormulaire;
    @FXML
    private JFXButton btnAjouter;
    ////
    Restaurant rec = new Restaurant();
    CrudRestaurant work = new CrudRestaurant();
    private ObservableList<Restaurant> ListRestaurants;

    @FXML
    private JFXTextField txtNom;
    @FXML
    private ImageView PreviewImage;
    File selectedFile;
    private FileChooser Fc = new FileChooser();
    private File file;
    private static String pathImage = "";
    @FXML
    private TableColumn<Restaurant, String> col_Lieux;
    @FXML
    private JFXTextField txtLieux;
    @FXML
    private JFXTextField txtEtoile;
    @FXML
    private StackPane StckRestaurant;
    @FXML
    private TableView<Restaurant> TableViewRestaurant;
    @FXML
    private Label txtOne;
    @FXML
    private Label txtTwo;
    @FXML
    private Label txtThree;
    @FXML
    private Label txtFour;
    @FXML
    private Label txtFive;
    @FXML
    private ImageView imgQRCodeGen;
    private Image genQRCodeImg; // Generated QR Code image

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableRestaurants();
        ShowStat();
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("FrontActivite.fxml"));
        StckRestaurant.getChildren().removeAll();
        StckRestaurant.getChildren().setAll(menu);
    }

    @FXML
    private void GoToActivite(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackActivite.fxml"));
        StckRestaurant.getChildren().removeAll();
        StckRestaurant.getChildren().setAll(menu);
    }

    @FXML
    private void GoToEquipement(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackEquipement.fxml"));
        StckRestaurant.getChildren().removeAll();
        StckRestaurant.getChildren().setAll(menu);
    }

    @FXML
    private void ModifierRestautantClicked(MouseEvent event) {

        int idRestaurant = 0;
        if (TableViewRestaurant.getSelectionModel().getSelectedItem() != null) {
            idRestaurant = Integer.valueOf((TableViewRestaurant.getSelectionModel().getSelectedItem().getIdRestaurant()));
        }
        if (txtNom.getText().isEmpty()) {
            txtNom.requestFocus();
            shake(txtNom);
            return;
        }
        if (txtLieux.getText().isEmpty()) {
            txtLieux.requestFocus();
            shake(txtLieux);
            return;
        }
        if (txtEtoile.getText().isEmpty()) {
            txtEtoile.requestFocus();
            shake(txtEtoile);
            return;
        }
        if (pathImage.isEmpty()) {
            PreviewImage.requestFocus();
            shake(PreviewImage);
            return;
        }
        rec.setNomRestaurant(txtNom.getText());
        rec.setNbrFourchetteRestaurant(Integer.valueOf(txtEtoile.getText()));
        rec.setLieuxRestaurant(txtLieux.getText());
        rec.setImageRestaurant(pathImage);
        rec.setIdRestaurant(idRestaurant);
        Boolean result = work.ModifierRestaurant(rec);

        if (result) {
            txtNom.clear();
            txtLieux.clear();
            txtEtoile.clear();
            pathImage = "";
            // ComboFournisseur.setValue(null);
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableRestaurants();

            ///
            File file = new File("C:\\xampp\\htdocs\\Projet\\Uploads" + "uploadimageicon.png");
            Image imagee = new Image(file.toURI().toString());
            PreviewImage.setImage(imagee);
        }
        LoadTableRestaurants();
                    File file = new File("C:\\xampp\\htdocs\\Projet\\Uploads" + "uploadimageicon.png");
            Image imagee = new Image(file.toURI().toString());
            PreviewImage.setImage(imagee);
    }

    @FXML
    private void AjouterRestaurantClicked(MouseEvent event) {
        if (txtNom.getText().isEmpty()) {
            txtNom.requestFocus();
            shake(txtNom);
            return;
        }
        if (txtLieux.getText().isEmpty()) {
            txtLieux.requestFocus();
            shake(txtLieux);
            return;
        }
        if (txtEtoile.getText().isEmpty()) {
            txtEtoile.requestFocus();
            shake(txtEtoile);
            return;
        }

        if (pathImage.isEmpty()) {
            PreviewImage.requestFocus();
            shake(PreviewImage);
            return;
        }
        rec.setNomRestaurant(txtNom.getText());
        rec.setNbrFourchetteRestaurant(Integer.valueOf(txtEtoile.getText()));
        rec.setLieuxRestaurant(txtLieux.getText());
        rec.setImageRestaurant(pathImage);

        boolean result = work.AjouterRestaurant(rec);
        if (result) {

            txtNom.clear();
            txtLieux.clear();
            txtEtoile.clear();
            pathImage = "";
            // ComboFournisseur.setValue(null);
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableRestaurants();
        }
    }

    @FXML
    private void GoToHotel(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackHotel.fxml"));
        StckRestaurant.getChildren().removeAll();
        StckRestaurant.getChildren().setAll(menu);
    }

    @FXML
    private void GoToRestaurant(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackRestaurant.fxml"));
        StckRestaurant.getChildren().removeAll();
        StckRestaurant.getChildren().setAll(menu);
    }

    private class ImageRestaurantCellValueFactory implements Callback<TableColumn.CellDataFeatures<Restaurant, ImageView>, ObservableValue<ImageView>> {

        @Override
        public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Restaurant, ImageView> param) {
            Restaurant item = param.getValue();
            ImageView img = null;

            img = item.getImgViewRestaurant();

            return new SimpleObjectProperty<>(img);
        }
    }

    private void LoadTableRestaurants() {

        List<Restaurant> listee = new ArrayList<>();
        listee = work.AfficherAllRestaurant(rec);
        ObservableList<Restaurant> Liste = FXCollections.observableArrayList(listee);

        col_Nom.setCellValueFactory(new PropertyValueFactory<>("NomRestaurant"));
        col_Nombre.setCellValueFactory(new PropertyValueFactory<>("nbrFourchetteRestaurant"));
        col_image.setCellValueFactory(new ImageRestaurantCellValueFactory());
        col_Lieux.setCellValueFactory(new PropertyValueFactory<>("LieuxRestaurant"));
        //
        ListRestaurants = FXCollections.observableArrayList(listee);
        TableViewRestaurant.setItems(ListRestaurants);

        //add cell of button edit 
        Callback<TableColumn<Restaurant, String>, TableCell<Restaurant, String>> cellFoctory = (TableColumn<Restaurant, String> param) -> {
            //make cell containing buttons

            final TableCell<Restaurant, String> cell = new TableCell<Restaurant, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {

                        ImageView Deleteicon, Editicon;
                        Deleteicon = new ImageView(new Image("/pidev/images/deleteicon.png"));
                        Deleteicon.setFitHeight(30);
                        Deleteicon.setFitWidth(30);
                        setGraphic(Deleteicon);

                        Editicon = new ImageView(new Image("/pidev/images/editicon.png"));
                        Editicon.setFitHeight(30);
                        Editicon.setFitWidth(30);
                        setGraphic(Editicon);

                        Editicon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon Edit is pressed !");
                            txtNom.setText(TableViewRestaurant.getSelectionModel().getSelectedItem().getNomRestaurant());
                            txtLieux.setText(TableViewRestaurant.getSelectionModel().getSelectedItem().getLieuxRestaurant());
                            txtEtoile.setText(String.valueOf(TableViewRestaurant.getSelectionModel().getSelectedItem().getNbrFourchetteRestaurant()));

                            //PreviewImage.setImage(value);
                            OpenPopupModifier();
                        });
                        Deleteicon.setOnMouseClicked((MouseEvent event) -> {
                            //   System.out.println("icon delete is pressed !");
                            if (TableViewRestaurant.getSelectionModel().getSelectedItem() != null) {
                                rec = TableViewRestaurant.getSelectionModel().getSelectedItem();
                                Boolean result = work.SupprimerRestaurant(rec.getIdRestaurant());
                                if (result) {
                                    System.out.println("Restaurant Has Been Deleted ✔");
                                    LoadTableRestaurants();
                                }
                            }
                        });

                        //managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(Deleteicon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(Editicon, new Insets(2, 3, 0, 2));
                        HBox managebtn = new HBox(Editicon, Deleteicon);
                        setGraphic(managebtn);
                    }
                }
            };
            return cell;
        };
        col_Action.setCellFactory(cellFoctory);

        /////// CodeQR
        TableViewRestaurant.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {
                String Nom = TableViewRestaurant.getSelectionModel().getSelectedItem().getNomRestaurant();
                String Etoile = String.valueOf(TableViewRestaurant.getSelectionModel().getSelectedItem().getNbrFourchetteRestaurant());
                String Lieux = TableViewRestaurant.getSelectionModel().getSelectedItem().getLieuxRestaurant();
                String AllInfo = " Nom : " + Nom + "\n Etoile : " + Etoile + "\n Lieux : " + Lieux + "";
                ////////////////////////:
                if (!AllInfo.isEmpty()) {
                    String foregroundColor = "#2E3437";
                    String backgroundColor = "#FFFFFF";
                    imgQRCodeGen.setVisible(true);
                    genQRCodeImg = encode(AllInfo, Integer.parseInt("300"), Integer.parseInt("300"), foregroundColor, backgroundColor);
                    if (genQRCodeImg != null) {
                        imgQRCodeGen.setImage(genQRCodeImg);
                    }
                }
                imgQRCodeGen.setVisible(true);
            }
        });
    }

    private void OpenPopupModifier() {
        int idRestaurant = 0;
        if (TableViewRestaurant.getSelectionModel().getSelectedItem() != null) {
            idRestaurant = Integer.valueOf((TableViewRestaurant.getSelectionModel().getSelectedItem().getIdRestaurant()));
        }
        ////////////

        try {
            String Destination = "C:\\xampp\\htdocs\\Projet\\Uploads\\";
            File dest = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\");

            String requeteee = "SELECT image_restaurant FROM restaurant WHERE idrestaurant = '" + idRestaurant + "'";
            Statement psttt = Projectbd.getInstance().getCnx().createStatement();
            ResultSet rsss = psttt.executeQuery(requeteee);
            while (rsss.next()) {
                String exist = "";
                exist = rsss.getString(1);
                if (exist != null && !exist.isEmpty()) {
                    String ImageProduct = Destination + rsss.getString(1);
                    String NomImage = rsss.getString(1);
                    pathImage = rsss.getString(1);
                    File f = new File(dest, NomImage);
                    if (f.exists()) {
                        System.out.println("File  Exist  in Uploads");
                        Image imagee = new Image(new FileInputStream(ImageProduct), 200, 200, true, true);
                        PreviewImage.setImage(imagee);
                    } else {
                        System.out.println("File Not Exist  in Uploads");
                        File file = new File(Destination + "uploadimageicon.png");
                        Image imagee = new Image(file.toURI().toString());
                        PreviewImage.setImage(imagee);
                    }

                } else if (exist == null || exist.isEmpty()) {
                    System.out.println("Base de donnée champ image null or empty !");
                    File file = new File(Destination + "uploadimageicon.png");
                    Image imagee = new Image(file.toURI().toString());
                    PreviewImage.setImage(imagee);
                }

            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BackEquipementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ///////////
        btnModifier.toFront();
        TitreFormulaire.setText("Modifier Le Restaurant");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    @FXML
    private void OpenFormulaireAdd(MouseEvent event) {
        txtNom.clear();
        txtLieux.clear();
        txtEtoile.clear();
        btnAjouter.toFront();
        TitreFormulaire.setText("Ajouter un Restaurant");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    @FXML
    private void CloseFormulaireClicked(MouseEvent event) {
        PaneFormulaire.setVisible(false);
        PaneBlur.setVisible(false);

        //
        File file = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "uploadimageicon.png");
        Image imagee = new Image(file.toURI().toString());
        PreviewImage.setImage(imagee);
        //
        pathImage = "";
    }

    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }

    public static void shake(Node node) {
        new Shake(node).play();
    }

    @FXML
    private void UploadImageClicked(MouseEvent event) {
        File dest = new File("C:\\xampp\\htdocs\\Projet\\Uploads");
        Fc.setTitle("Open Resource File");
        Fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        selectedFile = Fc.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                String Destination = "C:\\xampp\\htdocs\\Projet\\Uploads";
                File f = new File(dest, selectedFile.getName());

                FileUtils.copyFileToDirectory(selectedFile, dest);
                pathImage = selectedFile.getName();

                Image image = new Image(new FileInputStream(selectedFile), 200, 200, true, true);
                PreviewImage.setImage(image);
            } catch (IOException ex) {
                ex.getStackTrace();
            }
        }
    }

    private Stage getStage() {
        return (Stage) PreviewImage.getScene().getWindow();
    }

    private File getFileSelected() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        fileChooser.setTitle("Select an image");

        File selectedImage = fileChooser.showOpenDialog(getStage());
        return selectedImage;
    }

    private void ShowStat() {
        // Changing random data after every 1 second.
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int TotalOne = work.countEtoileRestaurant(1);
                if (TotalOne != 0) {
                    txtOne.setText(String.valueOf(TotalOne));
                    txtOne.setAlignment(Pos.CENTER);
                }
                //
                int TotatTwo = work.countEtoileRestaurant(2);
                if (TotatTwo != 0) {
                    txtTwo.setText(String.valueOf(TotatTwo));
                    txtTwo.setAlignment(Pos.CENTER);

                }
                int totalThree = work.countEtoileRestaurant(3);
                if (totalThree != 0) {
                    txtThree.setText(String.valueOf(totalThree));
                    txtThree.setAlignment(Pos.CENTER);
                }
                //
                int TotatFour = work.countEtoileRestaurant(4);
                if (TotatFour != 0) {
                    txtFour.setText(String.valueOf(TotatFour));
                    txtFour.setAlignment(Pos.CENTER);
                }
                //
                int TotatFive = work.countEtoileRestaurant(5);
                if (TotatFive != 0) {
                    txtFive.setText(String.valueOf(TotatFive));
                    txtFive.setAlignment(Pos.CENTER);
                }

            }
        }));
        ///Repeat indefinitely until stop() method is called.
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
    }

    
        @FXML
    private void GoToHome(MouseEvent event) {
        
        
                           try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/clientinterface.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
