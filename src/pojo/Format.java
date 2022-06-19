package pojo;

public class Format {
    
    private int idFormat;
    private String nameFormat;
    private String route;
    private int type;

    public Format(int idFormat, String nameFormat, String route, int type) {
        this.idFormat = idFormat;
        this.nameFormat = nameFormat;
        this.route = route;
        this.type = type;
    }

    public Format() {
    }

    public int getIdFormat() {
        return idFormat;
    }

    public void setIdFormat(int idFormat) {
        this.idFormat = idFormat;
    }

    public String getNameFormat() {
        return nameFormat;
    }

    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getType() {
        return type;
    }

    public void setTipo(int tipo) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Format{" + "idFormat=" + idFormat + ", nameFormat=" + nameFormat + ", route=" + route + ", tipo=" + type + '}';
    }
}
