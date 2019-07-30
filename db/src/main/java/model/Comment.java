package model;

/**
 * This class represents comment instances
 */
// TODO: 7/29/2019 all fields should be final
public class Comment {
    /**
     * id of a comment
     */
    private final long commentId;
    /**
     * id of a picture the comment belongs to
     */
    private final long pictureId;
    /**
     * login of a user the comment belongs to
     */
    private final String login;
    /**
     * name of a user the comment belongs to
     */
    private final String name;
    /**
     * text of the comment
     */
    private final String commentData;

    /**
     * This constructor creates instance of Comment
     *
     * @param commentId   id of a comment
     * @param pictureId   id of a picture the comment belongs to
     * @param login       login of a user the comment belongs to
     * @param name        name of a user the comment belongs to
     * @param commentData text of the comment
     */
    public Comment(long commentId, long pictureId, String login, String name, String commentData) {
        this.commentId = commentId;
        this.pictureId = pictureId;
        this.login = login;
        this.name = name;
        this.commentData = commentData;
    }

    /**
     * Getter for commentId
     *
     * @return id of a comment
     */
    public long getCommentId() {
        return commentId;
    }

    /**
     * Getter for pictureId
     *
     * @return id of a picture the comment belongs to
     */
    public long getPictureId() {
        return pictureId;
    }

    /**
     * Getter for login
     *
     * @return login of a user the comment belongs to
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter for name
     *
     * @return name of a user the comment belongs to
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for commentData
     *
     * @return text of the comment
     */
    public String getCommentData() {
        return commentData;
    }
}
