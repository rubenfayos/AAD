/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package t1_aev3;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    @FXML
    private TextField paginasLibro;
    @FXML
    private TextField nuevaIDLibro;
    @FXML
    private TextField XMLSalida;
    
    Map<Integer, Libro> libros;
    Biblioteca biblioteca;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        biblioteca = new Biblioteca();
    }    

    @FXML
    private void cargarXML(ActionEvent event) {
        
        //Carga el XML
        if(rutaText.getText().isEmpty()){
            Alert file = new Alert(Alert.AlertType.ERROR, "No has introducido el fichero");
            file.showAndWait();
        }else{
            salidaText.setText(biblioteca.cargarXML(rutaText.getText()));

        }
          
    }

    @FXML
    private void MostrarLibros(ActionEvent event) {
        
        //Muestra todos los libros
        if(biblioteca == null){
            salidaText.setText("No hay ningún libro");
        }else{
            salidaText.setText(biblioteca.mostrarLibros());
        }
           
    }

    @FXML
    private void MostrarLibro(ActionEvent event) {
        
        //Busca el libro por la id y luego lo muestra
        String salida = biblioteca.mostrarLibro(biblioteca.buscarLibro(Integer.parseInt(idLibro.getText())));
        salidaText.setText(salida);
        
    }

    @FXML
    private void CrearLibro(ActionEvent event) {
        
        //Comprueba que todos los campos estén introducidos
        if(idLibro.getText().isEmpty() || tituloLibro.getText().isEmpty() || autorLibro.getText().isEmpty() || editorialLibro.getText().isEmpty() || añoLibro.getText().isEmpty() || paginasLibro.getText().isEmpty()){
            
            Alert errorA = new Alert(Alert.AlertType.ERROR, "Falta por introducir algún campo");
            errorA.showAndWait();
            
        }else{
        
            //Crea un nuevo libro
            Libro l = new Libro();
            l.setId(Integer.parseInt(idLibro.getText()));
            l.setTitulo(tituloLibro.getText());
            l.setAutor(autorLibro.getText());
            l.setEditorial(editorialLibro.getText());
            l.setAñoPublicacion(Integer.parseInt(añoLibro.getText()));
            l.setPaginas(Integer.parseInt(paginasLibro.getText()));

            //Añade el libro a la biblioteca
            if(biblioteca.crearLibro(l.getId(), l) > 0){
                Alert conf = new Alert(Alert.AlertType.CONFIRMATION, "Libro creado correctamente");
                conf.showAndWait();
                limpiar();
            }
        
        }
        
    }

    @FXML
    private void actualizarLibro(ActionEvent event) {
        
        //Comprueba que el id esté introducido
        if(!idLibro.getText().isEmpty()){
            Libro l = biblioteca.buscarLibro(Integer.parseInt(idLibro.getText()));
            
            //Comprueba que el libro exista
            if(l == null){
                Alert error = new Alert(Alert.AlertType.INFORMATION, "Ese libro no existe o no se ha encontrado"); 
                error.showAndWait();
            }else{
        
                if(!nuevaIDLibro.getText().isEmpty())
                    l.setId(Integer.parseInt(nuevaIDLibro.getText()));
                if(!tituloLibro.getText().isEmpty())
                    l.setTitulo(tituloLibro.getText());
                if(!autorLibro.getText().isEmpty())
                    l.setAutor(autorLibro.getText());
                if(!editorialLibro.getText().isEmpty())
                    l.setEditorial(editorialLibro.getText());
                if(!añoLibro.getText().isEmpty())
                    l.setAñoPublicacion(Integer.parseInt(añoLibro.getText()));
                if(!paginasLibro.getText().isEmpty())
                    l.setPaginas(Integer.parseInt(paginasLibro.getText()));
                
                //Edita el libros
                biblioteca.editarLibro(Integer.parseInt(idLibro.getText()), l);
                
                Alert actualizar = new Alert(Alert.AlertType.INFORMATION, "Libro actualizado correctamente");
                actualizar.showAndWait();
                limpiar();
            }
      
        }else{
            Alert libroAlert = new Alert(Alert.AlertType.ERROR, "No has introducido ningún campo");
            libroAlert.showAndWait();
        }
               
    }

    @FXML
    private void eliminarLibro(ActionEvent event) {
        
        //Elimina el libro
        if(!idLibro.getText().isEmpty()){
            if(biblioteca.eliminarLibro(Integer.parseInt(idLibro.getText())) == 1){
                Alert eliminar = new Alert(Alert.AlertType.INFORMATION, "Libro eliminado correctamente");
                eliminar.showAndWait();
                limpiar();
            }else{
                Alert error = new Alert(Alert.AlertType.ERROR, "Ese libro no existe o no se ha encontrado"); 
                error.showAndWait();
            }
        
        }
        
    }

    @FXML
    private void guardarXML(ActionEvent event) {
        
        //Guarda el xml
        if(XMLSalida.getText().isEmpty()){
            Alert file = new Alert(Alert.AlertType.ERROR, "No has introducido el fichero de salida");
            file.showAndWait();
        }else if(biblioteca != null){
            salidaText.setText(biblioteca.guardarXML(XMLSalida.getText()));
        }
    }
    
     private void limpiar(){
        
        //limpia el texto
        tituloLibro.setText("");
        autorLibro.setText("");
        editorialLibro.setText("");
        añoLibro.setText("");
        paginasLibro.setText("");
        
    }
    
     
}
