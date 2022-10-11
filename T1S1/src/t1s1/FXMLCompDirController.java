/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author DAM 2
 */
public class FXMLCompDirController implements Initializable {
    
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField dirText;
    @FXML
    private TextArea dirInfo;
    @FXML
    private AnchorPane scene;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        String dir = dirText.getText();
        
        File f = new File(dir);
        
        if(f.exists()){
            
            Alert ac = new Alert(Alert.AlertType.INFORMATION, "Ese directorio existe");
            
            ac.setHeaderText("Confirmación");
            
            ac.showAndWait();
            
            dirInfo.setText("Name: " + f.getName() + "\n\r");
            dirInfo.setText(dirInfo.getText() + "Parent directory: " + f.getParent() + "\n\r");
            dirInfo.setText(dirInfo.getText() + "Size: " + f.length() + "\n\r");
            dirInfo.setText(dirInfo.getText() + "Absolute Path: " + f.getPath() + "\n\r");
            dirInfo.setText(dirInfo.getText() + "Is directory: " + f.isDirectory() + "\n\r");
            dirInfo.setText(dirInfo.getText() + "Is hidden: " + f.isHidden() + "\n\r");
            
        }else{
            
            Alert ae = new Alert(Alert.AlertType.ERROR, "Ese directorio no existe");
            
            ae.showAndWait();
            
        }
              
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Volver(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        this.scene.getChildren().setAll(pane);
        
    }
    
}
