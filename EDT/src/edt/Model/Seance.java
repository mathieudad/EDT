/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author mathi
 */
public class Seance {
    private int id;
    private int semaine;
    private Date date;
    private Time heure_debut;
    private Time heure_fin;
    private int Etat;
    private Cours cours;
    private TypeCours typeCours;
    private ArrayList<String> groupes;
    private ArrayList<String> enseignants;
    private ArrayList<String> salles;

    public Seance(int id, int semaine, Date date, Time heure_debut, Time heure_fin, int Etat, Cours cours, TypeCours typeCours, ArrayList<String> groupes, ArrayList<String> enseignants, ArrayList<String> salles) {
        this.id = id;
        this.semaine = semaine;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.Etat = Etat;
        this.cours = cours;
        this.typeCours = typeCours;
        this.groupes = groupes;
        this.enseignants = enseignants;
        this.salles = salles;
    }

  
    public Seance(){
        
    }
    
    public void printInfos(){
        System.out.println("cours :" + cours.getNom());
        for(String e : enseignants){
            System.out.println(e);
        }
        for(String e : salles){
            System.out.println(e);
        }
        for(String e : groupes){
            System.out.println(e);
        }
    }

    public int getId() {
        return id;
    }

    public int getSemaine() {
        return semaine;
    }

    public Date getDate() {
        return date;
    }

    public Time getHeure_debut() {
        return heure_debut;
    }

    public Time getHeure_fin() {
        return heure_fin;
    }

    public int getEtat() {
        return Etat;
    }

    public Cours getCours() {
        return cours;
    }

    public TypeCours getTypeCours() {
        return typeCours;
    }
    
      public ArrayList<String> getGroupes() {
        return groupes;
    }

    public ArrayList<String> getEnseignants() {
        return enseignants;
    }

    public ArrayList<String> getSalles() {
        return salles;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHeure_debut(Time heure_debut) {
        this.heure_debut = heure_debut;
    }

    public void setHeure_fin(Time heure_fin) {
        this.heure_fin = heure_fin;
    }

    public void setEtat(int Etat) {
        this.Etat = Etat;
    }
    
    
    
    
    
}
