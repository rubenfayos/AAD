/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hibernateejercicios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Ruben Fayos
 */
public class Model {
    
    private Session session;
    private Transaction tx;
    
    public boolean Conexion(){
        
        try{
        
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Maravilla.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();

            // Initialize Session Object
            this.session = sessionFactory.openSession();

            this.tx = this.session.beginTransaction();
            
            return true;
            
        }catch(Exception e){
            return false;
        }
        
        
    }
    
    public List<Maravilla> listarMaravillas(){
        
       List<Maravilla> maravillas = new ArrayList<>();
        
       maravillas = this.session.createQuery("From Maravilla").list();
        
        return maravillas;
    }
    
}
