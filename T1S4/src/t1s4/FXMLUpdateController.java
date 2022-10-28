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
public class FXMLUpdateController implements Initializable {

    @FXML
    private TextField nombreText;
    @FXML
    private TextField edadText;
    @FXML
    private TextField alturaText;
    @FXML
    private TextField pesoText;
    @FXML
    private TextField idText;
    
    private Model model;
    @FXML
    private ComboBox<String> sexoComboBox;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.model = new Model();
        this.model.Conexion();
        
        sexoComboBox.getItems().add("Hombre");
        sexoComboBox.getItems().add("Mujer");
    }    

    @FXML
    private void Update(ActionEvent event) {
        
        if(!idText.getText().isEmpty()){
            
            Persona p = this.model.FindPersona(Integer.parseInt(idText.getText()));
            
            if(p != null){
                
                try{
                
                    if(!nombreText.getText().isEmpty())
                        p.setNombre(nombreText.getText());

                    if(!alturaText.getText().isEmpty())
                        p.setAltura(Double.parseDouble(alturaText.getText()));

                    if(!pesoText.getText().isEmpty())
                        p.setPeso(Double.parseDouble(pesoText.getText()));

                    if(!edadText.getText().isEmpty())
                        p.setEdad(Integer.parseInt(edadText.getText()));

                    String sexo = sexoComboBox.getValue();

                    if(sexo != null){
                        if(sexo.equals("Hombre"))
                            p.setSexo('H');
                        if(sexo.equals("Mujer"))
                            p.setSexo('M');
                    }

                    if(this.model.Update(p, Integer.parseInt(idText.getText())) > 0){
                        Alert conf = new Alert(Alert.AlertType.CONFIRMATION, "Persona actualizada correctamente");
                        conf.showAndWait();
                    }
                
                }catch(Exception e){
                    Alert error = new Alert(Alert.AlertType.ERROR, "Algún campo es incorrecto");
                    error.showAndWait();
                }
                
            }else{
                Alert error = new Alert(Alert.AlertType.ERROR, "Esa persona no existe");
                error.showAndWait();
            }
              
        }
        
    }
    
}
