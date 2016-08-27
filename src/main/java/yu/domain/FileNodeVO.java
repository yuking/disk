package yu.domain;


public class FileNodeVO {

    private long id;
    private String name;
    private boolean dir;
    private String size;
    private String date;

    FileNodeVO() {
    }

    FileNodeVO(long id, String name, boolean dir, String size, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.dir = dir;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileNodeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
