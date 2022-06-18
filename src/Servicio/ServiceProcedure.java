/******************************************************************/
/* Archivo:     ServiceProcedure.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	13-06-2022	*/
/* Fecha modificación:	19-06-2022	*/
/* Descripción:	 Recibe los datos (tramite) de la API
*/
/*******************************************************/

package Servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;
import pojo.User;
import pojo.Procedure;

public class ServiceProcedure {
    public Procedure getStatusProcedure(String token) throws JSONException{
        try{
            URL url = new URL("http://127.0.0.1:7070/api/tramite");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-token", token);
            Reader imput = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            
            for(int c; (c = imput.read()) >= 0;){
                stringBuilder.append((char)c);
            }
            
            String response = stringBuilder.toString();                
            JSONObject obtainedProcedure = new JSONObject(response);
            boolean successRequest = obtainedProcedure.getBoolean("ok");
            
            if (successRequest){
                int status = obtainedProcedure.getInt("estadoEval");
                if (status != 0){
                    String peroid = obtainedProcedure.getString("periodo");
                    String name = obtainedProcedure.getString("nombre");
                    int month = obtainedProcedure.getInt("mes");
                    int day = obtainedProcedure.getInt("dia");
                    int year = obtainedProcedure.getInt("anio");
                    Procedure newProcedure = new Procedure(status,peroid,name,day,month,year);
                    return newProcedure;
                }else{
                    Procedure newProcedure = new Procedure(status);
                    return newProcedure;
                }
            }else{
                Procedure newProcedure = new Procedure(true);
                return newProcedure;
            }
        } catch (IOException ex) {
            System.out.println(ex);
            Procedure newProcedure = new Procedure(true);
            return newProcedure;
        }
    }
}
