/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1aev5;

import java.net.URL;
import java.util.ResourceBundle;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Crea la conexion
        this.model = new Model();
        this.model.Conexion();
    }    

    @FXML
    private void Insert(ActionEvent event) {
        
        Libro l = new Libro();
        l.setTitulo(tituloText.getText());
        l.setAutor(autorText.getText());
        l.setPaginas(paginasText.getText());
        l.setAnyoPublicacion(añoPublicacionText.getText());
        l.setAnyoNacimiento(añoNacimientoText.getText());
        l.setEditorial(editorialText.getText());
        
        //Inserta el libro
        if(this.model.insertLibro(l) != 0){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Se ha insertado correctamente");
            a.showAndWait();
        } 
        
        l.setTitulo("");
        l.setAutor("");
        l.setPaginas("");
        l.setAnyoPublicacion("");
        l.setAnyoNacimiento("");
        l.setEditorial("");
        
    }
    
}
