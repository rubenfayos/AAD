/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static javax.script.ScriptEngine.FILENAME;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

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
    private Button button;
    @FXML
    private TextField inputText;
    @FXML
    private TextArea outputText;
    @FXML
    private Label atributoText;
    @FXML
    private TextField añadirAtributoText;
    @FXML
    private Button siguienteButton;
    
    ArrayList<Persona> personas = new ArrayList<>();
    private int position = 0;
    Persona p;
    @FXML
    private TextField rutaCrearFichero;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void readFile(ActionEvent event) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
        
        outputText.setText("");
        
        Document doc = processXML(inputText.getText());

        NodeList list = doc.getElementsByTagName("persona");

        //Obtiene el total de nodos del documento
        outputText.setText("Total nodes: " + list.getLength() + "\n\r");

        for(int i = 0; i < list.getLength(); i++){

            outputText.setText(outputText.getText() + "nodo " + (i+1) + "\n\r");

            Node node = list.item(i);

            //Parse de node a element
            Element element = (Element) node;

            //Crea un objeto y le añade los atributos
            Persona p = new Persona();

            p.setNombre(element.getAttribute("nombre"));
            p.setEdad(Integer.parseInt(element.getElementsByTagName("edad").item(0).getTextContent()));
            p.setSexo(element.getElementsByTagName("sexo").item(0).getTextContent());
            p.setAltura(Double.parseDouble(element.getElementsByTagName("altura").item(0).getTextContent()));
            p.setPeso(Double.parseDouble(element.getElementsByTagName("peso").item(0).getTextContent()));

            personas.add(p);

            outputText.setText(outputText.getText() + "Nombre: " + p.getNombre() + "\n\r");
            outputText.setText(outputText.getText() + "Edad: " +  p.getEdad() + "\n\r");
            outputText.setText(outputText.getText() + "Sexo: " + p.getSexo() + "\n\r");
            outputText.setText(outputText.getText() + "Altura: " + p.getAltura() + "\n\r");
            outputText.setText(outputText.getText() + "Peso: " + p.getPeso() + "\n\r");

        }
        
            
    }
    
    private Document processXML(String file) throws SAXException, IOException, ParserConfigurationException{
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        // optional, but recommended
        // process XML securely, avoid attacks like XML External Entities (XXE)
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // parse XML file
        DocumentBuilder db = dbf.newDocumentBuilder();

        return db.parse(new File(file));
        
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
        
        if(position == 1){
            p.setNombre(añadirAtributoText.getText());
            atributoText.setText("Edad: ");
        }else if (position == 2){
            p.setEdad(Integer.parseInt(añadirAtributoText.getText()));
            atributoText.setText("Sexo: ");
        }else if (position == 3){
            p.setSexo(añadirAtributoText.getText());
            atributoText.setText("Altura: ");
        }else if (position == 4){
            p.setAltura(Double.parseDouble(añadirAtributoText.getText()));
            atributoText.setText("Peso: ");
        }else if (position == 5){
            p.setPeso(Double.parseDouble(añadirAtributoText.getText()));
            atributoText.setText("Objeto creado");
            personas.add(p);
        }
        
        position++;
        añadirAtributoText.setText("");
        
    }

    @FXML
    private void guardarXML(ActionEvent event) {
        
        if(!rutaCrearFichero.getText().isEmpty()){
        
            try {

                DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
                DocumentBuilder build = dFact.newDocumentBuilder();
                Document doc = build.newDocument();
                Element raiz = doc.createElement("personas");
                doc.appendChild(raiz);

                for(Persona p : personas){
                    //Crea un nuevo elemento
                    Element persona = doc.createElement("persona");
                    //Le añade el atributo al elemento
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

                TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
                Transformer aTransformer = tranFactory.newTransformer();
                aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
                aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);


                try {
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
            
        }
    }
}
