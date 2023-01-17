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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLAlquilarPeliculasController implements Initializable {

    @FXML
    private TableView<?> tablaPeliculas;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> generoColumn;
    @FXML
    private TableColumn<?, ?> nombreColumn;
    @FXML
    private TableColumn<?, ?> detalleColumn;
    @FXML
    private TableColumn<?, ?> protagonistaColumn;
    @FXML
    private TableColumn<?, ?> precioColumn;
    @FXML
    private TableColumn<?, ?> estadoColumn;
    @FXML
    private TableView<?> tablaPeliculas1;
    @FXML
    private TableColumn<?, ?> idColumn1;
    @FXML
    private TableColumn<?, ?> generoColumn1;
    @FXML
    private TableColumn<?, ?> nombreColumn1;
    @FXML
    private TableColumn<?, ?> detalleColumn1;
    @FXML
    private TableColumn<?, ?> protagonistaColumn1;
    @FXML
    private TableColumn<?, ?> precioColumn1;
    @FXML
    private TableColumn<?, ?> estadoColumn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void listarPeliculas(ActionEvent event) {
    }

    @FXML
    private void listarAlquiladas(ActionEvent event) {
    }
    
}
