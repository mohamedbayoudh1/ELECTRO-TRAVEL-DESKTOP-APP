/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author lenovo
 */
public class Bus {
      private int id;
    private String type;
    private int nbrPlace;
    private int prix;
     private Agence agence;
      public static String pathfile; 
    public static String filename="";
  
    private String image;

    @Override
    public String toString() {
        return "Bus{" + "id=" + id + ", type=" + type + ", nbrPlace=" + nbrPlace + ", prix=" + prix +  ", image=" + image +", agence=" + agence+ '}';
    }

    public Bus(int id, String type, int nbrPlace, int prix, String image) {
        this.id = id;
        this.type = type;
        this.nbrPlace = nbrPlace;
        this.prix = prix;
        this.image = image;
        this.agence=agence ;
    }

    public Bus(String type, int nbrPlace, int prix, String image) {
        this.type = type;
        this.nbrPlace = nbrPlace;
        this.prix = prix;
        this.image = image;
       
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Bus() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
