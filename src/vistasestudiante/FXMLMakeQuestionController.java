/******************************************************************/
/* Archivo:     FXMLMAkeQuestionController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	17-06-2022	*/
/* Descripción:	 Carga la ventana FXMLMAkeQuestion.fxml con los campos para enviar una pregunta a las secretarias
*/
/*******************************************************/
package vistasestudiante;

import Servicio.ServiceProcedure;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONException;
import pojo.Student;
import sedif.FXMLLoginController;
import static sedif.FXMLLoginController.procedure;

public class FXMLMakeQuestionController implements Initializable {

    @FXML
    private Label lbDocName;
    String route;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void loadDocs(Label label, String route){
        Stage stage = (Stage) lbDocName.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.docx", "*.doc", "*.pdf"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        
        File selectedFile = fileChooser.showOpenDialog(stage);
        
        if (selectedFile != null) {
           String nameDoc = selectedFile.getName();
           route = selectedFile.getAbsolutePath();
           label.setText(nameDoc);
           System.out.println(nameDoc + "  ==  " + route);
        }
    }

    @FXML
    private void clicBtnAttach(ActionEvent event) {
        loadDocs(lbDocName, route);
    }

    @FXML
    private void clicBtnSend(ActionEvent event) {
    }

    @FXML
    private void clicBtnBack(ActionEvent event) throws JSONException {
        Student newStudent = FXMLLoginController.newStudent;
        
        ServiceProcedure serviceProcedure = new ServiceProcedure();
        procedure = serviceProcedure.getStatusProcedure(newStudent.getToken());
        
        if (procedure.isError()){
            showAlert("Error", "Error en el servidor", Alert.AlertType.ERROR);
        }else{
            if (procedure.getStatus()==0){
                changeWindow("/vistasestudiante/FXMLMainEstudiante.fxml");
            }else if (procedure.getStatus()==1){
                changeWindow("/vistasestudiante/FXMLMainStatus.fxml");
            } 
        }
    }
    
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) lbDocName.getScene().getWindow();
            Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource(window)));
            stage.setScene(mainScene);
            stage.setTitle("SEDIF");
            stage.getIcons().add(new Image("img/LOGOUV.png"));
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
