package model;

import java.util.Date;

/**
 * This class represents picture instances
 */
public class Picture {
    /**
     * id of a picture
     */
    private final long id;
    /**
     * Who painted a picture
     */
    private final User paintedBy;
    /**
     * Picture data
     */
    private String content;
    /**
     * When picture was painted
     */
    private final Date createdWhen;

    /**
     * Getter for id
     *
     * @return picture id
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for painted picture
     *
     * @return who painted picture
     */
    public User getPaintedBy() {
        return paintedBy;
    }

    /**
     * Getter for content
     *
     * @return picture data
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter for content
     *
     * @param content picture content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter for createdWhen
     *
     * @return when picture was created
     */
    public Date getCreatedWhen() {
        return createdWhen;
    }

    /**
     * This constructor creates instance of Picture
     *
     * @param id          picture id
     * @param paintedBy   who painted picture
     * @param createdWhen when picture was created
     * @param content     picture data
     */
    public Picture(long id, User paintedBy, Date createdWhen, String content) {
        this.id = id;
        this.paintedBy = paintedBy;
        this.content = content;
        this.createdWhen = createdWhen;
    }

}
