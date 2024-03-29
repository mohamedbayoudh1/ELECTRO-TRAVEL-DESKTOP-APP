/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Agence;
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
public class serviceAgence {

    Connection connexion;
    Statement stm;

    public serviceAgence() {
        connexion = Projectbd.getInstance().getCnx();
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
    
      public List<Agence> afficherAgenceNBRBUSdesc() throws SQLException {
        List<Agence> agences = new ArrayList<>();
        String req = "select * from agence ORDER BY nbrbus DESC";
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
/*******************************************************************/
      
      public List<Agence> afficherAgenceNBRBUSASC() throws SQLException {
        List<Agence> agences = new ArrayList<>();
        String req = "select * from agence ORDER BY nbrbus ASC";
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
/*******************************************************************/
      
      public List<Agence> afficherAgenceADRESSdesc() throws SQLException {
        List<Agence> agences = new ArrayList<>();
        String req = "select * from agence ORDER BY adresse DESC";
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
/*******************************************************************/
      
      public List<Agence> afficherAgenceADRESSASC() throws SQLException {
        List<Agence> agences = new ArrayList<>();
        String req = "select * from agence ORDER BY adresse ASC";
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
/*******************************************************************/
      
      public List<Agence> afficherAgenceNOMASC() throws SQLException {
        List<Agence> agences = new ArrayList<>();
        String req = "select * from agence ORDER BY nom ASC";
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
/*******************************************************************/
      
      public List<Agence> afficherAgenceNOMDESC() throws SQLException {
        List<Agence> agences = new ArrayList<>();
        String req = "select * from agence ORDER BY nom DESC";
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
/*******************************************************************/
    public void SupprimerAgence(Agence a) throws SQLException {
  
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

    public String QRdonner(String nom) {
      String req = "select * from agence where nom='"+nom+"';";
        String final5 = null;
        try {
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            if (rst.next()){
               
               Agence a = new Agence();
            a.setId(rst.getInt("id"));
            a.setNom(rst.getString("nom"));
            a.setAdresse(rst.getString("adresse"));
            a.setNumtel(rst.getInt("numtel"));
            a.setNbrbus(rst.getInt("nbrbus"));            
                final5=a.toString();
              
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    
    return final5;
    }

}
