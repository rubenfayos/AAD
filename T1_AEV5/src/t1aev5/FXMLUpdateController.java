/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1aev5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLUpdateController implements Initializable {

    @FXML
    private TextField tituloText;
    @FXML
    private TextField autorText;
    @FXML
    private TextField añoPublicacionText;
    @FXML
    private TextField editorialText;
    private ComboBox<Integer> idComboBox;
    @FXML
    private TextField añoNacimientoText;
    
    private Model model;
    private Libro libro;
    @FXML
    private TextField paginasText;
    @FXML
    private ComboBox<String> tituloComboBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        this.model.Conexion();
        
        //Crea un observable list para elegir el libro a cambiar
        ObservableList<String> titulos = FXCollections.observableArrayList();
        for(Libro l : this.model.listarLibros()){
            titulos.add(l.getTitulo());
        }
        
        tituloComboBox.setItems(titulos);
       
        //añade el listener para el libro elegido
        tituloComboBox.valueProperty().addListener((obs, oldItem, newItem) -> {
            
            if (newItem != null) {
                //Lista el libro a partir del titulo y actualiza los campos
               this.libro = this.model.listarLibro(newItem);
               actualizarCampos();
            }
        });
        
        // Obliga al input a ser solo numerico
        paginasText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    paginasText.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        añoNacimientoText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    añoNacimientoText.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        añoPublicacionText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    añoPublicacionText.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }    
    
    private void actualizarCampos(){
        
        tituloText.setText(this.libro.getTitulo());
        editorialText.setText(this.libro.getEditorial());
        añoNacimientoText.setText(this.libro.getAnyoNacimiento());
        añoPublicacionText.setText(this.libro.getAnyoPublicacion());
        autorText.setText(this.libro.getAutor());
        paginasText.setText(this.libro.getPaginas());
        
    }

    @FXML
    private void Update(ActionEvent event) {
        
        if(tituloText.getText().isEmpty() || editorialText.getText().isEmpty() || añoNacimientoText.getText().isEmpty() || añoPublicacionText.getText().isEmpty() || autorText.getText().isEmpty() || paginasText.getText().isEmpty()){
            
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Faltan datos por introducir");
            errorAlert.showAndWait();
            
        }else{
            
            libro.setTitulo(tituloText.getText());
            libro.setEditorial(editorialText.getText());
            libro.setAnyoNacimiento(añoNacimientoText.getText());
            libro.setAnyoPublicacion(añoPublicacionText.getText());
            libro.setPaginas(paginasText.getText());
            libro.setAutor(autorText.getText());

            this.model.updateLibro(libro);
            
            Alert confAlert = new Alert(Alert.AlertType.INFORMATION, "Libro actualizado correctamente");
            confAlert.showAndWait();
        }
           
    }
    
}
