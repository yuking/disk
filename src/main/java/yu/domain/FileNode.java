package yu.domain;

import groovy.transform.ToString;
import jdk.nashorn.internal.codegen.MapCreator;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class FileNode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long parentId;
    private String filename;
    private String username;
    private boolean dir;
    private long size;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FileNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", filename='" + filename + '\'' +
                ", username='" + username + '\'' +
                ", dir=" + dir +
                ", size=" + size +
                ", createTime=" + createTime +
                '}';
    }

    public FileNodeVO getVO() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(createTime);
        String sizeStr;
        String suffix;
        if(size >= 1048576) {
            sizeStr = Math.ceil(size/104857.6)/10.0 + " M";
        } else if(size >= 1024) {
            sizeStr = Math.ceil(size/102.4)/10.0 + " K";
        } else {
            sizeStr = size + " B";
        }

        return new FileNodeVO(id, filename, dir, sizeStr, date);
    }
}