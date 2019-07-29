package application.controller;

import DAOImpl.UserDAOImpl;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.sql.SQLException;
// TODO: 7/29/2019 ADD PASSWORD ENCRYPTION
/**
 * This class is used for handle events on Login page
 */
@Controller
@SessionAttributes(value = "loggedUser")
public class LoginController {
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
     * @param userDAO UserDAOImpl instance
     */
    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    // TODO: 7/29/2019 add javadoc after refactor
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("user") User user, ModelMap model) { // TODO: 7/29/2019 replace ModelAttribute parameter with RequestParam parameters
        try {
            if (userDAO.isUserExists(user, model)) {
                model.put("loggedUser", user);
                return "redirect:/home";
            }
        } catch (SQLException e) {
            model.addAttribute("SQLError", "Error while trying to register user. <br>Please try again");
            // TODO: 21.07.2019 add logger
        }
        return "signin";
    }

    /**
     * Deletes logged user from session and redirects to Home page
     *
     * @param status session status
     * @return name of a page to render
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(SessionStatus status) {
        status.setComplete();
        return "redirect:/home";
    }

    // TODO: 7/29/2019 DELETE THIS METHOD
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String goToSignin() {
        return "signin";
    }
}
