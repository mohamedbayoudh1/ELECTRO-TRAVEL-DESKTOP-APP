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
public class Agence {
    private int id ;
     private String adresse , nom ;
     private int numtel ,nbrbus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public int getNbrbus() {
        return nbrbus;
    }

    public void setNbrbus(int nbrbus) {
        this.nbrbus = nbrbus;
    }

    public Agence(int id, String nom,String adresse, int numtel, int nbrbus) {
        this.id = id;
         this.nom = nom;
        this.adresse = adresse;
       
        this.numtel = numtel;
        this.nbrbus = nbrbus;
    }

    public Agence() {
    }
    public Agence( String nom,String adresse, int numtel, int nbrbus) {
       this.nom = nom;
        this.adresse = adresse;
        
        this.numtel = numtel;
        this.nbrbus = nbrbus;
    }

    @Override
    public String toString() {
        return "Agence{" + "id=" + id + ", adresse=" + adresse + ", nom=" + nom + ", numtel=" + numtel + ", nbrbus=" + nbrbus + '}';
    }
    
     
}
