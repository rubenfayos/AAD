/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package aev6.controllers;

import aev6.Libro;
import aev6.MongoModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    private TableColumn<Libro, String> tituloColumn;
    @FXML
    private TextField añoPublicacionText;
    @FXML
    private TextField añoNacimientoText;
    @FXML
    private TextField paginasText;
    
    private MongoModel mongoModel;
    @FXML
    private TextField editorialText;
    
    private Libro libroSeleccionado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Inicialización model 
        mongoModel = new MongoModel();
        mongoModel.Conexion();
        
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        
        //Listener libro seleccionado
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
    private void Insert(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/aev6/views/FXMLInsert.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void Delete(ActionEvent event) {
    }

    @FXML
    private void Modificar(ActionEvent event) {
        
        Libro l = new Libro();
        l = getLibroSeleccionado();
        
    }

    @FXML
    private void Select(ActionEvent event) {
        
            table.setItems(mongoModel.Select());
             
    }
    
    public void Info(){
        
        Libro l = getLibroSeleccionado();
        
        editorialText.setText(l.getEditorial());
        paginasText.setText(l.getPaginas());
        añoNacimientoText.setText(l.getAñoNacimiento());
        añoPublicacionText.setText(l.getAñoPublicacion());
    }

    public Libro getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(Libro libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

}
