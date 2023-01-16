package com.dam.ruben;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import com.dam.ruben.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Ruben Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField dbNameInput;
    @FXML
    private TextField tableInput;
    @FXML
    private TextField outputPathInput;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    

    @FXML
    private void Convert(ActionEvent event) {
        
        if(dbNameInput.getText().isEmpty() || tableInput.getText().isEmpty() || outputPathInput.getText().isEmpty()){
            
        }else{
            
            Model m = new Model();
            m.Conexion(dbNameInput.getText());
            m.ReadTable(tableInput.getText());
            m.writeJson(outputPathInput.getText());
        
        }
        
    }

    
}
