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
public class Enseignant extends Utilisateur{
    private ArrayList<Cours> cours;
    private ArrayList<Seance> seances;

    public Enseignant(ArrayList<Cours> cours, ArrayList<Seance> seances, int id, String email, String passwd, String nom, String prenom, String droit) {
        super(id, email, passwd, nom, prenom, droit);
        this.cours = cours;
        this.seances = seances;
    }
    

    public Enseignant() {
    }
    
    /**
     * infos a afficher dans la jList referant et admin pour un enseignant
     * @return
     */
    public String infosPourList(){
        return nom +" "+ prenom;
    }
    
    /**
     * test si cet enseignant enseigne pour le cours donnée 
     * @param cour
     * @return
     */
    public boolean estEnseignant(Cours cour){
        for(Cours c : cours)
            if(c.getId() == cour.getId())
                return true;
        return false;
    }
    public ArrayList<Cours> getCours() {
        return cours;
    }

    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    /**
     * recupere les seances de l'enseignant pour une semaine donnée
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
