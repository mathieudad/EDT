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
public class TypeCours {
    private int id;
    private String nom;
    
    public TypeCours(int id, String nom){
        this.id = id;
        this.nom = nom;
    }
    
    public TypeCours(){
        
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    
    
}
