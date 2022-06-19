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
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pojo.Procedure;
import pojo.ProcedureInfo;

/**
 *
 * @author peres
 */
public class ServiceSecretarieProcedure {
    public ArrayList<ProcedureInfo> getProcedures(String token) throws JSONException{
       try{
            URL url = new URL("http://127.0.0.1:7070/api/tramite/secretaria");
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
                System.out.println(obtainedProcedure.toString());
                JSONArray proceduresJson = obtainedProcedure.getJSONArray("tramites");
                ArrayList<ProcedureInfo>  procedures = new ArrayList<ProcedureInfo>();
                
                for (int i=0;i<proceduresJson.length();i++){
                    String lastname = proceduresJson.getJSONObject(i).getString("apellidos");
                    int status =  proceduresJson.getJSONObject(i).getInt("estado");
                    int idProcedure = proceduresJson.getJSONObject(i).getInt("idTramites");
                    String period = proceduresJson.getJSONObject(i).getString("periodo");
                    String name = proceduresJson.getJSONObject(i).getString("nombres");
                    String nameProcedure = proceduresJson.getJSONObject(i).getString("nombre");
                    String matricula = proceduresJson.getJSONObject(i).getString("matricula");
                    int idProcedureType = proceduresJson.getJSONObject(i).getInt("idTipo");
                    int month = proceduresJson.getJSONObject(i).getInt("mes");
                    int secretarieID = proceduresJson.getJSONObject(i).getInt("secretariaID");
                    int semester = proceduresJson.getJSONObject(i).getInt("semestre");
                    String carrer = proceduresJson.getJSONObject(i).getString("carrera");
                    int day = proceduresJson.getJSONObject(i).getInt("dia");
                    int year = proceduresJson.getJSONObject(i).getInt("anio");
                    
                    ProcedureInfo procedureInfo = new ProcedureInfo(secretarieID,matricula,name,lastname,semester,carrer,idProcedure,nameProcedure,idProcedureType, period,year,month,day,status);
                    procedures.add(procedureInfo);
                }
                return procedures;
                
            }else{
                ArrayList<ProcedureInfo>  procedures = new ArrayList<ProcedureInfo>();
                return procedures;
            }
        
        } catch (IOException ex) {
            System.out.println(ex);
            ArrayList<ProcedureInfo>  procedures = new ArrayList<ProcedureInfo>();
            return procedures;
        }
    }
}
