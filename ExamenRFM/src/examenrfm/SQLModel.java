/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenrfm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLModel {
    
    private String servidor, puerto, usuario, contraseña, bdd;
    private Connection conn;

    public SQLModel() {
    }

    public SQLModel(String servidor, String puerto, String usuario, String contraseña, String bdd) {
        this.servidor = servidor;
        this.puerto = puerto;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.bdd = bdd;
    }
    
    public boolean conexion(){
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + servidor + ":" + puerto + "/" + bdd , usuario, contraseña);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public ArrayList<Maravilla> listarMaravillas(){
        
        ArrayList<Maravilla> maravillas = new ArrayList<>();
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM maravillas");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                Maravilla m = new Maravilla();
                m.setId(rs.getInt(1));
                m.setNombre(rs.getString(2));
                m.setPais(rs.getString(3));
                m.setDescripcion(rs.getString(4));
                
                maravillas.add(m);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return maravillas;
        
    }
    
    public int insertarTodo(ArrayList<Maravilla> maravillas){
        
        int res = 0;
        
        for(Maravilla m : maravillas){
            
            res += insert(m);
            
        }
        
        return res;
    }
    
    public int insert(Maravilla m){
        
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO maravillas (maravilla, pais, descripcion) VALUES (?, ?, ?)");
            
                
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getPais());
            ps.setString(3, m.getDescripcion());

            return ps.executeUpdate();
            
                     
        } catch (SQLException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

        
    }
    
    public int delete(int id){
        
        int res = 0;
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM maravillas WHERE id=?");
                
            ps.setInt(1, id);
   
            res += ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
        
    }
    
    public void ConsultaBruto(String consulta){
        
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement(consulta);
            ps.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public void borrarTabla(String tabla){
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement("TRUNCATE TABLE " + tabla + ";");
            ps.execute();

            
        } catch (SQLException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
