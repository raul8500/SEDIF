
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
import pojo.Document;
import pojo.Format;

public class ServiceDocument {
    public ArrayList<Document> getDocuments(String token,int idProcedure) throws JSONException{
        try{
            URL url = new URL("http://127.0.0.1:7070/api/documentos/"+idProcedure);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-token", token);
            Reader imput = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            
            for(int c; (c = imput.read()) >= 0;){
                stringBuilder.append((char)c);
            }
            
            String response = stringBuilder.toString();                
            JSONObject obtainedDocuments = new JSONObject(response);
            boolean successRequest = obtainedDocuments.getBoolean("ok");
            
            if (successRequest){
                System.out.println(obtainedDocuments.toString());
                JSONArray documentsJson = obtainedDocuments.getJSONArray("tramites");
                ArrayList<Document>  documents = new ArrayList<Document>();
                
                for (int i=0;i<documentsJson.length();i++){
                    JSONObject document = documentsJson.getJSONObject(i);
                    String name = document.getString("nombre");
                    int idFile = document.getInt("idArchivo");
                    documents.add(new Document(idFile,name));
                }
                return documents;
                
            }else{
                ArrayList<Document>  documents = new ArrayList<Document>();
                return documents;
            }
        } catch (IOException ex) {
            System.out.println(ex);
            ArrayList<Document>  documents = new ArrayList<Document>();
            return documents;
        }
    }
    
}
