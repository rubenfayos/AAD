/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//Import para utilizar XML
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
    
    private Label label;
    @FXML
    private TextField inputText;
    @FXML
    private TextArea outputText;
    @FXML
    private Label atributoText;
    @FXML
    private TextField añadirAtributoText;
    @FXML
    private TextField rutaCrearFichero;
    
    ArrayList<Persona> personas = new ArrayList<>();
    private int position = 0;
    Persona p;
    Alert fileAlarm = new Alert(Alert.AlertType.ERROR, "Ese documento no existe");
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void readFile(ActionEvent event) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
        
        if(!inputText.getText().isEmpty()){
        
            outputText.setText("");

            //Procesa un documento para que se pueda leer como un XML
            Document doc = processXML(inputText.getText());

            //Crea una lista con los nodos del documento
            NodeList list = doc.getElementsByTagName("persona");

            //Obtiene el total de nodos del documento
            outputText.setText("Total nodes: " + list.getLength() + "\n\r");

            //Iteración de los nodos
            for(int i = 0; i < list.getLength(); i++){

                outputText.setText(outputText.getText() + "nodo " + (i+1) + "\n\r");

                //Obtiene todos los atributos del nodo
                Node node = list.item(i);

                //Convierte el nodo a element
                Element element = (Element) node;

                //Crea un objeto y le añade los atributos
                Persona p = new Persona();

                p.setNombre(element.getAttribute("nombre"));
                p.setEdad(Integer.parseInt(element.getElementsByTagName("edad").item(0).getTextContent()));
                p.setSexo(element.getElementsByTagName("sexo").item(0).getTextContent());
                p.setAltura(Double.parseDouble(element.getElementsByTagName("altura").item(0).getTextContent()));
                p.setPeso(Double.parseDouble(element.getElementsByTagName("peso").item(0).getTextContent()));

                //Añade las personas al arraylist
                personas.add(p);

                outputText.setText(outputText.getText() + "Nombre: " + p.getNombre() + "\n\r");
                outputText.setText(outputText.getText() + "Edad: " +  p.getEdad() + "\n\r");
                outputText.setText(outputText.getText() + "Sexo: " + p.getSexo() + "\n\r");
                outputText.setText(outputText.getText() + "Altura: " + p.getAltura() + "\n\r");
                outputText.setText(outputText.getText() + "Peso: " + p.getPeso() + "\n\r");

            }
            
        }else{
            fileAlarm.showAndWait();
            inputText.setText("");
        }
                  
    }
    
    private Document processXML(String file){
        
        Document doc = null;
        
        try{
            
            //Método para procesar un documento y convertirlo en un documento legible
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            doc = db.parse(new File(file));
        
        }   catch (ParserConfigurationException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return doc;
    }

    @FXML
    private void addObject(ActionEvent event) {
        
        
        position = 0;
        
        p = new Persona();
        
        atributoText.setText("Nombre: ");
        
        position++;
        
    }

    @FXML
    private void siguiente(ActionEvent event) {
        
        //Segun la posicion actual añade un atributo o otro
        switch (position) {
            case 1:
                p.setNombre(añadirAtributoText.getText());
                atributoText.setText("Edad: ");
                break;
            case 2:
                p.setEdad(Integer.parseInt(añadirAtributoText.getText()));
                atributoText.setText("Sexo: ");
                break;
            case 3:
                p.setSexo(añadirAtributoText.getText());
                atributoText.setText("Altura: ");
                break;
            case 4:
                p.setAltura(Double.parseDouble(añadirAtributoText.getText()));
                atributoText.setText("Peso: ");
                break;
            case 5:
                p.setPeso(Double.parseDouble(añadirAtributoText.getText()));
                atributoText.setText("Objeto creado");
                personas.add(p);
                break;
            default:
                break;
        }
        
        position++;
        añadirAtributoText.setText("");      
    }

    @FXML
    private void guardarXML(ActionEvent event) {
        
        //Método para guardar el documento como xml     
        if(!rutaCrearFichero.getText().isEmpty()){
        
            try {

                //Nos permite procesar documentos XML
                DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
                DocumentBuilder build = dFact.newDocumentBuilder();
                Document doc = build.newDocument();
                
                //Define el elemento raiz y lo añade al documento
                Element raiz = doc.createElement("personas");
                doc.appendChild(raiz);

                //Itera los objetos persona
                for(Persona p : personas){
                    //Crea un nuevo elemento
                    Element persona = doc.createElement("persona");
                    //Le añade el atributo y la informacion al elemento
                    persona.setAttribute("nombre", p.getNombre());
                    //Añade el elemento a la raiz
                    raiz.appendChild(persona);

                    //Crea el atributo edad
                    Element edad = doc.createElement("edad");
                    //Le añade el texto al atributo
                    edad.appendChild(doc.createTextNode(String.valueOf(p.getEdad())));
                    //Añade el atributo a persona
                    persona.appendChild(edad);

                    //Elemento sexo
                    Element sexo = doc.createElement("sexo");
                    sexo.appendChild(doc.createTextNode(p.getSexo()));
                    persona.appendChild(sexo);

                    //Elemento altura
                    Element altura = doc.createElement("altura");
                    altura.appendChild(doc.createTextNode(String.valueOf(p.getAltura())));
                    persona.appendChild(altura);

                    //Elemento peso
                    Element peso = doc.createElement("peso");
                    peso.appendChild(doc.createTextNode(String.valueOf(p.getPeso())));
                    persona.appendChild(peso);


                }

                //Clases para poder crear un XML
                TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
                Transformer aTransformer = tranFactory.newTransformer();
                aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
                aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); //Cantidad de indentacion
                aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);

                outputText.setText("Fichero creado satisfactoriamente");
                
                try {
                    //Crea el fichero y lo escbribe
                    FileWriter fw = new FileWriter(rutaCrearFichero.getText()); // Definir el nombre del fichero y guardar
                    StreamResult result = new StreamResult(fw);
                    aTransformer.transform(source, result);
                    fw.close();
                 } catch (IOException e) {
                    e.printStackTrace();
                }

                } catch (TransformerException ex) {
                    System.out.println("Error escribiendo el documento");
                } catch (ParserConfigurationException ex) {
                    System.out.println("Error construyendo el documento");
                }
        
        }else{
            fileAlarm.showAndWait();
        }
    }
}
