/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package hibernateejercicios;

import hibernateejercicios.Maravilla;
import hibernateejercicios.Model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ruben Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    private Model model;
    private Maravilla maravillaSeleccionada;
    
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
    @FXML
    private TextField nombreInsert;
    @FXML
    private TextField paisInsert;
    @FXML
    private TextField descripcionInsert;
    @FXML
    private Pane ButtonsPane;

    public Maravilla getMaravillaSeleccionada() {
        return maravillaSeleccionada;
    }

    public void setMaravillaSeleccionada(Maravilla maravillaSeleccionada) {
        this.maravillaSeleccionada = maravillaSeleccionada;
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
        
        maravillasTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {

                Maravilla m = maravillasTable.getSelectionModel().getSelectedItem();

                //Si el item seleccionado != null
                if(m != null) {    

                    setMaravillaSeleccionada(m);
                    ButtonsPane.setVisible(true);
                    descripcionText.setText(m.getDescripcion());
                }else{
                    ButtonsPane.setVisible(false);
                }

            }
        
        });
        
        
        
    }    

    @FXML
    private void Select(ActionEvent event) {
        
        List<Maravilla> maravillas = new ArrayList<>();
        maravillas = model.listarMaravillas();
        
        //Crea el observablelist a partir de la lista
        ObservableList<Maravilla> maravillasOL = FXCollections.observableArrayList(maravillas);
        maravillasTable.setItems(maravillasOL);
        
    }

    @FXML
    private void Insert(ActionEvent event) {
        
        if(nombreInsert.getText().isEmpty() || paisInsert.getText().isEmpty() || descripcionInsert.getText().isEmpty()){
            
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Se ha insertado correctamente");
            a.showAndWait();
            
        }else{
                
        Maravilla nuevaMaravilla = new Maravilla();
        nuevaMaravilla.setNombre(nombreInsert.getText());
        nuevaMaravilla.setPais(paisInsert.getText());
        nuevaMaravilla.setDescripcion(descripcionInsert.getText());
        
        model.insertMaravilla(nuevaMaravilla);
        
        nombreInsert.setText("");
        paisInsert.setText("");
        descripcionInsert.setText("");
        Select(new ActionEvent());
                
        }
        
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
        
        //Creo un objeto para así poder pasar el objeto en el constructor
        //El Controller crea un nuevo pane en vez de crearse desde aquí
        FXMLUpdateController controller = new FXMLUpdateController(maravillaSeleccionada);
        controller.showStage();
        Select(new ActionEvent());
    }

    @FXML
    private void Delete(ActionEvent event) {
        
        Alert confAlert = new Alert(Alert.AlertType.CONFIRMATION, "Seguro quieres eliminar la maravilla con el id: " + maravillaSeleccionada.getId());
        confAlert.showAndWait();
        
        model.deleteMaravilla(maravillaSeleccionada);
        Select(new ActionEvent());
    }
    
}
