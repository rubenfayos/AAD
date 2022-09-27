/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1_15;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
 * @author DAM 2
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField rutaText;
    @FXML
    private TextArea escribirText;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void escribir(ActionEvent event) {
        
        String text = escribirText.getText();
        
        if(!text.isEmpty()){
            
            File f = new File(rutaText.getText());
        
            //Fecha actual
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date date = new Date();   
            String d = formatter.format(date);

            if(f.exists()){

                try {

                FileWriter fWriter = new FileWriter(f, true);
                fWriter.write(text + " " + d);
                fWriter.flush();
                fWriter.close();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
        }
        
        
             
    }
    
}
