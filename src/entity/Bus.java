/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author lenovo
 */
public class Bus {
      private int id;
    private String type;
    private int nbrdeplace;
    private int prix;
     private Agence agence;
      public static String pathfile; 
    public static String filename="";
  
    private String image;

    @Override
    public String toString() {
        return "Bus{" + "id=" + id + ", type=" + type + ", nbrdeplace=" + nbrdeplace + ", prix=" + prix +  ", image=" + image +", agence=" + agence+ '}';
    }

    public Bus(int id, String type, int nbrPlace, int prix, String image) {
        this.id = id;
        this.type = type;
        this.nbrdeplace = nbrPlace;
        this.prix = prix;
        this.image = image;
       
    }

    public Bus(String type, int nbrdeplace, int prix, String image) {
        this.type = type;
        this.nbrdeplace = nbrdeplace;
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
        return nbrdeplace;
    }

    public void setNbrPlace(int nbrdeplace) {
        this.nbrdeplace = nbrdeplace;
    }

    public Bus(String type, int nbrdeplace, int prix, Agence agence, String image) {
        this.type = type;
        this.nbrdeplace = nbrdeplace;
        this.prix = prix;
        this.agence = agence;
        this.image = image;
    }

    public int getNbrdeplace() {
        return nbrdeplace;
    }

    public void setNbrdeplace(int nbrdeplace) {
        this.nbrdeplace = nbrdeplace;
    }

    public static String getPathfile() {
        return pathfile;
    }

    public static void setPathfile(String pathfile) {
        Bus.pathfile = pathfile;
    }

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        Bus.filename = filename;
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
