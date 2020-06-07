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
    
    /**
     * Constructeur de groupe
     * @param id
     * @param nom
     * @param promo
     * @param seances
     */
    public Groupe(int id, String nom, Promotion promo, ArrayList<Seance> seances){
        this.id = id;
        this.nom = nom;
        this.promo = promo;
        this.seances = seances;
    }

    public Groupe() {
        id = -1;
    }
    
    /**
     * retourne les infos a afficher dans la JList
     * @return
     */
    public String infosPourList(){
        return nom+ " , " +promo.getNom();
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
    
    /**
     *
     * @param week
     * @return le nombre de seances pour un week donné
     */
    public ArrayList<Seance> getSeances(int week) {
        ArrayList<Seance> seancesWeek = new ArrayList();
        for(Seance s : seances){
            if(s.getSemaine() == week){
                seancesWeek.add(s);
            }
        }
        return seancesWeek;
    }
    
    /**
     * retourne les seances a une date precise
     * @param date
     * @return
     */
    public ArrayList<Seance> getSeances(String date){
         ArrayList<Seance> seancesDate = new ArrayList();
        for(Seance s : seances){
            if(s.getDate().toString().equals(date)){
                seancesDate.add(s);
            }
        }
        return seancesDate;
    }
    
  
    
}
