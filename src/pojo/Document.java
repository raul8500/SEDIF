
package pojo;

public class Document {
    
    private int idFile;
    private String nameFile;

    public Document(int idFile, String nameFile) {
        this.idFile = idFile;
        this.nameFile = nameFile;
    }

    public Document() {
    }

    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    @Override
    public String toString() {
        return "Document{" + "idFile=" + idFile + ", nameFile=" + nameFile + '}';
    }
}
