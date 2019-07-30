package application.controller;

import DAOImpl.UserDAOImpl;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;
// TODO: 7/29/2019 ADD PASSWORD ENCRYPTION

/**
 * This class is used for handle events on Registration page
 */
@Controller
@SessionAttributes(value = "loggedUser")
public class RegistrationController {
    /**
     * UserDAOImpl instance
     */
    private UserDAOImpl userDAO;
    //private InputValidator validator;

    //public void setValidator(InputValidator validator) {
    //	this.validator = validator;
    //}

    /**
     * Setter for UserDAOImpl instance
     *
     * @param userDAO
     */
    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    // TODO: 7/29/2019 DELETE THIS METHOD
    @ModelAttribute("Money")
    public User getLoggedUser() {
        return null; //or however you create a default
    }

    /**
     * Prints Registration page
     *
     * @return name of a page to render
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String goToRegistration() {
        return "registration";
    }

    /**
     * Registers new user. If registration is successful it redirects to home page and add user to session. Otherwise it reloads Registration page and shows error message
     *
     * @param user  user trying to register
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, ModelMap model) {
        try {
            if (!userDAO.registerNewUser(user, model)) { // TODO: 7/29/2019 this may be useless. Consider to change method return type to void
                model.put("loggedUser", user);
                return "redirect:/home";
            }
        } catch (SQLException e) {
            model.addAttribute("SQLError", "Error while trying to register user. <br>Please try again");
            // TODO: 20.07.2019 add logger
        }
        return "registration";
    }
}
