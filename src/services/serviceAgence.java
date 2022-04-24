/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Agence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author lenovo
 */
public class serviceAgence {

    Connection connexion;
    Statement stm;

    public serviceAgence() {
        connexion = MyDB.getInstance().getConnexion();
    }

    public void ajouterAgence(Agence a) throws SQLException {
        String req = "INSERT INTO `agence` (`nom`, `adresse`,`numtel`,`nbrbus`) VALUES ( '"
                + a.getNom() + "', '" + a.getAdresse() + "', '" + a.getNumtel() + "', '" + a.getNbrbus() + "') ";

        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    public List<Agence> afficherAgence() throws SQLException {
        List<Agence> agences = new ArrayList<>();
        String req = "select * from agence";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Agence a = new Agence();
            a.setId(rst.getInt("id"));
            a.setNom(rst.getString("nom"));
            a.setAdresse(rst.getString("adresse"));
            a.setNumtel(rst.getInt("numtel"));
            a.setNbrbus(rst.getInt("nbrbus"));

            agences.add(a);
        }
        return agences;
    }

    public void SupprimerAgence(Agence a) throws SQLException {
        //String req = "DELETE FROM `bus` WHERE agence_id='"+a+"' ";

        //stm = connexion.createStatement();
        //stm.executeUpdate(req);
        String req = "delete from agence where id= ?";
        // String req1 = "DELETE FROM `agence` WHERE id='"+a+"' ";

        PreparedStatement pst = connexion.prepareStatement(req);
        int id = a.getId();
        pst.setInt(1, id);
        pst.executeUpdate();

    }

    public void ModifierAgence(Agence a) throws SQLException {
        String req = "UPDATE `agence` SET `nom`='" + a.getNom() + "',`adresse`='" + a.getAdresse() + "',`numtel`='" + a.getNumtel() + "',`nbrbus`='" + a.getNbrbus() + "' WHERE id= " + a.getId();
        PreparedStatement ps = connexion.prepareStatement(req);

        ps.executeUpdate();
    }

    public ObservableList<Agence> getRepasList() throws SQLException {

        ObservableList<Agence> abo = FXCollections.observableArrayList();

        List<Agence> id = new ArrayList<>();
        Statement stm = connexion.createStatement();
        String query = "select * from agence";

        ResultSet rst = stm.executeQuery(query);
        rst = stm.executeQuery(query);
        Agence repas;
        while (rst.next()) {
            Agence a = new Agence();

            a.setNom(rst.getString("nom"));
            a.setAdresse(rst.getString("adresse"));
            a.setNumtel(rst.getInt("numtel"));
            a.setNbrbus(rst.getInt("nbrbus"));

            abo.add(a);

        }
        return abo;

    }

}
