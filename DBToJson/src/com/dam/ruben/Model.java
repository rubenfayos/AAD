/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.ruben;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ruben Fayos
 */
public class Model {
    
    private Connection conn;
    private ArrayList<ArrayList> data;
    
    public void Conexion(String dbName){
        
        String url;  
        this.conn = null;  
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");  
            url="jdbc:mysql://localhost:3306/" + dbName;
            
            this.conn = DriverManager.getConnection(url, "2DAM", "2DAM2022"); 
            //return true;
            
        }catch (Exception e) {  
            System.out.println(e.toString());
            //return false;
        }
        
    }
    
    public void ReadTable(String table){
        
        data = new ArrayList<>();
        ArrayList<String> columns = new ArrayList<>();
        
        try {
        PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM " + table);
        ResultSet rs = ps.executeQuery();
        
        //Getting metadata from the table rs
        ResultSetMetaData rsmd = rs.getMetaData();
        
        //Total columns
        int totalColumns = rsmd.getColumnCount();
        
        while(rs.next()) {
        
            if(columns.size() < 1){
                
                for(int i = 1; i <= totalColumns; i++){

                    columns.add(rsmd.getColumnName(i));

                }
                
                data.add(columns);
            }
            
            ArrayList <String> row = new ArrayList<>();
            
            for(int i = 1; i <= totalColumns; i++){

                row.add(String.valueOf(rs.getObject(i)));

            }
            
            data.add(row);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void writeJson(String url){
        
        //Creating a JSONObject object
        JSONObject parent = new JSONObject();
        int a = 0;
        
        try {

            for(ArrayList row : data){
                JSONObject child = new JSONObject();
                for(int i = 0; i < row.size(); i++){
                        //Takes the position of the column name and the data
                        child.put(String.valueOf(data.get(0).get(i)), row.get(i));
                }
                parent.put(a+")", child);
                a++;
            }
        
        } catch (JSONException ex) {
                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            FileWriter file = new FileWriter("output.json");
            file.write(parent.toString());
            file.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
