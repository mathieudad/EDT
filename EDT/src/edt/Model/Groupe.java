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
public class Groupe {
    private int id_groupe;
    private String nom;
    private Promotion promo;
    
    public Groupe(int id_groupe, String nom, Promotion promo){
        this.id_groupe = id_groupe;
        this.nom = nom;
        this.promo = promo;
    }

    public Groupe() {
        id_groupe = -1;
    }
    
    public void printInfos(){
        System.out.println(nom+ " " + promo.getNom());
    }

    public int getId_groupe() {
        return id_groupe;
    }

    public String getNom() {
        return nom;
    }

    public Promotion getPromo() {
        return promo;
    }
    
  
    
}
