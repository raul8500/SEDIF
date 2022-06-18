/******************************************************************/
/* Archivo:     Secretarie.java	 */
/* Programador: Raul Arturo Peredo Estudillo  */
/* Fecha:	19-05-2022	*/
/* Fecha modificación:	17-06-2022	*/
/* Descripción:	 Clase para la creacion de objetos Secretaria
*/
/*******************************************************/

package pojo;

public class Secretarie {
    private int rol;
    private String name;
    private String lastName;
    private String nameRol;
    private String token;

    public Secretarie(int rol, String name, String lastName, String nameRol, String token) {
        this.rol = rol;
        this.name = name;
        this.lastName = lastName;
        this.nameRol = nameRol;
        this.token = token;
    }
    
    public Secretarie() {
    }
    
    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameRol() {
        return nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    @Override
    public String toString() {
        return "secretarie{" + "rol=" + rol + ", name=" + name + ", lastName=" + lastName + ", nameRol=" + nameRol + ", token=" + token + '}';
    }
    
    
}
