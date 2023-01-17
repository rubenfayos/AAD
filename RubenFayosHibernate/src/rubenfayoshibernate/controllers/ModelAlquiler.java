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
import rubenfayoshibernate.Objetos.Alquiler;
import rubenfayoshibernate.Objetos.Pelicula;

/**
 *
 * @author Ruben Fayos
 */
public class ModelAlquiler {
    
    SessionFactory sessionFactory;

    public ModelAlquiler() {
        
        //Configuracion básica
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Alquiler.class);
        sessionFactory = configuration.buildSessionFactory();
        
    }
    
    public Session CrearSesion(){
        
        //devuelve la sesion a partir del session factory creado anteriormente
       return sessionFactory.openSession();
       
    }
    
    
    public ObservableList<Alquiler> ListarTodo(){
        
        //Saco todos los alquileres
        
        Session s = CrearSesion();
        s.beginTransaction();
        List<Alquiler> alquileres = new ArrayList<Alquiler>();
        alquileres = s.createQuery("FROM Alquiler").list();
        ObservableList<Alquiler> obAlquiler = FXCollections.observableArrayList(alquileres);
        CerrarSesion(s);
        return obAlquiler;
        
    }
    
    
    public ObservableList<Alquiler> ListarSelect(String sentencia){
        
        //Saco los alquileres del usuario
        
        Session s = CrearSesion();
        s.beginTransaction();
        List<Alquiler> alquileres = new ArrayList<Alquiler>();
        alquileres = s.createQuery("FROM Alquiler").list();
        ObservableList<Alquiler> obAlquiler = FXCollections.observableArrayList(alquileres);
        CerrarSesion(s);
        return obAlquiler;
        
    }
    
    public boolean Crear(Alquiler a){
        
        //Crea un nuevo Alquiler en la bdd
        Session s = CrearSesion();
        s.getTransaction();
        Serializable id = s.save(a);
        CerrarSesion(s);
        return true;
    }
    
    /*
    public boolean Eliminar(){
        
    }
    
    public boolean Editar(){
        
    }
    */
    public void CerrarSesion(Session s){
        s.getTransaction().commit();
        s.close();
    }
    
}
