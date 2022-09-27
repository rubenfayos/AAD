/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField rutaText;
    @FXML
    private TextArea leerText;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException {
        
        File f = new File(rutaText.getText());
        FileReader fReader = new FileReader(f);
        
        
        int contador = 0;
        int fr;
        
        while((fr = fReader.read()) != -1){
            
            if(contador == 50){
                
                leerText.setText(leerText.getText() + "Introduce una tecla para continuar");
                
                String leer = leerText.getText();
                
                while( leer.equals(leerText.getText())){
                
                    
                
                }
                
            }

            System.out.println(Character.toString((char)fr));
            leerText.setText(leerText.getText() + Character.toString((char)fr));
            contador++;
                
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
