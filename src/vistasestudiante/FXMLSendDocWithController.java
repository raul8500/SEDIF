/******************************************************************/
/* Archivo:     FXMLSendDocWithController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	17-06-2022	*/
/* Descripción:	 Carga la ventana FXMLSendDocWith.fxml para enviar documento con cambios
/*******************************************************/
package vistasestudiante;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLSendDocWithController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Label lbUpDoc;
    @FXML
    private Label lbDownDoc;
    
    String route1;
    String route2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void clicBtnSendProcedure(ActionEvent event) {
        //Validacion del envio
        showAlert("Envio de Documentos", "El envio fue...", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void clicBtnLoadDocUp(ActionEvent event) {
        loadDocs(lbUpDoc,route2);
    }

    @FXML
    private void clicBtnLoadDocDown(ActionEvent event) {
        loadDocs(lbDownDoc,route1);
    }
    
    @FXML
    private void clicBtnBack(ActionEvent event) {
        changeWindow("FXMLMainStudent.fxml");
    }

    @FXML
    private void clicBtnHelp(ActionEvent event) {
        changeWindow("FXMLMakeQuestion.fxml");
    }

    @FXML
    private void clicBtnSendDocWithout(ActionEvent event) {
        changeWindow("FXMLSendDocWithout.fxml");
    }

    @FXML
    private void clicBtnSendDocIns(ActionEvent event) {
        changeWindow("FXMLNoInscription.fxml");
    }

    @FXML
    private void clicBtnDownload(ActionEvent event) {
    }

    
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource(window)));
            stage.setScene(mainScene);
            stage.setTitle("SEDIF");
            stage.getIcons().add(new Image("img/LOGOUV.png"));
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private void loadDocs(Label label, String route){
        Stage stage = (Stage) btnBack.getScene().getWindow();
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
    
    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
