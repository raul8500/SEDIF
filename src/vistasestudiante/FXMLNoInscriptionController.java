/******************************************************************/
/* Archivo:     FXMLNoInscriptionController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	17-06-2022	*/
/* Descripción:	 Carga la ventana FXMLNoInscription.fxml para poder realizar el tramite no inscripcion
/*******************************************************/

package vistasestudiante;

import Servicio.ServiceProcedure;
import Servicio.ServiceUpload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONException;
import pojo.FilePath;
import sedif.FXMLLoginController;

public class FXMLNoInscriptionController implements Initializable {

    @FXML
    private Button btnHelp;
    String route;
    @FXML
    private Label lbLoadDoc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clicBtnLoadDoc(ActionEvent event) {
        Stage stage = (Stage) btnHelp.getScene().getWindow();
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
           lbLoadDoc.setText(nameDoc);
           System.out.println(nameDoc + "  ==  " + route);
        }
    }

    @FXML
    private void clicBtnBack(ActionEvent event) {
        changeWindow("FXMLMainStudent.fxml");
    }

    @FXML
     private void clicBtnSendProcedure(ActionEvent event) {
         
        if (route.equals("")){
            showAlert("Envio de Documentos", "No has seleccionado todos los documentos", Alert.AlertType.INFORMATION);
        }else{
            String token = FXMLLoginController.newStudent.getToken();
            ServiceProcedure sd = new ServiceProcedure();
            ServiceUpload su = new ServiceUpload();
            
            
            try {
                ArrayList<FilePath> filePaths = sd.setStatusProcedureInscription(token);
                for(int i=0;i<filePaths.toArray().length;i++){
                   int type = filePaths.get(i).getType(); 
                   String path = filePaths.get(i).getBdPath();
                   su.uploadFile(route,path);
                }
            } catch (JSONException ex) {
                Logger.getLogger(FXMLSendDocWithoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
            showAlert("Envio de Documentos", "El envio fue correcto la aplicacion se reiniciara para ver los cambios", Alert.AlertType.INFORMATION);
            changeWindow("FXMLMainStatus.fxml");
        }
    }

    @FXML
    private void clicBtnSendDocWithout(ActionEvent event) {
        changeWindow("FXMLSendDocWithout.fxml");
    }

    @FXML
    private void clicBtnSendDocWith(ActionEvent event) {
        changeWindow("FXMLSendDocWith.fxml");
    }

    @FXML
    private void clicBtnHelp(ActionEvent event) {
        changeWindow("FXMLMakeQuestion.fxml");
    }
    
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) btnHelp.getScene().getWindow();
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
