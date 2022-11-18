/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1aev5;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLSelectController implements Initializable {

    @FXML
    private AnchorPane scene;
    @FXML
    private TableView<Libro> table;
    @FXML
    private TableColumn<Libro, String> tituloColumn;
    @FXML
    private TableColumn<Libro, String> autorColumn;
    @FXML
    private TableColumn<Libro, String> añoNacimientoColumn;
    @FXML
    private TableColumn<Libro, String> añoPublicacionColumn;
    @FXML
    private TableColumn<Libro, String> editorialColumn;
    @FXML
    private TableColumn<Libro, String> paginasColumn;
    
    private Model model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        if(!this.model.Conexion()){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error con la base de datos");
            error.showAndWait();
        }else{
        
            tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
            añoNacimientoColumn.setCellValueFactory(new PropertyValueFactory<>("anyoNacimiento"));
            añoPublicacionColumn.setCellValueFactory(new PropertyValueFactory<>("anyoPublicacion"));
            editorialColumn.setCellValueFactory(new PropertyValueFactory<>("editorial"));
            paginasColumn.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        
            refrescar(new ActionEvent());
        
        }
        
         
    }    

    @FXML
    private void refrescar(ActionEvent event) {
        
        List libros = new ArrayList();
        libros = this.model.listarLibros();
        
        //Crea el observablelist a partir de la lista
        ObservableList<Libro> libroOL = FXCollections.observableArrayList(libros);
        
        table.setItems(libroOL);
    }
    
}
