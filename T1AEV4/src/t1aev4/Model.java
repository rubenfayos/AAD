/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1aev4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ruben Fayos
 */
public class Model {
    
    private Connection conn;

    public Model() {
    }

    public boolean Conexion(){
        
        String url;  
        this.conn = null;  
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");  
            url="jdbc:mysql://localhost:3306/biblioteca";
            
            this.conn = DriverManager.getConnection(url, "2DAM", "2DAM2022"); 
            return true;
            
        }catch (Exception e) {  
            System.out.println(e.toString());
            return false;
        }
        
    }
    
    public ObservableList<Libro> consulta(){
        
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM libros");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                
                Libro l = new Libro();
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAñoNacimiento(rs.getInt("añoNacimiento"));
                l.setAñoPublicacion(rs.getInt("añoPublicacion"));
                l.setEditorial("editorial");
                l.setPaginas(rs.getInt("paginas"));
             
                libros.add(l);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return libros;
    }
    
    public ObservableList<Libro> consultaManual(String consulta){
        
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        
        try {
            
            PreparedStatement ps = this.conn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            
                while(rs.next()) {
                    
                    if(rs.getString("titulo").isEmpty())
                        break;

                    Libro l = new Libro();
                    l.setTitulo(rs.getString("titulo"));
                    l.setAutor(rs.getString("autor"));
                    l.setAñoNacimiento(rs.getInt("añoNacimiento"));
                    l.setAñoPublicacion(rs.getInt("añoPublicacion"));
                    l.setEditorial("editorial");
                    l.setPaginas(rs.getInt("paginas"));

                    libros.add(l);

                }
            
            
        } catch (SQLException ex) {
            
        }
        
        return libros;
    }
    
    public int Insert(Libro l){
        
        int temp = 0;
        
        try {
            
            //SQL de insert
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO libros (titulo, autor, añoNacimiento, añoPublicacion, editorial, paginas) VALUES(?,?,?,?,?,?);");
            
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setInt(3, l.getAñoNacimiento());
            ps.setInt(4, l.getAñoPublicacion());
            ps.setString(5, l.getEditorial());
            ps.setInt(6, l.getPaginas());
            
            //Comprueba si se ha insertado correctamente
            temp = ps.executeUpdate();
                 
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
        
    }
    
    public int Update(int id){
        
        int temp = 0;
        
        try {
            
            //SQL de insert
            PreparedStatement ps = this.conn.prepareStatement("UPDATE persona SET nombre=?, edad=?, Sexo=?, altura=?, peso=? WHERE id = ?;");
            
            
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
    
    public boolean leerCsv(String csv){
        
        File f = new File(csv);
        
        String line = "";
        
        BufferedReader br; 
        try {
            
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"ISO-8859-1")); //Reader para acentos y ñ
            
            br.readLine();
       		
            while ((line = br.readLine()) != null){  

                Libro l = new Libro();

                String[] values = line.split(";");    // separator  

                l.setTitulo(values[0]);
                l.setAutor(values[1]);
                l.setAñoNacimiento(Integer.parseInt(values[2]));
                l.setAñoPublicacion(Integer.parseInt(values[3]));
                l.setEditorial(values[4]);
                l.setPaginas(Integer.parseInt(values[5]));

                //Inserta los libros en la tabla
                Insert(l);

            }
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
          
    }
    
    
}
