/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package rubenfayoshibernate.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import rubenfayoshibernate.Objetos.Cliente;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLCrearUsuarioController implements Initializable {

    @FXML
    private TextField usuarioText;
    @FXML
    private TextField apellidosText;
    @FXML
    private TextField telefonoText;
    @FXML
    private TextField direccionText;
    @FXML
    private TextField dniText;
    @FXML
    private TextField estadoText;
    
    private ModelCliente mc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mc = new ModelCliente();
    }    

    @FXML
    private void AñadirUsuario(ActionEvent event) {
        
        Cliente c = new Cliente();
        c.setNombre(usuarioText.getText());
        c.setApellido(apellidosText.getText());
        c.setTelefono(telefonoText.getText());
        c.setDireccion(direccionText.getText());
        c.setDni(dniText.getText());
        c.setEstado(estadoText.getText());
        this.mc.Crear(c);
        
    }
    
}
