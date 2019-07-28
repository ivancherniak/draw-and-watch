package model;

import java.util.Date;

public class Picture {
    private long id;
    private String paintedBy;
    private String content;
    private Date createdWhen;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaintedBy() {
        return paintedBy;
    }

    public void setPaintedBy(String paintedBy) {
        this.paintedBy = paintedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }

    public Picture() {

    }

    public Picture(long id, String paintedBy, Date createdWhen, String content) {
        this.id = id;
        this.paintedBy = paintedBy;
        this.createdWhen = createdWhen;
        this.content = content;

    }
}
