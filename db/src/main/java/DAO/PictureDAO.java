package DAO;

import model.Comment;
import model.Picture;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PictureDAO {

    //Picture getPictureById(long id) throws SQLException;
    List<Picture> getPictureByUser(String userName) throws SQLException;
    List<Picture> getFavoritesPictures(String userName) throws SQLException;
    void savePicture(String userName, long createdWhen, String content) throws SQLException;
    void deletePicture(long id) throws SQLException;
    void addCommentToPicture(long pictureId, String login, String comment) throws SQLException;
    Long getLastCommentForPicture(long pictureId) throws SQLException;
    List<Comment> getCommentsForPicture(long pictureId) throws SQLException;
}
