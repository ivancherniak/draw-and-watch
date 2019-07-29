package DAO;

import model.Comment;
import model.Picture;

import java.sql.SQLException;
import java.util.List;
// TODO: 7/29/2019 delete unused methods

/**
 * base methods for picture DAO
 */
public interface PictureDAO {

    //Picture getPictureById(long id) throws SQLException;

    /**
     * Gets all pictures painted by user
     *
     * @param login login of author
     * @return list of pictures
     * @throws SQLException when select statement fails
     */
    List<Picture> getPicturesByUser(String login) throws SQLException;

    List<Picture> getFavoritesPictures(String userName) throws SQLException;

    /**
     * Saves picture to database
     *
     * @param login       author of a picture
     * @param createdWhen when a picture was created
     * @param content     picture data
     * @throws SQLException when insert statement fails
     */
    void savePicture(String login, long createdWhen, String content) throws SQLException;

    void deletePicture(long id) throws SQLException;

    /**
     * Adds comment to picture
     *
     * @param pictureId id of a picture
     * @param login     login of a author
     * @param comment   text of a comments
     * @throws SQLException when insert statement fails
     */
    void addCommentToPicture(long pictureId, String login, String comment) throws SQLException;

    Long getLastCommentForPicture(long pictureId) throws SQLException; // TODO: 7/29/2019 should be private

    /**
     * Gets all comments under picture
     *
     * @param pictureId id of a picture
     * @return list of comments
     * @throws SQLException when select statement fails
     */
    List<Comment> getCommentsForPicture(long pictureId) throws SQLException;

    /**
     * Adds like to picture. If the picture is already likes, then it removes like
     *
     * @param pictureId id of a picture
     * @param login     login of a author
     * @throws SQLException when insert statement fails
     */
    void likeThePicture(long pictureId, String login) throws SQLException;

    /**
     * Gets amount of likes for picture
     *
     * @param pictureId id of a picture
     * @return amount of likes
     * @throws SQLException when select statement fails
     */
    int countLikes(long pictureId) throws SQLException;

    /**
     * Gets Picture instance by its id
     * @param id id of a picture
     * @return Picture instance
     * @throws SQLException when select statement fails
     */
    Picture getPictureById(long id) throws SQLException;
}
