/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Equipement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Projectbd;

public class CrudEquipement implements IEquipementt<Equipement> {

    @Override
    public boolean AjouterEquipement(Equipement p) {
        try {
            String requete = "INSERT INTO Equipement (nom_equipement,image_equipement,nbr_equipement)"
                    + "VALUES (?,?,?)";
            PreparedStatement pst = Projectbd.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNomEquipement());
            pst.setString(2, p.getImageEquipement());
            pst.setInt(3, p.getNbrEquipement());

            pst.executeUpdate();

            System.out.println("Equipement été ajouté ✔");
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public List<Equipement> AfficherAllEquipement(Equipement t) {
        List<Equipement> EquipementList = new ArrayList<>();
        try {
            String requete = "SELECT nom_equipement,image_equipement,nbr_equipement,id_equipement FROM Equipement ORDER BY id_equipement DESC";
            Statement pst = Projectbd.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Equipement r = new Equipement();
                ///
                ImageView img = new ImageView();
                Image image;
                try {
                    if (rs.getString("image_equipement") == null) {
                    } else if (rs.getString("image_equipement") != null) {
                        image = new Image(new FileInputStream(("C:\\xampp\\htdocs\\Projet\\Uploads\\" + rs.getString("image_equipement"))));
                        img.setImage(image);
                        img.setPreserveRatio(false);
                        img.setFitWidth(50);
                        img.setFitHeight(50);

                    }
                } catch (FileNotFoundException ex) {
                    try {
                        System.out.println(ex.getMessage());
                        image = new Image(new FileInputStream(("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "nophoto.jpg")));
                        img.setImage(image);
                        img.setPreserveRatio(true);
                        img.setFitWidth(50);
                        img.setFitHeight(50);
                    } catch (FileNotFoundException ex1) {
                        // Logger.getLogger(CrudEquipement.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                //
                r.setImgViewEquipement(img);
                //
                r.setNomEquipement(rs.getString(1));
                r.setImageEquipement(rs.getString(2));
                r.setNbrEquipement(rs.getInt(3));
                r.setIdEquipement(rs.getInt(4));

                EquipementList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return EquipementList;
    }

    public static boolean ModifierEquipement(Equipement t) {
        try {
            String sql = "UPDATE Equipement SET nom_equipement= ?,image_equipement = ?, nbr_equipement = ? WHERE id_equipement = ?";
            PreparedStatement preparedStatement = Projectbd.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, t.getNomEquipement());
            preparedStatement.setString(2, t.getImageEquipement());
            preparedStatement.setInt(3, t.getNbrEquipement());
            preparedStatement.setInt(4, t.getIdEquipement());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }
//    

    @Override
    public boolean SupprimerEquipement(int idEquipement) {
        try {
            String requete = "DELETE FROM Equipement where id_equipement=" + String.valueOf(idEquipement) + "";
            PreparedStatement pst = Projectbd.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("Equipement supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    public int countTotalEquipement() {
        String req = "SELECT COUNT(*) as cu FROM equipement   ";
        ResultSet rs = null;
        try {
            Statement ste = Projectbd.getInstance().getCnx().createStatement();
            rs = ste.executeQuery(req);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        int cu = 0;
        try {
            while (rs.next()) {
                cu = rs.getInt("cu");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return cu;
    }

}
