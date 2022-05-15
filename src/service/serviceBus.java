/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Bus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Projectbd;

/**
 *
 * @author lenovo
 */
public class serviceBus {

    Connection connexion;
    Statement stm;
    private int nb1, nb2, nb3;

    public serviceBus() {
        connexion = Projectbd.getInstance().getCnx();
    }

    public void ajouterBus(Bus b) throws SQLException {
        String req = "INSERT INTO `bus` (`type`, `nbrdeplace`,`prix`,`image`) VALUES ( '"
                + b.getType() + "', '" + b.getNbrPlace() + "', '" + b.getPrix() + "', '" + b.getImage() + "') ";

        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    public List<Bus> afficherBus() throws SQLException {
        List<Bus> buses = new ArrayList<>();
        String req = "select * from bus";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Bus b = new Bus(
              rst.getInt("id"),
            rst.getString("type"),
            rst.getInt("nbrdeplace"),
            rst.getInt("prix"),
            rst.getString("image"));
           

            buses.add(b);
        }
        return buses;
    }
    

    public void SupprimerBus(Bus b) throws SQLException {
        // String req = "DELETE FROM `bus` WHERE buq_id='"+a+"' ";

        String req = "delete from bus where id= ?";

        PreparedStatement pst = connexion.prepareStatement(req);
        int id = b.getId();
        pst.setInt(1, id);
        pst.executeUpdate();
    }

    public void ModifierBus(Bus b) throws SQLException {
        String req = "UPDATE `bus` SET `type`='" + b.getType() + "',`nbrdeplace`='" + b.getNbrPlace() + "',`prix`='" + b.getPrix() +  "',`image`='" + b.getImage() + "' WHERE id= " + b.getId();
        PreparedStatement ps = connexion.prepareStatement(req);

        ps.executeUpdate();
    }

    public int pendingType() throws SQLException {
        String req = "SELECT count(id) from bus where type ='comfort';";

        stm = connexion.createStatement();
        //rs=ste.executeQuery(req);
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            nb1 = rst.getInt(1);
        }
        return nb1;
    }

    public int confirmedType() throws SQLException {
        String req = "SELECT count(id) from bus where type ='moyenne';";

        stm = connexion.createStatement();
        //rs=ste.executeQuery(req);
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            nb2 = rst.getInt(1);
        }
        return nb2;
    }

    public int cancelledType() throws SQLException {
     
        String req = "SELECT count(id) from bus where type ='mini';";

        stm = connexion.createStatement();
        //rs=ste.executeQuery(req);
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            nb3 = rst.getInt(1);

        }
        return nb3;
    }

}
