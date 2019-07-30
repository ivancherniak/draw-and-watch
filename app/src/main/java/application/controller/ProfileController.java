package application.controller;

import DAOImpl.PictureDAOImpl;
import DAOImpl.UserDAOImpl;
import model.User;
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
    public String goToProfile(@RequestParam(value = "login") String login, ModelMap model) {
        User user = (User) model.get("loggedUser");
        if (login == null || login.length() == 0) { // TODO: 7/29/2019 check this case. It may never happen
            if (user == null) return "redirect:/errorPage";
            login = user.getLogin(); // TODO: 7/30/2019 rewrite logic
        }
        try {
            model.put("userProfile", userDAO.getUserProfile(login));
            model.put("isProfileInFavourites", user != null && userDAO.isProfileInFavourites(user.getLogin(), login));
            model.put("userPictures", pictureDAO.getPicturesByUser(login));
        } catch (SQLException e) {
            // TODO: 27.07.2019 add logger
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
        //User user = (User) model.get("loggedUser"); // TODO: 7/29/2019 check the refactor
        if (!model.containsKey("loggedUser")) return "redirect:/signin";
        if (login == null || login.length() == 0)
            return "redirect:/errorPage"; // TODO: 7/29/2019 check this case. It may never happen
        try {
            //model.put("userProfile", userDAO.getUserProfile(login));
            //model.put("isProfileInFavourites", userDAO.getFavouriteProfiles((User) model.get("loggedUser")).size() != 0); // TODO: 7/29/2019 wrong method, create new one for checking if profile is in favourites
            userDAO.addProfileToFavourites(((User) model.get("loggedUser")).getLogin(), login);
        } catch (SQLException e) {
            // TODO: 27.07.2019 add logger
            return "redirect:/errorPage";
        }
        return "redirect:/profile?login=" + login;
    }
}
