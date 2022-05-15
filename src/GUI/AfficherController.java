/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Agence;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import service.serviceAgence;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherController implements Initializable {

    @FXML
    private TableView<Agence> TableView1;
    @FXML
    private TableColumn<Agence, String> tnom;
    @FXML
    private TableColumn<Agence, String> tadresse;
    @FXML
    private TableColumn<Agence, Integer> tnumtel;
    @FXML
    private TableColumn<Agence, Integer> tnbrbus;
    ObservableList list;
    @FXML
    private TextField nomup;
    @FXML
    private TextField adresseup;
    @FXML
    private TextField numtelup;
    @FXML
    private TextField nbrbusup;
    @FXML
    private Button btnmod;
    @FXML
    private TextField recherche;

    ObservableList<Agence> aglist = FXCollections.observableArrayList();
    @FXML
    private Button QRcode;
    @FXML
    private TextField tqr;
    @FXML
    private ComboBox<String> ttypetri;
    @FXML
    private ComboBox<String> tselon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherA();
        searchact();
        tselon.setItems(FXCollections.observableArrayList("Nom","Adress","Nombre de bus"));
        ttypetri.setItems(FXCollections.observableArrayList("decroissant","Croissante"));
    }

    public void afficherA() {
        try {
            serviceAgence sa = new serviceAgence();
            List<Agence> agences = sa.afficherAgence();
            list = FXCollections.observableArrayList(agences);
            TableView1.setItems(list);

            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            tnbrbus.setCellValueFactory(new PropertyValueFactory<>("nbrbus"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void load(List<Agence> la) {

        tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        tnbrbus.setCellValueFactory(new PropertyValueFactory<>("nbrbus"));

        if (la != null) {

            refreshTable(la);
        }

    }

    private void refreshTable(List<Agence> la) {

        TableView1.getItems().clear();//actualiser

        aglist.addAll(la);

        TableView1.setItems(aglist);

    }

    @FXML
    private void btnsupprimerA(javafx.event.ActionEvent event) throws SQLException {

        if (TableView1.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            JOptionPane.showMessageDialog(null, "Aucune agence n'est selectionnée ,veuillez choisir une agence");
        } else {
            int responce = JOptionPane.showConfirmDialog(null, "Attention vous allez supprimer l'agence sélectionnée etes-vous sur ?", "Suppression", JOptionPane.YES_NO_OPTION);
            if (responce == JOptionPane.YES_OPTION) {
                serviceAgence sa = new serviceAgence();
                Agence a = (Agence) TableView1.getSelectionModel().getSelectedItem();
                sa.SupprimerAgence(a);

                refreshTable(list);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setContentText("Votre agence a été bien supprime");
                JOptionPane.showMessageDialog(null, "agence supprimé");
                afficherA();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Annulation");
                JOptionPane.showMessageDialog(null, "Suppression annulé");
            }

        }
        afficherA();
    }

    @FXML
    private void btnmodA(javafx.event.ActionEvent event) {

        Agence a = TableView1.getSelectionModel().getSelectedItem();
        if (nomup.getText().equals("") || adresseup.getText().equals("") || numtelup.getText().equals("") || nbrbusup.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "veuillez remplir les champs vides");
        } else if (!(numtelup.getText().matches("^[0-9]+$"))) {
            JOptionPane.showMessageDialog(null, "Le numéro de telephone est non Valide");
        } else if (numtelup.getText().length() != 8) {
            JOptionPane.showMessageDialog(null, "Le Numero de telephone doit contenir 8 chiifres");
        } else if (!(nbrbusup.getText().matches("^[0-9]+$"))) {
            JOptionPane.showMessageDialog(null, "Le Nombre de Bus n'est pas Valide");
        } else {
            a.setNom(nomup.getText());

            a.setAdresse(adresseup.getText());

            a.setNbrbus(Integer.valueOf(nbrbusup.getText()));
            a.setNumtel(Integer.valueOf(numtelup.getText()));

            // a.setImage(imagecomp);               
            serviceAgence sa = new serviceAgence();

            try {

                sa.ModifierAgence(a);
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

           afficherA();

        }
    }

    @FXML
    private void selectA(MouseEvent event) {
        Agence a = TableView1.getSelectionModel().getSelectedItem();

        nomup.setText(a.getNom());

        adresseup.setText(a.getAdresse());
        nbrbusup.setText(String.valueOf(a.getNbrbus()));
        numtelup.setText(String.valueOf(a.getNumtel()));
        tqr.setText(a.getNom());

    }

    public void searchact() {
        try {
            serviceAgence sa = new serviceAgence();
            List<Agence> allact = sa.afficherAgence();
            recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                // System.out.println(newValue.toString());
                if (newValue.isEmpty()) {
                    //loadDate();
                    load(allact);

                } else {

                    List<Agence> a = new ArrayList();
                    a = allact.stream().filter(item -> {

                        int index = item.getNom().toUpperCase().indexOf(newValue.toUpperCase());
                        int index2 = item.getAdresse().toUpperCase().indexOf(newValue.toUpperCase());

                        int index3 = Integer.toString(item.getNumtel()).toUpperCase().indexOf(newValue.toUpperCase());
                        int index4 = Integer.toString(item.getNbrbus()).toUpperCase().indexOf(newValue.toUpperCase());

                        if ((index == -1) && (index2 == -1) && (index3 == -1) && (index4 == -1)) {
                            return false;
                        } else {

                            return true;
                        }
                    }
                    ).collect(Collectors.toList());

                    load(a);

                }

            });

            load(allact);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void btnpdf(javafx.event.ActionEvent event) throws SQLException, FileNotFoundException, IOException {

        serviceAgence sa = new serviceAgence();
        ObservableList<Agence> list = sa.getRepasList();

        // ObservableList<Abonnement>  ActList = FXCollections.observableArrayList();
        try {
            OutputStream file = new FileOutputStream(new File("E:\\Liste agence.pdf"));
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter.getInstance(document, file);
            document.open();

            Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph pdfTitle = new Paragraph("Liste Des Agences", font);
            pdfTitle.setAlignment(Element.ALIGN_CENTER);

            document.add(pdfTitle);
            document.add(new Chunk("\n"));
            PdfPTable table = new PdfPTable(4);
            table.setHeaderRows(3);

            table.addCell("nom");
            table.addCell("adresse");
            table.addCell("numtel");
            table.addCell("nbrbus");

            aglist.forEach((_item) -> {
                table.addCell(_item.getNom());
                table.addCell(_item.getAdresse());
                table.addCell(Integer.toString(_item.getNumtel()));
                table.addCell(Integer.toString(_item.getNbrbus()));

            });

            document.add(table);

            document.close();

            file.close();

        } catch (DocumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Cannot export data!");
            alert.show();
        }
         Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setContentText("PDF Créé");
                JOptionPane.showMessageDialog(null, "PDF Créé");
    }

    @FXML
    private void btnQR(MouseEvent event) {
            serviceAgence sa = new serviceAgence();
        try {
           
              
              
            String details =tqr.getText(); 
           
            ByteArrayOutputStream out =QRCode.from(sa.QRdonner(tqr.getText())).to(net.glxn.qrgen.image.ImageType.PNG).stream();
            String f_name = tqr.getText();
            
            String Path_name="C:\\Users\\lenovo\\Desktop\\private\\finaluser\\src\\pic\\";//******************************************
           // C:\Users\lenovo\Desktop\private\finaluser\src\pic
            FileOutputStream fout = new FileOutputStream(new File(Path_name +(f_name +".PNG")));
                    fout.write(out.toByteArray());
                    fout.flush();
                    JOptionPane.showMessageDialog(null, "QRcode Crée");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @FXML
    private void btnimprimer(javafx.event.ActionEvent event) {
              PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.TableView1;
        
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
   }
    }

    @FXML
    private void btntri(javafx.event.ActionEvent event) {
        String va1 = (String) tselon.getValue();
        String va2 = (String) ttypetri.getValue();
        System.out.println(va1);
        System.out.println(va2);
        TableView1.getItems().clear();  
        if((va1.equals("Nom")) && (va2.equals("decroissant")))
        {
            try {
            serviceAgence sa = new serviceAgence();
            List<Agence> agences = sa.afficherAgenceNOMDESC();
            list = FXCollections.observableArrayList(agences);
            TableView1.setItems(list);

            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            tnbrbus.setCellValueFactory(new PropertyValueFactory<>("nbrbus"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        else if (("Nom".equals(va1)) && ("Croissante".equals(va2)))
        {System.out.println("*************");
        try {
            serviceAgence sa = new serviceAgence();
            List<Agence> agences = sa.afficherAgenceNOMASC();
            list = FXCollections.observableArrayList(agences);
            TableView1.setItems(list);

            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            tnbrbus.setCellValueFactory(new PropertyValueFactory<>("nbrbus"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }
        else if (("Adress".equals(va1)) && ("Croissante".equals(va2)))
        {
            try {
            serviceAgence sa = new serviceAgence();
            List<Agence> agences = sa.afficherAgenceADRESSASC();
            list = FXCollections.observableArrayList(agences);
            TableView1.setItems(list);

            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            tnbrbus.setCellValueFactory(new PropertyValueFactory<>("nbrbus"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        else if (("Adress".equals(va1)) && ("decroissant".equals(va2)))
        {
            try {
            serviceAgence sa = new serviceAgence();
            List<Agence> agences = sa.afficherAgenceADRESSdesc();
            list = FXCollections.observableArrayList(agences);
            TableView1.setItems(list);

            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            tnbrbus.setCellValueFactory(new PropertyValueFactory<>("nbrbus"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        else if (("Nombre de bus".equals(va1)) && (va2=="decroissant"))
        {
            try {
            serviceAgence sa = new serviceAgence();
            List<Agence> agences = sa.afficherAgenceNBRBUSdesc();
            list = FXCollections.observableArrayList(agences);
            TableView1.setItems(list);

            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            tnbrbus.setCellValueFactory(new PropertyValueFactory<>("nbrbus"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
        }
        else if (("Nombre de bus".equals(va1)) || ("Croissante".equals(va2)))
        {
                   try {
            serviceAgence sa = new serviceAgence();
            List<Agence> agences = sa.afficherAgenceNBRBUSASC();
            list = FXCollections.observableArrayList(agences);
            TableView1.setItems(list);

            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            tnbrbus.setCellValueFactory(new PropertyValueFactory<>("nbrbus"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
        else {System.out.println("error");}
        
    }
        
        
        
    }
    


