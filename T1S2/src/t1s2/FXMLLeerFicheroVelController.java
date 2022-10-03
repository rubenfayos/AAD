/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author Fayos
 */
public class FXMLLeerFicheroVelController implements Initializable {
    
    @FXML
    private Button button;
    @FXML
    private TextField rutaText;
    @FXML
    private TextField rutaText1;
    @FXML
    private Slider velLecturaLineas;
    @FXML
    private TextArea contenidoText;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException, InterruptedException {
        
        contenidoText.setText("");
        
        //Contador de lineas
        File f = new File(rutaText.getText());
        FileReader fReader = new FileReader(f);
        BufferedReader bffReader = new BufferedReader(fReader);
        
        int lines = 0;
        while (bffReader.readLine() != null) lines++;
        bffReader.close();
        
        
        
        int velocidad = (int) velLecturaLineas.getValue();
        

        int duration = (int) f.length();
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(velocidad), new EventHandler<ActionEvent>() {
            
            File f = new File(rutaText.getText());
            FileReader fReader = new FileReader(f);
            BufferedReader bfReader = new BufferedReader(fReader);
            
            @Override
            public void handle(ActionEvent event) {
               
                try { 
                
                contenidoText.setText(contenidoText.getText() + bfReader.readLine() + "\n\r");
                
                
                
                } catch (IOException ex) {
                    Logger.getLogger(FXMLLeerFicheroController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
        }));
        
        //Selecciona el tiempo total
        timeline.setCycleCount(lines);
        timeline.playFromStart();
            
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
