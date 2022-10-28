/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package t1examen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ruben Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Button button1;
    @FXML
    private TextField borrarText;
    @FXML
    private AnchorPane scene;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchCrearDirectorio(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCrearDirectorio.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void switchListarDirectorio(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLListarDirectorio.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void BorrarFicheros(ActionEvent event) {
        
        File f = new File(borrarText.getText());
        if(f.exists()){
            
            if(f.isDirectory()){
                
                deleteFiles(f);
                
                if(f.delete()){
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Directorio borrardo");
                    a.showAndWait();
                }
                
            }
            
        }else{
                Alert error = new Alert(Alert.AlertType.ERROR, "Ese directorio no existe");
                error.showAndWait();
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
