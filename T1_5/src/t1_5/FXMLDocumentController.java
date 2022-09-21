/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1_5;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author DAM 2
 */
public class FXMLDocumentController implements Initializable {
    
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
    private void handleButtonAction(ActionEvent event) {
        
        File f = new File(dirText.getText());
        
        FileFilter fFilefilter = new FileFilter(){
            public boolean accept(File file) {
		if (file.getName().endsWith(ExtText.getText())) {
			return true;
		}
		return false;
            }
        };
        
        if(f.isDirectory()){
            if(ExtText.getText().isEmpty()){
                
                String dirFiles[] = f.list();
                
                for(String files : dirFiles){

                    DirFiles.setText(DirFiles.getText() + files + "\n\r");

                }
                
            }else{
            
                File dirFiles[] = f.listFiles(fFilefilter);

                DirFiles.setText("Files in " + f.getName() + "\n\r");

                for(File files : dirFiles){

                    DirFiles.setText(DirFiles.getText() + files.getName() + "\n\r");

                }
            }
            
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
