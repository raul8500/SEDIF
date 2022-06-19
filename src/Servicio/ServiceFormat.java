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
import pojo.Format;
import pojo.Procedure;
import pojo.ProcedureInfo;

public class ServiceFormat {
    public ArrayList<Format> getFormats(String token) throws JSONException{
        try{
            URL url = new URL("http://127.0.0.1:7070/api/formatos");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-token", token);
            Reader imput = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            
            for(int c; (c = imput.read()) >= 0;){
                stringBuilder.append((char)c);
            }
            
            String response = stringBuilder.toString();                
            JSONObject obtainedFormats = new JSONObject(response);
            boolean successRequest = obtainedFormats.getBoolean("ok");
            
            if (successRequest){
                System.out.println(obtainedFormats.toString());
                JSONArray fromatsJson = obtainedFormats.getJSONArray("formatos");
                ArrayList<Format>  formats = new ArrayList<Format>();
                
                for (int i=0;i<fromatsJson.length();i++){
                    String nameformat = fromatsJson.getJSONObject(i).getString("nombre");
                    int idFormat =  fromatsJson.getJSONObject(i).getInt("idFormato");
                    int type = fromatsJson.getJSONObject(i).getInt("tipo");
                    String route = fromatsJson.getJSONObject(i).getString("ruta");
                  
                    
                    Format format = new Format(idFormat,nameformat,route,type);
                    formats.add(format);
                }
                return formats;
                
            }else{
                ArrayList<Format>  formats = new ArrayList<Format>();
                return formats;
            }
        } catch (IOException ex) {
            System.out.println(ex);
            ArrayList<Format>  formats = new ArrayList<Format>();
            return formats;
        }
    }
    
}
