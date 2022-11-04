/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examen2;

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
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author RubenFayos
 */
public class BuscarPalabrasController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField pathOrigen;
    @FXML
    private Text numeroVecesPalabra;
    @FXML
    private TextField palabraBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void leerArchivoBuscar(ActionEvent event) {
        
        try {
        
        if(pathOrigen.getText().isEmpty()){
            Alert fileError = new Alert(Alert.AlertType.ERROR, "No se encuentra el archivo");
            fileError.showAndWait();
        }else{
            
            if(palabraBuscar.getText().isEmpty()) {
                Alert wordError = new Alert(Alert.AlertType.ERROR, "No has introducido ninguna palabra");
                wordError.showAndWait();
            }else{
                
            

                String lectura = "";
                FileReader fr = new FileReader(pathOrigen.getText());
                int c = 0;
                while(( c = fr.read()) != -1){

                    lectura += (char)c;

                }

                int cont = BuscarPalabras(lectura, palabraBuscar.getText());
                numeroVecesPalabra.setText("Total repeticiones palabra " + palabraBuscar.getText() + ": " + String.valueOf(cont));
            }
        }
        
        } catch (FileNotFoundException ex) {
                Logger.getLogger(BuscarPalabrasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BuscarPalabrasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }  
    
    
    private int BuscarPalabras(String texto, String palabra){
        
        String punctuation = "";
        
        int cont = 0;
        String[] palabras = texto.split(" ");
        
        for(String temp : palabras){
            
            //Nos permite buscar ignorando comas y puntos
            if(temp.contains(",")){
                temp = temp.replace(",", "");
                punctuation = ",";
            }else if(temp.contains(".")){
                temp = temp.replace(".", "");
                punctuation = ".";
            }
            
            if(palabra.equals(temp)){
                cont++;
                textArea.setText(textArea.getText() + "<<" + palabra + ">>");
            }
            
            else{
                textArea.setText(textArea.getText() + temp);
            }
            
            //añade la puntuacion si es necesaria
            textArea.setText(textArea.getText() + punctuation + " ");
            punctuation = "";
        }
        
        return cont;
    }
}
