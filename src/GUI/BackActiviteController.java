/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import animatefx.animation.Shake;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import entity.Activite;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.CrudActivite;


/**
 * FXML Controller class
 *
 * @author 
 */
public class BackActiviteController implements Initializable {

    @FXML
    private Pane PaneFormulaire;
    @FXML
    private Text TitreFormulaire;
    ////
    Activite rec = new Activite();
    CrudActivite work = new CrudActivite();
    private ObservableList<Activite> ListActivite;
    ////
    @FXML
    private TableColumn<Activite, String> col_Nom;
    @FXML
    private TableColumn<Activite, String> col_Action;
    @FXML
    private TableView<Activite> TableViewActivites;
    @FXML
    private Pane PaneBlur;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private JFXButton btnAjouter;
    @FXML
    private TableColumn<Activite, String> col_Type;
    @FXML
    private TableColumn<Activite, String> col_Date;
    @FXML
    private TableColumn<Activite, String> col_Heure;
    @FXML
    private TableColumn<Activite, String> col_Duree;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtDuree;
    @FXML
    private JFXTextField txtType;
    @FXML
    private JFXTextField txtHeure;
    @FXML
    private StackPane StckActivite;
    @FXML
    private Label txtStatTotal;
    Desktop desktop = Desktop.getDesktop();
    @FXML
    private JFXDatePicker txtDate;

