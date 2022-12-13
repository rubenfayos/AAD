/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package hibernateejercicios;

import hibernateejercicios.Maravilla;
import hibernateejercicios.Model;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Ruben Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    private Model model;
    
    private Label label;
    @FXML
    private TableColumn<Maravilla, Integer> idColumn;
    @FXML
    private TableColumn<Maravilla, String> nombreColumn;
    @FXML
    private TableColumn<Maravilla, String> paisColumn;
    @FXML
    private TextArea descripcionText;
    @FXML
    private TableView<Maravilla> maravillasTable;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        model = new Model();
        if(model.Conexion()){
            
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            paisColumn.setCellValueFactory(new PropertyValueFactory<>("pais"));
            
        }else{
            Alert conError = new Alert(Alert.AlertType.ERROR, "Error en la conexion");
            conError.showAndWait();
        }
        
        
        
    }    

    @FXML
    private void Select(ActionEvent event) {
        
        List<Maravilla> maravillas = new ArrayList<>();
        maravillas = model.listarMaravillas();
        
        //Crea el observablelist a partir de la lista
        ObservableList<Maravilla> maravillasOL = FXCollections.observableArrayList(maravillas);
        maravillasTable.setItems(maravillasOL);
        
    }
    
}
