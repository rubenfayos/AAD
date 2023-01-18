/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package aev6.controllers;

import aev6.Libro;
import aev6.MongoModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;

/**
 *
 * @author Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TableView<Libro> table;
    @FXML
    private TableColumn<Libro, String> autorColumn;
    @FXML
    private TableColumn<Libro, String> idColumn;
    @FXML
    private TableColumn<Libro, String> tituloColumn;
    @FXML
    private TextField añoPublicacionText;
    @FXML
    private TextField añoNacimientoText;
    @FXML
    private TextField paginasText;
    
    private MongoModel mongoModel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Inicialización model 
        mongoModel = new MongoModel();
        mongoModel.Conexion();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        
    }    

    @FXML
    private void Insert(ActionEvent event) {
    }

    @FXML
    private void Delete(ActionEvent event) {
    }

    @FXML
    private void Modificar(ActionEvent event) {
    }

    @FXML
    private void Select(ActionEvent event) {
        
        
            
            table.setItems(mongoModel.Select());
             
    }
    
}
