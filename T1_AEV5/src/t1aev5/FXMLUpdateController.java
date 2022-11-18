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
    @FXML
    private ComboBox<Integer> idComboBox;
    @FXML
    private TextField añoNacimientoText;
    
    private Model model;
    private Libro libro;
    @FXML
    private TextField paginasText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        this.model.Conexion();
        for(Libro l : this.model.listarLibros()){
            idComboBox.getItems().add(l.getId());
        }
       
        idComboBox.valueProperty().addListener((obs, oldItem, newItem) -> {
            
            if (newItem != null) {
               System.out.print("a");
               this.libro = this.model.listarLibro(newItem);
               actualizarCampos();
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
        
        libro.setTitulo(tituloText.getText());
        libro.setEditorial(editorialText.getText());
        libro.setAnyoNacimiento(añoNacimientoText.getText());
        libro.setAnyoPublicacion(añoPublicacionText.getText());
        libro.setPaginas(paginasText.getText());
        libro.setAutor(autorText.getText());
        
        this.model.updateLibro(libro);
        
    }
    
}
