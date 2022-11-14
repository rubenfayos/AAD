/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1aev5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLSelectController implements Initializable {

    @FXML
    private AnchorPane scene;
    @FXML
    private TableView<Libro> table;
    @FXML
    private TableColumn<Libro, String> nombreColumn;
    @FXML
    private TableColumn<Libro, String> sexoColumn;
    @FXML
    private TableColumn<Libro, String> edadColumn;
    @FXML
    private TableColumn<Libro, String> alturaColumn;
    @FXML
    private TableColumn<Libro, String> pesoColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void refrescar(ActionEvent event) {
    }
    
}
