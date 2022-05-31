/******************************************************************/
/* Archivo:     FXMLSendDocWithoutController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	10-06-2021	*/
/* Descripción:	 Carga la ventana FXMLSendDocWithout.fxml con los campos de enviar documentos sin cambios
/*******************************************************/

package sedif.vistas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FXMLSendDocWithoutController implements Initializable {
    
    @FXML
    private Button btnBack;
    @FXML
    private Label lbHorario;
    @FXML
    private Label lbBaucher;
    @FXML
    private Label lbDocEval;
    private String route1;
    private String route2;
    private String route3;
    

    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicBtnNoInscription(ActionEvent event) {
        changeWindow("FXMLNoInscription.fxml");
    }

    @FXML
    private void clicBtnSendDocWith(ActionEvent event) {
        changeWindow("FXMLSendDocWith.fxml");
    }

    @FXML
    private void clicBtnHelp(ActionEvent event) {
        changeWindow("");
    }

    @FXML
    private void clicBtnBack(ActionEvent event) {
        changeWindow("FXMLMain.fxml");
    }

    @FXML
    private void clicBtnSendProcedure(ActionEvent event) {
        //Validacion del envio
        showAlert("Envio de Documentos", "El envio fue...", Alert.AlertType.INFORMATION);
    }
    
    @FXML
    private void clicBtnLoadBaucher(ActionEvent event) {
        loadDocs(lbBaucher, route1);
    }

    @FXML
    private void clicBtnLoadSchedule(ActionEvent event) {
        loadDocs(lbHorario, route2);
    }

    @FXML
    private void clicBtnLoadDocEval(ActionEvent event) {
        loadDocs(lbDocEval, route3);
    }
    
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource(window)));
            stage.setScene(scenePrincipal);
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
            new ExtensionFilter("Image Files", "*.docx", "*.doc", "*.pdf"),
            new ExtensionFilter("All Files", "*.*"));
        
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
