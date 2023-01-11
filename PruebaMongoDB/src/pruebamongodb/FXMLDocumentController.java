package pruebamongodb;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pruebamongodb.MongoModel;

/**
 *
 * @author Ruben Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    private MongoModel model;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        this.model.Insert();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.model = new MongoModel();
        this.model.Conexion();
    }    
    
}
