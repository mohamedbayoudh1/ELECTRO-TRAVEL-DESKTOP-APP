/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entity.Equipement;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import service.CrudEquipement;
import utils.Projectbd;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class BackEquipementController implements Initializable {

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
    Equipement rec = new Equipement();
    CrudEquipement work = new CrudEquipement();
    private ObservableList<Equipement> ListEquipements;
    ////
    @FXML
    private TableColumn<Equipement, String> col_Nom;

    @FXML
    private TableColumn<Equipement, ImageView> col_image;
    @FXML
    private TableColumn<Equipement, String> col_Action;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private ImageView PreviewImage;
    File selectedFile;
    private FileChooser Fc = new FileChooser();
    private File file;
    private static String pathImage = "";
    @FXML
    private TableColumn<Equipement, String> col_Nombre;
    @FXML
    private TableView<Equipement> TableViewEquipements;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private StackPane StckEquipement;
    @FXML
    private Label txtStatTotal;
    Desktop desktop = Desktop.getDesktop();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableEquipements();
        countTotalEquipement();
    }

    private void countTotalEquipement() {
        int Total = work.countTotalEquipement();
        txtStatTotal.setText(String.valueOf(Total));
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("FrontActivite.fxml"));
        StckEquipement.getChildren().removeAll();
        StckEquipement.getChildren().setAll(menu);
    }

    @FXML
    private void ModifierEquipementClicked(MouseEvent event) {

        int idEquipement = 0;
        if (TableViewEquipements.getSelectionModel().getSelectedItem() != null) {
            idEquipement = Integer.valueOf((TableViewEquipements.getSelectionModel().getSelectedItem().getIdEquipement()));
        }
        if (txtNom.getText().isEmpty()) {
            txtNom.requestFocus();
            shake(txtNom);
            return;
        }
        if (txtNombre.getText().isEmpty()) {
            txtNombre.requestFocus();
            shake(txtNombre);
            return;
        }
        if (pathImage.isEmpty()) {
            PreviewImage.requestFocus();
            shake(PreviewImage);
            return;
        }
        rec.setNomEquipement(txtNom.getText());
        rec.setNbrEquipement(Integer.valueOf(txtNombre.getText()));
        rec.setImageEquipement(pathImage);
        rec.setIdEquipement(idEquipement);
        Boolean result = work.ModifierEquipement(rec);

        if (result) {
            txtNom.clear();
            txtNombre.clear();
            pathImage = "";
            // ComboFournisseur.setValue(null);
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableEquipements();
            countTotalEquipement();

            ///
            File file = new File("C:\\xampp\\htdocs\\Projet\\Uploads" + "uploadimageicon.png");
            Image imagee = new Image(file.toURI().toString());
            PreviewImage.setImage(imagee);
        }
        LoadTableEquipements();
    }

    @FXML
    private void AjouterEquipementClicked(MouseEvent event) {

        if (txtNom.getText().isEmpty()) {
            txtNom.requestFocus();
            shake(txtNom);
            return;
        }
        if (txtNombre.getText().isEmpty()) {
            txtNombre.requestFocus();
            shake(txtNombre);
            return;
        }
        if (pathImage.isEmpty()) {
            PreviewImage.requestFocus();
            shake(PreviewImage);
            return;
        }
        rec.setNomEquipement(txtNom.getText());
        rec.setNbrEquipement(Integer.valueOf(txtNombre.getText()));
        rec.setImageEquipement(pathImage);

        boolean result = work.AjouterEquipement(rec); // Fonction AjoutUser
        if (result) {

            txtNom.clear();
            txtNombre.clear();
            pathImage = "";
            // ComboFournisseur.setValue(null);
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableEquipements();
            countTotalEquipement();
        }
    }

    @FXML
    private void GoToActivite(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackActivite.fxml"));
        StckEquipement.getChildren().removeAll();
        StckEquipement.getChildren().setAll(menu);
    }

    @FXML
    private void GoToEquipement(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackEquipement.fxml"));
        StckEquipement.getChildren().removeAll();
        StckEquipement.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHotel(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackHotel.fxml"));
        StckEquipement.getChildren().removeAll();
        StckEquipement.getChildren().setAll(menu);
    }

    @FXML
    private void GoToRestaurant(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackRestaurant.fxml"));
        StckEquipement.getChildren().removeAll();
        StckEquipement.getChildren().setAll(menu);
    }

    @FXML
    private void GenerateExcel(MouseEvent event) {
        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        try {
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("ID");
            rowhead.createCell((short) 1).setCellValue("Nom");
            rowhead.createCell((short) 2).setCellValue("image");
            rowhead.createCell((short) 3).setCellValue("Nombre");

            Statement st = Projectbd.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery("Select id_equipement,nom_equipement,image_equipement,nbr_equipement from equipement");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);

                row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id_equipement")));
                row.createCell((short) 1).setCellValue(rs.getString("nom_equipement"));
                row.createCell((short) 2).setCellValue(rs.getString("image_equipement"));
                row.createCell((short) 3).setCellValue(rs.getString("nbr_equipement"));
                i++;
            }
            FileOutputStream fileOut = new FileOutputStream(String.valueOf(DateLyoum + ".xls"));
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

            ///Open Excel
            File file = new File(DateLyoum + ".xls");
            if (file.exists()) //checks file exists or not  
            {
                desktop.open(file); //opens the specified file   
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    private void LoadTableEquipements() {

        List<Equipement> listee = new ArrayList<>();
        listee = work.AfficherAllEquipement(rec);
        ObservableList<Equipement> Liste = FXCollections.observableArrayList(listee);

        col_Nom.setCellValueFactory(new PropertyValueFactory<>("NomEquipement"));
        col_Nombre.setCellValueFactory(new PropertyValueFactory<>("nbrEquipement"));
        col_image.setCellValueFactory(new ImageEquipementCellValueFactory());
        //
        ListEquipements = FXCollections.observableArrayList(listee);
        TableViewEquipements.setItems(ListEquipements);

        //add cell of button edit 
        Callback<TableColumn<Equipement, String>, TableCell<Equipement, String>> cellFoctory = (TableColumn<Equipement, String> param) -> {
            //make cell containing buttons

            final TableCell<Equipement, String> cell = new TableCell<Equipement, String>() {

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
                            txtNom.setText(TableViewEquipements.getSelectionModel().getSelectedItem().getNomEquipement());
                            txtNombre.setText(String.valueOf(TableViewEquipements.getSelectionModel().getSelectedItem().getNbrEquipement()));

                            //PreviewImage.setImage(value);
                            OpenPopupModifier();
                        });
                        Deleteicon.setOnMouseClicked((MouseEvent event) -> {
                            //   System.out.println("icon delete is pressed !");
                            if (TableViewEquipements.getSelectionModel().getSelectedItem() != null) {
                                rec = TableViewEquipements.getSelectionModel().getSelectedItem();
                                
                                Boolean result = work.SupprimerEquipement(rec.getIdEquipement());
                                
                                
                                if (result) {
                                    System.out.println("Equipement Has Been Deleted ✔");
                                    LoadTableEquipements();
                                    countTotalEquipement();
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
    }

    private void OpenPopupModifier() {
        int idEquipement = 0;
        if (TableViewEquipements.getSelectionModel().getSelectedItem() != null) {
            idEquipement = Integer.valueOf((TableViewEquipements.getSelectionModel().getSelectedItem().getIdEquipement()));
        }
        ////////////
        try {
            String Destination = "C:\\xampp\\htdocs\\Projet\\Uploads\\";
            File dest = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\");

            String requeteee = "SELECT image_equipement FROM equipement WHERE id_equipement = '" + idEquipement + "'";
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
        TitreFormulaire.setText("Modifier Le Equipement");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    @FXML
    private void OpenFormulaireAdd(MouseEvent event) {
        txtNom.clear();
        txtNombre.clear();
        btnAjouter.toFront();
        TitreFormulaire.setText("Ajouter un Equipement");
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


    private class ImageEquipementCellValueFactory implements Callback<TableColumn.CellDataFeatures<Equipement, ImageView>, ObservableValue<ImageView>> {

        @Override
        public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Equipement, ImageView> param) {
            Equipement item = param.getValue();
            ImageView img = null;

            img = item.getImgViewEquipement();

            return new SimpleObjectProperty<>(img);
        }
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
