/******************************************************************/
/* Archivo:     FXMLLoginController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	10-06-2021	*/
/* Descripción:	 Carga la ventana FXMLLogin.fxml con los campos de Login
/*******************************************************/
package sedif.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {

    @FXML
    private PasswordField pfPassword;
    @FXML
    private TextField tfUser;
    @FXML
    private Label lbErrorUser;
    @FXML
    private Label lbErrorPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnLogin(ActionEvent event) {
        lbErrorUser.setText("");
        lbErrorPassword.setText("");
       
        String txtUsuario = tfUser.getText();
        String txtPassword = pfPassword.getText();
        boolean isTrue = true;
       
        if(txtUsuario.isEmpty()){
            lbErrorUser.setText("Campo usuario requerido");
            isTrue = false;
        }
       
        if(txtPassword.isEmpty()){
            lbErrorPassword.setText("Campo contraseña requerido");
            isTrue = false;
        }
        if(isTrue) {
            verificateUserType();
        }
    }
    
    private void goMainWindow(String window){
        try {
            Stage stage = (Stage) tfUser.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource(window)));
            stage.setScene(scenePrincipal);
            stage.setTitle("SEDIF");
            stage.getIcons().add(new Image("img/LOGOUV.png"));
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void clicBtnRecoverPass(ActionEvent event) {
        showAlert("Recuperar Contraseña","Para recuperar tu contraseña es necesario que te pongas en "
                + "contacto con tu secretaria académica correspondiente.",Alert.AlertType.INFORMATION);
    }
    
    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void clicBtnExit(ActionEvent event) {
        Stage stage = (Stage) tfUser.getScene().getWindow();
        stage.close();
    }
    
    public void verificateStatusStudent(){
        //se hace la verificacion para saber que trmite esta haciendo el estudiante
        //regresa un numero segun el tramite
        
        int tramite = 1;
        
        if (tramite == 0){
            goMainWindow("FXMLMain.fxml");
        }else if (tramite == 1){
            goMainWindow("FXMLMainStatus.fxml");
        } 
    }
    
    public void verificateUserType(){
        //hace la verificacion para saber si es estudiante o secretaria;
        
        //0 estudiante
        //1 secretaria
        int userType = 0;
        
        if (userType == 0){
            verificateStatusStudent();
        }else{
            goMainWindow("FXMLMainSecretaries.fxml");
        }
    }
    
}
