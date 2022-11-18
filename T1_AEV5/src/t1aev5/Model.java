/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1aev5;

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
            configuration.addAnnotatedClass(Libro.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();

            // Initialize Session Object
            this.session = sessionFactory.openSession();

            this.tx = this.session.beginTransaction();
            
            return true;
            
        }catch(Exception e){
            return false;
        }
        
        
    }
    
    public Libro listarLibro(int id){
        
        Libro l = (Libro) this.session.get(Libro.class, id);

        return l;
    }
    
    public List<Libro> listarLibros(){
        
       List<Libro> libros = new ArrayList<>();
        
       libros = this.session.createQuery("From Libro").list();
        
        return libros;
    }
    
    public int insertLibro(Libro l){
        
        Serializable id = this.session.save(l);
        this.tx.commit();
        return (int) id;
        
    }
    
    public void updateLibro(int id, Libro nuevoLibro){
        
        Libro l = this.session.load(Libro.class, id);
        
        l.setTitulo(nuevoLibro.getTitulo());
        l.setAutor(nuevoLibro.getAutor());
        l.setAnyoNacimiento(nuevoLibro.getAnyoNacimiento());
        l.setAnyoPublicacion(nuevoLibro.getAnyoPublicacion());
        l.setPaginas(nuevoLibro.getPaginas());
        l.setEditorial(nuevoLibro.getEditorial());
        
        this.session.update(l);
        
        
    }
    
    
    
    public void deleteLibro(Libro l){
        
        this.session.delete(l);
        this.tx.commit();
        
    }
    
}
