package DAOImpl;

import DAO.Statements;
import DAO.UserDAO;
import model.User;
import org.springframework.ui.ModelMap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all operations with users
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {
    /**
     * Connection instance
     */
    private Connection connection;

    /**
     * Gets connection
     *
     * @throws SQLException
     */
    private void getConnection() throws SQLException {
        connection = jdbcTemplate.getDataSource().getConnection();
    }

    /**
     * Gets list of all users
     *
     * @return list of users
     * @throws SQLException when select statement fails
     */
    @Override
    public List<User> getAllUsers() throws SQLException {
        if (connection == null || connection.isClosed()) getConnection();
        try {
            return parseUsers(connection
                    .prepareStatement(Statements.GET_ALL_USERS)
                    .executeQuery());
        } finally {
            connection.close();
        }
    }

    /**
     * Gets list of user from ResultSet
     *
     * @param resultSet
     * @return list of users
     * @throws SQLException
     */
    private List<User> parseUsers(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new User(
                    resultSet.getString(2),
                    resultSet.getString(1),
                    null, null
            ));
        }
        return list;
    }

    /**
     * Registers new user
     *
     * @param user  user to register
     * @param model model to put error messages if occur
     * @return true if user is successfully registered, and false otherwise
     * @throws SQLException
     */
    @Override
    public boolean registerNewUser(User user, ModelMap model) throws SQLException {
        if (isUniqueLogin(user, model)) {
            if (connection == null || connection.isClosed()) getConnection();
            try {
                statement = connection.prepareStatement(Statements.INSERT_NEW_USER);
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getName());
                statement.setString(3, user.getPassword());
                return statement.execute();
            } finally {
                //if (statement != null) statement.close();
                connection.close();
            }
        }
        return false;
    }

    /**
     * Checks whether user's login is unique in database
     *
     * @param user  user to check
     * @param model model to add error messages if occur
     * @return true if login is unique, and false otherwise
     * @throws SQLException when select statement fails
     */
    private boolean isUniqueLogin(User user, ModelMap model) throws SQLException {
        if (connection == null || connection.isClosed()) getConnection();
        try {
            statement = connection.prepareStatement(Statements.GET_USER_BY_LOGIN);
            statement.setString(1, user.getLogin());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                model.addAttribute("invalidLogin", "This login is already in use");
                return false;
            }
            return true;
        } finally {
            connection.close();
            //if (statement != null) statement.close();
            //if (resultSet != null) resultSet.close();
        }
    }

    /**
     * Checks whether user exists in database
     *
     * @param user  user to check
     * @param model
     * @return true if user exists in database, false otherwise
     * @throws SQLException when select statement fails
     */
    @Override
    public boolean isUserExists(User user, ModelMap model) throws SQLException {
        if (connection == null || connection.isClosed()) getConnection();
        try {
            statement = connection.prepareStatement(Statements.SELECT_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setName(resultSet.getString(2));
                return true;
            } else {
                model.addAttribute("invalidUser", "Incorrect login or password");
                return false;
            }
        } finally {
            connection.close();
        }
    }

    /**
     * Gets list of favourite profiles for current user
     *
     * @param user current user
     * @return list of users
     * @throws SQLException when select statement fails
     */
    // TODO: 7/29/2019 rebuild method logic
    @Override
    public List<User> getFavouriteProfiles(User user) throws SQLException {
        if (connection == null || connection.isClosed()) getConnection();
        try {
            statement = connection.prepareStatement(Statements.GET_FAVOURITE_PROFILES_BY_USER_LOGIN);
            statement.setString(1, user.getLogin());
            return parseUsers(statement.executeQuery());
        } finally {
            connection.close();
        }
    }

    /**
     * Gets User instance by login
     *
     * @param login user's login
     * @return User instance
     * @throws SQLException when select statement fails
     */
    @Override
    public User getUserProfile(String login) throws SQLException {
        if (connection == null || connection.isClosed()) getConnection();
        try {
            statement = connection.prepareStatement(Statements.GET_USER_DATA_BY_LOGIN);
            statement.setString(1, login);
            List<User> list = parseUsers(statement.executeQuery());
            return list == null || list.size() == 0 ? null : list.get(0);
        } finally {
            connection.close();
        }
    }

    /**
     * Adds profile to favourites for current user
     *
     * @param login login of current user
     * @param likes login user to add to favourites
     * @throws SQLException when insert statement fails
     */
    @Override
    public void addProfileToFavourites(String login, String likes) throws SQLException {
        if (connection == null || connection.isClosed()) getConnection();
        try {
            statement = connection.prepareStatement(Statements.ADD_PROFILE_TO_FAVOURITES);
            statement.setString(1, login);
            statement.setString(2, likes);
            statement.execute();
        } finally {
            connection.close();
        }
    }

    /**
     * deletes profile from favourites for current user
     *
     * @param login login of current user
     * @param likes login of user to delete from favourites
     * @throws SQLException when delete statement fails
     */
    @Override
    public void deleteProfileFromFavourites(String login, String likes) throws SQLException {
        if (connection == null || connection.isClosed()) getConnection();
        try {
            statement = connection.prepareStatement(Statements.DELETE_PROFILE_FROM_FAVOURITES);
            statement.setString(1, login);
            statement.setString(2, likes);
            statement.execute();
            jdbcTemplate.getDataSource().getConnection().close(); // TODO: 7/29/2019 remove it
        } finally {
            connection.close();
        }
    }
}
