/******************************************************************/
/* Archivo:     FXMLMainStudentController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	17-06-2022	*/
/* Descripción:	 Controlador de la ventana FXMLMainStudent.fxml
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pojo.Student;
import sedif.FXMLLoginController;

public class FXMLMainStudentController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private ImageView imgMain;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicBtnExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clicBtnHelp(ActionEvent event) {
        changeWindow("FXMLMakeQuestion.fxml");
    }

    @FXML
    private void clicBtnSendWithout(ActionEvent event) {
        changeWindow("FXMLSendDocWithout.fxml");
    }

    @FXML
    private void clicBtnSendDocWith(ActionEvent event) {
        changeWindow("FXMLSendDocWith.fxml");
    }

    @FXML
    private void clicBtnNoInscription(ActionEvent event) {
        changeWindow("FXMLNoInscription.fxml");
    }
    
    private void changeWindow(String window){
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

    @FXML
    private void clicBtnFormats(ActionEvent event) {
        changeWindow("FXMLDownloadFiles.fxml");
    }
}
