/******************************************************************/
/* Archivo:     ServiceLogin.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	13-06-2022	*/
/* Fecha modificación:	19-06-2022	*/
/* Descripción:	 Recibe los datos (usuario) de la API
*/
/*******************************************************/

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
import pojo.User;

public class ServiceLogin {
    
    public User Authenticate(String email, String password){
        try{
            URL url = new URL("http://127.0.0.1:7070/api/auth");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            String data = "{\"email\": \"" + email +"\", \"password\": \""+ password + "\"}" ;
            //System.out.println(data);
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);
            OutputStream output = conexion.getOutputStream();
            output.write(data.getBytes());
            output.flush();
            output.close();
            
            if(conexion.getResponseCode() == 200){
                Reader imput = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                for(int c; (c = imput.read()) >= 0;){
                    stringBuilder.append((char)c);
                }
                
                String response = stringBuilder.toString();                
                JSONObject obtainedUser = new JSONObject(response);
                boolean isLogged = obtainedUser.getBoolean("ok") ;
                if (isLogged){
                    String token = obtainedUser.getString("token");
                    JSONObject userInfo = obtainedUser.getJSONObject("userInfo");
                    int numRol = userInfo.getInt("rol");
                    User newUser = new User(token,userInfo,true);
                    return newUser;
                }else{
                    User newUser = new User(null,null,false);
                    return newUser;
                }
            }
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServiceLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServiceLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        User newUser = new User(null,null,false);
        return newUser;
    }
}