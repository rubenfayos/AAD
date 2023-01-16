/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1aev5;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLDeleteController implements Initializable {

    @FXML
    private TableView<Libro> table;
    @FXML
    private TableColumn<Libro, Integer> idTableRow;
    @FXML
    private TableColumn<Libro, Integer> tituloTableRow;
    
    private Model model;
    
    private Libro libroSeleccionado;
    @FXML
    private Label infoText;
    @FXML
    private AnchorPane contentPane;

    public Libro getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(Libro libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        this.model.Conexion();
        
        idTableRow.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloTableRow.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        
        Select();
        
        //Listener para el libro seleccionado
        
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {

                Libro l = new Libro();

                //Si el item seleccionado != null
                if(table.getSelectionModel().getSelectedItem() != null) {    

                    setLibroSeleccionado(table.getSelectionModel().getSelectedItem());
                    Info();
                }

            }
        
        });
        
    }    

    @FXML
    private void Delete(ActionEvent event) {
        
        this.model.deleteLibro(libroSeleccionado);
        
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Libro eliminado correctamente");
        a.showAndWait();

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/t1aev5/Views/FXMLDelete.fxml"));
            this.contentPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void Select(){
        
        List libros = new ArrayList();
        libros = this.model.listarLibros();
        
        //Crea el observablelist a partir de la lista
        ObservableList<Libro> librosOL = FXCollections.observableArrayList(libros);
        
        table.setItems(librosOL);
        
    }
    
    private void Info(){
        
        infoText.setText("id: " + libroSeleccionado.getId() + "\n\r" + 
                        "Titulo: " + libroSeleccionado.getTitulo() + "\n\r" +
                        "Autor: " + libroSeleccionado.getAutor() + "\n\r" +
                        "Año nacimiento: " + libroSeleccionado.getAnyoNacimiento() + "\n\r" +
                        "Año publicación: " + libroSeleccionado.getAnyoPublicacion() + "\n\r" +
                        "Páginas: " + libroSeleccionado.getPaginas() + "\n\r");
        
    }
    
}
