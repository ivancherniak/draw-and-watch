package DAO;

import model.Picture;
import model.PictureModel;
import model.SimplePicture;

import java.sql.SQLException;
import java.util.List;
// TODO: 7/29/2019 delete unused methods

/**
 * base methods for picture DAO
 */
public interface PictureDAO {
    /**
     * Gets all pictures painted by user
     *
     * @param login login of author
     * @return list of pictures
     * @throws SQLException when select statement fails
     */
    List<SimplePicture> getSimplePicturesByLogin(String login) throws SQLException;

    /**
     * Saves picture to database
     *
     * @param login       author of a picture
     * @param createdWhen when a picture was created
     * @param content     picture data
     * @throws SQLException when insert statement fails
     */
    void savePicture(String login, long createdWhen, String content) throws SQLException;

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

    /**
     * Gets picture, its likes and comments by id
     * @return PictureModel instance
     * @throws SQLException when select statement fails
     */
    PictureModel getPictureModelById(long id) throws SQLException;
}
