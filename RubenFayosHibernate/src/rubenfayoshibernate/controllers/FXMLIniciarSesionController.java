/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package rubenfayoshibernate.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rubenfayoshibernate.Objetos.Cliente;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLIniciarSesionController implements Initializable {

    @FXML
    private TextField usuarioText;

    /**
     * Initializes the controller class.
     */
    
    private ModelCliente m;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        m = new ModelCliente();
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        
        Cliente c = m.ListarSelect(Integer.parseInt(usuarioText.getText()));
        if(c != null){
           Parent root = FXMLLoader.load(getClass().getResource("FXMLAlquilarPeliculas.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show(); 
        }
        
    }

    @FXML
    private void crearUsuario(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCrearUsuario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }
    
}
