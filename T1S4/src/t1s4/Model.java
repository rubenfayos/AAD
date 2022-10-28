/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1s4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rubén Fayos
 */
public class Model {

    private Connection conn;

    public Model() {
    }

    public static void main(String args[]) throws ClassNotFoundException {  
        
        
    }
    
    public int Conexion(){
        
        String url;  
        this.conn = null;  
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");  
            url="jdbc:mysql://localhost:3306/personas";
            
            this.conn = DriverManager.getConnection(url, "2DAM", "2DAM2020"); 
            return 1;
            
        }catch (Exception e) {  
            System.out.println(e.toString());
            return 0;
        }  
   
    }
    
    public ObservableList<Persona> consulta(){
        
        String salida = "";
        ObservableList<Persona> personas = FXCollections.observableArrayList();
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM persona");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                
                Persona p = new Persona();
                
                p.setNombre(rs.getString("nombre"));
                p.setSexo(rs.getString("sexo").charAt(0));
                p.setEdad(rs.getInt("edad"));
                p.setAltura(rs.getDouble("altura"));
                p.setPeso(rs.getDouble("peso"));
                
                personas.add(p);
                    
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return personas;
    }
    
    
    
    public Persona FindPersona(int id){
        
        
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM persona WHERE id=?");
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                
                Persona p = new Persona();
                
                p.setNombre(rs.getString("nombre"));
                p.setEdad(rs.getInt("edad"));
                p.setSexo(rs.getString("sexo").charAt(0));
                p.setAltura(rs.getFloat("altura"));
                p.setPeso(rs.getFloat("peso"));
                
                return p;
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }

    
    
    public int Insert(Persona p){
        
        int temp = 0;
        
        try {
            
            //SQL de insert
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO persona(nombre, edad, sexo, altura, peso) VALUES(?,?,?,?,?);");
            
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setString(3, String.valueOf(p.getSexo()));
            ps.setDouble(4, p.getAltura());
            ps.setDouble(5, p.getPeso());
            
            //Comprueba si se ha insertado correctamente
            temp = ps.executeUpdate();
                 
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
        
    }
    
    public int Update(Persona p, int id){
        
        int temp = 0;
        
        try {
            
            //SQL de insert
            PreparedStatement ps = this.conn.prepareStatement("UPDATE persona SET nombre=?, edad=?, Sexo=?, altura=?, peso=? WHERE id = ?;");
            
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setString(3, String.valueOf(p.getSexo()));
            ps.setDouble(4, p.getAltura());
            ps.setDouble(5, p.getPeso());
            ps.setInt(6, id);
            
            //Comprueba si se ha insertado correctamente
            temp = ps.executeUpdate();
                 
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
        
    }
    
    public int Delete(int id){
        
        int temp = 0;
        
        try {
            
            //SQL de insert
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM PERSONA WHERE id =?;");
            
            ps.setInt(1, id);
            
            //Comprueba si se ha insertado correctamente
            temp = ps.executeUpdate();
                 
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
        
    }
    
    
}
