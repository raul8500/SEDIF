package vistasestudiante;

import Servicio.ServiceDownload;
import Servicio.ServiceFormat;
import Servicio.ServiceProcedure;
import Servicio.ServiceSecretarieProcedure;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import org.json.JSONException;
import pojo.Format;
import pojo.ProcedureInfo;
import pojo.Secretarie;
import pojo.Student;
import sedif.FXMLLoginController;
import static sedif.FXMLLoginController.procedure;


public class FXMLDownloadFilesController implements Initializable {
    private ObservableList<Format> formats;
    @FXML
    private TableView<Format> tbFormats;
    @FXML
    private TableColumn<?, ?> tbNameFormat;
    private String route;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formats = FXCollections.observableArrayList();
        
        this.tbNameFormat.setCellValueFactory(new PropertyValueFactory("nameFormat"));
        
        Student student = FXMLLoginController.newStudent;
        String token = student.getToken();
        ServiceFormat sf = new ServiceFormat();
        
        try {
            ArrayList<Format> formatsInfo =  sf.getFormats(token);;
            formatsInfo.forEach((formatInfo)->formats.add(formatInfo));
            this.tbFormats.setItems(formats);
            
        } catch (JSONException ex) {
            Logger.getLogger(FXMLDownloadFilesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clicBtnDownload(ActionEvent event) {
        int selection = tbFormats.getSelectionModel().getSelectedIndex();
        
        if (selection >=0){
            String ruta = formats.get(selection).getRoute();
            ServiceDownload sd = new ServiceDownload();
            loadDocs();
            String nameFile = formats.get(selection).getNameFormat();
            
            sd.download(ruta, route+"\\"+nameFile+".pdf");  
        }else{
            System.out.println("No seleccionaste nada");
        }
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
                changeWindow("/vistasestudiante/FXMLMainStudent.fxml");
            }else if (procedure.getStatus()==1){
                changeWindow("/vistasestudiante/FXMLMainStatus.fxml");
            } 
        }
    }
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) tbFormats.getScene().getWindow();
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
    
    private void loadDocs(){
        
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setFileSelectionMode(
        JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showDialog(null,
        "Select Directory");
        if (option == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            // if the user accidently click a file, then select the parent directory.
            if (!f.isDirectory()) {
                f = f.getParentFile();
            }
            route = f.getAbsolutePath();
        }
    }
}
