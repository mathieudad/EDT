/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Connexion extends JFrame{
    //Les imports habituels


  private JPanel container = new JPanel();
  private JTextField email = new JTextField("example@edt.fr");
  private JPasswordField password = new JPasswordField();
  private JLabel labelemail = new JLabel("Votre adresse email :");
  private JLabel labelpassword = new JLabel("Votre mot de passe :");

  public Connexion(){
    this.setTitle("EDT : Votre emploi du temps");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());
    
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    JPanel top = new JPanel();
    top.setBackground(Color.white);
    Font police = new Font("Arial", Font.BOLD, 14);
    email.setFont(police);
    email.setPreferredSize(new Dimension(150, 30));
    email.setForeground(Color.orange);
    
    password.setFont(police);
    password.setPreferredSize(new Dimension(150, 30));
    password.setForeground(Color.orange);
    
    
    top.add(labelemail);
    top.add(email);
    top.add(labelpassword);
    top.add(password);
    
    container.add(top, BorderLayout.CENTER);
    this.setContentPane(container);
    this.setVisible(true);            
  }
  
  private void makeFrameFullSize(JFrame aFrame) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    aFrame.setSize(screenSize.width, screenSize.height);
}
  
 public static void main(String[] args){
     Connexion test = new Connexion();
     test.makeFrameFullSize(test);
 }
}


