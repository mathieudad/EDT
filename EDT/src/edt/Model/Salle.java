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
    
    /**
     * Constructeur de la salle
     * @param id
     * @param nom
     * @param capacite
     * @param site
     * @param seances
     */
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
    
    /**
     *retourne une string avec la salle et le site pour la liste
     * @return
     */
    public String infosPourList(){
        return nom +" , "+site.getNom();
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
    
    /**
     * retourne les seances pour un week donné
     * @param week
     * @return
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
     * retourne les seances a cette date
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
