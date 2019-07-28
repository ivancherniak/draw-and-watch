package model;

import java.util.Date;

public class Picture {
    private long id;
    private User paintedBy;
    private String content;
    private Date createdWhen;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPaintedBy(User paintedBy) {
        this.paintedBy = paintedBy;
    }

    public User getPaintedBy() {
        return paintedBy;
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

    public Picture(long id, User paintedBy, Date createdWhen, String content) {
        this.id = id;
        this.paintedBy = paintedBy;
        this.content = content;
        this.createdWhen = createdWhen;
    }

}
