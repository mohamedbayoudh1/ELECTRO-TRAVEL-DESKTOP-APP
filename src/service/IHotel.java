/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Hotel;
import java.util.List;

/**
 *
 * @author 
 */
public interface IHotel<P> {
    
    public boolean AjouterHotel(P p);

    public List<Hotel> AfficherAllHotel(P p);

    public boolean SupprimerHotel(int idHotel);
}