    //
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableActivte();
        countTotalActivite();
    }

    private void countTotalActivite() {
        int Total = work.countTotalActivite();
        txtStatTotal.setText(String.valueOf(Total));
    }

    private void LoadTableActivte() {

        List<Activite> listee = new ArrayList<>();
        listee = work.AfficherAllActivite(rec);
        ObservableList<Activite> Liste = FXCollections.observableArrayList(listee);

        col_Nom.setCellValueFactory(new PropertyValueFactory<>("NomActivite"));
        col_Type.setCellValueFactory(new PropertyValueFactory<>("TypeActivite"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("DateActivite"));
        col_Heure.setCellValueFactory(new PropertyValueFactory<>("HeureActivite"));
        col_Duree.setCellValueFactory(new PropertyValueFactory<>("DureeActivite"));
        ListActivite = FXCollections.observableArrayList(listee);
        TableViewActivites.setItems(ListActivite);

        //add cell of button edit 
        Callback<TableColumn<Activite, String>, TableCell<Activite, String>> cellFoctory = (TableColumn<Activite, String> param) -> {
            //make cell containing buttons

            final TableCell<Activite, String> cell = new TableCell<Activite, String>() {

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
                            //   System.out.println("icon Edit is pressed !");
                            txtNom.setText(TableViewActivites.getSelectionModel().getSelectedItem().getNomActivite());
                            txtType.setText(TableViewActivites.getSelectionModel().getSelectedItem().getTypeActivite());
                            txtDate.setValue(TableViewActivites.getSelectionModel().getSelectedItem().getDateActivite().toLocalDate());
                            txtHeure.setText(TableViewActivites.getSelectionModel().getSelectedItem().getHeureActivite());
                            txtDuree.setText(TableViewActivites.getSelectionModel().getSelectedItem().getDureeActivite());
                            OpenPopupModifier();
                        });
                        Deleteicon.setOnMouseClicked((MouseEvent event) -> {
                            //   System.out.println("icon delete is pressed !");
                            if (TableViewActivites.getSelectionModel().getSelectedItem() != null) {
                                rec = TableViewActivites.getSelectionModel().getSelectedItem();
                                Boolean result = work.SupprimerActivite(rec.getIdActivite());
                                if (result) {
                                    System.out.println("Activite Has Been Deleted ✔");
                                    LoadTableActivte();
                                    countTotalActivite();
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
        btnModifier.toFront();
        TitreFormulaire.setText("Modifier L'activité");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    @FXML
    private void CloseFormulaireClicked(MouseEvent event) {
        PaneFormulaire.setVisible(false);
        PaneBlur.setVisible(false);
    }

    @FXML
    private void OpenFormulaireAdd(MouseEvent event) {
        txtNom.clear();
        txtType.clear();
        txtDate.setValue(null);
        txtHeure.clear();
        txtDuree.clear();
        btnAjouter.toFront();
        TitreFormulaire.setText("Ajouter une Activite");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    public static void shake(Node node) { // f
        new Shake(node).play();
    }

    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("FrontActivite.fxml"));
        StckActivite.getChildren().removeAll();
        StckActivite.getChildren().setAll(menu);
    }

    @FXML
    private void ModifierActiviteClicked(MouseEvent event) {

        int idActivite = 0;
        if (TableViewActivites.getSelectionModel().getSelectedItem() != null) {
            idActivite = Integer.valueOf((TableViewActivites.getSelectionModel().getSelectedItem().getIdActivite()));
        }
        if (txtNom.getText().isEmpty()) {
            txtNom.requestFocus();
            shake(txtNom);
            return;
        }
        if (txtType.getText().isEmpty()) {
            txtType.requestFocus();
            shake(txtType);
            return;
        }

        if (txtDate.getEditor().getText().isEmpty()) {
            txtDate.requestFocus();
            shake(txtDate);
            return;
        }
        if (txtHeure.getText().isEmpty()) {
            txtHeure.requestFocus();
            shake(txtHeure);
            return;
        }

        if (txtDuree.getText().isEmpty()) {
            txtDuree.requestFocus();
            shake(txtDuree);
            return;
        }
        rec.setNomActivite(txtNom.getText());
        rec.setTypeActivite(txtType.getText());
        rec.setDateActivite(java.sql.Date.valueOf(txtDate.getValue()));
        rec.setHeureActivite(txtHeure.getText());
        rec.setDureeActivite(txtDuree.getText());
        rec.setIdActivite(idActivite);
        Boolean result = work.ModifierFournisseur(rec); // Requet Modiifer

        if (result) {
            txtNom.clear();
            txtType.clear();
            txtDate.setValue(null);
            txtHeure.clear();
            txtDuree.clear();
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableActivte();
            countTotalActivite();
        }
        LoadTableActivte();
    }

    @FXML
    private void AjouterActiviteClicked(MouseEvent event) {

        if (txtNom.getText().isEmpty()) {
            txtNom.requestFocus();
            shake(txtNom);
            return;
        }
        if (txtType.getText().isEmpty()) {
            txtType.requestFocus();
            shake(txtType);
            return;
        }

        if (txtDate.getEditor().getText().isEmpty()) {
            txtDate.requestFocus();
            shake(txtDate);
            return;
        }
        if (txtHeure.getText().isEmpty()) {
            txtHeure.requestFocus();
            shake(txtHeure);
            return;
        }

        if (txtDuree.getText().isEmpty()) {
            txtDuree.requestFocus();
            shake(txtDuree);
            return;
        }

        rec.setNomActivite(txtNom.getText());
        rec.setTypeActivite(txtType.getText());
        rec.setDateActivite(java.sql.Date.valueOf(txtDate.getValue()));
        rec.setHeureActivite(txtHeure.getText());
        rec.setDureeActivite(txtDuree.getText());

        boolean result = work.AjouterActivite(rec); // Requet Ajout
        if (result) {
            txtNom.clear();
            txtType.clear();
            txtDate.setValue(null);
            txtHeure.clear();
            txtDuree.clear();
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableActivte();
            countTotalActivite();
        }

    }

    @FXML
    private void GoToActivite(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackActivite.fxml"));
        StckActivite.getChildren().removeAll();
        StckActivite.getChildren().setAll(menu);
    }

    @FXML
    private void GoToEquipement(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackEquipement.fxml"));
        StckActivite.getChildren().removeAll();
        StckActivite.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHotel(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackHotel.fxml"));
        StckActivite.getChildren().removeAll();
        StckActivite.getChildren().setAll(menu);
    }

    @FXML
    private void GoToRestaurant(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackRestaurant.fxml"));
        StckActivite.getChildren().removeAll();
        StckActivite.getChildren().setAll(menu);
    }

    @FXML
    private void GeneratePDF(MouseEvent event) throws IOException {

        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("DateLyoummmmmmmmmmmmmmmmmmmmm   " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Rapport Pour :" + DateRapport);
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(6); // colone

            //contenu du tableau.
            table.addCell("idActivite");
            table.addCell("Nom");
            table.addCell("Type");
            table.addCell("Date");
            table.addCell("Duree");
            table.addCell("Heure");
            //     table.addCell("Image : ");
            work.AfficherAllActivite(rec).forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getIdActivite()));
                table.addCell(e.getNomActivite());
                table.addCell(e.getTypeActivite());
                table.addCell(String.valueOf(e.getDateActivite()));
                table.addCell(e.getDureeActivite());
                table.addCell(e.getHeureActivite());
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            //  document.addAuthor("Bike");
            // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File(DateLyoum + ".pdf");
        if (file.exists()) //checks file exists or not  
        {
            desktop.open(file); //opens the specified file   
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
