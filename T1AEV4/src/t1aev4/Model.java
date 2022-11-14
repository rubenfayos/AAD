/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1aev4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
                l.setAñoNacimiento(rs.getString("añoNacimiento"));
                l.setAñoPublicacion(rs.getString("añoPublicacion"));
                l.setEditorial(rs.getString("editorial"));
                l.setPaginas(rs.getString("paginas"));
             
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
            
            //ResultSetMetaData para averiguar las columnas que queremos
            ResultSetMetaData rsmd = rs.getMetaData();
            
            //Cogemos las columnas 
            int columns = rsmd.getColumnCount();
            
                while(rs.next()) {
                    
                    Libro l = new Libro();
                    
                    for(int i = 1; i <= columns; i++){

                        if(rsmd.getColumnName(i).equals("titulo")){
                            l.setTitulo(rs.getString("titulo"));
                            continue;
                        }

                        if(rsmd.getColumnName(i).equals("autor")){ 
                            l.setAutor(rs.getString("autor"));
                            continue;
                        }

                        if(rsmd.getColumnName(i).equals("añoNacimiento")){
                            l.setAñoNacimiento(rs.getString("añoNacimiento"));
                            continue;
                        }

                        if(rsmd.getColumnName(i).equals("añoPublicacion")){
                            l.setAñoPublicacion(rs.getString("añoPublicacion"));
                            continue;
                        }

                        if(rsmd.getColumnName(i).equals("editorial")){
                            l.setEditorial(rs.getString("editorial"));
                            continue;
                        }

                        if(rsmd.getColumnName(i).equals("paginas")){
                            l.setPaginas(rs.getString("paginas"));
                            continue;
                        }
  
                    }
                    
                    if(l.getAutor() == null)
                        l.setAutor("");
                    if(l.getTitulo() == null)
                        l.setTitulo("");
                    if(l.getAñoNacimiento() == null)
                        l.setAñoNacimiento("");
                    if(l.getAñoPublicacion() == null)
                        l.setAñoPublicacion("");
                    if(l.getEditorial() == null)
                        l.setEditorial("");
                    if(l.getPaginas() == null)
                        l.setPaginas("");
                    
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
            ps.setString(3, l.getAñoNacimiento());
            ps.setString(4, l.getAñoPublicacion());
            ps.setString(5, l.getEditorial());
            ps.setString(6, l.getPaginas());
            
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
    
    public int creacionBDD(){
        
        int temp = 0;
        
        try {
            
            String sql = "DROP table if exists libros;\n" +
            "\n" +
            "create table libros(id int auto_increment primary key,\n" +
                                "titulo varchar(255) DEFAULT \"N.C\",\n" +
                                "autor varchar(255) DEFAULT \"N.C\",\n" +
                                "añoNacimiento varchar(4) DEFAULT \"N.C\",\n" +
                                "añoPublicacion varchar(4) DEFAULT \"N.C\",\n" +
                                "editorial varchar(255) DEFAULT \"N.C\",\n" +
                                "paginas varchar(6) DEFAULT \"N.C\");";
            
            //SQL de insert
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
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

                if(!values[0].equals(""))
                    l.setTitulo(values[0]);
                else
                    l.setTitulo("N.C");
                
                if(!values[1].equals(""))
                    l.setAutor(values[1]);
                else
                    l.setAutor("N.C");
                
                if(!values[2].equals(""))
                    l.setAñoNacimiento(values[2]);
                else
                    l.setAñoNacimiento("N.C");
                
                if(!values[3].equals(""))
                    l.setAñoPublicacion(values[3]);
                else
                    l.setAñoPublicacion("N.C");
                
                if(!values[4].equals(""))
                    l.setEditorial(values[4]);
                else
                    l.setEditorial("N.C");
                
                if(!values[5].equals(""))
                    l.setPaginas(values[5]);
                else
                    l.setPaginas("N.C");

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
