/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1aev4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Rubén Fayos
 */
public class FXMLDocumentController implements Initializable {
    
     private Model model;
    
    private Label label;
    @FXML
    private TextField rutaText;
    @FXML
    private TableView<Libro> table;
    @FXML
    private TableColumn<Libro, String> tituloColumn;
    @FXML
    private TableColumn<Libro, String> autorColumn;
    @FXML
    private TableColumn<Libro, String> añoNacimientoColumn;
    @FXML
    private TableColumn<Libro, String> añoPublicacionColumn;
    @FXML
    private TableColumn<Libro, String> editorialColumn;
    @FXML
    private TableColumn<Libro, String> paginasColumn;
    @FXML
    private TextField consultaManualText;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        this.model = new Model();   
        
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        añoNacimientoColumn.setCellValueFactory(new PropertyValueFactory<>("añoNacimiento"));
        añoPublicacionColumn.setCellValueFactory(new PropertyValueFactory<>("añoPublicacion"));
        editorialColumn.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        paginasColumn.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        
        if(this.model.Conexion()){
           if(this.model.creacionBDD() != 0){
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Base de datos creada");
               a.showAndWait();
           }
        }else{
            Alert conectionError = new Alert(Alert.AlertType.ERROR, "Error en la base de datos");
            conectionError.showAndWait();
        }
         
    }    

    @FXML
    private void read(ActionEvent event) {
        
        if(this.model.leerCsv(rutaText.getText())){
            Alert conection = new Alert(Alert.AlertType.INFORMATION, "Se ha leido el fichero correctamente");
            conection.showAndWait();
        }else{
            Alert conectionError = new Alert(Alert.AlertType.ERROR, "No se ha podido leer el fichero");
            conectionError.showAndWait();
        }
        
    }

    @FXML
    private void consulta(ActionEvent event) {
        
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        libros = this.model.consulta();
        
        table.setItems(libros);
        
    }


    @FXML
    private void consultaManual(ActionEvent event) {
        
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        if(!consultaManualText.getText().isEmpty()){
            libros = this.model.consultaManual(consultaManualText.getText());
            if(libros.size() > 0)
                table.setItems(libros);
            else{
                Alert consultaError = new Alert(Alert.AlertType.ERROR, "No se ha encontrado ninguna coincidencia");
                consultaError.showAndWait();
            }
        }
        else{
            Alert consultaError = new Alert(Alert.AlertType.ERROR, "No has introducido ninguna consulta");
            consultaError.showAndWait();
        }
        
    }

    
}
