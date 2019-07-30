package DAO;

import model.User;
import org.springframework.ui.ModelMap;

import java.sql.SQLException;
import java.util.List;

/**
 * base methods for user DAO
 */
public interface UserDAO {
    /**
     * Gets list of all users
     *
     * @return list of users
     * @throws SQLException when select statement fails
     */
    List<User> getAllUsers() throws SQLException;

    /**
     * Registers new user
     *
     * @param user  user to register
     * @param model model to put error messages if occur
     * @return true if user is successfully registered, and false otherwise
     * @throws SQLException
     */
    boolean registerNewUser(User user, ModelMap model) throws SQLException;

    /**
     * Checks whether user exists in database
     *
     * @param user  user to check
     * @param model
     * @return true if user exists in database, false otherwise
     * @throws SQLException when select statement fails
     */
    boolean isUserExists(User user, ModelMap model) throws SQLException;

    /**
     * Gets list of favourite profiles for current user
     *
     * @param login current user's login
     * @return list of users
     * @throws SQLException when select statement fails
     */
    List<User> getFavouriteProfiles(String login) throws SQLException;

    /**
     * Gets User instance by login
     *
     * @param login user's login
     * @return User instance
     * @throws SQLException when select statement fails
     */
    User getUserProfile(String login) throws SQLException;

    /**
     * Adds profile to favourites for current user
     *
     * @param login login of current user
     * @param likes login user to add to favourites
     * @throws SQLException when insert statement fails
     */
    void addProfileToFavourites(String login, String likes) throws SQLException;

    /**
     * Checks whether profile in favourites for current user
     *
     * @param login current user's login
     * @param likes login of user to check
     * @return true if profile is in favourites, false otherwise
     * @throws SQLException when select statement fails
     */
    boolean isProfileInFavourites(String login, String likes) throws SQLException;
}
