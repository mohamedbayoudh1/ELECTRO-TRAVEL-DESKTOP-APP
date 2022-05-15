/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
import entity.Restaurant;
import utils.Projectbd;

public class CrudRestaurant implements IRestaurant<Restaurant> {

    @Override
    public boolean AjouterRestaurant(Restaurant p) {
        try {
            String requete = "INSERT INTO Restaurant (nom_restaurant,nombre_fourchette,image_restaurant,lieux)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = Projectbd.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNomRestaurant());
            pst.setInt(2, p.getNbrFourchetteRestaurant());
            pst.setString(3, p.getImageRestaurant());
            pst.setString(4, p.getLieuxRestaurant());

            pst.executeUpdate();

            System.out.println("Restaurant été ajouté ✔");
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public List<Restaurant> AfficherAllRestaurant(Restaurant t) {
        List<Restaurant> RestaurantList = new ArrayList<>();
        try {
            String requete = "SELECT nom_restaurant,nombre_fourchette,image_restaurant,lieux,idrestaurant FROM Restaurant ORDER BY idrestaurant DESC";
            Statement pst = Projectbd.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Restaurant r = new Restaurant();
                ///
                ImageView img = new ImageView();
                Image image;
                try {
                    if (rs.getString("image_restaurant") == null) {
                    } else if (rs.getString("image_restaurant") != null) {
                        image = new Image(new FileInputStream(("C:\\xampp\\htdocs\\Projet\\Uploads\\" + rs.getString("image_restaurant"))));
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
                r.setImgViewRestaurant(img);
                //
                r.setNomRestaurant(rs.getString(1));
                r.setNbrFourchetteRestaurant(rs.getInt(2));
                r.setImageRestaurant(rs.getString(3));
                r.setLieuxRestaurant(rs.getString(4));
                r.setIdRestaurant(rs.getInt(5));

                RestaurantList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return RestaurantList;
    }

    public static boolean ModifierRestaurant(Restaurant t) {
        try {
            String sql = "UPDATE Restaurant SET nom_restaurant= ?,nombre_fourchette = ?, image_restaurant = ? , lieux = ? WHERE idrestaurant = ?";
            PreparedStatement preparedStatement = Projectbd.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, t.getNomRestaurant());
            preparedStatement.setInt(2, t.getNbrFourchetteRestaurant());
            preparedStatement.setString(3, t.getImageRestaurant());
            preparedStatement.setString(4, t.getLieuxRestaurant());
            preparedStatement.setInt(5, t.getIdRestaurant());

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
    public boolean SupprimerRestaurant(int idRestaurant) {
        try {
            String requete = "DELETE FROM Restaurant where idrestaurant=" + String.valueOf(idRestaurant) + "";
            PreparedStatement pst = Projectbd.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("Restaurant supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    public int countEtoileRestaurant(int nbrEtoile) {
        String req = "SELECT COUNT(*) as cu FROM Restaurant u WHERE nombre_fourchette =" + String.valueOf(nbrEtoile) + "";
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
