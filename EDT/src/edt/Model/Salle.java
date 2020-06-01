/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import java.util.ArrayList;

/**
 *
 * @author mathi
 */
public class Salle {
    private int id;
    private String nom;
    private int capacite;
    private Site site;
    private ArrayList<Seance> seances;
    
    public Salle(int id, String nom, int capacite, Site site, ArrayList<Seance> seances){
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.site = site;
        this.seances = seances;
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

    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    
    
    public void printInfos(){
        System.out.println(nom +" "+ site.getNom());
    }
    
    
}
