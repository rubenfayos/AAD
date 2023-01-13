/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebamongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

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
        doc.append("_id", new ObjectId());
        doc.append("SongName", "Vdatw");
        doc.append("Singer", "Caadtfhfnte");
        
        this.coleccion.insertOne(doc);
        
    }
    
    public void Select() throws JSONException{
        
        MongoCursor<Document> cursor = this.coleccion.find().iterator();
        
        try {
            
            while(cursor.hasNext()){
                JSONObject obj = new JSONObject(cursor.next().toJson());
                System.out.println(obj.getString("SongName"));
            }
            
        } finally {
            cursor.close();
        }
        
        
    }
    
    public void Update(Cancion c){
        
        
    }
    
    public void Delete(){
        
        
        
    }
     
}
