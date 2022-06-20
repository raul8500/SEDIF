
package Servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pojo.FilePath;

public class ServiceEvaluation {
    
    public boolean setEvaluation(String token,int idProcedure, boolean validate , String observations) throws JSONException{
        try{
            URL url = new URL("http://127.0.0.1:7070/api/evaluaciones/"+idProcedure);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String data = "{\n" +
                    "    \"valido\":"+ validate+",\n" +
                    "    \"observaciones\": \""+observations+"\"\n" +
                    "}" ;
            System.out.println(data);
            connection.setRequestProperty("x-token", token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            OutputStream output = connection.getOutputStream();
            output.write(data.getBytes());
            output.flush();
            output.close();
            Reader imput = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for(int c; (c = imput.read()) >= 0;){
                stringBuilder.append((char)c);
            }
            String response = stringBuilder.toString();
            System.out.println(response);
            JSONObject obtainedFiles = new JSONObject(response);
            boolean successRequest = obtainedFiles.getBoolean("ok");
            return successRequest;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
