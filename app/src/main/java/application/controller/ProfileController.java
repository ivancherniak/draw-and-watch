package application.controller;

import DAOImpl.PictureDAOImpl;
import DAOImpl.UserDAOImpl;
import model.SimpleUser;
import model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * This class is used for handle events on Profile page
 */
@Controller
@SessionAttributes(value = {"loggedUser", "errorMsg"})
public class ProfileController {
    /**
     * Logger object
     */
    private static final Logger logger = Logger.getLogger(ProfileController.class);
    /**
     * UserDAOImpl instance
     */
    private UserDAOImpl userDAO;
    /**
     * PictureDAOImpl instance
     */
    private PictureDAOImpl pictureDAO;

    /**
     * Setter for UserDAOImpl instance
     *
     * @param userDAO UserDAOImpl instance
     */
    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Setter for PictureDAOImpl instance
     *
     * @param pictureDAO PictureDAOImpl instance
     */
    public void setPictureDAO(PictureDAOImpl pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    /**
     * Prints Profile page
     *
     * @param login login of user to whom this profile belongs to
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String goToProfile(@RequestParam(value = "login", required = false) String login, ModelMap model) {
        User user = (User) model.get("loggedUser");
        if (login == null || login.length() == 0) {
            if (user == null)  {
                logger.error("login parameter is empty while trying to go to Profile page");
                return "redirect:/errorPage";
            }
            login = user.getLogin();
        }
        try {
            String lgn = user != null ? user.getLogin() : null;
            SimpleUser userProfile = lgn != null && lgn.equals(login) ? user : userDAO.getSimpleUserByLogin(login);
            if (userProfile == null) {
                logger.error("Trying to get data about user that does not exist: login=" + login);
                return "redirect:/errorPage";
            }
            model.put("userProfile", userProfile);
            model.put("isProfileInFavourites", user != null && userDAO.isProfileInFavourites(lgn, login));
            model.put("userPictures", pictureDAO.getSimplePicturesByLogin(login));
        } catch (SQLException e) {
            logger.error("Error while trying to get data for Profile page", e);
            return "redirect:/errorPage";
        }
        return "profile";
    }

    /**
     * Adds profile to favourites and reloads Profile page. If profile is already in favourites, then it deletes from favourites.
     * If the user is not logged in, then it redirects to Signin page
     *
     * @param login login of user whom this profile belongs to
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = "/addToFavourites", method = RequestMethod.GET)
    public String addToFavourites(@RequestParam(value = "login") String login, ModelMap model) {
        if (!model.containsKey("loggedUser")) {
            logger.info("Unregistered user tries to add profile to favourites");
            return "redirect:/signin";
        }
        if (login == null || login.length() == 0) {
            logger.error("login parameter is empty while trying to add profile to favourites");
            return "redirect:/errorPage";
        }
        try {
            userDAO.addProfileToFavourites(((User) model.get("loggedUser")).getLogin(), login);
        } catch (SQLException e) {
            logger.error("Error while trying to add profile to favourites", e);
            return "redirect:/errorPage";
        }
        return "redirect:/profile?login=" + login;
    }
}
