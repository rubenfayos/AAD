/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    private TextArea leerText;
    @FXML
    private TextField rutaText;
    @FXML
    private Slider velLecturaLineas;
    @FXML
    private TextField caracteresText;
    @FXML
    private Button continuarButton;
    @FXML
    private Button button;
    @FXML
    private TextField rutaSalidaText;
    
    Boolean comp = false;
    int contador = 0;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void leer(ActionEvent event) throws FileNotFoundException, IOException, InterruptedException {
        
        leerText.setText("");
        
        if(velLecturaLineas.getValue() > 0){

            File f = new File(rutaText.getText());
            FileReader fReader = new FileReader(f);
            BufferedReader bfReader = new BufferedReader(fReader);

            int totalLines = 0;
            while(bfReader.readLine() != null){
                totalLines++;
            }

                printLines(totalLines, (int) velLecturaLineas.getValue());
        }else if(Integer.parseInt(caracteresText.getText()) > 0)
            printCharacters(0);
            
             
    }
    
    public void printLines(int totalLines, int linesPerSecond) throws FileNotFoundException, IOException{
        
        
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
         
        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(linesPerSecond), eventHandler));
        animation.setCycleCount(totalLines);
        animation.playFromStart();
        
        
    }



    public void printCharacters(int saltar) throws FileNotFoundException, IOException, InterruptedException{
        
        continuarButton.setStyle("-fx-background-color: green; ");
        
        int caracteres = Integer.parseInt(caracteresText.getText());
        File f = new File(rutaText.getText());
        FileReader fReader  = new FileReader(f);
        
        //Salta al caracter por el que va
        fReader.skip(saltar);
        
        //Define los caracteres que quedan por leer
        int fLength = (int)f.length() - contador;
        
        int duration;
        
        //Define la duracion del timeline 
        if(fLength >= contador)
                duration = caracteres;
            else{
                duration = contador;
            }
            
        contador += duration;
    
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

    
            int fr = fReader.read();

            @Override
            public void handle(ActionEvent event) {

                try {

                leerText.setText(leerText.getText() + Character.toString((char)fr));
                fr = fReader.read();


                } catch (IOException ex) {
                    Logger.getLogger(FXMLLeerFicheroController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }));
        
            
            
        timeline.setCycleCount(duration);
        timeline.play();
        
    
        timeline.setOnFinished( e -> continuarButton.setStyle("-fx-background-color: red; "));
        
        return ;
    
        }

    @FXML
    private void continuar(ActionEvent event) throws IOException, FileNotFoundException, InterruptedException {
        
        //Continua imprimiendo con salto de caracteres
        printCharacters(contador);
        
    }

    @FXML
    private void guardar(ActionEvent event) {
        
        String texto = leerText.getText();
        
        if(!texto.isEmpty()){
            
            File f = new File(rutaSalidaText.getText());
        
            try {

            FileWriter fWriter = new FileWriter(f);
            fWriter.write(texto);
            fWriter.flush();
            fWriter.close();

            } catch (IOException ex) {
                Logger.getLogger(FXMLEscribirFicheroController.class.getName()).log(Level.SEVERE, null, ex);
            }
     
        }
        
    }
    
                    
}

