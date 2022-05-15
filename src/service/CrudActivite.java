                                            /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Activite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Projectbd;


public class CrudActivite implements IActivite<Activite> {

    @Override
    public boolean AjouterActivite(Activite p) {
        try {
            String requete = "INSERT INTO Activite (nom_activite,type_activite,date_activite,heure,duree)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = Projectbd.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNomActivite());
            pst.setString(2, p.getTypeActivite());
            pst.setDate(3, p.getDateActivite());
            pst.setString(4, p.getHeureActivite());
            pst.setString(5, p.getDureeActivite());

            pst.executeUpdate();

            System.out.println("fournisseur été ajouté ✔");
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public List<Activite> AfficherAllActivite(Activite t) {
        List<Activite> ListActivite = new ArrayList<>();
        try {
            String requete = "SELECT id_activite,nom_activite,type_activite,date_activite,heure,duree FROM activite ORDER BY id_activite DESC";
            Statement pst = Projectbd.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Activite r = new Activite();
                r.setIdActivite(rs.getInt(1));
                r.setNomActivite(rs.getString(2));
                r.setTypeActivite(rs.getString(3));
                r.setDateActivite(rs.getDate(4));
                r.setHeureActivite(rs.getString(5));
                r.setDureeActivite(rs.getString(6));

                ListActivite.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return ListActivite;
    }

    public static boolean ModifierFournisseur(Activite t) {
        try {
            String sql = "UPDATE activite SET nom_activite= ?,type_activite = ?, date_activite = ?, heure = ?, duree = ? WHERE id_activite = ?";
            PreparedStatement preparedStatement = Projectbd.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, t.getNomActivite());
            preparedStatement.setString(2, t.getTypeActivite());
            preparedStatement.setDate(3, t.getDateActivite());
            preparedStatement.setString(4, t.getHeureActivite());
            preparedStatement.setString(5, t.getDureeActivite());
            preparedStatement.setInt(6, t.getIdActivite());

            System.out.println("Activite été modifié ✔");

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean SupprimerActivite(int idActivite) {
        try {
            String requete = "DELETE FROM Activite where id_activite=" + String.valueOf(idActivite) + "";
            PreparedStatement pst = Projectbd.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("Activite supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    public int countTotalActivite() {
        String req = "SELECT COUNT(*) as cu FROM Activite   ";
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
