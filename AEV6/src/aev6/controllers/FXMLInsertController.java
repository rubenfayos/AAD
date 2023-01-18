/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aev6.controllers;

import aev6.Libro;
import aev6.MongoModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Fayos
 */
public class FXMLInsertController implements Initializable {

    @FXML
    private TextField tituloText;
    @FXML
    private TextField autorText;
    @FXML
    private TextField añoNacimientoText;
    @FXML
    private TextField añoPublicacionText;
    @FXML
    private TextField EditorialText;
    @FXML
    private TextField totalPaginasText;
    
    private MongoModel mongoModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mongoModel = new MongoModel();
        mongoModel.Conexion();
    }    

    @FXML
    private void Insertar(ActionEvent event) {
        
        Libro l = new Libro();
        l.setAutor(autorText.getText());
        l.setTitulo(tituloText.getText());
        l.setAñoNacimiento(añoNacimientoText.getText());
        l.setAñoPublicacion(añoPublicacionText.getText());
        l.setEditorial(EditorialText.getText());
        l.setPaginas(totalPaginasText.getText());
        
        mongoModel.Insertar(l);
        
    }
    
}
