/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistassecretarias;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author peres
 */
public class FXMLAnswerController implements Initializable {

    @FXML
    private Button btnExit;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnExit(ActionEvent event) {
        goMainWindow("FXMLMainSecretarias.fxml");
    }
    
    private void goMainWindow(String window){
        try {
            Stage stage = (Stage) btnExit.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource(window)));
            stage.setScene(scenePrincipal);
            stage.setTitle("SEDIF");
            stage.getIcons().add(new Image("img/LOGOUV.png"));
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
