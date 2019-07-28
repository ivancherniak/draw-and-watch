package DAO;

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

}
