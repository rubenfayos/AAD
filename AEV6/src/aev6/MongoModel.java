/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aev6;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fayos
 */
public class MongoModel {
    
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> coleccion;
    
    public void Conexion(){
        this.mongoClient = new MongoClient("localhost", 27017);
        this.database = mongoClient.getDatabase("Libros");
        this.coleccion = database.getCollection("libros");
    }
    
    public ObservableList<Libro> Select(){
        
        MongoCursor<Document> cursor = this.coleccion.find().iterator();
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        
        try {
            
            while(cursor.hasNext()){
                
                Libro l = new Libro();
                
                JSONObject obj = new JSONObject(cursor.next().toJson());
                //Coge el objeto a partir de la id
                JSONObject idObj = (JSONObject) obj.get("_id");
                l.setId(idObj.getString("$oid"));
                l.setAutor(obj.getString("Autor"));
                l.setTitulo(obj.getString("Titol"));
                l.setAñoNacimiento(obj.getString("Any_naixement"));
                l.setAñoPublicacion(obj.getString("Any_publicacio"));
                l.setEditorial(obj.getString("Editorial"));
                l.setPaginas(obj.getString("Nombre_pagines"));
                
                libros.add(l);
            }
            
            cursor.close();
            return libros;
            
        } catch (JSONException ex) {
            Logger.getLogger(MongoModel.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return null;
  
    }
    
    public void Insertar(Libro l){
        
        Document doc = new Document();
        doc.append("Titol", l.getTitulo());
        doc.append("Autor", l.getAutor());
        doc.append("Any_naixement", l.getAñoNacimiento());
        doc.append("Any_publicacio", l.getAñoPublicacion());
        doc.append("Editorial", l.getEditorial());
        doc.append("Nombre_pagines", l.getPaginas());
        
        this.coleccion.insertOne(doc);
        
    }
    
    public void Actualizar(Libro l){
        
        Bson query = eq("_id", l.getId());
        this.coleccion.updateOne(query, new Document("$set", query));
    }
    
}
