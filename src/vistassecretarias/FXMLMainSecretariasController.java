/******************************************************************/
/* Archivo:     FXMLMainSecretariesController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	17-06-2022	*/
/* Descripción:	 Carga la ventana FXMLMainSecretaries.fxml ventana principal de las secretarias
*/
/*******************************************************/

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
import pojo.Secretarie;
import sedif.FXMLLoginController;


public class FXMLMainSecretariasController implements Initializable {

    @FXML
    private Button btnClose;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Secretarie newSecretarie = FXMLLoginController.newSecretarie;
        System.out.println(newSecretarie.toString());
    }    

    @FXML
    private void clicBtnProcedures(ActionEvent event) {
        changeWindow("FXMLSecretariesProcedures.fxml");
    }

    @FXML
    private void clicBtnQuestions(ActionEvent event) {
        changeWindow("FXMLAnswer.fxml");
    }

    @FXML
    private void clicBtnClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) btnClose.getScene().getWindow();
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
