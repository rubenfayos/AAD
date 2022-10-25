/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1_aev3;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author DAM 2
 */
public class Biblioteca {
    
    private Map<Integer, Libro> libros;
 
    
    public Biblioteca() {
    }
    
    
    public int crearLibro(int id, Libro l){
        
        if(!libros.containsKey(id)){
            
            libros.put(id, l);

            return id;
        }
        
        return 0;
        
    }
    
    public String mostrarLibro(Libro libro){
        
        if(libro == null){
            return "El libro no se ha encontrado";
        }
        
        String info = "ID: " + libro.getId()
                + "\nTítulo: " + libro.getTitulo() 
                + "\nAutor: " + libro.getAutor() 
                + "\nAño publicación: " + libro.getAñoPublicacion()
                + "\nEditorial: " + libro.getEditorial()
                + "\nTotal páginas: " + libro.getPaginas() + "\n";
        
        return info;
        
    }
    
    public String mostrarLibros(){
        
        String infoLibros = "";
        
        if(libros.isEmpty()){
            return "No hay ningun libro";
            
        }else{
            
        for(Libro l : libros.values()){
            
            infoLibros += mostrarLibro(l);
            
        }
        
        return infoLibros;
        
        }
        
    }
    
    public Libro buscarLibro(int id){
        
        if(libros.containsKey(id))
            return libros.get(id);
        
        return null;
           
    }
    
    public void editarLibro(int id, Libro libroNuevo ){
        
        libros.remove(id);
        libros.put(libroNuevo.getId(), libroNuevo);
        
    }
    
    public int eliminarLibro(int id){
        
        if(libros.containsKey(id)){
            libros.remove(id);
            return 1;
        }
        
        return 0;
        
        
    }
    
    public String cargarXML(String fichero){
        
    //Hashmap de libros
    libros = new HashMap<Integer, Libro>();

    //Procesa un documento para que se pueda leer como un XML
    Document doc = processXML(fichero);
    
    if(doc != null){

        NodeList list = doc.getElementsByTagName("libro");

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

            libros.put(l.getId(), l);


        }
        
        return "Documento cargador correctamente";
        
    }
    
    return "";
        
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
            
        } 
        
        return doc;
    }
    
    public String guardarXML(String fichero){
        
        String salida = "";
        
        try {

                //Nos permite procesar documentos XML
                DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
                DocumentBuilder build = dFact.newDocumentBuilder();
                Document doc = build.newDocument();
                
                //Define el elemento raiz y lo añade al documento
                Element raiz = doc.createElement("libros");
                doc.appendChild(raiz);

                //Itera los objetos libro
                for(Libro l : libros.values()){
                    //Crea un nuevo elemento
                    Element libro = doc.createElement("libro");
                    //Le añade el atributo y la informacion al elemento
                    libro.setAttribute("id", String.valueOf(l.getId()));
                    //Añade el elemento a la raiz
                    raiz.appendChild(libro);

                    //Crea el atributo titulo
                    Element titulo = doc.createElement("titulo");
                    //Le añade el texto al atributo
                    titulo.appendChild(doc.createTextNode(l.getTitulo()));
                    //Añade el atributo a libro
                    libro.appendChild(titulo);

                    //Elemento autor
                    Element autor = doc.createElement("autor");
                    autor.appendChild(doc.createTextNode(l.getAutor()));
                    libro.appendChild(autor);

                    //Elemento editorial
                    Element editorial = doc.createElement("editorial");
                    editorial.appendChild(doc.createTextNode(l.getEditorial()));
                    libro.appendChild(editorial);

                    //Elemento año publicacion
                    Element añoPublicacion = doc.createElement("añoPublicacion");
                    añoPublicacion.appendChild(doc.createTextNode(String.valueOf(l.getAñoPublicacion())));
                    libro.appendChild(añoPublicacion);
                    
                    //Elemento páginas
                    Element paginas = doc.createElement("paginas");
                    paginas.appendChild(doc.createTextNode(String.valueOf(l.getPaginas())));
                    libro.appendChild(paginas);
                }

                //Clases para poder crear un XML
                TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
                Transformer aTransformer = tranFactory.newTransformer();
                aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // Darle formato al documento
                aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); //Cantidad de indentacion
                aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);

                salida = "Fichero creado satisfactoriamente";
                
                try {
                    //Crea el fichero y lo escbribe
                    File f = new File(fichero);
                    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8")); // Definir el nombre del fichero y guardar
                    StreamResult result = new StreamResult(wr);
                    aTransformer.transform(source, result);
                    wr.close();
                 } catch (IOException e) {
                    e.printStackTrace();
                }

                } catch (TransformerException ex) {
                    salida = "Error escribiendo el documento";
                } catch (ParserConfigurationException ex) {
                    salida = "Error escribiendo el documento";
                }
        
        return salida;
        
    } 
    
}
