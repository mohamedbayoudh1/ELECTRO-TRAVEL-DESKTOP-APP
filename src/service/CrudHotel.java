/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Hotel;
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


public class CrudHotel implements IHotel<Hotel> {

    @Override
    public boolean AjouterHotel(Hotel p) {
        try {
            String requete = "INSERT INTO hotel (nom_hotel,nombre_etoile,image_hotel,lieux)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = Projectbd.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNomHotel());
            pst.setInt(2, p.getNbrEtoileHotel());
            pst.setString(3, p.getImageHotel());
            pst.setString(4, p.getLieuxHotel());

            pst.executeUpdate();

            System.out.println("Hotel été ajouté ✔");
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public List<Hotel> AfficherAllHotel(Hotel t) {
        List<Hotel> HotelList = new ArrayList<>();
        try {
            String requete = "SELECT nom_hotel,nombre_etoile,image_hotel,lieux,idhotel FROM hotel ORDER BY idhotel DESC";
            Statement pst = Projectbd.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Hotel r = new Hotel();
                ///
                ImageView img = new ImageView();
                Image image;
                try {
                    if (rs.getString("image_hotel") == null) {
                    } else if (rs.getString("image_hotel") != null) {
                        image = new Image(new FileInputStream(("C:\\xampp\\htdocs\\Projet\\Uploads\\" + rs.getString("image_hotel"))));
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
                r.setImgViewHotel(img);
                //
                r.setNomHotel(rs.getString(1));
                r.setNbrEtoileHotel(rs.getInt(2));
                r.setImageHotel(rs.getString(3));
                r.setLieuxHotel(rs.getString(4));
                r.setIdHotel(rs.getInt(5));

                HotelList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return HotelList;
    }

    public static boolean ModifierHotel(Hotel t) {
        try {
            String sql = "UPDATE hotel SET nom_hotel= ?,nombre_etoile = ?, image_hotel = ? , lieux = ? WHERE idhotel = ?";
            PreparedStatement preparedStatement = Projectbd.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, t.getNomHotel());
            preparedStatement.setInt(2, t.getNbrEtoileHotel());
            preparedStatement.setString(3, t.getImageHotel());
            preparedStatement.setString(4, t.getLieuxHotel());
            preparedStatement.setInt(5, t.getIdHotel());

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
    public boolean SupprimerHotel(int idHotel) {
        try {
            String requete = "DELETE FROM hotel where idhotel=" + String.valueOf(idHotel) + "";
            PreparedStatement pst = Projectbd.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("Hotel supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    public int countEtoileHotel(int nbrEtoile) {
        String req = "SELECT COUNT(*) as cu FROM hotel u WHERE nombre_etoile =" + String.valueOf(nbrEtoile) + "";
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
            ex.getStackTrace();
        }
        return cu;
    }

}
