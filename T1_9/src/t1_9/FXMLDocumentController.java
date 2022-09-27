/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button button;
    @FXML
    private TextField rutaText;
    @FXML
    private TextArea contenidoText;
    @FXML
    private TextField velocidadText;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, InterruptedException {
        
        try{
        
            File f = new File(rutaText.getText());
            FileReader fReader  = new FileReader(f);

            int fr;

            while((fr = fReader.read()) != -1){

                System.out.println(Character.toString((char)fr));
                contenidoText.setText(contenidoText.getText() + Character.toString((char)fr));
                
                

            }
          
        }   catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
