
package pojo;


public class ProcedureInfo {
    private int secretarieID;
    private String matricula;
    private String name;
    private String lastname;
    private int semester;
    private String carrer; 
    private int idProcedure;
    private String nameProcedure;
    private int idProcedureType;
    private String period;
    private int year;
    private int month;
    private int day;
    private int status;
    private boolean error;

    public ProcedureInfo(int secretarieID, String matricula, String name, String lastname, int semester, String carrer, int idProcedure, String nameProcedure, int idProcedureType, String period, int year, int month, int day, int status) {
        this.secretarieID = secretarieID;
        this.matricula = matricula;
        this.name = name;
        this.lastname = lastname;
        this.semester = semester;
        this.carrer = carrer;
        this.idProcedure = idProcedure;
        this.nameProcedure = nameProcedure;
        this.idProcedureType = idProcedureType;
        this.period = period;
        this.year = year;
        this.month = month;
        this.day = day;
        this.status = status;
    }

    public ProcedureInfo(boolean error) {
        this.error = error;
    }
    
    public ProcedureInfo() {
    }

    public int getSecretarieID() {
        return secretarieID;
    }

    public void setSecretarieID(int secretarieID) {
        this.secretarieID = secretarieID;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public int getIdProcedure() {
        return idProcedure;
    }

    public void setIdProcedure(int idProcedure) {
        this.idProcedure = idProcedure;
    }

    public String getNameProcedure() {
        return nameProcedure;
    }

    public void setNameProcedure(String nameProcedure) {
        this.nameProcedure = nameProcedure;
    }

    public int getIdProcedureType() {
        return idProcedureType;
    }

    public void setIdProcedureType(int idProcedureType) {
        this.idProcedureType = idProcedureType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ProcedureInfo{" + "secretarieID=" + secretarieID + ", matricula=" + matricula + ", name=" + name + ", lastname=" + lastname + ", semester=" + semester + ", carrer=" + carrer + ", idProcedure=" + idProcedure + ", nameProcedure=" + nameProcedure + ", idProcedureType=" + idProcedureType + ", period=" + period + ", year=" + year + ", month=" + month + ", day=" + day + ", status=" + status + '}';
    }
}
