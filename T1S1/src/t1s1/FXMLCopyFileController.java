/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
public class FXMLCopyFileController implements Initializable {
    
    @FXML
    private Button button;
    @FXML
    private TextField FileText;
    @FXML
    private TextArea proccessText;
    @FXML
    private TextField FileName;
    @FXML
    private Button volverButton;
    @FXML
    private AnchorPane scene;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        File f = new File(FileText.getText());
        
        //Crea un nuevo file utilizando la ruta del otro file
        File f2 = new File(f.getParentFile().getAbsolutePath() + "\\" + FileName.getText());
        
        try{
        
            if(f.exists()){

                copyFileUsingStream(f, f2);
                proccessText.setText(f2.getName() + " created in " + f2.getAbsolutePath());

            }
        
        } catch (IOException e) {
            proccessText.setText("An error occurred.");
        }
    }
    
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        
    InputStream is = null;
    OutputStream os = null;
    
    try {
        
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        
    } finally {
        is.close();
        os.close();
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
