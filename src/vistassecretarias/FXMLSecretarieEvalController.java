
package vistassecretarias;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pojo.ProcedureInfo;


public class FXMLSecretarieEvalController implements Initializable {

    @FXML
    private RadioButton rbValidate;
    @FXML
    private RadioButton rbFeedback;
    final ToggleGroup group = new ToggleGroup();
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbName;
    @FXML
    private Label lbCarrer;
    @FXML
    private Label lbProcedure;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rbValidate.setToggleGroup(group);
        rbFeedback.setToggleGroup(group);
        ProcedureInfo pf = FXMLSecretariesProceduresController.personInfo;
        lbMatricula.setText(pf.getMatricula());
        lbName.setText(pf.getName()+","+pf.getLastname());
        lbCarrer.setText(pf.getCarrer());
        lbProcedure.setText(pf.getNameProcedure());
    }

    @FXML
    private void clicBtnSend(ActionEvent event) {
        if (rbValidate.isSelected()){
            System.out.println("Validado");
        }else if(rbFeedback.isSelected()) {
            System.out.println("Feedback");
        }else{
            System.out.println("No selecciono nada");
        }
    }

    @FXML
    private void clicBtnBack(ActionEvent event) {
        changeWindow("FXMLSecretariesProcedures.fxml");
    }
    
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) rbValidate.getScene().getWindow();
            Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource(window)));
            stage.setScene(mainScene);
            stage.setTitle("SEDIF");
            stage.getIcons().add(new Image("img/LOGOUV.png"));
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
