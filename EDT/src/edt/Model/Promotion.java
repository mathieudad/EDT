/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

/**
 *
 * @author mathi
 */
public class Promotion {
    private int id_promo;
    private String nom;
    
    public Promotion(int id_promo, String nom){
        this.nom = nom;
    }

    public Promotion() {
       
    }

    public int getId_promo() {
        return id_promo;
    }

    public String getNom() {
        return nom;
    }
    
    
}
