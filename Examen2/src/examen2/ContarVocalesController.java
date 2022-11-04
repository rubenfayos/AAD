/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examen2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author RubenFayos
 */
public class ContarVocalesController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField pathOrigen;
    @FXML
    private Text numeroVecesVocales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void leerArchivoContar(ActionEvent event) {
        
        //Para contra los caracteres he usado un hashmap
        HashMap<Character, Integer> vocales = new HashMap<Character, Integer>();
        vocales.put('a', 0);
        vocales.put('e', 0);
        vocales.put('i', 0);
        vocales.put('o', 0);
        vocales.put('u', 0);
        
        
        try {
        
        if(pathOrigen.getText().isEmpty()){
            Alert fileError = new Alert(Alert.AlertType.ERROR, "No se encuentra el archivo");
            fileError.showAndWait();
            
        }else{
            
            char caracter = 'a';
            String lectura = "";
            FileReader fr = new FileReader(pathOrigen.getText());
            int c = 0;
            
            while(( c = fr.read()) != -1){
            
                caracter = (char)c;
                lectura += caracter;
                
                //Comprueba si es caracter es una vocal para así sumar
                if(vocales.containsKey(caracter)){
                    int repeticiones = vocales.get((char)c);
                    vocales.put(caracter, repeticiones+1);
                }
                
            }
            
            textArea.setText(lectura);
            numeroVecesVocales.setText("Repeticiones a: " + vocales.get('a') + ", e: " + vocales.get('e') + ", i: " + vocales.get('i') + ", o: " + vocales.get('o') + ", u: " + vocales.get('u'));
            
        }
        
        } catch (FileNotFoundException ex) {
                Logger.getLogger(BuscarPalabrasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BuscarPalabrasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
