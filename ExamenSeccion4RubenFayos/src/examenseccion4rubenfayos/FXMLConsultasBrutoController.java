/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examenseccion4rubenfayos;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */



public class FXMLConsultasBrutoController implements Initializable {

    @FXML
    private TextArea consultaText;
    private Model model;
    @FXML
    private TextArea salidaConsultaText;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        this.model = new Model();
        
    }    

    @FXML
    private void consultar(ActionEvent event) {
        
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        
        salidaConsultaText.setText("");
        
        ciudades = this.model.consultaBruto(consultaText.getText());
        
        if(ciudades.size() == 0){
            
            for(Ciudad c : ciudades){
                salidaConsultaText.setText(salidaConsultaText.getText() + "ID: " + c.getId() + "\n\r");
                salidaConsultaText.setText(salidaConsultaText.getText() + "Nombre: " + c.getId() + "\n\r");
                salidaConsultaText.setText(salidaConsultaText.getText() + "Codigo: " + c.getId() + "\n\r");
                salidaConsultaText.setText(salidaConsultaText.getText() + "Distrito: " + c.getId() + "\n\r");
                salidaConsultaText.setText(salidaConsultaText.getText() + "Poblacion: " + c.getId() + "\n\r");
                salidaConsultaText.setText(salidaConsultaText.getText() + "-------------------------------\n\r" );
            }
            
        }else{
            
            salidaConsultaText.setText("Ha ocurrido algún error en la consulta");
            
        }
        
        
        
    }
    
}
