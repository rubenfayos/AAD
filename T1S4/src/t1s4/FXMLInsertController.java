/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package t1s4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rubén Fayos
 */
public class FXMLInsertController implements Initializable {

    private Model model;
    @FXML
    private TextField nombreText;
    @FXML
    private TextField edadText;
    @FXML
    private TextField alturaText;
    @FXML
    private TextField pesoText;
    @FXML
    private ComboBox<String> SexoComboBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        this.model.Conexion();

        SexoComboBox.getItems().add("Hombre");
        SexoComboBox.getItems().add("Mujer");
        
        
    }

    @FXML
    private void Insert(ActionEvent event) {
        
        
        
        
        try{
            Persona p = new Persona();
            p.setNombre(nombreText.getText());
       
            p.setEdad(Integer.parseInt(edadText.getText()));
       
            p.setPeso(Double.parseDouble(pesoText.getText()));
            
            String sexo = SexoComboBox.getValue();
            if(sexo.equals("Hombre"))
                p.setSexo('H');
            if(sexo.equals("Mujer"))
                 p.setSexo('M');
            
            p.setAltura(Double.parseDouble(alturaText.getText()));
        
            if(this.model.Insert(p) > 0){
                Alert insertConf = new Alert(Alert.AlertType.INFORMATION, "Persona añadida satisfactoriamente");
                insertConf.showAndWait();
            }
        }catch(NumberFormatException e){
            Alert error = new Alert(Alert.AlertType.ERROR, "Falta algún campo por introducir o no es correcto");
            error.showAndWait();
        }
        
    }

    
    
}
