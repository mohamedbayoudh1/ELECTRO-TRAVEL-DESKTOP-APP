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
public class Hotel {

    private String NomHotel, ImageHotel,LieuxHotel;  //
    int idHotel, nbrEtoileHotel;
    ImageView imgViewHotel;

    public Hotel() {// Constructeur sans paramétre
    }

    public Hotel(String NomHotel, String ImageHotel, String LieuxHotel, int idHotel, int nbrEtoileHotel, ImageView imgViewHotel) { // Constructeur paramétré
        this.NomHotel = NomHotel;
        this.ImageHotel = ImageHotel;
        this.LieuxHotel = LieuxHotel;
        this.idHotel = idHotel;
        this.nbrEtoileHotel = nbrEtoileHotel;
        this.imgViewHotel = imgViewHotel;
    }

    public String getNomHotel() {
        return NomHotel;
    }

    public void setNomHotel(String NomHotel) {
        this.NomHotel = NomHotel;
    }

    public String getImageHotel() {
        return ImageHotel;
    }

    public void setImageHotel(String ImageHotel) {
        this.ImageHotel = ImageHotel;
    }

    public String getLieuxHotel() {
        return LieuxHotel;
    }

    public void setLieuxHotel(String LieuxHotel) {
        this.LieuxHotel = LieuxHotel;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getNbrEtoileHotel() {
        return nbrEtoileHotel;
    }

    public void setNbrEtoileHotel(int nbrEtoileHotel) {
        this.nbrEtoileHotel = nbrEtoileHotel;
    }

    public ImageView getImgViewHotel() {
        return imgViewHotel;
    }

    public void setImgViewHotel(ImageView imgViewHotel) {
        this.imgViewHotel = imgViewHotel;
    }

    @Override
    public String toString() {
        return "Hotel{" + "NomHotel=" + NomHotel + ", ImageHotel=" + ImageHotel + ", LieuxHotel=" + LieuxHotel + ", idHotel=" + idHotel + ", nbrEtoileHotel=" + nbrEtoileHotel + ", imgViewHotel=" + imgViewHotel + '}';
    }

    
       
}
