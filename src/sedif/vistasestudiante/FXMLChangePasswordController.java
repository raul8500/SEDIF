/******************************************************************/
/* Archivo:     FXMLChangePasswordController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	10-06-2021	*/
/* Descripción:	 Carga la ventana FXMLChangePasswordController.fxml con los campos de change password
/*******************************************************/
package sedif.vistasestudiante;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FXMLChangePasswordController implements Initializable {

    @FXML
    private PasswordField pfChangePass;
    @FXML
    private PasswordField pfConfirmPass;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicBtnChangePass(ActionEvent event) {
        //validacion del cambio de contraseña
        goMainWindow();
        
    }
    
    private void goMainWindow(){
        try {
            Stage stage = (Stage) pfChangePass.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("FXMLMain.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("SEDIF");
            stage.getIcons().add(new Image("img/LOGOUV.png"));
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
