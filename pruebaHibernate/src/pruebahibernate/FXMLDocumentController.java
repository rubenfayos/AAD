/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package pruebahibernate;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author denos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Song.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        // Initialize Session Object
        Session session = sessionFactory.openSession();
        
        Transaction tx = session.beginTransaction();
        
        //Get Example
        try{
            Song song1 = (Song) session.get(Song.class, new Integer(1));
            System.out.println("Song get called");
            if(song1 != null){
                System.out.println("Song GET ID= "+song1.getId());
                System.out.println("Song Get Details:: "+song1.getArtist()+"\n");
                System.out.println("Song Get Details:: "+song1.getSongName()+"\n");
            }
        }catch(Exception e){
                e.printStackTrace();
        }        
        
        
        
        /*
        Song song1 = new Song();
        
         song1.setId(1);
        song1.setSongName("Broken Angel");
        song1.setArtist("Akon");
 
        session.beginTransaction();
 
        // Here we have used
        // save() method of JPA
        session.save(song1);
        */
        //session.getTransaction().commit();
    }    
    
}
