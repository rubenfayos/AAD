/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package t1_aev3;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author DAM 2
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField rutaText;
    @FXML
    private TextArea salidaText;
    @FXML
    private TextField idLibro;
    @FXML
    private TextField tituloLibro;
    @FXML
    private TextField autorLibro;
    @FXML
    private TextField añoLibro;
    @FXML
    private TextField editorialLibro;
    
    ArrayList<Libro> libros;
    Biblioteca biblioteca;
    @FXML
    private TextField paginasLibro;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cargarXML(ActionEvent event) {
        
        libros = new ArrayList<>();
        
        if(!rutaText.getText().isEmpty()){
        
            salidaText.setText("");

            //Procesa un documento para que se pueda leer como un XML
            Document doc = processXML(rutaText.getText());
            
            NodeList list = doc.getElementsByTagName("libro");
            
            salidaText.setText("Total libros: " + list.getLength() + "\n\r");
            
            //Iteración de los nodos
            for(int i = 0; i < list.getLength(); i++){

                //Obtiene todos los atributos del nodo
                Node node = list.item(i);

                //Convierte el nodo a element
                Element element = (Element) node;
                
                Libro l = new Libro();
                
                l.setId(Integer.parseInt(element.getAttribute("id")));
                l.setTitulo(element.getElementsByTagName("titulo").item(0).getTextContent());
                l.setAutor(element.getElementsByTagName("autor").item(0).getTextContent());
                l.setEditorial(element.getElementsByTagName("editorial").item(0).getTextContent());
                l.setAñoPublicacion(Integer.parseInt(element.getElementsByTagName("añoPublicacion").item(0).getTextContent()));
                l.setPaginas(Integer.parseInt(element.getElementsByTagName("paginas").item(0).getTextContent()));
                
                libros.add(l);
                salidaText.setText("Documento cargado correctamente");
            
            }
            
            biblioteca = new Biblioteca(libros);
            
        }
        
    }
    
    private Document processXML(String file){
        
        Document doc = null;
        
        try{
            
            File f = new File(file);
            
            if(f.exists()){
            
                //Método para procesar un documento y convertirlo en un documento legible
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

                // process XML securely, avoid attacks like XML External Entities (XXE)
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

                // parse XML file
                DocumentBuilder db = dbf.newDocumentBuilder();

                doc = db.parse(f);
                
            }else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Ese fichero no existe");
                errorAlert.showAndWait();
            }
        
        }   catch (ParserConfigurationException | SAXException | IOException e) {
            salidaText.setText("Algo ha fallado");
        } 
        
        return doc;
    }

    @FXML
    private void MostrarLibros(ActionEvent event) {
        
        salidaText.setText(biblioteca.mostrarLibros());
        
    }

    @FXML
    private void MostrarLibro(ActionEvent event) {
        
        //Busca el libro por la id y luego lo muestra
        String salida = biblioteca.mostrarLibro(biblioteca.buscarLibro(Integer.parseInt(idLibro.getText())));
        salidaText.setText(salida);
        
    }

    @FXML
    private void CrearLibro(ActionEvent event) {
        
        Libro l = new Libro();
        l.setId(Integer.parseInt(idLibro.getText()));
        l.setTitulo(tituloLibro.getText());
        l.setAutor(autorLibro.getText());
        l.setEditorial(editorialLibro.getText());
        l.setAñoPublicacion(Integer.parseInt(añoLibro.getText()));
        l.setPaginas(Integer.parseInt(paginasLibro.getText()));
        
        biblioteca.crearLibro(l);
        
    }
    
    
    
}
