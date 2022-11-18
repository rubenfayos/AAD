package examenseccion4rubenfayos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    
    private ArrayList<Ciudad> ciudades = new ArrayList<>();
    private Model model;
    private int posicion;
    private Ciudad c;
    private Boolean añadir = false;
    
    
    @FXML
    private Label posicionText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        this.model.Conexion();
        
        posicion = -1;
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
    private void siguiente(ActionEvent event) {
        
        this.ciudades.clear();
        this.ciudades = this.model.recorrerCiudades();
        
        if(añadir){
            añadirCiudad();
            añadir = false;
        }
        
        posicion++;
        
        posicionText.setText(String.valueOf(posicion));
        
        if(idText.getText().isEmpty() && this.c != null){
            
            this.model.delete(this.c.getId());
            posicion--;
            this.ciudades.clear();
            this.ciudades = this.model.recorrerCiudades();
            
        }
        
        //Coge la ciudad de la posicion
        if(posicion < this.ciudades.size()){
            if(posicion > 0)
                update();
            
            this.c = this.ciudades.get(posicion);
        }
        else{
            this.c = null;
            posicion = this.ciudades.size();
        }
        
        
        if(this.c != null){
        
            idText.setText(String.valueOf(c.getId()));
            ciudadText.setText(c.getNombre());
            codCiudadText.setText(c.getCodigo());
            distritoText.setText(c.getDistrito());
            poblacionText.setText(String.valueOf(c.getPoblacion()));
            
        }else{
            
            idText.setText("");
            ciudadText.setText("");
            codCiudadText.setText("");
            distritoText.setText("");
            poblacionText.setText("");
            añadir = true;
            
        }
        
                     
    }

    @FXML
    private void anterior(ActionEvent event) {
        
        this.ciudades.clear();
        this.ciudades = this.model.recorrerCiudades();
        
        posicion--;
        
        posicionText.setText(String.valueOf(posicion));
        
        //Coge la ciudad de la posicion
        if(idText.getText().isEmpty() && this.c != null){
            this.ciudades.remove(posicion+1);
            this.model.delete(this.c.getId());
            
        }
        
        
        
        //Coge la ciudad de la posicion
        if(posicion > this.ciudades.size()){
            posicion = this.ciudades.size();
           
        }
        
        this.c = this.ciudades.get(posicion);
        
        
        if(this.c != null){
        
            idText.setText(String.valueOf(c.getId()));
            ciudadText.setText(c.getNombre());
            codCiudadText.setText(c.getCodigo());
            distritoText.setText(c.getDistrito());
            poblacionText.setText(String.valueOf(c.getPoblacion()));
            
        }
        
        
        
    }
    
    private void añadirCiudad(){
        
        Ciudad nuevaCiudad = new Ciudad();
        nuevaCiudad.setId(Integer.parseInt(idText.getText()));
        nuevaCiudad.setNombre(ciudadText.getText());
        nuevaCiudad.setDistrito(distritoText.getText());
        nuevaCiudad.setCodigo(codCiudadText.getText());
        nuevaCiudad.setPoblacion(Integer.parseInt(poblacionText.getText()));
        
        this.model.insert(nuevaCiudad);
        this.ciudades.add(c);
        
        this.c = nuevaCiudad;
        
    }
    
    private void update(){
        
        Ciudad nuevaCiudad = new Ciudad();
        nuevaCiudad.setId(Integer.parseInt(idText.getText()));
        nuevaCiudad.setNombre(ciudadText.getText());
        nuevaCiudad.setDistrito(distritoText.getText());
        nuevaCiudad.setCodigo(codCiudadText.getText());
        nuevaCiudad.setPoblacion(Integer.parseInt(poblacionText.getText()));
        
        if(nuevaCiudad != this.c){
            this.model.update(nuevaCiudad);
        }
    }
    
}
