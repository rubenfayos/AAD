/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1examen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLCrearDirectorioController implements Initializable {

    @FXML
    private TextField numDirText;
    @FXML
    private TextField numFileText;
    @FXML
    private TextField pathText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CrearDirectorio(ActionEvent event) {
        
        int directorios = 0, ficheros = 0;
        
        if(!numDirText.getText().isEmpty())
            directorios = Integer.parseInt(numDirText.getText());
        
        if(!numFileText.getText().isEmpty())
            ficheros = Integer.parseInt(numFileText.getText());
        
        //Comprobacion file
        if(!pathText.getText().isEmpty()){
            
            File f = new File(pathText.getText());
        
            if(f.exists())
                deleteFiles(f);

            String[] files = f.getAbsolutePath().split("\\\\");

            String file = "";

            for(String temp : files){

                file += temp + "\\";
                File tempf = new File(file);
                if(!tempf.exists())
                    tempf.mkdir();
            }

            f.mkdir();

            

            //For creacion directorios
            for(int i = 0; i < directorios; i++){

                File f1 = new File(f.getAbsolutePath() + "\\Ruben"+i);
                f1.mkdir();

            }

            //For creacion ficheros
            for(int i = 0; i < ficheros; i++){

                File f1 = new File(f.getAbsolutePath() + "\\Fayos"+i);

                try {

                    f1.createNewFile();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLCrearDirectorioController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "No has introducido nada");
            a.showAndWait();
        }
             
    }
    
    public void deleteFiles(File f){
        
        for(File tempf : f.listFiles()){
                    
            if(tempf.isDirectory())
                deleteFiles(tempf);
                
            tempf.delete();
                    
        }
        
    }
    
}
