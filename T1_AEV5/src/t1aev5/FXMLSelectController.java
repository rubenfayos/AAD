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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        this.model.Conexion();
        
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        añoNacimientoColumn.setCellValueFactory(new PropertyValueFactory<>("añoNacimiento"));
        añoPublicacionColumn.setCellValueFactory(new PropertyValueFactory<>("añoPublicacion"));
        editorialColumn.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        paginasColumn.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        
        List libros = new ArrayList();
        
        ObservableList<Libro> libross = FXCollections.observableArrayList();
        libross = (ObservableList<Libro>) libros;
        
        table.setItems(libross);
        
    }    

    @FXML
    private void refrescar(ActionEvent event) {
    }
    
}
