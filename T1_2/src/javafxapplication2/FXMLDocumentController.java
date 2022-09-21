/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author DAM 2
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField dirText;
    @FXML
    private TextArea dirInfo;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        String dir = dirText.getText();
        
        File f = new File(dir);
        
        if(f.exists()){
            
            dirInfo.setText("Name: " + f.getName() + "\n\r");
            dirInfo.setText(dirInfo.getText() + "Parent directory: " + f.getParent() + "\n\r");
            dirInfo.setText(dirInfo.getText() + "Size: " + f.length() + "\n\r");
            dirInfo.setText(dirInfo.getText() + "Absolute Path: " + f.getPath() + "\n\r");
            dirInfo.setText(dirInfo.getText() + "Is directory: " + f.isDirectory() + "\n\r");
            
        }else{
            
            Alert a = new Alert(Alert.AlertType.ERROR, "Ese directorio no existe");
            
            a.showAndWait();
            
           
            
        }
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
