/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import entity.Activite;

/**
 *
 * @author 
 */
public interface IActivite<P> {

    public boolean AjouterActivite(P p);

    public List<Activite> AfficherAllActivite(P p);

    public boolean SupprimerActivite(int idActivite);

}
