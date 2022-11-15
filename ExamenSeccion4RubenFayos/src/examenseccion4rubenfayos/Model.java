/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenseccion4rubenfayos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruben Fayos
 */
public class Model {
    
    private Connection conn;
    private ArrayList<Ciudad> ciudades = new ArrayList<>();
    private String servidor = "localhost", puerto = "3306", nombreBDD = "world2", usuario = "2DAM", contraseña = "2DAM2022";

    public Model(String servidor, String puerto, String nombreBDD, String usuario, String contraseña) {
        this.servidor = servidor;
        this.puerto = puerto;
        this.nombreBDD = nombreBDD;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Model() {
    }
    
    
    
    public int Conexion(){
        
        String url;  
        this.conn = null;  
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");  
            url="jdbc:mysql://" + servidor + ":" + puerto + "/" + nombreBDD;
            
            this.conn = DriverManager.getConnection(url, usuario, contraseña); 

            return 1;
            
        }catch (Exception e) {  
            System.out.println(e.toString());
            return 0;
        }
        
    }
    
    public ArrayList<Ciudad> recorrerCiudades(){
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM city");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                
                Ciudad c = new Ciudad();
                c.setId(rs.getInt("ID"));
                c.setNombre(rs.getString("Name"));
                c.setCodigo(rs.getString("CountryCode"));
                c.setDistrito(rs.getString("District"));
                c.setPoblacion(rs.getInt("Population"));
                
                this.ciudades.add(c);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.ciudades;
           
    }
    
    public void delete(int id){
        
        try {
            
            //SQL de insert
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM city WHERE ID =?;");
            
            ps.setInt(1, id);
            
            ps.execute();
                 
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
             
    }
    
    public void insert(Ciudad c){
        
        try {
            
            //SQL de insert
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO city (Name, CountryCode, District, Population, ID) VALUES(?,?,?,?,?);");
            
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCodigo());
            ps.setString(3, c.getDistrito());
            ps.setInt(4, c.getPoblacion());
            ps.setInt(5, c.getId());

            ps.execute();
                 
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void update(Ciudad c){
        
        try {
            
            //SQL de insert
            PreparedStatement ps = this.conn.prepareStatement("UPDATE city SET Name=? ,CountryCode=?, District=?, Population=? WHERE ID=?;");
            
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCodigo());
            ps.setString(3, c.getDistrito());
            ps.setInt(4, c.getPoblacion());
            ps.setInt(5, c.getId());

            ps.execute();
                 
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Ciudad> consultaBruto(String sql){
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                
                Ciudad c = new Ciudad();
                c.setId(rs.getInt("ID"));
                c.setNombre(rs.getString("Name"));
                c.setCodigo(rs.getString("CountryCode"));
                c.setDistrito(rs.getString("District"));
                c.setPoblacion(rs.getInt("Population"));
                
                this.ciudades.add(c);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.ciudades;
           
    }
    
}
