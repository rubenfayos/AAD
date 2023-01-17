/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rubenfayoshibernate.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rubenfayoshibernate.Objetos.Cliente;

/**
 *
 * @author Ruben Fayos
 */
public class ModelCliente {
    
    SessionFactory sessionFactory;

    public ModelCliente() {
        
        //Configuracion básica
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Cliente.class);
        sessionFactory = configuration.buildSessionFactory();
        
    }
    
    public Session CrearSesion(){
        
        //devuelve la sesion a partir del session factory creado anteriormente
       return sessionFactory.openSession();
       
    }
    
    
    public List ListarTodo(){
        //Lista todos los clientes
        
        Session s = CrearSesion();
        s.beginTransaction();
         
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        listaClientes = s.createQuery("From Cliente").list();
        CerrarSesion(s);
        return listaClientes;
        
    }
    
    
    public Cliente ListarSelect(int id){
        
        Session s = CrearSesion();
        s.beginTransaction();
        //en este caso el select lo hacemos del nombre del cliente para identificarlo
        Cliente c = (Cliente) s.get(Cliente.class, id);
        CerrarSesion(s);
        return c;
        
    }
    
    public boolean Crear(Cliente c){
        
        //Crea un nuevo cliente en la bdd
        Session s = CrearSesion();
        s.beginTransaction();
        Serializable id = s.save(c);
        CerrarSesion(s);
        return true;
    }
    
    
    public boolean Eliminar(Cliente c){
        
        //Recibe el cliente y lo elimina
        Session s = CrearSesion();
        s.beginTransaction();
        s.delete(c);
        CerrarSesion(s);
        return true;
        //saca un comprobador
    }
    
    public boolean Editar(Cliente nuevoCliente){
        
        //Recibe el cliente actual y uno modificado y lo actualiza
        Session s = CrearSesion();
        s.beginTransaction();
        s.update(nuevoCliente);
        CerrarSesion(s);
        return true;
    }
    
    public void CerrarSesion(Session s){
        //Metodo que recibe la sesion y la cierra
        s.getTransaction().commit();
        s.close();
    }
}
