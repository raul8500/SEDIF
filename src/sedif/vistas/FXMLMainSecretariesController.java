/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sedif.vistas;

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

public class FXMLMainSecretariesController implements Initializable {

    @FXML
    private Button btnLogout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clicBtnProcedures(ActionEvent event) {
        changeWindow("");
    }

    @FXML
    private void clicBtnQuestions(ActionEvent event) {
        changeWindow("");
    }

    @FXML
    private void clicBtnLogout(ActionEvent event) {
        changeWindow("FXMLLogin.fxml");
    }
    
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
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
