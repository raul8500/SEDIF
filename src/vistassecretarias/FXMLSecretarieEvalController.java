
package vistassecretarias;

import Servicio.ServiceDocument;
import Servicio.ServiceDownload;
import Servicio.ServiceEvaluation;
import Servicio.ServiceFile;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import org.json.JSONException;
import pojo.Document;
import pojo.ProcedureInfo;
import pojo.Secretarie;
import sedif.FXMLLoginController;


public class FXMLSecretarieEvalController implements Initializable {

    @FXML
    private RadioButton rbValidate;
    @FXML
    private RadioButton rbFeedback;
    final ToggleGroup group = new ToggleGroup();
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbName;
    @FXML
    private Label lbCarrer;
    @FXML
    private Label lbProcedure;
    @FXML
    private TableView<Document> tbFiles;
    @FXML
    private TableColumn<?, ?> clbNameFile;
    
    private ObservableList<Document> files;
    
    private String route1;
    @FXML
    private TextArea tfFeedback;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rbValidate.setToggleGroup(group);
        rbFeedback.setToggleGroup(group);
        ProcedureInfo pf = FXMLSecretariesProceduresController.procedureInfo;
        lbMatricula.setText(pf.getMatricula());
        lbName.setText(pf.getName()+","+pf.getLastname());
        lbCarrer.setText(pf.getCarrer());
        lbProcedure.setText(pf.getNameProcedure());
        files = FXCollections.observableArrayList();
        
        
        this.clbNameFile.setCellValueFactory(new PropertyValueFactory("nameFile"));
        Secretarie secretarie = FXMLLoginController.newSecretarie;
        String token = secretarie.getToken();
        ServiceDocument ssp = new ServiceDocument();
        try {
            ArrayList<Document> documentsInfo =  ssp.getDocuments(token,pf.getIdProcedure());
            documentsInfo.forEach((documentInfo)->files.add(documentInfo));
            this.tbFiles.setItems(files);
        } catch (JSONException ex) {
            Logger.getLogger(FXMLSecretariesProceduresController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @FXML
    private void clicBtnSend(ActionEvent event) {
        ServiceEvaluation se = new ServiceEvaluation();
        Secretarie sec = FXMLLoginController.newSecretarie;
        String token = sec.getToken();
        ProcedureInfo pf = FXMLSecretariesProceduresController.procedureInfo;
        int idProcedure= pf.getIdProcedure();
        String hola = tfFeedback.getText();
            
        if (rbValidate.isSelected()){
            
            try {
                se.setEvaluation(token, idProcedure, true, hola);
            } catch (JSONException ex) {
                Logger.getLogger(FXMLSecretarieEvalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(rbFeedback.isSelected()) {
            try {
                se.setEvaluation(token, idProcedure, false, hola);
            } catch (JSONException ex) {
                Logger.getLogger(FXMLSecretarieEvalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("No selecciono nada");
        }
    }

    @FXML
    private void clicBtnBack(ActionEvent event) {
        changeWindow("FXMLSecretariesProcedures.fxml");
    }
    
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) rbValidate.getScene().getWindow();
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
    private void clicBtnDownload(ActionEvent event) {
        int selection= tbFiles.getSelectionModel().getSelectedIndex();
        ServiceFile sf = new ServiceFile();
        Secretarie sec = FXMLLoginController.newSecretarie;
        String token = sec.getToken();
        if (selection >=0){
            int idFile= files.get(selection).getIdFile();
            try {
                String routeServer = sf.getRoute(token, idFile);
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
                    route1 = f.getAbsolutePath();
                }
                System.out.println(route1);
                System.out.println(routeServer);
                String nameFile = files.get(selection).getNameFile();
                ServiceDownload sd = new ServiceDownload();
                sd.download(routeServer, route1+"\\"+nameFile+".pdf");
            } catch (JSONException ex) {
                Logger.getLogger(FXMLSecretarieEvalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            changeWindow("FXMLSecretarieEval.fxml");
        }else{
            System.out.println("No seleccionaste nada");
        }
    }
}
