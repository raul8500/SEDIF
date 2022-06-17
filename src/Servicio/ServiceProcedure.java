
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
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("x-token", token);
            
            Reader entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            
            for(int c; (c = entrada.read()) >= 0;){
                stringBuilder.append((char)c);
            }
            
            String respuesta = stringBuilder.toString();                
            JSONObject tramiteObtenido = new JSONObject(respuesta);
            boolean successRequest = tramiteObtenido.getBoolean("ok");
            
            if (successRequest){
                int status = tramiteObtenido.getInt("estadoEval");
                if (status != 0){
                    String periodo = tramiteObtenido.getString("periodo");
                    String nombre = tramiteObtenido.getString("nombre");
                    int mes = tramiteObtenido.getInt("mes");
                    int dia = tramiteObtenido.getInt("dia");
                    int anio = tramiteObtenido.getInt("anio");
                    Procedure newProcedure = new Procedure(status,periodo,nombre,dia,mes,anio);
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
