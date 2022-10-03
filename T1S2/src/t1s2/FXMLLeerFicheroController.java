/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s2;

import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author Fayos
 */
public class FXMLLeerFicheroController implements Initializable {
    
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
        
        contenidoText.setText("");
        
        File f = new File(rutaText.getText());
        
        String velText = velocidadText.getText();
        
        
        //Definimos la velocidad de lectura
        int velocidadLectura = 1;
        if(!velText.isEmpty() && Integer.parseInt(velText) > 0){
            velocidadLectura = Integer.parseInt(velText);
        }
        
        long fileLength = f.length();
        
         
        int duration = (int) fileLength;
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(velocidadLectura), new EventHandler<ActionEvent>() {
            
            //Crea el FileReader
            FileReader fReader  = new FileReader(f);
            int fr = fReader.read();
            
            @Override
            public void handle(ActionEvent event) {
               
                try {
                    
                //Imprime el texto
                contenidoText.setText(contenidoText.getText() + Character.toString((char)fr));
                fr = fReader.read();
                
               
                
                } catch (IOException ex) {
                    Logger.getLogger(FXMLLeerFicheroController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
        }));
        
        //Selecciona el tiempo total
        timeline.setCycleCount((int) duration);
        timeline.playFromStart();
        
        //fReader.close();
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
