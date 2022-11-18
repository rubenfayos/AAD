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
    private ComboBox<Libro> idComboBox;
    @FXML
    private TextField añoNacimientoText;
    
    private Model model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        this.model.Conexion();
        for(Libro l : this.model.listarLibros()){
            idComboBox.getItems().add(l);
        }
        
        
        
        idComboBox.valueProperty().addListener((obs, oldItem, newItem) -> {
            
            if (newItem != null) {
               System.out.print("a");
            }
        });
                
                
        
    }    

    @FXML
    private void Update(ActionEvent event) {
    }
    
}
