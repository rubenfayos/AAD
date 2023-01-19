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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

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
        
        table.setEditable(true); 
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        
        //permite que la celda se pueda editar
        tituloColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tituloColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Libro, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Libro, String> event) {
                Libro l = event.getRowValue();
                l.setTitulo(event.getNewValue());
            }
            
        });
        
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        
        //permite que la celda se pueda editar
        autorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        autorColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Libro, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Libro, String> event) {
                Libro l = event.getRowValue();
                l.setAutor(event.getNewValue());
            }
            
        });
        
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
        stage.showAndWait();
        
        Select(new ActionEvent());
    }

    @FXML
    private void Delete(ActionEvent event) {
        
        Libro l = getLibroSeleccionado();
        mongoModel.Delete(l.getId());
        
        Alert confAlert = new Alert(Alert.AlertType.CONFIRMATION, "Libro borrado correctamente");
        confAlert.showAndWait();
        
        Select(new ActionEvent());
        
    }

    @FXML
    private void Modificar(ActionEvent event) {
        
        Libro l = getLibroSeleccionado();
        l.setEditorial(editorialText.getText());
        l.setAñoNacimiento(añoNacimientoText.getText());
        l.setAñoPublicacion(añoPublicacionText.getText());
        l.setPaginas(paginasText.getText());
        mongoModel.Actualizar(l);    
        
        Alert confAlert = new Alert(Alert.AlertType.CONFIRMATION, "Libro modificado correctamente");
        confAlert.showAndWait();
        
        Select(new ActionEvent());
        
    }

    @FXML
    private void Select(ActionEvent event) {
        
        table.setItems(mongoModel.Select());
        LimpiarCampos();
             
    }
    
    public void Info(){
        
        Libro l = getLibroSeleccionado();
        
        editorialText.setText(l.getEditorial());
        paginasText.setText(l.getPaginas());
        añoNacimientoText.setText(l.getAñoNacimiento());
        añoPublicacionText.setText(l.getAñoPublicacion());

    }
    
    public void LimpiarCampos(){
        
        editorialText.setText("");
        paginasText.setText("");
        añoNacimientoText.setText("");
        añoPublicacionText.setText("");
        
    }

    public Libro getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(Libro libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

}
