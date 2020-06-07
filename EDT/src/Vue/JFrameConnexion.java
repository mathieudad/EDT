/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author mathi
 */
public class JFrameConnexion extends JFrame {
    private JPanel panel = new JPanel();
    private JTextField email = new JTextField(20);
    private JPasswordField password = new JPasswordField(20);
    private JLabel labelemail = new JLabel("Votre adresse email :");
    private JLabel labelpassword = new JLabel("Votre mot de passe :");
    private JButton button = new JButton("Se connecter");

    public JFrameConnexion(){
        panel.add(labelemail);
        panel.add(email);
        panel.add(labelpassword);
        panel.add(password);
        panel.add(button);
        build();
    }
    
    private void build(){
        this.setTitle("EDT connexion");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setVisible(true);
    }
    
    /**
     * est lancé quand de mot de passe est mauvais
     */
    public void essayezEncore(){
        panel.removeAll();
        repaint();
        panel.add(new JLabel("Mauvais email ou mdp"));
        panel.add(labelemail);
        panel.add(email);
        panel.add(labelpassword);
        panel.add(password);
        panel.add(button);
        this.add(panel);
        this.setVisible(true);
        
    }

    public JTextField getEmail() {
        return email;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JButton getButton() {
        return button;
    }
    
    
    
}
