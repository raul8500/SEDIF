
package pojo;
//status = 0 no iniciado
//1 = en espera de evaluacion;
//2 requiere cambios
//3 valido a mimir;

public class Procedure {
    private int status;
    private String perior;
    private String nameProcedure;
    private int day;
    private int month;
    private int year;
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Procedure(boolean error) {
        this.error = error;
    }

    public Procedure(int status, String perior, String nameProcedure, int day, int month, int year) {
        this.status = status;
        this.perior = perior;
        this.nameProcedure = nameProcedure;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Procedure(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPerior() {
        return perior;
    }

    public void setPerior(String perior) {
        this.perior = perior;
    }

    public String getNameProcedure() {
        return nameProcedure;
    }

    public void setNameProcedure(String nameProcedure) {
        this.nameProcedure = nameProcedure;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Procedure{" + "status=" + status + ", perior=" + perior + ", nameProcedure=" + nameProcedure + ", day=" + day + ", month=" + month + ", year=" + year + '}';
    }
}
