package model;

/**
 * This class represents comment instances
 */
public class Comment {
    /**
     * The author of comment
     */
    private SimpleUser user;
    /**
     * text of the comment
     */
    private String commentData;

    /**
     * This constructor creates instance of Comment
     *
     * @param user        author of comment
     * @param commentData text of the comment
     */
    public Comment(SimpleUser user, String commentData) {
        this.user = user;
        this.commentData = commentData;
    }

    /**
     * Getter for commentData
     *
     * @return text of the comment
     */
    public String getCommentData() {
        return commentData;
    }

    /**
     * Setter for user
     *
     * @param user author of a comment
     */
    public void setUser(SimpleUser user) {
        this.user = user;
    }

    /**
     * Setter for commentData
     *
     * @param commentData text of a comment
     */
    public void setCommentData(String commentData) {
        this.commentData = commentData;
    }
}
