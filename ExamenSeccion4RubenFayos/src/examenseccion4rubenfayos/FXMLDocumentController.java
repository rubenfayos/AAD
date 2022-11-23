package examenseccion4rubenfayos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ruben Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button button1;
    @FXML
    private TextField idText;
    @FXML
    private TextField ciudadText;
    @FXML
    private TextField codCiudadText;
    @FXML
    private TextField distritoText;
    @FXML
    private TextField poblacionText;

    private Model model;
    private int posicion;
    private Ciudad c;
    private Boolean añadir = false;
    
    private ResultSet ciudades;
    
    
    @FXML
    private Label posicionText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        this.model.Conexion();
        
        this.ciudades = this.model.recorrerCiudades();
        
        
        //Muestra la primera ciudad
        try {
            ciudades.next();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarCiudad();
        
        posicion = 1;
    }    

    @FXML
    private void configuracion(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLConfiguracion.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void consultasBruto(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLConsultasBruto.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void siguiente(ActionEvent event) throws SQLException {
  
        posicion++;
        
        if(añadir){
            
            //Comprueba si añade la ciudad
            if(añadirCiudad()){
                
            }else{
                //Si no se añade se resta la posicion
                posicion--;
            }
            
            //Como el siguiente campo sigue estando vacio lo vacía
            idText.setText("");
            ciudadText.setText("");
            codCiudadText.setText("");
            distritoText.setText("");
            poblacionText.setText("");
            
        }else{
        
            if(delete()){
                //resta a la posicion para que el objeto se muestre
                posicion--;
                goToRow();
                mostrarCiudad();
            }else if(update()){
                goToRow();
                mostrarCiudad();

            }else if(ciudades.next()){

                mostrarCiudad();

            }else{

                idText.setText("");
                ciudadText.setText("");
                codCiudadText.setText("");
                distritoText.setText("");
                poblacionText.setText("");

                añadir = true;

            }
            
        }
        
        posicionText.setText(String.valueOf(posicion));
        
                     
    }

    @FXML
    private void anterior(ActionEvent event) throws SQLException {
        
        posicion--;
        
        if(añadir){
            
            añadir = false;
            //Comprueba si se añade la ciudad para moverse
            if(añadirCiudad()){
                goToRow();
                mostrarCiudad();
                
            //Si no se añade la ciudad vuelve a la anterior sin añadirla
            }else{
               ciudades.previous(); 
               mostrarCiudad();
            }
    
        }else{
            //Comprueba si borra
            if(delete()){
                goToRow();
                mostrarCiudad();

            //Comprueba si actualiza
            }else if(update()){
                goToRow();
                mostrarCiudad();

            //Comprueba si la posicion es menor a 1
            }else if(posicion < 1){
                posicion++;
            }else{
                
                //Si no hay nada muestra
                if(ciudades.previous()){

                mostrarCiudad();

            }else{

                idText.setText("");
                ciudadText.setText("");
                codCiudadText.setText("");
                distritoText.setText("");
                poblacionText.setText("");

            }
                
            }
            
        }
        
        posicionText.setText(String.valueOf(posicion));
               
    }
    
    public void goToRow() throws SQLException{
        
        //Se mueve a la posicion
       for(int i = 0; i < posicion; i++){
           ciudades.next();
       }
       
    }
    
    private boolean update(){
        
        Ciudad nuevaCiudad = new Ciudad();
        nuevaCiudad.setId(Integer.parseInt(idText.getText()));
        nuevaCiudad.setNombre(ciudadText.getText());
        nuevaCiudad.setDistrito(distritoText.getText());
        nuevaCiudad.setCodigo(codCiudadText.getText());
        nuevaCiudad.setPoblacion(Integer.parseInt(poblacionText.getText()));
        
        //Compara los 2 objetos y hace el update si son diferentes  
        if(!nuevaCiudad.equals(this.c)){
            if(this.model.update(nuevaCiudad)){
                this.ciudades = this.model.recorrerCiudades();
                return true;
            }
        }
        
        return false;
        
    }
    
    private boolean delete(){
        
        if(idText.getText().isEmpty()){
            this.model.delete(c.getId());
            ciudades = this.model.recorrerCiudades();
            return true;
        }
        
        return false;     
    }
    
    private void mostrarCiudad(){
        
        this.c = new Ciudad();

        try {

            this.c.setId(ciudades.getInt("ID"));
            this.c.setNombre(ciudades.getString("Name"));
            this.c.setCodigo(ciudades.getString("CountryCode"));
            this.c.setDistrito(ciudades.getString("District"));
            this.c.setPoblacion(ciudades.getInt("Population"));

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        idText.setText(String.valueOf(c.getId()));
        ciudadText.setText(c.getNombre());
        codCiudadText.setText(c.getCodigo());
        distritoText.setText(c.getDistrito());
        poblacionText.setText(String.valueOf(c.getPoblacion()));
        
    }
    
    private boolean añadirCiudad(){
        
        if(!idText.getText().isEmpty() && !ciudadText.getText().isEmpty() && !distritoText.getText().isEmpty() && !codCiudadText.getText().isEmpty() && !poblacionText.getText().isEmpty()){
        
            Ciudad nuevaCiudad = new Ciudad();
            nuevaCiudad.setId(Integer.parseInt(idText.getText()));
            nuevaCiudad.setNombre(ciudadText.getText());
            nuevaCiudad.setDistrito(distritoText.getText());
            nuevaCiudad.setCodigo(codCiudadText.getText());
            nuevaCiudad.setPoblacion(Integer.parseInt(poblacionText.getText()));

            if(this.model.insert(nuevaCiudad)){
                ciudades = this.model.recorrerCiudades();
                return true;
            }
        
        }
        
        return false;
    }
    
}
