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
public class Etudiant extends Utilisateur{
    private int numero;
    private Groupe groupe;
    private ArrayList<Seance> seances;
    
    /**
     *
     * @param id
     * @param email
     * @param passwd
     * @param nom
     * @param prenom
     * @param droit
     * @param numero
     * @param groupe
     */
    public Etudiant(int id, String email, String passwd, String nom, String prenom, String droit, int numero, Groupe groupe) {
        super(id, email, passwd, nom, prenom, droit);
        this.numero = numero;
        this.groupe = groupe;
    }
    
}
