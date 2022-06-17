/******************************************************************/
/* Archivo:     FXMLLoginController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	10-06-2021	*/
/* Descripción:	 Carga la ventana FXMLLogin.fxml con los campos de Login
/*******************************************************/
package sedif;

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
import Servicio.ServiceLogin;
import Servicio.ServiceProcedure;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import pojo.Procedure;
import pojo.Secretarie;
import pojo.User;
import pojo.Student;

public class FXMLLoginController implements Initializable {
    public static Student newStudent;
    public static Secretarie newSecretarie;
    public static Procedure procedure;
    
    
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
            verificateUserType(txtUsuario,txtPassword);
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
    
    public void verificateStatusStudent(Student newStudent) throws JSONException{
        ServiceProcedure serviceProcedure = new ServiceProcedure();
        procedure = serviceProcedure.getStatusProcedure(newStudent.getToken());
        
        if (procedure.isError()){
            showAlert("Error", "Error en el servidor", Alert.AlertType.ERROR);
        }else{
            
            if (procedure.getStatus()==0){
                goMainWindow("/vistasestudiante/FXMLMainEstudiante.fxml");
            }else if (procedure.getStatus()==1){
                goMainWindow("/vistasestudiante/FXMLMainStatus.fxml");
            } 
        }
    }
    
    public void verificateUserType(String usuario, String password){
        //hace la verificacion para saber si es estudiante o secretaria;
        
        //0 estudiante
        //1 secretaria
        try{
            ServiceLogin sc = new ServiceLogin();
            User newUser = sc.Autenticar(usuario,password);

            if (newUser.isIsLogued()){
                JSONObject userInfo = newUser.getUserInfo();
                int userType = userInfo.getInt("rol");

                if (userType == 0){
                    String token = newUser.getToken();
                    String name = userInfo.getString("nombres");
                    String lastName = userInfo.getString("apellidos");
                    String carrer = userInfo.getString("Carrera");
                    String nameRol = userInfo.getString("nombreRol");
                    newStudent = new Student(name,lastName,carrer,userType,nameRol,token);
                    verificateStatusStudent(newStudent);
                }else{
                    String token = newUser.getToken();
                    String name = userInfo.getString("nombres");
                    String lastName = userInfo.getString("apellidos");
                    String nameRol = userInfo.getString("nombreRol");
                    newSecretarie = new Secretarie(userType,name,lastName,nameRol,token);
                    goMainWindow ("/vistassecretarias/FXMLMainSecretarias.fxml");
                }
            }else{
                showAlert("Credenciales incorrectas", "No existe un usuario con las credenciales proporcionadas", Alert.AlertType.ERROR);
            }
        }catch (JSONException ex) {
            Logger.getLogger(ServiceLogin.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
