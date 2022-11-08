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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
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
    private TableColumn<Libro, Integer> añoNacimientoColumn;
    @FXML
    private TableColumn<Libro, Integer> añoPublicacionColumn;
    @FXML
    private TableColumn<Libro, String> editorialColumn;
    @FXML
    private TableColumn<Libro, Integer> paginasColumn;
    @FXML
    private TextField consultaManualText;
    @FXML
    private ChoiceBox<String> campoComboBox;
    @FXML
    private CheckBox checkboxIgual;
    @FXML
    private CheckBox checkboxMenor;
    @FXML
    private CheckBox checkboxMayor;
    @FXML
    private TextField consultaText;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        this.model = new Model();
        
        campoComboBox.getItems().add("titulo");
        campoComboBox.getItems().add("autor");
        campoComboBox.getItems().add("añoPublicacion");
        campoComboBox.getItems().add("añoNacimiento");
        campoComboBox.getItems().add("editorial");
        campoComboBox.getItems().add("paginas");
        
        EventHandler<ActionEvent> event =
                  new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                checkboxIgual.setVisible(true);
                
                if(campoComboBox.getValue().equals("titulo") || campoComboBox.getValue().equals("autor") || campoComboBox.getValue().equals("editorial")){
                    checkboxMenor.setVisible(false);
                    checkboxMayor.setVisible(false);
                }else{
                    checkboxMenor.setVisible(true);
                    checkboxMayor.setVisible(true);
                    
                }
            }
        };
        
        // Set on action
       campoComboBox.setOnAction(event);
        
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
  
                public void handle(ActionEvent e)
                {
                    if (checkboxMayor.isSelected()){
                        checkboxIgual.setSelected(false);
                        checkboxMenor.setSelected(false);
                        
                    }else if(checkboxIgual.isSelected()){
                        
                        checkboxMayor.setSelected(false);
                        checkboxMenor.setSelected(false); 
                        
                    }else if(checkboxMenor.isSelected()){
                        checkboxMayor.setSelected(false);
                        checkboxIgual.setSelected(false);
                    }
                        
                }
  
            };
        
        checkboxIgual.setOnAction(event2);
        checkboxMayor.setOnAction(event2);
        checkboxMenor.setOnAction(event2);
        
        
        tituloColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));
        añoNacimientoColumn.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("añoNacimiento"));
        añoPublicacionColumn.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("añoPublicacion"));
        editorialColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("editorial"));
        paginasColumn.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("paginas"));
        
         if(!this.model.Conexion()){
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

    @FXML
    private void consultaEstandar(ActionEvent event) {
        
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        
        /*
        String consulta = "SELECT * FROM libro WHERE " + campoComboBox.getValue() + " " + 
                + " " + consultaText+";";
        
        */
        
    }

    
}
