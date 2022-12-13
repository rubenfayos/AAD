/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenrfm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Ruben Fayos
 */
public class XMLModel {
    
    public boolean writeXmlFile(String fichero, ArrayList<Maravilla> maravillas) {
        
            
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();          
            Document doc = dBuilder.newDocument();
            doc.setXmlVersion("1.0");
            
            Element raiz = doc.createElement("maravillas");
            
            doc.appendChild(raiz);
            for (Maravilla m : maravillas) {
                
                Element maravilla = doc.createElement("maravilla");
                String id = String.valueOf(m.getId());
                maravilla.setAttribute("id",id);
                raiz.appendChild(maravilla);
                
                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(m.getNombre()));
                maravilla.appendChild(nombre);
                
                Element pais = doc.createElement("pais");
                pais.appendChild(doc.createTextNode(m.getPais()));
                maravilla.appendChild(pais);
                
                Element descripcion = doc.createElement("descripcion");
                descripcion.appendChild(doc.createTextNode(m.getDescripcion()));
                maravilla.appendChild(descripcion);
                
                
            }
            
            TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
            Transformer aTransformer = tranFactory.newTransformer();
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
            aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            
        
            FileWriter fw = new FileWriter(fichero); // Definir el nombre del fichero y guardar
            StreamResult result = new StreamResult(fw);
            aTransformer.transform(source, result);
            fw.flush();
            fw.close();
            
            return true;
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    public ArrayList<Maravilla> readXML (String file) {
        
        ArrayList<Maravilla> maravillas = new ArrayList<>();
        
        try {
            
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new File(file));
            
            Element raiz = document.getDocumentElement();

            NodeList nodeList = document.getElementsByTagName("maravilla");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                
                Node node = nodeList.item(i);
                Element eElement = (Element) node;
                
                Maravilla m = new Maravilla();
                
                m.setId(Integer.parseInt(eElement.getAttribute("id")));
                
                m.setNombre(eElement.getElementsByTagName("nombre").item(0).getTextContent());
                m.setPais((eElement.getElementsByTagName("pais").item(0).getTextContent()));
                m.setDescripcion((eElement.getElementsByTagName("descripcion").item(0).getTextContent()));
                
                maravillas.add(m);
                
            }   
            
        } catch (SAXException ex) {
            Logger.getLogger(XMLModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return maravillas;
        
    }
            
    
}
