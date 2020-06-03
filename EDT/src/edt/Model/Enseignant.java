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
    private Cours cours;
    private ArrayList<Seance> seances;

    public Enseignant(Cours cours, ArrayList<Seance> seances, int id, String email, String passwd, String nom, String prenom, String droit) {
        super(id, email, passwd, nom, prenom, droit);
        this.cours = cours;
        this.seances = seances;
    }
    

    public Enseignant() {
       
    }

    public Cours getCours() {
        return cours;
    }

    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    public ArrayList<Seance> getSeances(int week) {
        ArrayList<Seance> seancesWeek = new ArrayList();
        for(Seance s : seances){
            if(s.getSemaine() == week){
                seancesWeek.add(s);
            }
        }
        return seancesWeek;
    }
    
}
