/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import entity.Equipement;

/**
 *
 * @author 
 */
public interface IEquipementt<P> {
    
    public boolean AjouterEquipement(P p);

    public List<Equipement> AfficherAllEquipement(P p);

    public boolean SupprimerEquipement(int idEquipement);
}
