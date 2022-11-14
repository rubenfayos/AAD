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
    private TextField nombreText;
    @FXML
    private TextField edadText;
    @FXML
    private TextField alturaText;
    @FXML
    private TextField pesoText;
    @FXML
    private ComboBox<?> sexoComboBox;
    @FXML
    private TextField idText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Update(ActionEvent event) {
    }
    
}
