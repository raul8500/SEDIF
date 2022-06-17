
package pojo;

public class Student {
    private String name;
    private String lastName;
    private String carrer;
    private int rol;
    private String nameRol;
    private String token;

    public Student() {
        
    }

    public Student(String name, String lastName, String carrer, int rol, String nameRol, String token) {
        this.name = name;
        this.lastName = lastName;
        this.carrer = carrer;
        this.rol = rol;
        this.nameRol = nameRol;
        this.token = token;
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

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
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
        return "student{" + "name=" + name + ", lastName=" + lastName + ", carrer=" + carrer + ", rol=" + rol + ", nameRol=" + nameRol + ", token=" + token + '}';
    }
}
