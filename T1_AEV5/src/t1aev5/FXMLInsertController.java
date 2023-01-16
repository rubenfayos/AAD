/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1aev5;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLInsertController implements Initializable {

    @FXML
    private TextField tituloText;
    @FXML
    private TextField autorText;
    @FXML
    private TextField paginasText;
    @FXML
    private TextField añoPublicacionText;
    @FXML
    private TextField añoNacimientoText;
    @FXML
    private TextField editorialText;
    
    private Model model;
    @FXML
    private AnchorPane contentPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Crea la conexion
        this.model = new Model();
        this.model.Conexion();
        
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

    @FXML
    private void Insert(ActionEvent event) {
        
        if(tituloText.getText().isEmpty() || editorialText.getText().isEmpty() || añoNacimientoText.getText().isEmpty() || añoPublicacionText.getText().isEmpty() || autorText.getText().isEmpty() || paginasText.getText().isEmpty()){
            
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Faltan datos por introducir");
            errorAlert.showAndWait();
            
        }else{
        
            Libro l = new Libro();
            l.setTitulo(tituloText.getText());
            l.setAutor(autorText.getText());
            l.setPaginas(paginasText.getText());
            l.setAnyoPublicacion(añoPublicacionText.getText());
            l.setAnyoNacimiento(añoNacimientoText.getText());
            l.setEditorial(editorialText.getText());
            
            this.model.insertLibro(l);


            Alert a = new Alert(Alert.AlertType.INFORMATION, "Se ha insertado correctamente");
            a.showAndWait();

            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/t1aev5/Views/FXMLSelect.fxml"));
                this.contentPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
}
