/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package rubenfayoshibernate.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rubenfayoshibernate.Objetos.Pelicula;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLGestionPeliculasController implements Initializable {

    @FXML
    private TableView<Pelicula> tablaPeliculas;
    @FXML
    private TableColumn<Pelicula, Integer> idColumn;
    @FXML
    private TableColumn<Pelicula, String> generoColumn;
    @FXML
    private TableColumn<Pelicula, String> nombreColumn;
    @FXML
    private TableColumn<Pelicula, String> detalleColumn;
    @FXML
    private TableColumn<Pelicula, String> protagonistaColumn;
    @FXML
    private TableColumn<Pelicula, Double> precioColumn;
    @FXML
    private TableColumn<Pelicula, String> estadoColumn;
    @FXML
    private TableView<?> tablaAlquileres;
    
    private ModelPeliculas mp;
    private ModelAlquiler ma;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mp = new ModelPeliculas();
        ma = new ModelAlquiler();
        
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        detalleColumn.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        protagonistaColumn.setCellValueFactory(new PropertyValueFactory<>("protagonista"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }    

    @FXML
    private void todasPeliculas(ActionEvent event) {
        
        tablaPeliculas.setItems(mp.ListarTodo());
        
    }

    @FXML
    private void VerAlquileres(ActionEvent event) {
    }

    @FXML
    private void PeliculasLibres(ActionEvent event) {
    }
    
}
