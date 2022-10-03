/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1_aev2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea readText;
    @FXML
    private TextArea readText1;
    
    private Model model = new Model();
    @FXML
    private TextField findText;
    @FXML
    private TextField replaceText; 
    @FXML
    private TextField pathText;
    
    File f;
    Alert a = new Alert(Alert.AlertType.ERROR, "Algo está mal");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
    }    

    @FXML
    private void find(ActionEvent event) throws IOException {
        
        if(!findText.getText().isEmpty()){
        
            String word = findText.getText();

            int occurrences = model.findWord(model.ReadFile(f), word);

            findText.setText(String.valueOf(occurrences));
        }else{
            a.setContentText("Faltan campos por introducir");
            a.showAndWait();
        }
        
    }

    @FXML
    private void replace(ActionEvent event) throws IOException {
        
        if(!findText.getText().isEmpty() && !replaceText.getText().isEmpty()){
        
            String word = findText.getText();
            String newWord = replaceText.getText();

            String newText = model.replaceWord(model.ReadFile(f), word, newWord);

            readText1.setText(newText);

            // create a text input dialog
            TextInputDialog td = new TextInputDialog();

            // setHeaderText
            td.setHeaderText("Introduce el nuevo nombre del fichero");

            td.showAndWait();

            if(!td.getEditor().getText().isEmpty()){
                model.CreateFile(newText, td.getEditor().getText());
            }else{
                a.setContentText("Ese archivo no existe");
                a.showAndWait();
            }
            
        }else{
            a.setContentText("Faltan campos por introducir");
            a.showAndWait();
        }
            
  
    }

    @FXML
    private void read(ActionEvent event) throws IOException {
        
        if(!pathText.getText().isEmpty()){
            
            f = new File(pathText.getText());
            readText.setText(model.ReadFile(f));
            
        }else{
            a.setContentText("Ese archivo no existe");
            a.showAndWait();
        }
        
    }
    
}
