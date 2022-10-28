/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1aev4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 *
 * @author Rubén Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField rutaText;
    @FXML
    private TableColumn<Libro, String> tituloColumn;
    @FXML
    private TableColumn<Libro, String> autorColumn;
    @FXML
    private TableColumn<Libro, Integer> añoNacimientoColumn;
    @FXML
    private TableColumn<Libro, Integer> añoPublicacionColumn;
    @FXML
    private TableColumn<Libro, String> editorialColumn;
    @FXML
    private TableColumn<Libro, Integer> paginasColumn;
    
    private Model model;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        this.model = new Model();
        
        
    }    

    @FXML
    private void read(ActionEvent event) {
        
        if(!this.model.Conexion()){
            Alert conectionError = new Alert(Alert.AlertType.ERROR, "Error en la base de datos");
            conectionError.showAndWait();
        }
        
        if(this.model.leerCsv(rutaText.getText())){
            Alert conection = new Alert(Alert.AlertType.INFORMATION, "Se ha leido el fichero correctamente");
            conection.showAndWait();
        }else{
            Alert conectionError = new Alert(Alert.AlertType.ERROR, "No se ha podido leer el fichero");
            conectionError.showAndWait();
        }
        
    }
    
}
