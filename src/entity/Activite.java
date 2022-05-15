/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;


/**
 *
 * @author 
 */
public class Activite {
    
    private String NomActivite, TypeActivite, HeureActivite ,DureeActivite; //
    int idActivite;
    Date DateActivite;

    public Activite() { // Constructeur sans paramétre
    }

    public Activite(String NomActivite, String TypeActivite, Date DateActivite, String HeureActivite, String DureeActivite, int idActivite) { // Constructeur paramétré
        this.NomActivite = NomActivite;
        this.TypeActivite = TypeActivite;
        this.DateActivite = DateActivite;
        this.HeureActivite = HeureActivite;
        this.DureeActivite = DureeActivite;
        this.idActivite = idActivite;
    }

    // Getter & Setter 
    public String getNomActivite() {
        return NomActivite;
    }

    public void setNomActivite(String NomActivite) {
        this.NomActivite = NomActivite;
    }

    public String getTypeActivite() {
        return TypeActivite;
    }

    public void setTypeActivite(String TypeActivite) {
        this.TypeActivite = TypeActivite;
    }

    public Date getDateActivite() {
        return DateActivite;
    }

    public void setDateActivite(Date DateActivite) {
        this.DateActivite = DateActivite;
    }

    public String getHeureActivite() {
        return HeureActivite;
    }

    public void setHeureActivite(String HeureActivite) {
        this.HeureActivite = HeureActivite;
    }

    public String getDureeActivite() {
        return DureeActivite;
    }

    public void setDureeActivite(String DureeActivite) {
        this.DureeActivite = DureeActivite;
    }

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    @Override
    public String toString() {
        return "activite{" + "NomActivite=" + NomActivite + ", TypeActivite=" + TypeActivite + ", DateActivite=" + DateActivite + ", HeureActivite=" + HeureActivite + ", DureeActivite=" + DureeActivite + ", idActivite=" + idActivite + '}';
    }
    
    

}
