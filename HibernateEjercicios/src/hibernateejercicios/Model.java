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
        
       Conexion();
       List<Maravilla> maravillas = new ArrayList<>();
        
       maravillas = this.session.createQuery("From Maravilla").list();
        
       return maravillas;
    }
    
    public void insertMaravilla(Maravilla m){
        
        Conexion();
        Serializable id = this.session.save(m);
        this.tx.commit();

        
        
    }
    
    public void deleteMaravilla(Maravilla m){
        Conexion();
        this.session.delete(m);
        this.tx.commit();
        
    }
    
    public void closeConnection(){
        this.session.close();
    }
    
    public void updateMaravilla(Maravilla nuevaMaravilla){
        
        Conexion();
        Maravilla m = this.session.load(Maravilla.class, nuevaMaravilla.getId());
        
        m.setNombre(nuevaMaravilla.getNombre());
        m.setPais(nuevaMaravilla.getPais());
        m.setDescripcion(nuevaMaravilla.getDescripcion());
        
        this.session.update(m);
        this.tx.commit();
    }
    
}
