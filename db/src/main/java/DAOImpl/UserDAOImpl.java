package DAOImpl;

import DAO.Statements;
import DAO.UserDAO;
import model.SimpleUser;
import model.User;
import model.UserLoginModel;
import model.UserRegistrationModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
     * Gets main data of all users. It returns list of SimpleUser instances
     *
     * @return list of users
     * @throws SQLException when select statement fails
     */
    @Override
    public List<SimpleUser> getAllUsers() throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_MAIN_DATA_OF_ALL_USERS);
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
    private List<SimpleUser> parseUsers() throws SQLException {
        List<SimpleUser> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new SimpleUser(
                    resultSet.getString(1),
                    resultSet.getString(2)
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
    public boolean registerNewUser(UserRegistrationModel user, ModelMap model) throws SQLException {
        try {
            if (isUniqueLogin(user.getLogin(), model)) {
                getConnection();
                statement = connection.prepareStatement(Statements.INSERT_NEW_USER);
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getName());
                statement.setString(3, user.getPassword());
                return !statement.execute();
            }
            return false;
        } finally {
            closeAll();
        }
    }

    /**
     * Checks whether user's login is unique in database
     *
     * @param login user's login to check
     * @param model model to add error messages if occur
     * @return true if login is unique, and false otherwise
     * @throws SQLException when select statement fails
     */
    private boolean isUniqueLogin(String login, ModelMap model) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_USER_BY_LOGIN);
            statement.setString(1, login);
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
     * Gets main data of favourite profiles for current user
     *
     * @param login current user's login
     * @return list of users
     * @throws SQLException when select statement fails
     */
    @Override
    public List<SimpleUser> getFavouriteProfiles(String login) throws SQLException {
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
     * Adds profile to favourites for current user. If profile is already in favourites, then it removes from favourites
     *
     * @param login login of current user
     * @param likes login user to add to favourites
     * @throws SQLException when insert statement fails
     */
    @Override
    public void addProfileToFavourites(String login, String likes) throws SQLException {
        boolean isInFavourites = isProfileInFavourites(login, likes);
        try {
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

    /**
     * Gets full information about user
     *
     * @param user  user's login data
     * @param model model to put error messages if occur
     * @return instance of User
     * @throws SQLException when select statement fails
     */
    @Override
    public User getUserByLoginAndPassword(UserLoginModel user, ModelMap model) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_USER_DATA_BY_LOGIN);
            statement.setString(1, user.getLogin());
            resultSet = statement.executeQuery();
            String pass;
            if (!resultSet.next() || !new BCryptPasswordEncoder().matches(user.getPassword(), pass = resultSet.getString(2))) {
                model.put("invalidUser", "Incorrect login or password");
                return null;
            }
            return new User(user.getLogin(), resultSet.getString(1), pass);
        } finally {
            closeAll();
        }
    }

    /**
     * Gets main information about user
     *
     * @param login user's login
     * @return instance of SimpleUser
     * @throws SQLException when select statement fails
     */
    @Override
    public SimpleUser getSimpleUserByLogin(String login) throws SQLException {
        try {
            getConnection();
            statement = connection.prepareStatement(Statements.GET_MAIN_USER_DATA_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return resultSet.next() ? new SimpleUser(login, resultSet.getString(1)) : null;
        } finally {
            closeAll();
        }
    }
}
