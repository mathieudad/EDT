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
public class Etudiant extends Utilisateur{
    private int numero;
    private int id_groupe;
    
    /**
     *
     * @param id
     * @param email
     * @param passwd
     * @param nom
     * @param prenom
     * @param droit
     * @param numero
     * @param id_gorupe
     */
    public Etudiant(int id, String email, String passwd, String nom, String prenom, String droit, int numero, int id_gorupe) {
        super(id, email, passwd, nom, prenom, droit);
        this.numero = numero;
        this.id_groupe = id_groupe;
    }
    
}
