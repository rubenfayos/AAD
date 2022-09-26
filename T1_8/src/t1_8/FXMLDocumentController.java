/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1_8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jdk.jfr.events.FileWriteEvent;

/**
 *
 * @author DAM 2
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button button;
    private TextField FileText;
    @FXML
    private TextArea proccessText;
    private TextField FileName;
    @FXML
    private TextField fileText;
    @FXML
    private TextField fileName;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException {
        
        //File reader
        File f1 = new File(fileText.getText());
        FileReader f1Reader = new FileReader(f1);
        BufferedReader bf1 = new BufferedReader(f1Reader);

        
        
        //File writter
        File f2 = new File(fileName.getText());
        FileWriter f2Writter = new FileWriter(f2, true);
        BufferedWriter bf2 = new BufferedWriter(f2Writter);

        
        String lines = "";
        while((lines = bf1.readLine()) != null){
            
            bf2.write(lines + "\n");
                   
        }
        
        bf1.close();
        bf2.close();
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
