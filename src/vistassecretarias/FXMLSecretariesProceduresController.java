/******************************************************************/
/* Archivo:     FXMLSecretariesProceduresController.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	17-06-2022	*/
/* Descripción:	 Carga la ventana FXMLSecretariesProcedures.fxml con los tramites que tiene que validar
/*******************************************************/


package vistassecretarias;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
