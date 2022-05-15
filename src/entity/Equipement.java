/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.scene.image.ImageView;

/**
 *
 * @author 
 */
public class Equipement {

    private String NomEquipement, ImageEquipement;

    int idEquipement, nbrEquipement;

    ImageView imgViewEquipement;

    public Equipement() { // Constructeur sans paramétré
    }

    public Equipement(String NomEquipement, String ImageEquipement, int idEquipement, int nbrEquipement, ImageView imgViewEquipement) { // Constructeur paramétré
        this.NomEquipement = NomEquipement;
        this.ImageEquipement = ImageEquipement;
        this.idEquipement = idEquipement;
        this.nbrEquipement = nbrEquipement;
        this.imgViewEquipement = imgViewEquipement;
    }

    public String getNomEquipement() {
        return NomEquipement;
    }

    public void setNomEquipement(String NomEquipement) {
        this.NomEquipement = NomEquipement;
    }

    public String getImageEquipement() {
        return ImageEquipement;
    }

    public void setImageEquipement(String ImageEquipement) {
        this.ImageEquipement = ImageEquipement;
    }

    public int getIdEquipement() {
        return idEquipement;
    }

    public void setIdEquipement(int idEquipement) {
        this.idEquipement = idEquipement;
    }

    public int getNbrEquipement() {
        return nbrEquipement;
    }

    public void setNbrEquipement(int nbrEquipement) {
        this.nbrEquipement = nbrEquipement;
    }

    public ImageView getImgViewEquipement() {
        return imgViewEquipement;
    }

    public void setImgViewEquipement(ImageView imgViewEquipement) {
        this.imgViewEquipement = imgViewEquipement;
    }

    @Override
    public String toString() {
        return "equipement{" + "NomEquipement=" + NomEquipement + ", ImageEquipement=" + ImageEquipement + ", idEquipement=" + idEquipement + ", nbrEquipement=" + nbrEquipement + ", imgViewEquipement=" + imgViewEquipement + '}';
    }
    
    
    
    
}
