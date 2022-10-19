/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1_aev1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author DAM 2
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField rutaText;
    @FXML
    private TextArea leerText;
    
    private File f;
    private Alert fileAlert;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.fileAlert = new Alert(Alert.AlertType.ERROR, "El fichero es incorrecto o no existe");
        
    }    

    @FXML
    private void Info(ActionEvent event) {
        
        this.f = new File(rutaText.getText());
        
        leerText.setText("");
        
        if(this.f.exists()){
        
            leerText.setText("Nombre: " + this.f.getName() + "\n\r");

            if(this.f.isDirectory())
                leerText.setText(leerText.getText() + "Tipo: Directorio\n\r");
            else
                leerText.setText(leerText.getText() + "Tipo: Archivo\n\r");

            leerText.setText(leerText.getText() + "Ubicación: " + this.f.getPath() + "\n\r");

            //Obtener la última fecha de modificacion
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(this.f.lastModified());
            leerText.setText(leerText.getText() + "Ultima modificación: " + cal.getTime() + "\n\r");
            leerText.setText(leerText.getText() + "Es oculto: " + this.f.isHidden() + "\n\r");
            
        }else{
            
            fileAlert.showAndWait();
            
        }
        
    }

    @FXML
    private void eliminar(ActionEvent event) {
        
        this.f = new File(rutaText.getText());
        
        if(this.f.exists()){
        
            //Nos pregunta si queremos confirmar
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Seguro que quieres eliminar el fichero?");
            
            Optional<ButtonType> action = confirmationAlert.showAndWait();
            
            if(action.get() == ButtonType.OK){
                
                //Borra el archivo
                this.f.delete();
                
            }
                
        }else{
            
            fileAlert.showAndWait();
            
        }
       
    }

    @FXML
    private void renombrar(ActionEvent event) throws IOException {
        
        this.f = new File(rutaText.getText());
        
        if(this.f.exists()){
        
            // create a text input dialog
            TextInputDialog td = new TextInputDialog();

            // setHeaderText
            td.setHeaderText("Introduce el nuevo nombre");

            td.showAndWait();
            
            if(td.getEditor().getText().isEmpty()){
                
                leerText.setText("");
                leerText.setText("Ese nombre no es válido");
                
            }else{
                
                //Copiar el fichero
                Path pf1 = Paths.get(this.f.getAbsolutePath());

                Files.move(pf1, pf1.resolveSibling(td.getEditor().getText()));
                
                leerText.setText("");
                leerText.setText("Se ha renombrado el archivo"); 
                
            }
               
        }else{
            
            fileAlert.showAndWait();
            
        }
        
    }

    @FXML
    private void CrearFichero(ActionEvent event) throws IOException {
        
        this.f = new File(rutaText.getText());
        
        if(this.f.isDirectory()){
        
            // create a text input dialog
            TextInputDialog td = new TextInputDialog();

            // setHeaderText
            td.setHeaderText("Introduce el nuevo nombre del fichero");

            td.showAndWait();

            File newFile = new File(this.f.getAbsolutePath() + "\\" + td.getEditor().getText());
            
            if(newFile.exists()){
                
                fileAlert.showAndWait();
                
            }else{
                
                FileWriter fw = new FileWriter(newFile);
            
                fw.write("");

                leerText.setText("");
                leerText.setText("Se ha creado " + newFile.getAbsolutePath() + ".");
                
                fw.flush();
                fw.close();
                
            }
            
        }else{
            
            fileAlert.showAndWait();
            
        }
        
    }

    @FXML
    private void CrearDirectorio(ActionEvent event) {
        
        this.f = new File(rutaText.getText());
        
        if(this.f.isDirectory()){
        
            // create a text input dialog
            TextInputDialog td = new TextInputDialog();

            // setHeaderText
            td.setHeaderText("Introduce el nuevo nombre del directorio");

            td.showAndWait();

            File newFile = new File(this.f.getAbsolutePath() + "\\" + td.getEditor().getText());
            
            if(newFile.mkdir()){
                leerText.setText("");
                leerText.setText("Se ha creado " + newFile.getAbsolutePath() + ".");
            }   
        
        }else{
            
            fileAlert.showAndWait();
            
        }
        
    }
   
}
