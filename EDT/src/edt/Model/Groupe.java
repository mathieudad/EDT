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
public class Groupe {
    private int id;
    private String nom;
    private Promotion promo;
    private ArrayList<Seance> seances;
    
    
    public Groupe(int id, String nom, Promotion promo, ArrayList<Seance> seances){
        this.id = id;
        this.nom = nom;
        this.promo = promo;
        this.seances = seances;
    }
    
    public ArrayList<Seance> seancesFromWeek(int week){
        ArrayList<Seance> seancesWeek = new ArrayList();
        for(Seance s : seances){
            if(s.getSemaine() == week){
                seancesWeek.add(s);
            }
        }
        return seancesWeek;
    }

    public Groupe() {
        id = -1;
    }
    
    public void printInfos(){
        System.out.println(nom+ " " + promo.getNom());
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Promotion getPromo() {
        return promo;
    }

    public ArrayList<Seance> getSeances() {
        return seances;
    }

    public ArrayList<Seance> getSeances(int week) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    
}
