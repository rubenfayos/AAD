/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication42;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ruben Fayos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField nombreText;
    @FXML
    private TextField apellidosText;
    @FXML
    private TextField incidenciaText;
    @FXML
    private TextField idText;
    
    private ArrayList<Incidencia> incidencias;
    
    private int posicion = 0;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        incidencias = new ArrayList<Incidencia>();
        
        // TODO
    }    

    @FXML
    private void configuracionBoton(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLConfiguracion.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void anteriorBoton(ActionEvent event) {
        
        posicion -= 1;
        
        
        Incidencia ini = incidencias.get(posicion);
        
        idText.setText(String.valueOf(ini.getId()));
        nombreText.setText(ini.getNombre());
        apellidosText.setText(ini.getApellidos());
        incidenciaText.setText(ini.getIncidencia());
        
    }

    @FXML
    private void posteriorBoton(ActionEvent event) {
        
        
        if(idText.getText().isEmpty()){
            
            incidencias.remove(posicion);
            
        }else if (incidencias.size() > posicion){
            
                Incidencia ini = new Incidencia();
            
                ini.setId(Integer.parseInt(idText.getText()));
                ini.setNombre(nombreText.getText());
                ini.setApellidos(apellidosText.getText());
                ini.setIncidencia(incidenciaText.getText());

                incidencias.add(ini);
                
                idText.setText("");
                nombreText.setText("");
                apellidosText.setText("");
                incidenciaText.setText("");
                
        }
        
        posicion++;

        if(incidencias.size() > posicion+1){
            
            Incidencia ini = incidencias.get(posicion);
        
            idText.setText(String.valueOf(ini.getId()));
            nombreText.setText(ini.getNombre());
            apellidosText.setText(ini.getApellidos());
            incidenciaText.setText(ini.getIncidencia());
            
        }
        
    }
    
}
