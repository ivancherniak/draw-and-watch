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
     * Gets list of all users
     *
     * @return list of users
     * @throws SQLException when select statement fails
     */
    @Override
    public List<User> getAllUsers() throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_ALL_USERS);
            resultSet = statement.executeQuery();
            return parseUsers();
        } finally {
            closeAll();
        }
    }

    /**
     * Gets list of user from ResultSet
     *
     * @return list of users
     * @throws SQLException
     */
    private List<User> parseUsers() throws SQLException {
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
        try {
            if (isUniqueLogin(user, model)) {
                getConnection();
                statement = connection.prepareStatement(Statements.INSERT_NEW_USER);
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getName());
                statement.setString(3, user.getPassword());
                return statement.execute();
            }
            return false;
        } finally {
            closeAll();
        }
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
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_USER_BY_LOGIN);
            statement.setString(1, user.getLogin());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                model.addAttribute("invalidLogin", "This login is already in use");
                return false;
            }
            return true;
        } finally {
            closeAll();
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
        try {
            getConnection();
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
            closeAll();
        }
    }

    /**
     * Gets list of favourite profiles for current user
     *
     * @param login current user's login
     * @return list of users
     * @throws SQLException when select statement fails
     */
    // TODO: 7/29/2019 rebuild method logic
    @Override
    public List<User> getFavouriteProfiles(String login) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_FAVOURITE_PROFILES_BY_USER_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return parseUsers();
        } finally {
            closeAll();
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
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_USER_DATA_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            List<User> list = parseUsers();
            return list == null || list.size() == 0 ? null : list.get(0);
        } finally {
            closeAll();
        }
    }

    /**
     * Adds profile to favourites for current user. If profile is already in favourites, then it removes from favourites
     *
     * @param login login of current user
     * @param likes login user to add to favourites
     * @throws SQLException when insert statement fails
     */
    @Override
    public void addProfileToFavourites(String login, String likes) throws SQLException {
        try {
            boolean isInFavourites = isProfileInFavourites(login, likes);
            getConnection();
            statement = connection.prepareStatement(isInFavourites
                    ? Statements.DELETE_PROFILE_FROM_FAVOURITES
                    : Statements.ADD_PROFILE_TO_FAVOURITES);
            statement.setString(1, login);
            statement.setString(2, likes);
            statement.execute();
        } finally {
            closeAll();
        }
    }

    /**
     * Checks whether profile in favourites for current user
     *
     * @param login current user's login
     * @param likes login of user to check
     * @return true if profile is in favourites, false otherwise
     * @throws SQLException when select statement fails
     */
    @Override
    public boolean isProfileInFavourites(String login, String likes) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.IS_PROFILE_IN_FAVOURITES);
            statement.setString(1, login);
            statement.setString(2, likes);
            resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) != 0;
        } finally {
            closeAll();
        }
    }
}
