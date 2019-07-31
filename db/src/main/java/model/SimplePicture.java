package model;

/**
 * This class is used to contain main data of picture when there is no need to store the whole data
 */
public class SimplePicture {
    /**
     * id of a picture
     */
    protected long id;
    /**
     * Picture data
     */
    protected String content;

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
     * Getter for id
     *
     * @return picture id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id picture id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * This constructor creates instance of SimplePicture
     * @param id picture id
     * @param content picture data
     */
    public SimplePicture(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
