/******************************************************************/
/* Archivo:     FXMLMainStatusController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	17-06-2022	*/
/* Descripción:	 Controlador de la ventana FXMLMainStatus.fxml
*/
/*******************************************************/

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
    private Button btnExit;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Procedure prc = FXMLLoginController.procedure;
        //System.out.println(prc.toString());
    }

    @FXML
    private void clicBtnStatus(ActionEvent event) {
        chageWindow("FXMLStatusProcedure.fxml");
    }

    @FXML
    private void clicBtnExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void clicBtnHelp(ActionEvent event) {
        chageWindow("FXMLMakeQuestion.fxml");
    }
    
    private void chageWindow(String window){
        try {
            Stage stage = (Stage) btnExit.getScene().getWindow();
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
