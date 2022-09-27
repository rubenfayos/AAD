/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    private Label leerText;
    @FXML
    private TextField rutaText1;
    @FXML
    private Slider velLecturaLineas;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException, InterruptedException {
        
        File f = new File(rutaText.getText());
        
        
        int velocidad = (int) velLecturaLineas.getValue();
        
        ArrayList<Character> readChars = new ArrayList<Character>();

        
        
        if(f.exists()){
            
            FileReader fReader = new FileReader(f);
        
            int contador = 0;
            int fr;
            
            

            while((fr = fReader.read()) != -1){
                
                /*

                if(contador == 50){

                    leerText.setText(leerText.getText() + "Introduce una tecla para continuar");

                    String leer = leerText.getText();

                    while( leer.equals(leerText.getText())){

                    }
                }

                */
            
                readChars.add((char)fr);

                System.out.println(Character.toString((char)fr));
                //leerText.setText(leerText.getText() + Character.toString((char)fr));
                contador++;
                
                

            }

            
        }

        

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            
            int contadorr = 0;
            
            @Override
            public void handle(ActionEvent event) {
               leerText.setText(leerText.getText() + readChars.get(contadorr));
               contadorr++;
                
            }
            
        }));
            
        
        timeline.setCycleCount(readChars.size());
        timeline.play();
            
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
