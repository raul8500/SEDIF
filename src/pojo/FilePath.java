
package pojo;

public class FilePath {
    
    private String bdPath;
    private int type;

    public FilePath(String bdPath, int type) {
        this.bdPath = bdPath;
        this.type = type;
    }

    public FilePath() {
    }

    public String getBdPath() {
        return bdPath;
    }

    public void setBdPath(String bdPath) {
        this.bdPath = bdPath;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "File{" + "bdPath=" + bdPath + ", idFile=" + type + '}';
    }
    
    
}
