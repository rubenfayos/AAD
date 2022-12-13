package examenrfm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import examenrfm.SQLModel;
import examenrfm.XMLModel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Ruben Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField archivoText;
    @FXML
    private TextField servidorText;
    @FXML
    private TextField puertoText;
    @FXML
    private TextField usuarioText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField bddText;
    @FXML
    private TextField xmlText;
    
    private SQLModel sqlModel;
    private XMLModel xmlModel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        xmlModel = new XMLModel();
        
    }    

    @FXML
    private void sqlToXml(ActionEvent event) {
        
        sqlModel = new SQLModel(servidorText.getText(), puertoText.getText(), usuarioText.getText(), passwordText.getText(), bddText.getText());
        
        if(sqlModel.conexion()){
            
            if(xmlModel.writeXmlFile(xmlText.getText(), sqlModel.listarMaravillas())){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Datos introducidos correctamente");
                a.showAndWait();
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR, "Algo ha fallado");
                a.showAndWait();
            }
            
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Conexion con bdd incorrecta");
            a.showAndWait();
        }
        
        
        
        
        
    }

    @FXML
    private void xmlToSql(ActionEvent event) {
        
        sqlModel = new SQLModel(servidorText.getText(), puertoText.getText(), usuarioText.getText(), passwordText.getText(), bddText.getText());
        
        if(sqlModel.conexion()){
        
            sqlModel.ConsultaBruto("TRUNCATE table maravillas;");
        
            if(sqlModel.insertarTodo(xmlModel.readXML(xmlText.getText())) > 0){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Datos introducidos correctamente");
                a.showAndWait();
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR, "Algo ha fallado");
                a.showAndWait();
            }
            
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Conexion con bdd incorrecta");
            a.showAndWait();
        }
        
    }

    @FXML
    private void leerCsv(ActionEvent event) {
        
        try {
            FileReader fr = new FileReader(archivoText.getText());
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            
            String[] datos = line.split(";");
            servidorText.setText(datos[0]);
            puertoText.setText(datos[1]);
            usuarioText.setText(datos[2]);
            passwordText.setText(datos[3]);
            bddText.setText(datos[4]);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
}
