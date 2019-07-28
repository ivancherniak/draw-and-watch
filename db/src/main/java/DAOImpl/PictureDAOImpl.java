package DAOImpl;

import DAO.PictureDAO;
import DAO.Statements;
import model.Picture;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PictureDAOImpl extends BaseDAO implements PictureDAO {
    //@Override
    public Picture getPictureById(long id) throws SQLException {

        return null;
    }

    @Override
    public List<Picture> getPictureByUser(String userName) throws SQLException{
        try {
            statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.GET_PICTURE_BY_USER);
            statement.setString(1, userName);
            return parsePictures(statement.executeQuery());

        } finally {
            //if (statement != null) statement.close();
        }
    }

    @Override
    public List<Picture> getFavoritesPictures(String userName) throws SQLException {
        return null;
    }

    @Override
    public void savePicture(String userName, long createdWhen, String content) throws SQLException{
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            Clob imgClob = connection.createClob();
            imgClob.setString(1, content);
            statement = connection.prepareStatement(Statements.SAVE_PICTURE);
            statement.setString(1, userName);
            statement.setDate(2, new Date(createdWhen));
            statement.setClob(3, imgClob);
            statement.execute();
        } finally {
            //if (statement != null) statement.close();
        }
    }

    @Override
    public void deletePicture(long id) throws SQLException {

    }

    private List<Picture> parsePictures(ResultSet resultSet) throws SQLException {
        List<Picture> userPictures = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                Clob clob = resultSet.getClob(4);
                userPictures.add(new Picture(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        clob.getSubString(1, (int) clob.length())
                ));
            }
        }
        return userPictures;
    }
}
