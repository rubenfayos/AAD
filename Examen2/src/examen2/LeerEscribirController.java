/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examen2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

/**
 * FXML Controller class
 *
 * @author RubenFayos
 */
public class LeerEscribirController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField pathOrigen;
    @FXML
    private TextField pathDestino;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void leerArchivo(ActionEvent event) {
        
        try{
        
            if(pathOrigen.getText().isEmpty()){
                Alert fileError = new Alert(Alert.AlertType.ERROR, "No se encuentra el archivo");
                fileError.showAndWait();

            }else{

                String lectura = "";
                FileReader fr = new FileReader(pathOrigen.getText());
                int c = 0;
                while(( c = fr.read()) != -1){

                    lectura += (char)c;

                }

                textArea.setText(lectura);

            }
        
        } catch (FileNotFoundException ex) {
                Logger.getLogger(BuscarPalabrasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BuscarPalabrasController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void escribirArchivo(ActionEvent event) {
        
        FileWriter fw = null;
        
        try {
            
            //Escribe el archivo
            String lectura = textArea.getText();
            fw = new FileWriter(pathDestino.getText());
            fw.write(lectura);
            
            
        } catch (IOException ex) {
            Logger.getLogger(LeerEscribirController.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(LeerEscribirController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
