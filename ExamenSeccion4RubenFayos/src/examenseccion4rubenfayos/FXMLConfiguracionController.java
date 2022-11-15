/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examenseccion4rubenfayos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLConfiguracionController implements Initializable {

    @FXML
    private TextField ubicacionText;
    @FXML
    private TextField archivoText;
    @FXML
    private TextField servidorText;
    @FXML
    private TextField puertoText;
    @FXML
    private TextField nombreBddText;
    @FXML
    private TextField usuarioText;
    @FXML
    private TextField contraseñaText;
    @FXML
    private TextArea salidaText;
    
    private Model model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void crear(ActionEvent event) {
        
        this.model = new Model(servidorText.getText(), puertoText.getText(), nombreBddText.getText(), usuarioText.getText(), contraseñaText.getText());
        
        if(this.model.Conexion() == 1)
            salidaText.setText("Conexión satisfactoria");
        else{
            salidaText.setText("No se ha podido establecer conexion");
        }
    }
    
}
