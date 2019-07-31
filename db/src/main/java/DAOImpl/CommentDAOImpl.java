package DAOImpl;

import DAO.CommentDAO;
import DAO.Statements;
import model.Comment;
import model.SimpleUser;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all operations with comments
 */
public class CommentDAOImpl extends BaseDAO implements CommentDAO {
    /**
     * Gets list of comments under picture
     *
     * @param id picture id
     * @return list of comments
     * @throws SQLException when select statement fails
     */
    @Override
    public List<Comment> getCommentsByPictureId(long id) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_COMMENTS_FOR_PICTURE);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            return parseComments();
        } finally {
            closeAll();
        }
    }

    /**
     * Adds comment to picture
     *
     * @param pictureId id of a picture
     * @param login     login of a author
     * @param comment   text of a comments
     * @throws SQLException when insert statement fails
     */
    @Override
    public void addCommentToPicture(long pictureId, String login, String comment) throws SQLException {
        try {
            Long parent = getLastCommentForPicture(pictureId);
            getConnection();
            statement = connection.prepareStatement(Statements.ADD_COMMENT_TO_PICTURE);
            statement.setLong(1, pictureId);
            if (parent != null) {
                statement.setLong(2, parent.longValue());
            } else {
                statement.setNull(2, Types.DECIMAL);
            }
            statement.setString(3, login);
            statement.setString(4, comment);
            statement.execute();
        } finally {
            closeAll();
        }
    }

    /**
     * Gets id of the last comment under picture
     *
     * @param pictureId id of a picture
     * @return id of the last comment
     * @throws SQLException when select statement fails
     */
    public Long getLastCommentForPicture(long pictureId) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_LAST_COMMENT_FOR_PICTURE);
            statement.setLong(1, pictureId);
            resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getLong(1) : null;
        } finally {
            closeAll();
        }
    }

    /**
     * Gets list of comments from result set
     *
     * @return list of comments
     * @throws SQLException
     */
    private List<Comment> parseComments() throws SQLException {
        List<Comment> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Comment(
                    new SimpleUser(
                            resultSet.getString(1),
                            resultSet.getString(2)),
                    resultSet.getString(3)
            ));
        }
        return list;
    }
}
