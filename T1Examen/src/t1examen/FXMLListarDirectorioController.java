/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1examen;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLListarDirectorioController implements Initializable {

    @FXML
    private TextField pathText;
    @FXML
    private TextArea outputText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Listar(ActionEvent event) {
        
        outputText.setText("");
        
        if(!pathText.getText().isEmpty()){
            
            File f = new File(pathText.getText());
            
            if(f.exists()){
                
               File[] files = f.listFiles();
            
                for(File tempf : files){
                
                outputText.setText(outputText.getText() + tempf.getName() + "\n");
                outputText.setText(outputText.getText() + "\t" + tempf.getAbsolutePath() + "\n");
                
                if(tempf.isDirectory())
                    outputText.setText(outputText.getText() + "\t" + "Directorio" + "\n");
                else
                    outputText.setText(outputText.getText() + "\t" + "Fichero" + "\n");
                
                if(tempf.isHidden())
                    outputText.setText(outputText.getText() + "\t" + "Oculto" + "\n");
                else
                    outputText.setText(outputText.getText() + "\t" + "No oculto" + "\n");     
                } 
            }else{
              Alert a = new Alert(Alert.AlertType.ERROR, "Ese fichero no existe");
                a.showAndWait();  
            }     
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Ruta vacia");
            a.showAndWait();
        }
        
    }
    
}
