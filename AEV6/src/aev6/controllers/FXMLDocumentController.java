/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package aev6.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> tituloColumn;
    @FXML
    private TableColumn<?, ?> autorColumn;
    @FXML
    private TableColumn<?, ?> añoNacimientoColumn;
    @FXML
    private TableColumn<?, ?> añoPublicacionColumn;
    @FXML
    private TableColumn<?, ?> editorialColumn;
    @FXML
    private TableColumn<?, ?> paginasColumn;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
