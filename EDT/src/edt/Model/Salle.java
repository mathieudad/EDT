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
public class Salle {
    private int id;
    private String nom;
    private int capacite;
    private Site site;
    
    public Salle(int id, String nom, int capacite, Site site){
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.site = site;
    }

    public Salle() {
        id = -1;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public Site getSite() {
        return site;
    }
    
    public void printInfos(){
        System.out.println(nom +" "+ site.getNom());
    }
    
    
}
