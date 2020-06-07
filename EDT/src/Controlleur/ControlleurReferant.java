/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Vue.JFrameReferant;
import edt.Model.Enseignant;
import edt.Model.Etudiant;
import edt.Model.Groupe;
import edt.Model.Referant;
import edt.Model.Salle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author mathi
 */
public class ControlleurReferant{
    Referant referant;
    JFrameReferant jFrameReferant;
    ControlleurEnseignant ce= null;
    ControlleurEtudiant cetu =null;
    ControlleurSalle cs = null;
    ControlleurGroupe cg = null;
    
    /**
     * Constructeur du controlleur referant
     * @param referant
     */
    public ControlleurReferant(Referant referant){
        this.referant = referant;
        jFrameReferant = new JFrameReferant(referant);
        addMenuListener();
        ajoutListener();
    }
    
    /**
     * Listener du retour a l'accueil dans le menu referant
     */
    public void addMenuListener(){
        jFrameReferant.getAccueil().addMenuListener(new MenuListener(){
            @Override
            public void menuSelected(MenuEvent me) {
                 jFrameReferant.setVisible(false);
                 if(ce !=null)
                    ce.getjFrame().setVisible(false);
                 if(cetu !=null)
                    cetu.getjFrame().setVisible(false);
                 if(cs !=null)
                    cs.getjFrame().setVisible(false);
                 if(cg !=null)
                    cg.getjFrame().setVisible(false);
                 new ControlleurReferant(referant);
                 //System.exit(0);
            }

            @Override
            public void menuDeselected(MenuEvent me) {
                
            }

            @Override
            public void menuCanceled(MenuEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
    }
    
    /**
     * ajoute les 4 listener aux listes de JFrameRef et lorsqu'u objet de list est cliqué ouvre le controlleur de l'emploi du temps de celui-ci
     */
    public void ajoutListener(){
        MouseListener mlEnseignant = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  int index = jFrameReferant.getEnseignantList().locationToIndex(e.getPoint());
                  Enseignant ens = referant.getEnseignants().get(index);                  
                  jFrameReferant.setVisible(false);
                  ce = new ControlleurEnseignant(ens);
                  ce.getjFrame().ajoutMenu(jFrameReferant.getJMenuBar());
                  
            }
        };
        jFrameReferant.getEnseignantList().addMouseListener(mlEnseignant);
         MouseListener mlGroupe = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  int index = jFrameReferant.getGroupeList().locationToIndex(e.getPoint());
                  Groupe groupe = referant.getGroupes().get(index);
                  jFrameReferant.setVisible(false);
                  cg = new ControlleurGroupe(groupe);
                  cg.getjFrame().ajoutMenu(jFrameReferant.getJMenuBar());
                  
            }
        };
        jFrameReferant.getGroupeList().addMouseListener(mlGroupe);
         MouseListener mlEtudiant = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  int index = jFrameReferant.getEtudiantList().locationToIndex(e.getPoint());
                  Etudiant etu = referant.getEtudiants().get(index);
                  jFrameReferant.setVisible(false);
                  cetu = new ControlleurEtudiant(etu);
                  cetu.getjFrame().ajoutMenu(jFrameReferant.getJMenuBar());
                  
            }
        };
        jFrameReferant.getEtudiantList().addMouseListener(mlEtudiant);
         MouseListener mlSalle = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  int index = jFrameReferant.getSalleList().locationToIndex(e.getPoint());
                  Salle salle = referant.getSalles().get(index);
                  jFrameReferant.setVisible(false);
                  cs = new ControlleurSalle(salle);
                  cs.getjFrame().ajoutMenu(jFrameReferant.getJMenuBar());
                  
            }
        };
        jFrameReferant.getSalleList().addMouseListener(mlSalle);
    }

    public JFrameReferant getjFrame() {
        return jFrameReferant;
    }

    public ControlleurEnseignant getCe() {
        return ce;
    }

    public ControlleurEtudiant getCetu() {
        return cetu;
    }

    public ControlleurSalle getCs() {
        return cs;
    }

    public ControlleurGroupe getCg() {
        return cg;
    }

    
  
    
}
