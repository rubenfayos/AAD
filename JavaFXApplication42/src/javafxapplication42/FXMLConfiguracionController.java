/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication42;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;


/**
 *
 * @author Ruben Fayos
 */
public class FXMLConfiguracionController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField ubicacionText;
    @FXML
    private TextArea brutoXML;
    @FXML
    private TextField archivoConfText;
    @FXML
    private TextField archivoDatosText;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void crearBoton(ActionEvent event) throws IOException, TransformerConfigurationException {
        
        
        
        TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
        Transformer aTransformer = tranFactory.newTransformer();
        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
        aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //DOMSource source = new DOMSource(doc);

        
    }

    @FXML
    private void generarCodigoBoton(ActionEvent event) {
        
            brutoXML.setText(brutoXML.getText()+"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\r");
            brutoXML.setText(brutoXML.getText()+"<configuracion>\n\r");
            brutoXML.setText(brutoXML.getText()+"<XMLUbicacion></XMLUbicacion>\n\r");
            brutoXML.setText(brutoXML.getText()+"<XMLConfiguracion>conf.xml</XMLConfiguracion>\n\r");
            brutoXML.setText(brutoXML.getText()+"<XMLDatos></XMLDatos>\n\r");
            brutoXML.setText(brutoXML.getText()+"</configuracion>\n\r");        
        
    }
    
    private void ProcesarXML(){
        
        
        
    }
    
}
