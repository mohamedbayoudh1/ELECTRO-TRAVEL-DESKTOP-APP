/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import entity.Restaurant;

/**
 *
 * @author yassin
 */
public interface IRestaurant <P>  {

    public boolean AjouterRestaurant(P p);

    public List<Restaurant> AfficherAllRestaurant(P p);

    public boolean SupprimerRestaurant(int idRestaurant);

}
