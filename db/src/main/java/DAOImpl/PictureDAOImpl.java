package DAOImpl;

import DAO.PictureDAO;
import DAO.Statements;
import model.Comment;
import model.Picture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// TODO: 7/29/2019 rewrite connection logic and closeAll method

/**
 * This class contains all operations with pictures
 */
public class PictureDAOImpl extends BaseDAO implements PictureDAO {
    /**
     * UserDAOImpl instance
     */
    private UserDAOImpl userDAO;

    protected PictureDAOImpl() throws SQLException { // TODO: 7/29/2019 probably should be deleted
    }

    /**
     * Setter for userDAO
     *
     * @param userDAO UserDAOImpl instance
     */
    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Getter for userDAO
     *
     * @return UserDAOImpl instance
     */
    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    /**
     * Gets Picture instance by its id
     *
     * @param id id of a picture
     * @return Picture instance
     * @throws SQLException when select statement fails
     */
    @Override
    public Picture getPictureById(long id) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_PICTURE_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Picture> list = parsePictures(resultSet);
            return list == null || list.size() == 0 ? null : list.get(0);
        } finally {
            closeAll();
        }
    }

    /**
     * Gets all pictures painted by user
     *
     * @param login login of author
     * @return list of pictures
     * @throws SQLException when select statement fails
     */
    @Override
    public List<Picture> getPicturesByUser(String login) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_PICTURE_BY_USER);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return parsePictures(resultSet);
        } finally {
            closeAll();
        }
    }

    @Override
    public List<Picture> getFavoritesPictures(String userName) throws SQLException {
        return null;
    }

    /**
     * Saves picture to database
     *
     * @param login       author of a picture
     * @param createdWhen when a picture was created
     * @param content     picture data
     * @throws SQLException when insert statement fails
     */
    @Override
    public void savePicture(String login, long createdWhen, String content) throws SQLException {
        try {
            getConnection();
            Clob imgClob = connection.createClob(); // TODO: 7/30/2019 probably free() method should be called in finally block to release clob
            imgClob.setString(1, content);
            statement = connection.prepareStatement(Statements.SAVE_PICTURE);
            statement.setString(1, login);
            statement.setDate(2, new Date(createdWhen));
            statement.setClob(3, imgClob);
            statement.execute();
        } finally {
            closeAll();
        }
    }

    @Override
    public void deletePicture(long id) throws SQLException {

    }

    /**
     * Gets list of pictures from ResultSet
     *
     * @param resultSet result of a query
     * @return list of pictures
     * @throws SQLException
     */
    private List<Picture> parsePictures(ResultSet resultSet) throws SQLException {
        try {
            List<Picture> userPictures = new ArrayList<>();
            if (resultSet != null) { // TODO: 7/29/2019 probably useless check
                while (resultSet.next()) {
                    Clob clob = resultSet.getClob(4);
                    userPictures.add(new Picture(
                            resultSet.getLong(1),
                            userDAO.getUserProfile(resultSet.getString(2)),
                            resultSet.getDate(3),
                            clob.getSubString(1, (int) clob.length())
                    ));
                }
            }
            return userPictures;
        } finally {
            if (resultSet != null) resultSet.close();
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
                statement.setNull(2, Types.BIGINT);
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
     * Gets all comments under picture
     *
     * @param pictureId id of a picture
     * @return list of comments
     * @throws SQLException when select statement fails
     */
    @Override
    public List<Comment> getCommentsForPicture(long pictureId) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_COMMENTS_FOR_PICTURE);
            statement.setLong(1, pictureId);
            resultSet = statement.executeQuery();
            return parseComments();
        } finally {
            closeAll();
        }
    }

    /**
     * Gets list of comments from ResultSet
     *
     * @return list of comments
     * @throws SQLException
     */
    private List<Comment> parseComments() throws SQLException {
        List<Comment> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Comment(
                    resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return list;
    }

    /**
     * Checks whether picture is like by user
     *
     * @param pictureId id of a picture
     * @param login     login of a user
     * @return true if a picture is liked, false otherwise
     * @throws SQLException when select statement fails
     */
    private boolean isPictureLiked(long pictureId, String login) throws SQLException {
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(Statements.IS_PICTURE_LIKED);
            statement.setLong(1, pictureId);
            statement.setString(2, login);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        }
    }

    /**
     * Adds like to picture. If the picture is already likes, then it removes like
     *
     * @param pictureId id of a picture
     * @param login     login of a author
     * @throws SQLException when insert statement fails
     */
    @Override
    public void likeThePicture(long pictureId, String login) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(isPictureLiked(pictureId, login)
                    ? Statements.REMOVE_LIKE
                    : Statements.ADD_LIKE);
            statement.setLong(1, pictureId);
            statement.setString(2, login);
            statement.execute();
        } finally {
            closeAll();
        }
    }

    /**
     * Gets amount of likes for picture
     *
     * @param pictureId id of a picture
     * @return amount of likes
     * @throws SQLException when select statement fails
     */
    @Override
    public int countLikes(long pictureId) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.COUNT_LIKES);
            statement.setLong(1, pictureId);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } finally {
            closeAll();
        }
    }
}
