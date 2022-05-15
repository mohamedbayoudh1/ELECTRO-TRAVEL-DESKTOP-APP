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
public class Restaurant {

    private String NomRestaurant, ImageRestaurant,LieuxRestaurant;
    int idRestaurant, nbrFourchetteRestaurant;
    ImageView imgViewRestaurant;

    public Restaurant() { // Constructeur sans paramétre
    }

    // Constructeur  paramétré
    public Restaurant(String NomRestaurant, String ImageRestaurant, String LieuxRestaurant, int idRestaurant, int nbrFourchetteRestaurant, ImageView imgViewRestaurant) {
        this.NomRestaurant = NomRestaurant;
        this.ImageRestaurant = ImageRestaurant;
        this.LieuxRestaurant = LieuxRestaurant;
        this.idRestaurant = idRestaurant;
        this.nbrFourchetteRestaurant = nbrFourchetteRestaurant;
        this.imgViewRestaurant = imgViewRestaurant;
    }

    public String getNomRestaurant() {
        return NomRestaurant;
    }

    public void setNomRestaurant(String NomRestaurant) {
        this.NomRestaurant = NomRestaurant;
    }

    public String getImageRestaurant() {
        return ImageRestaurant;
    }

    public void setImageRestaurant(String ImageRestaurant) {
        this.ImageRestaurant = ImageRestaurant;
    }

    public String getLieuxRestaurant() {
        return LieuxRestaurant;
    }

    public void setLieuxRestaurant(String LieuxRestaurant) {
        this.LieuxRestaurant = LieuxRestaurant;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public int getNbrFourchetteRestaurant() {
        return nbrFourchetteRestaurant;
    }

    public void setNbrFourchetteRestaurant(int nbrFourchetteRestaurant) {
        this.nbrFourchetteRestaurant = nbrFourchetteRestaurant;
    }

    public ImageView getImgViewRestaurant() {
        return imgViewRestaurant;
    }

    public void setImgViewRestaurant(ImageView imgViewRestaurant) {
        this.imgViewRestaurant = imgViewRestaurant;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "NomRestaurant=" + NomRestaurant + ", ImageRestaurant=" + ImageRestaurant + ", LieuxRestaurant=" + LieuxRestaurant + ", idRestaurant=" + idRestaurant + ", nbrFourchetteRestaurant=" + nbrFourchetteRestaurant + ", imgViewRestaurant=" + imgViewRestaurant + '}';
    }

    
     
}
