package DAO;

import model.Comment;

import java.sql.SQLException;
import java.util.List;

/**
 * base methods for comments DAO
 */
public interface CommentDAO {
    /**
     * Gets list of comments under picture
     *
     * @param id picture id
     * @return list of comments
     * @throws SQLException when select statement fails
     */
    List<Comment> getCommentsByPictureId(long id) throws SQLException;

    /**
     * Adds comment to picture
     *
     * @param pictureId id of a picture
     * @param login     login of a author
     * @param comment   text of a comments
     * @throws SQLException when insert statement fails
     */
    void addCommentToPicture(long pictureId, String login, String comment) throws SQLException;
}
