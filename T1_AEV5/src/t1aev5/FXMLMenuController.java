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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private Pane contentPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/t1aev5/Views/FXMLSelect.fxml"));
            this.contentPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void switchInsert(ActionEvent event) {
    }

    @FXML
    private void switchSelect(ActionEvent event) {
    }

    @FXML
    private void switchUpdate(ActionEvent event) {
    }

    @FXML
    private void switchDelete(ActionEvent event) {
    }
    
}
