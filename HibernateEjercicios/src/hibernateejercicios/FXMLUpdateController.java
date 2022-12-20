/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hibernateejercicios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ruben Fayos
 */
public class FXMLUpdateController implements Initializable {

    @FXML
    private TextField nombreText;
    @FXML
    private TextField paisText;
    @FXML
    private TextArea descripcionText;
    
    private Maravilla m;
    
    private Stage st;
    
    private Model model = new Model();

    /**
     * Initializes the controller class.
     */
    
    
    
    public FXMLUpdateController(Maravilla m) {
        
        this.m = m;
        
       // Create the new stage
       st = new Stage();

        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLUpdate.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            st.setScene(new Scene(loader.load()));


        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        model = new Model();
        model.Conexion();
        nombreText.setText(m.getNombre());
        paisText.setText(m.getPais());
        descripcionText.setText(m.getDescripcion());
        
    }  

    @FXML
    private void update(ActionEvent event) {
        
        if(nombreText.getText().isEmpty() || paisText.getText().isEmpty() || descripcionText.getText().isEmpty()){
            
            Alert a = new Alert(Alert.AlertType.ERROR, "Faltan datos por introducir");
            a.showAndWait();
            
        }else{
            
            m.setNombre(nombreText.getText());
            m.setPais(paisText.getText());
            m.setDescripcion(descripcionText.getText());
            model.updateMaravilla(m);
            st.close();
        }
                 
    }
    
     public void showStage() {
         
        st.showAndWait();
        
    }
      
}
