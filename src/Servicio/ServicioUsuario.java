package Servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class ServicioUsuario {
    
    public int Autenticar(String correo, String contrasenia){
        try{
            URL url = new URL("http://127.0.0.1:7070/api/auth");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            String datos = "{\"email\": \"" + correo +"\", \"password\": \""+ contrasenia + "\"}" ;
            System.out.println(datos);
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);
            OutputStream output = conexion.getOutputStream();
            output.write(datos.getBytes());
            output.flush();
            output.close();
            
            if(conexion.getResponseCode() == 200){
                Reader entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                for(int c; (c = entrada.read()) >= 0;){
                    stringBuilder.append((char)c);
                }
                System.out.println("Arriba los knick");
                String respuesta = stringBuilder.toString();
                System.out.println(respuesta);
                
                JSONObject usuarioObtenido = new JSONObject(respuesta);
                boolean isLogged = usuarioObtenido.getBoolean("ok") ;
                if (isLogged){
                    String token = usuarioObtenido.getString("token");
                    JSONObject userInfo = usuarioObtenido.getJSONObject("userInfo");
                    int numRol = userInfo.getInt("rol");
                    return numRol;
                }else{
                    return -1;
                }
                //{"ok":true,"token":"312ewadaw",
                //"userInfo":{"rol":0,"nombres":"Yuriana","apellidos":"Lopez Vazquez",
                //"Carrera":"Ingeniería de Software","nombreRol":"Estudiante"}}
            }
            
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 3;
    }
}