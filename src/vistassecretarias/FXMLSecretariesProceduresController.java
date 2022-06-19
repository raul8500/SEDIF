/******************************************************************/
/* Archivo:     FXMLSecretariesProceduresController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	17-06-2022	*/
/* Descripción:	 Carga la ventana FXMLSecretariesProcedures.fxml con los tramites que tiene que validar
/*******************************************************/


package vistassecretarias;

import Servicio.ServiceSecretarieProcedure;
import static java.awt.PageAttributes.MediaType.C;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONException;
import pojo.ProcedureInfo;
import pojo.Secretarie;
import sedif.FXMLLoginController;


public class FXMLSecretariesProceduresController implements Initializable {

    @FXML
    private Button btnLogout;
    @FXML
    private TableColumn<?, ?> clNameStudent;
    @FXML
    private TableColumn<?, ?> clProcedureType;
    @FXML
    private TableColumn<?, ?> clMatricula;
    @FXML
    private ComboBox<?> cbFilter;
    
    private ObservableList<ProcedureInfo> persons;
    @FXML
    private TableView<ProcedureInfo> tbProcedures;
    
    public static ProcedureInfo personInfo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        persons = FXCollections.observableArrayList();
        
        this.clNameStudent.setCellValueFactory(new PropertyValueFactory("name"));
        this.clProcedureType.setCellValueFactory(new PropertyValueFactory("nameProcedure"));
        this.clMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        
        Secretarie secretarie = FXMLLoginController.newSecretarie;
        String token = secretarie.getToken();
        ServiceSecretarieProcedure ssp = new ServiceSecretarieProcedure();
        try {
            ArrayList<ProcedureInfo> proceduresInfo =  ssp.getProcedures(token);
            proceduresInfo.forEach((procedureInfo)->persons.add(procedureInfo));
            this.tbProcedures.setItems(persons);
        } catch (JSONException ex) {
            Logger.getLogger(FXMLSecretariesProceduresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void clicBtnLogout(ActionEvent event) {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clicBtnFilter(ActionEvent event) {
        
    }
    
    private void changeWindow(String window){
        try {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
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
    private void clicBtnEval(ActionEvent event) {
        int selection= tbProcedures.getSelectionModel().getSelectedIndex();
        
        if (selection >=0){
            personInfo = persons.get(selection);
            changeWindow("FXMLSecretarieEval.fxml");
        }else{
            System.out.println("No seleccionaste nada");
        }
    }
}
