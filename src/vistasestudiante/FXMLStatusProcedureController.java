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
import pojo.Procedure;
import sedif.FXMLLoginController;

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
        Procedure prc = FXMLLoginController.procedure;
        //status = 0 no iniciado
        //1 = en espera de evaluacion;
        //2 requiere cambios
        //3 valido a mimir;
        int status = prc.getStatus();
        
        switch (status) {
            case 1:
                imgDoc.setVisible(false);
                btnSelect.setVisible(false);
                imgSelect.setVisible(false);
                lbMod.setVisible(false);
                clicBtnModify.setVisible(false);
                clicBtnModify.setDisable(true);
                lbStatus.setText("En espera de evaluacion");
                break;
            case 2:
                imgDoc.setVisible(true);
                btnSelect.setVisible(true);
                imgSelect.setVisible(true);
                lbMod.setVisible(true);
                clicBtnModify.setVisible(true);
                clicBtnModify.setDisable(false);
                lbStatus.setText("Requiere realizar cambios");
                break;
            case 3:
                imgDoc.setVisible(false);
                btnSelect.setVisible(false);
                imgSelect.setVisible(false);
                lbMod.setVisible(false);
                clicBtnModify.setVisible(false);
                clicBtnModify.setDisable(true);
                lbStatus.setText("Aprobada");
                break;
            default:
                break;
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
