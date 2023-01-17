/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rubenfayoshibernate.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rubenfayoshibernate.Objetos.Pelicula;

/**
 *
 * @author Ruben Fayos
 */
public class ModelPeliculas {
    
SessionFactory sessionFactory;

    public ModelPeliculas() {
        
        //Configuracion básica
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Pelicula.class);
        sessionFactory = configuration.buildSessionFactory();
        
    }
    
    public Session CrearSesion(){
        
        //devuelve la sesion a partir del session factory creado anteriormente
       return sessionFactory.openSession();
       
    }
    
    public ObservableList<Pelicula> ListarTodo(){
        
        Session s = CrearSesion();
        s.beginTransaction();
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        peliculas = s.createQuery("FROM Pelicula").list();
        ObservableList<Pelicula> obPeliculas = FXCollections.observableArrayList(peliculas);
        CerrarSesion(s);
        return obPeliculas;
        
    }
 
    
    public Pelicula ListarSelect(String sentencia){
        
        //en este caso el select lo hacemos del nombre del cliente para identificarlo
        Pelicula p = (Pelicula) CrearSesion().get(Pelicula.class, sentencia);
        return p;
        
    }
    
    public boolean Crear(Pelicula p){
        
        //Crea un nuevo cliente en la bdd
        Session s = CrearSesion();
        s.beginTransaction();
        Serializable id = s.save(p);
        CerrarSesion(s);
        return true;
    }
    
    
    public boolean Eliminar(Pelicula p){
        
        //Eliminar la pelicula
        Session s = CrearSesion();
        s.beginTransaction();
        s.delete(p);
        CerrarSesion(s);
        return true;
        //saca un comprobador
        
    }
    
    public boolean Editar(Pelicula nuevaPelicula){
        
        //Recibe el cliente actual y uno modificado y lo actualiza
        Session s = CrearSesion();
        s.beginTransaction();
        s.update(nuevaPelicula);
        CerrarSesion(s);
        return true;
        
    }
    
    public void CerrarSesion(Session s){
        s.getTransaction().commit();
        s.close();
    }
}
