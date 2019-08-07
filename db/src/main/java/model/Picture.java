package model;

import java.util.Date;

/**
 * This class the whole information about picture
 */
public class Picture extends SimplePicture {
    /**
     * Who painted a picture
     */
    private SimpleUser paintedBy;
    /**
     * When picture was painted
     */
    private Date createdWhen;

    /**
     * Getter for painted picture
     *
     * @return who painted picture
     */
    public SimpleUser getPaintedBy() {
        return paintedBy;
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
    public Picture(long id, SimpleUser paintedBy, Date createdWhen, String content) {
        super(id, content);
        this.paintedBy = paintedBy;
        this.createdWhen = createdWhen;
    }

    /**
     * Setter for createdWhen
     *
     * @param createdWhen when the picture was created
     */
    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }

    /**
     * Setter for paintedBy
     * @param paintedBy author of a picture
     */
    public void setPaintedBy(SimpleUser paintedBy) {
        this.paintedBy = paintedBy;
    }
}
