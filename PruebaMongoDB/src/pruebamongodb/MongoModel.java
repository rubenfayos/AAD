/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebamongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Ruben Fayos
 */
public class MongoModel {
    
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> coleccion;
        
    public void Conexion(){
    
        this.mongoClient = new MongoClient("localhost", 27017);
        this.database = mongoClient.getDatabase("Canciones");
        this.coleccion = database.getCollection("canciones");
           
    }
    
    public void Insert(Cancion c){
        
        Document doc = new Document();
        doc.append("SongName", "Ruben");
        doc.append("Singer", "Cantante");
        this.coleccion.insertOne(doc);
        
        this.mongoClient.close();
        
    }
    
}
