
package Servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import pojo.Procedure;


public class ServiceFile {
    
    public String getRoute(String token,int idFile) throws JSONException{
        try{
            URL url = new URL("http://127.0.0.1:7070/api/archivos/"+idFile);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-token", token);
            Reader imput = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            
            for(int c; (c = imput.read()) >= 0;){
                stringBuilder.append((char)c);
            }
            
            String response = stringBuilder.toString();                
            JSONObject obtainedFile = new JSONObject(response);
            boolean successRequest = obtainedFile.getBoolean("ok");
            
            if (successRequest){
                String route  = obtainedFile.getString("ruta");
                return route;
            }else{
                return "";
            }
        } catch (IOException ex) {
            System.out.println(ex);
            return "";
        }
    }
}
