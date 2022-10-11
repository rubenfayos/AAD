/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s1;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author DAM 2
 */
public class FXMLFilterDirController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField dirText;
    @FXML
    private TextArea DirFiles;
    @FXML
    private TextField ExtText;
    @FXML
    private AnchorPane scene;
    @FXML
    private Button button1;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        DirFiles.setText("");
        
        File f = new File(dirText.getText());
        
        String[] arrOfStr = ExtText.getText().split(";");
        
        
        if(f.isDirectory()){
            if(ExtText.getText().isEmpty()){
                
                String dirFiles[] = f.list();
                
                for(String files : dirFiles){

                    DirFiles.setText(DirFiles.getText() + files + "\n\r");

                }
                
            }else{
                
                DirFiles.setText("Files in " + f.getName() + "\n\r");
                
                for(String ext : arrOfStr){
                    
                    FileFilter fFilefilter = new FileFilter(){
                        
                        public boolean accept(File file) {
                            
                            if (file.getName().endsWith(ext)) {
                            
                                return true;
                            }
                                return false;
                        }
                    };
                    
                    DirFiles.setText(DirFiles.getText() + "Extensión " + ext + ": \n\r");
                    
                    File dirFiles[] = f.listFiles(fFilefilter);

                    for(File files : dirFiles){

                        DirFiles.setText(DirFiles.getText() + files.getName() + "\n\r");

                    }
                    
                }
    
            }
            
        }else{
            DirFiles.setText("Ese directorio no existe");
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Volver(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        this.scene.getChildren().setAll(pane);
        
    }
    
}
