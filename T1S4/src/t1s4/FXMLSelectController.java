/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package t1s4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Rubén Fayos
 */
public class FXMLSelectController implements Initializable {
    
    
    private Model model;
    private TextArea salidaText;
    @FXML
    private AnchorPane scene;
    @FXML
    private TableView<Persona> table;
    @FXML
    private TableColumn<Persona, String> nombreColumn;
    @FXML
    private TableColumn<Persona, Character> sexoColumn;
    @FXML
    private TableColumn<Persona, Integer> edadColumn;
    @FXML
    private TableColumn<Persona, Double> alturaColumn;
    @FXML
    private TableColumn<Persona, Double> pesoColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        model = new Model();
        //Comprobación de conexión con la base de datos
        if(model.Conexion() == 1){
            Alert sqlConn = new Alert(Alert.AlertType.INFORMATION, "Conexión existosa con la base de datos");
            sqlConn.show();
            
            nombreColumn.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
            sexoColumn.setCellValueFactory(new PropertyValueFactory<Persona, Character>("sexo"));
            edadColumn.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
            alturaColumn.setCellValueFactory(new PropertyValueFactory<Persona, Double>("altura"));
            pesoColumn.setCellValueFactory(new PropertyValueFactory<Persona, Double>("peso"));
        
            Consulta();
        }else{
            Alert sqlError = new Alert(Alert.AlertType.ERROR, "Error con la base de datos");
            sqlError.show();
        }
         
    } 
    
    public void Consulta(){
        
        ObservableList<Persona> personas = FXCollections.observableArrayList();
        personas = this.model.consulta();
        
        table.setItems(personas);
        
    }

    private void a(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLInsert.fxml"));
        this.scene.getChildren().setAll(pane);
    }

    @FXML
    private void refrescar(ActionEvent event) {
        
        Consulta();
        
    }
    
}
