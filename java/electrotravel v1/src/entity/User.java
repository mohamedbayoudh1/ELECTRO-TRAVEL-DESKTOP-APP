/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Asus
 */
public class User {
    int   id_user; 
    String    mdp,nom,prenom, email, adresse_user ; 
    private Role role  ; 
    int numtel_user ;

    public User() {
    }

    public User(int id_user, String mdp, String nom, String prenom, String email, Role role,  int numtel_user,String adresse_user) {
        this.id_user = id_user;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;

        
        this.numtel_user = numtel_user;
            this.adresse_user = adresse_user;
    }

    public User(String mdp, String email) {
        this.mdp = mdp;
        this.email = email;
    }

   
    
    
    
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAdresse_user() {
        return adresse_user;
    }

    public void setAdresse_user(String adresse_user) {
        this.adresse_user = adresse_user;
    }

    public int getNumtel_user() {
        return numtel_user;
    }

    public void setNumtel_user(int numtel_user) {
        this.numtel_user = numtel_user;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", mdp=" + mdp + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse_user=" + adresse_user + ", role=" + role + ", numtel_user=" + numtel_user + '}'+'\n';
    }

   








}
