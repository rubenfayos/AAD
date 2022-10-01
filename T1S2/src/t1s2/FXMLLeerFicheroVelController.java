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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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
    private TextArea leerText;
    @FXML
    private TextField rutaText1;
    @FXML
    private Slider velLecturaLineas;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException, InterruptedException {
        
        int totalLines = 0;
        
        File f = new File(rutaText.getText());
        FileReader fReader = new FileReader(f);
        BufferedReader bfReader = new BufferedReader(fReader);
        
        while(bfReader.readLine() != null) {
            totalLines++;
        }
        
        int velocidad = (int) velLecturaLineas.getValue();
         
        if(f.exists()){
            
            
        
            int contador = 0;
            int fr;
            
            /*

            while((fr = fReader.read()) != -1){
                
                

                if(contador == 50){

                    leerText.setText(leerText.getText() + "Introduce una tecla para continuar");

                    String leer = leerText.getText();

                    while( leer.equals(leerText.getText())){

                    }
                }

                
            
                readChars.add((char)fr);

                System.out.println(Character.toString((char)fr));
                //leerText.setText(leerText.getText() + Character.toString((char)fr));
                contador++;
                
                

            }

        */

            
        }
        
       

        
        
        /*
 

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(velocidad), new EventHandler<ActionEvent>() {
            
            File f = new File(rutaText.getText());
            FileReader fReader = new FileReader(f);
            BufferedReader bfReader = new BufferedReader(fReader);
            int contadorr = 0;
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    leerText.setText(leerText.getText() + bfReader.readLine() + "\n\r");
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLLeerFicheroVelController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               contadorr++;
                
            }
            
        }));
            
        
        timeline.setCycleCount(totalLines);
        timeline.play();

    */
        
        printLines();
            
        
        
    }
    
    public void printLines() throws FileNotFoundException{
        
        File f = new File(rutaText.getText());
        FileReader fReader = new FileReader(f);
         BufferedReader bfReader = new BufferedReader(fReader);
        
        // Create a handler for animation
        EventHandler<ActionEvent> eventHandler = e -> {
            
    
            try {
                leerText.setText(leerText.getText() + bfReader.readLine() + "\n\r");
            } catch (IOException ex) {
                Logger.getLogger(FXMLLeerFicheroVelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
    
        };
        
       
        new KeyValue(wv, f))
        
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(5);
        animation.playFromStart();
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
