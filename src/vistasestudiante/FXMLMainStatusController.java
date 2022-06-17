package vistasestudiante;

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
import pojo.Procedure;
import sedif.FXMLLoginController;

public class FXMLMainStatusController implements Initializable {

    @FXML
    private Button btnLogout;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Procedure prc = FXMLLoginController.procedure;
        System.out.println(prc.toString());
    }

    @FXML
    private void clicBtnStatus(ActionEvent event) {
        /// optiene el status antes para realizar la carga de componetes de venatan
        
        chageWindow("FXMLStatusProcedure.fxml");
    }

    @FXML
    private void clicBtnLogout(ActionEvent event) {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void clicBtnHelp(ActionEvent event) {
        chageWindow("FXMLMakeQuestion.fxml");
    }
    
    private void chageWindow(String window){
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
