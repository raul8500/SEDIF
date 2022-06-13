package vistasestudiante;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLStatusProcedureController implements Initializable {

    @FXML
    private Button btnLogout;
    @FXML
    private Button clicBtnModify;
    @FXML
    private ImageView imgDoc;
    @FXML
    private Label lbMod;
    @FXML
    private ImageView imgSelect;
    @FXML
    private Button btnSelect;
    @FXML
    private Label lbStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //status 0 = en revision
        //status 1 = aprobada
        //status 2 = regresada con errores
        int status = 0;
        if(status == 1){
            imgDoc.setVisible(false);
            btnSelect.setVisible(false);
            imgSelect.setVisible(false);
            lbMod.setVisible(false);
            clicBtnModify.setVisible(false);
            clicBtnModify.setDisable(true);
        }
    }    

    @FXML
    private void clicBtnLogout(ActionEvent event) {
        Stage stage = (Stage) imgDoc.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clicBtnStatus(ActionEvent event) {
    }

    @FXML
    private void clicBtnSelect(ActionEvent event) {
    }
}
