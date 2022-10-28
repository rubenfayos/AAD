/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1s4;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rubén Fayos
 */
public class FXMLDeleteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField idText;
    Model model;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        this.model.Conexion();
    }    

    @FXML
    private void Delete(ActionEvent event) {
        
        if(idText.getText().isEmpty()){
            
        }else{
            
            //Confirmación
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Seguro que quieres eliminar la persona?");
            
            Optional<ButtonType> action = confirmationAlert.showAndWait();
            
            if(action.get() == ButtonType.OK){
                int id = Integer.parseInt(idText.getText());
                //Elimina la persona
                if(this.model.Delete(id)>0){
                    Alert conf = new Alert(Alert.AlertType.CONFIRMATION, "Persona eliminada correctamente");
                    conf.showAndWait();
                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR, "La persona no se encuentra");
                    error.showAndWait();
                }   
            } 
        }
        
        
        
    }
    
}
