package DAO;

import services.SimpleUser;
import model.User;
import services.UserLoginModel;
import services.UserRegistrationModel;
import org.springframework.ui.ModelMap;

import java.sql.SQLException;
import java.util.List;

/**
 * base methods for user DAO
 */
public interface UserDAO {
    /**
     * Gets main data of all users. It returns list of SimpleUser instances
     *
     * @return list of users
     * @throws SQLException when select statement fails
     */
    List<SimpleUser> getAllUsers() throws SQLException;

    /**
     * Registers new user
     *
     * @param user  user to register
     * @param model model to put error messages if occur
     * @return true if user is successfully registered, and false otherwise
     * @throws SQLException
     */
    boolean registerNewUser(UserRegistrationModel user, ModelMap model) throws SQLException;


    /**
     * Gets main data of favourite profiles for current user
     *
     * @param login current user's login
     * @return list of users
     * @throws SQLException when select statement fails
     */
    List<SimpleUser> getFavouriteProfiles(String login) throws SQLException;

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

    /**
     * Gets full information about user
     *
     * @param user  user's login data
     * @param model model to put error messages if occur
     * @return instance of User
     * @throws SQLException when select statement fails
     */
    User getUserByLoginAndPassword(UserLoginModel user, ModelMap model) throws SQLException;

    /**
     * Gets main information about user
     *
     * @param login user's login
     * @return instance of SimpleUser
     * @throws SQLException when select statement fails
     */
    SimpleUser getSimpleUserByLogin(String login) throws SQLException;
}
